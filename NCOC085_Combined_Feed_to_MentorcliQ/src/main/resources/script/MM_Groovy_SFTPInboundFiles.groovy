import com.sap.it.api.mapping.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.DecimalFormat;



//To convert the exponential person id values to their repective proper formatted numeric values.
//For eg: 1.2345E4 is converted to 12345
def String exponentToNumeric_Convert(String personId, MappingContext context){
    
    if(personId != ""){
       BigDecimal myNumber = new BigDecimal(personId);
        
        double myDouble = myNumber.doubleValue();
        NumberFormat formatter = new DecimalFormat("#.##########");
        
        return formatter.format(myDouble);
        //return(personId);
    }
    else {
        return("");
    }
}