//This script logs payload data 

import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;

 

// PayLoad Attachment -1. Initial Payload 
def Message initialIncomingPayload(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
	    messageLog.addAttachmentAsString("1. Initial Incoming Payload", bodyAsString, "text/xml");	
    }    
	return message;
} 


// PayLoad Attachment - Final Error PayLoad after interface has executed 3 times 
def Message errorPayloadAfterRetry(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
	    messageLog.addAttachmentAsString("Final Error Payload After Retry", bodyAsString, "text/xml");	
    }    
	return message;
} 


// PayLoad Attachment - 2. Work Order details of the contingent worker from SF
def Message SFPayload(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
	    messageLog.addAttachmentAsString("2. SF Payload", bodyAsString, "text/xml");	
    }    
	return message;
} 

// PayLoad Attachment -3. Final Payload after message mapping
def Message SFPayloadAfterMap(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
	    messageLog.addAttachmentAsString("3. After Map Final SF Payload", bodyAsString, "text/xml");	
    }    
	return message;
} 

// PayLoad Attachment -Soap Message sent as input for reexecution
def Message soapMsgForRetry(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
	    messageLog.addAttachmentAsString("Soap Msg for Retry", bodyAsString, "text/xml");	
    }    
	return message;
} 


//Log the payload if any error occurs in the interface
def Message exceptionMessage(Message message) {
    
    def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");
//	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null)
//	{
	    messageLog.addAttachmentAsString("Exception Payload", bodyAsString, "text/xml");	
 //   }   
	return message;


} 

