package Model;

import java.io.Serializable;

public class Employee implements Serializable {
    private int id;
    private String username;
    private String pass;

    public Employee(int id, String username, String pass){
        this.id = id;
        this.username = username;
        this.pass = pass;
    }

    public Employee(String username, String pass){
        this.username = username;
        this.pass = pass;
    }
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPass() {
        return pass;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
