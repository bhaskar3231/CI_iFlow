
import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
def Message processData(Message message) {
    //Body
       def xml = message.getBody(String.class);
      def completeXml = new XmlSlurper().parseText(xml);
     
      def AddressIDs = completeXml.Response_Data.Worker.Worker_Data.Personal_Data.Contact_Data.Address_Data.'**'.findAll{node->node.name() == 'Address_ID'}*.text();
      String listString = String.join(",",AddressIDs);
      String list = listString.replaceAll('ADDRESS_REFERENCE-','');
     message.setProperty("AddressIDs",list)
       
       return message;
}