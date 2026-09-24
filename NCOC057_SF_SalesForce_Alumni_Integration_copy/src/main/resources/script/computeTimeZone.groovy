import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.Calendar;

def Message processData(Message message) {
              
              def body = message.getBody();
              def messageLog = messageLogFactory.getMessageLog(message);
              def pMap = message.getProperties();
              def time_zone = pMap.get("TIMEZONE")
              
              SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'00:00:00");
              Calendar calendarDate = Calendar.getInstance();
              sdf.setTimeZone(TimeZone.getTimeZone(time_zone));
              def currentDate = sdf.format(calendarDate.getTime());
              message.setProperty("CURRENT_DATE", currentDate);
              
             
             
              return message;    
}

