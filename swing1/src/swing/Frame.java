package swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Queue;
import java.awt.event.ActionEvent;
import swing.Reservation;
import javax.swing.JRadioButton;
public class Frame {

	private JFrame frame;
	Student s;
	boolean check=true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame window = new Frame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws ParseException 
	 */
	public Frame() throws ParseException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ParseException 
	 */
	private void initialize() throws ParseException {
		boolean flag=true;
		JButton btnButton = new JButton("Present");
		JButton btnNewButton = new JButton("Absent");
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");    
		Reservation res=new Reservation();
		Queue<Student> queue=res.addAppointments();
		if(!queue.isEmpty()) {
			s=queue.remove();
		}
		else {
			btnButton.setEnabled(false);
			btnNewButton.setEnabled(false);
		}
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Email Address");
		lblNewLabel.setBounds(21, 33, 98, 16);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblSampleQuestion = new JLabel("Sample Question");
		lblSampleQuestion.setBounds(21, 61, 116, 16);
		frame.getContentPane().add(lblSampleQuestion);

		JLabel lblReservationTime = new JLabel("Reservation Time");
		lblReservationTime.setBounds(21, 103, 116, 16);
		frame.getContentPane().add(lblReservationTime);

		JLabel lblNewLabel_1 = new JLabel("No Appointments");
		lblNewLabel_1.setEnabled(false);
		lblNewLabel_1.setBounds(239, 33, 145, 16);
		frame.getContentPane().add(lblNewLabel_1);



		JLabel lblNoAppointments = new JLabel("No Appointments");
		lblNoAppointments.setEnabled(false);
		lblNoAppointments.setBounds(239, 43, 205, 52);
		frame.getContentPane().add(lblNoAppointments);


		JLabel lblNoAppointments_1 = new JLabel("No Appointments");
		lblNoAppointments_1.setEnabled(false);
		lblNoAppointments_1.setBounds(239, 103, 145, 16);


		if(flag==true && s!=null) {
			lblNewLabel_1.setText(s.getEmail());
			lblNoAppointments.setText(s.getQuestion());
			lblNoAppointments_1.setText(s.getReservationTime());
			flag=false;
		}

		frame.getContentPane().add(lblNoAppointments_1);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!queue.isEmpty()) {
					Date date;
					try {
						date = (Date)sdf.parse(s.getReservationTime());
						long mills=date.getTime();
						if(mills<=System.currentTimeMillis()-660000) {
							//currentDate=banDate;
							System.out.println("Heyyy");
							Date date1=new Date(System.currentTimeMillis());
							s.setBanDate(date1);
							JOptionPane.showMessageDialog(null, "This student is banned!");
							check=true;
						}
						else if(mills>System.currentTimeMillis()-660000 && mills<System.currentTimeMillis()) {
							//s.setReservationTime(System.currentTimeMillis());
							System.out.println("Hello");
							if(check) {
								System.out.println("Odd");
								res.addAppointmentToend(s);
							}
							//check=true;
						}
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Current Queue Status::"+queue.size());
					s=queue.remove();
					lblNewLabel_1.setText(s.getEmail());
					lblNoAppointments.setText(s.getQuestion());
					lblNoAppointments_1.setText(s.getReservationTime());

				}
				else {
					Date date;
					try {
						date = (Date)sdf.parse(s.getReservationTime());
						long mills=date.getTime();
						if(mills<=System.currentTimeMillis()-660000) {
							//currentDate=banDate;
							System.out.println("Heyyy!!");
							Date date1=new Date(System.currentTimeMillis());
							s.setBanDate(date1);
							JOptionPane.showMessageDialog(null, "This student is banned!");
						}
						else if(mills>System.currentTimeMillis()-660000 && mills<System.currentTimeMillis()) {
							//s.setReservationTime(System.currentTimeMillis());
							System.out.println("Hello!!!");
							res.addAppointmentToend(s);
							JOptionPane.showMessageDialog(null, "Current Queue Status::"+queue.size());
							check=false;
						}
					}catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(check) {
						lblNewLabel_1.setText("No Appointment");
						lblNoAppointments.setText("No Appointment");
						lblNoAppointments_1.setText("No Appointment");
						System.out.println("HEyyyoo");
						btnNewButton.setEnabled(false);
						btnButton.setEnabled(false);
						JOptionPane.showMessageDialog(null, "Current Queue Status::"+queue.size());
						JOptionPane.showMessageDialog(null, "No Appointments Remaining!!");
					}
				}

			}
		});
		btnNewButton.setBounds(21, 151, 117, 29);
		frame.getContentPane().add(btnNewButton);

		//JButton btnButton = new JButton("Present");
		btnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!queue.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Current Queue Status::"+queue.size());
					s=queue.remove();
					lblNewLabel_1.setText(s.getEmail());
					lblNoAppointments.setText(s.getQuestion());
					lblNoAppointments_1.setText(s.getReservationTime());
					check=true;
				}
				else {
					lblNewLabel_1.setText("No Appointment");
					lblNoAppointments.setText("No Appointment");
					lblNoAppointments_1.setText("No Appointment");
					btnButton.setEnabled(false);
					btnNewButton.setEnabled(false);
					System.out.println("Huhuhu");
					JOptionPane.showMessageDialog(null, "Current Queue Status::"+queue.size());
					JOptionPane.showMessageDialog(null, "No Appointments Remaining!!");
				}
			}
		});
		btnButton.setBounds(239, 151, 117, 29);
		frame.getContentPane().add(btnButton);


	}
}
