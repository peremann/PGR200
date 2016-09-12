package mockito.assignment2;

import main.mockito.assignment2.MailService;

import javax.mail.Message;
import javax.mail.MessagingException;

public class MailServiceStub implements MailService {

    /**
     * Stub by placing the incoming message in a list of message (and do NOT
     * send anything). Also emplement a method for determining how many messages
     * have been sent (been placed in the list). Implementation needed for
     * exercise 2.
     */
    public void send(Message msg) throws MessagingException {
    }

}