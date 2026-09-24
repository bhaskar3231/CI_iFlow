import com.sap.gateway.ip.core.customdev.util.Message;





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
    
	def lastRunDateTime = pMap.get("NewHirelastRunDateTime");
	def lastRunDateTimeManual = pMap.get("lastRunDateTimeManual");
	//def FutureDateTime = pMap.get("FutureDateTime");
	

    
    def messageLog = messageLogFactory.getMessageLog(message);
    
    if(UserId.trim().length()>0){
        UserId = UserId.trim().replace(",","','");
        str.append("(personIdExternal in '"+UserId+"')");
    
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
    
    //str.append(" and (lastModifiedDateTime lt datetime'" + lastRunDateTime + "')");
    //str.append(" and (lastModifiedDateTime ge datetime'" + lastRunDateTimeManual + "')");
    //str.append(" and (lastModifiedDateTime lt datetime'" + CurrentDateTime + "')");
    
    if(lastRunDateTimeManual != ""){
        //str.append(" and (lastModifiedDateTime ge datetime'" + lastRunDateTimeManual + "')");
        str.append("&fromDate="+lastRunDateTimeManual.substring(0, 10));
        
    }
    else{
       // str.append(" and (lastModifiedDateTime ge datetime'" + lastRunDateTime + "')");
       str.append("&fromDate="+lastRunDateTime.substring(0, 10));
        
    }

    
    
    //setProperty
    message.setProperty("QueryParam1", str.toString());
    
    message.setHeader("enablePayloadLogging", enablePayloadLogging)
    messageLog.addAttachmentAsString("Employees Filter", str.toString(), "text/xml");
	    
    return message;
}














