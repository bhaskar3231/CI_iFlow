<?xml version="1.0" encoding="UTF-8"?>
<!-- This Script will be processed on incoming payload and it will combined the incoming Records on the basic of personIdExternal -->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
   <xsl:output method="xml" indent="yes" omit-xml-declaration="yes"/>



 <xsl:template match="/">
    <EmpEmployment>
        <xsl:for-each-group select="/EmpEmployment/EmpEmployment" group-by="personIdExternal">
          <Group>
            <xsl:for-each select="current-group()">
             <xsl:sort select="Job_StartDate" order="descending"/>
             <xsl:copy-of select="."/>
            </xsl:for-each>
          </Group>
            
        </xsl:for-each-group>
        
    </EmpEmployment>  
  </xsl:template>
</xsl:stylesheet>