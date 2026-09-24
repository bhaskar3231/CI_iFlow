import com.sap.gateway.ip.core.customdev.util.Message;
def Message processData(Message message) {
    def body = message.getBody(java.lang.String) as String;
    def xml = new XmlSlurper().parseText(body);
    def map=message.getProperties();
    def users=map.get("USER_ID");
     def sb=new StringBuffer();
     def all_codes="";
    if(!users.equals("")){
        users=users.trim();
        String[] usersList=users.split(",",-1);
        for(String user in usersList)
           sb=sb.append("'").append(user).append("',");
         all_codes="userId in "+sb.substring(0,sb.length()-1);
        // all_codes=all_codes.append(" and isContingentWorker eq 'false'")
     }
    else{
            int i=0;
            xml.FOCostCenter.each{
                i=i+1;
                sb=sb.append("'").append(it.externalCode.text()).append("',");
            }
            if(i > 0)
                all_codes="jobInfoNav/costCenter in "+sb.substring(0,sb.length()-1);
               // all_codes=all_codes.append(" and isContingentWorker eq 'false'")
    }
    
    all_codes=all_codes+" and jobInfoNav/emplStatusNav/externalCode eq 'A' and isContingentWorker eq false"
     message.setProperty("EMPLOYMENT_FILTER",all_codes);
     println all_codes;
     return message;
}
