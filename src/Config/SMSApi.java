package Config;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;



/**
 *
 * @author salmouc
 */
public class SMSApi {
    
    
     public static final String ACCOUNT_SID = "ACf21e7eb519ea419819035ea916148525";
    public static final String AUTH_TOKEN = "404c67c0779a9182924d1cad2ef1ecff";

    public static void sendSMS( String msg) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(new PhoneNumber("+21655841954"),new PhoneNumber("+19206787636"), msg).create();

        System.out.println(message.getSid());

    }
}
