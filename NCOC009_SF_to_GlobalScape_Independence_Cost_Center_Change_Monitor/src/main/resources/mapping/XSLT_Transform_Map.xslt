<?xml version="1.0" encoding="UTF-8"?>
<!--This Groovy script filters and transforms the incoming SF emp payload to the ac as a source for target required format.-->

<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:hci="http://sap.com/it/" exclude-result-prefixes="hci">
    <xsl:template match="/">
		<FOCostCenter>
			<xsl:for-each select="/FOCostCenter/Row">
			    
                <!-- Populate field values for each employee-->
					<FOCostCenter>
						
						
						<xsl:copy-of select="FOCostCenter[1]/lastModifiedOn"/>
						<xsl:copy-of select="FOCostCenter[1]/startDate"/>
						<xsl:copy-of select="FOCostCenter[1]/endDate"/>
						<xsl:copy-of select="FOCostCenter[1]/externalCode"/>
						<xsl:copy-of select="FOCostCenter[1]/name"/>
					
							<xsl:if test="FOCostCenter[1]/cust_Business != FOCostCenter[2]/cust_Business">
								<cust_Business_new><xsl:value-of select="FOCostCenter[1]/cust_Business"/></cust_Business_new>
								<cust_Business_old><xsl:value-of select="FOCostCenter[2]/cust_Business"/></cust_Business_old>
							</xsl:if>
							<xsl:if test="FOCostCenter[1]/cust_BusinessArea != FOCostCenter[2]/cust_BusinessArea">
								<cust_BusinessArea_new><xsl:value-of select="FOCostCenter[1]/cust_BusinessArea"/></cust_BusinessArea_new>
								<cust_BusinessArea_old><xsl:value-of select="FOCostCenter[2]/cust_BusinessArea"/></cust_BusinessArea_old>
							</xsl:if>
							<xsl:if test="FOCostCenter[1]/cust_BusinessLine != FOCostCenter[2]/cust_BusinessLine">
								<cust_BusinessLine_new><xsl:value-of select="FOCostCenter[1]/cust_BusinessLine"/></cust_BusinessLine_new>
								<cust_BusinessLine_old><xsl:value-of select="FOCostCenter[2]/cust_BusinessLine"/></cust_BusinessLine_old>
							</xsl:if>
							<xsl:if test="FOCostCenter[1]/cust_BusinessSubLine != FOCostCenter[2]/cust_BusinessSubLine">
								<cust_BusinessSubLine_new><xsl:value-of select="FOCostCenter[1]/cust_BusinessSubLine"/></cust_BusinessSubLine_new>
								<cust_BusinessSubLine_old><xsl:value-of select="FOCostCenter[2]/cust_BusinessSubLine"/></cust_BusinessSubLine_old>
							</xsl:if>

					</FOCostCenter>

			</xsl:for-each>		
		</FOCostCenter>
	</xsl:template>
</xsl:stylesheet>