import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
def Message processData(Message message) {
def msgBodyOriginal = message.getBody(java.lang.String) as String
//Special Character Handling
def msgBodyModified = msgBodyOriginal.replaceAll('&amp;','%26');
message.setBody(msgBodyModified);
return message;
}