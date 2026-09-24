<?xml version="1.0"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method="xml" omit-xml-declaration="yes" indent="yes"/>
  <xsl:template match="/">
  <EmpEmployment>
    <xsl:for-each-group select="/EmpEmployment/EmpEmployment" group-by="personIdExternal">
      <Group>
         <xsl:for-each select="current-group()">
		 	      <xsl:sort select="startDate" order="descending"/>
		 	      <EmpEmployment>
		 	          <xsl:copy-of select="personIdExternal"/>
		 	          <xsl:copy-of select="assignmentClass"/>
		 	          <xsl:copy-of select="userId"/>
		 	          <costCenter><xsl:value-of select="jobInfoNav/EmpJob/costCenter"/></costCenter>
		              <costCenter_name><xsl:value-of select="jobInfoNav/EmpJob/costCenterNav/FOCostCenter/name"/></costCenter_name>
    	              <countryOfCompany><xsl:value-of select="jobInfoNav/EmpJob/countryOfCompany"/></countryOfCompany>
                      <Event><xsl:value-of select="jobInfoNav/EmpJob/eventNav/PicklistOption/externalCode"/></Event>
                      <Event_name><xsl:value-of select="jobInfoNav/EmpJob/eventNav/PicklistOption/localeLabel"/></Event_name>
		 	          <emplStatus><xsl:value-of select="jobInfoNav/EmpJob/emplStatusNav/PicklistOption/externalCode"/></emplStatus>
		 	          <empjob_startDate><xsl:value-of select="jobInfoNav/EmpJob/startDate"/></empjob_startDate>
		 	      </EmpEmployment>
	       </xsl:for-each>
      </Group>
    </xsl:for-each-group>
</EmpEmployment>
</xsl:template>
</xsl:stylesheet>