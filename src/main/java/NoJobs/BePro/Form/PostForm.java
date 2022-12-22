package NoJobs.BePro.Form;

public class PostForm {
    String title;
    String[] tag;
    String detail;
    String uploaderId;
    String id;

    String board;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getTag() {
        return tag;
    }

    public void setTag(String[] tag) {
        this.tag = tag;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String context) {
        this.detail = context;
    }

    public String getUploaderId() {
        return uploaderId;
    }

    public void setUploaderId(String uploaderId) {
        this.uploaderId = uploaderId;
    }

    public void setBoard(String board) { this.board = board; }

    public String getBoard() { return board; }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
