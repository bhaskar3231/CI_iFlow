import com.sap.it.api.mapping.*

def void identityList(String[] nationalID, String[] govtID, String[] passport,String[] license, Output output, MappingContext context) {
    //def searchFor = attributeToLocate[0]
    if(nationalID[] != null)
    {
    for(int i=0;i<nationalID.length;i++)
	{
    output.addValue(nationalID[i])
    }
	
    }
 if(govtID[] != null)
    {
    for(int j=0;j<govtID.length;j++)
	{
    output.addValue(govtID[j])
    }
	}
	if(passport[] != null)
    {
    for(int k=0;k<passport.length;k++)
	{
    output.addValue(passport[k])
    }
	}
	if(license[] != null)
    {
    for(int l=0;l<license.length;l++)
	{
    output.addValue(license[l])
    }
	}
}	
   