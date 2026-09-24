<!-- This XSLT script is used to enclose field values in double quotes -->

<xsl:stylesheet version="1.0"
 xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output omit-xml-declaration="yes" indent="yes"/>
	<xsl:strip-space elements="*"/>

	<xsl:variable name="delimeter">"</xsl:variable>
	<xsl:template match="/">
		<xsl:element name="{name(*)}">
			<xsl:for-each select="/*/*">
				<xsl:element name="{local-name()}">
					<xsl:for-each select="./node()">
						<xsl:choose>
							<xsl:when test="self::node()[text() != '']">
								<xsl:element name="{local-name()}">
									<xsl:copy-of select="concat($delimeter,.,$delimeter)"/>
								</xsl:element>
							</xsl:when>
							<xsl:otherwise>
								<xsl:copy-of select="."/>
							</xsl:otherwise>
						</xsl:choose>    
					</xsl:for-each>
				</xsl:element>
			</xsl:for-each>
		</xsl:element>
	</xsl:template>
</xsl:stylesheet>
