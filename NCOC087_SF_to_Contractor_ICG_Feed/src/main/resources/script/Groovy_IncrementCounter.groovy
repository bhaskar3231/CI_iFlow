//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
/*
This groovy script increments the value of the 'ErrorRetryCounter' parameter by '1'. 
This incremented value is then passed in the SOAP message to track the number of times interface has executed in case of failure in execution of the integration. 
*/
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.xml.*
import com.sap.gateway.ip.core.customdev.util.Message;

def Message processData(Message message) {
    //Body 
       def body = message.getBody();
       
       //Properties
       map = message.getProperties();
       def ErrorRetryCounter = map.get("ErrorRetryCounter");
        
        //Check if the value of 'ErrorRetryCounter' is equal to '0', i.e., it was initial execution of the integration
        if(ErrorRetryCounter == '0'){
            //Increment the value of 'ErrorRetryCounter' by '1' - For 1st re execution of interface after error
            message.setProperty("ErrorRetryCounter", 1);
        }
        //Check if the value of 'ErrorRetryCounter' is equal to '1'
        if(ErrorRetryCounter == '1'){
            //Increment the value of 'ErrorRetryCounter' by '1' - For 2nd re execution of interface after error
            message.setProperty("ErrorRetryCounter", 2);
        }
        //Check if the value of 'ErrorRetryCounter' is equal to '2'
        else if(ErrorRetryCounter == '2'){
            //Increment the value of 'ErrorRetryCounter' by '1' - For 3rd re execution of interface after error
    	    message.setProperty("ErrorRetryCounter", 3);
        }
        //Else no change required
        else {
                
        }
       
       return message;
}