<?xml version="1.0"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method="xml" omit-xml-declaration="yes" indent="yes"/>
  <xsl:template match="/">
  <cust_IndependenceProfile>
    <xsl:for-each select="/EmpEmployment/Group">
        
        <xsl:variable name="v_GA_Host_Company" select="EmpEmployment[1]/countryOfCompany"/>
        <xsl:variable name="v_ST_Home_Company" select=".//EmpEmployment[assignmentClass = 'ST' and emplStatus = 'D']/countryOfCompany"/>
        
        <xsl:variable name="v_GA_Host_UserId" select="EmpEmployment[1]/userId"/>
        <xsl:variable name="v_ST_Home_UserId" select=".//EmpEmployment[assignmentClass = 'ST' and emplStatus = 'D']/userId"/>
        
        <xsl:variable name="v_GA_Host_StartDate" select="EmpEmployment[1]/empjob_startDate"/>
        <xsl:variable name="v_ST_Home_StartDate" select=".//EmpEmployment[assignmentClass = 'ST' and emplStatus = 'D']/empjob_startDate"/>
        
        <xsl:variable name="v_GA_Host_CostCenter" select="EmpEmployment[1]/costCenter"/>
        <xsl:variable name="v_ST_Home_CostCenter" select=".//EmpEmployment[assignmentClass = 'ST' and emplStatus = 'D']/costCenter"/>
        
        <xsl:variable name="v_GA_Host_CostCenterName" select="EmpEmployment[1]/costCenter_name"/>
        <xsl:variable name="v_ST_Home_CostCenterName" select=".//EmpEmployment[assignmentClass = 'ST' and emplStatus = 'D']/costCenter_name"/>
        
        <xsl:choose>
           <xsl:when test="($v_ST_Home_Company != 'USA' and $v_ST_Home_Company != 'IND') and ($v_GA_Host_Company = 'USA' or $v_GA_Host_Company = 'IND')">
               <cust_IndependenceProfile>
                   <externalCode><xsl:value-of select="$v_GA_Host_UserId"/></externalCode>
                   <effectiveStartDate><xsl:value-of select="$v_GA_Host_StartDate"/></effectiveStartDate>
                   <mdfSystemEffectiveEndDate>9999-12-31T00:00:00</mdfSystemEffectiveEndDate>
                   <costCenter><xsl:value-of select="substring($v_GA_Host_CostCenter,6,string-length($v_GA_Host_CostCenter))"/></costCenter>
                   <costCenter_name><xsl:value-of select="$v_GA_Host_CostCenterName"/></costCenter_name>
                </cust_IndependenceProfile>
           </xsl:when>
           <xsl:when test="($v_ST_Home_Company = 'USA') and ($v_GA_Host_Company = 'IND')">
               <cust_IndependenceProfile>
                   <externalCode><xsl:value-of select="$v_GA_Host_UserId"/></externalCode>
                   <effectiveStartDate><xsl:value-of select="$v_GA_Host_StartDate"/></effectiveStartDate>
                   <mdfSystemEffectiveEndDate>9999-12-31T00:00:00</mdfSystemEffectiveEndDate>
                   <costCenter><xsl:value-of select="substring($v_GA_Host_CostCenter,6,string-length($v_GA_Host_CostCenter))"/></costCenter>
                   <costCenter_name><xsl:value-of select="$v_GA_Host_CostCenterName"/></costCenter_name>
                </cust_IndependenceProfile>
           </xsl:when>
           <xsl:when test="($v_ST_Home_Company = 'USA') and ($v_GA_Host_Company != 'IND' or $v_GA_Host_Company != 'USA')">
               <cust_IndependenceProfile>
                   <externalCode><xsl:value-of select="$v_ST_Home_UserId"/></externalCode>
                   <effectiveStartDate><xsl:value-of select="$v_ST_Home_StartDate"/></effectiveStartDate>
                   <mdfSystemEffectiveEndDate>9999-12-31T00:00:00</mdfSystemEffectiveEndDate>
                   <costCenter><xsl:value-of select="substring($v_ST_Home_CostCenter,6,string-length($v_ST_Home_CostCenter))"/></costCenter>
                   <costCenter_name><xsl:value-of select="$v_ST_Home_CostCenterName"/></costCenter_name>
                </cust_IndependenceProfile>
           </xsl:when>
           
        </xsl:choose>
        
    </xsl:for-each>
  </cust_IndependenceProfile>
</xsl:template>
</xsl:stylesheet>