package main.mockito.assignment2;

import javax.mail.Message;
import javax.mail.MessagingException;

public interface MailService {
	public void send(Message msg) throws MessagingException;
}
