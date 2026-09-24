//Headers
import com.sap.gateway.ip.core.customdev.util.Message;
import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.xml.*

def Message processData(Message message) {

//Body
def body = message.getBody();

// Properties
def parser = new XmlParser();
def pMap = message.getProperties();
//def currentdatecheck = pMap.get("current_date");
def list = parser.parseText(body);
//def currentdate = currentdatecheck.toString();

// XML parsing
for(ee in list.EmpEmployment) {

//Remove the extra nodes from jobinfo nav tab if the data is not changing today or not created today
def ji = ee.jobInfoNav.EmpJob;
for(k = 0; k < ji.size(); k++) {
    
//lastModifiedDate = ji[k].lastModifiedDateTime.text().substring(0,10);
//startDate = ji[k].startDate.text().substring(0,10);

//Logic for segriting the empl status
status = ji[k].emplStatusNav.PicklistOption.externalCode.text();


if(status == 'D')
{

ji[k].parent().remove(ji[k]);
}
}
}

def dat = XmlUtil.serialize(list);
message.setBody(dat)

return message;
}