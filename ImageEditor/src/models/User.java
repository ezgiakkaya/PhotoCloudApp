package models;

import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nickname;
	private String password;
	private String realName;
	private String surname;
	private int age;
	private String email;
	private Image profilePhoto;

	public User(String nickname, String password, String realName, String surname, int age, String email) {
		try {
			this.nickname = nickname;
			this.password = password;
			this.realName = realName;
			this.surname = surname;
			this.age = age;
			this.email = email;
			this.profilePhoto = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createFolder() {
		String folderPath = "resources/" + nickname;
		File folder = new File(folderPath);
		if (!folder.exists()) {
			if (folder.mkdirs()) {
				System.out.println("Images folder created for user: " + nickname);
			} else {
				System.err.println("Failed to create images folder for user: " + nickname);
			}
		}
	}

	// othermethods

	public boolean authenticate(String nickname, String password) {
		return this.nickname.equals(nickname) && this.password.equals(password);
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Image getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(Image profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	@Override
	public String toString() {
		return "User [nickname=" + nickname + ", password=" + password + ", realName=" + realName + ", surname="
				+ surname + ", age=" + age + ", email=" + email + ", profilePhoto=" + profilePhoto + "]";
	}

}
