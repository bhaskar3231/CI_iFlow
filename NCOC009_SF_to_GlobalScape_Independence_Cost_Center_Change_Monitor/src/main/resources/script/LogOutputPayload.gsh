import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;

def Message processData(Message message) {
	
	def messageLog = messageLogFactory.getMessageLog(message);
	
	String body = message.getBody(java.lang.String) as String;
	
	//log output payload
	if(messageLog != null){
			messageLog.addAttachmentAsString("Output Payload", body, "text/xml");
	}	
	
	return message;
}