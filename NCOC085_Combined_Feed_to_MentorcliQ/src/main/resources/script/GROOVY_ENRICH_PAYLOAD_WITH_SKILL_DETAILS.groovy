import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.xml.XmlUtil;
import groovy.xml.*;

/*---- PURPOSE - This groovy script is used to enrich and append the pm_report fields like SkillCategory, Mileston, etc. and TIME_IN_LEVEL_COMMON field for each employee------------ */
def Message processData(Message message) {
	
	def body = message.getBody(java.lang.String) as String;
	def get_properties = message.getProperties();

   	def empData =new XmlParser().parseText(body);
  
    
    // Fetch PM Report HashMap
     HashMap<String,String> multiMap1  = get_properties.get("multiMap_PMReport");
     
    // Fetch TIME_IN_LEVEL_COMMON field HashMap
     HashMap<String,String> multiMap2  = get_properties.get("multiMap_TIL");
     
     //Fetch Dnu, BCLP, Buddy Details 
     def DNU_PersonIds=get_properties.get("DNU_PersonIds");
     def BCLP_PersonIds=get_properties.get("BCLP_PersonIds");
     def Buddy_PersonIds=get_properties.get("BUDDY_PERSON_IDS");
     
     
     def v_userId="";
	 def v_personIdExternal= "";
     def flag_emp_status="";
	 //Loop over each employee
     empData.EmpEmployment.each{
	    v_userId= it.userId.text();
	    v_personIdExternal= it.personIdExternal.text();
	    flag_emp_status=it.FLAG_EMP_STATUS.text();
	    def pm_report="";
	    def til="";
	     
	     /*---Enrich PMReport field values from the hashmap----*/
	     def flag_skill="";
	    
	    if(flag_emp_status.equals("A") || flag_emp_status.equals("LOA") || flag_emp_status.equals("FT")){
	       
	       //Check for Skill category
	        if(!v_userId.equals("")){
	            if(multiMap1.containsKey(v_userId)){
			    pm_report=multiMap1.get(v_userId);
			    if(!pm_report.equals("")){
				    it.appendNode("Milestone",[:],pm_report[0]);
				    it.appendNode("YAL",[:],pm_report[1]);
				    it.appendNode("ClusterInformation",[:],pm_report[2]);
				    it.appendNode("SkillCategory",[:],pm_report[3]);
				    
				    flag_skill="Y"
			    }
			  }
		   }
		   
		   //Check for Buddy Pool
		   if(Buddy_PersonIds.contains(v_personIdExternal))
		       it.appendNode("Buddy_Pool",[:],"Yes");
		   else
		      it.appendNode("Buddy_Pool",[:],"No");
		      
		   //Check for BCLP
		   if(BCLP_PersonIds.contains(v_personIdExternal))
		       it.appendNode("BCLP",[:],"Yes");
		   else
		      it.appendNode("BCLP",[:],"No");
		      
		  //Check for DNU
		   if(DNU_PersonIds.contains(v_personIdExternal))
		       it.appendNode("DNU",[:],"Yes");
		   else
		      it.appendNode("DNU",[:],"No");
		         
		  
	  }
	   
	   if(flag_skill.equals("")){
	      	it.appendNode("Milestone",[:],"");
			it.appendNode("YAL",[:],"");
			it.appendNode("ClusterInformation",[:],"");
			it.appendNode("SkillCategory",[:],"");
	   }
    
      
	    /*---Enrich TIL field values from the hashmap----------*/
	     if(!v_personIdExternal.equals("")){
	        if(multiMap2.containsKey(v_personIdExternal)){
			   til=multiMap2.get(v_personIdExternal);
			   if(!til.equals(""))
			     it.appendNode("TIME_IN_LEVEL_COMMON",[:],til[0]);
			   else 
				it.appendNode("TIME_IN_LEVEL_COMMON",[:],"");
			}
			else 
			  it.appendNode("TIME_IN_LEVEL_COMMON",[:],"");
		}
	    else
	        it.appendNode("TIME_IN_LEVEL_COMMON",[:],"");
	}//Loop


    message.setBody(XmlUtil.serialize(empData));
    return message;

}