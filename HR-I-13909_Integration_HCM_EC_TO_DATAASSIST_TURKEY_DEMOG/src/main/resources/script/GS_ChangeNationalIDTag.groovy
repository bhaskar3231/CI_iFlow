import com.sap.gateway.ip.core.customdev.util.Message

Message processData(Message message) {

    Reader reader = message.getBody(Reader)

    def root = new XmlSlurper().parse(reader)

    // Iterate over each person independently
    root.CompoundEmployee.person.each { person ->

        def cards = person.national_id_card

        cards.each { card ->

            def action   = card.action.text()
            def cardType = card.card_type.text()
            def natId    = card.national_id.text()

            // GUARD: DELETE is never modified
            if (action == 'DELETE') {
                return
            }

            // Handle INSERT only
            if (action == 'INSERT') {

                // Find paired DELETE inside SAME person
                def deletePair = cards.find { other ->
                    other.action.text() == 'DELETE' &&
                    other.card_type.text() == cardType
                }

                // If DELETE exists and national_id differs → CHANGE
                if (deletePair && deletePair.national_id.text() != natId) {
                    card.action.replaceBody('CHANGE')
                }
            }
        }
    }

    // Serialize back to XML
    message.setBody(groovy.xml.XmlUtil.serialize(root))
    return message
}