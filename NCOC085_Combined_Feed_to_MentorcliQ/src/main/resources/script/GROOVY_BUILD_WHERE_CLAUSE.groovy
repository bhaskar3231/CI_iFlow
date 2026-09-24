/* 
----------------------------------------------------
PURPOSE -- This groovy script is used to build the filter clause of OData query based on the external parameters provided
   - Active, Future Dated Terminations, Future Dated Hires, Leave of Absences and No-Show employee(s) from :
         Consulting, Tax, Audit and Advisory. Member Firm Business: Consulting (20000001), Tax (20000004), Audit & Assurance (20000000) and Risk and Financial Advisory (20000003)
    The SF data is filtered based on the filter generated dynamically.
----------------------------------------------------------
*/

import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Calendar;

def Message processData(Message message) {
	//Body	
	def body = message.getBody();
	def all_props= message.getProperties();
	
	//Read external parameters
	def future_hire_rehire_no_weeks=all_props.get("FUTURE_HIRE_REHIRE_NO_WEEKS");
	def future_sep_no_of_days=all_props.get("FUTURE_SEPARATION_NO_OF_DAYS");
	def country_of_company=all_props.get("COUNTRY_OF_COMPANY");
    def exclude_emp_type=all_props.get("EXC_PERSONNEL_TYPE");
    def exclude_company_codes=all_props.get("EXC_COMPANY_CODE");
    def employee_ids=all_props.get("PERSON_ID");
    def member_firm_business=all_props.get("MEMBER_FIRM_BUSINEESS");
	def events=all_props.get("EVENT");
	def empl_status=all_props.get("EMPLOYEE_STATUS");
	def future_hire_or_rehire_event = all_props.get("FUTURE_HIRE_REHIRE_EVENT");
	def future_separation_status = all_props.get("FUTURE_SEPARATION_STATUS");
	
	def last_run_date85=all_props.get("LAST_RUN_DATE_85");
	def last_run_date_ext=all_props.get("LAST_RUN_DATE_EXT");
	def current_date=all_props.get("TODAY");
	
       
    
    // Find the date of last Saturday
    Calendar startDate = Calendar.getInstance()
    startDate.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY)
    startDate.add(Calendar.WEEK_OF_YEAR, -1)
    
   
    // Set the end date to four weeks from the current date
    Calendar endDate = Calendar.getInstance()
    endDate.add(Calendar.WEEK_OF_YEAR, future_hire_rehire_no_weeks.toInteger())
    
    // Format the start date and end date as yyyy-MM-dd
    def hire_startDate = startDate.getTime().format("yyyy-MM-dd");
    def hire_endDate = endDate.getTime().format("yyyy-MM-dd");
        
    //120 days from today's date for future termination
    def current_date_plus_120_days = new Date().plus(Integer.parseInt(future_sep_no_of_days)).format("yyyy-MM-dd");
    
    
    message.setProperty("FUTURE_SEPARATION_DATE",current_date_plus_120_days);
	message.setProperty("hire_startDate",hire_startDate);
	message.setProperty("hire_endDate",hire_endDate);
	
	
	
	
	def last_run_date="";
	if(last_run_date_ext.equals(""))
	    last_run_date=last_run_date85;
	else
	    last_run_date=last_run_date_ext;
	    
	message.setProperty("last_run_date",last_run_date);
	
	//Declare variables
	def query_asoftoday_filter = "";
	def query_future_hires_terms_filter = "";

	
	
   
	
    /* --------Check for external parameter value - Member Firms ------*/
    if(member_firm_business.trim().length() != 0){
       member_firm_business=member_firm_business.trim();
       member_firm_business=member_firm_business.replace(",","','");
       query_asoftoday_filter=query_asoftoday_filter+"(jobInfoNav/division in '"+member_firm_business+"')";
     }
     
      /* --------Check for external parameter value - Conuntry of Company ------*/
    if(country_of_company.trim().length() != 0){
       country_of_company=country_of_company.trim();
       country_of_company=country_of_company.replace(",","','");
       query_asoftoday_filter=query_asoftoday_filter+" and (jobInfoNav/countryOfCompany in '"+country_of_company+"')";
     }
     
     /* ----Filter based on Employee Type - Exclusion --------*/
    if(exclude_emp_type.contains(",")){
       String[] eTypes=exclude_emp_type.split(",",-1);
       def etype_filter=new StringBuffer();
       for(int v_index=0;v_index<eTypes.size();v_index++){
            etype_filter=etype_filter+"jobInfoNav/employmentTypeNav/externalCode ne '"+eTypes[v_index]+"'";
            
            if(v_index<(eTypes.size()-1))
               etype_filter=etype_filter+" and ";
             
       }
      query_asoftoday_filter=query_asoftoday_filter+" and ("+etype_filter.toString()+(")");
    }else{
        query_asoftoday_filter=query_asoftoday_filter+" and (jobInfoNav/employmentTypeNav/externalCode ne '"+exclude_emp_type+"')"; 
    }
    
    
    /* ----Filter based on Company codes - Exclusion --------*/
    
    if(exclude_company_codes.length() != 0){
       exclude_company_codes=exclude_company_codes.trim();
    }
    if(exclude_company_codes.contains(",")){
       String[] eTypes=exclude_company_codes.split(",",-1);
       def etype_filter=new StringBuffer();
       for(int v_index=0;v_index<eTypes.size();v_index++){
            etype_filter=etype_filter+"jobInfoNav/company ne '"+eTypes[v_index]+"'";
            
            if(v_index<(eTypes.size()-1))
               etype_filter=etype_filter+" and ";
             
       }
      query_asoftoday_filter=query_asoftoday_filter+" and ("+etype_filter.toString()+(")");
     }else{
      query_asoftoday_filter=query_asoftoday_filter+" and (jobInfoNav/company ne '"+exclude_company_codes+"')"; 
     }
    
	
    query_future_hires_terms_filter=query_asoftoday_filter;
    
    future_separation_status = future_separation_status.trim().replace(",", "','");
    future_hire_or_rehire_event =  future_hire_or_rehire_event.trim().replace(",", "','");
    
    query_future_hires_terms_filter=query_future_hires_terms_filter+" and (((jobInfoNav/emplStatusNav/externalCode in '"+future_separation_status+"') and (endDate gt '"+last_run_date+"' and endDate le '"+current_date_plus_120_days+"')) or ((jobInfoNav/eventNav/externalCode in '"+future_hire_or_rehire_event+"') and (jobInfoNav/startDate gt '"+hire_startDate+"' and jobInfoNav/startDate le '"+hire_endDate+"')))";
    
    /*query_future_hires_terms_filter=query_future_hires_terms_filter+" and (((jobInfoNav/emplStatusNav/externalCode in '"+future_separation_status+"') and (endDate gt '"+last_run_date+"' and endDate le '"+current_date_plus_120_days+"')) or (originalStartDate ge '"+hire_startDate+"' and originalStartDate le '"+hire_endDate+"'))";*/
    
   
    /* --------Check for external parameter value - Employee Status------*/
    if(empl_status.trim().length() != 0){
       empl_status=empl_status.trim();
       empl_status=empl_status.replace(",","','");
       query_asoftoday_filter=query_asoftoday_filter+" and (jobInfoNav/emplStatusNav/externalCode in '"+empl_status+"'";
     }
    
      /* --------Check for external parameter value - Employee Status------*/
    if(events.trim().length() != 0){
       events=events.trim();
       events=events.replace(",","','");
       query_asoftoday_filter=query_asoftoday_filter+" or jobInfoNav/eventNav/externalCode in '"+events+"')";
     }
   
    
     /*--------Check for external parameter - EMPLOYEE_ID ---------*/
     if(employee_ids.trim().length() != 0){
       employee_ids=employee_ids.trim();
       employee_ids=employee_ids.replace(",","','");
       query_asoftoday_filter="("+query_asoftoday_filter+") and (personIdExternal in '"+employee_ids+"')";
       query_future_hires_terms_filter = "("+query_future_hires_terms_filter+") and (personIdExternal in '"+employee_ids+"')";
       message.setProperty("PERSON_IDS_FLAG","Y");
     }else
         message.setProperty("PERSON_IDS_FLAG","N");
         
    query_asoftoday_filter="("+query_asoftoday_filter+")";
    query_future_hires_terms_filter="("+query_future_hires_terms_filter+")";
    
    message.setProperty("QUERY_AS_OF_TODAY_FILTER",query_asoftoday_filter);
    message.setProperty("QUERY_FUTURE_HIRE_TERM_FILTER",query_future_hires_terms_filter);
    
    
    
   return message;
    
}   
         
         
         