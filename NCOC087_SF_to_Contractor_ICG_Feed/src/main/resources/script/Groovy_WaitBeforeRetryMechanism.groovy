//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
/*
This groovy script checks if the value of 'ErrorRetryCounter' parameter is less than or equal to '3'. 
If it is true, the interface waits for ten minutes before proceeding for re execution of the integration.
*/
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;


def Message processData(Message message) {
       //Body 
       def body = message.getBody();
    
  
       //Properties 
       map = message.getProperties();
       def ErrorRetryCounter = map.get("ErrorRetryCounter");
      
      //Check if the value of ErrorRetryCounter is less than or equal to '3'
      if(ErrorRetryCounter <= "3"){
          //Wait for 10 minutes(600000 milliseconds). Value to be entered in milliseconds. (1sec = 1000 milliseconds)
           sleep(600000);
      } 
      message.setBody(body);
      
      return message;
}