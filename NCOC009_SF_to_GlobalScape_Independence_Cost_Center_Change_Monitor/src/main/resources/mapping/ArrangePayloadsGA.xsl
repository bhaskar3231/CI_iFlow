<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="3.0">
  <xsl:output method="xml" indent="yes" omit-xml-declaration="yes"/>
  <xsl:param name="currentDate"/>
  <xsl:variable name="v_Message1"></xsl:variable>
  <xsl:variable name="v_Message2"></xsl:variable>
  <xsl:template match="Root">
    <Root> 
      <xsl:for-each select="Record[1]/cust_IndependenceProfile/cust_IndependenceProfile">
        <Record>
            <xsl:variable name="pos" select="position()"/>
            <Personnelnumber><xsl:value-of select="/Root/Record[1]/cust_IndependenceProfile/cust_IndependenceProfile[$pos]/externalCode"/></Personnelnumber>
			      <Begindate><xsl:value-of select="$currentDate"/></Begindate>
			      <Enddate>31/12/9999</Enddate>
			      <Costcentercode><xsl:value-of select="/Root/Record[2]/cust_IndependenceProfile/cust_IndependenceProfile[$pos]/costCenter"/></Costcentercode >
            <Costcentertext><xsl:value-of select="/Root/Record[2]/cust_IndependenceProfile/cust_IndependenceProfile[$pos]/costCenter_name"/></Costcentertext>
            <Oldscopevalue><xsl:value-of select="/Root/Record[2]/cust_IndependenceProfile/cust_IndependenceProfile[$pos]/cust_IndependenceProfile/cust_Scope"/></Oldscopevalue>
            <Newscopevalue><xsl:value-of select="/Root/Record[1]/cust_IndependenceProfile/cust_IndependenceProfile[$pos]/cust_Scope"/></Newscopevalue>
            <Oldrulevalue><xsl:value-of select="/Root/Record[2]/cust_IndependenceProfile/cust_IndependenceProfile[$pos]/cust_IndependenceProfile/cust_RuleSet"/></Oldrulevalue>
            <Newrulevalue><xsl:value-of select="/Root/Record[1]/cust_IndependenceProfile/cust_IndependenceProfile[$pos]/cust_RuleSet"/></Newrulevalue>
            <xsl:variable name="v_NewRule" select="/Root/Record[1]/cust_IndependenceProfile/cust_IndependenceProfile[$pos]/cust_RuleSet"/>
            <xsl:variable name="v_NewScope" select="/Root/Record[1]/cust_IndependenceProfile/cust_IndependenceProfile[$pos]/cust_Scope"/>
            <xsl:choose>
            <xsl:when test="$v_NewRule = ''">
                <xsl:choose>
                    <xsl:when test="$v_NewScope = ''">
                    <Message>RULESET NOT FOUND,SCOPE NOT FOUND</Message>
                    </xsl:when>
                    <xsl:otherwise>
                    <Message>RULESET NOT FOUND</Message>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:when>
            <xsl:otherwise>
                <xsl:choose>
                    <xsl:when test="$v_NewScope = ''">
                    <Message>SCOPE NOT FOUND</Message>
                    </xsl:when>
                    <xsl:otherwise>
                    <Message></Message>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:otherwise>
            </xsl:choose>            

        </Record>
     </xsl:for-each>
    </Root>  
  </xsl:template>
</xsl:stylesheet>