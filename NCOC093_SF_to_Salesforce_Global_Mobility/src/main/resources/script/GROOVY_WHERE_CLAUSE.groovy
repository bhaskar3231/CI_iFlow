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
	def fullextract = pMap.get("FULL_LOAD");
    def payloadLogging = pMap.get("ENABLE_PAYLOAD_LOGGING");
	def userId = pMap.get("PERSON_ID_EXTERNAL");
	def countryOfCompany = pMap.get("COMPANY");
	def lastRunTimestamp_l = pMap.get("LAST_RUN_TIMESTAMP");
    def manual_lastRunTimestamp_l = pMap.get("MANUAL_LAST_RUN_TIMESTAMP");
    def lastRunstamp_l = pMap.get("CURRENT_DATE");
    def emplstatus = pMap.get("EMPL_STATUS");
    
    //println lastRunstamp_l;
	def flag = 0;
	def flag1 = 0;
	
	
if(manual_lastRunTimestamp_l != "") {
	lastRunTimestamp_l = manual_lastRunTimestamp_l;
}
	
//Exclude Contingent workers
str.append("(isContingentWorker in 'false') and ");

// Filter on emplstatus	
if(emplstatus.trim().length()>0){
	    if(flag == 1){
	   str.append(" and ");
	    }
		emplstatus = emplstatus.trim().replace(",", "','");
	    //str.append("(employmentNav/jobInfoNav/countryOfCompany in '"+countryOfCompany+"')");
	    str.append("(jobInfoNav/emplStatusNav/externalCode in '"+emplstatus+"')");
	    flag = 1;
     }

// Filter on countryOfCompany	
if(countryOfCompany.trim().length()>0){
	    if(flag == 1){
	   str.append(" and ");
	    }
		countryOfCompany = countryOfCompany.trim().replace(",", "','");
	    //str.append("(employmentNav/jobInfoNav/countryOfCompany in '"+countryOfCompany+"')");
	    str.append("(jobInfoNav/countryOfCompany in '"+countryOfCompany+"')");
	    flag = 1;
     }
    
//Add for delta extract parameter check
if(fullextract=="N"){

    if(userId == ""){
    userId="00000"
    }

    if(userId.trim().length()>0 && userId != "00000"){
	   if(flag == 1){
	    str.append(" and ");
	    }
		userId = userId.trim().replace(",", "','");
		 str.append("(personIdExternal in '" + userId + "' ) and (lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or  jobInfoNav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or jobInfoNav/companyNav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or jobInfoNav/costCenterNav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or jobInfoNav/customString10Nav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or jobInfoNav/customString13Nav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or jobInfoNav/customString14Nav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or jobInfoNav/customString3Nav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or jobInfoNav/customString4Nav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or jobInfoNav/customString9Nav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or jobInfoNav/departmentNav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or jobInfoNav/locationNav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or jobInfoNav/locationNav/addressNavDEFLT/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or personNav/homeAddressNavDEFLT/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or personNav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or personNav/emailNav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or personNav/nationalIdNav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or personNav/phoneNav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or personNav/personalInfoNav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or personNav/personalInfoNav/localNavIND/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or userNav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or empWorkPermitNav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or empJobRelationshipNav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or startDate eq datetimeoffset'" + lastRunstamp_l.trim() + "' or jobInfoNav/startDate eq datetimeoffset'" + lastRunstamp_l.trim() + "' or personNav/homeAddressNavDEFLT/startDate eq datetimeoffset'" + lastRunstamp_l.trim() + "' or personNav/personalInfoNav/startDate eq datetimeoffset'" + lastRunstamp_l.trim() + "' )");

       
	    flag =1;
	    flag1=1;
	}


	if(flag1 == 0){
        if(flag == 1){
	   str.append(" and ");
	    }
 		//str.append("(lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "')");
 		str.append("(lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or  jobInfoNav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or jobInfoNav/companyNav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or jobInfoNav/costCenterNav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or jobInfoNav/customString10Nav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or jobInfoNav/customString13Nav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or jobInfoNav/customString14Nav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or jobInfoNav/customString3Nav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or jobInfoNav/customString4Nav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or jobInfoNav/customString9Nav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or jobInfoNav/departmentNav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or jobInfoNav/locationNav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or jobInfoNav/locationNav/addressNavDEFLT/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or personNav/homeAddressNavDEFLT/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or personNav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or personNav/emailNav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or personNav/nationalIdNav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or personNav/phoneNav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or personNav/personalInfoNav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or personNav/personalInfoNav/localNavIND/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or userNav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or empWorkPermitNav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or empJobRelationshipNav/lastModifiedDateTime ge datetimeoffset'" + lastRunTimestamp_l.trim() + "' or startDate eq datetimeoffset'" + lastRunstamp_l.trim() + "' or jobInfoNav/startDate eq datetimeoffset'" + lastRunstamp_l.trim() + "' or personNav/homeAddressNavDEFLT/startDate eq datetimeoffset'" + lastRunstamp_l.trim() + "' or personNav/personalInfoNav/startDate eq datetimeoffset'" + lastRunstamp_l.trim() + "' )");
 	}
 	
 	
}

//Add for full extract parameter check
if(fullextract=="Y"){
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
message.setProperty("WhereQuery", str.toString());
    //message.setHeader("QueryParameters", queryParameter);
	return message;
}
//}