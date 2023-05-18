package gui;

import javax.swing.*;
import models.Photo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import models.DataLayer;
import models.User;

public class ProfilePage extends JPanel {
	private String userNickname; // User's nickname

	public ProfilePage(String nickname) {
		DataLayer.setCurrentuser("ilgim");
		User user = DataLayer.getCurrentuser();
		String userNickname = user.getNickname();
		getUploadedPhotos(userNickname);
		this.userNickname = userNickname;

		// Create a panel to display the user's information and posts
		JPanel panel = new JPanel(new BorderLayout());
		add(panel);

		// Create a label to display the user's information
		JLabel infoLabel = new JLabel("User Information:");
		panel.add(infoLabel, BorderLayout.NORTH);

		// Create a text area to display the user's information
		JTextArea infoTextArea = new JTextArea(getUserInfo(userNickname));
		infoTextArea.setEditable(false);
		JScrollPane infoScrollPane = new JScrollPane(infoTextArea);
		panel.add(infoScrollPane, BorderLayout.CENTER);

		// Create a button to modify the user's information
		JButton modifyButton = new JButton("Modify Information");
		modifyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Logic to modify the user's information
				// Implement this according to your specific requirements
				// You can open a dialog or new page to allow the user to modify their
				// information
			}
		});
		panel.add(modifyButton, BorderLayout.SOUTH);
		setVisible(true);

	}

	private List<Photo> getUploadedPhotos(String userNickname) {
		List<Photo> uploadedPhotos = new ArrayList<>();
		String filePath = "resources/" + userNickname + "/images.txt";
		// File folder = new File(filePath);
		// File[] files = folder.listFiles();
		try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
			uploadedPhotos = (List<Photo>) inputStream.readObject();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return uploadedPhotos;
	}

	private void showUploadedPhotos(String userNickname, JPanel panel) {
		List<Photo> uploadedPhotos = getUploadedPhotos(userNickname);
		GridLayout layout = new GridLayout(0, 3);
		layout.setHgap(10);
		layout.setVgap(10);

		panel.setLayout(layout);

		for (Photo photo : uploadedPhotos) {
			//BufferedImage image = ImageIO.read(new File("resources/" + userNickname + "/images/" + photo.getName() + photo.getExtension()));
			ImageIcon icon = new ImageIcon("resources/" + userNickname + "/images/" + photo.getName() );
			JLabel photoLabel = new JLabel(icon);
			panel.add(photoLabel);
		}
		panel.revalidate();
		panel.repaint();

	}

	private String getUserInfo(String userNickname) {
		// Logic to retrieve and format the user's information
		// Replace this with actual data retrieval and formatting based on the provided
		// userNickname
		return "User Information:\n" + "Nickname: " + userNickname + "\n" + "Name: John\n" + "Surname: Doe\n"
				+ "Age: 30\n" + "Email: john.doe@example.com";
	}

//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(() -> new ProfilePage("JohnDoe")); // Example usage with a user nickname
//	}
}