package gui;

import javax.imageio.ImageIO;
import javax.swing.*;

import filters.BlurFilter;
import filters.BrightnessFilter;
import filters.EdgeDetectionFilter;
import filters.Filter;
import filters.GrayscaleFilter;
import filters.SharpenFilter;
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

import gui.FileChooser;
import utils.ImageMatrix;
import java.util.Map;

import java.util.HashMap;
import java.util.logging.Logger;

/*
 * showupluoadedphotos guncellenecek
 * ilk girişte fotomuz olmucagı için profilpagede altta discover,upload photo diye iki buton olmasını planladım?
 * upload ettikten sonra bence photogrid oluşturulsun bilemedim bu  nokatyı suan
 * signupta addprofilephoto da olsun filechooserla ekleriz? emin değlim
 * profil fotosu da borderlayout.north filan deriz yukarda durur
 * 
 */

/*
 * photolar showuploaedphoto metoduyla once gride gönderilsin
 * sonra gride basıldıgında her fotonun  buyuk hali çıksın
 * çıkan panelde edit ve remove olsun
 * 
 */

public class ProfilePage extends JPanel {
	private static final Logger logger = Logger.getLogger(FileChooser.class.getName());

	private User user = DataLayer.getCurrentuser();
	private String userNickname = user.getNickname(); // User's nickname

