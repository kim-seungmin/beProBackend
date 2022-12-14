package NoJobs.BePro.Repository;

import NoJobs.BePro.Domain.Comment;
import NoJobs.BePro.Form.CommentForm;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Map;

public class JdbcCommentRepository implements CommentRepository{
    private final DataSource dataSource;
    public JdbcCommentRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Map save(CommentForm form) {
        JdbcMemberRepository memberRepository = new JdbcMemberRepository(dataSource);

        String sql = "INSERT INTO comment(comment_member_idnum, comment_post_id, comment_contents, comment_isanonymous) VALUES(?,?,?,?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1, memberRepository.findByName(form.getCommentNick()).get().getIdNum());
            pstmt.setInt(2, form.getId());
            pstmt.setString(3,form.getCommentDetail());
            if(form.isAnony()){
                pstmt.setInt(4,1);
            }else{
                pstmt.setInt(4,0);
            }
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
        return Map.of("msg","댓글 등록에 성공했습니다.");
    }

    @Override
    public Map erase(int erase) {
        JdbcMemberRepository memberRepository = new JdbcMemberRepository(dataSource);

        String sql="DELETE FROM comment WHERE comment_id = ? ";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, erase);
            pstmt.execute();
            rs = pstmt.getGeneratedKeys();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
        return Map.of("msg","성공적으로 삭제되었습니다.");
    }

    @Override
    public Map update(int index, String detail) {
        String sql = "update comment set comment_contents = ? where comment_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, detail);
            pstmt.setInt(2, index);
            pstmt.execute();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return Map.of("msg","업데이트에 성공했습니다.");
    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }
    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs)
    {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }
}
