import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.xml.*
import com.sap.gateway.ip.core.customdev.util.Message;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.time.DayOfWeek;
import java.util.Date; 

//Filter and transformation of Active, No show, LOA employees data
def Message processData_ActiveEmps(Message message) {

    //Body 
        def body = message.getBody();

       def parser = new XmlParser();

       def get_properties = message.getProperties();
       def CURRENT_DATE = get_properties.get("CURRENT_DATE");


        //def emp = parser.parseText(body);
        def emp = new XmlParser().parseText(body);
        
        //Calculating the last Saturday's date to mark and map the flag as 'X' for FutureHire_Flag field in the output
        
        def dayOfWeek = new Date().parse("yyyy-MM-dd", CURRENT_DATE).format("EEEE")
        def empStartDate = new Date();
       // def todayDate = new Date();
         if(dayOfWeek == 'Sunday') {
            empStartDate = empStartDate - 1;
         }
         else if(dayOfWeek == 'Monday') {
            empStartDate = empStartDate - 2;
         }
         else if(dayOfWeek == 'Tuesday') {
            empStartDate = empStartDate - 3;
         }
         else if(dayOfWeek == 'Wednesday') {
           empStartDate = empStartDate - 4;
         }
         else if(dayOfWeek == 'Thursday') {
            empStartDate = empStartDate - 5;
         }
         else if(dayOfWeek == 'Friday') {
            empStartDate = empStartDate - 6;
         }
         else if(dayOfWeek == 'Saturday') {
            empStartDate = empStartDate - 7;
         }
         else {}
    
        empStartDate = empStartDate.format("yyyy-MM-dd'T'HH:mm:ss.SSS"); 
        //Convert and store empStartDate value in a string parameter
        String empStartDate_str = empStartDate;
       // todayDate = todayDate.format("yyyy-MM-dd'T'HH:mm:ss.SSS");
        //String date_current = todayDate;
        
        //Loop over each employee
        for(ee in emp.EmpEmployment) {
          
            def ejr = ee.empJobRelationshipNav.EmpJobRelationships;
            def en = ee.personNav.PerPerson.emailNav.PerEmail;
            def pn = ee.personNav.PerPerson.personalInfoNav.PerPersonal;
            def ji = ee.jobInfoNav.EmpJob;
            def et = ee.userNav.User.userIdOfEmployeeTimeNav.EmployeeTime;
            String startDate_str = ee.startDate.text();

            for (i = 0; i < ejr.size(); i++) {
              
              if(ejr[i].relationshipTypeNav.PicklistOption.localeLabel.text() == "Coach"){
            //if(ejr[i].relationshipTypeNav.PicklistOption.localeLabel.text() == "Coach"){                 
    	        ee.appendNode("CounselorFirstNameName",[:],ejr[i].relEmploymentNav.EmpEmployment.personNav.PerPerson.personalInfoNav.PerPersonal.firstName.text());
    	        ee.appendNode("CounselorLastName",[:],ejr[i].relEmploymentNav.EmpEmployment.personNav.PerPerson.personalInfoNav.PerPersonal.lastName.text());
    	       // ee.appendNode("CounselorFullName",[:],ejr[i].relUserNav.User.defaultFullName.text());
    	        ee.appendNode("JobRelationshipUserId",[:],ejr[i].relUserNav.User.userId.text());
    	        ee.appendNode("CounseloruserId",[:],ejr[i].relUserNav.User.empInfo.EmpEmployment.personNav.PerPerson.customString2.text());
    	        ee.appendNode("CounselorEmail",[:],ejr[i].relUserNav.User.email.text());
    	       // break;
    	       
	           }
            }
            

            for (i = 0; i < en.size(); i++) {
              //  if(pn.emailNav.PerEmail.emailTypeNav.PicklistOption.externalCode.text() == 'B'){
                if(en[i].emailTypeNav.PicklistOption.externalCode.text() == 'B'){
        	        ee.appendNode("BusinessEmail",[:],en[i].emailAddress.text());
        	        continue;
	            }
    	        if(en[i].emailTypeNav.PicklistOption.externalCode.text() == 'P'){
    	            ee.appendNode("PersonalEmail",[:],en[i].emailAddress.text());
    	        }
            }

            for (i = 0; i < pn.size(); i++) {
              //  if(pn[i].endDate.text() == '9999-12-31T00:00:00.000'){
        	        ee.appendNode("firstName",[:],pn[i].firstName.text());
        	        ee.appendNode("lastName",[:],pn[i].lastName.text());
        	        ee.appendNode("salutation",[:],pn[i].salutationNav.PicklistOption.localeLabel.text());
        	    //    break;
	           // }
            }
            
            ee.appendNode("customString2",[:],ee.personNav.PerPerson.customString2.text());


             for (i = 0; i < ji.size(); i++) {
              //  if(pn.emailNav.PerEmail.emailTypeNav.PicklistOption.externalCode.text() == 'B'){
            String jobLevelDesc_str = ji[i].customString4Nav.cust_Level.externalName.text();
	       // ee.appendNode("customString4",[:],ji[i].customString4Nav.cust_Level.externalName.text());
	        ee.appendNode("customString4",[:],jobLevelDesc_str);
	        ee.appendNode("jobTitle",[:],ji[i].jobTitle.text());
	        ee.appendNode("jobCode",[:],ji[i].jobCode.text());
	        ee.appendNode("costCenter",[:],ji[i].costCenter.text());
	        ee.appendNode("costCenterName",[:],ji[i].costCenterNav.FOCostCenter.name.text());
	        ee.appendNode("custBusiness",[:],ji[i].costCenterNav.FOCostCenter.cust_Business.text());
	        ee.appendNode("cust_BusinessArea",[:],ji[i].costCenterNav.FOCostCenter.cust_BusinessArea.text());
	        ee.appendNode("cust_BusinessLine",[:],ji[i].costCenterNav.FOCostCenter.cust_BusinessLine.text());
	        ee.appendNode("cust_Region",[:],ji[i].costCenterNav.FOCostCenter.cust_Region.text());
	        ee.appendNode("location_group_name",[:],ji[i].locationNav.FOLocation.locationGroupNav.FOLocationGroup.name.text());
	    
	        ee.appendNode("cust_ZZOFFICE",[:],ji[i].costCenterNav.FOCostCenter.cust_ZZOFFICENav.PickListValueV2.label_defaultValue.text());
	        ee.appendNode("cust_ZZFEDERAL",[:],ji[i].costCenterNav.FOCostCenter.cust_ZZFEDERAL.text());
	        ee.appendNode("cust_BusinessSubLine",[:],ji[i].costCenterNav.FOCostCenter.cust_BusinessSubLine.text());
	        ee.appendNode("hireSource",[:],ji[i].hireSourceNav.PicklistOption.localeLabel.text());
	        ee.appendNode("customString9",[:],ji[i].customString9Nav.cust_capability.externalName.text());
	        ee.appendNode("departmentCode",[:],ji[i].departmentNav.FODepartment.externalCode.text());
	        ee.appendNode("departmentName",[:],ji[i].departmentNav.FODepartment.name.text());
	        ee.appendNode("employmentType",[:],ji[i].employmentTypeNav.PicklistOption.localeLabel.text());
	        ee.appendNode("customString9Code",[:],ji[i].customString9.text());
	        ee.appendNode("customString10",[:],ji[i].customString10Nav.cust_subcapability.externalName.text());
	        ee.appendNode("customString10Code",[:],ji[i].customString10.text());
	        ee.appendNode("customString21",[:],ji[i].customString21Nav.PicklistOption.localeLabel.text());
	        ee.appendNode("customString7",[:],ji[i].customString7Nav.PicklistOption.localeLabel.text());
	        ee.appendNode("employmeeClass",[:],ji[i].employeeClassNav.PicklistOption.localeLabel.text());
	        //ee.appendNode("departmentCode",[:],ji[i].startDate.text());
	        ee.appendNode("eventReasonCode",[:],ji[i].eventReason.text());
	        ee.appendNode("eventReasonDesc",[:],ji[i].eventReasonNav.FOEventReason.name.text());
	        ee.appendNode("event",[:],ji[i].eventNav.PicklistOption.externalCode.text());
	        ee.appendNode("eventDesc",[:],ji[i].eventNav.PicklistOption.localeLabel.text());
	        ee.appendNode("employeeStatus",[:],ji[i].emplStatusNav.PicklistOption.externalCode.text());
	        ee.appendNode("jobStartDate",[:],ji[i].startDate.text());
	        ee.appendNode("jobEndDate",[:],ji[i].endDate.text());
            
            for (j = 0; j < et.size(); j++) {
    	        if(ji[i].eventNav.PicklistOption.externalCode.text() == '10' && et[j].approvalStatus.text() == 'APPROVED'){
    	            ee.appendNode("EmpTime_startDate",[:],et[j].startDate.text());
    	            ee.appendNode("EmpTime_endtDate",[:],et[j].endDate.text());
    	            break;
    	        }
            }
            
            

	        if(ji[i].costCenterNav.FOCostCenter.cust_BusinessArea.text() == 'CONSULTING' && ji[i].costCenterNav.FOCostCenter.cust_BusinessArea.text() == 'DC Platforms'){
	            ee.appendNode("ExceptionType",[:],"DC Platforms");
	        }

	        else if(ji[i].costCenterNav.FOCostCenter.cust_BusinessArea.text() == 'CONSULTING' || ji[i].costCenterNav.FOCostCenter.cust_BusinessArea.text() == 'National Consulting'){
	            ee.appendNode("ExceptionType",[:],"National Consulting");
	        }

	        else if(ji[i].customString7Nav.PicklistOption.localeLabel.text() == 'Project Delivery'){
	            ee.appendNode("ExceptionType",[:],"Project Delivery Model");
	        }

	        else if((jobLevelDesc_str == 'ADMIN MANAGING DIRECTOR') || (jobLevelDesc_str == 'CS DIRECTOR - ND') || (jobLevelDesc_str == 'CS MANAGING DIRECTOR') || (jobLevelDesc_str == 'Partner/Principal') || (jobLevelDesc_str == 'XIN-CS MANAGING DIRECTOR')){
	            ee.appendNode("ExceptionType",[:],"PPMD");
	        }
	        else {
	            ee.appendNode("ExceptionType",[:],"");
	        }
	       
	       /*If employee was hired after the last Saturday, employee will considered as future hire*/ 
	       if((ji[i].eventNav.PicklistOption.externalCode.text() == 'H' || ji[i].eventNav.PicklistOption.externalCode.text() == 'R') && (ee.assignmentClass.text() == 'ST') && (startDate_str >= empStartDate_str) && (startDate_str <= CURRENT_DATE)){
	            ee.appendNode("FutureHire_Flag",[:],"X");
	        }
	        
	        if(ji[i].eventNav.PicklistOption.externalCode.text() == 'NS'){
	            ee.appendNode("NoShow_Flag",[:],"X");
	        }

            }

            ee.empJobRelationshipNav.replaceNode {};
            ee.personNav.replaceNode {};
            ee.userNav.replaceNode {};
            ee.jobInfoNav.replaceNode {};

        }

        def dat = XmlUtil.serialize(emp);
        message.setBody(dat)

        return message;
}

