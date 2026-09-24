import com.sap.gateway.ip.core.customdev.util.Message;

def Message processData(Message message) {
    //Body
   
    def pMap         = message.getProperties();
    StringBuffer str = new StringBuffer();
    
    //Properties
    def lastRunDate = ""
    def query = ""
    def UserId                = pMap.get("UserId");
    def enablePayloadLogging  = pMap.get("enablePayloadLogging");
	def currentDateQuery      = pMap.get("currentDateQuery");
	def lastRunDateTime       = pMap.get("EmpChanglastRunDateTime");
	def lastRunDateTimeManual = pMap.get("lastRunDateTimeManual")
    def toDateManual = pMap.get("toDate")
    
    def messageLog = messageLogFactory.getMessageLog(message);
    
    if(lastRunDateTimeManual.length()>10){
        lastRunDate = lastRunDateTimeManual.substring(0, 10);
    }
    else{
	    if(lastRunDateTime.length()>10){
		    lastRunDate = lastRunDateTime.substring(0, 10);
	    }
    }
    
	//Build Where Query
	if(lastRunDateTimeManual.trim().length()>0){
	    str.append("(last_modified_on > to_datetime('" + lastRunDateTimeManual.trim()  + "')");     
	}
	else{
	    if(lastRunDateTime.trim().length()>0){
		    str.append("(last_modified_on > to_datetime('" + lastRunDateTime.trim()  + "')");
	    }
	}
    
    if(UserId.length()>0){
        UserId = UserId.trim().replace(",","','");
        str.append(" AND person_id_external IN ('" + UserId +"')");
    }
    
    str.append(" and fromDate = to_date('"+lastRunDate+"','YYYY-MM-DD')");
	//str.append(" and toDate = to_date('"+currentDateQuery+"','YYYY-MM-DD'))");
	str.append(" and toDate = to_date('"+toDateManual+"','YYYY-MM-DD'))");
	
	
	
	//Build Query Parameters
	if(lastRunDate==currentDateQuery){
        query = "queryMode=PeriodDelta;resultOptions=renderPreviousTags,isNotFirstQuery";
    } else {
        query = "queryMode=PeriodDelta;resultOptions=renderPreviousTags";
    }    
	
    //setProperty
    message.setProperty("QueryParam", str.toString());
    message.setProperty("Parameters", query);
    message.setHeader("enablePayloadLogging", enablePayloadLogging);
    
    messageLog.addAttachmentAsString("Delta Filters", str.toString(), "text/xml");
	    
    return message;
}





















































/*import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
def Message processData(Message message) {
    //Body
    def body         = message.getBody();
    def pMap         = message.getProperties();
    StringBuffer str = new StringBuffer();
    
    //Properties 
    def UserId                = pMap.get("UserId");
    def enablePayloadLogging  = pMap.get("enablePayloadLogging");
    def fromDate              = pMap.get("fromDate");
	def currentDateQuery      = pMap.get("currentDateQuery");
	def lastRunDateTime       = pMap.get("lastRunDateTime");
	def lastRunDateTimeManual = pMap.get("lastRunDateTimeManual")
	def WorkerClass           = pMap.get("WorkerClass");
    def country               = pMap.get("countryOfCompany");
    def Status                = pMap.get("Status");
    
    def messageLog = messageLogFactory.getMessageLog(message);
    
    if(lastRunDateTimeManual.length()>10){
        lastRunDate = lastRunDateTimeManual.substring(0, 10);
    }
    else{
	    if(lastRunDateTime.length()>10){
		    lastRunDate = lastRunDateTime.substring(0, 10);
	    }
    }
    
	//Build Where Query
	if(lastRunDateTimeManual.trim().length()>0){
	    str.append("(last_modified_on > to_datetime('" + lastRunDateTimeManual.trim()  + "')");     
	}
	else{
	    if(lastRunDateTime.trim().length()>0){
		    str.append("(last_modified_on > to_datetime('" + lastRunDateTime.trim()  + "')");
	    }
	}
    
    if(UserId.length()>0){
        UserId = UserId.trim().replace(",","','");
        str.append(" AND person_id_external IN ('" + UserId +"'))");
    
    	if(Status.trim().length()>0){
        Status = Status.trim().replace(",","','");
        str.append(" AND job_information/emplStatus IN ('"+Status+"')");
    }
    }
	else
	{
    if(Status.trim().length()>0){
        Status = Status.trim().replace(",","','");
        str.append(" job_information/emplStatus IN ('"+Status+"')");
    }
	}
	
    if(country.trim().length()>0){
        country = country.trim().replace(",","','");
        str.append(" AND job_information/company_territory_code IN ('"+country+"')");
    }
    
    if(WorkerClass.trim().length()>0){
        WorkerClass = WorkerClass.trim().replace(",","','");
        str.append(" AND NOT job_information/employee_class IN ('"+WorkerClass+"')");
    }
    
    str.append(" and fromDate = to_date('"+lastRunDate+"','YYYY-MM-DD')");
	str.append(" and toDate = to_date('"+currentDateQuery+"','YYYY-MM-DD')");
	
	//Build Query Parameters
	if(lastRunDate==currentDateQuery){
        query = "queryMode=PeriodDelta;resultOptions=renderPreviousTags,isNotFirstQuery";
    } else {
        query = "queryMode=PeriodDelta;resultOptions=renderPreviousTags";
    }    
	
    //setProperty
    message.setProperty("QueryParam", str.toString());
    message.setProperty("Parameters", query);
    message.setHeader("enablePayloadLogging", enablePayloadLogging);
    
    messageLog.addAttachmentAsString("Delta Filters", str.toString(), "text/xml");
	    
    return message;
}



*/





















