package Config;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author salmouc
 */
public class SMSApi {

    public SMSApi() {
    }
    public static final String ACCOUNT_SID = "AC2d8fa7f72ddb64a7316285415d146ddb";
    public static final String AUTH_TOKEN = "94b5a3e4e0698b016bcab0202e9df7d0";

    public void sendSMS(String num, String msg) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber("+21627198506"),
                new PhoneNumber("+17404803036"),
                "Livraison Créer avec succes, " + msg).create();

        System.out.println(message.getSid());

    }
}
