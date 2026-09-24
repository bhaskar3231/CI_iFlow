<!-- This XSLT script is used to extract values from the payload coming from SF -->

<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" indent="yes" omit-xml-declaration="yes" />
    <xsl:strip-space elements="*"/>

    <xsl:template match="*">
        <Record>
          <PERSON_ID_EXTERNAL><xsl:value-of select="/ExternalEvent/events/event/params/param[name='personIdExternal']/value"/></PERSON_ID_EXTERNAL>
          <HOME_USER_ID><xsl:value-of select="/ExternalEvent/events/event/params/param[name='homeUserId']/value"/></HOME_USER_ID>
          <START_DATE><xsl:value-of select="concat(/ExternalEvent/events/event/entityKeys/entityKey[name='startDate']/value,'T00:00:00')"/></START_DATE>
          <HOST_USER_ID><xsl:value-of select="/ExternalEvent/events/event/entityKeys/entityKey[name='userId']/value"/></HOST_USER_ID>
        </Record>
    </xsl:template>
</xsl:stylesheet>