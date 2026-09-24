import com.sap.gateway.ip.core.customdev.util.Message 
def Message processData(Message message)
 {
 // Get the property "Sequence number"
 def seqNum = message.getProperty("Sequence Number")
 // Convert to integer, increment by 1
 def newSeqNum = (seqNum as Integer) + 1
 // Set the incremented value back to the property
 message.setProperty("Sequence Number", newSeqNum)
 return message
 }