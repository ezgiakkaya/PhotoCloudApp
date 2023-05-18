package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DiscoverPage extends JFrame {

    private List<PhotoThumbnail> photoThumbnails;

    public DiscoverPage() {
        setTitle("Discover Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Create a grid layout panel to display the photo thumbnails
        JPanel gridPanel = new JPanel(new GridLayout(4, 4, 10, 10));
        add(gridPanel, BorderLayout.CENTER);

        // Create sample photo thumbnails (replace with actual data)
        photoThumbnails = createSamplePhotoThumbnails();

        // Add the photo thumbnails to the grid layout panel
        for (PhotoThumbnail thumbnail : photoThumbnails) {
            gridPanel.add(thumbnail);
        }

        setVisible(true);
    }

    private List<PhotoThumbnail> createSamplePhotoThumbnails() {
        List<PhotoThumbnail> thumbnails = new ArrayList<>();

        // Create sample photo thumbnails (replace with actual data)
        for (int i = 1; i <= 9; i++) {
            PhotoThumbnail thumbnail = new PhotoThumbnail("User " + i, "Thumbnail " + i);
            thumbnail.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Open larger view of the photo when thumbnail is clicked
                    openPhotoView(thumbnail.getUserName(), thumbnail.getPhotoName());
                }
            });
            thumbnails.add(thumbnail);
        }

        return thumbnails;
    }

    private void openPhotoView(String userName, String photoName) {
        // Logic to open larger view of the photo
        // You can display the full-sized photo, user's profile photo, nickname, and description in a modal or separate page
        // Implement this according to your specific requirements
    }

    // Custom JButton subclass representing a photo thumbnail
    private class PhotoThumbnail extends JButton {
        private String userName;
        private String photoName;

        public PhotoThumbnail(String userName, String photoName) {
            this.userName = userName;
            this.photoName = photoName;
            setPreferredSize(new Dimension(100, 100));
            setText("<html>" + userName + "<br>" + photoName + "</html>");
        }

        public String getUserName() {
            return userName;
        }

        public String getPhotoName() {
            return photoName;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DiscoverPage());
    }
}
