package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.DataLayer;

public class Main {

	public static void main(String args[]) {
		DataLayer.init();
		//Navigation navigation = new Navigation();
		//navigation.init();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Navigation navigation = new Navigation();
                navigation.navigateToLoginPage();
            }
        });
		
	}

}
