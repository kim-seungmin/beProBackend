package NoJobs.BePro.Domain;

public class Post {
    private long id;
    private String title;
    private String uploaderId;
    private String uploaderNike;
    private String uploadtime;
    private String detail;
    private String category;
    private String[] tags;
    private long view;
    private long like;

    public String getUploaderNike() {
        return uploaderNike;
    }

    public void setUploaderNike(String uploaderNike) {
        this.uploaderNike = uploaderNike;
    }

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

}
