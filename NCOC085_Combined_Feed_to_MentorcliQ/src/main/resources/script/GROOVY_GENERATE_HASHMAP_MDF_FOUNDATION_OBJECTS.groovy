/* 
------------------------------------------------------------------------------------------
   PURPOSE -- This groovy script creates HashMaps for Pickists, MDF and Foundation Objects
-------------------------------------------------------------------------------------------
*/

import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;

/* --- Store Picklists Information in HashMap ---*/

def Message picklists_hashmap(Message message) {

	//Body	
	def body = message.getBody(java.lang.String) as String;
	def get_properties = message.getProperties();

	def picklist_xml = new XmlSlurper().parseText(body);
    
    //define hashmaps for each picklist
  
    HashMap<String,String> Picklist_Salutaion_Hashmap  = new HashMap<String,String>();
    HashMap<String,String> Picklist_EmployeeStatus_Hashmap  = new HashMap<String,String>();
    HashMap<String,String> Picklist_EmployeeClass_HashMap  = new HashMap<String,String>();
    HashMap<String,String> Picklist_EmployeeClass_Desc_HashMap  = new HashMap<String,String>();
    HashMap<String,String> Picklist_EmploymentType_Hashmap  = new HashMap<String,String>();
    HashMap<String,String> Picklist_Event_Hashmap  = new HashMap<String,String>();
    HashMap<String,String> Picklist_SWIFT_OFFICE_CODE_Hashmap  = new HashMap<String,String>();
   
    HashMap<String,String> Picklist_GPS_FLAG_Label_Hashmap  = new HashMap<String,String>();
    HashMap<String,String> Picklist_HireSource_Label_Hashmap  = new HashMap<String,String>();
    HashMap<String,String> Picklist_Industry_Hashmap  = new HashMap<String,String>();
    HashMap<String,String> Picklist_Sector_Hashmap  = new HashMap<String,String>();
    
    HashMap<String,String> Picklist_TalentModel_Hashmap  = new HashMap<String,String>();
    HashMap<String,String> Picklist_HXM_FOCUS_Hashmap  = new HashMap<String,String>();
    
    
    
    
    
    /* === Read all the records from the incoming payload and store them in a Hashmap == */
    
    picklist_xml.PickListValueV2.each{
        if((it.PickListV2_id.text()).equals("event"))
		    Picklist_Event_Hashmap.put(it.optionId.text(),it.externalCode.text());
	    else if((it.PickListV2_id.text()).equals("salutation"))
            Picklist_Salutaion_Hashmap.put(it.optionId.text(),it.label_en_US.text());
        else if((it.PickListV2_id.text()).equals("employee-status"))
            Picklist_EmployeeStatus_Hashmap.put(it.optionId.text(),it.externalCode.text());
        else if((it.PickListV2_id.text()).equals("employmentType"))
            Picklist_EmploymentType_Hashmap.put(it.optionId.text(),it.label_en_US.text());
        else if((it.PickListV2_id.text()).equals("EmployeeClass")){
            Picklist_EmployeeClass_Desc_HashMap.put(it.optionId.text(),it.label_en_US.text());
            Picklist_EmployeeClass_HashMap.put(it.optionId.text(),it.externalCode.text());
        }
        else if((it.PickListV2_id.text()).equals("HXM_CC_SWIFT_OFFICE"))
            Picklist_SWIFT_OFFICE_CODE_Hashmap.put(it.externalCode.text(),it.label_en_US.text());
        else if((it.PickListV2_id.text()).equals("HXM_GPSFLAG_US"))
             Picklist_GPS_FLAG_Label_Hashmap.put(it.optionId.text(),it.label_en_US.text());
        else if((it.PickListV2_id.text()).equals("HXM_HIRE_SOURCE"))
            Picklist_HireSource_Label_Hashmap.put(it.optionId.text(),it.label_en_US.text());
        else if((it.PickListV2_id.text()).equals("industry"))
             Picklist_Industry_Hashmap.put(it.optionId.text(),it.label_en_US.text());
        else if((it.PickListV2_id.text()).equals("HXM_SECTOR"))
            Picklist_Sector_Hashmap.put(it.optionId.text(),it.label_en_US.text());
        else if((it.PickListV2_id.text()).equals("HXM_TALENTMODEL"))
            Picklist_TalentModel_Hashmap.put(it.optionId.text(),it.label_en_US.text());
        else if((it.PickListV2_id.text()).equals("HXM_FOCUS"))
            Picklist_HXM_FOCUS_Hashmap.put(it.optionId.text(),it.label_en_US.text());    
        else if((it.PickListV2_id.text()).equals("ecEmailType")){
            if((it.externalCode.text()).equals("B"))
                message.setProperty("Business_Email_OptionId",it.optionId.text());
            else if((it.externalCode.text()).equals("P"))
                message.setProperty("Personal_Email_OptionId",it.optionId.text());
        }
        else if((it.PickListV2_id.text()).equals("jobRelType")){
            if((it.label_en_US.text()).equals("Coach"))
                message.setProperty("JobRel_Coach_OptionId",it.optionId.text());
        }
    }
	
		/* ============ Set the HashMaps created as properties to be used in mapping=============*/
	message.setProperty("Picklist_Salutaion_Hashmap",Picklist_Salutaion_Hashmap);
	message.setProperty("Picklist_EmployeeStatus_Hashmap",Picklist_EmployeeStatus_Hashmap);
	message.setProperty("Picklist_EmployeeClass_HashMap",Picklist_EmployeeClass_HashMap);
    message.setProperty("Picklist_EmploymentType_Hashmap",Picklist_EmploymentType_Hashmap);
    message.setProperty("Picklist_Event_Hashmap",Picklist_Event_Hashmap);
    message.setProperty("Picklist_SWIFT_OFFICE_CODE_Hashmap",Picklist_SWIFT_OFFICE_CODE_Hashmap);
    message.setProperty("Picklist_GPS_FLAG_Label_Hashmap",Picklist_GPS_FLAG_Label_Hashmap);
    message.setProperty("Picklist_HireSource_Label_Hashmap",Picklist_HireSource_Label_Hashmap);
    message.setProperty("Picklist_Industry_Hashmap",Picklist_Industry_Hashmap);
    message.setProperty("Picklist_Sector_Hashmap",Picklist_Sector_Hashmap);
    message.setProperty("Picklist_TalentModel_Hashmap",Picklist_TalentModel_Hashmap);
    message.setProperty("Picklist_HXM_FOCUS_Hashmap",Picklist_HXM_FOCUS_Hashmap);
    message.setProperty("Picklist_EmployeeClass_Desc_HashMap",Picklist_EmployeeClass_Desc_HashMap);
   
    return message;
}


