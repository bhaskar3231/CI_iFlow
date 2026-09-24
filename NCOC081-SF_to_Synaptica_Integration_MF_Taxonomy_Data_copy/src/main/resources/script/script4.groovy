/* Refer the link below to learn more about the use cases of script.
https://help.sap.com/viewer/368c481cd6954bdfa5d0435479fd4eaf/Cloud/en-US/148851bf8192412cba1f9d2c17f4bd25.html

If you want to know more about the SCRIPT APIs, refer the link below
https://help.sap.com/doc/a56f52e1a58e4e2bac7f7adbf45b2e26/Cloud/en-US/index.html */
import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.xml.XmlUtil;
import groovy.xml.*;
import java.util.*;
import java.io.*;

def Message map_personnelclass_type(Message message) {
    
    def body = message.getBody(java.lang.String) as String;
	def get_properties = message.getProperties();
	
	def personnelclass_data =new XmlParser().parseText(body);
     LinkedHashMap<String,String> multiMap = get_properties.get("personnel_type");
     
     personnelclass_data.PickListValueV2.each{
 //        for(entry in multiMap) { println"Hex Code: $entry.key = Color Name: $entry.value"}
 Set<String> keySet = multiMap.keySet();
        List<String> listKeys = new ArrayList<String>(keySet);
        def len = multiMap.size();
        for(int i =0;i<len;i++){
            Node EmpEmployment = it.appendNode("EmpEmployment");
            String key = listKeys.get(i);
            def value = multiMap.get(key);
     //       for (multiMap.Entry<String,String> entry : multiMap.entrySet()) {
   // def value = entry.getValue();
    // Do things with the listit.externalCode
    EmpEmployment.appendNode("PickListV2_effectiveStartDate_emptype",[:],value[0]);
     EmpEmployment.appendNode("label_defaultValue_emptype",[:],value[1]);
             EmpEmployment.appendNode("status_emptype",[:],value[2]);
	       EmpEmployment.appendNode("lastModifiedDateTime_emptype",[:],value[3]);
	     EmpEmployment.appendNode("externalCode_emptype",[:],value[4]);
           // EmpEmployment.appendNode("EmpEmployment");
        }
       
      
        }
        
      /* multiMap.each{     
           
       /*    it.appendNode("lastModifiedDateTime_emptype",[:],it.value[0]);
	       it.appendNode("externalCode_emptype",[:],it.value[1]);
	       it.appendNode("label_defaultValue_emptype",[:],it.value[2]);
	       it.appendNode("status_emptype",[:],it.value[3]);
	       it.appendNode("PickListV2_effectiveStartDate_emptype",[:],it.value[4]); */
	       
  /*   personnelclass_data.PickListValueV2.each{
 //        for(entry in multiMap) { println"Hex Code: $entry.key = Color Name: $entry.value"}
      it.appendNode("lastModifiedDateTime_emptype",[:],it.value[0]);
        }*/

message.setBody(XmlUtil.serialize(personnelclass_data));
 return message;
}