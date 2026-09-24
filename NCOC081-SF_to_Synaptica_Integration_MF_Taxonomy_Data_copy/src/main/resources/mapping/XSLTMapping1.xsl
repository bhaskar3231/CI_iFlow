<?xml version="1.0" encoding="UTF-8"?>
<!-- This script splits the incoming payload and gives output containing each employee class details  -->
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:hci="http://sap.com/it/" exclude-result-prefixes="hci">
	<xsl:param name="CURRENT_DATE"/>
	<xsl:template match="/">
		<PickListValueV2>
			<xsl:for-each select="PickListValueV2/PickListValueV2">
				<!-- store all data minus EICSKSM segment in a variable -->
				<xsl:variable name="var_data" select="./*[not(name()='EmpEmployment')]"/>
				<!-- Check and pick the cost center E1CSKSM segment which is effective dated or future dated-->
				<xsl:for-each select="EmpEmployment">
					<EmpClass>
						<xsl:copy-of select="$var_data"/>
						<EmpEmployment>
							<xsl:copy-of select="./*"/>
						</EmpEmployment>
					</EmpClass>
				</xsl:for-each>
			</xsl:for-each>
		</PickListValueV2>
	</xsl:template>
</xsl:stylesheet>