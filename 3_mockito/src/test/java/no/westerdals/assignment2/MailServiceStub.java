package no.westerdals.assignment2;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

public class MailServiceStub implements MailService {

    private List<Message> messages = new ArrayList<Message>();

    /**
     * Stub by placing the incoming message in a list of message (and do NOT
     * send anything). Also emplement a method for determining how many messages
     * have been sent (been placed in the list). Implementation needed for
     * exercise 2.
     */
    //@Override
    public void send(Message msg) throws MessagingException {
        messages.add(msg);
    }

    public int numberSent() {
        return messages.size();
    }

}