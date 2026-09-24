/* Refer the link below to learn more about the use cases of script.
https://help.sap.com/viewer/368c481cd6954bdfa5d0435479fd4eaf/Cloud/en-US/148851bf8192412cba1f9d2c17f4bd25.html

If you want to know more about the SCRIPT APIs, refer the link below
https://help.sap.com/doc/a56f52e1a58e4e2bac7f7adbf45b2e26/Cloud/en-US/index.html */
import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.xml.XmlUtil;
import groovy.xml.*;

//This function fetches certain fields from Industry portlet from SuccessFactors for all employees and stores it into hashmap for enrichment later on. 
def Message storeData_hashmap_personnel_type(Message message) {

    //Body
    def body = message.getBody(java.lang.String) as String;
    
    //Read the incoming xml payload from the Industry portlet
	def value_map_xml = new XmlSlurper().parseText(body);

        Map<String, List<String>> personnel_type = new HashMap<String, List<String>>();
        
        //Iterate over each row of the incoming xml payload
    	value_map_xml.PickListValueV2.each{
        
        //Define a list to store the field values from industry xml payload
        List<String> values = new ArrayList<String>();
        
def pers_key = it.externalCode.text() + it.PickListV2_effectiveStartDate.text() ;

        //Add the field values to the list
        values.add(it.PickListV2_effectiveStartDate.text());
        values.add(it.label_defaultValue.text());
        values.add(it.status.text());
        values.add(it.lastModifiedDateTime.text());
        values.add(it.externalCode.text())
    
        //Corresponding to each userID in the incoming payload, assign the respective list containing the field and append it in the hashmap
		personnel_type.put(pers_key,values);
		//for apache library

	}
    
    //Assign the hashmap to be stored in the property paramater "multiMap_bgIndustry"
	 message.setProperty("personnel_type",personnel_type); 
    
    return message;

} //End function

/*------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


