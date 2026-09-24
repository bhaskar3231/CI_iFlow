<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="3.0">
    <xsl:output method="xml" indent="yes"/>
    <!-- Identity template: copies everything as-is -->
    <xsl:template match="@* | node()">
        <xsl:copy>
            <xsl:apply-templates select="@* | node()"/>
        </xsl:copy>
    </xsl:template>
    <!-- Template to process text nodes and remove [ and ] -->
    <xsl:template match="text()">
        <xsl:value-of select="replace(replace(., '\[', ''), '\]', '')"/>
    </xsl:template>
</xsl:stylesheet>