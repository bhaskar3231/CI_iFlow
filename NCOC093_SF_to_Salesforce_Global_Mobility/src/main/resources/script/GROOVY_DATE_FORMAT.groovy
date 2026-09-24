//Changing the data format to Salesforce acceptance format

import com.sap.it.api.mapping.*;
def String formatDate(String in_date){   
   if(in_date.equals(""))
      return "";
    else{
         def out_date=in_date.substring(0,10);
         return out_date;
    }
}