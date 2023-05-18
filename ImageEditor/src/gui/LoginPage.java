package gui;

import javax.swing.*;
import models.UserAuthentication;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;
import java.awt.*;

import models.DataLayer;
import models.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import gui.Navigation;

public class LoginPage extends JPanel {
    private Navigation navigation;

	public LoginPage(Navigation navigation) {
        this.navigation = navigation;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// Create labels and text fields for nickname and password
		JLabel nicknameLabel = new JLabel("Nickname:");
		JTextField nicknameTextField = new JTextField(20);

		JLabel passwordLabel = new JLabel("Password:");
		JPasswordField passwordField = new JPasswordField(20);

		add(nicknameLabel);
		add(nicknameTextField);
		add(passwordLabel);
		add(passwordField);

		// Create a login button
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nickname = nicknameTextField.getText();
				String password = new String(passwordField.getPassword());

				// Perform login authentication
				boolean loginSuccessful = performLogin(nickname, password);

				if (loginSuccessful) {
					// Navigate to the user's profile page or Discover page
					//navigateToProfilePage(nickname);
					DataLayer.setCurrentuser(nickname);
					navigation.navigateToProfilePage();
					
				} else {
					// Display an error message
					JOptionPane.showMessageDialog(LoginPage.this, "Invalid nickname or password");
				}
			}
		});

		add(loginButton);

		// Create a signup link
		JLabel signupLabel = new JLabel("Don't have an account? ");
		JLabel signupLink = new JLabel("<html><u>Signup</u></html>");
		signupLink.setForeground(Color.BLUE);
		signupLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		signupLink.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
                navigation.navigateToSignupPage();

			}
		});

		JPanel signupPanel = new JPanel();
		signupPanel.add(signupLabel);
		signupPanel.add(signupLink);
		add(signupPanel);
	}

	private boolean performLogin(String nickname, String password) {
		// Perform authentication logic, try catch, get user from nickname,
		// Replace this with your actual authentication implementation
		// Return true if the login is successful, false otherwise
		
		HashMap<String, User> users = DataLayer.getUsers();
		
		if (!users.containsKey(nickname)) {
			//invalid exception throwla
			return false;
		}

		try {
			User user = users.get(nickname);
			if (user.getPassword().equals(password)) {
				System.out.println(user+"logged in");
				//when a succesful login happens, currentuser is set to logged user
				DataLayer.setCurrentuser(nickname);
				
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return false;
	}

	private void navigateToProfilePage(String nickname) {
		// read the user_data.txt file,

		// Logic to navigate to the user's profile page
		// Replace this with your actual navigation implementation
		System.out.println("Navigating to profile page: " + nickname);
	}

	private void navigateToSignupPage() {
		// Logic to navigate to the signup page
		// Replace this with your actual navigation implementation
		System.out.println("Navigating to signup page");
	}
}