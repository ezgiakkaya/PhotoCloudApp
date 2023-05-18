package models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class DataLayer {
	private static HashMap<String, User> users;
	private static User currentuser;
	private static final Logger logger = Logger.getLogger(DataLayer.class.getName());
	private static HashMap<String, List<Photo>> photos;
	private static String USER_DATA_FILE = "resources/user_data.txt";
	private static String imagesFilePath="resources/images_data.txt";

	public static void init() {
		loadUsersData();
		loadImagesData();
		
	}

	private static void loadUsersData() {
		try {
			

			FileInputStream fileIn = new FileInputStream(USER_DATA_FILE);
			ObjectInputStream os = new ObjectInputStream(fileIn);
			users = (HashMap<String, User>) os.readObject();

			if (users == null) {
				users = new HashMap<>();
			}

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

	private static void loadImagesData() {
		try {
			FileInputStream fis=new FileInputStream(imagesFilePath);
			ObjectInputStream ois=new ObjectInputStream(fis);
			photos=(HashMap<String,List<Photo>>) ois.readObject();
			if(photos==null) {
				photos=new HashMap<>();
			}
			ois.close();
			fis.close();
		}catch(Exception e){
			
		}

	}

	public static void addUser(User user) {
		try (ObjectOutputStream outputStream = new ObjectOutputStream(
				new FileOutputStream("resources/user_data.txt"))) {
			users.put(user.getNickname(), user);
			outputStream.writeObject(users);
		} catch (IOException e) {
			System.err.println("Failed to save user data: " + e.getMessage());
			users.remove(user.getNickname());
		}
	}

	public static HashMap<String, User> getUsers() {
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

	public static void saveUserData(String nickname) {
		HashMap<String, User> users = DataLayer.getUsers();
		try (ObjectOutputStream outputStream = new ObjectOutputStream(
				new FileOutputStream("resources/user_data.txt"))) {
			outputStream.writeObject(users);
		} catch (IOException e) {
			System.err.println("Failed to save user data: " + e.getMessage());
		}
	}
	
	public static void addPhoto(Photo photo) {
		try {
			FileOutputStream fos=new FileOutputStream(imagesFilePath);
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			String nickname = photo.getOwner().getNickname();
			List<Photo> photoList=photos.get(nickname);
			photoList.add(photo);
			oos.writeObject(photos);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
/*
	// SAVE USER PHOTO CLASS WITH IT'S KEY TO IMAGES.TXT
	public static void saveUserPhoto(Photo photo, String key) {
		HashMap<String, Photo> new_photo = new HashMap<String, Photo>();
		new_photo.put(key, photo);

		try (ObjectOutputStream outputStream = new ObjectOutputStream(
				new FileOutputStream("resources/images_data.txt"))) {
			outputStream.writeObject(new_photo);
		} catch (IOException e) {
			System.err.println("Failed to save user data: " + e.getMessage());
		}
	}
	*/

}
