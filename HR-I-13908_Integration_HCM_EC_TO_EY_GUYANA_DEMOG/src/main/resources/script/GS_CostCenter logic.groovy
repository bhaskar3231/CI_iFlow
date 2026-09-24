import com.sap.it.api.mapping.*;

def String customFunc(String employeeClass, String customString66, String externalCode){
    if((employeeClass != "") && (employeeClass == "2" || employeeClass == "3" || employeeClass == "C" || employeeClass == "D")){
        return customString66
    }
    else{
        return externalCode
    }
	
}