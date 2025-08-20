package service;

import model.User;
import dao.UserDAO;
import java.util.*;

public class UserService {
    private List<User> users;
    private UserDAO userDAO;
    
    public UserService() {
        this.userDAO = new UserDAO();
        this.users = userDAO.loadUsers();
    }
    
    public void addUser(String name, String email, String phone, String city) {
        String id = generateUserId();
        User user = new User(id, name, email, phone, city);
        users.add(user);
        saveUsers();
    }
    
    public User getUserById(String id) {
        return users.stream()
                   .filter(u -> u.getId().equals(id))
                   .findFirst()
                   .orElse(null);
    }
    
    public User getUserByEmail(String email) {
        return users.stream()
                   .filter(u -> u.getEmail().equalsIgnoreCase(email))
                   .findFirst()
                   .orElse(null);
    }
    
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
    
    public boolean updateUser(String id, String name, String email, String phone, String city) {
        User user = getUserById(id);
        if (user != null) {
            user.setName(name);
            user.setEmail(email);
            user.setPhone(phone);
            user.setCity(city);
            saveUsers();
            return true;
        }
        return false;
    }
    
    private String generateUserId() {
        return "USR" + System.currentTimeMillis();
    }
    
    private void saveUsers() {
        userDAO.saveUsers(users);
    }
} 