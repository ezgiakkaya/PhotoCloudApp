package gui;

import javax.swing.BoxLayout;
import models.Photo;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import models.DataLayer;
import models.User;
import models.UserAuthentication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileChooser extends JFrame {

	private static final long serialVersionUID = 1L;

	public FileChooser() {

		User currentUser = DataLayer.getCurrentuser();

		setTitle("Chooser");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		add(panel);

		JLabel chooserLabel = new JLabel("Choose a photo:");
		JTextField chooserTextField = new JTextField(20);

		panel.add(chooserLabel);
		panel.add(chooserTextField);

		JButton chooseButton = new JButton("Choose");
		chooseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int result = fileChooser.showOpenDialog(FileChooser.this);
				if (result == JFileChooser.APPROVE_OPTION) {
					String selectedFilePath = fileChooser.getSelectedFile().getPath();
					// String fullFileName = fileChooser.getSelectedFile().getName();
					// String extension = fullFileName.substring(fullFileName.indexOf("."),
					// fullFileName.length());
					chooserTextField.setText(selectedFilePath);

					try {
						// !	LOCALDEN FOTOYU KAYDETME .PNG 
						Path sourcePath = Paths.get(selectedFilePath);
						Path targetPath = Paths.get("resources/" + currentUser.getNickname() + "/images");
						Files.createDirectories(targetPath.getParent());
						Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
						// Path newSourcePath = Paths.get(getParent().toString(), fullFileName);

						// Path newTargetPath = Paths.get("resources/" + currentUser.getNickname() +
						// "/images/" + Photo.generateName(extension));
						// Files.move(newSourcePath, newTargetPath,
						// StandardCopyOption.REPLACE_EXISTING);
						JOptionPane.showMessageDialog(FileChooser.this, "File copied successfully.");
						Photo photo = new Photo(currentUser);
						DataLayer.addPhoto(photo);
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(FileChooser.this, "Failed to copy file: " + ex.getMessage());
					}
				}
			}
		});
		panel.add(chooseButton);

		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		// User currentUser = new User("JohnDoe");
		// SwingUtilities.invokeLater(() -> new FileChooser(currentUser));
	}
}
