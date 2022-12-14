package NoJobs.BePro.Domain;

import NoJobs.BePro.Form.CommentForm;

public class Comment {
    private int id;
    private int uploader_id;
    private int post_id;
    private String date;
    private String contents;
    private boolean isAnonymous;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUploader_id() {
        return uploader_id;
    }

    public void setUploader_id(int uploader_id) {
        this.uploader_id = uploader_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
    }
}
