import com.sap.it.api.mapping.*;
//import com.sap.it.api.mapping.MappingContext;
//import com.sap.gateway.ip.core.customdev.util.Message;


def String ExtractCode(String CCfield){
    String arg1 ="";
    String f = CCfield.contains("-");
   if(CCfield != "" && f == "true"){
         arg1 = CCfield.substring(0, CCfield.lastIndexOf("-"))
         //arg1 = CCfield
    }
   else {
        arg1 = CCfield
    }
	return arg1
}

def String ExtractDesc(String CCfield){
    String arg1 ="";
    String f = CCfield.contains("-");
    if(CCfield != "" && f == "true"){
        arg1 = CCfield.substring(CCfield.lastIndexOf("-") + 1)
    }
    else {
        arg1 = CCfield
    }
	return arg1 
}