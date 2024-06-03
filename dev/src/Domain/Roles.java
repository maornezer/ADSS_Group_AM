package Domain;

import java.util.ArrayList;
import java.util.List;

public class Roles {
    private int id;
    private List<String> roles;

    public Roles(int id) {
        this.id = id;
        roles = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public void addRole(String role){
        this.roles.add(role);
    }

    public boolean removeRole(String role){
        return this.roles.remove(role);
    }

    public boolean checkRole(String role){
        for(String r: this.roles){
            if(r.equals(role))
                return true;
        }
        return false;
    }

    public boolean checkShiftManager(){
        for(String r: this.roles){
            if(r.equals("Domain.Shift Manager"))
                return true;
        }
        return false;
    }

    public String toString(){
        if(roles.isEmpty())
            return "";
        else
            return this.roles.get(0);
    }


}
