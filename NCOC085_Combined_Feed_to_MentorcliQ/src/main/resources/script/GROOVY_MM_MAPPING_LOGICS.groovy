import com.sap.it.api.mapping.*;
import com.sap.it.api.mapping.MappingContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.math.RoundingMode;

/* --------Derive Exception Type based on cust_BusinessArea , Job Level Description----------- */


/* Misc1 -- Send "New Hire" if the employee is hired in 4 week. Current week any days between (Sunday to Saturday) + 4 weeks (Only applicable for future hire) */

def String misc1(String flag){
    if(flag.equals("FH"))
      return "New Hire";
    else
      return "";
}


/* Buddy Pool - If an employee is identified as a buddy (Part of Buddy Report), on Buddy Pool column: employee must be tagged with 'Yes' flag, else send 'No' */
def String buddyPool (String propertyName, String personnelNo, String flag, MappingContext context) {
    if(flag.equals("NS") || flag.equals("FH"))
      return "";
    else{
         String UserIDs = context.getProperty(propertyName);
         if (UserIDs.contains(personnelNo))
		    return("Yes");
        else 
            return("No");
    }
}


/* Employee Current Tenure -- Current Date - EmpEmployment -> startDate  e.g. 9.24.(only 2 decimal places) If value = -ve for future hires, pass the values as '0'. */
def String employeeCurrentTenure(String startDate, String currentDate) {
  String tenure= "";
  if(!startDate.equals("")){
         //Parsing the date
        LocalDate dateBefore = LocalDate.parse(startDate);
        LocalDate dateAfter = LocalDate.parse(currentDate);

         //calculating number of days in between
         long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);
         double days = (double) noOfDaysBetween;
         double years;
         


         if (noOfDaysBetween >0){
           years = days/365;
           BigDecimal bigDecimal = new BigDecimal(Double.toString(years));
           bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
           tenure = bigDecimal;
         }
         else if (noOfDaysBetween<=0 )
            tenure="0";
         else
            tenure="";
    }
    else
      return "";

    return tenure;
}

/* GE Rollup Level TIL  -- If the employee is part of the HANA report then populate this field value with 'TIME_IN_LEVEL_COMMON' field value in the HANA Report.. */
def String ge_RollupLevel(String personIdExternal, String propertyName, MappingContext context) {
  String UserIDs = context.getProperty(propertyName);
  if (UserIDs.containsKey(personIdExternal))
	 return UserIDs.get(personIdExternal);
  else
     return "";
}

//Smart Match Eligible - OBA (Yes/No)

def String SME_OBA(String exceptionType){
    if(exceptionType.equals("DC Platforms"))
      return "NA";
    else if(exceptionType.equals("National Consulting"))
      return "NA";
    else if(exceptionType.equals("Project Delivery Model"))
      return "Yes";
    else if(exceptionType.equals("PPMD"))
      return "NA";
    else
      return "Yes";
}

//Smart Match Eligible - Coach (Yes/No)

def String SME_Coach(String exceptionType){
    if(exceptionType.equals("DC Platforms"))
      return "NA";
    else if(exceptionType.equals("National Consulting"))
      return "NA";
    else if(exceptionType.equals("Project Delivery Model"))
      return "No";
    else if(exceptionType.equals("PPMD"))
      return "NA";
    else
      return "Yes";
}

//Requires Approval - OBA (Yes/No)

def String Requires_Approval_OBA(String exceptionType){
    if(exceptionType.equals("DC Platforms"))
      return "NA";
    else if(exceptionType.equals("National Consulting"))
      return "NA";
    else if(exceptionType.equals("Project Delivery Model"))
      return "No";
    else if(exceptionType.equals("PPMD"))
      return "NA";
    else
      return "No";
}

//Requires Approval - Coach (Yes/No)

def String Requires_Approval_COACH(String exceptionType){
    if(exceptionType.equals("DC Platforms"))
      return "NA";
    else if(exceptionType.equals("National Consulting"))
      return "NA";
    else if(exceptionType.equals("Project Delivery Model"))
      return "No";
    else if(exceptionType.equals("PPMD"))
      return "NA";
    else
      return "No";
}