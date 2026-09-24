import com.sap.it.api.mapping.*;
import com.sap.it.api.mapping.MappingContext;
import com.sap.gateway.ip.core.customdev.util.Message;


//Concatenate code with the description
def String ConcatFields (String fullName, String userName, MappingContext context) {
	String AfterConcat = "";
	//Check if both code and description are not null - Concatenate code and description with hyphen between them
	if ((fullName != "") && (userName != "")) {
	    AfterConcat = fullName.concat(" (").concat(userName).concat(")");
			return(AfterConcat);
	    
	}
		//Check if code is not null and description is null - Use only code for mapping
		 else if ((fullName != "") && (userName == "")) {
			return(fullName);
	}
	//Check if code is null and description is not null - Use only description for mapping
	else if ((fullName == "") && (userName != "")) {
			return(userName);
	}
	//Default: Both parameter values are null - Pass null value for mapping
	else {
	    return("");
	}
}