//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
/*
This groovy script sets the value of the 'ErrorRetryCounter' property parameter to '0' on the first run of the integration.
ErrorRetryCounter parameter keeps count of the no. of times the integration has executed in case there is failure in execution of the integration. 
*/
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.xml.*;
import com.sap.gateway.ip.core.customdev.util.Message;


//Sets the 'ErrorRetryCounter parameter' to '0' on the first execution. On the first run, the 'ErrorRetryCounter' paramter will be empty.
def Message processData(Message message) {
       //Body 
       def body = message.getBody();
  
       //Properties 
       map = message.getProperties();
       def ErrorRetryCounter = map.get("ErrorRetryCounter");
       
       //Set the ErrorRetryCounter paramater to '0' when the interface is executed for the first time.
       //Check if the ErrorRetryCounter paramater is empty.
       if(ErrorRetryCounter == ""){
        //Set the value of ErrorRetryCounter to '0', i.e. for the first run.
        message.setProperty("ErrorRetryCounter","0");
       }
       
       return message;
}