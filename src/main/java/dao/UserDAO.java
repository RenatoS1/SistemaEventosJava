package dao;

import model.User;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static final String FILE_PATH = "users.data";
    
    public void saveUsers(List<User> users) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (User user : users) {
                writer.println(serializeUser(user));
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }
    
    public List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        File file = new File(FILE_PATH);
        
        if (!file.exists()) {
            return users;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                User user = deserializeUser(line);
                if (user != null) {
                    users.add(user);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar usuários: " + e.getMessage());
        }
        
        return users;
    }
    
    private String serializeUser(User user) {
        return String.format("%s|%s|%s|%s|%s",
                           user.getId(), user.getName(), user.getEmail(), 
                           user.getPhone(), user.getCity());
    }
    
    private User deserializeUser(String line) {
        try {
            String[] parts = line.split("\\|");
            if (parts.length >= 5) {
                return new User(parts[0], parts[1], parts[2], parts[3], parts[4]);
            }
        } catch (Exception e) {
            System.err.println("Erro ao deserializar usuário: " + e.getMessage());
        }
        return null;
    }
} 