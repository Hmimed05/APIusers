package fstt.hamza.exerciceAPI;



public class OneUser {
    private User data;

    public String getFname(){
        return data.getFirst_name();
    }
    public String getLname(){
        return data.getLast_name();
    }
    public String getEmail(){
        return data.getEmail();
    }
    public String getId_str(){
        return data.getId_str();
    }
    public Integer getId(){
        return data.getId();
    }
    public String getAvatar(){
        return data.getAvatar();
    }
    public String toString() {
        return "UserApiResponse [data=" + data + "]";
    }
}
