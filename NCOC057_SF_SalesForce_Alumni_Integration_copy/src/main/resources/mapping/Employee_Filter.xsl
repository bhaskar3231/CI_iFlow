<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
    <xsl:output method="xml" omit-xml-declaration="yes" encoding="UTF-8" indent="yes" />
    <xsl:template match="/">
        <EmpEmployment>
             <xsl:for-each-group select="/EmpEmployment/EmpEmployment" group-by="personIdExternal">
               
                 <xsl:if test="jobInfoNav[1]/EmpJob/emplStatusNav/PicklistOption/externalCode ne 'D'">
                    <xsl:copy-of select="current-group()[1]"/>
                </xsl:if>
                
                 
                
        	 </xsl:for-each-group>
		</EmpEmployment>
    </xsl:template>
</xsl:stylesheet>