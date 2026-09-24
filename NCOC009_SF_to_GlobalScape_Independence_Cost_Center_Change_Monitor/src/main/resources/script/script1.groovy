import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.xml.XmlUtil;
import groovy.xml.*
import java.text.SimpleDateFormat;
import groovy.util.*;
import java.util.*;
import java.util.Date;
def Message processData(Message message) {
def body = message.getBody(java.lang.String);
//def xml = new XmlSlurper().parseText(body)

message.setHeader("ChangeRecord", body);
   


return message;

}