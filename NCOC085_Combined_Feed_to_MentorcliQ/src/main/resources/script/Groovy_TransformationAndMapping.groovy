/*
 The integration developer needs to create the method processData 
 This method takes Message object of package com.sap.gateway.ip.core.customdev.util 
which includes helper methods useful for the content developer:
The methods available are:
    public java.lang.Object getBody()
	public void setBody(java.lang.Object exchangeBody)
    public java.util.Map<java.lang.String,java.lang.Object> getHeaders()
    public void setHeaders(java.util.Map<java.lang.String,java.lang.Object> exchangeHeaders)
    public void setHeader(java.lang.String name, java.lang.Object value)
    public java.util.Map<java.lang.String,java.lang.Object> getProperties()
    public void setProperties(java.util.Map<java.lang.String,java.lang.Object> exchangeProperties) 
    public void setProperty(java.lang.String name, java.lang.Object value)
    public java.util.List<com.sap.gateway.ip.core.customdev.util.SoapHeader> getSoapHeaders()
    public void setSoapHeaders(java.util.List<com.sap.gateway.ip.core.customdev.util.SoapHeader> soapHeaders) 
       public void clearSoapHeaders()
 */
import com.sap.gateway.ip.core.customdev.util.Message;
import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.xml.*

def Message processData(Message message) {

    //Body 
        def body = message.getBody();

       def parser = new XmlParser();

       def get_properties = message.getProperties();

        //def emp = parser.parseText(body);
        def emp = new XmlParser().parseText(body);
        for(ee in emp.EmpEmployment) {
            def ejr = ee.empJobRelationshipNav.EmpJobRelationships;
            def en = ee.personNav.PerPerson.emailNav.PerEmail;
            def pn = ee.personNav.PerPerson.personalInfoNav.PerPersonal;
            def ji = ee.jobInfoNav.EmpJob;

            for (i = 0; i < ejr.size(); i++) {
                if(ejr[i].endDate.text() == '9999-12-31T00:00:00.000' && ejr[i].relationshipTypeNav.PicklistOption.localeLabel.text() == 'Coach'){
	        ee.appendNode("CounselorFullName",[:],ejr[i].relUserNav.User.defaultFullName.text());
	        ee.appendNode("CounseloruserId",[:],ejr[i].relUserNav.User.userId.text());
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

            for (i = 0; i < en.size(); i++) {
              //  if(pn.emailNav.PerEmail.emailTypeNav.PicklistOption.externalCode.text() == 'B'){
             if(pn[0].endDate.text() == '9999-12-31T00:00:00.000'){
	        ee.appendNode("firstName",[:],pn.firstName.text());
	        ee.appendNode("lastName",[:],pn.lastName.text());
	        ee.appendNode("salutation",[:],pn.salutation.text());
	        break;
	    }

            }

            ee.appendNode("EmpTime_startDate",[:],ee.userNav.User.userIdOfEmployeeTimeNav.startDate.text());
	        ee.appendNode("EmpTime_endtDate",[:],ee.userNav.User.userIdOfEmployeeTimeNav.endDate.text());

             for (i = 0; i < ji.size(); i++) {
              //  if(pn.emailNav.PerEmail.emailTypeNav.PicklistOption.externalCode.text() == 'B'){

	        ee.appendNode("customString4Nav",[:],ji[i].customString4Nav.cust_Level.externalName.text());
	        ee.appendNode("jobTitle",[:],ji[i].jobTitle.text());
	        ee.appendNode("jobCode",[:],ji[i].jobCode.text());
	        ee.appendNode("costCenter",[:],ji[i].costCenter.text());
	        ee.appendNode("costCenterName",[:],ji[i].costCenterNav.FOCostCenter.name.text());
	        ee.appendNode("custBusiness",[:],ji[i].costCenterNav.FOCostCenter.cust_Business.text());
	        ee.appendNode("cust_BusinessArea",[:],ji[i].costCenterNav.FOCostCenter.cust_BusinessArea.text());
	        ee.appendNode("cust_BusinessLine",[:],ji[i].costCenterNav.FOCostCenter.cust_BusinessLine.text());
	        ee.appendNode("cust_Region",[:],ji[i].costCenterNav.FOCostCenter.cust_Region.text());
	        ee.appendNode("cust_ZZOFFICE",[:],ji[i].costCenterNav.FOCostCenter.cust_ZZOFFICE.text());
	        ee.appendNode("cust_ZZFEDERAL",[:],ji[i].costCenterNav.FOCostCenter.cust_ZZFEDERAL.text());
	        ee.appendNode("cust_BusinessSubLine",[:],ji[i].costCenterNav.FOCostCenter.cust_BusinessSubLine.text());
	        ee.appendNode("hireSource",[:],ji[i].hireSourceNav.PicklistOption.externalCode.text());
	        ee.appendNode("customString9",[:],ji[i].customString9Nav.cust_capability.externalName.text());
	        ee.appendNode("departmentCode",[:],ji[i].department.text());
	        ee.appendNode("departmentName",[:],ji[i].departmentNav.FODepartment.name.text());
	        ee.appendNode("employmentType",[:],ji[i].employmentTypeNav.PicklistOption.localeLabel.text());
	        ee.appendNode("customString9Code",[:],ji[i].customString9.text());
	        ee.appendNode("customString10Code",[:],ji[i].customString10.text());
	        ee.appendNode("customString21",[:],ji[i].customString21Nav.PicklistOption.localeLabel.text());
	        ee.appendNode("customString7",[:],ji[i].customString7Nav.PicklistOption.localeLabel.text());
	        ee.appendNode("employmeeClass",[:],ji[i].employeeClassNav.PicklistOption.localeLabel.text());
	        ee.appendNode("departmentCode",[:],ji[i].startDate.text());
	        ee.appendNode("eventReason",[:],ji[i].eventReasonNav.FOEventReason.eventNav.PicklistOption.externalCode.text());
	        ee.appendNode("eventReasonName",[:],ji[i].eventReasonNav.FOEventReason.eventNav.PicklistOption.localeLabel.text());
	        ee.appendNode("event",[:],ji[i].eventNav.PicklistOption.externalCode.text());
	        ee.appendNode("employeeStatus",[:],ji[i].emplStatusNav.PicklistOption.externalCode.text());
	        ee.appendNode("jobStartDate",[:],ji[i].startDate.text());
	        ee.appendNode("jobEndDate",[:],ji[i].endDate.text());


            }

            ee.empJobRelationshipNav.replaceNode {};
            ee.personNav.replaceNode {};
            ee.userNav.replaceNode {};
            ee.jobInfoNav.replaceNode {};

       /*     for (i = 1; i < ji.size(); i++) {
                ji[i].parent().remove(ji[i]);
            }
            for (i = 1; i < ci.size(); i++) {
                ci[i].parent().remove(ci[i]);
            }
            for(i = 1; i < pi.size(); i++) {
                pi[i].parent().remove(pi[i]);
            }
            for(i = 0; i < ai.size(); i++) {
                if(ai[i].country.text() != 'USA'){
                ai[i].parent().remove(ai[i]);
                }
            }*/
        }

        def dat = XmlUtil.serialize(emp);
        message.setBody(dat)

        return message;
}