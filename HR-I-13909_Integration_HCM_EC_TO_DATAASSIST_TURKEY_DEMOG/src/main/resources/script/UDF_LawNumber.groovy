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

def String customFunc(String externalCode, String emplStatus){
    def LawNumber = ""
    if((externalCode != "" || emplStatus != "") && (externalCode == '1' ||  externalCode == '6' ||  externalCode == '8' ||  externalCode == '2' ||  externalCode == 'C') && (emplStatus == 'A' || emplStatus == 'D' || emplStatus == 'U' || emplStatus == 'P')){
        return "5510"
    }
    else{
        if(emplStatus != "" && emplStatus == "R"){
            return "0000"
        }
    }
	return LawNumber 
}