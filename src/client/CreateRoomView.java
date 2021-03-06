package client;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.simple.parser.ParseException;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import functionality.controllers.RoomController;
import tests.Test_LocalRooms;

public class CreateRoomView extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel createroomPanel;
	public JButton btnExit, btnDone, btnBack;
	public JLabel lblRoomName, lblTopicQuestion;
	public JTextField txtRoomName, txtTopicQuestion;
	public static String roomname;
	HomeScreenView homescreen;
	RoomController roomcontroller;


	/**
	 * Create the application.
	 */
	public CreateRoomView() {
		roomcontroller = new RoomController();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//Create the frame
		createroomPanel = new JPanel();
		createroomPanel.setLayout(new GridLayout(3,1));
		JPanel infoPanel = new JPanel();
		JPanel btnPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(4,1));
		btnPanel.setLayout(new GridBagLayout());
	
		JLabel lblCreateRoom = DefaultComponentFactory.getInstance().createTitle("Create new room");
		
		btnBack = new JButton();
		btnBack.setText("Back");
		btnBack.addActionListener(this);
		
		btnDone = new JButton();
		btnDone.setText("Done");
		btnDone.addActionListener(this);
		
		lblRoomName = new JLabel();
		lblRoomName.setText("Enter Room name");
		lblTopicQuestion = new JLabel();
		lblTopicQuestion.setText("Enter a topic/question");
		
		txtRoomName = new JTextField();
		txtTopicQuestion = new JTextField();
		
		createroomPanel.add(infoPanel);
		infoPanel.add(lblRoomName);
		infoPanel.add(txtRoomName);
		infoPanel.add(lblTopicQuestion);
		infoPanel.add(txtTopicQuestion);
		createroomPanel.add(btnPanel);
		btnPanel.add(btnDone);
		btnPanel.add(btnBack);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if(cmd.equals("Back")){
			homescreen = new HomeScreenView();
			MainWindow.frame.getContentPane().add(homescreen.homeScreenPanel);
			createroomPanel.setVisible(false);
		}
		if(cmd.equals("Done")){
			
			try {
				roomcontroller.createRoom(txtRoomName.getText().toString(), "public");
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}

			homescreen = new HomeScreenView();
			MainWindow.frame.getContentPane().add(homescreen.homeScreenPanel);
			createroomPanel.setVisible(false);

		}
	}
}
