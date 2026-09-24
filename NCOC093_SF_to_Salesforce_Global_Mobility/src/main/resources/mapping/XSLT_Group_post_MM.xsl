<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="3.0">
<!-- This Script will be processed on incoming payload and it will group the incoming payload -->
   <xsl:output method="xml" indent="yes" omit-xml-declaration="yes"/>
   <xsl:template match="/">
    <root>
      <xsl:for-each-group select="/root/contact" group-by="WCT_Person_Id__c">
         <Group>
           <xsl:copy-of select="current-group()"/>
         </Group>
      </xsl:for-each-group>
    </root>
    
  </xsl:template>
</xsl:stylesheet>