//Filter and transformation of future terminated employees since the last run
def Message processData_TerminatedEmps(Message message) {
    
    //Body 
        def body = message.getBody();
       
       def parser = new XmlParser();
       
        def get_properties = message.getProperties();
        def lastRunDateTime = get_properties.get("lastRunDateTime");
        def CURRENT_DATE = get_properties.get("CURRENT_DATE");
       
        //def emp = parser.parseText(body);
        def emp = new XmlParser().parseText(body);
        for(ee in emp.EmpEmployment) {
            
            if(ee.assignmentClass.text() == 'ST') {
                
                def ejr = ee.empJobRelationshipNav.EmpJobRelationships;
                def en = ee.personNav.PerPerson.emailNav.PerEmail;
                def pn = ee.personNav.PerPerson.personalInfoNav.PerPersonal;
                def ji = ee.jobInfoNav.EmpJob;
                def et = ee.userNav.User.userIdOfEmployeeTimeNav.EmployeeTime;  
                
            for (i = 0; i < ejr.size(); i++) {
                
              //if(ejr[i].endDate.text() == '9999-12-31T00:00:00.000' && ejr[i].relationshipTypeNav.PicklistOption.localeLabel.text() == "Coach"){
              if(ejr[i].startDate.text() <= CURRENT_DATE && ejr[i].endDate.text() >= CURRENT_DATE && ejr[i].relationshipTypeNav.PicklistOption.localeLabel.text() == "Coach"){
    	        //ee.appendNode("CounselorFullName",[:],ejr[i].relUserNav.User.defaultFullName.text());
    	        ee.appendNode("CounselorFirstName",[:],ejr[i].relEmploymentNav.EmpEmployment.personNav.PerPerson.personalInfoNav.PerPersonal.firstName.text());
    	        ee.appendNode("CounselorLastName",[:],ejr[i].relEmploymentNav.EmpEmployment.personNav.PerPerson.personalInfoNav.PerPersonal.lastName.text());
    	        ee.appendNode("JobRelationshipUserId",[:],ejr[i].relUserNav.User.userId.text());
    	        ee.appendNode("CounseloruserId",[:],ejr[i].relUserNav.User.empInfo.EmpEmployment.personNav.PerPerson.customString2.text());
    	        ee.appendNode("CounselorEmail",[:],ejr[i].relUserNav.User.email.text());
    	        break;
	            }
            }
            

            for (i = 0; i < en.size(); i++) {
              //  if(pn.emailNav.PerEmail.emailTypeNav.PicklistOption.externalCode.text() == 'B'){
                if(en[i].emailTypeNav.PicklistOption.externalCode.text() == 'B'){
        	        ee.appendNode("BusinessEmail",[:],en[i].emailAddress.text());
        	        continue;
	            }
    	        if(en[i].emailTypeNav.PicklistOption.externalCode.text() == 'P'){
    	            ee.appendNode("PersonalEmail",[:],en[i].emailAddress.text());
    	        }
            }

            for (i = 0; i < pn.size(); i++) {
                if(pn[i].startDate.text() <= CURRENT_DATE && pn[i].endDate.text() >= CURRENT_DATE){
        	        ee.appendNode("firstName",[:],pn[i].firstName.text());
        	        ee.appendNode("lastName",[:],pn[i].lastName.text());
        	        ee.appendNode("salutation",[:],pn[i].salutationNav.PicklistOption.localeLabel.text());
        	        break;
	            }
            }
            
            ee.appendNode("customString2",[:],ee.personNav.PerPerson.customString2.text());
            
             for (i = 0; i < ji.size(); i++) {
             
              String startDate_str = ji[i].startDate.text();
             if((ji[i].eventNav.PicklistOption.externalCode.text() == '26') && (startDate_str >= lastRunDateTime)){
                 
            String jobLevelDesc_str = ji[i].customString4Nav.cust_Level.externalName.text();
	        ee.appendNode("customString4",[:],jobLevelDesc_str);
	        ee.appendNode("jobTitle",[:],ji[i].jobTitle.text());
	        ee.appendNode("jobCode",[:],ji[i].jobCode.text());
	        ee.appendNode("costCenter",[:],ji[i].costCenter.text());
	        ee.appendNode("costCenterName",[:],ji[i].costCenterNav.FOCostCenter.name.text());
	        ee.appendNode("custBusiness",[:],ji[i].costCenterNav.FOCostCenter.cust_Business.text());
	        ee.appendNode("cust_BusinessArea",[:],ji[i].costCenterNav.FOCostCenter.cust_BusinessArea.text());
	        ee.appendNode("cust_BusinessLine",[:],ji[i].costCenterNav.FOCostCenter.cust_BusinessLine.text());
	        ee.appendNode("cust_Region",[:],ji[i].costCenterNav.FOCostCenter.cust_Region.text());
	        ee.appendNode("cust_ZZOFFICE",[:],ji[i].costCenterNav.FOCostCenter.cust_ZZOFFICENav.PickListValueV2.label_defaultValue.text());
	        ee.appendNode("cust_ZZFEDERAL",[:],ji[i].costCenterNav.FOCostCenter.cust_ZZFEDERAL.text());
	        ee.appendNode("cust_BusinessSubLine",[:],ji[i].costCenterNav.FOCostCenter.cust_BusinessSubLine.text());
	        ee.appendNode("hireSource",[:],ji[i].hireSourceNav.PicklistOption.localeLabel.text());
	        ee.appendNode("customString9",[:],ji[i].customString9Nav.cust_capability.externalName.text());
	        ee.appendNode("departmentCode",[:],ji[i].departmentNav.FODepartment.externalCode.text());
	        ee.appendNode("departmentName",[:],ji[i].departmentNav.FODepartment.name.text());
	        ee.appendNode("employmentType",[:],ji[i].employmentTypeNav.PicklistOption.localeLabel.text());
	        ee.appendNode("customString9Code",[:],ji[i].customString9.text());
	        ee.appendNode("customString10Code",[:],ji[i].customString10.text());
	        ee.appendNode("customString10",[:],ji[i].customString10Nav.cust_subcapability.externalName.text());
	        ee.appendNode("customString21",[:],ji[i].customString21Nav.PicklistOption.localeLabel.text());
	        ee.appendNode("customString7",[:],ji[i].customString7Nav.PicklistOption.localeLabel.text());
	        ee.appendNode("employmeeClass",[:],ji[i].employeeClassNav.PicklistOption.localeLabel.text());
	        //ee.appendNode("departmentCode",[:],ji[i].startDate.text());
	        ee.appendNode("eventReasonCode",[:],ji[i].eventReason.text());
	        ee.appendNode("eventReasonDesc",[:],ji[i].eventReasonNav.FOEventReason.name.text());
	        ee.appendNode("event",[:],ji[i].eventNav.PicklistOption.externalCode.text());
	        ee.appendNode("eventDesc",[:],ji[i].eventNav.PicklistOption.localeLabel.text());
	        ee.appendNode("employeeStatus",[:],ji[i].emplStatusNav.PicklistOption.externalCode.text());
	        ee.appendNode("jobStartDate",[:],ji[i].startDate.text());
	        ee.appendNode("jobEndDate",[:],ji[i].endDate.text());
	        
	        ee.appendNode("EmpTime_startDate",[:],"");
	        ee.appendNode("EmpTime_endtDate",[:],"");
	        
	        if(ji[i].costCenterNav.FOCostCenter.cust_BusinessArea.text() == 'CONSULTING' || ji[i].costCenterNav.FOCostCenter.cust_BusinessArea.text() == 'DC Platforms'){
	            ee.appendNode("ExceptionType",[:],"DC Platforms");
	        }
	        
	        else if(ji[i].costCenterNav.FOCostCenter.cust_BusinessArea.text() == 'CONSULTING' || ji[i].costCenterNav.FOCostCenter.cust_BusinessArea.text() == 'National Consulting'){
	            ee.appendNode("ExceptionType",[:],"National Consulting");
	        }
	        
	        else if(ji[i].customString7Nav.PicklistOption.localeLabel.text() == 'Project Delivery'){
	            ee.appendNode("ExceptionType",[:],"Project Delivery Model");
	        }
	        
	        else if((jobLevelDesc_str == 'ADMIN MANAGING DIRECTOR') || (jobLevelDesc_str == 'CS DIRECTOR - ND') || (jobLevelDesc_str == 'CS MANAGING DIRECTOR') || (jobLevelDesc_str == 'Partner/Principal') || (jobLevelDesc_str == 'XIN-CS MANAGING DIRECTOR')){
	            ee.appendNode("ExceptionType",[:],"PPMD");
	        }
	        
	        else{
	             ee.appendNode("ExceptionType",[:],"");
	        }
	        
	        break;
	    }
            }
            
            ee.empJobRelationshipNav.replaceNode {};
            ee.personNav.replaceNode {};
            ee.userNav.replaceNode {};
            ee.jobInfoNav.replaceNode {};
            
            }
       
        }
        
        def dat = XmlUtil.serialize(emp);
        message.setBody(dat)
        
        return message;
}


