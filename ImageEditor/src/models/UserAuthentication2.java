package models;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserAuthentication2 {
    public static final String USER_DATA_FILE = "resources/user_data.txt";
    private Map<String, User> users;

    public UserAuthentication2() {
        users = loadUserData();
    }

    private Map<String, User> loadUserData() {
    	
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(USER_DATA_FILE))) {
            return (Map<String, User>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to load user data: " + e.getMessage());
        }
        return new HashMap<>();
    }

    private void saveUserData() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(USER_DATA_FILE))) {
            outputStream.writeObject(users);
        } catch (IOException e) {
            System.err.println("Failed to save user data: " + e.getMessage());
        }
    }

    public void signup(String nickname, String password, String realName, String surname, int age, String email) {
        if (users.containsKey(nickname)) {
            throw new IllegalArgumentException("Nickname is already taken. Please choose a different nickname.");
        }

        User user = new User(nickname, password, realName, surname, age, email);
        users.put(nickname, user);
        saveUserData();
    }

    public User login(String nickname, String password) {
        if (users.containsKey(nickname)) {
            User user = users.get(nickname);
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
