import com.sap.gateway.ip.core.customdev.util.Message
import groovy.util.XmlParser


def Message processData(Message message) {
    // Get the property value from Content Modifier
    def ChangeEffectiveDateValue = message.getProperty("ChangeEffectiveDate")

    // Get the current XML body as String
    def body = message.getBody(java.lang.String) as String

    // Parse XML
    def root = new XmlParser().parseText(body)

    // Define a closure for recursive processing
    def appendChangeEffectiveDateToEmpEmployment
    appendChangeEffectiveDateToEmpEmployment = { node ->
        if (node.name() == 'EmpEmployment') {
            node.appendNode('ChangeEffectiveDate', ChangeEffectiveDateValue)
        }
        node.children().findAll { it instanceof Node }.each { appendChangeEffectiveDateToEmpEmployment(it) }
    }

    // Start the recursion from the root
    appendChangeEffectiveDateToEmpEmployment(root)

    // Convert back to XML string
    def sw = new StringWriter()
    def xmlNodePrinter = new XmlNodePrinter(new PrintWriter(sw))
    xmlNodePrinter.setPreserveWhitespace(true)
    xmlNodePrinter.print(root)

    // Set the new body
    message.setBody(sw.toString())
    return message
}