import com.sap.gateway.ip.core.customdev.util.Message;
def Message processData(Message message) {
    // Body
    def pMap         = message.getProperties();

    // Properties 
    def RunSequenceNumber = pMap.get("DependentSequenceNumber");
     def SequenceNumber = RunSequenceNumber.toInteger() + 1
     
    def PaddedSequenceNumber = String.format("%010d", SequenceNumber);
    
    message.setProperty("DependentSequenceNumber", PaddedSequenceNumber);
    return message;
    
}