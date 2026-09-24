//This script logs payload data 

import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;

def Message sfresponse(Message message) {
	
	def messageLog = messageLogFactory.getMessageLog(message);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");
	String body = message.getBody(java.lang.String) as String;
	
	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
			messageLog.addAttachmentAsString("SF Response", body, "text/xml");
	}	
	
	return message;
}

def Message beforemap(Message message) {
	
	def messageLog = messageLogFactory.getMessageLog(message);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");
	String body = message.getBody(java.lang.String) as String;
	
	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
			messageLog.addAttachmentAsString("Before Mapping", body, "text/xml");
	}	
	
	return message;
}
def Message afterfilter(Message message) {
	
	def messageLog = messageLogFactory.getMessageLog(message);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");
	String body = message.getBody(java.lang.String) as String;
	
	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
			messageLog.addAttachmentAsString("After Filter", body, "text/xml");
	}	
	
	return message;
}
def Message afterMap(Message message) {
	
	def messageLog = messageLogFactory.getMessageLog(message);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");
	String body = message.getBody(java.lang.String) as String;
	
	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
			messageLog.addAttachmentAsString("After Map", body, "text/xml");
	}	
	
	return message;
}
def Message exceptionMessage(Message message) {
	
	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null)
	{
	    messageLog.addAttachmentAsString("Exception Payload", bodyAsString, "text/xml");	
    }   
	return message;
}  
def Message successRecords(Message message) {
	
	def messageLog = messageLogFactory.getMessageLog(message);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");
	String body = message.getBody(java.lang.String) as String;
	
	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
			messageLog.addAttachmentAsString("Success Payload", body, "text/xml");
	}	
	
	return message;
}

def Message afterMap_CCChangesRecords(Message message) {
	
	def messageLog = messageLogFactory.getMessageLog(message);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");
	String body = message.getBody(java.lang.String) as String;
	
	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
			messageLog.addAttachmentAsString("After Map CCChanges Payload", body, "text/xml");
	}	
	
	return message;
}

def Message sf_CCChangesRecords(Message message) {
	
	def messageLog = messageLogFactory.getMessageLog(message);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");
	String body = message.getBody(java.lang.String) as String;
	
	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
			messageLog.addAttachmentAsString("SF CCChanges Payload", body, "text/xml");
	}	
	
	return message;
}

def Message After_xslt_CCChangesRecords(Message message) {
	
	def messageLog = messageLogFactory.getMessageLog(message);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");
	String body = message.getBody(java.lang.String) as String;
	
	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
			messageLog.addAttachmentAsString("After XSLT CCChanges Payload", body, "text/xml");
	}	
	
	return message;
}



def Message afterMap_CCChangesRecord(Message message) {
	
	def messageLog = messageLogFactory.getMessageLog(message);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");
	String body = message.getBody(java.lang.String) as String;
	
	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
			messageLog.addAttachmentAsString("Final CC Changes Payload", body, "text/xml");
	}	
	
	return message;
}

















