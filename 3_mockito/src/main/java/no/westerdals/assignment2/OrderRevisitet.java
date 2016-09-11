package no.westerdals.assignment2;

import no.westerdals.Warehouse;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.swing.*;
import java.util.logging.Logger;

public class OrderRevisitet {

	private String productName;
	private int quantity;
	boolean filled;
	private MailService mailService;

	Logger logger = Logger.getLogger("MyLog");

	public OrderRevisitet(String productName, int quantity) {
		this.productName = productName;
		this.quantity = quantity;
		mailService = new MailServiceImpl();
	}

	public void fill(Warehouse warehouse) {
		if (warehouse.hasInventory(productName, quantity)) {
			try {
				warehouse.remove(productName, quantity);
				filled = true;
			} catch (RuntimeException e) {
				logger.info("Unable to fill order:" + e.getStackTrace());
			}
		} else {
			sendNotEnoughQuantityMail();
		}
	}

	private void sendNotEnoughQuantityMail() {
		try {
			mailService
					.send(getNotEnoughQuantityMassage(productName, quantity));
		} catch (MessagingException e) {
			// Unable to send email. Log?
			e.printStackTrace();
		}
	}

	private Message getNotEnoughQuantityMassage(String productName2,
			int quantity2) {
		/*
		 * Not implemented (and no need for it in these exercises). Should have
		 * been placed in a MessageProvider class.
		 */
		return null;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public boolean isFilled() {
		return filled;
	}
	
	public String returnStringMethod(){
		JOptionPane.showMessageDialog(null, "What?!?");
		return "dummy";
	}

}
