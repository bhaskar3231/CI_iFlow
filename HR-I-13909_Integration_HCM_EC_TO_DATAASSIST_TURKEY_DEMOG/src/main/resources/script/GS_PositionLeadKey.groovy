import com.sap.gateway.ip.core.customdev.util.Message;

def Message processData(Message message) {
    //Body 
    def body = message.getBody(java.io.Reader);
    
    //Parse the key from Original message
    def response = new XmlSlurper().parse(body);
    def keys = response.'**'.findAll{ node-> node.name() == 'position' }*.text();
    
    //Formulate the filter query. Eg: 	$filter=externalCode in '1710-2018'
    def leadKey = "'" + keys.unique().join("','") + "'";
    if(leadKey == null || leadKey.trim().isEmpty()){
        message.setProperty("leadKeyPosition", "");
    } else{
        message.setProperty("leadKeyPosition", "\$filter=code in " + leadKey);
    }
    return message;
}