package swing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Reservation class to hold the queue of appointments made
 * 
 * @author poorva
 *
 */
public class Reservation {
	Queue<Student> appointments = new LinkedList<>();

	/**
	 * obtains the with student data
	 * 
	 * @return ArrayList<Student> returns an array of Student object
	 */
	public ArrayList<Student> getStudentData() {
		ArrayList<Integer> timeList = new ArrayList<>();
		timeList.add(300000);
		timeList.add(660000);
		timeList.add(0);
		Student s1 = new Student("s1@buffalo.edu", "What to do in question 1?",
				System.currentTimeMillis() - timeList.get(getRandomNumber(0, 2)));
		Student s2 = new Student("s2@buffalo.edu", "No Sample Question",
				System.currentTimeMillis() - timeList.get(getRandomNumber(0, 2)));
		Student s3 = new Student("s3@buffalo", "Didn't understand the distributed concept!",
				System.currentTimeMillis() - timeList.get(getRandomNumber(0, 2)));
		Student s4 = new Student("s4@buffalo.edu", "No Sample Question",
				System.currentTimeMillis() - timeList.get(getRandomNumber(0, 2)));

		ArrayList<Student> studentList = new ArrayList<>();
		studentList.add(s1);
		studentList.add(s2);
		studentList.add(s3);
		studentList.add(s4);
		return studentList;

	}

	/**
	 * add the appointments to the queue to populate the queue initally
	 * 
	 * @return Queue<Student> A queue containing students who has made the
	 *         appointments
	 */
	public Queue<Student> addAppointments() {
		Student st;
		int noOfAppointments = getRandomNumber(0, 4);
		System.out.println("noOfAppointments::" + noOfAppointments);
		ArrayList<Student> studentList = getStudentData();
		for (int i = 0; i < noOfAppointments; i++) {
			st = studentList.get(i);
			System.out.println(st.getEmail() + " " + st.getQuestion() + st.getReservationTime() + " ");
			appointments.add(st);

		}
		return appointments;

	}

	/**
	 * add the student to the end of the queue
	 * 
	 * @param s student object to add to the queue
	 */
	public void addAppointmentToend(Student s) {
		appointments.add(s);
	}

	/**
	 * Gives a random number between min and max
	 * 
	 * @param min the min number that can be obtained from random function
	 * @param max the max number that can be obtained from randm function
	 * @return int a random value
	 */
	public int getRandomNumber(int min, int max) {
		Random r = new Random();
		int value = r.nextInt(max - min + 1);
		return value;
	}

}