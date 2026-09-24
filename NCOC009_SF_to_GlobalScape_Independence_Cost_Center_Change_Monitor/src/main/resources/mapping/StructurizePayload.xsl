<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="3.0">
  <xsl:output method="xml" indent="yes" omit-xml-declaration="yes"/>

  <xsl:template match="EmpEmployment">
    <cust_IndependenceProfile> 
      
            <xsl:for-each select="EmpEmployment">
      
        <cust_IndependenceProfile>
            <externalCode><xsl:value-of select="userId"/></externalCode>
            <effectiveStartDate><xsl:value-of select="jobInfoNav/EmpJob/startDate"/></effectiveStartDate>
            <mdfSystemEffectiveEndDate>9999-12-31T00:00:00</mdfSystemEffectiveEndDate>
            <costCenter><xsl:value-of select="jobInfoNav/EmpJob/costCenter"/></costCenter>
            <costCenter_name><xsl:value-of select="jobInfoNav/EmpJob/FOCostCenter/name"/></costCenter_name>
        	
        </cust_IndependenceProfile>
        
     </xsl:for-each>
    </cust_IndependenceProfile>  
  </xsl:template>
</xsl:stylesheet>