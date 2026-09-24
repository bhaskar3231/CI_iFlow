<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:env="http://schemas.xmlsoap.org/soap/envelope/"
                xmlns:wd="urn:com.workday/bsvc"
                version="1.0">

    <xsl:output method="xml" indent="yes"/>

    <!-- Root template -->
    <xsl:template match="/">
        <Response_Data>
            <xsl:copy-of select="//wd:Response_Data/*"/>
        </Response_Data>
    </xsl:template>

</xsl:stylesheet>