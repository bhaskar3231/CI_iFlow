import com.sap.it.api.mapping.*;







def void processData(String[] externalCode, String[] EmailAddress, Output output, MappingContext context) {
    for (int i = 0; i < externalCode.length; i++) {
        if (externalCode[i] != null && externalCode[i].equals("B")) {
            output.addValue(EmailAddress[i])
            return // Stop after finding the first business email
        }
    }
    // If no business email found, return empty string
    output.addValue("")
}
