<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
<!-- Replace the empty value with NA -->
	<xsl:output method="xml" indent="yes" omit-xml-declaration="yes"/>
	<xsl:template match="/">
		<root>
		    <xsl:for-each select="/*/*">
				<contact>
					<xsl:for-each select="./child::*">
						<xsl:choose>
							<xsl:when test="node()">
								<xsl:copy-of select="."/>
							</xsl:when>
							<xsl:otherwise>
								<xsl:element name="{name()}">
									<xsl:text>NA</xsl:text>
								</xsl:element>
							</xsl:otherwise>
						</xsl:choose>
					</xsl:for-each>
				</contact>
				</xsl:for-each>
		</root>
	</xsl:template>
</xsl:stylesheet>
