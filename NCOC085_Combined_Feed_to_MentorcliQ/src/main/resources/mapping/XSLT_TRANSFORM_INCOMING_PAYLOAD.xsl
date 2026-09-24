<!-- This XSLT Script transforms the structure of the incoming payload and also populates the picklist values using stored hashmaps-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0" xmlns:map="java:java.util.Map" extension-element-prefixes="map" xmlns:xs="http://www.w3.org/2001/XMLSchema" exclude-result-prefixes="#all">
<xsl:output method="xml" indent="yes" omit-xml-declaration="yes"/>

 <!-- Include Properties -->
    <xsl:param name="CURRENT_DATE"/>
    <xsl:param name="TODAY"/>
    <xsl:param name="Picklist_Salutaion_Hashmap"/>
    <xsl:param name="Picklist_EmployeeStatus_Hashmap"/>
    <xsl:param name="Picklist_EmployeeClass_HashMap"/>
    <xsl:param name="Picklist_EmploymentType_Hashmap"/>
    <xsl:param name="Picklist_Event_Hashmap"/>
    <xsl:param name="Picklist_EmployeeClass_Desc_HashMap"/>
    
    
    <xsl:param name="Picklist_HXM_FOCUS_Hashmap"/>
    
    <xsl:param name="Business_Email_OptionId"/>
    <xsl:param name="Personal_Email_OptionId"/>
    <xsl:param name="JobRel_Coach_OptionId"/>
    
    <xsl:param name="FO_CostCenter_HashMap"/>
    <xsl:param name="FO_Event_Reason_HashMap"/>

    <xsl:param name="Picklist_SWIFT_OFFICE_CODE_Hashmap"/>
    <xsl:param name="MDF_LocationGroup_Map"/>
   
    <xsl:param name="Picklist_GPS_FLAG_Label_Hashmap"/>
    <xsl:param name="Picklist_HireSource_Label_Hashmap"/>
   
    <xsl:param name="Background_Industry_HashMap"/>
    <xsl:param name="Picklist_TalentModel_Hashmap"/>
    
    <xsl:param name="Picklist_Industry_Hashmap"/>
    <xsl:param name="Picklist_Sector_Hashmap"/>
    
     
   <xsl:template match="/">
    <EmpEmployment>
      <xsl:for-each select="/EmpEmployment/EmpEmployment">
        <EmpEmployment>
         
         <!-- Variable Declaration  -->  
         <xsl:variable name="employee_status" select="map:get($Picklist_EmployeeStatus_Hashmap,string(jobInfoNav/EmpJob/emplStatus))"/>
         <xsl:variable name="event_reason" select="jobInfoNav/EmpJob/eventReason"/>
         <xsl:variable name="event_code" select="map:get($Picklist_Event_Hashmap,string(jobInfoNav/EmpJob/event))"/>
          
          <!-- Biographic Information -->
          
          <personIdExternal><xsl:value-of select="personIdExternal"/></personIdExternal>
          <GE_Personnel_ID><xsl:value-of select="personNav/PerPerson/customString2"/></GE_Personnel_ID>
          <userId><xsl:value-of select="userId"/></userId>
          
          
          <assignmentClass><xsl:value-of select="assignmentClass"/></assignmentClass>
          <isContingentWorker><xsl:value-of select="isContingentWorker"/></isContingentWorker>
          
          <endDate><xsl:value-of select="endDate"/></endDate>
          <hireDate>
              <xsl:if test="startDate != ''">
                 <xsl:value-of select="format-dateTime(startDate,'[M01]/[D01]/[Y0001]')"/>
              </xsl:if>
           </hireDate>
          
           <startDate><xsl:value-of select="substring-before(startDate,'T')"/></startDate>
          <lastDateWorked><xsl:value-of select="lastDateWorked"/></lastDateWorked>
          
          <!-- Personal Information-->
          
           <firstName><xsl:value-of select="personNav/PerPerson/personalInfoNav/PerPersonal/firstName"/></firstName>
           <lastName><xsl:value-of select="personNav/PerPerson/personalInfoNav/PerPersonal/lastName"/></lastName>
           <salutation><xsl:value-of select="map:get($Picklist_Salutaion_Hashmap,string(personNav/PerPerson/personalInfoNav/PerPersonal/salutation))"/></salutation>
          
          <!-- Email Information-->
          <Business_EmailAddress><xsl:value-of select="personNav/PerPerson/emailNav/PerEmail[emailType = $Business_Email_OptionId]/emailAddress"/></Business_EmailAddress>
          <Personal_EmailAddress><xsl:value-of select="personNav/PerPerson/emailNav/PerEmail[emailType = $Personal_Email_OptionId]/emailAddress"/></Personal_EmailAddress>
          
          <!-- Job Information -->
          
           <customString52><xsl:value-of select="jobInfoNav/EmpJob/customString52"/></customString52>
           <jobCode><xsl:value-of select="jobInfoNav/EmpJob/jobCode"/></jobCode>
           
           <xsl:variable name="job_level_desc" select="jobInfoNav/EmpJob/customString4Nav/cust_Level/externalName"/>
           <job_level><xsl:value-of select="$job_level_desc"/></job_level>
           <location_group_name><xsl:value-of select="map:get($MDF_LocationGroup_Map,string(jobInfoNav/EmpJob/location))"/></location_group_name>
           
           <xsl:variable name="GPS_Flag" select="map:get($Picklist_GPS_FLAG_Label_Hashmap,string(jobInfoNav/EmpJob/customString23))"/>
           <Federal_GPS_Indicator>
               <xsl:choose>
                   <xsl:when test="($GPS_Flag = 'GPS Alumni' or $GPS_Flag = 'GPS Borrowed' or $GPS_Flag = 'GPS Home')">
                       <xsl:text>GPS</xsl:text>
                   </xsl:when>
                   <xsl:otherwise>
                       <xsl:text>COMMERCIAL</xsl:text>
                   </xsl:otherwise>
               </xsl:choose>
            </Federal_GPS_Indicator>   
            <event><xsl:value-of select="$event_code"/></event>
           <!-- <LOA_Indicator>
                <xsl:choose>
                    <xsl:when test="$event_code = '10' and (userNav/User/userIdOfEmployeeTimeNav/EmployeeTime[1]/endDate &gt; CURRENT_DATE) ">
                        <xsl:text>Yes</xsl:text>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:text>No</xsl:text>
                    </xsl:otherwise>
                </xsl:choose>
            </LOA_Indicator>-->
            
            <LOA_Indicator>
                <xsl:choose>
                    <xsl:when test="($event_code = '10') and ((xs:dateTime(userNav/User/userIdOfEmployeeTimeNav/EmployeeTime[1]/startDate) &lt;= xs:dateTime($CURRENT_DATE)) and (xs:dateTime(userNav/User/userIdOfEmployeeTimeNav/EmployeeTime[1]/endDate) &gt;= xs:dateTime($CURRENT_DATE)))">
                        <xsl:text>Yes</xsl:text>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:text>No</xsl:text>
                    </xsl:otherwise>
                </xsl:choose>
            </LOA_Indicator>
            
          
          <hireSource><xsl:value-of select="map:get($Picklist_HireSource_Label_Hashmap,string(jobInfoNav/EmpJob/hireSource))"/></hireSource> 
          
          <!--Industry Information -->
         
          <Primary_Industry><xsl:value-of select="map:get($Picklist_Industry_Hashmap,string(map:get($Background_Industry_HashMap,string(userId))[1]))"/></Primary_Industry>
          <Primary_Sector><xsl:value-of select="map:get($Picklist_Sector_Hashmap,string(map:get($Background_Industry_HashMap,string(userId))[2]))"/></Primary_Sector>
          <Secondary_Industry><xsl:value-of select="map:get($Picklist_Industry_Hashmap,string(map:get($Background_Industry_HashMap,string(userId))[3]))"/></Secondary_Industry>
          <Secondary_Sector><xsl:value-of select="map:get($Picklist_Sector_Hashmap,string(map:get($Background_Industry_HashMap,string(userId))[4]))"/></Secondary_Sector>
    
         
          <Primary_Market_Offering><xsl:value-of select="jobInfoNav/EmpJob/customString13Nav/cust_PrimaryMarketOffering/externalName"/></Primary_Market_Offering>
          <Secondary_Market_Offering><xsl:value-of select="jobInfoNav/EmpJob/customString14Nav/cust_SecondaryMarketOffering/externalName"/></Secondary_Market_Offering>
          
          <Capability_Desc><xsl:value-of select="jobInfoNav/EmpJob/customString9Nav/cust_capability/externalName"/></Capability_Desc>
          <SubCapability_Desc><xsl:value-of select="jobInfoNav/EmpJob/customString10Nav/cust_subcapability/externalName"/></SubCapability_Desc>
          
          <employmentType><xsl:value-of select="map:get($Picklist_EmploymentType_Hashmap,string(jobInfoNav/EmpJob/employmentType))"/></employmentType>
          
          <employee_class_description><xsl:value-of select="map:get($Picklist_EmployeeClass_Desc_HashMap,string(jobInfoNav/EmpJob/employeeClass))"/></employee_class_description>
          
          <FocusArea_Desc><xsl:value-of select="map:get($Picklist_HXM_FOCUS_Hashmap,string(jobInfoNav/EmpJob/customString21))"/></FocusArea_Desc>
          
          
          
        <xsl:variable name="talent_model" select="map:get($Picklist_TalentModel_Hashmap,string(jobInfoNav/EmpJob/customString7))"/>
        <Talent_Model>
            <xsl:if test="$talent_model != ''">
                <xsl:value-of select="concat($talent_model,' ','Model')"/>
            </xsl:if>
        </Talent_Model>
        <Talent_Track>
            <xsl:if test="$talent_model != ''">
                <xsl:value-of select="concat($talent_model,' ','Track')"/>
            </xsl:if>
        </Talent_Track>
        
        <!-- Coach (Custom Manager) Informatopn -->
        
        <Coach_Name>
            <!-- <xsl:value-of select="empJobRelationshipNav/EmpJobRelationships[relationshipType = $JobRel_Coach_OptionId]/relUserNav/User/defaultFullName"/> -->
            <xsl:variable name="f_name" select="empJobRelationshipNav/EmpJobRelationships[relationshipType = $JobRel_Coach_OptionId]/relEmploymentNav/EmpEmployment/personNav/PerPerson/personalInfoNav/PerPersonal/firstName"/>
            <xsl:variable name="l_name" select="empJobRelationshipNav/EmpJobRelationships[relationshipType = $JobRel_Coach_OptionId]/relEmploymentNav/EmpEmployment/personNav/PerPerson/personalInfoNav/PerPersonal/lastName"/>
            
            <xsl:choose>
               <xsl:when test="$f_name != '' and $l_name != ''">
                 <xsl:value-of select="concat($f_name,' ',$l_name)"/>
               </xsl:when>
               <xsl:when test="$f_name != ''">
                <xsl:value-of select="$f_name"/>
               </xsl:when>
               <xsl:otherwise>
                 <xsl:value-of select="$l_name"/>
               </xsl:otherwise>
            </xsl:choose>
           
        </Coach_Name>
        
        <Coach_PersonIdExternal><xsl:value-of select="empJobRelationshipNav/EmpJobRelationships[relationshipType = $JobRel_Coach_OptionId]/relUserNav/User/empInfo/EmpEmployment/personIdExternal"/></Coach_PersonIdExternal>
        
        <Coach_Email><xsl:value-of select="empJobRelationshipNav/EmpJobRelationships[relationshipType = $JobRel_Coach_OptionId]/relUserNav/User/email"/></Coach_Email>
        
        
        <!--Cost center Information-->
          <xsl:variable name="cost_center" select="jobInfoNav/EmpJob/costCenter"/>
          <xsl:variable name="cust_Business" select="map:get($FO_CostCenter_HashMap,string($cost_center))[2]"/>
          <xsl:variable name="cust_BusinessArea" select="map:get($FO_CostCenter_HashMap,string($cost_center))[3]"/>
          
          
          <costCenter><xsl:value-of select="$cost_center"/></costCenter>
          
          <costCenter_name><xsl:value-of select="map:get($FO_CostCenter_HashMap,string($cost_center))[1]"/></costCenter_name>
          <costCenter_cust_Business><xsl:value-of select="substring-after(map:get($FO_CostCenter_HashMap,string($cost_center))[2],'-')"/></costCenter_cust_Business>
          <costCenter_cust_BusinessArea><xsl:value-of select="substring-after(map:get($FO_CostCenter_HashMap,string($cost_center))[3],'-')"/></costCenter_cust_BusinessArea>
          <costCenter_cust_BusinessLine><xsl:value-of select="substring-after(map:get($FO_CostCenter_HashMap,string($cost_center))[4],'-')"/></costCenter_cust_BusinessLine>
          <costCenter_cust_Region><xsl:value-of select="substring-after(map:get($FO_CostCenter_HashMap,string($cost_center))[5],'-')"/></costCenter_cust_Region>
          <costCenter_cust_BusinessSubLine><xsl:value-of select="substring-after(map:get($FO_CostCenter_HashMap,string($cost_center))[7],'-')"/></costCenter_cust_BusinessSubLine>
          
          <!--<Facility_Region><xsl:value-of select="jobInfoNav/EmpJob/locationNav/FOLocation/locationGroupNav/FOLocationGroup/name"/></Facility_Region>-->
          <Facility_Region><xsl:value-of select="substring-after(map:get($FO_CostCenter_HashMap,string($cost_center))[5],'-')"/></Facility_Region>
          
          
          <costCenter_cust_ZZOFFICE><xsl:value-of select="map:get($Picklist_SWIFT_OFFICE_CODE_Hashmap,string(map:get($FO_CostCenter_HashMap,string($cost_center))[6]))"/></costCenter_cust_ZZOFFICE>
          
          
          <employeeClass><xsl:value-of select="map:get($Picklist_EmployeeClass_HashMap,string(jobInfoNav/EmpJob/employeeClass))"/></employeeClass>
          <emplStatus><xsl:value-of select="$employee_status"/></emplStatus>
          
        
        <!--Seperation details -->
        
        
        <!--<Seperation_Effective_Date>
            <xsl:if test="(endDate != '')">
                 <xsl:value-of select="format-dateTime(endDate,'[M01]/[D01]/[Y0001]')"/>
            </xsl:if>
        </Seperation_Effective_Date>-->
        
        <!--<Seperation_Effective_Date>
			<xsl:if test="(jobInfoNav/EmpJob/startDate != '')">
			<xsl:variable name="endDate" select="xs:dateTime(jobInfoNav/EmpJob/startDate) + xs:dayTimeDuration('P1D')"/>
			<xsl:value-of select="format-dateTime($endDate,'[M01]/[D01]/[Y0001]')"/>
			</xsl:if>
		</Seperation_Effective_Date>-->
		
		<Seperation_Effective_Date>
			<xsl:if test="(endDate != '')">
			<xsl:variable name="endDate" select="xs:dateTime(endDate) + xs:dayTimeDuration('P1D')"/>
			<xsl:value-of select="format-dateTime($endDate,'[M01]/[D01]/[Y0001]')"/>
			</xsl:if>
		</Seperation_Effective_Date>
        
        <Seperation_Type_Desc>
            <xsl:if test="($employee_status = 'T' or $employee_status = 'R')">
                 <xsl:choose>
                     <xsl:when test="starts-with($event_reason,'INVOL')">
                         <xsl:text>Involuntary</xsl:text>
                     </xsl:when>
                      <xsl:when test="starts-with($event_reason,'VOL')">
                         <xsl:text>Voluntary</xsl:text>
                     </xsl:when>
                 </xsl:choose>
            </xsl:if>
        </Seperation_Type_Desc>
        
        <Last_Date_Worked>
             <xsl:if test="(lastDateWorked != '')">
                 <xsl:value-of select="format-dateTime(lastDateWorked,'[M01]/[D01]/[Y0001]')"/>
            </xsl:if>
            <xsl:if test="(lastDateWorked = '') and (endDate != '')">
                 <xsl:value-of select="format-dateTime(endDate,'[M01]/[D01]/[Y0001]')"/>
            </xsl:if>
        </Last_Date_Worked>
        <Employment_EndDate>
             <xsl:if test="(endDate != '')">
                 <xsl:value-of select="format-dateTime(endDate,'[M01]/[D01]/[Y0001]')"/>
            </xsl:if>
        </Employment_EndDate>
          
         
         <!-- Leave Of Absence Details -->
         
        <LOA_Reason_Code>
             <xsl:if test="$event_code = '10'">
                 <xsl:value-of select="$event_reason"/>
             </xsl:if>
        </LOA_Reason_Code>
        
        <LOA_Reason_Desc>
             <xsl:if test="$event_code = '10'">
                 <xsl:value-of select="map:get($FO_Event_Reason_HashMap,string($event_reason))"/>
             </xsl:if>
        </LOA_Reason_Desc>

        <Leave_StartDate>
             <xsl:if test="$event_code = '10' and userNav/User/userIdOfEmployeeTimeNav/EmployeeTime[1]/approvalStatus eq 'APPROVED' and userNav/User/userIdOfEmployeeTimeNav/EmployeeTime[1]/startDate != ''">
                 <xsl:value-of select="format-dateTime(userNav/User/userIdOfEmployeeTimeNav/EmployeeTime[1]/startDate,'[M01]/[D01]/[Y0001]')"/>
             </xsl:if>
        </Leave_StartDate> 
        <Leave_EndDate>
             <xsl:if test="$event_code = '10' and userNav/User/userIdOfEmployeeTimeNav/EmployeeTime[1]/approvalStatus eq 'APPROVED' and userNav/User/userIdOfEmployeeTimeNav/EmployeeTime[1]/endDate != ''">
                 <xsl:value-of select="format-dateTime(userNav/User/userIdOfEmployeeTimeNav/EmployeeTime[1]/endDate,'[M01]/[D01]/[Y0001]')"/>
             </xsl:if>
        </Leave_EndDate> 
          
       
        <Action_Type>
            <xsl:if test="$event_code = 'NS'">
                 <xsl:value-of select="$event_code"/>
             </xsl:if>
        </Action_Type>
        
        <DateOfResignation>
            <xsl:if test="(payrollEndDate != '')">
                 <xsl:value-of select="format-dateTime(payrollEndDate,'[M01]/[D01]/[Y0001]')"/>
            </xsl:if>
        </DateOfResignation>
        
        <FLAG_EMP_STATUS>
            <xsl:choose>
                <xsl:when test="$event_code = '10'">
                    <xsl:text>LOA</xsl:text>
                </xsl:when>
                <xsl:when test="$event_code = 'NS'">
                    <xsl:text>NS</xsl:text>
                </xsl:when>
                 <xsl:when test="$employee_status = 'T' or $employee_status = 'R'">
                    <xsl:text>FT</xsl:text>
                </xsl:when>
                <xsl:when test="(($event_code = 'H' or $event_code = 'R') and (number(translate(substring-before(startDate,'T'),'-','')) gt number(translate($TODAY,'-','')))) ">
                    <xsl:text>FH</xsl:text>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:text>A</xsl:text>
                </xsl:otherwise>
            </xsl:choose>
        </FLAG_EMP_STATUS>
        
        
         <xsl:variable name="cust_Business_desc" select="substring-after($cust_Business,'-')"/>
         <xsl:variable name="cust_BusinessArea_desc" select="substring-after($cust_BusinessArea,'-')"/>
         
         <!-- Exception details -->
         <xsl:variable name="exception_type_value">
            <xsl:choose>
                 <xsl:when test="((upper-case($cust_Business_desc) = 'CONSULTING') and (upper-case($cust_BusinessArea_desc) = 'DC PLATFORMS'))">
                     <xsl:text>DC Platforms</xsl:text>
                 </xsl:when>
                 <xsl:when test="((upper-case($cust_Business_desc) = 'CONSULTING') and (upper-case($cust_BusinessArea_desc) = 'NATIONAL CONSULTING'))">
                     <xsl:text>National Consulting</xsl:text>
                 </xsl:when>
                 <xsl:when test="((upper-case($talent_model) = 'PROJECT DELIVERY'))">
                    <xsl:text>Project Delivery Model</xsl:text>
                 </xsl:when>
                 <xsl:when test="(upper-case($job_level_desc) = 'ADMIN MANAGING DIRECTOR' or upper-case($job_level_desc) = 'CS DIRECTOR - ND' or upper-case($job_level_desc) = 'CS MANAGING DIRECTOR' or upper-case($job_level_desc) = 'PARTNER/PRINCIPAL' or upper-case($job_level_desc) = 'XIN-CS MANAGING DIRECTOR' or contains(upper-case($job_level_desc),'MANAGING DIRECTOR'))">
					<xsl:text>PPMD</xsl:text>
				 </xsl:when>
             </xsl:choose>
         </xsl:variable>
         
         <Exception_Type><xsl:value-of select="$exception_type_value"/></Exception_Type>
         
         <TODAY><xsl:value-of select="$TODAY"/></TODAY>
         <employment_startDate>
             <xsl:if test="startDate != ''">
                 <xsl:value-of select="substring-before(startDate,'T')"/>
             </xsl:if>
         </employment_startDate>
         
         <job_endDate><xsl:value-of select="jobInfoNav/EmpJob/endDate"/></job_endDate>
          
          <countryOfCompany><xsl:value-of select="jobInfoNav/EmpJob/countryOfCompany"/></countryOfCompany>
        
        
          <eventReason><xsl:value-of select="jobInfoNav/EmpJob/eventReason"/></eventReason>
          <customString7><xsl:value-of select="jobInfoNav/EmpJob/customString7"/></customString7>
          <division><xsl:value-of select="jobInfoNav/EmpJob/division"/></division>
         
          
          <department><xsl:value-of select="jobInfoNav/EmpJob/department"/></department>
         
          <job_startDate><xsl:value-of select="jobInfoNav/EmpJob/startDate"/></job_startDate>
          <job_seqNumber><xsl:value-of select="jobInfoNav/EmpJob/seqNumber"/></job_seqNumber>
          
          
          
          <BLANK/>
        </EmpEmployment>
      </xsl:for-each>
    </EmpEmployment>
  </xsl:template>
  
</xsl:stylesheet>