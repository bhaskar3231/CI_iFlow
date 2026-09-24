<?xml version="1.0" encoding="UTF-8"?>
<!-- This script splits the incoming payload and gives output containing each sector & respective industry details  -->
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:hci="http://sap.com/it/" exclude-result-prefixes="hci">
	<xsl:template match="/">
		<PickListValueV2>
			<xsl:for-each select="PickListValueV2/PickListValueV2">
				<!-- store all data minus Industry segment in a variable i.e. Sector data -->
				<xsl:variable name="var_data" select="./*[not(name()='PickListValueV2')]"/>
				<!-- Loop over each industry node which is effective dated or future dated and build the output-->
				<xsl:for-each select="PickListValueV2">
					<PickListValueV2>
					<!-- enrich sector stored in var_data -->  
						<xsl:copy-of select="$var_data"/>
						<PickListValueV2>
						    <!-- enrich industry data -->  
							<xsl:copy-of select="./*"/>
						</PickListValueV2>
					</PickListValueV2>
				</xsl:for-each>
			</xsl:for-each>
		</PickListValueV2>
	</xsl:template>
</xsl:stylesheet>