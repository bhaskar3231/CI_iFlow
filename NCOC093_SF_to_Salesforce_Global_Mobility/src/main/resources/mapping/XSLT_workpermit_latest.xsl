<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" version="3.0" exclude-result-prefixes="xs">
<!-- This Script will be processed on incoming payload and on sort the expiration date -->
   <xsl:output method="xml" indent="yes" omit-xml-declaration="yes"/>
   
   <xsl:template match="@*|node()">
     <xsl:copy>
       <xsl:apply-templates select = "@*|node()"/>
       
     </xsl:copy>
   </xsl:template>
   
   <xsl:template match="empWorkPermitNav">

<xsl:variable name = "sortedDate" as="xs:string*">
  
  <xsl:for-each select ="./EmpWorkPermit">
    
    <xsl:perform-sort select = "./expirationDate">
      
      <xsl:sort select = "."/>
      
    </xsl:perform-sort>
    
  </xsl:for-each>
  
</xsl:variable>

<xsl:variable name= "maxDate" select = "max($sortedDate)"/>
   
<empWorkPermitNav>  
<xsl:copy-of select= "./EmpWorkPermit[expirationDate = $maxDate]"/>
   
</empWorkPermitNav>     
  </xsl:template>
</xsl:stylesheet>
