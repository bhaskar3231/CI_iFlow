import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.Calendar;

def Message processData(Message message) {
              
              def body = message.getBody();
              def messageLog = messageLogFactory.getMessageLog(message);
              def pMap = message.getProperties();
              def time_zone = pMap.get("TIMEZONE")
              
              SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
              Calendar calendarDate = Calendar.getInstance();
              sdf.setTimeZone(TimeZone.getTimeZone(time_zone));
              def currentDate = sdf.format(calendarDate.getTime());
              message.setProperty("CURRENT_DATE", currentDate);
              
              SimpleDateFormat sdf_mail = new SimpleDateFormat("yyyyMMdd");
              def mailTimestamp = sdf_mail.format(calendarDate.getTime());
              message.setProperty("CURRENT_DATE_MAIL", mailTimestamp); 
              
              SimpleDateFormat sdftime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
              message.setProperty("CURRENT_TIMESTAMP_GMT", sdftime.format(calendarDate.getTime()));
              
              sdftime.setTimeZone(TimeZone.getTimeZone(time_zone));
              def currentTimestamp = sdftime.format(calendarDate.getTime());
              message.setProperty("CURRENT_TIMESTAMP", currentTimestamp);
              
            //   def hours = currentTimestamp.substring(11,13)
            //   Integer intHours = hours as Integer
            //   if(intHours >= 12)
            //     message.setProperty("CURRENT_DATE_MAIL", mailTimestamp+"PM"); 
            //   else 
            //     message.setProperty("CURRENT_DATE_MAIL", mailTimestamp+"AM"); 
              
              calendarDate.add(Calendar.DATE, 14);
              def cutoffdate = sdf.format(calendarDate.getTime());
              message.setProperty("CUTOFF_DATE", cutoffdate);
             
              return message;    
}

