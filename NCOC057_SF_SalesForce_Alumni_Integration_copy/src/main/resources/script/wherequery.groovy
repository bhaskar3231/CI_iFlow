//This Script is use to create the WHERE Clause to filter the data from the SuccessFactor Employee Central.

import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.Calendar;

def Message processData(Message message) {
	//Body	
	def body = message.getBody();
	def pMap = message.getProperties();
    def pMap1 = message.getHeaders();
	def queryParameter = "";
	StringBuffer str = new StringBuffer();
   
	//Logger
	def messageLog = messageLogFactory.getMessageLog(message);

	//Properties
	def fullextract = pMap.get("FULL_EXTRACT");
    def payloadLogging = pMap.get("ENABLE_PAYLOAD_LOGGING");
	def userId = pMap.get("USER_ID");
	def countryOfCompany = pMap.get("LEGAL_ENTITY");
	def lastRunTimestamp_l = pMap.get("LAST_RUN_TIMESTAMP");
    def manual_lastRunTimestamp_l = pMap.get("MANUAL_LAST_RUN_TIMESTAMP");
    def lastRunstamp_l = pMap.get("START_DATE");
    
    //println lastRunstamp_l;
	def flag = 0;
	def flag1 = 0;
	
	
if(manual_lastRunTimestamp_l != "") {
	lastRunTimestamp_l = manual_lastRunTimestamp_l;
}
	
//Include Contingent workers
//str.append(" (employmentNav/isContingentWorker in 'true') and ");
//if(/PerPerson/PerPerson/employmentNav/EmpEmployment/jobInfoNav/EmpJob/emplStatusNav/PicklistOption/externalCode != 'D'){

// Filter on countryOfCompany	
if(countryOfCompany.trim().length()>0){
	    if(flag == 1){
	   str.append(" and ");
	    }
		countryOfCompany = countryOfCompany.trim().replace(",", "','");
	    str.append("(jobInfoNav/countryOfCompany in '"+countryOfCompany+"')");
	    flag = 1;
     }
    
//Add for delta extract parameter check
if(fullextract=="FALSE"){

    if(userId == ""){
    userId="00000"
    }

    if(userId.trim().length()>0 && userId != "00000"){
	   if(flag == 1){
	    str.append(" and ");
	    }
		userId = userId.trim().replace(",", "','");
		 str.append("(personIdExternal in '" + userId + "' ) and (lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or personNav/emailNav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or jobInfoNav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or personNav/homeAddressNavDEFLT/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or personNav/nationalIdNav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or personNav/personalInfoNav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or personNav/phoneNav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or  personNav/userAccountNav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or startDate eq datetimeoffset'" + lastRunstamp_l.trim() + "' or jobInfoNav/startDate eq datetimeoffset'" + lastRunstamp_l.trim() + "' or personNav/homeAddressNavDEFLT/startDate eq datetimeoffset'" + lastRunstamp_l.trim() + "' or personNav/personalInfoNav/startDate eq datetimeoffset'" + lastRunstamp_l.trim() + "')");

       
	    flag =1;
	    flag1=1;
	}


	if(flag1 == 0){
        if(flag == 1){
	   str.append(" and ");
	    }
 		//str.append("(lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "')");
 		str.append("(lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or personNav/emailNav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or jobInfoNav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or personNav/homeAddressNavDEFLT/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or personNav/nationalIdNav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or personNav/personalInfoNav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or personNav/phoneNav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or personNav/userAccountNav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or startDate eq datetimeoffset'" + lastRunstamp_l.trim() + "' or jobInfoNav/startDate eq datetimeoffset'" + lastRunstamp_l.trim() + "' or personNav/homeAddressNavDEFLT/startDate eq datetimeoffset'" + lastRunstamp_l.trim() + "' or personNav/personalInfoNav/startDate eq datetimeoffset'" + lastRunstamp_l.trim() + "' )");
 	}
 	
 	
}

//Add for full extract parameter check
if(fullextract=="TRUE"){
//Add user id parameter check
	if(userId.trim().length()>0){
	   if(flag == 1){
	    str.append(" and ");
	    }
		userId = userId.trim().replace(",", "','");
	    str.append("(personIdExternal in '"+userId+"')");
	    flag =1;
	}
}

println str;
	
	if(payloadLogging.toUpperCase().equals("TRUE") && messageLog != null){
	    messageLog.addAttachmentAsString("1. Where Query", str.toString(), "text/plain");
	    //messageLog.addAttachmentAsString("5. datetet", lastRunstamp_l.toString(), "text/plain");
	}

	message.setHeader("WhereQuery", str.toString());
    //message.setHeader("QueryParameters", queryParameter);
	return message;
}
//}