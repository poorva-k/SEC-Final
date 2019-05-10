package swing;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Student class to hold the details of the student
 * 
 * @author poorva
 *
 */
public class Student {
	String email;
	String question;
	long reservationTime;
	Date banDate;
	SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
	Date resultdate;

	
	/**
	 * parameterized  constructor to initialise the student object 
	 * @param email
	 * @param question
	 * @param reservationTime
	 */
	Student(String email, String question, long reservationTime) {
		this.email = email;
		this.question = question;
		this.reservationTime = reservationTime;
	}

	public Date getBanDate() {
		return banDate;
	}

	public void setBanDate(Date banDate) {
		this.banDate = banDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getReservationTime() {
		resultdate = new Date(reservationTime);
		return sdf.format(resultdate);
	}

	public void setReservationTime(long reservationTime) {
		this.reservationTime = reservationTime;
	}

}
