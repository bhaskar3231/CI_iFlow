
import groovy.xml.XmlUtil
import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
//Here in this script capturing the personIdExternal values which are coming from source message and into a property.So can use in querying time to get required data for the all user's at time
def Message processData(Message message) {
	
   def body = message.getBody(java.lang.String);
   
   def str=""
   //Parsing xml and getting the useID value into string
   def list = new XmlSlurper().parseText(body)
   
   list.EmpEmployment.each
   {
		if("".equals(str))
		   str = str + "personIdExternal eq '" + it.personIdExternal.text() + "'";
	   else
		   str = str + " or personIdExternal eq '" + it.personIdExternal.text() + "'";
		
   }
   
   //setting  the property
   message.setProperty("AllpersonIdExternal",str); 
   
   
   return message;
}
