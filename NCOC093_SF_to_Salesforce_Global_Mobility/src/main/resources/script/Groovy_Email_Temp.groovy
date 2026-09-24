/* Refer the link below to learn more about the use cases of script.
https://help.sap.com/viewer/368c481cd6954bdfa5d0435479fd4eaf/Cloud/en-US/148851bf8192412cba1f9d2c17f4bd25.html

If you want to know more about the SCRIPT APIs, refer the link below
https://help.sap.com/doc/a56f52e1a58e4e2bac7f7adbf45b2e26/Cloud/en-US/index.html */
//This script will give input to the shared email in cas eof failure
import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
def Message processData(Message message) {
    //Body
    //def body = message.getBody();
    //message.setBody(body + " Body is modified");
    //Headers
    def headers = message.getHeaders();
    def properties = message.getProperties();
    def fail_rec, total_rec;
    
    def fail = properties.get("failedCount"); 
    def total = properties.get("totalCount"); 
    if(fail == ""){
        fail_rec = 0;
    }
    else{
        fail_rec = Integer.parseInt(fail);
    }
    if(total == ""){
        total_rec = 0;
    }
    else{
        total_rec = Integer.parseInt(total);
    }
    //def fail_rec = fail as Integer;
    //def total_rec = total as Integer;
    def success = (total_rec - fail_rec).toString();
    message.setProperty("successRecordCount",success);
    message.setProperty("failedRecordCount",fail);
    message.setProperty("totalRecordCount",total);
    
    return message;
}