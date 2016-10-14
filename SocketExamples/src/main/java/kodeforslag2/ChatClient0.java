package kodeforslag2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatClient0 extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField messageField;
	private JTextArea displayArea;
	private String server;

	/**
	 * Initializes Client and GUI elements.
	 * 
	 * @param host
	 *            the server host to communicate with
	 */
	public ChatClient0(String host) {
		super("Client");
		server = host;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		messageField = new JTextField();
		messageField.setEditable(true);
		messageField.setFont(new Font(null, Font.BOLD, 15));
		messageField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				sendData(event.getActionCommand());
				messageField.setText("");
			}
		});
		add(messageField, BorderLayout.NORTH);

		displayArea = new JTextArea();
		displayArea.setFont(new Font(null, Font.BOLD, 15));
		add(new JScrollPane(displayArea), BorderLayout.CENTER);
		displayArea.setEditable(false);
		setSize(400, 200);
		setVisible(true);
		startClient();
	}

	/**
	 * Create the socket and read messages from Server.
	 */
	private void startClient() {

	}

	private void sendData(String melding) {
	}

	private void displayMessage(String message) {
		displayArea.append(message + "\n");
		displayArea.setCaretPosition(displayArea.getText().length());
	}

	public static void main(String args[]) {
		String ipAddress = "localhost";
		new ChatClient0(ipAddress);
	}

}