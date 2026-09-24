import com.sap.gateway.ip.core.customdev.util.Message;
import java.lang.Exception;
import java.text.SimpleDateFormat;


def Message processData(Message message) {
	
	//Read the logging property defined in the interface
	def pmap = message.getProperties();
	String enableLogging = pmap.get("enablePayloadLogging");
	
	//Log if logging is enabled
	if(enableLogging != null && enableLogging.toUpperCase().equals("TRUE")){
		def body = message.getBody(java.lang.String) as String;
		
	//Create the attachment name for payload log
		String timeStamp = new SimpleDateFormat("HH:mm:ss.SSS").format(new Date());
		String logTitle = timeStamp + " :Terminated Employees Payload ";
		def messageLog = messageLogFactory.getMessageLog(message);
		
    //Create the attachment with the defined name
		if(messageLog != null){			
				messageLog.addAttachmentAsString(logTitle, body, "text/xml");
					}
					else{
				throw new Exception("Message Could not be logged");
			
		}
	}
	
	return message;
}
