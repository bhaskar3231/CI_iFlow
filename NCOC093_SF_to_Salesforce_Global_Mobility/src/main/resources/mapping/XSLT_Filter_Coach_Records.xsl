<?xml version="1.0" encoding="UTF-8"?>
<!-- This Script will be processed on incoming payload and it will filter all the coach Records -->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes" omit-xml-declaration="yes"/>
<xsl:template match="/">
<EmpEmployment>
<xsl:for-each select="/EmpEmployment/EmpEmployment">
<EmpEmployment>
<xsl:copy-of select="userNav"/>
<xsl:copy-of select="startDate"/>
<xsl:copy-of select="lastModifiedDateTime"/>
<xsl:copy-of select="endDate"/>
<xsl:copy-of select="assignmentClass"/>
<xsl:copy-of select="isContingentWorker"/>
<xsl:copy-of select="userId"/>
<xsl:copy-of select="lastDateWorked"/>
<xsl:copy-of select="originalStartDate"/>
<xsl:copy-of select="jobInfoNav"/>
<xsl:copy-of select="personNav"/>
<xsl:copy-of select="personIdExternal"/>
<xsl:copy-of select="empWorkPermitNav"/>

<empJobRelationshipNav>
   <xsl:for-each select="empJobRelationshipNav/EmpJobRelationships">
   
	     <xsl:if test="relationshipTypeNav/PicklistOption/localeLabel='Coach'">
		 <EmpJobRelationships>
<relationshipTypeNav>
<PicklistOption>
<externalCode><xsl:value-of select="relationshipTypeNav/PicklistOption/externalCode"/></externalCode>
<localeLabel><xsl:value-of select="relationshipTypeNav/PicklistOption/localeLabel"/></localeLabel>
</PicklistOption>
</relationshipTypeNav>

<userId><xsl:value-of select="userId"/></userId>
<relUserId><xsl:value-of select="relUserId"/></relUserId>
</EmpJobRelationships>
		 </xsl:if>
		 </xsl:for-each>
   
</empJobRelationshipNav>
</EmpEmployment>
</xsl:for-each>
</EmpEmployment>
</xsl:template>
</xsl:stylesheet>