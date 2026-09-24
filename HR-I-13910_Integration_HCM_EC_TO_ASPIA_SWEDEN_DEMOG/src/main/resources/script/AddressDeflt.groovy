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

def String AddressInformationaddressType(String compoundEmployee){
    
    def parsedXml = new XmlSlurper().parseText(compoundEmployee)
    // Filter and collect start_date values for Host address_type
    def hostStartDates = parsedXml.person.address_information.findAll {
        it.address_type.text() == 'Host'
    }.collect {
        it.address_type.text()
    }   

// Return the final value
return hostStartDates ? hostStartDates : ""
}