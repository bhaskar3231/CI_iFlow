import com.sap.it.api.mapping.*;
import com.sap.it.api.mapping.MappingContext;


def void addContextChange1(String[] input, Output output, MappingContext context) {
def item,item_array,item_length;
def input1 = input.toString();
if(input1.trim().length()>2) {
if(input1.contains("-")) {
item_array = input1.split('-')[1];
item_length = item_array.length();
item = item_array.substring(0,(item_length-1));
} else {
item = "";
}
} else {
item = "";
}
output.addValue(item);
}



def void splitChange2(String[] input, Output output, MappingContext context) { 
    def item=input[0].split('-')[0]	
    output.addValue(item);   
}


def void dateChange(String[] input, Output output, MappingContext context) {
def item;
def input1 = input.toString();
if(input1.trim().length()>2) {
String outdate = input1.substring(1,11);
item = outdate;
} else {
item = "";
}
output.addValue(item);
}


def String rehireTransformation(String okToRehire, MappingContext context){
    
    def output;
    if(okToRehire == "true")
        output = 'Y';
    else if(okToRehire == "false")
        output = 'N';
    else
        output = '';
        
	return output;
}


def String substring_calc_fourty(String name, MappingContext context){
def output

if(name == "")
output = ""

else if(name.length()<=40)
output = name

else
output = name.substring(0,39)

	return output;
}

def String substring_calc_fourty_External(String externalName, MappingContext context){
def output

if(externalName == "")
output = ""

else if(externalName.length()<=40)
output = externalName

else
output = externalName.substring(0,39)

	return output;
}


def String substring_calc_thirty(String username, MappingContext context){
def output

if(username == "")
output = ""

else if(username.length()<=30)
output = username

else
output = username.substring(0,29)

	return output;
}



def String substring_calc_ten(String costCenter, MappingContext context){
def output

if(costCenter == "")
output = ""

else if(costCenter.length()<=10)
output = costCenter

else
output = costCenter.substring(0,9)

	return output;
}