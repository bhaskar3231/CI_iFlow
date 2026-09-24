<!--XSLT to filter only future Hire,Rehire,Terminated,Retired Records-->
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:map="java:java.util.Map" extension-element-prefixes="map">
	<xsl:output method="xml" omit-xml-declaration="yes" encoding="UTF-8" indent="yes"/>
	
	<xsl:param name="FUTURE_SEPARATION_DATE"/>
	<xsl:param name="FUTURE_SEPARATION_STATUS"/>
	<xsl:param name="hire_startDate"/>
	<xsl:param name="hire_endDate"/>
	<xsl:param name="last_run_date"/>
	<xsl:param name="Picklist_EmployeeStatus_Hashmap"/>
	
	
	<xsl:template match="/">
	
    <xsl:variable name="FUTURE_SEPARATION_STATUS_array" select="tokenize($FUTURE_SEPARATION_STATUS,',')" />
    
		<EmpEmployment>
		<xsl:for-each select="/EmpEmployment/EmpEmployment">
					
					<xsl:variable name="employee_status" select="map:get($Picklist_EmployeeStatus_Hashmap,string(jobInfoNav/EmpJob/emplStatus))"/>
					
					<xsl:if test="originalStartDate ge $hire_startDate and originalStartDate le $hire_endDate and $employee_status ne 'RNS'">
							<xsl:copy-of select="."/>
					</xsl:if>
					
					<xsl:if test="endDate ge $last_run_date and endDate le $FUTURE_SEPARATION_DATE and $FUTURE_SEPARATION_STATUS_array = $employee_status">
							<xsl:copy-of select="."/>
					</xsl:if>

		</xsl:for-each>
		</EmpEmployment>
	</xsl:template>
</xsl:stylesheet>