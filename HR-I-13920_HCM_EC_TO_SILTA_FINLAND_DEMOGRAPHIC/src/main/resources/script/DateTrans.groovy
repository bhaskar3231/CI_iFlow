import com.sap.it.api.mapping.*;
import java.text.SimpleDateFormat;
import java.util.Date;
def String formatDate(String inputDate){
    
    if(inputDate){
        dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS"
        date = new SimpleDateFormat(dateFormat).parse(inputDate);
        newDate = new SimpleDateFormat("yyyyMMdd").format(date)
        
    }else{
        newDate = ''
    }
    
    return newDate; 
}