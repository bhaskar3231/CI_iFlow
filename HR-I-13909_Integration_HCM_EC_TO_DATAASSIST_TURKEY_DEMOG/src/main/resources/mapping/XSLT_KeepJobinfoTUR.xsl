<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="3.0" xmlns:xs="http://www.w3.org/2001/XMLSchema">
    <xsl:output method="xml" indent="yes"/>
    <!-- Match the root and copy it -->
    <xsl:template match="/*">
        <xsl:copy>
            <xsl:apply-templates select="node()|@*"/>
        </xsl:copy>
    </xsl:template>
    <!-- Copy elements and attributes by default -->
    <xsl:template match="node()|@*">
        <xsl:copy>
            <xsl:apply-templates select="node()|@*"/>
        </xsl:copy>
    </xsl:template>
    <!-- Only keep <employment_information> whose <job_information>/<company_territory_code> is 'CZE' -->
    <xsl:template match="employment_information">
        <xsl:if test="job_information/company_territory_code = 'TUR'">
            <xsl:copy>
                <xsl:apply-templates select="node()|@*"/>
            </xsl:copy>
        </xsl:if>
    </xsl:template>
</xsl:stylesheet>