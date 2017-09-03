package mockito.assignment2;

import mockito.assignment2.MailService;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

public class MailServiceStub implements MailService {


    List<Integer> sent = new ArrayList<>();

    /**
     * Stub by placing the incoming message in a list of message (and do NOT
     * send anything). Also implement a method for determining how many messages
     * have been sent (been placed in the list). Implementation needed for
     * exercise 2.
     */
    public void send(Message msg) throws MessagingException {
        sent.add(sent.size() + 1);
    }

    public int getSent() {
        return sent.size();
    }
}
