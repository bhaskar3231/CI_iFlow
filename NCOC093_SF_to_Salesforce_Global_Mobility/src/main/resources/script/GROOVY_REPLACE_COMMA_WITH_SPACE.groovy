/* This scripts escapes special characters from the incoming payload */

import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import org.apache.commons.lang.StringEscapeUtils;
def Message processData(Message message) {
    
       def body = message.getBody(java.lang.String) as String;
       //body=StringEscapeUtils.escapeXml(body);
       //	body=body.replaceAll("[^a-zA-Z0-9]", " ");
	   body=body.replaceAll(",", " ");
       message.setBody(body);
       return message;
} 
 
