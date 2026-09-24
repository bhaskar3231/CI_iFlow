import com.sap.it.api.mapping.*;

def void addContextChange(String[] input, Output output, MappingContext context) {
    def item,item_array,item_length;
    def input1 = input.toString();
    if(input1.trim().length()>2) {
        if(input1.contains("-")) {
        item_array = input1.split('-')[0];
        item_length = item_array.length();
        item = item_array.substring(1,item_length);
        } else {
        item = "";
    } 
    } else {
    item = "";
    }
    output.addValue(item);   
}