/*import com.sap.gateway.ip.core.customdev.util.Message
import groovy.util.Node
import groovy.util.XmlParser
import groovy.util.XmlNodePrinter
import java.io.StringWriter
import java.io.PrintWriter*/

import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {

    def payload = message.getBody(String)
    def root = new XmlParser().parseText(payload)

    // NON‑effective‑dated portlets only
    def nonEffectivePortlets = ['email_information', 'national_id_card']

    // Memory to prevent duplicate dates
    def processedKeys = new HashSet<String>()

    root.CompoundEmployee.each { emp ->

        def person = emp.person[0]
        def personId = person.person_id?.text()
        def finalStartNode = person.finalstartdate?.getAt(0)

        // Skip if already populated (effective‑dated record)
        if (!finalStartNode || finalStartNode.text()?.trim()) {
            return
        }

        // Find non‑effective CHANGE portlets
        emp.depthFirst().findAll { Node node ->
            node.action?.text() == 'CHANGE' &&
            node.last_modified_on?.text() &&
            nonEffectivePortlets.contains(node.name())
        }.each { Node changePortlet ->

            def date = changePortlet.last_modified_on.text()
            def key = "${personId}|${changePortlet.name()}|${date}"

            // Skip if already written once
            if (processedKeys.contains(key)) {
                return
            }

            processedKeys.add(key)
            finalStartNode.value = date
        }
    }

    // Write XML back
    def sw = new StringWriter()
    def printer = new XmlNodePrinter(new PrintWriter(sw))
    printer.preserveWhitespace = true
    printer.print(root)

    message.setBody(sw.toString())
    return message
}


/*def Message processData(Message message) {

    // Read payload again (required if this is a new script step)
    def payload = message.getBody(String)
    def parser = new XmlParser()
    def root = parser.parseText(payload)
    
    // Configuration: Declare NON-effective-dated portlets here
    def nonEffectivePortlets = 'national_id_card,email_information,person'

    def nonEffectivePortletSet = nonEffectivePortlets.split(',').collect { it.trim() }.toSet()

    // Populate finalstartdate ONLY for declared portlets

    root.CompoundEmployee.each { emp ->

        def person = emp.person[0]
        def finalStartNode = person.finalstartdate?.getAt(0)

        // Proceed only if finalstartdate exists and is empty
        if (finalStartNode && !finalStartNode.text()?.trim())
        {
            def changePortlet = emp.depthFirst().find { Node node ->
                node.action?.text() == 'CHANGE' &&
                node.last_modified_on?.text() &&
                nonEffectivePortletSet.contains(node.name())
            }

            if (changePortlet)
            {
                person.finalstartdate[0].value = changePortlet.last_modified_on.text()
            }
        }
    }

    // Write XML back to message
    def sw = new StringWriter()
    def printer = new XmlNodePrinter(new PrintWriter(sw))
    printer.preserveWhitespace = true
    printer.print(root)

    message.setBody(sw.toString())
    return message
}*/
