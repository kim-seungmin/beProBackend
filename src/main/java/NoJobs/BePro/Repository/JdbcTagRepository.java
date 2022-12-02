package NoJobs.BePro.Repository;

import NoJobs.BePro.Domain.Tag;
import org.springframework.jdbc.datasource.DataSourceUtils;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcTagRepository implements TagRepository {
    private final DataSource dataSource;

    public JdbcTagRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Tag save(Tag tag) {
        return null;
    }

    @Override
    public Optional<Tag> findByPostId(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Tag> findByDetail(String detail) {
        return Optional.empty();
    }

    @Override
    public List<Tag> findAll() {
        String sql = "select * from tag";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<Tag> tags = new ArrayList<>();
            while (rs.next()) {
                Tag tag = new Tag();
                tag.setId(rs.getInt("tag_id"));
                tag.setPostId(rs.getInt("tag_post_id"));
                tag.setDetail(rs.getString("tag_detail"));
                tags.add(tag);
            }
            return tags;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public List<String> getRank(int start, int end) {
        String sql = "SELECT tag_detail, COUNT(tag_detail) FROM tag GROUP BY tag_detail";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<String> result = new ArrayList<>();
            for(int i=0;i<start;i++){
                if(!rs.next())return result;
            }
            for(int i=start; i<end;i++){
                if(rs.next()){
                    result.add(rs.getString("tag_detail"));
                }else {
                    return result;
                }
            }
            return result;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
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
