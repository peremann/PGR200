package kodeforslag2;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatServer0 extends JFrame {
	private static final long serialVersionUID = 5708029136536183246L;
	private JTextField messageField;
	private JTextArea displayArea;
	private JLabel numberOfClientsLabel;
	private int id = 1000;
	private int numberOfClients = 0;
	private ArrayList<ClientConnection> clients;

	public ChatServer0() {
		super("Chatserver");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		clients = new ArrayList<ClientConnection>(0);

		messageField = new JTextField();
		messageField.setEditable(true);
		messageField.setFont(new Font(null, Font.BOLD, 15));
		messageField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String message = event.getActionCommand();
				// ...
			}
		});

		add(messageField, BorderLayout.NORTH);

		displayArea = new JTextArea();
		displayArea.setFont(new Font(null, Font.BOLD, 15));
		add(new JScrollPane(displayArea), BorderLayout.CENTER);
		displayArea.setEditable(false);
		numberOfClientsLabel = new JLabel("Number of clients connected: 0");
		add(numberOfClientsLabel, BorderLayout.SOUTH);
		setSize(400, 200);
		setVisible(true);
		start();
	}

	private void start() {

	}

	private class ClientConnection implements Runnable {
		private int id;
		private Socket clientContact;

		public ClientConnection(Socket socket, int id) {
			this.id = id;
			this.clientContact = socket;
			new Thread(this).start();
		}

		@Override
		public void run() {

		}

		public int getID() {
			return id;
		}

		public String toString() {
			return "Client " + id;
		}

		public boolean equals(Object o) {
			if (!(o instanceof ClientConnection))
				return false;
			if (o == this)
				return true;
			ClientConnection k = (ClientConnection) o;
			return k.getID() == getID();
		}

		public void sendData(String melding) {

		}

	}

	private void displayMessage(String message) {
		displayArea.append(message + "\n");
		displayArea.setCaretPosition(displayArea.getText().length());
	}

	public static void main(String args[]) {
		new ChatServer0();
	}
}