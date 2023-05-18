package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import models.Photo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import models.DataLayer;
import models.User;
import java.util.HashMap;
/*
 * showupluoadedphotos guncellenecek
 * ilk girişte fotomuz olmucagı için profilpagede altta discover,upload photo diye iki buton olmasını planladım?
 * upload ettikten sonra bence photogrid oluşturulsun bilemedim bu  nokatyı suan
 * signupta addprofilephoto da olsun filechooserla ekleriz? emin değlim
 * profil fotosu da borderlayout.north filan deriz yukarda durur
 * 
 */

public class ProfilePage extends JPanel {
	
	private User user = DataLayer.getCurrentuser();
	private String userNickname = user.getNickname(); // User's nickname

	public ProfilePage(String userNickname) {
		DataLayer.setCurrentuser(userNickname);
		//User user = DataLayer.getCurrentuser();
		//String userNickname = user.getNickname();*/
		//getUploadedPhotos(userNickname);//if there is any??
		this.userNickname = userNickname;

		// Create a panel to display the user's information and posts
		JPanel panel = new JPanel(new BorderLayout());
		add(panel);

		// Create a label to display the user's information
		JLabel infoLabel = new JLabel("User Information:");
		panel.add(infoLabel, BorderLayout.NORTH);

		// Create a text area to display the user's information
		JTextArea infoTextArea = new JTextArea(getUserInfo(user));
		infoTextArea.setEditable(false);
		infoTextArea.setBackground(Color.PINK); // Set the background color to pink
	    infoTextArea.setPreferredSize(new Dimension(600, 600)); // S
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

		JPanel photoPanel = new JPanel();
		showUploadedPhotos(userNickname, photoPanel);
		panel.add(photoPanel, BorderLayout.SOUTH);

		setVisible(true);

	}

	/*private List<Photo> getUploadedPhotos(String userNickname) {
		List<Photo> uploadedPhotos = new ArrayList<>();
		String filePath = "resources/images_data.txt";

		try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
			HashMap<String, List<Photo>> photos = (HashMap<String, List<Photo>>) inputStream.readObject();

			if (photos != null) {
				uploadedPhotos = photos.get(userNickname);
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return uploadedPhotos;
	}*/
	
	
	  private List<Photo> getUploadedPhotos(String userNickname) {
    List<Photo> uploadedPhotos = new ArrayList<>();
    HashMap<String, List<Photo>> photos = DataLayer.getPhotos();

    if (photos != null) {
        uploadedPhotos = photos.getOrDefault(userNickname, new ArrayList<>());
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
			try {
				String imagePath = "resources/" + userNickname + "/images/" + photo.getName();
				File imageFile = new File(imagePath);
				ImageIcon icon = new ImageIcon(ImageIO.read(imageFile));

				JLabel photoLabel = new JLabel(icon);
				panel.add(photoLabel);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		panel.revalidate();
		panel.repaint();
	}

	private String getUserInfo(User user) {
		// Logic to retrieve and format the user's information
		// Replace this with actual data retrieval and formatting based on the provided
		// userNickname
		return "User Information:\n" + "Nickname: " + userNickname + "\n" + "Name:"+ user.getRealName() +"\n"+ "Surname: " + user.getSurname()+
				"\n"+ "Age: "+ user.getAge() + "\n"+ "Email: "+user.getEmail();
	}

//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(() -> new ProfilePage("JohnDoe")); // Example usage with a user nickname
//	}
}
