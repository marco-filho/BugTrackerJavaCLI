package Application.Model;

import Auth.Authorization.Role; 

public class User extends BaseModel {
    private String name;
    private String username;
    private String password;
    private Role role;
    private String team;

    //remove later
    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return name + "(@" + username + ")" + " | " + team;
    }
    

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }


    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }


    public void setTeam(String team) {
        this.team = team;
    }

    public String getTeam() {
        return team;
    }
}
