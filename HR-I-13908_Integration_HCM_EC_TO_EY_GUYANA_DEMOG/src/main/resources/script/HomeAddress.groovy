import com.sap.it.api.mapping.*;



def String HomeAddressHN(String homeAddress){
def xmlParser = new XmlSlurper().parseText(homeAddress)

// Find all PerNationalId nodes, check cardType, print nationalId if cardType == 'ID'
def ids = []
xmlParser.'**'.findAll { node ->
    node.name() == 'PerAddressDEFLT' && node.addressType.text() == 'home'
}.each { node ->
    ids << node.startDate.text()
}
return ids
}

def String HomeAddressHNS(String homeAddress){
def xmlParser = new XmlSlurper().parseText(homeAddress)

// Find all PerNationalId nodes, check cardType, print nationalId if cardType == 'ID'
def ids = []
xmlParser.'**'.findAll { node ->
    node.name() == 'PerAddressDEFLT' && node.addressType.text() == 'home'
}.each { node ->
    def address1 = node.address1.text()
    def address2 = node.address2.text()
    def address3 = node.address3.text()
    ids << "${address1} ${address2} ${address3}".trim()
}
return ids
}





def String HomeAddressCity(String homeAddress){
def xmlParser = new XmlSlurper().parseText(homeAddress)

// Find all PerNationalId nodes, check cardType, print nationalId if cardType == 'ID'
def ids = []
xmlParser.'**'.findAll { node ->
    node.name() == 'PerAddressDEFLT' && node.addressType.text() == 'home'
}.each { node ->
    ids << node.city.text()
}
return ids

}



def String HomeAddressPostalCode(String homeAddress){
def xmlParser = new XmlSlurper().parseText(homeAddress)

// Find all PerNationalId nodes, check cardType, print nationalId if cardType == 'ID'
def ids = []
xmlParser.'**'.findAll { node ->
    node.name() == 'PerAddressDEFLT' && node.addressType.text() == 'home'
}.each { node ->
    ids << node.zipCode.text()
}
return ids

}




def String HomeAddressCountry(String homeAddress){
def xmlParser = new XmlSlurper().parseText(homeAddress)

// Find all PerNationalId nodes, check cardType, print nationalId if cardType == 'ID'
def ids = []
xmlParser.'**'.findAll { node ->
    node.name() == 'PerAddressDEFLT' && node.addressType.text() == 'home'
}.each { node ->
    ids << node.country.text()
}
return ids

}