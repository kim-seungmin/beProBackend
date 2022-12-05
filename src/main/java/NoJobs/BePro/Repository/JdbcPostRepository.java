package NoJobs.BePro.Repository;

import NoJobs.BePro.Domain.Post;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcPostRepository implements PostRepository {
    private final DataSource dataSource;
    public JdbcPostRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public Post save(Post post) {
        return null;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Post> findBytitle(String title) {
        return Optional.empty();
    }

    @Override
    public List<Post> findBytitleAndTag(String title, String[] tags) {

        if (tags.length == 0) {
            String sql = "SELECT * FROM post LEFT JOIN member ON post.post_uploader = member.member_idnum WHERE post.post_title LIKE ?";
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                conn = getConnection();
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, "?" + title + "?");
                rs = pstmt.executeQuery();
                List<Post> posts = new ArrayList<>();
                while (rs.next()) {
                    Post post = new Post();
                    post.setId(rs.getInt("post_id"));
                    post.setTitle(rs.getString("post_title"));
                    post.setUploaderId(rs.getString("member_id"));
                    post.setUploadtime(rs.getString("post_uploadtime"));
                    post.setDetail(rs.getString("post_detail"));
                    post.setView(rs.getInt("post_view"));
                    post.setLike(rs.getInt("post_like"));
                    posts.add(post);
                }
                return posts;
            } catch (Exception e) {
                throw new IllegalStateException(e);
            } finally {
                close(conn, pstmt, rs);
            }

        } else {
            String sql = "SELECT * FROM post LEFT JOIN member ON post.post_uploader = member.member_idnum WHERE post.post_title LIKE ? AND post.post_id IN (SELECT tag_post_id FROM tag WHERE ";
            for (int i = 1; i < tags.length; i++) {
                sql += "tag_detail= ? OR ";
            }
            sql += "tag_detail= ?)";
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                conn = getConnection();
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, "%" + title + "%");
                int index = 2;
                for (String tag : tags) {
                    pstmt.setString(index++, tag);
                }
                rs = pstmt.executeQuery();
                List<Post> posts = new ArrayList<>();
                while (rs.next()) {
                    Post post = new Post();
                    post.setId(rs.getInt("post_id"));
                    post.setTitle(rs.getString("post_title"));
                    post.setUploaderId(rs.getString("member_nickname"));
                    post.setUploadtime(rs.getString("post_uploadtime"));
                    post.setDetail(rs.getString("post_detail"));
                    post.setView(rs.getInt("post_view"));
                    post.setLike(rs.getInt("post_like"));
                    posts.add(post);
                }
                return posts;
            } catch (Exception e) {
                throw new IllegalStateException(e);
            } finally {
                close(conn, pstmt, rs);
            }
        }
    }

    @Override
    public List<Post> findAll() {
        return null;
    }

    @Override
    public List<Post> findBetween(long start, long end) {
        return null;
    }

    @Override
    public List<Post> findBetween(long start, long end, String category) {
        String sql = "SELECT * FROM post LEFT JOIN member ON post.post_uploader = member.member_idnum WHERE post_category = ? ORDER BY post_uploadtime DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, category);
            rs = pstmt.executeQuery();
            List<Post> posts = new ArrayList<>();
            for(long i=0;i<start;i++){
                if(!rs.next())return posts;
            }
            for(long i=start;i<end;i++){
                if(rs.next()) {
                    Post post = new Post();
                    post.setId(rs.getInt("post_id"));
                    post.setTitle(rs.getString("post_title"));
                    post.setUploaderId(rs.getString("member_id"));
                    post.setUploadtime(rs.getString("post_uploadtime"));
                    post.setDetail(rs.getString("post_detail"));
                    post.setView(rs.getInt("post_view"));
                    post.setLike(rs.getInt("post_like"));
                    posts.add(post);
                }else{ return posts;}
            }
            return posts;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public List<Post> findByView(long start, long end, String category) {
        String sql = "SELECT * FROM post LEFT JOIN member ON post.post_uploader = member.member_idnum WHERE post_category = ? ORDER BY post_view DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, category);
            rs = pstmt.executeQuery();
            List<Post> posts = new ArrayList<>();
            for(long i=0;i<start;i++){
                if(!rs.next())return posts;
            }
            for(long i=start;i<end;i++){
                if(rs.next()) {
                    Post post = new Post();
                    post.setId(rs.getInt("post_id"));
                    post.setTitle(rs.getString("post_title"));
                    post.setUploaderId(rs.getString("member_id"));
                    post.setUploadtime(rs.getString("post_uploadtime"));
                    post.setDetail(rs.getString("post_detail"));
                    post.setView(rs.getInt("post_view"));
                    post.setLike(rs.getInt("post_like"));
                    posts.add(post);
                }else{ return posts;}
            }
            return posts;
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
