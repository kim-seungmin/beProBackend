package NoJobs.BePro.Repository;

import NoJobs.BePro.Domain.Member;
import NoJobs.BePro.Domain.Post;
import NoJobs.BePro.Form.PostForm;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

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
    public Map findByPostId(Long id,String board) {
        Map<String, Object> reslut = new HashMap<>();
        String sql = "SELECT * FROM post LEFT JOIN member ON post.post_uploader = member.member_idnum WHERE post_id = ? AND post.post_category = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            pstmt.setString(2, board);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                reslut.put("title", rs.getString("post_title"));
                reslut.put("id", rs.getString("post_id"));
                reslut.put("uploaderNick", rs.getString("member_nickname"));
                reslut.put("uploaderId",rs.getString("member_id"));
                reslut.put("view", rs.getString("post_view"));
                reslut.put("uploadtime", rs.getString("post_uploadtime"));
                reslut.put("like", rs.getString("post_like"));
                reslut.put("detail", rs.getString("post_detail"));
                reslut.put("tags", getTagById(rs.getString("post_id")));
                reslut.put("comment", getCommnetyId(rs.getString("post_id")));
                return reslut;
            }
            return null;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
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
                pstmt.setString(1, "%" + title + "%");
                rs = pstmt.executeQuery();
                List<Post> posts = new ArrayList<>();
                while (rs.next()) {
                    Post post = new Post();
                    post.setId(rs.getInt("post_id"));
                    post.setTitle(rs.getString("post_title"));
                    post.setUploaderNike(rs.getString("member_nickname"));
                    post.setUploadtime(rs.getString("post_uploadtime"));
                    post.setDetail(rs.getString("post_detail"));
                    post.setView(rs.getInt("post_view"));
                    post.setLike(rs.getInt("post_like"));
                    post.setCategory(rs.getString("post_category"));
                    post.setTags(getTagById(rs.getString("post_id")));
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
                    post.setUploaderNike(rs.getString("member_nickname"));
                    post.setUploadtime(rs.getString("post_uploadtime"));
                    post.setDetail(rs.getString("post_detail"));
                    post.setView(rs.getInt("post_view"));
                    post.setLike(rs.getInt("post_like"));
                    post.setCategory(rs.getString("post_category"));
                    post.setTags(getTagById(rs.getString("post_id")));
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

    private String[] getTagById(String post_id) {
        String sql = "SELECT * FROM tag WHERE tag_post_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<String> result = new ArrayList<>();
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, post_id);
            rs = pstmt.executeQuery();
            List<Post> posts = new ArrayList<>();
            while(rs.next()) {
                result.add(rs.getString("tag_detail"));
            }
            return result.toArray(new String[result.size()]);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    private List<Map> getCommnetyId(String post_id) {
        String sql = "SELECT * FROM comment LEFT JOIN member ON comment.comment_member_idnum = member.member_idnum WHERE comment_post_id = ?";
        List result = new ArrayList<Map>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, post_id);
            rs = pstmt.executeQuery();
            List<Post> posts = new ArrayList<>();
            while(rs.next()) {
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("commentDetail",rs.getString("comment_contents"));
                resultMap.put("commentUploadTime",rs.getString("comment_date"));
                if(rs.getInt("comment_isanonymous")==1){
                    resultMap.put("isAnony",true);
                }else{
                    resultMap.put("isAnony",false);
                }
                resultMap.put("commentNick",rs.getString("member_nickname"));
                resultMap.put("commentId",rs.getString("member_id"));
                resultMap.put("commentIndex",rs.getString("comment_id"));
                result.add(resultMap);
            }
            return result;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
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
                    post.setCategory(rs.getString("post_category"));
                    post.setTags(getTagById(rs.getString("post_id")));
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
                    post.setUploaderNike(rs.getString("member_nickname"));
                    post.setUploadtime(rs.getString("post_uploadtime"));
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
    public void insertPost(PostForm form, String category) {
        MemberRepository memberRepository = new JdbcMemberRepository(dataSource);
        Member uploader = memberRepository.findById(form.getUploaderId()).get();

        String sql = "INSERT INTO post(post_title, post_uploader, post_detail, post_category) VALUES(?,?,?,?) RETURNING post_id";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String postID;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, form.getTitle());
            pstmt.setLong(2, uploader.getIdNum());
            pstmt.setString(3, form.getDetail());
            pstmt.setString(4, category);
            pstmt.execute();
            rs = pstmt.getResultSet();
            if (rs.next()) {
                postID = (rs.getString(1));
            } else {
                throw new SQLException("등록 실패");
            }
            if(form.getTag()!=null) {
                sql = "INSERT INTO tag(tag_post_id, tag_detail) VALUES(?,?)";
                for (String tag : form.getTag()) {
                    pstmt = conn.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);
                    pstmt.setString(1, postID);
                    pstmt.setString(2, tag);
                    pstmt.executeUpdate();
                    rs = pstmt.getGeneratedKeys();
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public void updatePost(PostForm form, String category) {
        MemberRepository memberRepository = new JdbcMemberRepository(dataSource);
        Member uploader = memberRepository.findById(form.getUploaderId()).get();

        String sql = "UPDATE post SET post_title = ?, post_uploader = ?, post_detail = ?, post_category = ? WHERE post_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, form.getTitle());
            pstmt.setLong(2, uploader.getIdNum());
            pstmt.setString(3, form.getDetail());
            pstmt.setString(4, category);
            pstmt.setString(5, form.getId());
            pstmt.execute();
            rs = pstmt.getGeneratedKeys();

            sql="DELETE FROM tag WHERE tag_post_id = ? ";
            pstmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, form.getId());
            pstmt.execute();
            rs = pstmt.getGeneratedKeys();
            sql = "INSERT INTO tag(tag_post_id, tag_detail) VALUES(?,?)";
            for(String tag:form.getTag()){
                pstmt = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, form.getId());
                pstmt.setString(2, tag);
                pstmt.executeUpdate();
                rs = pstmt.getGeneratedKeys();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public List<Map> findAllIn(String board) {
        List<Map> resultList = new ArrayList<>();

        String sql = "SELECT * FROM post LEFT JOIN member ON post.post_uploader = member.member_idnum WHERE post_category = ? ORDER BY post_uploadtime DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, board);
            rs = pstmt.executeQuery();
            List<Post> posts = new ArrayList<>();
            while(rs.next()) {
                Map result = new HashMap<String, Object>();
                result.put("title",rs.getString("post_title"));
                result.put("id",rs.getInt("post_id"));
                result.put("uploaderNick",rs.getString("member_nickname"));
                result.put("view",rs.getInt("post_view"));
                result.put("uploadtime",rs.getString("post_uploadtime"));
                result.put("like",rs.getInt("post_like"));;
                resultList.add(result);
            }
            return resultList;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public List<Map> findByMember(String id) {
        List<Map> resultList = new ArrayList<>();
        String sql = "SELECT * FROM post LEFT JOIN member ON post.post_uploader = member.member_idnum WHERE member_id = ? ORDER BY post_uploadtime DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            List<Post> posts = new ArrayList<>();
            while(rs.next()) {
                Map result = new HashMap<String, Object>();
                result.put("title",rs.getString("post_title"));
                result.put("id",rs.getInt("post_id"));
                result.put("view",rs.getInt("post_view"));
                result.put("uploadtime",rs.getString("post_uploadtime"));
                result.put("detail",rs.getString("post_detail"));
                result.put("tags",getTagById(rs.getString("post_id")));
                result.put("category", rs.getString("post_category"));
                result.put("like",rs.getInt("post_like"));;
                resultList.add(result);
            }
            return resultList;
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
