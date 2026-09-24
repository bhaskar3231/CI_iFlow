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

def Message filter2(Message message) {
	
	def messageLog = messageLogFactory.getMessageLog(message);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");
	String body = message.getBody(java.lang.String) as String;
	
	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
			messageLog.addAttachmentAsString("2. Filter", body, "text/xml");
	}	
	
	return message;
}
def Message filter3(Message message) {
	
	def messageLog = messageLogFactory.getMessageLog(message);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");
	String body = message.getBody(java.lang.String) as String;
	
	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
			messageLog.addAttachmentAsString("3. Filter", body, "text/xml");
	}	
	
	return message;
}
def Message afterValidation(Message message) {
	
	def messageLog = messageLogFactory.getMessageLog(message);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");
	String body = message.getBody(java.lang.String) as String;
	
	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
			messageLog.addAttachmentAsString("After Validation", body, "text/xml");
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

def Message validationLog(Message message) {
	
	def messageLog = messageLogFactory.getMessageLog(message);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");
	String body = message.getBody(java.lang.String) as String;
	
	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
			messageLog.addAttachmentAsString("Validation Log", body, "text/xml");
	}	
	
	return message;
}

def Message exceptionMessage(Message message) {
	
	def messageLog = messageLogFactory.getMessageLog(message);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");
	String body = message.getBody(java.lang.String) as String;
	
	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
			messageLog.addAttachmentAsString("exceptionMessage", body, "text/xml");
	}	
	
	return message;
}

def Message DeltaPayloadLogging(Message message) {
	
	def messageLog = messageLogFactory.getMessageLog(message);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");
	String body = message.getBody(java.lang.String) as String;
	
	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
			messageLog.addAttachmentAsString("DeltaPayloadLogging", body, "text/xml");
	}	
	
	return message;
}
def Message SFDeltaPayloadLogging(Message message) {
	
	def messageLog = messageLogFactory.getMessageLog(message);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");
	String body = message.getBody(java.lang.String) as String;
	
	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
			messageLog.addAttachmentAsString("SFDeltaPayloadLogging", body, "text/xml");
	}	
	
	return message;
}
def Message SFFullLoadPayloadLogging(Message message) {
	
	def messageLog = messageLogFactory.getMessageLog(message);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");
	String body = message.getBody(java.lang.String) as String;
	
	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
			messageLog.addAttachmentAsString("SFFullLoadPayloadLogging", body, "text/xml");
	}	
	
	return message;
}
def Message FullLoadPayloadLogging(Message message) {
	
	def messageLog = messageLogFactory.getMessageLog(message);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");
	String body = message.getBody(java.lang.String) as String;
	
	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
			messageLog.addAttachmentAsString("FullLoadPayloadLogging", body, "text/xml");
	}	
	
	return message;
}