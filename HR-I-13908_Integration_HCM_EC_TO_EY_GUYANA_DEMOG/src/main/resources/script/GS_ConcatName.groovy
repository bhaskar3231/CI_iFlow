import com.sap.it.api.mapping.*; 
def void ConcatNameFields(String[] FirstName, String[] MiddleName, String[] LastName, Output output, MappingContext context) 
{
    String a1 = FirstName != null && FirstName.length > 0 ? FirstName[0] : null;
    String a2 = MiddleName != null && MiddleName.length > 0 ? MiddleName[0] : null;
    String a3 = LastName != null && LastName.length > 0 ? LastName[0] : null; 
    def fields = [a1, a2, a3]; 
    def presentFields = fields.findAll { it != null && it.trim() }; 
    def result = presentFields.join(' '); 
    output.addValue(result);
}