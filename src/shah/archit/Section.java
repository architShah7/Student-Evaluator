package shah.archit;

/**
 * 
 * @author Archit Shah
 * @version 1.0
 */
import java.util.ArrayList;

public class Section {

	private String courseCode, filePath;
	
	private ArrayList<Student> studentList = new ArrayList<>();
	private ArrayList<Student> assessedStudents = new ArrayList<>();
	
	/**
	 * Initialize a section using the course code and location of the file.
	 */
	public Section() {
		this.courseCode = "";
		this.filePath = "";
	}
	/**
	 * Assign a course code using the file's name.
	 * @param fileName - Name of the file
	 */
	public void setCourseCode(String fileName) {
		courseCode = fileName.substring(0,fileName.indexOf("."));
	}
	/**
	 * Assign a file path 
	 * @param filePath - Series of directories from root directory to the file.
	 */
	public void setFilePath(String filePath) {
		filePath = filePath.substring(0,filePath.indexOf("."));
	}
	/**
	 * Return the file path of the selected file.
	 * @return
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * Return the course code.
	 * @return
	 */
	public String getCourseCode() {
		return courseCode;
	}
	/**
	 * Return a student that is left to present at a given index.
	 * @param index - Reference for a particular student
	 * @return
	 */
	public Student getStudent(int index){
		return studentList.get(index);
	}
	
	/**
	 * Return a student that has already presented at a given index.
	 * @param index - Reference for a particular student
	 * @return
	 */
	public Student getAccessedStudent(int index){
		return assessedStudents.get(index);
	}
	
	/**
	 * Add a student to the list of students that are yet to present in the class. 
	 * @param student - Student's profile with name, student id, and marks
	 */
	public void addStudent(Student student) {
		studentList.add(student);
	}
	/**
	 * Remove a student that has presented from the list of students that are yet to present.
	 * @param index - Reference for a particular student
	 */
	public void removeStudent(int index) {
		studentList.remove(index);
	}
	/**
	 * Add a student that has presented to the list of students that have presented.
	 * @param student - Student's profile with name, student id, and marks
	 */
	public void addAssessed(Student student) {
		assessedStudents.add(student);
	}
	/**
	 * Return the number of students that are yet to present.
	 * @return
	 */
	public int getNumStudents() {
		return studentList.size();
		 
	}
	/**
	 * Return the number of students that have presented.
	 * @return
	 */
	public int getNumAssessed() {
		return assessedStudents.size(); 
	}
	
	/**
	 * Generate a randomly chosen index. 
	 * @return
	 */
	public int chooseStudent(){
		int index = (int) (Math.random() * getNumStudents());
		return index;
	}
	
	/**
	 * Return a student a randomly chosen index.
	 * @param index - Reference for a particular student
	 * @return
	 */
	public Student getRandomStudent(int index) {
		Student chosenStudent = studentList.get(index);
		return chosenStudent;

	}
	
}
