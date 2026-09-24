<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="3.0">
  <xsl:output method="xml" indent="yes" omit-xml-declaration="yes"/>

  <xsl:template match="Root">
    <Root> 
      
            <xsl:for-each select="Record[1]/cust_IndependenceProfile/cust_IndependenceProfile">
      
        <Record>
            <xsl:variable name="pos" select="position()"/>
            <Personnelnumber><xsl:value-of select="/Root/Record[1]/cust_IndependenceProfile/cust_IndependenceProfile[$pos]/externalCode"/></Personnelnumber>
			<Begindate></Begindate>
			<Enddate></Enddate>
			<xsl:variable name="v_CostCenter" select="/Root/Record[2]/EmpEmployment/EmpEmployment[$pos]/jobInfoNav/EmpJob/costCenterNav/FOCostCenter/externalCode"/>
			<Costcentercode><xsl:value-of select="substring($v_CostCenter,6,string-length($v_CostCenter))"/></Costcentercode >
            <Costcentertext><xsl:value-of select="/Root/Record[2]/EmpEmployment/EmpEmployment[$pos]/jobInfoNav/EmpJob/costCenterNav/FOCostCenter/name"/></Costcentertext>
            <Oldscopevalue><xsl:value-of select="/Root/Record[2]/EmpEmployment/EmpEmployment[$pos]/cust_IndependenceProfile/cust_Scope"/></Oldscopevalue>
            <Newscopevalue><xsl:value-of select="/Root/Record[1]/cust_IndependenceProfile/cust_IndependenceProfile[$pos]/cust_Scope"/></Newscopevalue>
            <Oldrulevalue><xsl:value-of select="/Root/Record[2]/EmpEmployment/EmpEmployment[$pos]/cust_IndependenceProfile/cust_RuleSet"/></Oldrulevalue>
            <Newrulevalue><xsl:value-of select="/Root/Record[1]/cust_IndependenceProfile/cust_IndependenceProfile[$pos]/cust_RuleSet"/></Newrulevalue>
            <Message><xsl:value-of select="/Root/Record[1]/cust_IndependenceProfile/cust_IndependenceProfile[$pos]/Message"/></Message>
        </Record>
        
     </xsl:for-each>
      
    </Root>  
  </xsl:template>
</xsl:stylesheet>