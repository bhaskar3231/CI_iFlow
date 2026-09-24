import com.sap.gateway.ip.core.customdev.util.Message;

def Message processData(Message message) {
    //Body
    
    def pMap         = message.getProperties();
    StringBuffer str_full = new StringBuffer();
    
    //Properties 
    def personIDExternal      = pMap.get("UserId");
    def enablePayloadLogging  = pMap.get("enablePayloadLogging");
    
    def messageLog = messageLogFactory.getMessageLog(message);
    
    
    if(personIDExternal.length()>0){
        personIDExternal = personIDExternal.trim().replace(",","','");
        str_full.append("AND person_id_external IN ('" + personIDExternal +"')");
    }  
    
	
    //setProperty
    message.setProperty("QueryParam_Full", str_full.toString());
    message.setHeader("enablePayloadLogging", enablePayloadLogging);
    
    messageLog.addAttachmentAsString("Full File Filters", str_full.toString(), "text/xml");
	    
    return message;
}
