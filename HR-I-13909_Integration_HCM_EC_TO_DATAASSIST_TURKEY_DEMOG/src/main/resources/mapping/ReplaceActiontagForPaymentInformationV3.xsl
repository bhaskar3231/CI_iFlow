<xsl:stylesheet version="3.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs">

    <xsl:output method="xml" indent="yes" encoding="UTF-8"/>

    <!-- Identity template -->
    <xsl:mode on-no-match="shallow-copy"/>
    
    <!-- Main template for PaymentInformationV3 -->
    <xsl:template match="PaymentInformationV3">
        <!-- All siblings for comparison -->
        <xsl:variable name="allPI" select="../PaymentInformationV3"/>
        <xsl:variable name="curStart" select="xs:date(effectiveStartDate)"/>
        <!-- Find the 'previous' PaymentInformationV3 by max effectiveStartDate < current -->
        <xsl:variable name="prevPI" select="$allPI[xs:date(effectiveStartDate) = max($allPI[xs:date(effectiveStartDate) lt $curStart]/xs:date(effectiveStartDate))]"/>
        <!-- Compare all child elements of PaymentInformationDetailV3 -->
        <xsl:variable name="differs" as="xs:boolean">
            <xsl:choose>
                <xsl:when test="$prevPI">
                    <xsl:sequence select="
                        deep-equal(PaymentInformationDetailV3/*, $prevPI/PaymentInformationDetailV3/*) = false()"/>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:sequence select="false()"/>
                </xsl:otherwise>
            </xsl:choose>
        </xsl:variable>
        
        <xsl:copy>
            <xsl:choose>
                <!-- If anything is different, set as CHANGE -->
                <xsl:when test="$differs">
                    <action>CHANGE</action>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:apply-templates select="action"/>
                </xsl:otherwise>
            </xsl:choose>
            <xsl:apply-templates select="node()[not(self::action)]"/>
        </xsl:copy>
    </xsl:template>
    
</xsl:stylesheet>