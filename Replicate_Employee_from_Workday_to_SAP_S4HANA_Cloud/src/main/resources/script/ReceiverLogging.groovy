import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import java.lang.StringBuffer;

def Message processData(Message message) {
    //Body
    def messageLog = messageLogFactory.getMessageLog(message);
    
    def logProperty = message.getProperty("PropertyLogging") as String;
    def logHeader = message.getProperty("HeaderLogging") as String;
    def logBody = message.getProperty("BodyLogging") as String;
    def refernceID = message.getProperty("SAP_ReferenceID") as String;
    String content = "";
    
    if(messageLog != null) {
        messageLog.setStringProperty("Logging#1", "Payload");
        
        if (logProperty != null && logProperty.equalsIgnoreCase("TRUE")) {
            def propertyMap = message.getProperties() as String;
             content = content + "\n" + "Message Properties" + "\n" + "\n" + propertyMap + "\n" 
        }
        
        if (logHeader != null && logHeader.equalsIgnoreCase("TRUE")) {
            def header = message.getHeaders() as String;
            content = content + "\n" + header;
        }
        
        if (logBody == null || logBody.equalsIgnoreCase("TRUE")) {
            def body = message.getBody(java.lang.String) as String;
            content = content + "\n" + body;
        }
        
        if (content.length() > 0) {
            def attachID  = "S4HANA Cloud Response MessageLog for Employee '" + refernceID + "'"	
            messageLog.addAttachmentAsString(attachID, content, "text/plain");    
        }
    }

    return message;
}