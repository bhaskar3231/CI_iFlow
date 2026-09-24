import com.sap.gateway.ip.core.customdev.util.Message;
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters;


def Message processData(Message message) {
    //Body
    def body         = message.getBody();
    def pMap         = message.getProperties();
    StringBuffer str = new StringBuffer();
    
    //Properties 
    def UserId                = pMap.get("UserId");
    def WorkerClass           = pMap.get("WorkerClass");
    def country               = pMap.get("countryOfCompany");
    def Status                = pMap.get("Status_Term");
    def enablePayloadLogging  = pMap.get("enablePayloadLogging");

    
    def messageLog = messageLogFactory.getMessageLog(message);
    
    def today = LocalDate.now()
    // Dates
    def currentMonthEnd = today.with(TemporalAdjusters.lastDayOfMonth());
    def priorMonthStart = today.minusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
    def dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    def currentMonthEndStr = currentMonthEnd.format(dateFormatter);
    def priorMonthStartStr = priorMonthStart.format(dateFormatter);
    
    
    if(UserId.trim().length()>0){
        UserId = UserId.trim().replace(",","','");
        str.append(" (personIdExternal in '"+UserId+"')");
    
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
    str.append(" and (endDate >= '"+priorMonthStartStr+"') and (endDate <= '"+currentMonthEndStr+"')");
    
    
    //setProperty
    message.setProperty("QueryParam2", str.toString());
    message.setProperty("FromPriorMonth", priorMonthStartStr)
    message.setProperty("ToCurrentMonthEnd", currentMonthEndStr)
    message.setHeader("enablePayloadLogging", enablePayloadLogging)
    
    messageLog.addAttachmentAsString("Terminated Employees Filter", str.toString(), "text/xml");
	    
    return message;
}
