package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.SignupPage;
import models.DataLayer;
import models.User;
import gui.ProfilePage;
import gui.LoginPage;


public class Navigation  extends JFrame {
	private User currentUser= DataLayer.getCurrentuser();
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public Navigation() {
    		
        JFrame frame = new JFrame("Navigation Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        // Create instances of each screen
        SignupPage signupPage = new SignupPage(this);
        LoginPage loginPage = new LoginPage(this);

        JPanel loginPanel = new JPanel();
        loginPanel.add(loginPage);

        cardPanel.add(signupPage, "signup");
        cardPanel.add(loginPanel, "login");

        // Show the signupPage panel by default
        cardLayout.show(cardPanel, "signup");

        //ProfilePage profilePage = new ProfilePage(currentUser.getNickname());
        //cardPanel.add(profilePage, "profile");
        cardLayout.show(cardPanel, "profile");
        
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(cardPanel, BorderLayout.CENTER);
        frame.setSize(600, 980);
        frame.setVisible(true);
       
    }
    
    public void init() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Navigation navigation = new Navigation();
                navigation.navigateToLoginPage();
            }
        });
    }

    public void navigateToLoginPage() {
        cardLayout.show(cardPanel, "login");
    }
    
    public void navigateToProfilePage() {
        ProfilePage profilePage = new ProfilePage(DataLayer.getCurrentuser().getNickname());
        cardPanel.add(profilePage, "profile");
        cardLayout.show(cardPanel, "profile");
    }

    public void navigateToSignupPage() {
        cardLayout.show(cardPanel, "signup");
    }

    
    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Navigation navigation = new Navigation();
                navigation.navigateToLoginPage();
            }
        });
    }*/
}