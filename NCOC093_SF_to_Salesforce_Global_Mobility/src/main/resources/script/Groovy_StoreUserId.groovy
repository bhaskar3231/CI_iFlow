//This script is for storing the userId of the records 
import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;

def Message processData(Message message){
 

//def Message storeUserId(Message message) {
def body = message.getBody(String);
def processedXML = new XmlSlurper().parseText(body);
//message.setProperty("check",processedXML);
def all_users=new StringBuffer();
def all_relusers=new StringBuffer();

//Parsing through the input XML and storing the userids' of the records in all_users variable
processedXML.EmpEmployment.each{

        all_users=all_users.append(it.userId.text()).append(",");
        all_relusers=all_relusers.append(it.empJobRelationshipNav.EmpJobRelationships.relUserId.text()).append(",");
}

if(all_users.length() > 0)
//Removing the comma at the end of the string stored in all_users
all_users=all_users.substring(0,all_users.length()-1);

if(all_relusers.length() > 0)
//Removing the comma at the end of the string stored in all_users
all_relusers=all_relusers.substring(0,all_relusers.length()-1);

//Check null all_relusers
int len = all_relusers.length();
int result = len;
for(int i=1; i<len; i++){
    if(all_relusers[i] != all_relusers[0]){
        result = result - 1;
    }
}
if(result==len){
    message.setProperty("ALL_RELUSERS","null");
}
else{
    message.setProperty("ALL_RELUSERS",all_relusers.toString());

}

//setting the property ALL_USERS with the all_users variable which contains all the userIds'
message.setProperty("ALL_USERS",all_users.toString());

return message;
//}
}