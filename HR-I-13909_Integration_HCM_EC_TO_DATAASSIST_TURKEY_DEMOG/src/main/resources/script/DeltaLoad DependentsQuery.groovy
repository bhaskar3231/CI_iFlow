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
    
	def lastRunDateTime = pMap.get("DependentslastRunDateTime");
	def toDateOdata = pMap.get("toDateOdata");
	def lastRunDateTimeManual = pMap.get("lastRunDateTimeManual");
	

    
    def messageLog = messageLogFactory.getMessageLog(message);
    
    if(UserId.trim().length()>0){
        UserId = UserId.trim().replace(",","','");
        str_Dependents.append("((personIdExternal in '"+UserId+"')");
    
	if(Status.trim().length()>0){
        Status = Status.trim().replace(",","','");
        str_Dependents.append(" and (jobInfoNav/emplStatusNav/externalCode in '"+Status+"')");
    }
    }
	else
	{
    if(Status.trim().length()>0){
        Status = Status.trim().replace(",","','");
        str_Dependents.append("((jobInfoNav/emplStatusNav/externalCode in '"+Status+"')");
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
    
    str_Dependents.append(" and (hiringNotCompleted eq false))");
    
    //str_Dependents.append(" and (lastModifiedDateTime ge datetime'" + lastRunDateTime + "')");
    //str_Dependents.append(" and (lastModifiedDateTime ge datetime'" + lastRunDateTimeManual + "')");
    //str_Dependents.append(" and (lastModifiedDateTime lt datetime'" + CurrentDateTime + "')");
    
    if(lastRunDateTimeManual != ""){
        str_Dependents.append(" and (((personNav/personRerlationshipNav/lastModifiedDateTime ge datetime'" + lastRunDateTimeManual + "')");
        str_Dependents.append(" and (personNav/personRerlationshipNav/lastModifiedDateTime le datetime'" + toDateOdata + "'))");
        //str_Dependents.append("&fromDate="+lastRunDateTimeManual.substring(0, 10));
    }
    else{
       str_Dependents.append(" and (((personNav/personRerlationshipNav/lastModifiedDateTime ge datetime'" + lastRunDateTime + "')");
       str_Dependents.append(" and (personNav/personRerlationshipNav/lastModifiedDateTime le datetime'" + toDateOdata + "'))");
       //str_Dependents.append("&fromDate="+lastRunDateTime.substring(0, 10));
        
    }
    
    if(lastRunDateTimeManual != ""){
        str_Dependents.append(" or ((personNav/personalInfoNav/localNavTUR/lastModifiedDateTime ge datetime'" + lastRunDateTimeManual + "')");
        str_Dependents.append(" and (personNav/personalInfoNav/localNavTUR/lastModifiedDateTime le datetime'" + toDateOdata + "')))");
        //str_Dependents.append("&fromDate="+lastRunDateTimeManual.substring(0, 10));
    }
    else{
       str_Dependents.append(" or ((personNav/personalInfoNav/localNavTUR/lastModifiedDateTime ge datetime'" + lastRunDateTime + "')");
       str_Dependents.append(" and (personNav/personalInfoNav/localNavTUR/lastModifiedDateTime le datetime'" + toDateOdata + "')))");
       //str_Dependents.append("&fromDate="+lastRunDateTime.substring(0, 10));
        
    }
    
    
    
    
    //setProperty
    message.setProperty("QueryParam3", str_Dependents.toString());
    
    message.setHeader("enablePayloadLogging", enablePayloadLogging)
    messageLog.addAttachmentAsString("Employees Dependents Filter", str_Dependents.toString(), "text/xml");
	    
    return message;
}

