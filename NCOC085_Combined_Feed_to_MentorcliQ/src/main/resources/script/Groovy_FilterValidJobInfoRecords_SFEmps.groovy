import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.xml.*
import com.sap.gateway.ip.core.customdev.util.Message;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.time.DayOfWeek;
import java.util.Date; 

//Filter employee Job Info records with employee status = 'Separated' and have start date >= last interface run
def Message filterJobInfoRecord_TerminatedEmps(Message message) {
    
    //Body 
        def body = message.getBody();
       
       def parser = new XmlParser();
       
    //   def get_properties = message.getProperties();
        def pMap = message.getProperties();
        def lastRunDateTime = pMap.get("lastRunDateTime");
       
        //def emp = parser.parseText(body);
        def emp = new XmlParser().parseText(body);
        for(ee in emp.EmpEmployment) {
            
            if(ee.assignmentClass.text() == 'ST') {
            
                def ji = ee.jobInfoNav.EmpJob;

                for (i = 0; i < ji.size(); i++) {
                  //  if(pn.emailNav.PerEmail.emailTypeNav.PicklistOption.externalCode.text() == 'B'){
                    String startDate_jobInfo = ji[i].startDate.text();
                    if(!((ji[i].eventNav.PicklistOption.externalCode.text() == '26') && (startDate_jobInfo >= lastRunDateTime))){
        	        
        	            ji[i].parent().remove(ji[i]);
        	        }
                }
            
            }
       
        }
        
        def dat = XmlUtil.serialize(emp);
        message.setBody(dat)
        
        return message;
}


//Filter employee job info records containing employee status = hire or rehire and lies between (current date+1) and 28 days in future from current date
def Message filterJobInfoRecord_FutureHireEmps(Message message) {

        //Body 
        def body = message.getBody();

       def parser = new XmlParser();
       def emp = new XmlParser().parseText(body);

       def get_properties = message.getProperties();
    

       def CURRENT_DATE = get_properties.get("CURRENT_DATE");


        def currentDate = new Date();
    
        //Set endDate for fetching future hires (currentDate+28) 
        def endDate = currentDate + 28;
        endDate = endDate.format("yyyy-MM-dd'T'HH:mm:ss.SSS");
        //Convert and store endDate value in a string parameter
        String endDate_str = endDate;

        
        //Loop over each employee
        for(ee in emp.EmpEmployment) {
            
            String startDate_str = ee.startDate.text();
            if((ee.assignmentClass.text() == 'ST') && (startDate_str > CURRENT_DATE) && (startDate_str <= endDate_str)) {
                
                def ejr = ee.empJobRelationshipNav.EmpJobRelationships;
                def en = ee.personNav.PerPerson.emailNav.PerEmail;
                def pn = ee.personNav.PerPerson.personalInfoNav.PerPersonal;
                def ji = ee.jobInfoNav.EmpJob;
                def et = ee.userNav.User.userIdOfEmployeeTimeNav.EmployeeTime;  
             
            //String startDate_jobInfo = "";
            
                for (i = 0; i < ji.size(); i++) {
                  
                    String startDate_jobInfo = ji[i].startDate.text();  
        
        			if (!((ji[i].eventNav.PicklistOption.externalCode.text() == 'H' || ji[i].eventNav.PicklistOption.externalCode.text() == 'R') && (startDate_jobInfo > CURRENT_DATE) && (startDate_jobInfo <= endDate_str))) {
        			   
                        ji[i].parent().remove(ji[i]);
        
                    }
    
                }
            
            }

        }

        def dat = XmlUtil.serialize(emp);
        message.setBody(dat)

        return message;
}