<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" indent="yes" omit-xml-declaration="yes" />
    <xsl:strip-space elements="*"/>

    <xsl:template match="*">
      <cust_Level>
         <xsl:for-each select="/Messages/Message1/cust_sublevel/cust_sublevel">
          <xsl:variable name="v_sublevel_extcode" select="cust_toJobLevel/cust_Level/externalCode"/>
          <cust_Level>
            <JobLevel_Startdate>
              <xsl:if test="cust_toJobLevel/cust_Level/effectiveStartDate != ''">
                <xsl:value-of select="format-dateTime(cust_toJobLevel/cust_Level/effectiveStartDate,'[M01]/[D01]/[Y0001]')"/>
              </xsl:if>
            </JobLevel_Startdate>
            <JobLevelID><xsl:value-of select="cust_toJobLevel/cust_Level/externalCode"/></JobLevelID>
            <JobLevel_Description><xsl:value-of select="cust_toJobLevel/cust_Level/externalName"/></JobLevel_Description>
            <JobLevel_LastModifiedDate>
              <xsl:if test="cust_toJobLevel/cust_Level/lastModifiedDateTime != ''">
                <xsl:value-of select="format-dateTime(cust_toJobLevel/cust_Level/lastModifiedDateTime,'[M01]/[D01]/[Y0001]')"/>
              </xsl:if>
            </JobLevel_LastModifiedDate>
             <SubLevel_Startdate>
              <xsl:if test="effectiveStartDate != ''">
                <xsl:value-of select="format-dateTime(effectiveStartDate,'[M01]/[D01]/[Y0001]')"/>
              </xsl:if>
            </SubLevel_Startdate>
            <SubLevelID><xsl:value-of select="externalCode"/></SubLevelID>
            <SubLevel_Description><xsl:value-of select="externalName"/></SubLevel_Description>
            <SubLevel_LastModifiedDate>
              <xsl:if test="lastModifiedDateTime != ''">
                <xsl:value-of select="format-dateTime(lastModifiedDateTime,'[M01]/[D01]/[Y0001]')"/>
              </xsl:if>
            </SubLevel_LastModifiedDate>
          </cust_Level>
         </xsl:for-each>
         
         <!-- For Non-matching JobLevel Records -->
         <xsl:for-each select="/Messages/Message2/cust_Level/cust_Level">
          <xsl:variable name="v_sublevel_extcode" select="externalCode"/>
          <xsl:variable name="result">
             <xsl:for-each select="/Messages/Message1/cust_sublevel/cust_sublevel">
               <xsl:if test="cust_toJobLevel/cust_Level/externalCode = $v_sublevel_extcode">
                     <xsl:value-of select="externalCode"/>
                 </xsl:if>
             </xsl:for-each>
          </xsl:variable>
         <xsl:if test="$result = ''">
            <cust_Level>
               <JobLevel_Startdate>
              <xsl:if test="effectiveStartDate != ''">
                <xsl:value-of select="format-dateTime(effectiveStartDate,'[M01]/[D01]/[Y0001]')"/>
              </xsl:if>
            </JobLevel_Startdate>
            <JobLevelID><xsl:value-of select="externalCode"/></JobLevelID>
            <JobLevel_Description><xsl:value-of select="externalName"/></JobLevel_Description>
            <JobLevel_LastModifiedDate>
              <xsl:if test="lastModifiedDateTime != ''">
                <xsl:value-of select="format-dateTime(lastModifiedDateTime,'[M01]/[D01]/[Y0001]')"/>
              </xsl:if>
            </JobLevel_LastModifiedDate>
               <SubLevel_Startdate/>
            <SubLevelID/>
            <SubLevel_Description/>
            <SubLevel_LastModifiedDate/>
            </cust_Level>
         </xsl:if>
      
      </xsl:for-each>
      </cust_Level>
     
    </xsl:template>
</xsl:stylesheet>