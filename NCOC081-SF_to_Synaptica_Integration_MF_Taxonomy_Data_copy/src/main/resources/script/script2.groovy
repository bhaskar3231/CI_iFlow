/* Refer the link below to learn more about the use cases of script.
https://help.sap.com/viewer/368c481cd6954bdfa5d0435479fd4eaf/Cloud/en-US/148851bf8192412cba1f9d2c17f4bd25.html

Calculate the date (today's date + 1 ) for future dated records */
import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;

def Message Future_record_date(Message message) {

	def messageLog = messageLogFactory.getMessageLog(message);
	def bodyAsString = message.getBody(String.class);
	def map = message.getProperties();
	def future_date = new Date() + 1 ;
     message.setProperty("future_date", future_date.format("yyyy-MM-dd'T'00:00:00.000")) ; 
     return message;
}  

 

		