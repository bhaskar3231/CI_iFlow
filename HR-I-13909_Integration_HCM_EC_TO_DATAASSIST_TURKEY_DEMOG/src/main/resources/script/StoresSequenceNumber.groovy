import com.sap.gateway.ip.core.customdev.util.Message;
def Message processData(Message message) {
    // Body
    def pMap         = message.getProperties();

    // Properties 
    def RunSequenceNumber = pMap.get("TermSequenceNumber");
     def SequenceNumber = RunSequenceNumber.toInteger() + 1
     
    def PaddedSequenceNumber = String.format("%010d", SequenceNumber);
    
    message.setProperty("TermSequenceNumber", PaddedSequenceNumber);
    return message;
    
}