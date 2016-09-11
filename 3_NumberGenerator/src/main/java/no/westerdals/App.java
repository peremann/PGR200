package no.westerdals;

import java.util.UUID;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    public static String generateUniqueKey() {
        return UUID.randomUUID().toString();
    }

}
