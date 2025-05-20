package Model;

public class Data {
    private int id;
    private String name;
    private String email;
    private String path;

    public Data(int id, String name, String email, String path) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.path = path;
    }

    public Data(int id, String name, String path) {
        this.id = id;
        this.name = name;
        this.path = path;
    }

    public int id() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String name() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String email() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String path() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    
}
