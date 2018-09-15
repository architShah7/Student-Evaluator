package shah.archit;

/**
 * 
 * @author Archit Shah
 * @version 1.0
 */
public class Student {

	private String firstName, lastName;
	private int contentMark, deliveryMark, studentId;

	/**
	 * Initialize a student profile with first name, last name, student Id and
	 * marks.
	 * @param firstName - Student's first name
	 * @param lastName - Student's last name
	 * @param studentId - Student number
	 * @param contentMark - Mark for content
	 * @param deliveryMark - Mark for delivery of the content
	 */
	public Student(String firstName, String lastName, int studentId, int contentMark, int deliveryMark) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentId = studentId;
		this.contentMark = contentMark;
		this.deliveryMark = deliveryMark;
	}

	/**
	 * Assign a student's first name.
	 * @param firstName - Student's first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Assign a student's last name.
	 * @param firstName - Student's first name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Assign a specified student ID.
	 * @param studentId - Student number
	 */
	public void setID(int studentId) {
		this.studentId = studentId;
	}

	/**
	 * Assign a content mark.
	 * @param contentMark - Mark for content
	 */
	public void setContentMark(int contentMark) {
		this.contentMark = contentMark;
	}

	/**
	 * Assign a delivery mark for the presentation.
	 * @param deliveryMark - Mark for delivery
	 */
	public void setDeliveryMark(int deliveryMark) {
		this.deliveryMark = deliveryMark;
	}

	/**
	 * Return a student's first name.
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Return a student's last name. 
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Return a student's student number. 
	 * @return
	 */
	public int getID() {
		return studentId;
	}

	/**
	 * Return a student's content mark.
	 * @return
	 */
	public int getContentMark() {
		return contentMark;
	}

	/**
	 * Return a student's delivery mark. 
	 * @return
	 */
	public int getDeliveryMark() {
		return deliveryMark;
	}

	/**
	 * Return a student's profile with first name, last name, student id,
	 * content and delivery mark.
	 */
	public java.lang.String toString() {
		return firstName + "," + lastName + "," + String.valueOf(studentId) + "," + String.valueOf(contentMark) + ","
				+ String.valueOf(deliveryMark);
	}

}
