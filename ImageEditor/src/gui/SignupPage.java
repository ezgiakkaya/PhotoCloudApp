package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import models.DataLayer;
import models.FreeTierUser;
import models.HobbyistTierUser;
import models.ProfessionalTierUser;
import models.User;
import gui.Navigation;

public class SignupPage extends JPanel {
	private Navigation navigation;

	public SignupPage(Navigation navigation) {
		this.navigation = navigation;

		setBackground(Color.PINK);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// Create labels and text fields for signup information
		JLabel nicknameLabel = new JLabel("Nickname:");
		nicknameLabel.setForeground(Color.PINK);
		JTextField nicknameTextField = new JTextField(20);

		JLabel passwordLabel = new JLabel("Password:");
		JPasswordField passwordField = new JPasswordField(20);

		JLabel nameLabel = new JLabel("Name:");
		JTextField nameTextField = new JTextField(20);

		JLabel surnameLabel = new JLabel("Surname:");
		JTextField surnameTextField = new JTextField(20);

		JLabel ageLabel = new JLabel("Age:");
		JTextField ageTextField = new JTextField(20);

		JLabel emailLabel = new JLabel("Email:");
		JTextField emailTextField = new JTextField(20);
		
		 JLabel tierLabel = new JLabel("Tier:");
	        String[] tierOptions = { "Hobbyist", "Free", "Professional" };
	        JComboBox<String> tierComboBox = new JComboBox<>(tierOptions);


		// Create a signup button
		JButton signupButton = new JButton("Signup");
		signupButton.setBackground(Color.DARK_GRAY);
		signupButton.setForeground(Color.PINK);

		signupButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nickname = nicknameTextField.getText();
				String password = new String(passwordField.getPassword());
				String name = nameTextField.getText();
				String surname = surnameTextField.getText();
				String ageText = ageTextField.getText();
				String email = emailTextField.getText();
				String tier = (String) tierComboBox.getSelectedItem();

				int age;
				try {
					age = Integer.parseInt(ageTextField.getText());
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(SignupPage.this, "Invalid age input. Please enter a valid number.");
					return;
				}
				
				// tier = (String) tierComboBox.getSelectedItem();

				boolean signupSuccessful = performSignup(nickname, password, name, surname, age, email, tier);

				if (signupSuccessful) {
					// Navigate to the user's profile page or Discover page
					navigation.navigateToLoginPage();
				} else {
					// Display an error message
					JOptionPane.showMessageDialog(SignupPage.this, "Signup failed. Please check your input.");
				}
			}
		});

		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				navigation.navigateToLoginPage();
			}
		});
		// Add components to the panel
		add(nicknameLabel);
		add(nicknameTextField);
		add(passwordLabel);
		add(passwordField);
		add(nameLabel);
		add(nameTextField);
		add(surnameLabel);
		add(surnameTextField);
		add(ageLabel);
		add(ageTextField);
		add(emailLabel);
		add(emailTextField);
		add(signupButton);
		add(loginButton);
		add(tierLabel);
		add(tierComboBox);

	}

	private boolean performSignup(String nickname, String password, String name, String surname, int age,
			String email, String tier) {
		HashMap<String, User> users = DataLayer.getUsers();

		try {
			// Perform signup validation logic
			 if (!users.containsKey(nickname)) {
	                User user = null;
	                if (tier.equals("Hobbyist")) {
	                    user = new HobbyistTierUser(nickname, password, name, surname, age, email);
	                    System.out.println("hobby user is created");
	                  
	                } else if (tier.equals("Free")) {
	                    user = new FreeTierUser(nickname, password, name, surname, age, email);
	                } else if (tier.equals("Professional")) {
	                    user = new ProfessionalTierUser(nickname, password, name, surname, age, email);
	                }
	                DataLayer.addUser(user);
	            }else {
				throw new IllegalArgumentException("User already exists!");
			}
			//DataLayer.setCurrentuser(nickname);
			//navigation.navigateToProfilePage();

			return true;

		} catch (IllegalArgumentException ex) {
			System.err.println("Signup failed: " + ex.getMessage());
			return false; // Signup failed
		}
	}

	/*private void saveUserData(String nickname) {
		HashMap<String, User> users = DataLayer.getUsers();

		try (ObjectOutputStream outputStream = new ObjectOutputStream(
				new FileOutputStream("resources/" + nickname + "/data.txt"))) {
			outputStream.writeObject(users);
		} catch (IOException e) {
			System.err.println("Failed to save user data: " + e.getMessage());
		}
	}*/

	/*public static void main(String[] args) {
		// Create an instance of SignupPage
		SignupPage signupPage = new SignupPage(new Navigation());

		// Create a JFrame to hold the SignupPage panel
		JFrame frame = new JFrame("Signup Page");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(signupPage);
		frame.pack();
		frame.setVisible(true);
		frame.setSize(600, 500);

	}*/
}