package models;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class DataLayer {
	private static HashMap<String, User> users;
	private static User currentuser;
	private static final Logger logger = Logger.getLogger(DataLayer.class.getName());

	public static void init() {
		try {
			String USER_DATA_FILE = "resources/user_data.txt";

			FileInputStream fileIn = new FileInputStream(USER_DATA_FILE);
			ObjectInputStream os = new ObjectInputStream(fileIn);
			HashMap<String, User> users = (HashMap<String, User>) os.readObject();

			for (Map.Entry<String, User> entry : users.entrySet()) {
			    String key = entry.getKey();
			    User value = entry.getValue();
			    System.out.println("Key: " + key + ", User: " + value);
			}

			os.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static HashMap<String, User>  getUsers() {
		return users;
	}

	public static void setUsers(HashMap<String, User> users) {
		DataLayer.users = users;
	}

	public static User getCurrentuser() {
		return currentuser;
	}

	public static void setCurrentuser(String nickname) {
	    System.out.println("nickname: " + nickname);
		User user = users.get(nickname);
		DataLayer.currentuser = user;
	}

	// SAVE USER PHOTO CLASS WITH IT'S KEY TO IMAGES.TXT
	public static void saveUserPhoto(Photo photo, String key) {
		HashMap<String, Photo> new_photo = new HashMap<String, Photo>();
		new_photo.put(key, photo);
		try (ObjectOutputStream outputStream = new ObjectOutputStream(
				new FileOutputStream("resources/" + currentuser.getNickname()+ "/images.txt"))) {
			outputStream.writeObject(new_photo);
		} catch (IOException e) {
			System.err.println("Failed to save user data: " + e.getMessage());
		}
	}
}
