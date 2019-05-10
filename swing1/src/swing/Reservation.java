package swing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import swing.Student;
import java.sql.Time;
import java.text.SimpleDateFormat;


public class Reservation {
	Reservation(){
		//addAppointments();
	}
	Queue<Student> appointments = new LinkedList<>();
	public ArrayList<Student> getStudentData(){
		ArrayList<Integer> timeList = new ArrayList<>();
		timeList.add(300000);
		timeList.add(660000);
		timeList.add(0);
		Student s1 = new Student("s1@buffalo.edu","What to do in question 1?",System.currentTimeMillis()- timeList.get(getRandomNumber(0,2)));
		Student s2= new Student("s2@buffalo.edu","No Sample Question",System.currentTimeMillis()-timeList.get(getRandomNumber(0,2)));
		Student s3= new Student("s3@buffalo","Didn't understand the distributed concept!",System.currentTimeMillis()-timeList.get(getRandomNumber(0,2)));
		Student s4=new Student("s4@buffalo.edu","No Sample Question",System.currentTimeMillis()-timeList.get(getRandomNumber(0,2)));
		
		ArrayList<Student> studentList = new ArrayList<>();
		studentList.add(s1);
		studentList.add(s2);
		studentList.add(s3);
		studentList.add(s4);
		return studentList;
		
	}
	
	public Queue<Student> addAppointments() {
		// add the appointments to the queue
		Student st;
		int noOfAppointments = getRandomNumber(0,4);
		System.out.println("noOfAppointments::"+noOfAppointments);
		ArrayList<Student> studentList = getStudentData();
		for(int i = 0 ; i < noOfAppointments ; i++) {
			st=studentList.get(i);
			System.out.println(st.getEmail()+" "+st.getQuestion()+ st.getReservationTime()+" ");
			appointments.add(st);
			
		}
		return appointments;
		
	}
	
	public Student getCurrentReservation() {
		if(appointments.isEmpty())
			return null;
		return appointments.remove();
	}
	
	public void addAppointmentToend(Student s) {
		appointments.add(s);
	}
	
	public int getRandomNumber(int min, int max) {
		Random r=new Random();
		int value = r.nextInt(max-min +1);
		return value;
	}
	
//	public static void main(String args[]) {
//		Reservation r = new Reservation();
//		r.addAppointments();
//	}
}