	public ProfilePage(String userNickname) {
		DataLayer.setCurrentuser(userNickname);
		// User user = DataLayer.getCurrentuser();
		// String userNickname = user.getNickname();*/
		// getUploadedPhotos(userNickname);//if there is any??
		this.userNickname = userNickname;

		// Create a panel to display the user's information and posts

		// Create a label to display the user's information
		JLabel infoLabel = new JLabel("Profile:");
		add(infoLabel, BorderLayout.NORTH);

		// Create a text area to display the user's information
		JTextArea infoTextArea = new JTextArea(getUserInfo(user));
		infoTextArea.setEditable(false);
		infoTextArea.setBackground(Color.PINK); // Set the background color to pink
		infoTextArea.setPreferredSize(new Dimension(600, 200)); // S
		JScrollPane infoScrollPane = new JScrollPane(infoTextArea);
		add(infoScrollPane, BorderLayout.CENTER);

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
		add(modifyButton, BorderLayout.SOUTH);

		// ADD PHOTO BUTTON
		JButton addPhotoButton = new JButton("Add Photo");
		addPhotoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FileChooser filechooser = new FileChooser();
			}
		});
		add(addPhotoButton, BorderLayout.SOUTH);

		JPanel photoPanel = new JPanel();
		showUploadedPhotos(userNickname, photoPanel);
		add(photoPanel, BorderLayout.SOUTH);

		setVisible(true);

	}

	/*
	 * private List<Photo> getUploadedPhotos(String userNickname) { List<Photo>
	 * uploadedPhotos = new ArrayList<>(); String filePath =
	 * "resources/images_data.txt";
	 * 
	 * try (ObjectInputStream inputStream = new ObjectInputStream(new
	 * FileInputStream(filePath))) { HashMap<String, List<Photo>> photos =
	 * (HashMap<String, List<Photo>>) inputStream.readObject();
	 * 
	 * if (photos != null) { uploadedPhotos = photos.get(userNickname); } } catch
	 * (IOException | ClassNotFoundException e) { e.printStackTrace(); }
	 * 
	 * return uploadedPhotos; }
	 */

	private List<Photo> getUploadedPhotos(String userNickname) {
		List<Photo> uploadedPhotos = new ArrayList<>();
		HashMap<String, List<Photo>> photos = DataLayer.getPhotos();

		if (photos != null) {
			logger.info("Number of users with uploaded photos: " + photos.size());

			for (Map.Entry<String, List<Photo>> entry : photos.entrySet()) {
				String userNickname1 = entry.getKey();
				List<Photo> userPhotos = entry.getValue();

				logger.info("User: " + userNickname1 + ", Number of photos: " + userPhotos.size());

				for (Photo photo : userPhotos) {
					logger.info("Photo: " + photo);
				}
			}

			uploadedPhotos = photos.getOrDefault(userNickname, new ArrayList<>());
		}

		return uploadedPhotos;
	}

	private void showUploadedPhotos(String userNickname, JPanel panel) {
		System.out.println("showUploadedPhotos : " + userNickname);
		List<Photo> uploadedPhotos = getUploadedPhotos(userNickname);

		GridLayout layout = new GridLayout(1,3, 10, 10);
		panel.setLayout(layout);

		for (Photo photo : uploadedPhotos) {
			System.out.println("photo log");

			String imagePath = "resources/" + userNickname + "/" + photo.getName() + ".png";
			// For each image path, create a label with the image and add it to the panel

			ImageIcon imageIcon = new ImageIcon(imagePath);
			JButton imageLabel = new JButton(imageIcon);
			imageLabel.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					createPhotoPanel(photo, panel);
					
				}
			});
			imageLabel.setPreferredSize(new Dimension(250, 100));
			panel.add(imageLabel);
			logger.info(userNickname + " " + photo.getName());

		}

		// createPhotoPanel(photo, panel);
	}

	private void createPhotoPanel(Photo photo, JPanel panel) {
		try {
			String imagePath = "resources/" + userNickname + "/images/" + photo.getName() +".png";
			ImageIcon icon = new ImageIcon(imagePath);

			JLabel photoLabel = new JLabel(icon);
			panel.add(photoLabel);

			JButton editButton = new JButton("Edit");
			editButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// Logic to handle the "Edit" button click
					// Show a selection dialog for choosing a filter
					String[] filterOptions = { "Grayscale", "Sepia", "Blur" }; // Add more filter options as needed

					String selectedFilter = (String) JOptionPane.showInputDialog(null, "Select a filter:", "Edit Photo",
							JOptionPane.PLAIN_MESSAGE, null, filterOptions, filterOptions[0]);

					if (selectedFilter != null) {
						// Show a slider to control the level of the filter
						JSlider levelSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
						levelSlider.setMajorTickSpacing(10);
						levelSlider.setMinorTickSpacing(1);
						levelSlider.setPaintTicks(true);
						levelSlider.setPaintLabels(true);

						JPanel controlPanel = new JPanel();
						controlPanel.add(levelSlider);

						JOptionPane.showMessageDialog(null, controlPanel, "Adjust Filter Level",
								JOptionPane.PLAIN_MESSAGE);

						int filterLevel = levelSlider.getValue();
						// Apply the selected filter with the chosen level
						Filter filter = createFilter(selectedFilter, filterLevel);
						ImageMatrix filteredImage = filter.apply(photo.getImage());

						// Update the photo with the filtered image
						photo.setImage(filteredImage);
						DataLayer.addPhoto(photo);
						// Save the modified photo or update it in the data layer

						// Refresh the photo grid to reflect the changes
						panel.removeAll();
						showUploadedPhotos(userNickname, panel);
						panel.revalidate();
						panel.repaint();
					}
				}
			});
			panel.add(editButton);
			JButton removeButton = new JButton("Remove");
			removeButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// Logic to handle the "Remove" button click
					int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this photo?",
							"Confirm Removal", JOptionPane.YES_NO_OPTION);

					if (confirm == JOptionPane.YES_OPTION) {
						removePhoto(panel, photoLabel, editButton, removeButton);

					}
				}
			});
			panel.add(removeButton);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void removePhoto(JPanel panel, JLabel photoLabel, JButton editButton, JButton removeButton) {
		panel.remove(photoLabel);
		panel.remove(editButton);
		panel.remove(removeButton);

		panel.revalidate();
		panel.repaint();
	}

	public Filter createFilter(String filterName, int filterLevel) {
		switch (filterName) {
		case "Grayscale":
			double grayscaleModifier = filterLevel / 100.0;
			return new GrayscaleFilter(grayscaleModifier);
		/*
		 * case "Blur": int blurRadius = filterLevel; return new BlurFilter(blurRadius);
		 * case "Brightness": double brightnessFactor = filterLevel / 100.0; return new
		 * BrightnessFilter(brightnessFactor); case "Contrast": double contrastFactor =
		 * filterLevel / 100.0; return new ContrastFilter(contrastFactor); case
		 * "EdgeDetection": return new EdgeDetectionFilter();
		 * 
		 * case "Sharpen": double sharpenFactor = filterLevel / 100.0; return new
		 * SharpenFilter(sharpenFactor);
		 */
		default:
			throw new IllegalArgumentException("Invalid filter name: " + filterName);
		}
	}

	private String getUserInfo(User user) {
		// Logic to retrieve and format the user's information
		// Replace this with actual data retrieval and formatting based on the provided
		// userNickname
		return "User Information:\n" + "Nickname: " + userNickname + "\n" + "Name:" + user.getRealName() + "\n"
				+ "Surname: " + user.getSurname() + "\n" + "Age: " + user.getAge() + "\n" + "Email: " + user.getEmail();
	}

//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(() -> new ProfilePage("JohnDoe")); // Example usage with a user nickname
//	}
}
