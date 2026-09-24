import com.sap.gateway.ip.core.customdev.util.Message;
def Message processData(Message message) {
    // Get the body as a String
    def body = message.getBody(java.lang.String)
    def processedBody = body.readLines().collect { line ->
        line.endsWith(';') ? line : line + ';'
    }.join('\n')
    // Set the processed body back to the message
    message.setBody(processedBody)
    return message
}