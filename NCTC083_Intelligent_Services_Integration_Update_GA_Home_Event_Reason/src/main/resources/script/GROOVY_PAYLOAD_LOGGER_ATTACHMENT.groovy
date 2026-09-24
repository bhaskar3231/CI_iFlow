/*=====================================================
     PURPOSE: The purpose of this groovy script is to log the payload a logger in CI tenant.
=========================================================*/

import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;

/* ----  Log the incoming payload fron SuccessFactors ----------*/
def Message InitialIncomingMsg(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
    def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

    if(payloadLogging.toUpperCase().equals("Y") && messageLog != null){
	    messageLog.addAttachmentAsString("1. Intial Incoming Soap Msg from SF - XML", bodyAsString, "text/xml");	
    }    
	return message;
}  

/* ----  Log the Host Record - Job Information fetched from SuccessFactors ----------*/
def Message hostRecord(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
    def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

    if(payloadLogging.toUpperCase().equals("Y") && messageLog != null){
	    messageLog.addAttachmentAsString("2. Host Record - XML", bodyAsString, "text/xml");	
    }    
	return message;
}  

/* ----  Log the Home Record - Job Information fetched from SuccessFactors ----------*/
def Message homeRecord(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
    def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

    if(payloadLogging.toUpperCase().equals("Y") && messageLog != null){
	    messageLog.addAttachmentAsString("3. Home Record - XML", bodyAsString, "text/xml");	
   }    
	return message;
}  

/* ----  Log the Payload sent to SuccessFactors ----------*/
def Message sfRequestPayload(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
    def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

    if(payloadLogging.toUpperCase().equals("Y") && messageLog != null){ 
	    messageLog.addAttachmentAsString("4. SF request Payload - XML", bodyAsString, "text/xml");	
   }    
	return message;
}  

/* ----  Log the upsert response coming from SuccessFactors ----------*/
def Message sfResponsePayload(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
    def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

    if(payloadLogging.toUpperCase().equals("Y") && messageLog != null){ 
	    messageLog.addAttachmentAsString("5. SF Response Payload - XML", bodyAsString, "text/xml");	
	}    
	return message;
}  


 
/* -- Log the error message payload from SF in case there was any error in upsertion of EmpJob data to SF --- */
def Message exceptionSFResponse(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

    if(payloadLogging.toUpperCase().equals("Y") && messageLog != null){ 
	    messageLog.addAttachmentAsString("Exception Payload", bodyAsString, "text/xml");	
	}   
	return message;
} 
