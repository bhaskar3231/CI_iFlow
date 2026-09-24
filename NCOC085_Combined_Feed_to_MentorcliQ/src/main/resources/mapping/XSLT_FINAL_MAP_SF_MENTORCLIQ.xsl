<!-- This XSLT Script transforms the structure of the incoming payload and also populates the picklist values using stored hashmaps-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0" xmlns:map="java:java.util.Map" xmlns:xs="http://www.w3.org/2001/XMLSchema" extension-element-prefixes="map" exclude-result-prefixes="xs">
<xsl:output method="xml" indent="yes" omit-xml-declaration="yes"/>

 <!-- Include Properties -->
    <xsl:param name="TODAY"/>
    <xsl:param name="multiMap_PMReport"/>
	<xsl:param name="multiMap_TIL"/>
	<xsl:param name="DNU_PersonIds"/>
	<xsl:param name="BCLP_PersonIds"/>
	<xsl:param name="BUDDY_PERSON_IDS"/>
     
	    
   <xsl:template match="/">
    
    <EmpEmployment>
      <xsl:for-each select="/EmpEmployment/EmpEmployment">
        <EmpEmployment>
            <xsl:variable name="flag_emp_status" select="FLAG_EMP_STATUS"/>
            <xsl:variable name="exception_type" select="Exception_Type"/> 
          <!-- Biographic Information -->
          
       	  <GE_PersonnelID><xsl:value-of select="personIdExternal"/></GE_PersonnelID>
          <!--<Personnel_Number><xsl:value-of select="personIdExternal"/></Personnel_Number>-->
          <First_Name><xsl:value-of select="firstName"/></First_Name>
          <Last_Name><xsl:value-of select="lastName"/></Last_Name>
          <No_of_Counselees_for_the_Professional/>
		  <Deloitte_Email_Address><xsl:value-of select="Business_EmailAddress"/></Deloitte_Email_Address>
		  <Counselee_Personal_Email><xsl:value-of select="Personal_EmailAddress"/></Counselee_Personal_Email>
		  <Title><xsl:value-of select="salutation"/></Title>
		  
		  <!-- Job Infomration -->
		  <Position_Title><xsl:value-of select="customString52"/></Position_Title>
		  <Job_Level_Desc><xsl:value-of select="job_level"/></Job_Level_Desc>
		  <Job_Code><xsl:value-of select="jobCode"/></Job_Code>
		  <Anniversary_Date><xsl:value-of select="hireDate"/></Anniversary_Date>
		  
		  <!-- CostCenter Information -->
		  <Employee_Cost_Center><xsl:value-of select="costCenter"/></Employee_Cost_Center>
		  
		  
		  <Employee_Cost_Center_Desc><xsl:value-of select="costCenter_name"/></Employee_Cost_Center_Desc>
		  <Current_Cost_Center_Business_Desc><xsl:value-of select="costCenter_cust_Business"/></Current_Cost_Center_Business_Desc>
		  <Current_Cost_Center_Business_Area_Desc><xsl:value-of select="costCenter_cust_BusinessArea"/></Current_Cost_Center_Business_Area_Desc>
		  <Current_Cost_Center_Business_Line_Desc><xsl:value-of select="costCenter_cust_BusinessLine"/></Current_Cost_Center_Business_Line_Desc>
		  <Current_Cost_Center_Region_Desc><xsl:value-of select="costCenter_cust_Region"/></Current_Cost_Center_Region_Desc>
		 
		  
		  <Facility_Region_Desc><xsl:value-of select="Facility_Region"/></Facility_Region_Desc>
		  <Personnel_Sub_Area><xsl:value-of select="costCenter_cust_ZZOFFICE"/></Personnel_Sub_Area>					
		  <FederalGPS_Indicator><xsl:value-of select="Federal_GPS_Indicator"/></FederalGPS_Indicator>
		  <LOA_Indicator><xsl:value-of select="LOA_Indicator"/></LOA_Indicator>		
		
		  <Hire_Type><xsl:value-of select="hireSource"/></Hire_Type>
		  
		  <!-- Industry Sector Information -->
		  <Current_Primary_Industry_Desc><xsl:value-of select="Primary_Industry"/></Current_Primary_Industry_Desc>					
		  <Current_Primary_Sector_Desc><xsl:value-of select="Primary_Sector"/></Current_Primary_Sector_Desc>
		  <Current_Secondary_Industry_Desc><xsl:value-of select="Secondary_Industry"/></Current_Secondary_Industry_Desc>				
		  <Current_Secondary_Sector_Desc><xsl:value-of select="Secondary_Sector"/></Current_Secondary_Sector_Desc>	
		  
		  <!-- Primary Secondary MArket Information -->
		  <Current_MarketOffering_Capability_Desc><xsl:value-of select="Primary_Market_Offering"/></Current_MarketOffering_Capability_Desc>					
		  <Current_Secondary_MarketOffering_Capability_Desc><xsl:value-of select="Secondary_Market_Offering"/></Current_Secondary_MarketOffering_Capability_Desc>
         
         
		 <GE_Rollup_Level_TIL>
		     <xsl:value-of select="map:get($multiMap_TIL,string(personIdExternal))[1]"/>
		 </GE_Rollup_Level_TIL>	
		 
		     <xsl:if test="xs:date(startDate) lt xs:date($TODAY)">
		        <Employee_Current_Tenure>
    		      <xsl:variable name="duration" select="xs:date($TODAY) - xs:date(startDate)" />
    		      <xsl:variable name="days" select="days-from-duration($duration)"/>
    		      <xsl:value-of select="format-number(($days div 365),'#.00')"/>
		        </Employee_Current_Tenure>
		     </xsl:if>
		     <xsl:if test="xs:date(startDate) ge xs:date($TODAY)">
		        <Employee_Current_Tenure>0</Employee_Current_Tenure>
		     </xsl:if>
		     
		 
		 <Employee_Subgroup_Desc><xsl:value-of select="employmentType"/></Employee_Subgroup_Desc>	
		 <!--<Competency_Area_Desc/>
		 <Competency_Desc/>
		 <Sub_Competency_Area_Desc/>
		 <Sub_Competency_Desc/>-->
		 <Capability><xsl:value-of select="Capability_Desc"/></Capability>
		 <Sub_Capability><xsl:value-of select="SubCapability_Desc"/></Sub_Capability>	
		 <Focus_Area_Desc><xsl:value-of select="FocusArea_Desc"/></Focus_Area_Desc>
		 <Talent_Model><xsl:value-of select="Talent_Model"/></Talent_Model>	
		 <Talent_Track><xsl:value-of select="Talent_Track"/></Talent_Track>				 
								
		 <!-- Coach (Custom Manager) Information -->
		<Current_Employee_Counselor_Name><xsl:value-of select="Coach_Name"/></Current_Employee_Counselor_Name>
		<Current_Employee_Counselor_ID><xsl:value-of select="Coach_PersonIdExternal"/></Current_Employee_Counselor_ID>
		<Employee_Counselor_Email_ID><xsl:value-of select="Coach_Email"/></Employee_Counselor_Email_ID>
		<Employee_Group_Desc><xsl:value-of select="employee_class_description"/></Employee_Group_Desc>
		
		<!-- Employee Seperation Information -->	
	    <Separation_Effective_Date><xsl:value-of select="Seperation_Effective_Date"/></Separation_Effective_Date>
		<Separation_Type><xsl:value-of select="Seperation_Type_Desc"/></Separation_Type>
		<Last_Day_Worked><xsl:value-of select="Last_Date_Worked"/></Last_Day_Worked>
		<Employment_End_Date><xsl:value-of select="Employment_EndDate"/></Employment_End_Date> 
				
		<!-- Leave Of Absence Details -->
		<LOA_Reason_Code><xsl:value-of select="LOA_Reason_Code"/></LOA_Reason_Code>
		<LOA_Reason_Desc><xsl:value-of select="LOA_Reason_Desc"/></LOA_Reason_Desc>
		<Valid_From><xsl:value-of select="Leave_StartDate"/></Valid_From>
		<Valid_To><xsl:value-of select="Leave_EndDate"/></Valid_To> 
		<Action_Type><xsl:value-of select="Action_Type"/></Action_Type>
		
		<!-- Inbound File Details -->
		
		<BCLP> 
			<xsl:if test="$flag_emp_status = 'A' or $flag_emp_status = 'LOA' or $flag_emp_status = 'FT'">
				<xsl:choose>
					<xsl:when test="contains($BCLP_PersonIds,personIdExternal)">
						<xsl:text>Yes</xsl:text>
					</xsl:when>
					<xsl:otherwise>
						<xsl:text>No</xsl:text>
					</xsl:otherwise>
				</xsl:choose>
		  </xsl:if>
		</BCLP>
		<DNU>
			<xsl:if test="$flag_emp_status = 'A' or $flag_emp_status = 'LOA' or $flag_emp_status = 'FT'">
				<xsl:choose>
					<xsl:when test="contains($DNU_PersonIds,personIdExternal)">
						<xsl:text>Yes</xsl:text>
					</xsl:when>
					<xsl:otherwise>
						<xsl:text>No</xsl:text>
					</xsl:otherwise>
				</xsl:choose>
		  </xsl:if>
		</DNU>
		
		<Exception_Type><xsl:value-of select="Exception_Type"/></Exception_Type>
		<Smart_Match_Eligible_OBA>
			<xsl:choose>
				<xsl:when test="$exception_type = 'DC Platforms'">
					<xsl:text>NA</xsl:text>
				</xsl:when>
				<xsl:when test="$exception_type = 'National Consulting'">
					<xsl:text>NA</xsl:text>
				</xsl:when>
				<xsl:when test="$exception_type = 'Project Delivery Model'">
					<xsl:text>Yes</xsl:text>
				</xsl:when>
				<xsl:when test="$exception_type = 'PPMD'">
					<xsl:text>NA</xsl:text>
				</xsl:when>
				<xsl:otherwise>
				   <xsl:text>Yes</xsl:text>
				</xsl:otherwise>
			</xsl:choose>
		</Smart_Match_Eligible_OBA> 
		
		<Smart_Match_Eligible_Coach>
			<xsl:choose>
				<xsl:when test="$exception_type = 'DC Platforms'">
					<xsl:text>NA</xsl:text>
				</xsl:when>
				<xsl:when test="$exception_type = 'National Consulting'">
					<xsl:text>NA</xsl:text>
				</xsl:when>
				<xsl:when test="$exception_type = 'Project Delivery Model'">
					<xsl:text>No</xsl:text>
				</xsl:when>
				<xsl:when test="$exception_type = 'PPMD'">
					<xsl:text>NA</xsl:text>
				</xsl:when>
				<xsl:otherwise>
				   <xsl:text>Yes</xsl:text>
				</xsl:otherwise>
			</xsl:choose>
		</Smart_Match_Eligible_Coach> 
		
		<Requires_Approval_OBA>
			<xsl:choose>
				<xsl:when test="$exception_type = 'DC Platforms'">
					<xsl:text>NA</xsl:text>
				</xsl:when>
				<xsl:when test="$exception_type = 'National Consulting'">
					<xsl:text>NA</xsl:text>
				</xsl:when>
				<xsl:when test="$exception_type = 'Project Delivery Model'">
					<xsl:text>No</xsl:text>
				</xsl:when>
				<xsl:when test="$exception_type = 'PPMD'">
					<xsl:text>NA</xsl:text>
				</xsl:when>
				<xsl:otherwise>
				   <xsl:text>No</xsl:text>
				</xsl:otherwise>
			</xsl:choose>
		</Requires_Approval_OBA> 
		
		<Requires_Approval_Coach>
			<xsl:choose>
				<xsl:when test="$exception_type = 'DC Platforms'">
					<xsl:text>NA</xsl:text>
				</xsl:when>
				<xsl:when test="$exception_type = 'National Consulting'">
					<xsl:text>NA</xsl:text>
				</xsl:when>
				<xsl:when test="$exception_type = 'Project Delivery Model'">
					<xsl:text>No</xsl:text>
				</xsl:when>
				<xsl:when test="$exception_type = 'PPMD'">
					<xsl:text>NA</xsl:text>
				</xsl:when>
				<xsl:otherwise>
				   <xsl:text>No</xsl:text>
				</xsl:otherwise>
			</xsl:choose>
		</Requires_Approval_Coach>
		
		<!-- PM Report Information -->
		<Misc1>
		  <xsl:if test="$flag_emp_status = 'FH'">
		    <xsl:text>New Hire</xsl:text>
		  </xsl:if>
		</Misc1>
		<Misc2/>	
	
		<Misc3>
			<xsl:if test="$flag_emp_status = 'A' or $flag_emp_status = 'LOA' or $flag_emp_status = 'FT'">
			   <xsl:value-of select="map:get($multiMap_PMReport,string(personIdExternal))[3]"/>
			</xsl:if>
		</Misc3>
		<Misc4>
			<xsl:if test="$flag_emp_status = 'A' or $flag_emp_status = 'LOA' or $flag_emp_status = 'FT'">
			   <xsl:value-of select="map:get($multiMap_PMReport,string(personIdExternal))[1]"/>
			</xsl:if>
		</Misc4>
		<Misc5>
		  <xsl:if test="$flag_emp_status = 'A' or $flag_emp_status = 'LOA' or $flag_emp_status = 'FT'">
			   <xsl:value-of select="map:get($multiMap_PMReport,string(personIdExternal))[2]"/>
			</xsl:if>
	    </Misc5>
		<Skill_Category>
			<xsl:if test="$flag_emp_status = 'A' or $flag_emp_status = 'LOA' or $flag_emp_status = 'FT'">
			   <xsl:value-of select="map:get($multiMap_PMReport,string(personIdExternal))[4]"/>
			</xsl:if>
		</Skill_Category>
		
		<Date_of_Resignation><xsl:value-of select="DateOfResignation"/></Date_of_Resignation>
		
		<Current_Cost_Center_Business_Sub_Line_Desc><xsl:value-of select="costCenter_cust_BusinessSubLine"/></Current_Cost_Center_Business_Sub_Line_Desc>
		<Buddy_Pool>
		  <xsl:if test="$flag_emp_status = 'A' or $flag_emp_status = 'LOA' or $flag_emp_status = 'FT'">
			<xsl:choose>
		    <xsl:when test="contains($BUDDY_PERSON_IDS,personIdExternal)">
				<xsl:text>Yes</xsl:text>
			</xsl:when>
			<xsl:otherwise>
			  <xsl:text>No</xsl:text>
			</xsl:otherwise>
		  </xsl:choose>
		  </xsl:if>
	    </Buddy_Pool>
	    
	    <job_startDate><xsl:value-of select="job_startDate"/></job_startDate>
	    <job_seqNumber><xsl:value-of select="job_seqNumber"/></job_seqNumber>
	    <emplStatus><xsl:value-of select="emplStatus"/></emplStatus>
	    
        </EmpEmployment>
      </xsl:for-each>
    </EmpEmployment>
  </xsl:template>
  
</xsl:stylesheet>