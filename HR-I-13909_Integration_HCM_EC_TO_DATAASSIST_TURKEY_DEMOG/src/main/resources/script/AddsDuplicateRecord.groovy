import com.sap.gateway.ip.core.customdev.util.Message
import groovy.util.XmlParser
import groovy.util.XmlNodePrinter
import java.io.StringWriter
import java.io.PrintWriter
import java.io.Reader

def Message processData(Message message) {

    Reader reader = message.getBody(Reader)
    def root = new XmlParser(false, false).parse(reader)

    def nonEffectivePortlets = 'email_information,national_id_card,person,employment_information'
    def nonEffectivePortletSet = nonEffectivePortlets.split(',').collect { it.trim() }.toSet()

    def duplicates = []

    root.CompoundEmployee.each { emp ->

        def personNode = emp.person[0]

        //PERSON‑LEVEL CHANGE (NON‑EFFECTIVE)
        if (personNode.action?.text() == 'CHANGE') {
            def dup = emp.clone()
            def dupPerson = dup.person[0]

            dupPerson.children().removeAll { it.name() == 'finalstartdate' }

            def finalStartDate = personNode.last_modified_on?.text()
            if (finalStartDate?.contains('T')) {
                finalStartDate = finalStartDate.substring(0, 10)
            }

            dupPerson.appendNode('finalstartdate', finalStartDate ?: '')
            duplicates << dup
        }

        // ALL OTHER PORTLETS
        emp.depthFirst().findAll { node ->
            node.name() != 'person' &&
            node.action?.text() == 'CHANGE'
        }.each { changedNode ->

            def dup = emp.clone()
            def dupPerson = dup.person[0]

            dupPerson.children().removeAll { it.name() == 'finalstartdate' }

            def finalStartDate = null

            // NON‑EFFECTIVE‑DATED PORTLETS
            if (nonEffectivePortletSet.contains(changedNode.name())) {
                finalStartDate = changedNode.last_modified_on?.text()
            }
            // EFECTIVE‑DATED PORTLETS
            else if (changedNode.start_date?.text()) {
                finalStartDate = changedNode.start_date.text()
            }
            else if (changedNode.effectiveStartDate?.text()) {
                finalStartDate = changedNode.effectiveStartDate.text()
            }
            else if (changedNode.parent()?.effectiveStartDate?.text()) {
                finalStartDate = changedNode.parent().effectiveStartDate.text()
            }

            if (finalStartDate?.contains('T')) {
                finalStartDate = finalStartDate.substring(0, 10)
            }

            dupPerson.appendNode('finalstartdate', finalStartDate ?: '')
            duplicates << dup
        }
    }

    // Replace originals
    root.children().removeAll { it.name() == 'CompoundEmployee' }
    duplicates.each { root.append(it) }

    def sw = new StringWriter()
    def printer = new XmlNodePrinter(new PrintWriter(sw))
    printer.preserveWhitespace = true
    printer.print(root)

    message.setBody(sw.toString())
    return message
}