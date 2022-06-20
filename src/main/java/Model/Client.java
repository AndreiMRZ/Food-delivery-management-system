package Model;

import java.io.Serializable;
import java.util.Objects;

public class Client implements Serializable, Comparable<Client> {
    private int id;
    private String username;
    private String pass;

    public Client(int id, String username, String pass){
        this.id = id;
        this.username = username;
        this.pass = pass;
    }

    public Client(String username, String pass) {
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

    @Override
    public int compareTo(Client o) {
        return Integer.compare(id, o.getId());
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        Client client = (Client) obj;
        return Objects.equals(username, client.username) && Objects.equals(pass, client.pass);
    }
    public int hashCode(){return Objects.hash(id, username, pass);}
    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
