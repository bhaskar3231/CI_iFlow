<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="3.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema">
    <xsl:template match="/">
        <queryCompoundEmployeeResponse>
            <xsl:for-each select="queryCompoundEmployeeResponse/CompoundEmployee">
                <!-- store CompoundEmployee in a variable -->
                <xsl:variable name="var_CompoundEmployee" select="./*" />
                    <xsl:variable name="var_emplstatus" select="person/employment_information/job_information/emplStatus/text()" />
                    <xsl:variable name="var_emplclass" select="person/employment_information/job_information/employee_class/text()" />
                    <xsl:variable name="var_country" select="person/employment_information/job_information/company_territory_code/text()" />
                    <xsl:if test="(($var_emplstatus = 'A' or $var_emplstatus = 'P' or $var_emplstatus = 'U' or $var_emplstatus = 'D' or $var_emplstatus = 'R' or $var_emplstatus = 'RNS' or $var_emplstatus = 'T') and ($var_emplclass != '9')) and ($var_country = 'TUR')">
                    <CompoundEmployee>
                    <xsl:copy-of select="$var_CompoundEmployee" />
                    </CompoundEmployee>
                    <!-- <xsl:text>I</xsl:text> -->
                </xsl:if>
                <!--<xsl:text>O</xsl:text> -->
                <!-- Ignore all other employees  -->
            </xsl:for-each>
        </queryCompoundEmployeeResponse>
    </xsl:template>
</xsl:stylesheet>