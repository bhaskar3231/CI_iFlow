//This script logs payload data 

import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;


// PayLoad Attachment -1.Data Fetched From SF
def Message SFCostCenterData(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");
	
	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
	    messageLog.addAttachmentAsString("1.SF Cost Center Data", bodyAsString, "text/xml");	
    }    
	return message;
}  

// PayLoad Attachment -2.After cust_InpendenceProfile Enrichment Payload
def Message SFSubLevelData(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");
	
	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
	    messageLog.addAttachmentAsString("2.SF Sub Level Data", bodyAsString, "text/xml");	
    }    
	return message;
} 


// PayLoad Attachment -2.Employee class
def Message SFEmployeestatusData(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");
	
	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
	    messageLog.addAttachmentAsString("3.SF Employeestatus Data", bodyAsString, "text/xml");	
    }    
	return message;
} 

// PayLoad Attachment -2.Personnel class and type 
def Message SFPersonnelclassData(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");
	
	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
	    messageLog.addAttachmentAsString("4.SF Personnelclass Data", bodyAsString, "text/xml");	
    }    
	return message;
} 

// PayLoad Attachment -2.After Talentmodel Payload
def Message SFTalentmodel(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");
	
	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
	    messageLog.addAttachmentAsString("5.SF Talentmodel", bodyAsString, "text/xml");	
    }    
	return message;
} 

// PayLoad Attachment -2.After Talentmodel Payload
def Message SFIndustrySector(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");
	
	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
	    messageLog.addAttachmentAsString("6.SF Industry Sector", bodyAsString, "text/xml");	
    }    
	return message;
} 

// PayLoad Attachment -2.After cap/subcapibility Payload
def Message SFsubcapibilityData(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");
	
	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
	    messageLog.addAttachmentAsString("7.SF subcapibility Data", bodyAsString, "text/xml");	
    }    
	return message;
} 

// PayLoad Attachment -3.After Mapping Payload
def Message afterMap(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");
	
	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
	    messageLog.addAttachmentAsString("8.After Mapping Payload", bodyAsString, "text/xml");	
    }    
	return message;
} 

// PayLoad Attachment -4.After xml to txt converted final payload
def Message finalPayload(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");
	
	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
	    messageLog.addAttachmentAsString("9.Final Payload", bodyAsString, "text/xml");	
    }    
	return message;
} 

// PayLoad Attachment -5.After Mapping reject file payload
def Message afterMap_rejectfile(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");
	
	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
	    messageLog.addAttachmentAsString("10.After Map RejectFile Payload ", bodyAsString, "text/xml");	
    }    
	return message;
} 

// PayLoad Attachment -6.After xml to txt converted reject file final payload
def Message rejectfilePayload(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");
	
	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
	    messageLog.addAttachmentAsString("11.Reject File Payload", bodyAsString, "text/xml");	
    }    
	return message;
} 


//Log the payload if any error occurs in the interface
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

/*	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");
	String body1 = map.get("exception_message");
	//log output payload
	//if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null)
	//{
	    messageLog.addAttachmentAsString("Exception Payload", body1, "text/xml");	
    //}  
	return message;*/
}  