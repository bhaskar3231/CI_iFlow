
import com.sap.gateway.ip.core.customdev.util.Message
import groovy.xml.MarkupBuilder
import groovy.util.XmlSlurper



def Message processData(Message message) {
    // Get the XML payload
    def body = message.getBody(String)
    def xml = new XmlSlurper().parseText(body)

    // Assuming the records are under a parent node, e.g., <Root><Record>...</Record></Root>
    def records = xml.Record

    // Sort records by Change_Effective_Date in ascending order
    def sortedRecords = records.sort { a, b ->
        Date dateA = Date.parse("dd/MM/yyyy", a.Change_Effective_Date.text())
        Date dateB = Date.parse("dd/MM/yyyy", b.Change_Effective_Date.text())
        return dateA <=> dateB  // Ascending order
    }

    // Build new XML with sorted records
    def writer = new StringWriter()
    def builder = new MarkupBuilder(writer)
    builder.Root {
        sortedRecords.each { rec ->
            Record {
                rec.children().each { child ->
                    "${child.name()}"(child.text())
                }
            }
        }
    }

    message.setBody(writer.toString())
    return message
}
