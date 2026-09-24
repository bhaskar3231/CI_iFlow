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





import com.sap.it.api.mapping.*
import java.text.SimpleDateFormat
import java.util.Date

def String formatDateAddress(String inputDate){
    if(inputDate == "[]"){
        return ''
    }
    else if(inputDate != null && inputDate.trim() != ""){
        // Remove square brackets if present
        inputDate = inputDate.replace("[", "").replace("]", "")
        
        def dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        def date = dateFormat.parse(inputDate)
        def newDate = new SimpleDateFormat("yyyyMMdd").format(date)
        return newDate
    } else {
        return ''
    }
}
