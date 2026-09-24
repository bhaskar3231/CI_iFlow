
import com.sap.gateway.ip.core.customdev.util.Message;
import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.xml.*

def Message processData(Message message) {
    
    //Body 
        def body = message.getBody();
       
        def parser = new XmlParser();
       	def pMap = message.getProperties();
       	def currentdatecheck = pMap.get("currentDate");
        def list = parser.parseText(body);
        def currentdate =  currentdatecheck.toString();
        
        for(ee in list.EmpEmployment) {
            def wp = ee.empWorkPermitNav.EmpWorkPermit;
            def ji = ee.jobInfoNav.EmpJob;
  
      
    //Remove the extra nodes from workpermit nav tab where isValidated field is not equal to true and country is not USA 
            for(i = 0; i < wp.size(); i++) {
                if(wp[i].countryNav.PicklistOption.externalCode.text() != 'USA'){
                wp[i].parent().remove(wp[i]);
                }
                else if((wp[i].isValidated.text() != 'true')){
                    wp[i].parent().remove(wp[i]);
                }
                //The below scenario was removed from design.
               /* else if((wp[i].isValidated.text() == 'true') && (wp[i].countryNav.PicklistOption.externalCode.text() == 'USA') || (wp[i].documentTypeNav.PicklistOption.externalCode.text() == 'US_GC')){
                    wp[i].parent().remove(wp[i]);
                }*/
            }
   
   
    //Remove the extra nodes from jobinfo nav tab where emplStatus is not Report No-Show, Separated and Dormant     
            for(j = 0; j < ji.size(); j++) {
                if((ji[j].emplStatusNav.PicklistOption.localeLabel.text() != 'Report No-Show') && (ji[j].emplStatusNav.PicklistOption.localeLabel.text() != 'Separated') && (ji[j].emplStatusNav.PicklistOption.localeLabel.text() != 'Dormant')){
                    ji[j].parent().remove(ji[j]);
                    }
            }
     /*         
    def emplstatus = pMap.get("EMPLSTATUS");
		if(emplstatus.trim().length()>0){
		
	    String[] emplstatus_1;
		emplstatus_1 = emplstatus.trim().split(',');
		for(int i=0; i<(emplstatus_1.size()-1);i++){
		    for(j = 0; j < ji.size(); j++) {
                if((ji[j].emplStatusNav.PicklistOption.localeLabel.text() != emplstatus_1[i]){
                    ji[j].parent().remove(ji[j]);
                    }
		    }
	    }
    */
    //Remove the extra nodes from jobinfo nav tab if the data is not changing today or not created today        
            def ki = ee.jobInfoNav.EmpJob;
             for(k = 0; k < ki.size(); k++) {
                    createdOn = ki[k].createdOn.text().substring(0,10);
                    startDate = ki[k].startDate.text().substring(0,10);
                    
                   if((createdOn != currentdate) && (startDate != currentdate)){
                   
                    ki[k].parent().remove(ki[k]);
                    }
            }
        }
        
        def dat = XmlUtil.serialize(list);
        message.setBody(dat)
        
        return message;
}