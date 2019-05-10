package swing;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Queue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 
 * @author poorva
 *
 */
public class App {

	// All the constant strings used
	private static final String COLON = " : ";
	private static final String SPACE = " ";
	private static final String PUSHED_TO_QUEUE = "Pushed_To_Queue";
	private static final String BANNED = "Banned";
	private static final String NULL = null;

	private JFrame frame;
	Reservation res = new Reservation();
	Queue<Student> queue;
	Student s;
	SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
	// prof
	Boolean check = true;

	/**
	 * convert the reservation time from string to long
	 * 
	 * @param date  This is the param for function. 
	 * @return long this returns the reservation time
	 */
	public long getReservationTime(String date) {
		long mills = 0;
		try {
			Date time = (Date) sdf.parse(date);
			mills = time.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return mills;
	}

	/**
	 * Handler for absent student. Bans the student based on the late time 
	 * 
	 * @param s Student is passed as param 
	 * @return string telling if the student is banned or added back to the queue
	 */
	public String absentHander(Student s) {
		long reservationTime = getReservationTime(s.getReservationTime());
		if (reservationTime <= System.currentTimeMillis() - 660000) {
			s.setBanDate(new Date());
			return BANNED;
		} else if (reservationTime > System.currentTimeMillis() - 660000
				&& reservationTime <= System.currentTimeMillis()) {
			res.addAppointmentToend(s);
			return PUSHED_TO_QUEUE;
		}
		return NULL;
	}

	
	/**
	 * Method to get all the appoinments in the queue
	 * 
	 * @return string the holds the details of all the appointment in the queue. T
	 */
	public String getQueueStatusAsString() {
		String content = "<html>";
		int i = 1;
		for (Student item : queue) {
			content = content + i++ + COLON + item.getEmail() + SPACE + item.getQuestion() + SPACE
					+ item.getReservationTime() + "<br>";
		}
		return content + "</html>";
	}

	/**
	 * diplays the second frame that shows the queue status. This will be called
	 * when the present / absent button is clicked
	 */
	public void displayQueueStatusFrame() {
		String queueStatus = getQueueStatusAsString();
		JFrame queueFrame = new JFrame();
		queueFrame.setBounds(300, 200, 500, 300);
		queueFrame.getContentPane().setLayout(null);

		JLabel queueSize = new JLabel("Current Queue Status::" + queue.size());
		queueSize.setBounds(21, 150, 300, 16);
		queueFrame.getContentPane().add(queueSize);

		JLabel queueDisplay = new JLabel(queueStatus);
		queueDisplay.setBounds(21, 33, 500, 100);
		queueFrame.getContentPane().add(queueDisplay);

		JButton absentOKButton = new JButton("OK");
		absentOKButton.setBounds(220, 200, 50, 29);
		queueFrame.getContentPane().add(absentOKButton);

		queueFrame.setVisible(true);

		absentOKButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				queueFrame.dispose();
				// ask prof
				if (check)
					frame.setVisible(true);

			}
		});

	}

	/**
	 * Create the application.
	 * 
	 * @throws ParseException
	 */
	public App() throws ParseException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws ParseException
	 */
	private void initialize() throws ParseException {

		JButton presentButton = new JButton("Present");
		JButton absentButton = new JButton("Absent");

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel emailLabel = new JLabel("Email Address");
		emailLabel.setBounds(21, 33, 98, 16);
		frame.getContentPane().add(emailLabel);

		JLabel sampleQuestionLabel = new JLabel("Sample Question");
		sampleQuestionLabel.setBounds(21, 61, 116, 16);
		frame.getContentPane().add(sampleQuestionLabel);

		JLabel reservationTimeLabel = new JLabel("Reservation Time");
		reservationTimeLabel.setBounds(21, 103, 116, 16);
		frame.getContentPane().add(reservationTimeLabel);

		JLabel emailValueLabel = new JLabel("No Appointments");
		emailValueLabel.setEnabled(false);
		emailValueLabel.setBounds(239, 33, 145, 16);
		frame.getContentPane().add(emailValueLabel);

		JLabel sampleQuestionValueLabel = new JLabel("No Appointments");
		sampleQuestionValueLabel.setEnabled(false);
		sampleQuestionValueLabel.setBounds(239, 43, 205, 52);
		frame.getContentPane().add(sampleQuestionValueLabel);

		JLabel reservationTimeValueLabel = new JLabel("No Appointments");
		reservationTimeValueLabel.setEnabled(false);
		reservationTimeValueLabel.setBounds(239, 103, 145, 16);
		frame.getContentPane().add(reservationTimeValueLabel);

		absentButton.setBounds(21, 151, 117, 29);
		frame.getContentPane().add(absentButton);
		absentButton.setEnabled(false);

		presentButton.setBounds(239, 151, 117, 29);
		frame.getContentPane().add(presentButton);
		presentButton.setEnabled(false);

		queue = res.addAppointments();
		if (!queue.isEmpty()) {
			s = queue.remove();
			System.out.println("Queue Size" + queue.size());
			absentButton.setEnabled(true);
			presentButton.setEnabled(true);
		}

		if (s != null) {
			emailValueLabel.setText(s.getEmail());
			sampleQuestionValueLabel.setText(s.getQuestion());
			reservationTimeValueLabel.setText(s.getReservationTime());
		}

		/*
		 * action listener for absent button
		 */
		absentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				String actionStatus = absentHander(s);
				System.out.println(actionStatus);
				displayQueueStatusFrame();

				// ask prof
				if (!queue.isEmpty()) {
					s = queue.remove();
					System.out.println("Queue Size" + queue.size());
					absentButton.setEnabled(true);
					presentButton.setEnabled(true);
					emailValueLabel.setText(s.getEmail());
					sampleQuestionValueLabel.setText(s.getQuestion());
					reservationTimeValueLabel.setText(s.getReservationTime());

				} else {
					s = null;
					emailValueLabel.setText("NA");
					sampleQuestionValueLabel.setText("NA");
					reservationTimeValueLabel.setText("NA");
				}

			}
		});

		/*
		 * action listener for present button
		 */
		presentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				// ask prof
				check = false;
				displayQueueStatusFrame();
			}
		});

	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
