<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="3.0">
<!-- This Script will be processed on incoming payload and it will fetch US record from incoming payload -->
   <xsl:output method="xml" indent="yes" omit-xml-declaration="yes"/>



 <xsl:template match="/">
    <EmpEmployment>
        <xsl:for-each select="/EmpEmployment/Group">
        
         <xsl:variable name="group_count" select="count(./EmpEmployment)"/>
         <xsl:choose>
           <xsl:when test="$group_count = 1">
              <xsl:copy-of select="./EmpEmployment"/>
             
           </xsl:when>
           
           <xsl:otherwise>
		   
		  <xsl:for-each select="EmpEmployment">
		  
		  <xsl:if test="jobInfoNav/EmpJob/countryOfCompany = 'USA' or jobInfoNav/EmpJob/countryOfCompany = 'IND'">
           
              
		
       
             
             <xsl:copy-of select="."/>
            
          
		   </xsl:if>
		   </xsl:for-each>
           </xsl:otherwise>
           
         </xsl:choose>
            
        </xsl:for-each>
        
    </EmpEmployment>  
  </xsl:template>
</xsl:stylesheet>