<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    <xsl:output method="xml" indent="yes" omit-xml-declaration="yes" />
    <xsl:strip-space elements="*"/>

    <xsl:template match="*">
        <xsl:element name="{local-name()}">
            <xsl:apply-templates/>
        </xsl:element>
    </xsl:template>
</xsl:stylesheet>