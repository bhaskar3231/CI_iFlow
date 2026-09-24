<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="3.0">
<!-- This Script will be processed on incoming payload and filter all the active records -->
      <xsl:output method="xml" indent="yes" omit-xml-declaration="yes"/>



 <xsl:template match="/">
   <root>
     <xsl:for-each select="/root/Group">
       <xsl:variable name="v_group_count" select="count(contact)"/>
       <xsl:variable name="v_active_count" select="count(contact[WCT_Employee_Status__c = 'Active'])"/>
       <xsl:variable name="v_terminated_count" select="count(contact[WCT_Employee_Status__c = 'Separated'])"/>
       
       <xsl:choose>
         <xsl:when test="$v_group_count = 1">
           <xsl:copy-of select="contact"/>
         </xsl:when>
         <xsl:when test="$v_active_count &gt;= 1">
           <xsl:copy-of select="contact[WCT_Employee_Status__c = 'Active'][1]"/>
         </xsl:when>
         <xsl:when test="$v_terminated_count &gt;= 1">
           <xsl:copy-of select="contact[WCT_Employee_Status__c = 'Separated'][1]"/>
         </xsl:when>
         <xsl:otherwise>
           <xsl:copy-of select="contact[1]"/>
         </xsl:otherwise>
       </xsl:choose>
     </xsl:for-each>
   </root>
  </xsl:template>
  
</xsl:stylesheet>