//This script is for storing the userId of the records 
import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;


def Message storeUserId(Message message) {
def body = message.getBody(String);
def processedXML = new XmlSlurper().parseText(body);

def all_users=new StringBuffer();

//Parsing through the input XML and storing the userids' of the records in all_users variable
processedXML.EmpEmployment.each{

        all_users=all_users.append(it.userId.text()).append(",");
}


if(all_users.length() > 0)
//Removing the comma at the end of the string stored in all_users
all_users=all_users.substring(0,all_users.length()-1);

//setting the property ALL_USERS with the all_users variable which contains all the userIds'
message.setProperty("ALL_USERS",all_users.toString());

return message;
}