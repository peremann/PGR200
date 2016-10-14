package kodeforslag2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class JListDemo extends JFrame {
	private JList<String> list;
	private DefaultListModel<String> listData;
	private ArrayList<String> choices;

	public JListDemo() {
		super("List");
		choices = new ArrayList<String>();
		listData = new DefaultListModel<String>();
		list = new JList<String>(listData);
		add(new JScrollPane(list), BorderLayout.CENTER);
		add(new ButtonPanel(), BorderLayout.SOUTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// setSize(450, 300);
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		new JListDemo();
	}

	private class ButtonPanel extends JPanel implements ActionListener {
		private JButton[] buttons;
		private final String[] buttonTexts = { "Add", "Remove", "Display", "Exit" };

		public ButtonPanel() {
			setLayout(new GridLayout(1, buttonTexts.length));
			buttons = new JButton[buttonTexts.length];

			for (int i = 0; i < buttons.length; i++) {
				buttons[i] = new JButton(buttonTexts[i]);
				buttons[i].addActionListener(this);
				add(buttons[i]);
			}
		}

		public void actionPerformed(ActionEvent event) {
			String teksten = event.getActionCommand();
			if (teksten.equals(buttonTexts[0])) {
				String element = JOptionPane.showInputDialog(JListDemo.this,
						"Enter text");
				listData.addElement(element);
			} else if (teksten.equals(buttonTexts[1])) {
				int index = list.getSelectedIndex();
				if (index >= 0) {
					listData.removeElementAt(index);
				} else {
					JOptionPane
							.showMessageDialog(JListDemo.this, "Choose text!");
				}
			} else if (teksten.equals(buttonTexts[2])) {
				choices = (ArrayList<String>) list.getSelectedValuesList();
				if (choices != null) {
					String data = "";
					for (String s : choices) {
						data += s + "\n";
					}
					JOptionPane.showMessageDialog(JListDemo.this, data);
				}
				choices.clear();
				list.clearSelection();
			} else {
				System.exit(0);
			}
		}
	}

}
