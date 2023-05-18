package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.DataLayer;

public class Main {

	public static void Main(String args[]) {
		DataLayer.init();
		Navigation navigation = new Navigation();
	}

}
