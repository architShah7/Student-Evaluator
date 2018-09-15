package shah.archit;

/**
 * 
 * @author Archit Shah
 * @version 1.0
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SelectPanel extends JPanel {

	final int FONT_SIZE = 24;

	JLabel lblCourseInfo;
	JLabel lblFirstName;
	JLabel lblLastName;
	JLabel lblStudentID;
	JLabel lblContentMark;
	JLabel lblDeliveryMark;
	JLabel lblRemainingStudents;

	JButton btnSelect;
	JButton btnRandPicker;
	JButton btnEvaluate;
	JButton btnSaveMarks;
	
	Section section;
	
	Student chosenStudent;
	
	String headerInfo;
	
	int ROWS = 6, COLS = 1;
	
	int studentIndex;
	int contentMark;
	int deliveryMark;
	
	final int MIN_MARK = 0;
	final int MAX_MARK = 100;

	/**
	 * Initialize the panel with labels and buttons.
	 */
	public SelectPanel() {

		setBackground(Color.yellow);
		setLayout(new GridLayout(ROWS, COLS, 10, 10));
		setPreferredSize(new Dimension(500, 275));
		setFont(new Font("Arial", Font.BOLD, FONT_SIZE));

		btnSelect = new JButton("Select Class");
		btnSelect.addActionListener(new SelectListener());

		lblCourseInfo = new JLabel("Course Code: ");
		lblFirstName = new JLabel("First Name: ");
		lblLastName = new JLabel("Last Name: ");
		lblStudentID = new JLabel("Student ID:");
		lblContentMark = new JLabel("Content Mark: ");
		lblDeliveryMark = new JLabel("Delivery Mark: ");

		btnRandPicker = new JButton("Pick Student");
		btnRandPicker.addActionListener(new SelectListener());

		btnEvaluate = new JButton("Evaluate");
		btnEvaluate.addActionListener(new SelectListener());

		btnSaveMarks = new JButton("Save");
		btnSaveMarks.addActionListener(new SelectListener());

		lblRemainingStudents = new JLabel("Students Remaining: ");
		
		add(lblCourseInfo);
		add(Box.createHorizontalGlue());
		add(lblRemainingStudents);
		add(btnSelect);
		add(Box.createVerticalGlue());
		add(Box.createHorizontalGlue());
		add(btnRandPicker);
		add(lblFirstName);
		add(lblLastName);
		add(btnEvaluate);
		add(Box.createVerticalGlue());
		add(lblStudentID);
		add(Box.createHorizontalGlue());
		add(btnEvaluate);
		add(lblContentMark);
		add(lblDeliveryMark);
		add(btnSaveMarks);

	}

	private class SelectListener implements ActionListener {
		
		/**
		 * Execute the function of the button that it is responsible for. 
		 */
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == (btnSelect)) {
				File file = getFile();
				if (file != null) {
					section = new Section();
					loadStudents(file, section);
					section.setCourseCode(file.getName());
					section.setFilePath(file.getAbsolutePath());
					lblCourseInfo.setText("Course Code: " + section.getCourseCode());
					lblRemainingStudents.setText("# of Students Remaining: " + section.getNumStudents());
					System.out.println("Course code: " + section.getFilePath());
					btnSelect.setEnabled(false);
				} else {
					JOptionPane.showMessageDialog(null, "No file was selected. Please Select Again.");
				}

			}

			if (event.getSource() == btnRandPicker) {
				if (section.getNumStudents() > 0) {
					btnEvaluate.setEnabled(true);
					studentIndex = section.chooseStudent();
					chosenStudent = section.getRandomStudent(studentIndex);
					lblFirstName.setText("First Name: " + chosenStudent.getFirstName());
					lblLastName.setText("Last Name: " + chosenStudent.getLastName());
					lblStudentID.setText(String.valueOf("Student ID: " + chosenStudent.getID()));
				}
			}

			if (event.getSource() == btnEvaluate) {
				boolean storeMarks = false;
				while (!storeMarks) {
					contentMark = evaluateContent();
					deliveryMark = evaluateDelivery();
					boolean valid = validateMarks(contentMark, deliveryMark);
					btnEvaluate.setEnabled(false);
					if (valid == true) {
						confirmMarks(contentMark, deliveryMark);
						setMarks(contentMark, deliveryMark);
						updateStudentList();
						updatePanel();
						storeMarks = true;
					}

				}
			}
			
			if (event.getSource() == btnSaveMarks) {
				int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to save the file?");
				if (result == JOptionPane.YES_OPTION) {
					fileExport();
				}

			}
		}
	}

	/**
	 * Import a selected 'csv' file for a section.
	 * @return
	 */
	private File getFile() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV file", "csv");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File inFile = new File(chooser.getSelectedFile().toString());
			return inFile;
		}
		return null;
	}

	/**
	 * Load the list of students from the selected file into a section.
	 * @param sourceFile - Selected file consisting of students' information
	 * @param sect - Section for a course
	 */
	private void loadStudents(File sourceFile, Section sect) {
		Scanner in;
		final int MAX_COMMAS = 5;

		try {
			in = new Scanner(sourceFile);
			String line = "";
			String[] studentInfo;
			headerInfo = in.nextLine();
			while (in.hasNextLine()) {
				line = in.nextLine();
				studentInfo = line.split(",");
				if (studentInfo.length == MAX_COMMAS) {
					sect.addStudent(new Student(studentInfo[0], studentInfo[1], Integer.valueOf(studentInfo[2]),
							Integer.valueOf(studentInfo[3]), Integer.valueOf(studentInfo[4])));
				} else {
					throw new Exception(
							"Invalid file format. \nExpected: Firstname, Lastname, Id, Content mark, Delivery mark");
				}
			}
			in.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	/**
	 * Return a student's mark for the presentation's content.
	 * @return
	 */
	private int evaluateContent() {
		boolean markObtained = false;
		String content = "";
		int contentMark = -1;

		while (!markObtained) {
			try {
				content = JOptionPane.showInputDialog(null, "Content mark (//100): ");
				contentMark = Integer.valueOf(content);
				markObtained = true;

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "No mark was entered, please try again.");
			}
		}
		return contentMark;
	}

	/**
	 * Return a student's mark for the delivery of the presentation.
	 * @return
	 */
	private int evaluateDelivery() {

		boolean markObtained = false;
		String delivery = "";
		int deliveryMark = -1;

		while (!markObtained) {
			try {
				delivery = JOptionPane.showInputDialog(null, "Delivery mark (//100): ");
				deliveryMark = Integer.valueOf(delivery);
				markObtained = true;

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "No mark was entered, please try again.");
			}
		}
		return deliveryMark;
	}
	
	/**
	 * Validate the marks as being anywhere from 0 - 100. 
	 * @param markCont - Mark for content
	 * @param markDel - Mark for delivery
	 * @return
	 */
	private boolean validateMarks(int markCont, int markDel) {
		boolean valid = false;
		if ((markCont >= MIN_MARK && markCont <= MAX_MARK) && (markDel >= MIN_MARK && markDel <= MAX_MARK)) {
			valid = true;
		} else {
			JOptionPane.showMessageDialog(null, "Please enter a valid mark.");
		}
		return valid;
	}

	/**
	 * Prompt the user to get a confirmation to save the entered marks. 
	 * @param markCont - Mark for content
	 * @param markDel - Mark for delivery
	 */
	private void confirmMarks(int markCont, int markDel) {
		int choice = JOptionPane.showConfirmDialog(null,
				"Confirm marks?    Content : " + markCont + "  Delivery: " + markDel);
		
		if (choice == JOptionPane.YES_OPTION) {
			lblContentMark.setText("Content: " + markCont);
			lblDeliveryMark.setText("Delivery: " + markDel);
		}
	}

	/**
	 * Assign the entered marks to a student being evaluated.
	 * @param markCont - Mark for content
	 * @param markDel - Mark for delivery
	 */
	private void setMarks(int markCont, int markDel) {
		section.getStudent(studentIndex).setContentMark(markCont);
		section.getStudent(studentIndex).setDeliveryMark(markDel);
	}

	/**
	 * Update the list of students who have presented and are left to present.
	 */
	private void updateStudentList() {
		section.addAssessed(section.getStudent(studentIndex));
		section.removeStudent(studentIndex);
	}

	/**
	 * Update the panel after each student presents.
	 */
	private void updatePanel() {
		if ((section.getNumStudents() == 0)) {
			lblRemainingStudents.setText("Everyone has presented! ");
			lblFirstName.setText("First Name: ");
			lblLastName.setText("Last Name: ");
			lblStudentID.setText("Student ID: ");
			lblContentMark.setText("Content: ");
			lblDeliveryMark.setText("Delivery: ");
			btnEvaluate.setEnabled(false);
			btnRandPicker.setEnabled(false);
		}else{
			lblFirstName.setText("First Name: ");
			lblLastName.setText("Last Name: ");
			lblStudentID.setText("Student ID: ");
			lblContentMark.setText("Content: ");
			lblDeliveryMark.setText("Delivery: ");
			lblRemainingStudents.setText("# of Students Remaining: "
					+ section.getNumStudents());
		}
	}

	/**
	 * Export the file with marks for students who have presented and/or no marks for those who haven't.
	 */
	private void fileExport() {
		try {
			File fileOut = new File(section.getFilePath() + "-FINAL.csv");
			FileWriter writeData = new FileWriter(fileOut);
			BufferedWriter writeFile = new BufferedWriter(writeData);

			writeFile.write(headerInfo);
			writeFile.newLine();

			for (int i = 0; i < section.getNumAssessed(); i++) {
				writeFile.write(section.getAccessedStudent(i).toString());
				writeFile.newLine();
			}

			for (int i = 0; i < section.getNumStudents(); i++) {
				writeFile.write(section.getStudent(i).toString());
				writeFile.newLine();
			}

			writeFile.close();

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "An error has occurred. Please try again!");
		}

	}

}
