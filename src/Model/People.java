package Model;

import java.util.ArrayList;


public class People {
    private ArrayList<Admin> admin = new ArrayList<>();
    private ArrayList<User> allUsers = new ArrayList<>();

    public void addUser(String username, String password) {
        allUsers.add(new User(username, password, 0, false));
    }

    public void setAdmin(String username, String password, boolean haveAdmin) {
        this.admin.add(new Admin(username, password, haveAdmin));
    }

    public void removeAdmin() {
        if (admin != null && admin.size() > 0){
            admin.remove(0);
        }
    }

    public Admin getAdmin() {
        if (admin != null && admin.size() > 0)
            return this.admin.get(0);
        return null;
    }

    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public User findUserByUsername(String username) {
        if (getAllUsers() != null) {
            for (int i = 0; i < getAllUsers().size(); i++) {
                if (getAllUsers().get(i) != null
                        && getAllUsers().get(i).getUsername().equals(username))
                    return getAllUsers().get(i);
            }
        }
        return null;
    }

    public void removeUserAccount(String username) {
        allUsers.remove(findUserByUsername(username));
    }


}