//Filter and transformtion of future hires between the respective dates 
def Message processData_FutureHiredEmps(Message message) {

        //Body 
        def body = message.getBody();

       def parser = new XmlParser();
       def emp = new XmlParser().parseText(body);

       def get_properties = message.getProperties();
    //   def pMap = message.getProperties();

       def CURRENT_DATE = get_properties.get("CURRENT_DATE");

        def dayOfWeek = new Date().parse("yyyy-MM-dd", CURRENT_DATE).format("EEEE");           
    //str.append("Day of Week"+dayOfWeek+"'");
    def currentDate = new Date();
    def empStartDate = new Date();
    def todayDate = new Date();
     if(dayOfWeek == 'Sunday') {
        empStartDate = empStartDate - 1;
     }
     else if(dayOfWeek == 'Monday') {
        empStartDate = empStartDate - 2;
     }
     else if(dayOfWeek == 'Tuesday') {
        empStartDate = empStartDate - 3;
     }
     else if(dayOfWeek == 'Wednesday') {
       empStartDate = empStartDate - 4;
     }
     else if(dayOfWeek == 'Thursday') {
        empStartDate = empStartDate - 5;
     }
     else if(dayOfWeek == 'Friday') {
        empStartDate = empStartDate - 6;
     }
     else if(dayOfWeek == 'Saturday') {
        empStartDate = empStartDate - 7;
     }
     else {}

    empStartDate = empStartDate.format("yyyy-MM-dd'T'HH:mm:ss.SSS"); 
    todayDate = todayDate.format("yyyy-MM-dd'T'HH:mm:ss.SSS");
    //Convert and store empStartDate value in a string parameter
    String empStartDate_str = empStartDate;
    String date_current = todayDate;
    def endDate = currentDate + 28;
    endDate = endDate.format("yyyy-MM-dd'T'HH:mm:ss.SSS");
    //Convert and store endDate value in a string parameter
    String endDate_str = endDate;

        //def emp = parser.parseText(body);
        
        for(ee in emp.EmpEmployment) {
            
            String startDate_str = ee.startDate.text();
           // if((ee.assignmentClass.text() == 'ST') && (startDate_str >= empStartDate_str) && (startDate_str <= endDate_str)) {
            if((ee.assignmentClass.text() == 'ST') && (startDate_str > date_current) && (startDate_str <= endDate_str)) {
                
                def ejr = ee.empJobRelationshipNav.EmpJobRelationships;
                def en = ee.personNav.PerPerson.emailNav.PerEmail;
                def pn = ee.personNav.PerPerson.personalInfoNav.PerPersonal;
                def ji = ee.jobInfoNav.EmpJob;
                def et = ee.userNav.User.userIdOfEmployeeTimeNav.EmployeeTime;  
               
            for (i = 0; i < ejr.size(); i++) {
             
              if(ejr[i].startDate.text() > CURRENT_DATE && ejr[i].startDate.text() <= endDate_str && ejr[i].relationshipTypeNav.PicklistOption.localeLabel.text() == "Coach"){
    	        //ee.appendNode("CounselorFullName",[:],ejr[i].relUserNav.User.defaultFullName.text());
    	        ee.appendNode("CounselorFirstName",[:],ejr[i].relEmploymentNav.EmpEmployment.personNav.PerPerson.personalInfoNav.PerPersonal.firstName.text());
    	        ee.appendNode("CounselorLastName",[:],ejr[i].relEmploymentNav.EmpEmployment.personNav.PerPerson.personalInfoNav.PerPersonal.lastName.text());
    	        ee.appendNode("JobRelationshipUserId",[:],ejr[i].relUserNav.User.userId.text());
    	        ee.appendNode("CounseloruserId",[:],ejr[i].relUserNav.User.empInfo.EmpEmployment.personNav.PerPerson.customString2.text());
    	        ee.appendNode("CounselorEmail",[:],ejr[i].relUserNav.User.email.text());
    	        break;
	            }
            }
            

            for (i = 0; i < en.size(); i++) {
                if(en[i].emailTypeNav.PicklistOption.externalCode.text() == 'B'){
        	        ee.appendNode("BusinessEmail",[:],en[i].emailAddress.text());
        	        continue;
	            }
    	        if(en[i].emailTypeNav.PicklistOption.externalCode.text() == 'P'){
    	            ee.appendNode("PersonalEmail",[:],en[i].emailAddress.text());
    	        }
            }

            for (i = 0; i < pn.size(); i++) {
                if(pn[i].startDate.text() > CURRENT_DATE && pn[i].startDate.text() <= endDate_str){
        	        ee.appendNode("firstName",[:],pn[i].firstName.text());
        	        ee.appendNode("lastName",[:],pn[i].lastName.text());
        	        ee.appendNode("salutation",[:],pn[i].salutationNav.PicklistOption.localeLabel.text());
        	        break;
	            }
            }
            
            ee.appendNode("customString2",[:],ee.personNav.PerPerson.customString2.text());
            
            String startDate_jobInfo = "";
            
            for (i = 0; i < ji.size(); i++) {
              //  if(pn.emailNav.PerEmail.emailTypeNav.PicklistOption.externalCode.text() == 'B'){
            // String startDate_str = ji[i].startDate.text();

			if ((ji[i].eventNav.PicklistOption.externalCode.text() == 'H' || ji[i].eventNav.PicklistOption.externalCode.text() == 'R') && (ji[i].startDate.text() > CURRENT_DATE && ji[i].startDate.text() <= endDate_str)) {
			    
            startDate_jobInfo = ji[i].startDate.text();
            String jobLevelDesc_str = ji[i].customString4Nav.cust_Level.externalName.text();
	        ee.appendNode("customString4",[:],jobLevelDesc_str);
	        ee.appendNode("jobTitle",[:],ji[i].jobTitle.text());
	        ee.appendNode("jobCode",[:],ji[i].jobCode.text());
	        ee.appendNode("costCenter",[:],ji[i].costCenter.text());
	        ee.appendNode("costCenterName",[:],ji[i].costCenterNav.FOCostCenter.name.text());
	        ee.appendNode("custBusiness",[:],ji[i].costCenterNav.FOCostCenter.cust_Business.text());
	        ee.appendNode("cust_BusinessArea",[:],ji[i].costCenterNav.FOCostCenter.cust_BusinessArea.text());
	        ee.appendNode("cust_BusinessLine",[:],ji[i].costCenterNav.FOCostCenter.cust_BusinessLine.text());
	        ee.appendNode("cust_Region",[:],ji[i].costCenterNav.FOCostCenter.cust_Region.text());
	        ee.appendNode("cust_ZZOFFICE",[:],ji[i].costCenterNav.FOCostCenter.cust_ZZOFFICENav.PickListValueV2.label_defaultValue.text());
	        ee.appendNode("cust_ZZFEDERAL",[:],ji[i].costCenterNav.FOCostCenter.cust_ZZFEDERAL.text());
	        ee.appendNode("cust_BusinessSubLine",[:],ji[i].costCenterNav.FOCostCenter.cust_BusinessSubLine.text());
	        ee.appendNode("hireSource",[:],ji[i].hireSourceNav.PicklistOption.localeLabel.text());
	        ee.appendNode("customString9",[:],ji[i].customString9Nav.cust_capability.externalName.text());
	        ee.appendNode("departmentCode",[:],ji[i].departmentNav.FODepartment.externalCode.text());
	        ee.appendNode("departmentName",[:],ji[i].departmentNav.FODepartment.name.text());
	        ee.appendNode("employmentType",[:],ji[i].employmentTypeNav.PicklistOption.localeLabel.text());
	        ee.appendNode("customString9Code",[:],ji[i].customString9.text());
	        ee.appendNode("customString10",[:],ji[i].customString10Nav.cust_subcapability.externalName.text());
	        ee.appendNode("customString10Code",[:],ji[i].customString10.text());
	        ee.appendNode("customString21",[:],ji[i].customString21Nav.PicklistOption.localeLabel.text());
	        ee.appendNode("customString7",[:],ji[i].customString7Nav.PicklistOption.localeLabel.text());
	        ee.appendNode("employmeeClass",[:],ji[i].employeeClassNav.PicklistOption.localeLabel.text());
	        //ee.appendNode("departmentCode",[:],ji[i].startDate.text());
	        ee.appendNode("eventReasonCode",[:],ji[i].eventReason.text());
	        ee.appendNode("eventReasonDesc",[:],ji[i].eventReasonNav.FOEventReason.name.text());
	        ee.appendNode("event",[:],ji[i].eventNav.PicklistOption.externalCode.text());
	        ee.appendNode("eventDesc",[:],ji[i].eventNav.PicklistOption.localeLabel.text());
	        ee.appendNode("employeeStatus",[:],ji[i].emplStatusNav.PicklistOption.externalCode.text());
	        ee.appendNode("jobStartDate",[:],ji[i].startDate.text());
	        ee.appendNode("jobEndDate",[:],ji[i].endDate.text());
	        
	        ee.appendNode("EmpTime_startDate",[:],"");
	        ee.appendNode("EmpTime_endtDate",[:],"");
	        
	        if(ji[i].costCenterNav.FOCostCenter.cust_BusinessArea.text() == 'CONSULTING' || ji[i].costCenterNav.FOCostCenter.cust_BusinessArea.text() == 'DC Platforms'){
	            ee.appendNode("ExceptionType",[:],"DC Platforms");
	        }
	        
	        else if(ji[i].costCenterNav.FOCostCenter.cust_BusinessArea.text() == 'CONSULTING' || ji[i].costCenterNav.FOCostCenter.cust_BusinessArea.text() == 'National Consulting'){
	            ee.appendNode("ExceptionType",[:],"National Consulting");
	        }
	        
	        else if(ji[i].customString7Nav.PicklistOption.localeLabel.text() == 'Project Delivery'){
	            ee.appendNode("ExceptionType",[:],"Project Delivery Model");
	        }
	        
	        else if((jobLevelDesc_str == 'ADMIN MANAGING DIRECTOR') || (jobLevelDesc_str == 'CS DIRECTOR - ND') || (jobLevelDesc_str == 'CS MANAGING DIRECTOR') || (jobLevelDesc_str == 'Partner/Principal') || (jobLevelDesc_str == 'XIN-CS MANAGING DIRECTOR')){
	            ee.appendNode("ExceptionType",[:],"PPMD");
	        }
	        
	        else{
	             ee.appendNode("ExceptionType",[:],"");
	        }
	        
	        ee.appendNode("FutureHire_Flag",[:],"X");
	        break;
	        

                }

            }
            

            ee.empJobRelationshipNav.replaceNode {};
            ee.personNav.replaceNode {};
            ee.userNav.replaceNode {};
            ee.jobInfoNav.replaceNode {};
          //  break;
        }
       // break;
        }

        def dat = XmlUtil.serialize(emp);
        message.setBody(dat)

        return message;
}