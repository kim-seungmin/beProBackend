package NoJobs.BePro.Controller;

public class MemberForm {
    private String nick;
    private String id;
    private String email;
    private String pw;
    private boolean isPro;
    private String major;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public boolean getIsPro() {
        return isPro;
    }

    public void setIsPro(boolean pro) {
        this.isPro = pro;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}