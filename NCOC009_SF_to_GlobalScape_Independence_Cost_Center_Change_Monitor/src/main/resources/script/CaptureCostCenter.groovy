
import groovy.xml.XmlUtil
import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
//Here in this script capturing the CostCenter values which are coming from source message and into a property.So can use in querying time to get required data for the all user's at time
def Message processData(Message message) {
	
   def body = message.getBody(java.lang.String);
   
   def str=""
   //Parsing xml and getting the useID value into string
   def list = new XmlSlurper().parseText(body)
  // def body = message.getBody();
   def pMap = message.getProperties();
   //Properties
    def USER_ID = pMap.get("USER_ID");
   /*
   	if(USER_ID.trim().length()>0){
	//	str.append(" and ");
	    String[] USER_ID_l;
		USER_ID_l = USER_ID.trim().split(',');
		for(int i=0; i<(USER_ID_l.size()-1);i++)
			str.append("userId eq '"+USER_ID_l[i].trim()+"'");
		str.append(",'"+USER_ID_l[USER_ID_l.size()-1].trim()+"'");
	    }
	    	message.setProperty("WhereQuery2", str.toString());*/
  
        list.FOCostCenter.each
        {
		    if("".equals(str))
		    str = str + "jobInfoNav/costCenter in '" + it.externalCode.text() + "'";
	        else
		    str = str + ",'" + it.externalCode.text() + "'";
        }
    //setting  the property
    message.setProperty("AllCostCenter",str);
  
   
   return message;
}
