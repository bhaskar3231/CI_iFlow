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


def String customFunc(String nationalIdNav){
	 
def xmlParser = new XmlSlurper().parseText(nationalIdNav)

// Find all PerNationalId nodes, check cardType, print nationalId if cardType == 'ID'
def ids = []
xmlParser.'**'.findAll { node ->
    node.name() == 'PerNationalId' && node.cardType.text() == 'HETU'
}.each { node ->
    ids << node.nationalId.text()
}
return ids

}
