import com.sap.gateway.ip.core.customdev.util.Message;





def Message processData(Message message) {
    //Body

    def pMap         = message.getProperties();
    
    StringBuffer str_term = new StringBuffer();
    
    //Properties 
    def UserId                = pMap.get("UserId");
    def WorkerClass           = pMap.get("WorkerClass");
    def country               = pMap.get("countryOfCompany");
    def Status_Term           = pMap.get("Status_Term");
    def enablePayloadLogging  = pMap.get("enablePayloadLogging");
    

	

    
    def messageLog = messageLogFactory.getMessageLog(message);
    
    if(UserId.trim().length()>0){
        UserId = UserId.trim().replace(",","','");
        str_term.append("(personIdExternal in '"+UserId+"')");
    
    if(Status_Term.trim().length()>0){
        Status_Term = Status_Term.trim().replace(",","','");
        str_term.append(" and (jobInfoNav/emplStatusNav/externalCode in '"+Status_Term+"')");
    }
    }
	else
	{
    if(Status_Term.trim().length()>0){
        Status_Term = Status_Term.trim().replace(",","','");
        str_term.append("(jobInfoNav/emplStatusNav/externalCode in '"+Status_Term+"')");
    }
	}
	
    if(country.trim().length()>0){
        country = country.trim().replace(",","','");
        str_term.append(" and (jobInfoNav/countryOfCompany like '"+country+"')");
    }
    if(WorkerClass.trim().length()>0){
        WorkerClass = WorkerClass.trim().replace(",","','");
        str_term.append(" and not (jobInfoNav/employeeClassNav/externalCode in '"+WorkerClass+"')");
    }
    
    

    
    //setProperty
    message.setProperty("QueryParam2", str_term.toString());
    message.setHeader("enablePayloadLogging", enablePayloadLogging)
    messageLog.addAttachmentAsString("Employees Termination Filter", str_term.toString(), "text/xml");
	    
    return message;
}

