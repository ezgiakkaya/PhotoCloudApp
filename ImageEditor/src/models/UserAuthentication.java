package models;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserAuthentication {
	public static final String USER_DATA_FILE = "resources/user_data.txt";

	private Map<String, User> users;
	private static User currentUser;

	public UserAuthentication() {
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

	/*private void saveUserData(String nickname) {
		try (ObjectOutputStream outputStream = new ObjectOutputStream(
				new FileOutputStream("resources/" + nickname + "/data.txt"))) {
			outputStream.writeObject(users);
		} catch (IOException e) {
			System.err.println("Failed to save user data: " + e.getMessage());
		}
	}*/

	/*public void signup(String nickname, String password, String realName, String surname, int age, String email) {
		if (users.containsKey(nickname)) {
			throw new IllegalArgumentException("Nickname is already taken. Please choose a different nickname.");
		}

		User user = new User(nickname, password, realName, surname, age, email);
		users.put(nickname, user);
		saveUserData(nickname);
	}*/

	/*public User login(String nickname, String password) {
		if (users.containsKey(nickname)) {
			User user = users.get(nickname);
			if (user.getPassword().equals(password)) {
				try {
					FileInputStream fileIn = new FileInputStream(UserAuthentication.USER_DATA_FILE);
					ObjectInputStream os = new ObjectInputStream(fileIn);
					HashMap<String, User> users = (HashMap<String, User>) os.readObject();
					User user=users.get(nickname);
					if(user.getPassword().equals(password)) {
						System.out.println(user);
						currentUser = user;
					}
				} catch (Exception e) {
					System.out.println(e);
				}
				
				return user;
			}
		}
		return null;
	}*/
	
	

	public Map<String, User> getUsers() {
		return users;
	}

	public void setUsers(Map<String, User> users) {
		this.users = users;
	}

	public static User getCurrentUser() {
		return currentUser;
	}

	public static void setCurrentUser(User currentUser) {
		UserAuthentication.currentUser = currentUser;
	}

	public static String getUserDataFile() {
		return USER_DATA_FILE;
	}
	
	
}
