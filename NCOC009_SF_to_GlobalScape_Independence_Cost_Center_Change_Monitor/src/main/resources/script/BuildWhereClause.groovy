//This Script is use to create the WHERE Clause to filter the data from the SuccessFactor Employee Central.

import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.Calendar;

def Message processData(Message message) {
	//Body	
	def body = message.getBody();
	def pMap = message.getProperties();
	def queryParameter = "";
	StringBuffer str = new StringBuffer();
    StringBuffer filter = new StringBuffer();
	
	
	//Logger
	def messageLog = messageLogFactory.getMessageLog(message);
	
	//Properties
    // def personIdExternal = pMap.get("PERSON_ID_EXTERNAL");
	def LMD_l = pMap.get("ManualExecutionDate");
    def last_run_timestamp = pMap.get("last_run_timestamp_l")
    def country = pMap.get("COUNTRY")
		
	//Get LMD from property and filter query where Last Modified Date is greater than LMDL property
	if(LMD_l.trim().length()>0){
	    LMD = LMD_l.trim() ; 
	 str.append(LMD);
	 }
	 else{
	     str.append(last_run_timestamp);
	 }
	
if(country.trim().length()>0){
		country = country.trim().replace(",", "','");
	    filter.append("(cust_LegalEntity/country in '" + country +"')");
	}

println filter
	//Set Log Attachments
	def payloadLogging = pMap.get("ENABLE_PAYLOAD_LOGGING");
	
	if(payloadLogging.toUpperCase().equals("TRUE")){
	    messageLog.addAttachmentAsString("Where Query", str.toString(), "text/plain");
	}

	
	//Set Headers
	message.setHeader("WhereQuery", str.toString());
    message.setHeader("country", filter.toString());
message.setHeader("QueryParameters", queryParameter);

println str
println queryParameter
	return message;
}