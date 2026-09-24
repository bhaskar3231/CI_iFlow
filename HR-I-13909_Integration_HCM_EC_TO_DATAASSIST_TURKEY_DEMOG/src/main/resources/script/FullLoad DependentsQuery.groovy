import com.sap.gateway.ip.core.customdev.util.Message;





def Message processData(Message message) {
    //Body
    
    def pMap         = message.getProperties();
    
    StringBuffer str_Dependents = new StringBuffer();
    
    //Properties 
    def UserId                = pMap.get("UserId");
    def WorkerClass           = pMap.get("WorkerClass");
    def country               = pMap.get("countryOfCompany");
    def Status                = pMap.get("Status");
    def enablePayloadLogging  = pMap.get("enablePayloadLogging");
    
	

    
    def messageLog = messageLogFactory.getMessageLog(message);
    
    if(UserId.trim().length()>0){
        UserId = UserId.trim().replace(",","','");
        str_Dependents.append("(personIdExternal in '"+UserId+"')");
    
	if(Status.trim().length()>0){
        Status = Status.trim().replace(",","','");
        str_Dependents.append(" and (jobInfoNav/emplStatusNav/externalCode in '"+Status+"')");
    }
    }
	else
	{
    if(Status.trim().length()>0){
        Status = Status.trim().replace(",","','");
        str_Dependents.append(" (jobInfoNav/emplStatusNav/externalCode in '"+Status+"')");
    }
	}
	
    if(country.trim().length()>0){
        country = country.trim().replace(",","','");
        str_Dependents.append(" and (jobInfoNav/countryOfCompany like '"+country+"')");
    }
    if(WorkerClass.trim().length()>0){
        WorkerClass = WorkerClass.trim().replace(",","','");
        str_Dependents.append(" and not (jobInfoNav/employeeClassNav/externalCode in '"+WorkerClass+"')");
    }
    
  
    str_Dependents.append(" and (hiringNotCompleted eq false)");
    
    
    
    
    //setProperty
    message.setProperty("QueryParam3", str_Dependents.toString());
    
    message.setHeader("enablePayloadLogging", enablePayloadLogging)
    messageLog.addAttachmentAsString("Employees Dependents Filter", str_Dependents.toString(), "text/xml");
	    
    return message;
}

