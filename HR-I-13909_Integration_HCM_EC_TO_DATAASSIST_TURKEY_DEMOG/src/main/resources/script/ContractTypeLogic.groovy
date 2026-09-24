import com.sap.it.api.mapping.*;

/*Add MappingContext parameter to read or set headers and properties
def String customFunc1(String P1,String P2,MappingContext context) {
         String value1 = context.getHeader(P1);
         String value2 = context.getProperty(P2);
         return value1+value2;
}

Add Output parameter to assign the output value.
def void custFunc2(String[] is,String[] ps, Output output, MappingContext context) {
        String value1 = context.getHeader(is[0]);
        String value2 = context.getProperty(ps[0]);
        output.addValue(value1);
        output.addValue(value2);
}*/

def String ContractType(String EmplStatus, String isFullTimeEmployee, String EmployeeClass) { 
  
  if(EmplStatus && EmplStatus == "R"){
    return "TR-RT"
  }
  else if(isFullTimeEmployee && isFullTimeEmployee == "N"){
    return "TR00"
  }
  else if(EmployeeClass && (EmployeeClass == "1" || EmployeeClass == "8")){
    return "TR-01"
  }
  else if(EmployeeClass && EmployeeClass == "6"){
    return "TR-02"
  }
   else if(EmployeeClass && EmployeeClass == "2"){
    return "TR-03"
  }
  else if(EmployeeClass && EmployeeClass == "3"){
    return "TR-04"
  }
  return ""
  
}