/* --- Store Background_Industry Information in HashMap ---*/


def Message background_industry_hashmap(Message message) {
	//Body	
	def body = message.getBody(java.lang.String) as String;
	def get_properties = message.getProperties();

	
	def industry_xml = new XmlSlurper().parseText(body);
	
	Map<String, List<String>> Background_Industry_HashMap = new HashMap<String, List<String>>();

	
    industry_xml.Background_Industry.each{
	    
	    List<String> industry_sector_values = new ArrayList<String>();
	    
	    industry_sector_values.add(it.Industry.text());
	    industry_sector_values.add(it.Sector.text());
	    industry_sector_values.add(it.Industry_2nd.text());
	    industry_sector_values.add(it.Sector_2nd.text());
	   
	   Background_Industry_HashMap.put(it.userId.text(),industry_sector_values);
   }
	
	/* Set the HashMaps created as properties to be used in mapping*/
	message.setProperty("Background_Industry_HashMap",Background_Industry_HashMap);

		
    return message;
    
}


/* --- Store CostCenter Information in HashMap ---*/

def Message cost_center_hashmap(Message message) {
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


/* --- Store Location Information in HashMap ---*/

def Message location_hashmap(Message message) {
	//Body	
	def body = message.getBody(java.lang.String) as String;
	def get_properties = message.getProperties();

	
	def location_xml = new XmlSlurper().parseText(body);
	
    HashMap<String,String> MDF_LocationGroup_Map  = new HashMap<String,String>();
   
		
	location_xml.FOLocation.each{
  	  MDF_LocationGroup_Map.put(it.externalCode.text(),it.locationGroupNav.FOLocationGroup.name.text());
	}
	
	/* Set the HashMaps created as properties to be used in mapping*/
	message.setProperty("MDF_LocationGroup_Map",MDF_LocationGroup_Map);

    return message;
    
}

/* --- Store Event Reason Information in HashMap ---*/
def Message eventReason_hashmap(Message message) {
	//Body	
	def body = message.getBody(java.lang.String) as String;
	def get_properties = message.getProperties();

	
	def event_reasons_xml = new XmlSlurper().parseText(body);
	
    HashMap<String,String> FO_Event_Reason_HashMap  = new HashMap<String,String>();
   
		
	event_reasons_xml.FOEventReason.each{
	    FO_Event_Reason_HashMap.put(it.externalCode.text(),it.name.text());
    }
    
    /* Set the HashMaps created as properties to be used in mapping*/
	message.setProperty("FO_Event_Reason_HashMap",FO_Event_Reason_HashMap);

		
    return message;
    
}