<xsl:stylesheet version="3.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs">
    <!-- ========================================================= -->
    <!-- Parameters coming from integration properties             -->
    <!-- ========================================================= -->
    <xsl:param name="EmpChanglastRunDateTime" as="xs:string?"/>
    <xsl:param name="lastRunDateTimeManual" as="xs:string?"/>
    <!-- ========================================================= -->
    <!-- Derive lastRunDt                                          -->
    <!-- If lastRunDateManual is present and non-empty, use it     -->
    <!-- Otherwise, use lastRunDate                                -->
    <!-- ========================================================= -->
    <xsl:variable name="lastRunDt" as="xs:dateTime">
        <xsl:sequence select="
            xs:dateTime(
                if (normalize-space($lastRunDateTimeManual))
                then $lastRunDateTimeManual
                else $EmpChanglastRunDateTime
            )
        "/>
    </xsl:variable>
    <!-- Identity template -->
    <xsl:mode on-no-match="shallow-copy"/>
    <!-- ========================================================= -->
    <!-- Filter CompoundEmployee nodes                              -->
    <!-- Keep only those where a PaymentInformationDetailV3         -->
    <!-- lastModifiedDate >= lastRunDt                              -->
    <!-- ========================================================= -->
    <xsl:template match="CompoundEmployee">
        <xsl:if test="
            .//person/last_modified_on[xs:dateTime(.) >= $lastRunDt] or .//phone_information/last_modified_on[xs:dateTime(.) >= $lastRunDt] or .//email_information/last_modified_on[xs:dateTime(.) >= $lastRunDt] or .//personal_information/last_modified_on[xs:dateTime(.) >= $lastRunDt] or .//national_id_card/last_modified_on[xs:dateTime(.) >= $lastRunDt] or .//employment_information/last_modified_on[xs:dateTime(.) >= $lastRunDt] or .//employment_information/job_relation/last_modified_on[xs:dateTime(.) >= $lastRunDt] or .//employment_information/global_assignment_information/last_modified_on[xs:dateTime(.) >= $lastRunDt] or .//employment_information/PaymentInformationV3/lastModifiedDate[xs:dateTime(.) >= $lastRunDt] or .//employment_information/PaymentInformationV3/PaymentInformationDetailV3/lastModifiedDate[xs:dateTime(.) >= $lastRunDt] or .//employment_information/job_information/last_modified_on[xs:dateTime(.) >= $lastRunDt] or .//employment_information/compensation_information/last_modified_on[xs:dateTime(.) >= $lastRunDt] or .//employment_information/compensation_information/paycompensation_recurring/last_modified_on[xs:dateTime(.) >= $lastRunDt] or .//address_information/last_modified_on[xs:dateTime(.) >= $lastRunDt]
        ">
            <xsl:copy>
                <xsl:apply-templates select="@* | node()"/>
            </xsl:copy>
        </xsl:if>
    </xsl:template>
</xsl:stylesheet>