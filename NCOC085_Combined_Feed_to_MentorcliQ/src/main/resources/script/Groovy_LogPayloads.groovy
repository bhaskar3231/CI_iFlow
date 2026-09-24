//This script logs payload data 

import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap; 

def Message xlsxToCsvTIL(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

	//log output payload
	if(payloadLogging.toUpperCase().equals("Y") && messageLog != null){
	    messageLog.addAttachmentAsString("1.1 XlsxToCSV Converted Payload - TIL", bodyAsString, "text/xml");	
    }    
	return message;
}

def Message afterCSVToXML_TIL(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

	//log output payload
	if(payloadLogging.toUpperCase().equals("Y") && messageLog != null){
	    messageLog.addAttachmentAsString("1.2 After CSVToXML TIL Payload", bodyAsString, "text/xml");	
    }    
	return message;
} 

def Message xlsxToCsvDNU(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

	//log output payload
	if(payloadLogging.toUpperCase().equals("Y") && messageLog != null){
	    messageLog.addAttachmentAsString("2.1 XlsxToCSV Converted Payload - DNU", bodyAsString, "text/xml");	
    }    
	return message;
}

def Message afterCSVToXML_DNU(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

	//log output payload
	if(payloadLogging.toUpperCase().equals("Y") && messageLog != null){
	    messageLog.addAttachmentAsString("2.2 After CSVToXML DNU Payload", bodyAsString, "text/xml");	
    }    
	return message;
} 

def Message xlsxToCsvBCLP(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

	//log output payload
	if(payloadLogging.toUpperCase().equals("Y") && messageLog != null){
	    messageLog.addAttachmentAsString("3.1 XlsxToCSV Converted Payload - BCLP", bodyAsString, "text/xml");	
    }    
	return message;
}

def Message afterCSVToXML_BCLP(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

	//log output payload
	if(payloadLogging.toUpperCase().equals("Y") && messageLog != null){
	    messageLog.addAttachmentAsString("3.2 After CSVToXML BCLP Payload", bodyAsString, "text/xml");	
    }    
	return message;
} 

def Message xlsxToCsvBuddy(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

	//log output payload
	if(payloadLogging.toUpperCase().equals("Y") && messageLog != null){
	    messageLog.addAttachmentAsString("4.1 XlsxToCSV Converted Payload - Buddy", bodyAsString, "text/xml");	
    }    
	return message;
}

def Message afterCSVToXML_Buddy(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

	//log output payload
	if(payloadLogging.toUpperCase().equals("Y") && messageLog != null){
	    messageLog.addAttachmentAsString("4.2 After CSVToXML Buddy Payload", bodyAsString, "text/xml");	
    }    
	return message;
} 

def Message xlsxToCsvPMReport(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

	//log output payload
	if(payloadLogging.toUpperCase().equals("Y") && messageLog != null){
	    messageLog.addAttachmentAsString("5.1 XlsxToCSV Converted Payload - PMReport", bodyAsString, "text/xml");	
    }    
	return message;
}

def Message afterCSVToXML_PMReport(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

	//log output payload
	if(payloadLogging.toUpperCase().equals("Y") && messageLog != null){
	    messageLog.addAttachmentAsString("5.2 After CSVToXML PMReport Payload", bodyAsString, "text/xml");	
    }    
	return message;
}

//US & USI Employees data fetched from SF who are active, no show or active future dated hires(from last Saturday till today)  
//For future dated hires - Select employees whose hire date is on Saturday of last week till four weeks from current date from SAP SuccessFactors EC and add the details to combined file. So from last Saturay till today, those employees will be active in the system.
def Message activeSFEmps(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

	//log output payload
	if(payloadLogging.toUpperCase().equals("Y") && messageLog != null){
	    messageLog.addAttachmentAsString("6.1 SF Emps - Active", bodyAsString, "text/xml");	
    }    
	return message;
}

//After filter active employees payload
def Message activeAfterTransformationSFEmps(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

	//log output payload
	if(payloadLogging.toUpperCase().equals("Y") && messageLog != null){
	    messageLog.addAttachmentAsString("6.2 SF Emps - After Transformation:Active", bodyAsString, "text/xml");	
    }    
	return message;
}  

//US & USI Employees data fetched from SF who are terminated from the last run date
//Select all the terminations from last run date including the day to any future date from SAP SuccessFactors EC and add the details to combined file.

def Message terminatedSFEmps(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

	//log output payload
	if(payloadLogging.toUpperCase().equals("Y") && messageLog != null){
	    messageLog.addAttachmentAsString("7.1 SF Emps - Terminated", bodyAsString, "text/xml");	
    }    
	return message;
} 

def Message afterFilterTerminatedSFEmps(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

	//log output payload
	if(payloadLogging.toUpperCase().equals("Y") && messageLog != null){
	    messageLog.addAttachmentAsString("7.2 SF Emps - Filter Valid Records:Terminated", bodyAsString, "text/xml");	
    }    
	return message;
} 

