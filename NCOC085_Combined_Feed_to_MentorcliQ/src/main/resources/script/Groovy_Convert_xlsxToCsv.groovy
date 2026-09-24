import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import groovy.xml.XmlUtil;
import groovy.xml.*;


//Process TIL SFTP File
def Message processData_TILFile(Message message) {

    def messageLog = messageLogFactory.getMessageLog(message);
    //def pMap = message.getProperties();
    def body = message.getBody(java.io.InputStream);
    
    //Call the function convertExcelToCSV_PMReport to read from the inbound PMReport file and fetch the person ids and TIL fields
    def output =  convertExcelToCSV_TIL(body, messageLog);
    message.setBody(output);
    return message;

}

//Process DNU List SFTP File
def Message processData_DNUFile(Message message) {

    def messageLog = messageLogFactory.getMessageLog(message);
    //def pMap = message.getProperties();
    //Set DNU Flag
    def dnu_flag = "DNU";
    def body = message.getBody(java.io.InputStream);
    
    //Call the function convertExcelToCSV to read from the inbound DNU file and fetch the person ids
    def output = convertExcelToCSV(body, messageLog, dnu_flag);
    message.setBody(output);
    
    return message;

}

//Process BCLP SFTP File
def Message processData_BCLPFile(Message message) {

    def messageLog = messageLogFactory.getMessageLog(message);
    //def pMap = message.getProperties();
    def dnu_bclp = "BCLP";
    def body = message.getBody(java.io.InputStream);
    
    //Call the function convertExcelToCSV to read from the inbound BCLP file and fetch the person ids
    def output = convertExcelToCSV(body, messageLog, dnu_bclp);
    message.setBody(output);
    return message;

}

//Process Buddy SFTP File
def Message processData_BuddyFile(Message message) {

    def messageLog = messageLogFactory.getMessageLog(message);
    //def pMap = message.getProperties();
    def dnu_buddy = "Buddy";
    def body = message.getBody(java.io.InputStream);
    
    //Call the function convertExcelToCSV to read from the inbound Buddy file and fetch the person ids
    def output = convertExcelToCSV(body, messageLog, dnu_buddy);
    message.setBody(output);
    return message;

}

//Process PM_Reports SFTP File
def Message processData_PMReportFile(Message message) {

    def messageLog = messageLogFactory.getMessageLog(message);
    //def pMap = message.getProperties();
    def body = message.getBody(java.io.InputStream);
    
    //Call the function convertExcelToCSV_PMReport to read from the inbound PMReport file and fetch the person ids and the respective required fields
    def output =  convertExcelToCSV_PMReport(body, messageLog);
    message.setBody(output);
    return message;

}

//Function Call for BDIFile
def String convertExcelToCSV_TIL(def is, def messageLog) throws Exception {

    StringBuilder sb = new StringBuilder()

    def LINE_FEED = "\r\n"
    try {
        //Read the xlsx file
        Workbook workbook = WorkbookFactory.create(is);
        //From the file, fetch the first sheet
        Sheet sheet = workbook.getSheetAt(0);
        //Iterate over each row in the excel sheet
        Iterator<Row> rowIterator = sheet.iterator()
        
         //Iterate over each row to fetch the person ids and the respective fields
        while (rowIterator.hasNext()) {
            
            // Now let's iterate over the columns of the current row
            Row row = rowIterator.next()
            
            // Get the last cell number of the current row
            int len = row.getLastCellNum();
            
            //Iterate over each column of the current row to fetch the required fields and append it to LINE_FEED parameter
            for (int i = 0; len > i; i++) {
                
                //Fetch and append the field value of 1st (PERSONID) and 4th (TIME_IN_LEVEL_COMMON) of the current row and store them in between the qualifiers to preseve the characters like comma, etc.
                if(i==0 || i==3){
                    sb.append("\"").append(row.getCell(i).toString()).append("\"");
                }
                else {
                    continue;
                }

                if (len - 1 == i) {
                    // print nothing
                } else {
                    sb.append(",");
                }

            }
            sb.append(LINE_FEED);

        }

    } catch (Exception exception) {

        throw new RuntimeException(exception.toString());

    }
    //Return the appended string as csv format
    return sb.toString()

} //End Function 

//Fetches the person ids from the inbound DNU, BCLP or Buddy file
def String convertExcelToCSV(def is, def messageLog, def flag) throws Exception {

    StringBuilder sb = new StringBuilder()

    def LINE_FEED = "\r\n"
    try {
        //Read the xlsx file
        Workbook workbook = WorkbookFactory.create(is);
        //From the file, fetch the first sheet
        Sheet sheet = workbook.getSheetAt(0);
        //Iterate over each row in the excel sheet
        Iterator<Row> rowIterator = sheet.iterator();

        //Iterate over each row to fetch the person ids from each row
        while (rowIterator.hasNext()) {
            
            // Now let's iterate over the columns of the current row
            Row row = rowIterator.next();
            
            //Check if the function is called when DNU xlsx file is being readd
               if(flag == "TIL"){ 
                //Fetch the person id value and append it
                sb.append(row.getCell(3));
               }    
            //Check if the function is called when DNU xlsx file is being read
               else if(flag == "DNU"){ 
                //Fetch the person id value and append it
                sb.append(row.getCell(2));
               }
            //Check if the function is called when BCLP xlsx file is being read
               else if(flag =="BCLP") {  
                   //Fetch the person id value and append it
                   sb.append(row.getCell(1));
               }
            //Check if the function is called when Buddy xlsx file is being read
               else if(flag =="Buddy") {  
                   //Fetch the person id value and append it
                   sb.append(row.getCell(0));
               }
                else{
                       sb.append(row.getCell(-1));
                }
            //Append the fetched person ids to the LINE_FEED parameter
            sb.append(LINE_FEED);

        }

    } catch (Exception exception) {

        throw new RuntimeException(exception.toString());

    }
    
    //Return the appended person ids as csv format 
    return sb.toString()

} //End Function


//Function Call for PM_ReportsFile
def String convertExcelToCSV_PMReport(def is, def messageLog) throws Exception {

    StringBuilder sb = new StringBuilder()

    def LINE_FEED = "\r\n"
    try {
        //Read the xlsx file
        Workbook workbook = WorkbookFactory.create(is);
        //From the file, fetch the first sheet
        Sheet sheet = workbook.getSheetAt(0);
        //Iterate over each row in the excel sheet
        Iterator<Row> rowIterator = sheet.iterator()
        
         //Iterate over each row to fetch the person ids and the respective fields i.e. Milestone,YAL,CLuster Information,Skill Category
        while (rowIterator.hasNext()) {
            
            // Now let's iterate over the columns of the current row
            Row row = rowIterator.next()
            
            // Get the last cell number of the current row
            int len = row.getLastCellNum();
            
            //Iterate over each column of the current row to fetch the required fields and append it to LINE_FEED parameter
            for (int i = 0; len > i; i++) {
                
                //Fetch and append the field value of each colum of the current row and store them in between the qualifiers to preseve the characters like comma, etc.
                sb.append("\"").append(row.getCell(i).toString()).append("\"");

                if (len - 1 == i) {
                    // print nothing
                } else {
                    sb.append(",");
                }

            }
            sb.append(LINE_FEED);

        }

    } catch (Exception exception) {

        throw new RuntimeException(exception.toString());

    }
    //Return the appended string as csv format
    return sb.toString()

} //End Function 