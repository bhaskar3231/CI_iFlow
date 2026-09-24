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

/*def String HomeAddressHN(String Home){
def xmlParser = new XmlSlurper().parseText(Home)

// Find all PerNationalId nodes, check cardType, print nationalId if cardType == 'ID'
def ids = []
xmlParser.'**'.findAll { node ->
    node.name() == 'PerAddressDEFLT' && node.addressType.text() == 'home'
}.each { node ->
    ids << node.address2.text()
}
return ids
}*/

def String HomeAddressHouseNo(String Home){
def xmlParser = new XmlSlurper().parseText(Home)

// Find all PerNationalId nodes, check cardType, print nationalId if cardType == 'ID'
def ids = []
xmlParser.'**'.findAll { node ->
    node.name() == 'PerAddressDEFLT' && node.addressType.text() == 'home'
}.each { node ->
    ids << node.address2.text()
}
return ids
}

def String HomeAddressHNS(String Home){
def xmlParser = new XmlSlurper().parseText(Home)

// Find all PerNationalId nodes, check cardType, print nationalId if cardType == 'ID'
def ids = []
xmlParser.'**'.findAll { node ->
    node.name() == 'PerAddressDEFLT' && node.addressType.text() == 'home'
}.each { node ->
    ids << node.address1.text()
}
return ids
}


def String HomeAddressExtraAddressLine(String Home){
def xmlParser = new XmlSlurper().parseText(Home)

// Find all PerNationalId nodes, check cardType, print nationalId if cardType == 'ID'
def ids = []
xmlParser.'**'.findAll { node ->
    node.name() == 'PerAddressDEFLT' && node.addressType.text() == 'home'
}.each { node ->
    ids << node.address4.text()
}
return ids
}




def String HomeAddressCity(String Home){
def xmlParser = new XmlSlurper().parseText(Home)

// Find all PerNationalId nodes, check cardType, print nationalId if cardType == 'ID'
def ids = []
xmlParser.'**'.findAll { node ->
    node.name() == 'PerAddressDEFLT' && node.addressType.text() == 'home'
}.each { node ->
    ids << node.city.text()
}
return ids
}



def String HomeAddressPostalCode(String Home){
def xmlParser = new XmlSlurper().parseText(Home)

// Find all PerNationalId nodes, check cardType, print nationalId if cardType == 'ID'
def ids = []
xmlParser.'**'.findAll { node ->
    node.name() == 'PerAddressDEFLT' && node.addressType.text() == 'home'
}.each { node ->
    ids << node.zipCode.text()
}
return ids
}



def String HomeAddressCountry(String Home){
def xmlParser = new XmlSlurper().parseText(Home)

// Find all PerNationalId nodes, check cardType, print nationalId if cardType == 'ID'
def ids = []
xmlParser.'**'.findAll { node ->
    node.name() == 'PerAddressDEFLT' && node.addressType.text() == 'home'
}.each { node ->
    ids << node.country.text()
}
return ids
}