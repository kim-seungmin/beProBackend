package  NoJobs.BePro.Domain;

public class Member {
    private String id;
    private String password;
    private String name;
    private String email;
    private String major;
    private String token;
    private Boolean isPro;
    private int admin;
    private long idNum;

    public Boolean getPro() {
        return isPro;
    }

    public void setPro(Boolean pro) {
        isPro = pro;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getIdNum() {return idNum;}

    public void setIdNum(long idNum) {this.idNum = idNum;}

    public String getMajor() {return major;}

    public void setMajor(String major) {this.major = major;}

    public String getToken() {return token;}

    public void setToken(String token) {this.token = token;}

    public String getId() {return id;}

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}