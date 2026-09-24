/* PURPOSE -- This groovy script is used to store the person ids fetched from DNU, BCLP & Buddy file and store it into the repective property parameters */

import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.xml.XmlUtil;
import groovy.xml.*;
//import org.apache.commons.collections.MultiMap;
//import org.apache.commons.collections.map.MultiValueMap;
//import java.util.Set;


/*Store the incoming xml payload fetched from the DNU List file and store it into "DNU_PersonIds" property paramater*/
def Message storePersonId_DNU(Message message) {
	
//Body	
	def body = message.getBody(java.lang.String) as String;
	//Read the incoming xml payload
	def value_map_xml = new XmlSlurper().parseText(body);

    def personIds=new StringBuffer();
    Set PersonIds_Set=[];

    //Loop over the incoming xml payload 
	value_map_xml.Row.each{
        PersonIds_Set.add(it.Personnel_ID.text());
	}
	if(PersonIds_Set.size() == 0)
           personIds="1";
    else
       personIds=String.join(",", PersonIds_Set);
        
         

	//Set the "Buddy_PersonIds" parameter with the list containing the person ids from the Buddy file
    message.setProperty("DNU_PersonIds",personIds.toString());

    return message;

}


/*Store the incoming xml payload fetched from the BCLP file and store it into "BCLP_PersonIds" property paramater*/
def Message storePersonId_BCLP(Message message) {
	
//Body	
	def body = message.getBody(java.lang.String) as String;
	//Read the incoming xml payload
	def value_map_xml = new XmlSlurper().parseText(body);

    def personIds=new StringBuffer();
    Set PersonIds_Set=[];

    //Loop over the incoming xml payload 
	value_map_xml.Row.each{
        PersonIds_Set.add(it.Personnel_ID.text());
	}
	if(PersonIds_Set.size() == 0)
           personIds="1";
    else
       personIds=String.join(",", PersonIds_Set);
        
         

	//Set the "Buddy_PersonIds" parameter with the list containing the person ids from the Buddy file
    message.setProperty("BCLP_PersonIds",personIds.toString());

    return message;

}

/*Store the incoming xml payload fetched from the Buddy file and store it into "Buddy_PersonIds" property paramater*/
def Message storePersonId_Buddy(Message message) {
	
//Body	
	def body = message.getBody(java.lang.String) as String;
	//Read the incoming xml payload
	def value_map_xml = new XmlSlurper().parseText(body);

    def personIds=new StringBuffer();
    Set PersonIds_Set=[];

    //Loop over the incoming xml payload 
	value_map_xml.Row.each{
        PersonIds_Set.add(it.Personnel_ID.text());
	}
	if(PersonIds_Set.size() == 0)
           personIds="1";
    else
       personIds=String.join(",", PersonIds_Set);
        
         

	//Set the "Buddy_PersonIds" parameter with the list containing the person ids from the Buddy file
    message.setProperty("BUDDY_PERSON_IDS",personIds.toString());

    return message;

}