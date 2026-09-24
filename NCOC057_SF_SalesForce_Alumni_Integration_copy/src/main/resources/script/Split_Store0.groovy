import com.sap.it.api.mapping.*;

def void addContextChange(String[] input, Output output, MappingContext context) { 
    def item=input[0].split('-')[0]	
    output.addValue(item);   
}