package NoJobs.BePro.Domain;
import java.util.List;

public class Post {
    private long id;
    private String title;
    private String uploaderId;
    private String uploadtime;
    private String detail;
    private long view;
    private long like;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUploaderId() {
        return uploaderId;
    }

    public void setUploaderId(String uploader) {
        this.uploaderId = uploader;
    }

    public String getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(String uploadtime) {
        this.uploadtime = uploadtime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public long getView() {
        return view;
    }

    public void setView(long view) {
        this.view = view;
    }

    public long getLike() {
        return like;
    }

    public void setLike(long like) {
        this.like = like;
    }
}
