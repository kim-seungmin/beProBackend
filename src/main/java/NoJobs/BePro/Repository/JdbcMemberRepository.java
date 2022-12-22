package NoJobs.BePro.Repository;

import NoJobs.BePro.Domain.Member;
import NoJobs.BePro.Form.MemberForm;
import org.springframework.jdbc.datasource.DataSourceUtils;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcMemberRepository implements MemberRepository {
    private final DataSource dataSource;
    public JdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public Member save(Member member) {
        String sql = "insert into member(member_id, member_password, member_email, member_nickname, member_major, member_isPro) values(?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, member.getId());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getEmail());
            pstmt.setString(4, member.getName());
            pstmt.setString(5, member.getMajor());
            if(member.getPro()){
                pstmt.setInt(6, 1);
            }else{
                pstmt.setInt(6, 0);
            }

            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                member.setId(rs.getString(1));
            } else {
                throw new SQLException("id 조회 실패");
            }
            return member;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
    @Override
    public Optional<Member> findById(String id) {
        String sql = "select * from member where member_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                Member member = new Member();
                member.setId(rs.getString("member_id"));
                member.setPassword(rs.getString("member_password"));
                member.setName(rs.getString("member_nickname"));
                member.setIdNum(rs.getLong("member_idnum"));
                member.setMajor(rs.getString("member_major"));
                member.setToken(rs.getString("member_token"));
                member.setAdmin(rs.getInt("member_admin"));
                return Optional.of(member);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
    @Override
    public List<Member> findAll() {
        String sql = "select * from member";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<Member> members = new ArrayList<>();
            while(rs.next()) {
                Member member = new Member();
                member.setId(rs.getString("member_id"));
                member.setName(rs.getString("member_nickname"));
                member.setIdNum(rs.getLong("member_idnum"));
                member.setMajor(rs.getString("member_major"));
                member.setToken(rs.getString("member_token"));
                members.add(member);
            }
            return members;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public boolean isUploader(long id, int index) {
        String sql = "select * from post where post_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, index);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                Member member = new Member();
                if(id==rs.getLong("post_uploader")){
                    return true;
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
        return false;
    }

    @Override
    public boolean updateMember(MemberForm form) {
        String sql = "UPDATE post SET member_password = ?, member_email = ?, member_nickname = ?, member_major = ?, member_isPro = ? WHERE member_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, form.getPw());
            pstmt.setString(2, form.getEmail());
            pstmt.setString(3, form.getNick());
            pstmt.setString(4, form.getMajor());
            if(form.getIsPro()) {
                pstmt.setInt(5, 1);
            }else{
                pstmt.setInt(5, 0);
            }
            pstmt.setString(6, form.getId());
            pstmt.execute();
            rs = pstmt.getGeneratedKeys();
            return true;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public boolean updateToken(Member member, Optional<String> token) {
        if(token.isPresent()) {
            String sql = "update member set member_token = ? where member_id = ?";
            Connection conn = null;
            PreparedStatement pstmt = null;
            try {
                conn = getConnection();
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, token.get());
                pstmt.setString(2, member.getId());
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
        }else{
            String sql = "update member set member_token = null where member_id = ?";
            Connection conn = null;
            PreparedStatement pstmt = null;
            try {
                conn = getConnection();
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, member.getId());
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
        }

        return true;
    }

    @Override
    public Optional<Member> findByName(String name) {
        String sql = "select * from member where member_nickname = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                Member member = new Member();
                member.setId(rs.getString("member_id"));
                member.setName(rs.getString("member_nickname"));
                return Optional.of(member);
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
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