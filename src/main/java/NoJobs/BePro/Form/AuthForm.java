package NoJobs.BePro.Form;

public class AuthForm {
    private String id;
    private String index;
    private String token;
    private Boolean isEdit;
    private Boolean isSignin;
    private Boolean isAdmin;

    private String cate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean edit) {
        isEdit = edit;
    }

    public Boolean getIsSignin() {
        return isSignin;
    }

    public void setIsSignin(Boolean signin) {
        isSignin = signin;
    }

    public Boolean getIsAdmin() { return isAdmin; }

    public void setIsAdmin(Boolean admin) { isAdmin = admin; }

    public String getCate() { return cate; }

    public void setCate(String cate) { this.cate = cate; }
}
