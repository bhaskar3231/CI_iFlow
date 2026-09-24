/*This Groovy script is used for storing field values corresponding to respective user ids in a hashmap*/
//1.Fetches certain fields from Background Industry portlet from SuccessFactors for all employees and stores it into hashmap for enrichment. 
//2.In case of PMReports inbound file, it fetches the userid of employee and the respective fields, i.e., Milestone, YAL, CLuster Information & Skill Category fields, and stores in a hashmap for enrichment of the fields for the required employees fetched from SuccessFactors*/

import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.xml.XmlUtil;
import groovy.xml.*;
//import org.apache.commons.collections.MultiMap;
//import org.apache.commons.collections.map.MultiValueMap;
//import java.util.Set;

//This function fetches certain fields from Background Industry portlet from SuccessFactors for all employees and stores it into hashmap for enrichment later on. 
def Message storeData_hashmapBgIndustry(Message message) {

    //Body
    def body = message.getBody(java.lang.String) as String;
    //Read the incoming xml payload from the Background Industry portlet
	def value_map_xml = new XmlSlurper().parseText(body);

  
        Map<String, List<String>> multiMap_bgIndustry = new HashMap<String, List<String>>();
        multiMap_bgIndustry.put("Dummy","Dummy");
        
        //Iterate over each row of the incoming xml payload
    	value_map_xml.Background_Industry.each{
        
        //Define a list to store the field values from background industry xml payload
        List<String> values = new ArrayList<String>();
   /*   values.add(it.Industry.text());
        values.add(it.Sector.text());
        values.add(it.Industry_2nd.text());
        values.add(it.Sector_2nd.text());*/
        
        //Add the field values to the list
        values.add(it.IndustryNav.PicklistOption.localeLabel.text());
        values.add(it.SectorNav.PicklistOption.localeLabel.text());
        values.add(it.Industry_2ndNav.PicklistOption.localeLabel.text());
        values.add(it.Sector_2ndNav.PicklistOption.localeLabel.text())
    
    
        //Corresponding to each userID in the incoming payload, assign the respective list containing the field and append it in the hashmap
		multiMap_bgIndustry.put(it.userId.text(),values);
		//for apache library
	/*	multiMap.put(it.userId.text(),it.Industry.text());
		multiMap.put(it.userId.text(),it.Sector.text());
		multiMap.put(it.userId.text(),it.Industry_2nd.text());
		multiMap.put(it.userId.text(),it.Sector_2nd.text());*/
	}
    
    //Assign the hashmap to be stored in the property paramater "multiMap_bgIndustry"
	 message.setProperty("multiMap_bgIndustry",multiMap_bgIndustry); 
    

    return message;

} //End function

/*------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

//Process TIL SFTP File
/*In case of TIL inbound file, it fetches the person id external of employee and the TIME_IN_LEVEL_COMMON field, and stores in a hashmap for enrichment of the fields for the required employees fetched from SuccessFactors*/
def Message storeData_hashmapTILFile(Message message) {

    //Body
    def body = message.getBody(java.lang.String) as String;
    //Read the incoming xml payload of th PMReport File
	def value_map_xml = new XmlSlurper().parseText(body);
	
	//Define a hashmap which stores TIME_IN_LEVEL_COMMON field values corresonding to each key, i.e. person id external  
	Map<String, List<String>> multiMap_TIL = new HashMap<String, List<String>>();
        multiMap_TIL.put("Dummy","Dummy");
        
        //Iterate over each row of the incoming xml payload
        value_map_xml.Row.each{
        
        //Define a list to store the TIME_IN_LEVEL_COMMON field value from the xml payload
	    List<String> values = new ArrayList<String>();
	    
	    //Add the field values to the list
        values.add(it.TIME_IN_LEVEL_COMMON.text());
        
        //Corresponding to each person id external in the incoming payload, assign the respective list containing the field and append it in the hashmap 
		multiMap_TIL.put(it.PERSONID.text(),values);
		
	}
    
    //Assign the hashmap to be stored in the property paramater "multiMap_TIL"
	 message.setProperty("multiMap_TIL",multiMap_TIL); 
    
    return message;

} //End Function

/*------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

//Process PM_Reports SFTP File
/*In case of PMReports inbound file, it fetches the userid of employee and the respective fields, i.e., Milestone, YAL, CLuster Information & Skill Category fields, and stores in a hashmap for enrichment of the fields for the required employees fetched from SuccessFactors*/
def Message storeData_hashmapPMReportFile(Message message) {

    //Body
    def body = message.getBody(java.lang.String) as String;
    //Read the incoming xml payload of th PMReport File
	def value_map_xml = new XmlSlurper().parseText(body);
	
	//Define a hashmap which stores multiple values corresonding to each key, i.e. for each userID, it stores four field values  
	Map<String, List<String>> multiMap_PMReport = new HashMap<String, List<String>>();
        multiMap_PMReport.put("Dummy","Dummy");
        
        //Iterate over each row of the incoming xml payload
        value_map_xml.Row.each{
        
        //Define a list to store the fiel values from the xml payload, i,e., Milestone, YAL, CLuster Information & Skill Category field values
	    List<String> values = new ArrayList<String>();
	    
	    //Add the field values to the list
        values.add(it.Milestone.text());
        values.add(it.YAL.text());
        values.add(it.ClusterInformation.text());
        values.add(it.SkillCategory.text())
        
        //Corresponding to each userID in the incoming payload, assign the respective list containing the field and append it in the hashmap 
		multiMap_PMReport.put(it.PersonnelID.text(),values);
		
	}
    
    //Assign the hashmap to be stored in the property paramater "multiMap_PMReport"
	 message.setProperty("multiMap_PMReport",multiMap_PMReport); 
    
    return message;

} //End Function

