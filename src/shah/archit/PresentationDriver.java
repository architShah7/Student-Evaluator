package shah.archit;

import javax.swing.*;

/**
 * PresentationDriver is an interactive program that allows a teacher to import
 * a list of students, evaluate their presentation for content and delivery, and
 * export a updated file.
 * 
 * @author A.Shah
 * @version 1.0
 */

public class PresentationDriver {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Student Picker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new SelectPanel());
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
	}

}