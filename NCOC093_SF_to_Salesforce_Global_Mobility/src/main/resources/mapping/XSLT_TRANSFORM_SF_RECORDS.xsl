<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
<!-- This Script will be processed on incoming payload and transform the payload as per defined structure -->
<xsl:output method="xml" indent="yes" omit-xml-declaration="yes"/>


  <xsl:template match="/">
    <root>
      <xsl:for-each select="/EmpEmployment/EmpEmployment">
        <contact>
           <WCT_Personnel_Number__c><xsl:value-of select="userId"/></WCT_Personnel_Number__c>
           <WCT_Person_Id__c><xsl:value-of select="personIdExternal"/></WCT_Person_Id__c>
           
           <xsl:variable name="country" select="personNav/PerPerson/nationalIdNav/PerNationalId/country"/>
           
           <WCT_SSN__c>
             <xsl:if test="$country = 'USA'">
               <xsl:variable name="v_card_length" select="string-length(personNav/PerPerson/nationalIdNav/PerNationalId[cardType = 'ssn']/nationalId)"/>
               <xsl:value-of select="substring(personNav/PerPerson/nationalIdNav/PerNationalId[cardType = 'ssn']/nationalId,($v_card_length - 3))"/>
             </xsl:if>
            </WCT_SSN__c>
           <WCT_India_PAN_Last_4_digits__c>
             <xsl:if test="$country = 'IND'">
              <xsl:variable name="v_card_length" select="string-length(personNav/PerPerson/nationalIdNav/PerNationalId[cardType = 'PAN']/nationalId)"/>
              <xsl:value-of select="substring(personNav/PerPerson/nationalIdNav/PerNationalId[cardType = 'PAN']/nationalId,($v_card_length - 3))"/>
             </xsl:if>
           </WCT_India_PAN_Last_4_digits__c>
           <Birthdate>
             <xsl:if test="personNav/PerPerson/dateOfBirth !=''">
               <xsl:value-of select="format-dateTime(personNav/PerPerson/dateOfBirth,'[Y0001]-[M01]-[D01]')"/>
             </xsl:if>
           </Birthdate> 
           <WCT_Marital_Status__c><xsl:value-of select="personNav/PerPerson/personalInfoNav/PerPersonal/maritalStatusNav/PicklistOption/localeLabel"/></WCT_Marital_Status__c>
           <WCT_Name_Prefix__c><xsl:value-of select="personNav/PerPerson/personalInfoNav/PerPersonal/salutationNav/PicklistOption/localeLabel"/></WCT_Name_Prefix__c>
          
           <LastName><xsl:value-of select="personNav/PerPerson/personalInfoNav/PerPersonal/lastName"/></LastName>
           <FirstName><xsl:value-of select="personNav/PerPerson/personalInfoNav/PerPersonal/firstName"/></FirstName>
           <WCT_Middle_Name__c><xsl:value-of select="personNav/PerPerson/personalInfoNav/PerPersonal/middleName"/></WCT_Middle_Name__c>
           <WCT_Preferred_Name__c><xsl:value-of select="normalize-space(concat(personNav/PerPerson/personalInfoNav/PerPersonal/preferredName,' ',personNav/PerPerson/personalInfoNav/PerPersonal/customString5))"/></WCT_Preferred_Name__c>
           <WCT_Prior_Name__c><xsl:value-of select="personNav/PerPerson/personalInfoNav/PerPersonal/birthName"/></WCT_Prior_Name__c>
           <WCT_Name_Suffix__c><xsl:value-of select="personNav/PerPerson/personalInfoNav/PerPersonal/suffixNav/PicklistOption/localeLabel"/></WCT_Name_Suffix__c>
           <Email><xsl:value-of select="personNav/PerPerson/emailNav/PerEmail[emailTypeNav/PicklistOption/externalCode = 'B']/emailAddress"/></Email>
           <WCT_ExternalEmail__c><xsl:value-of select="personNav/PerPerson/emailNav/PerEmail[emailTypeNav/PicklistOption/externalCode = 'P']/emailAddress"/></WCT_ExternalEmail__c>
           <USG_Personnel_Class__c><xsl:value-of select="jobInfoNav/EmpJob/employeeClassNav/PicklistOption/externalCode"/></USG_Personnel_Class__c>
           <USG_Personnel_Type__c><xsl:value-of select="jobInfoNav/EmpJob/employmentTypeNav/PicklistOption/externalCode"/></USG_Personnel_Type__c>
           <USG_Sub_Level__c><xsl:value-of select="jobInfoNav/EmpJob/customString3Nav/PicklistOption/externalCode"/></USG_Sub_Level__c>
           <USG_Country_Of_Registration__c><xsl:value-of select="jobInfoNav/EmpJob/countryOfCompany"/></USG_Country_Of_Registration__c>
           
           <WCT_Job_Level_Text__c><xsl:value-of select="jobInfoNav/EmpJob/customString4Nav/cust_Level/externalName"/></WCT_Job_Level_Text__c>
           <WCT_Job_Text__c><xsl:value-of select="jobInfoNav/EmpJob/customString52"/></WCT_Job_Text__c>
          
           <WCT_Employee_Status__c><xsl:value-of select="jobInfoNav/EmpJob/emplStatusNav/PicklistOption/localeLabel"/></WCT_Employee_Status__c>
           <WCT_Gender__c><xsl:value-of select="personNav/PerPerson/personalInfoNav/PerPersonal/gender"/></WCT_Gender__c>
           <WCT_Cost_Center__c><xsl:value-of select="jobInfoNav/EmpJob/costCenterNav/FOCostCenter/externalCode"/></WCT_Cost_Center__c>
           <WCT_Company_Codenew__c><xsl:value-of select="jobInfoNav/EmpJob/company"/></WCT_Company_Codenew__c>
           
           <WCT_Region__c><xsl:value-of select="jobInfoNav/EmpJob/costCenterNav/FOCostCenter/cust_Region"/></WCT_Region__c>
           <WCT_Function__c><xsl:value-of select="substring-after(jobInfoNav/EmpJob/costCenterNav/FOCostCenter/cust_Business,'-')"/></WCT_Function__c>
           <WCT_Service_Area__c><xsl:value-of select="substring-after(jobInfoNav/EmpJob/costCenterNav/FOCostCenter/cust_BusinessArea,'-')"/></WCT_Service_Area__c>
           <WCT_Service_Line__c><xsl:value-of select="substring-after(jobInfoNav/EmpJob/costCenterNav/FOCostCenter/cust_BusinessLine,'-')"/></WCT_Service_Line__c>
          
           <WCT_Org_Unit__c><xsl:value-of select="jobInfoNav/EmpJob/departmentNav/FODepartment/externalCode"/></WCT_Org_Unit__c>
           
           <WCT_Primary_Market_Offering__c><xsl:value-of select="jobInfoNav/EmpJob/customString13Nav/cust_PrimaryMarketOffering/externalName"/></WCT_Primary_Market_Offering__c>
           <WCT_Sub_Secondary_Market_Offering__c><xsl:value-of select="jobInfoNav/EmpJob/customString14Nav/cust_SecondaryMarketOffering/externalName"/></WCT_Sub_Secondary_Market_Offering__c>
    
			    <WCT_Primary_Industry__c><xsl:value-of select="Background_Industry/IndustryNav/PicklistOption/localeLabel"/></WCT_Primary_Industry__c> 
			    <WCT_Sub_Secondary_Industry__c><xsl:value-of select="Background_Industry/Industry_2ndNav/PicklistOption/localeLabel"/></WCT_Sub_Secondary_Industry__c> 
			    <WCT_Primary_Segment__c><xsl:value-of select="Background_Industry/SectorNav/PicklistOption/localeLabel"/></WCT_Primary_Segment__c> 
			    <WCT_Secondary_Segment__c><xsl:value-of select="Background_Industry/Sector_2ndNav/PicklistOption/localeLabel"/></WCT_Secondary_Segment__c> 
			    
			    <WCT_Original_Hire_Date__c><xsl:value-of select="substring-before(originalStartDate,'T')"/></WCT_Original_Hire_Date__c> 
			    <WCT_Most_Recent_Rehire__c><xsl:value-of select="substring-before(startDate,'T')"/></WCT_Most_Recent_Rehire__c> 
			    <WCT_Last_Day_Worked__c><xsl:value-of select="substring-before(lastDateWorked,'T')"/></WCT_Last_Day_Worked__c> 
			    <WCT_Terminated_Date__c><xsl:value-of select="substring-before(endDate,'T')"/></WCT_Terminated_Date__c> 
			    
			    <WCT_Facility_Name__c><xsl:value-of select="jobInfoNav/EmpJob/locationNav/FOLocation/externalCode"/></WCT_Facility_Name__c>
			    <Phone><xsl:value-of select="personNav/PerPerson/phoneNav/PerPhone[phoneTypeNav/PicklistOption/externalCode = 'B1']/phoneNumber"/></Phone>
			    <Fax><xsl:value-of select="personNav/PerPerson/phoneNav/PerPhone[phoneTypeNav/PicklistOption/externalCode = 'PT5']/phoneNumber"/></Fax>
			    <MobilePhone><xsl:value-of select="personNav/PerPerson/phoneNav/PerPhone[phoneTypeNav/PicklistOption/externalCode = 'PT3']/phoneNumber"/></MobilePhone>
			    
			    <WCT_DPM_Counselor__c><xsl:value-of select="normalize-space(concat(empJobRelationshipNav/EmpJobRelationships[relationshipTypeNav/PicklistOption/externalCode = 'custom manager']/PerPerson/personalInfoNav/PerPersonal/firstName,' ',empJobRelationshipNav/EmpJobRelationships[relationshipTypeNav/PicklistOption/externalCode = 'custom manager']/PerPerson/personalInfoNav/PerPersonal/lastName))"/></WCT_DPM_Counselor__c>
			    <WCT_Home_Street_2__c><xsl:value-of select="personNav/PerPerson/homeAddressNavDEFLT/PerAddressDEFLT[addressType = 'home']/address2"/></WCT_Home_Street_2__c>
			    <WCT_Home_Street__c><xsl:value-of select="personNav/PerPerson/homeAddressNavDEFLT/PerAddressDEFLT[addressType = 'home']/address1"/></WCT_Home_Street__c>
			    <WCT_Home_City__c><xsl:value-of select="personNav/PerPerson/homeAddressNavDEFLT/PerAddressDEFLT[addressType = 'home']/city"/></WCT_Home_City__c>
			    <WCT_Home_State__c><xsl:value-of select="personNav/PerPerson/homeAddressNavDEFLT/PerAddressDEFLT[addressType = 'home']/stateNav/PicklistOption/externalCode"/></WCT_Home_State__c>
			    <WCT_Home_Country__c><xsl:value-of select="personNav/PerPerson/homeAddressNavDEFLT/PerAddressDEFLT[addressType = 'home']/country"/></WCT_Home_Country__c>
			    
			    <WCT_Citizenship__c><xsl:value-of select="personNav/PerPerson/personalInfoNav/PerPersonal/nationality"/></WCT_Citizenship__c>
			    <WCT_Personnel_Area_Code__c><xsl:value-of select="jobInfoNav/EmpJob/locationNav/FOLocation/externalCode"/></WCT_Personnel_Area_Code__c>
			    <WCT_Office_City_Personnel_Subarea__c><xsl:value-of select="jobInfoNav/EmpJob/locationNav/FOLocation/addressNavDEFLT/FOCorporateAddressDEFLT/city"/></WCT_Office_City_Personnel_Subarea__c>
			    <WCT_PS_Group__c><xsl:value-of select="jobInfoNav/EmpJob/customString17Nav/PicklistOption/externalCode"/></WCT_PS_Group__c>
			    <CBP_Hire_Source_Description__c><xsl:value-of select="jobInfoNav/EmpJob/hireSourceNav/PicklistOption/localeLabel"/></CBP_Hire_Source_Description__c>

          <H1B_Work_Permit_Type__c><xsl:value-of select="empWorkPermitNav/EmpWorkPermit[1]/documentTypeNav/PicklistOption/localeLabel"/></H1B_Work_Permit_Type__c>
          <H1B_Work_Permit_Expiry_Date__c><xsl:value-of select="format-dateTime(empWorkPermitNav/EmpWorkPermit[1]/expirationDate,'[Y0001]-[M01]-[D01]')"/></H1B_Work_Permit_Expiry_Date__c> 
          <H1B_Cost_Center_Description__c><xsl:value-of select="jobInfoNav/EmpJob/costCenterNav/FOCostCenter/name"/></H1B_Cost_Center_Description__c>
          
          <ELE_Counselor_Email_ID__c><xsl:value-of select="empJobRelationshipNav/EmpJobRelationships[relationshipTypeNav/PicklistOption/externalCode = 'custom manager']/PerPerson/emailNav/PerEmail[emailTypeNav/PicklistOption/externalCode = 'B']/emailAddress"/></ELE_Counselor_Email_ID__c>
           
			    <CBP_Service_Date__c><xsl:value-of select="substring-before(jobInfoNav/EmpJob/customDate8,'T')"/></CBP_Service_Date__c>

			    <CBP_Job_Level_Code__c><xsl:value-of select="jobInfoNav/EmpJob/customString4Nav/cust_Level/externalCode"/></CBP_Job_Level_Code__c>
			    <CBP_Employment_Percent__c>
			        <xsl:if test="jobInfoNav/EmpJob/fte != ''">
			          <xsl:value-of select="(jobInfoNav/EmpJob/fte * 100)"/>
			        </xsl:if>
			     </CBP_Employment_Percent__c>
			    
			    <WCT_Capability_Description__c><xsl:value-of select="jobInfoNav/EmpJob/customString9Nav/cust_capability/externalName"/></WCT_Capability_Description__c>
			    <WCT_Sub_Capability_Description__c><xsl:value-of select="jobInfoNav/EmpJob/customString10Nav/cust_subcapability/externalName"/></WCT_Sub_Capability_Description__c>
			   
			    <WCT_HR_Service_Line_Code__c><xsl:value-of select="substring-before(jobInfoNav/EmpJob/costCenterNav/FOCostCenter/cust_BusinessLine,'-')"/></WCT_HR_Service_Line_Code__c>
			    <WCT_HR_Service_Area_Code__c><xsl:value-of select="substring-before(jobInfoNav/EmpJob/costCenterNav/FOCostCenter/cust_BusinessArea,'-')"/></WCT_HR_Service_Area_Code__c>
			    <!--<WCT_Company_Code__c><xsl:value-of select="substring(jobInfoNav/EmpJob/companyNav/FOCompany/name,0,38)"/></WCT_Company_Code__c>-->
			    <WCT_Company_Code__c><xsl:value-of select="jobInfoNav/EmpJob/companyNav/FOCompany/name"/></WCT_Company_Code__c>
			    <Cost_Center_Business_Sub_Line_Desc__c><xsl:value-of select="substring-before(jobInfoNav/EmpJob/costCenterNav/FOCostCenter/cust_BusinessSubLine,'-')"/></Cost_Center_Business_Sub_Line_Desc__c>
			     
		
			
        </contact>
      </xsl:for-each>
    </root>
  </xsl:template>
   

</xsl:stylesheet>
