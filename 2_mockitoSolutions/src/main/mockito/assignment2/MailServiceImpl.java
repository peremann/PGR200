package main.mockito.assignment2;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;

public class MailServiceImpl implements MailService {

	public void send(Message msg) throws MessagingException {
		Transport.send(msg);
	}

}
