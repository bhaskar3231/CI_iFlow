import com.sap.it.api.mapping.*; 
def void ConcatAddressFields(String[] address1, String[] address2, String[] address3, Output output, MappingContext context) 
{
    String a1 = address1 != null && address1.length > 0 ? address1[0] : null;
    String a2 = address2 != null && address2.length > 0 ? address2[0] : null;
    String a3 = address3 != null && address3.length > 0 ? address3[0] : null; 
    def fields = [a1, a2, a3]; 
    def presentFields = fields.findAll { it != null && it.trim() }; 
    def result = presentFields.join(' '); 
    output.addValue(result);
}