def Message afterTransformTerminatedSFEmps(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

	//log output payload
	if(payloadLogging.toUpperCase().equals("Y") && messageLog != null){
	    messageLog.addAttachmentAsString("7.3 SF Emps - After Transformation:Terminated", bodyAsString, "text/xml");	
    }    
	return message;
}

//After filter terminated employees payload
def Message terminatedFilterSFEmps(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

	//log output payload
	if(payloadLogging.toUpperCase().equals("Y") && messageLog != null){
	    messageLog.addAttachmentAsString("7.4 SF Emps - After Filter:Terminated", bodyAsString, "text/xml");	
    }    
	return message;
}  

//US & USI Employees data fetched from SF who are future hired employees
//Select employees whose hire date is on Saturday of last week till four weeks from current date from SAP SuccessFactors EC and add the details to combined file.
def Message futureHiredSFEmps(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

	//log output payload
	if(payloadLogging.toUpperCase().equals("Y") && messageLog != null){
	    messageLog.addAttachmentAsString("8.1 SF Emps - FutureHires", bodyAsString, "text/xml");	
    }    
	return message;
} 

//After filter future hired employees payload
def Message futureHiresFilterRecordsSFEmps(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

	//log output payload
	if(payloadLogging.toUpperCase().equals("Y") && messageLog != null){
	    messageLog.addAttachmentAsString("8.2 SF Emps - Filter Valid Records:FutureHires", bodyAsString, "text/xml");	
    }    
	return message;
}

//After filter future hired employees payload
def Message futureHiresTransformationSFEmps(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

	//log output payload
	if(payloadLogging.toUpperCase().equals("Y") && messageLog != null){
	    messageLog.addAttachmentAsString("8.3 SF Emps - After Transformation:FutureHires", bodyAsString, "text/xml");	
    }    
	return message;
}

def Message combinedSFData(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

	//log output payload
	if(payloadLogging.toUpperCase().equals("Y") && messageLog != null){
	    messageLog.addAttachmentAsString("9. SF Emps - after Gather ", bodyAsString, "text/xml");	
    }    
	return message;
} 

def Message enrich_bgIndustry_PMReport(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

	//log output payload
	if(payloadLogging.toUpperCase().equals("Y") && messageLog != null){
	    messageLog.addAttachmentAsString("10. SF Emps - After Enrichment", bodyAsString, "text/xml");	
    }    
	return message;
} 

// PayLoad Attachment -3.After Mapping Payload
def Message afterFinalMsgMap(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

	//log output payload
	if(payloadLogging.toUpperCase().equals("Y") && messageLog != null){
	    messageLog.addAttachmentAsString("11. Final Mapped Payload", bodyAsString, "text/xml");	
    }    
	return message;
} 

def Message finalOutboundPayload(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

	//log output payload
	if(payloadLogging.toUpperCase().equals("Y") && messageLog != null){
	    messageLog.addAttachmentAsString("12. Final Outbound Payload", bodyAsString, "text/xml");	
    }    
	return message;
}



def Message xmlToCsv(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

	//log output payload
	if(payloadLogging.toUpperCase().equals("Y") && messageLog != null){
	    messageLog.addAttachmentAsString("xml converted", bodyAsString, "text/xml");	
    }    
	return message;
}  





 



























// PayLoad Attachment -1.Data Fetched From SF
def Message afterFilter(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
	    messageLog.addAttachmentAsString(" After filter", bodyAsString, "text/xml");	
    }    
	return message;
} 

def Message fetchTerminatedEmps(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
	    messageLog.addAttachmentAsString(" SF Terminated Emps ", bodyAsString, "text/xml");	
    }    
	return message;
} 

def Message futureHires(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
	    messageLog.addAttachmentAsString(" SF Data Future Hires ", bodyAsString, "text/xml");	
    }    
	return message;
} 

def Message futureTerminatedFilter(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
	    messageLog.addAttachmentAsString(" Terminated Filter SF Data ", bodyAsString, "text/xml");	
    }    
	return message;
} 




// PayLoad Attachment -2.After cust_InpendenceProfile Enrichment Payload
def Message enrichedData(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def payloadLogging = map.get("ENABLE_PAYLOAD_LOGGING");

	//log output payload
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
	    messageLog.addAttachmentAsString("2.Independence Enriched Payload", bodyAsString, "text/xml");	
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
	    messageLog.addAttachmentAsString("4.Final Payload", bodyAsString, "text/xml");	
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
	    messageLog.addAttachmentAsString("5.After Map RejectFile Payload ", bodyAsString, "text/xml");	
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
	    messageLog.addAttachmentAsString("6.Reject File Payload", bodyAsString, "text/xml");	
    }    
	return message;
} 


def Message exceptionMessage(Message message) {
    
    def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	    messageLog.addAttachmentAsString("Exception Payload", bodyAsString, "text/xml");	

	return message;
}  