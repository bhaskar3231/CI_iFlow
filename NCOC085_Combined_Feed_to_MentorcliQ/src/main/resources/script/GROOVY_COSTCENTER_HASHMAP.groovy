/* 
---------------------------------------------------------------
   PURPOSE -- This groovy script creates Hshmap for CostCenter Information
*/

import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;

def Message processData(Message message) {
	//Body	
	def body = message.getBody(java.lang.String) as String;
	def get_properties = message.getProperties();

	
	def costCenter_xml = new XmlSlurper().parseText(body);
	
	Map<String, List<String>> FO_CostCenter_HashMap = new HashMap<String, List<String>>();
	
    costCenter_xml.FOCostCenter.each{
	    
	    List<String> costcenter_values = new ArrayList<String>();
	    
	    costcenter_values.add(it.name.text());
	    costcenter_values.add(it.cust_Business.text());
	    costcenter_values.add(it.cust_BusinessArea.text());
	    costcenter_values.add(it.cust_BusinessLine.text());
	    costcenter_values.add(it.cust_Region.text());
	    costcenter_values.add(it.cust_ZZOFFICE.text());
	    costcenter_values.add(it.cust_BusinessSubLine.text());
	 
	    FO_CostCenter_HashMap.put(it.externalCode.text(),costcenter_values);
	    
	    
	}
	
	/* Set the HashMaps created as properties to be used in mapping*/
	message.setProperty("FO_CostCenter_HashMap",FO_CostCenter_HashMap);

		
    return message;
    
}