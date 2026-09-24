import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.xml.XmlUtil;
import groovy.xml.*;

/*---- PURPOSE - This groovy script is used to enrich and append the background industry fields, pm_report fields like SkillCategory, Mileston, etc. and TIME_IN_LEVEL_COMMON field for each employee------------ */
def Message processData(Message message) {
	
	def body = message.getBody(java.lang.String) as String;
	def get_properties = message.getProperties();

    def pm_flag = get_properties.get("PM_Flag");

	def empData =new XmlParser().parseText(body);
  
    // Fetch Background Industry HashMap
     HashMap<String,String> multiMap  = get_properties.get("multiMap_bgIndustry");
     
    // Fetch PM Report HashMap
     HashMap<String,String> multiMap1  = get_properties.get("multiMap_PMReport");
     
    // Fetch TIME_IN_LEVEL_COMMON field HashMap
     HashMap<String,String> multiMap2  = get_properties.get("multiMap_TIL");
     def v_userId;
	

	 //Loop over each employee
     empData.EmpEmployment.each{
	     v_userId= it.userId.text();
	     def v_personIdExternal= it.personIdExternal.text();
	     def bg_industry="";
	     def pm_report="";
	     def til="";
	     
	     /*---Enrich Background Industry fields from the HashMap------------------------------------------------------------------------------------------------------------------------------*/
	     if(!v_userId.equals("")){
	       
	         if(multiMap.containsKey(v_userId))
	            bg_industry=multiMap.get(v_userId);
	         else
	            bg_industry="";
	     }

	      else{}
	     if(!bg_industry.equals("")){
	       it.appendNode("Industry",[:],bg_industry[0]);
	       it.appendNode("Sector",[:],bg_industry[1]);
	       it.appendNode("Industry_2nd",[:],bg_industry[2]);
	       it.appendNode("Sector_2nd",[:],bg_industry[3]);
	     }

	     else {
	       it.appendNode("Industry",[:],"");
	       it.appendNode("Sector",[:],"");
	       it.appendNode("Industry_2nd",[:],"");
	       it.appendNode("Sector_2nd",[:],"");

	     }

	     /*---Enrich PMReport field values from the hashmap------------------------------------------------------------------------------------------------------------------------------------*/
	     if(!v_userId.equals("")){
	        
	         if(multiMap1.containsKey(v_userId))
	            pm_report=multiMap1.get(v_userId);
	         else
	            pm_report="";
	     }

	      else{}
	      
	     if(!pm_report.equals("")){
	       it.appendNode("Milestone",[:],pm_report[0]);
	       it.appendNode("YAL",[:],pm_report[1]);
	       it.appendNode("ClusterInformation",[:],pm_report[2]);
	       it.appendNode("SkillCategory",[:],pm_report[3]);
	     }

	     else {
	       it.appendNode("Milestone",[:],"");
	       it.appendNode("YAL",[:],"");
	       it.appendNode("ClusterInformation",[:],"");
	       it.appendNode("SkillCategory",[:],"");

	     }

     
	     /*---Enrich TIL field values from the hashmap----------------------------------------------------------------------------------------------------------------------------------------*/
	     if(!v_personIdExternal.equals("")){
	        
	         if(multiMap2.containsKey(v_personIdExternal))
	            til=multiMap2.get(v_personIdExternal);
	         else
	            til="";
	     }

	      else{}
	     if(!til.equals("")){
	       it.appendNode("TIME_IN_LEVEL_COMMON",[:],til[0]);
	     }

	     else {
	       it.appendNode("TIME_IN_LEVEL_COMMON",[:],"");
	     }
	      /* it.appendNode("bankKey",[:],bankKey);
	       it.appendNode("effectiveStatus",[:],effectiveStatus);
	       it.appendNode("modifier",[:],status);*/


	}


    message.setBody(XmlUtil.serialize(empData));
    return message;

}