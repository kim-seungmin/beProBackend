package NoJobs.BePro.Form;

public class CommentForm {
    private String commentDetail;
    private int commentIndex;
    private boolean isAnony;
    private String commentNick;
    private String commentId;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommentIndex() {
        return commentIndex;
    }

    public void setCommentIndex(int commentIndex) {
        this.commentIndex = commentIndex;
    }

    public String getCommentDetail() {
        return commentDetail;
    }

    public void setCommentDetail(String commentDetail) {
        this.commentDetail = commentDetail;
    }

    /*public boolean isAnony() {
        return isAnony;
    }*/
    public boolean getIsAnony(){
        return this.isAnony;
    }
    public void setIsAnony(boolean isAnony){
        this.isAnony = isAnony;
    }

    public void setAnony(boolean anony) {
        isAnony = anony;
    }

    public String getCommentNick() {
        return commentNick;
    }

    public void setCommentNick(String commentNick) {
        this.commentNick = commentNick;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }
}
