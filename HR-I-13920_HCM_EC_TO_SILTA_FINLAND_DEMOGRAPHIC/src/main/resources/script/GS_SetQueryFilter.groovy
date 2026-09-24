import com.sap.gateway.ip.core.customdev.util.Message;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.format.DateTimeFormatter;




def Message processData(Message message) {
    //Body
    def pMap         = message.getProperties();
    StringBuffer str = new StringBuffer();
    
    //Properties 
    def UserId                = pMap.get("UserId");
    def WorkerClass           = pMap.get("WorkerClass");
    def country               = pMap.get("countryOfCompany");
    def Status                = pMap.get("Status");
    def enablePayloadLogging  = pMap.get("enablePayloadLogging");

    
    def messageLog = messageLogFactory.getMessageLog(message);
    def today = LocalDate.now();
    def currentMonthEnd = today.with(TemporalAdjusters.lastDayOfMonth());
    def dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    def currentMonthEndStr = currentMonthEnd.format(dateFormatter);
    
    
    if(UserId.trim().length()>0){
        UserId = UserId.trim().replace(",","','");
        str.append(" (assignmentIdExternal in '"+UserId+"')");
    
	if(Status.trim().length()>0){
        Status = Status.trim().replace(",","','");
        str.append(" and (jobInfoNav/emplStatusNav/externalCode in '"+Status+"')");
    }
    }
	else
	{
    if(Status.trim().length()>0){
        Status = Status.trim().replace(",","','");
        str.append(" (jobInfoNav/emplStatusNav/externalCode in '"+Status+"')");
    }
	}
	
    if(country.trim().length()>0){
        country = country.trim().replace(",","','");
        str.append(" and (jobInfoNav/countryOfCompany like '"+country+"')");
    }
    if(WorkerClass.trim().length()>0){
        WorkerClass = WorkerClass.trim().replace(",","','");
        str.append(" and not (jobInfoNav/employeeClassNav/externalCode in '"+WorkerClass+"')");
    }
    
    str.append(" and (hiringNotCompleted eq false)");
    
    //setProperty
    message.setProperty("QueryParam1", str.toString());
    message.setProperty("CurrentMonthEndDate", currentMonthEndStr)
    message.setHeader("enablePayloadLogging", enablePayloadLogging)
    
    messageLog.addAttachmentAsString("Active Employees Filter", str.toString(), "text/xml");
	    
    return message;
}













































/*import com.sap.gateway.ip.core.customdev.util.Message;
def Message processData(Message message) {
    //Body
    def body         = message.getBody();
    def pMap         = message.getProperties();
    StringBuffer str = new StringBuffer();
    
    //Properties 
    def UserId                = pMap.get("UserId");
    def WorkerClass           = pMap.get("WorkerClass");
    def country               = pMap.get("countryOfCompany");
    def Status                = pMap.get("Status");
    def enablePayloadLogging  = pMap.get("enablePayloadLogging");

    
    def messageLog = messageLogFactory.getMessageLog(message);
    
    
    if(UserId.trim().length()>0){
        UserId = UserId.trim().replace(",","','");
        str.append(" (userId in '"+UserId+"')");
    
	if(Status.trim().length()>0){
        Status = Status.trim().replace(",","','");
        str.append(" and (jobInfoNav/emplStatusNav/externalCode in '"+Status+"')");
    }
    }
	else
	{
    if(Status.trim().length()>0){
        Status = Status.trim().replace(",","','");
        str.append(" (jobInfoNav/emplStatusNav/externalCode in '"+Status+"')");
    }
	}
	
    if(country.trim().length()>0){
        country = country.trim().replace(",","%25' or jobInfoNav/countryOfCompany like '");
        str.append(" and (jobInfoNav/countryOfCompany like '"+country+"%25')");
    }
    if(WorkerClass.trim().length()>0){
        WorkerClass = WorkerClass.trim().replace(",","','");
        str.append(" and not (jobInfoNav/employeeClassNav/externalCode in '"+WorkerClass+"')");
    }
    
    //setProperty
    message.setProperty("QueryParam1", str.toString());
    message.setHeader("enablePayloadLogging", enablePayloadLogging)
    
    messageLog.addAttachmentAsString("Active Employees Filter", str.toString(), "text/xml");
	    
    return message;
}*/
































































































































