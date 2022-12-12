package NoJobs.BePro.Form;

public class PostForm {
    String title;
    String[] tag;
    String detail;
    String uploaderId;
    String index;

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

    public String getContext() {
        return detail;
    }

    public void setContext(String context) {
        this.detail = context;
    }

    public String getId() {
        return uploaderId;
    }

    public void setId(String id) {
        this.uploaderId = id;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
