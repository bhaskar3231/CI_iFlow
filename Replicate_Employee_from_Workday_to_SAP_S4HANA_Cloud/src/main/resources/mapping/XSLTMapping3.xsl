<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
      <xsl:output method="xml" indent="yes"  omit-xml-declaration="yes"/>
    <xsl:strip-space elements="*"/>

    <!-- Root template: iterates over each WD_S4_Type record and emits one JSON object per record.
         Multiple records are comma-separated. No outer array wrapper is produced. -->
    <xsl:template match="/">
        <xsl:for-each select="/ZI_HR_WORKFORCE_PERSON">
            <xsl:call-template name="buildRecord"/>
            <xsl:if test="position() != last()">
                <xsl:text>,&#10;</xsl:text>
            </xsl:if>
        </xsl:for-each>
    </xsl:template>


    <!-- Helper template: returns the string value of $node when present and non-empty,
         or falls back to $default (empty string by default). -->
    <xsl:template name="val">
        <xsl:param name="node"/>
        <xsl:param name="default" select="''"/>
        <xsl:choose>
            <xsl:when test="$node and string($node) != ''">
                <xsl:value-of select="$node"/>
            </xsl:when>
            <xsl:otherwise>
                <xsl:value-of select="$default"/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>


    <!-- Helper template: appends 'T00:00:00' to $node's value to form an ISO datetime string.
         Falls back to $default ('9999-12-31T00:00:00') when $node is absent or empty. -->
    <xsl:template name="dateTime">
        <xsl:param name="node"/>
        <xsl:param name="default" select="'9999-12-31T00:00:00'"/>
        <xsl:choose>
            <xsl:when test="$node and string($node) != ''">
                <xsl:value-of select="$node"/>
                <xsl:text>T00:00:00</xsl:text>
            </xsl:when>
            <xsl:otherwise>
                <xsl:value-of select="$default"/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>


    <!-- Main record builder: emits a single JSON object containing all sub-arrays
         (toAction, toOrganizationStructure, toPersonalData, toAddress, toCommunication)
         for one WD_S4_Type element. Called once per record from the root template. -->
    <xsl:template name="buildRecord">
        <!-- Shorthand variables: $perner = PersonnelNumber, $startDT = StartDate with time suffix -->
        <xsl:variable name="perner">
            <xsl:call-template name="val">
                <xsl:with-param name="node"    select="PersonnelNumber"/>
                <xsl:with-param name="default" select="''"/>
            </xsl:call-template>
        </xsl:variable>
        <xsl:variable name="startDT">
            <xsl:call-template name="dateTime">
                <xsl:with-param name="node"    select="StartDate"/>
                <xsl:with-param name="default" select="'9999-12-31T00:00:00'"/>
            </xsl:call-template>
        </xsl:variable>
        <xsl:text>{&#10;</xsl:text>
        <!-- ── Root level fields ── -->
        <xsl:text>  "PersonnelNumber" : "</xsl:text>
        <xsl:value-of select="$perner"/>
        <xsl:text>",&#10;</xsl:text>
        <xsl:text>  "ActionType"      : "</xsl:text>
        <xsl:call-template name="val">
            <xsl:with-param name="node"    select="ActionType"/>
            <xsl:with-param name="default" select="'01'"/>
        </xsl:call-template>
        <xsl:text>",&#10;</xsl:text>


        <!-- ── toAction: captures the hire action type, reason, and employment status ── -->
        <xsl:text>  "toAction" : [&#10;    {&#10;</xsl:text>
        <xsl:text>      "PersonnelNumber"   : "</xsl:text>
        <xsl:value-of select="$perner"/>
        <xsl:text>",&#10;</xsl:text>
        <xsl:text>      "SubType"           : "</xsl:text>
        <xsl:call-template name="val">
            <xsl:with-param name="node" select="SUBTY"/>
            <xsl:with-param name="default" select="''"/>
        </xsl:call-template>
        <xsl:text>",&#10;</xsl:text>
        <xsl:text>      "EndDate"           : "9999-12-31T00:00:00",&#10;</xsl:text>
        <xsl:text>      "StartDate"         : "</xsl:text>
        <xsl:value-of select="$startDT"/>
        <xsl:text>",&#10;</xsl:text>
        <xsl:text>      "ActionType"        : "</xsl:text>
        <xsl:call-template name="val">
            <xsl:with-param name="node" select="ActionType"/>
            <xsl:with-param name="default" select="'01'"/>
        </xsl:call-template>
        <xsl:text>",&#10;</xsl:text>
        <xsl:text>      "ReasonForAction"   : "01",&#10;</xsl:text>
        <xsl:text>      "EmployementStatus" : "3"&#10;</xsl:text>
        <xsl:text>    }&#10;  ],&#10;</xsl:text>


        <!-- ── toOrganizationStructure: captures company, personnel area, employee group/subgroup,
             payroll area, org unit, position, and job assignment ── -->
        <xsl:text>  "toOrganizationStructure" : [&#10;    {&#10;</xsl:text>
        <xsl:text>      "PersonnelNumber"   : "</xsl:text>
        <xsl:value-of select="$perner"/>
        <xsl:text>",&#10;</xsl:text>
        <xsl:text>      "EndDate"           : "9999-12-31T00:00:00",&#10;</xsl:text>
        <xsl:text>      "StartDate"         : "</xsl:text>
        <xsl:value-of select="$startDT"/>
        <xsl:text>",&#10;</xsl:text>
        <xsl:text>      "CompanyCode"       : "</xsl:text>
        <xsl:call-template name="val">
            <xsl:with-param name="node" select="CompanyCode"/>
            <xsl:with-param name="default" select="'0000'"/>
        </xsl:call-template>
        <xsl:text>",&#10;</xsl:text>
        <xsl:text>      "PersonnelArea"     : "</xsl:text>
        <xsl:call-template name="val">
            <xsl:with-param name="node" select="PersonnelArea"/>
            <xsl:with-param name="default" select="'0000'"/>
        </xsl:call-template>
        <xsl:text>",&#10;</xsl:text>
        <xsl:text>      "EmployeeGroup"     : "</xsl:text>
        <xsl:call-template name="val">
            <xsl:with-param name="node" select="EmployeeGroup"/>
            <xsl:with-param name="default" select="'1'"/>
        </xsl:call-template>
        <xsl:text>",&#10;</xsl:text>
        <xsl:text>      "EmployeeSubGroup"  : "</xsl:text>
        <xsl:call-template name="val">
            <xsl:with-param name="node" select="EmployeeSubGroup"/>
            <xsl:with-param name="default" select="'1S'"/>
        </xsl:call-template>
        <xsl:text>",&#10;</xsl:text>
        <xsl:text>      "PersonnelSubArea"  : "</xsl:text>
        <xsl:call-template name="val">
            <xsl:with-param name="node" select="PersonnelSubArea"/>
            <xsl:with-param name="default" select="'0001'"/>
        </xsl:call-template>
        <xsl:text>",&#10;</xsl:text>
        <xsl:text>      "PayrollArea"       : "US",&#10;</xsl:text>
        <xsl:text>      "OrganizationalUnit": "</xsl:text>
        <xsl:call-template name="val">
            <xsl:with-param name="node" select="OrganizationalUnit"/>
            <xsl:with-param name="default" select="'00000000'"/>
        </xsl:call-template>
        <xsl:text>",&#10;</xsl:text>
        <xsl:text>      "EmployeePosition"  : "99999999",&#10;</xsl:text>
        <xsl:text>      "Job"               : "</xsl:text>
        <xsl:call-template name="val">
            <xsl:with-param name="node" select="Job"/>
            <xsl:with-param name="default" select="'00000000'"/>
        </xsl:call-template>
        <xsl:text>"&#10;</xsl:text>
        <xsl:text>    }&#10;  ],&#10;</xsl:text>


        <!-- ── toPersonalData: captures name, gender, date of birth, nationality,
             communication language, and personal ID (SSN) ── -->
        <xsl:text>  "toPersonalData" : [&#10;    {&#10;</xsl:text>
        <xsl:text>      "PersonnelNumber"        : "</xsl:text>
        <xsl:value-of select="$perner"/>
        <xsl:text>",&#10;</xsl:text>
        <xsl:text>      "StartDate"              : "</xsl:text>
        <xsl:value-of select="$startDT"/>
        <xsl:text>",&#10;</xsl:text>
        <xsl:text>      "EndDate"                : "9999-12-31T00:00:00",&#10;</xsl:text>
        <xsl:text>      "LastName"               : "</xsl:text>
        <xsl:call-template name="val">
            <xsl:with-param name="node" select="LastName"/>
            <xsl:with-param name="default" select="''"/>
        </xsl:call-template>
        <xsl:text>",&#10;</xsl:text>
        <xsl:text>      "FirstName"              : "</xsl:text>
        <xsl:call-template name="val">
            <xsl:with-param name="node" select="FirstName"/>
            <xsl:with-param name="default" select="''"/>
        </xsl:call-template>
        <xsl:text>",&#10;</xsl:text>
        <xsl:text>      "Gender"                 : "</xsl:text>
        <xsl:choose>
            <xsl:when test="Gender = 'Male'">1</xsl:when>
            <xsl:when test="Gender = 'Female'">2</xsl:when>
            <xsl:otherwise>
                <xsl:call-template name="val">
                    <xsl:with-param name="node" select="Gender"/>
                    <xsl:with-param name="default" select="'0'"/>
                </xsl:call-template>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:text>",&#10;</xsl:text>
        <xsl:text>      "DateOfBirth"            : "</xsl:text>
        <xsl:call-template name="dateTime">
            <xsl:with-param name="node" select="DateOfBirth"/>
        </xsl:call-template>
        <xsl:text>",&#10;</xsl:text>
        <xsl:text>      "Nationality"            : "</xsl:text>
        <xsl:call-template name="val">
            <xsl:with-param name="node" select="Nationality"/>
            <xsl:with-param name="default" select="''"/>
        </xsl:call-template>
        <xsl:text>",&#10;</xsl:text>
        <xsl:text>      "CommunicationLanguage"  : "</xsl:text>
        <xsl:call-template name="val">
            <xsl:with-param name="node" select="CommunicationLanguage"/>
            <xsl:with-param name="default" select="'EN'"/>
        </xsl:call-template>
        <xsl:text>",&#10;</xsl:text>
        <xsl:text>      "PersonalIDNumber"       : "</xsl:text>
        <xsl:call-template name="val">
            <xsl:with-param name="node" select="ANSSA"/>
            <xsl:with-param name="default" select="'999999999'"/>
        </xsl:call-template>
        <xsl:text>"&#10;</xsl:text>
        <xsl:text>    }&#10;  ],&#10;</xsl:text>


        <!-- ── toAddress: captures home address details (street, city, postal code,
             country/region, state); fields may be empty for new hires ── -->
        <xsl:text>  "toAddress" : [&#10;    {&#10;</xsl:text>
        <xsl:text>      "PersonnelNumber"   : "</xsl:text>
        <xsl:value-of select="$perner"/>
        <xsl:text>",&#10;</xsl:text>
        <xsl:text>      "EndDate"           : "9999-12-31T00:00:00",&#10;</xsl:text>
        <xsl:text>      "StartDate"         : "</xsl:text>
        <xsl:value-of select="$startDT"/>
        <xsl:text>",&#10;</xsl:text>
        <xsl:text>      "SubType"           : "1",&#10;</xsl:text>
        <xsl:text>      "AddressRecordType" : "1",&#10;</xsl:text>
        <xsl:text>      "StreetHouseNumber" : "</xsl:text>
        <xsl:call-template name="val">
            <xsl:with-param name="node" select="StreetHouseNumber"/>
            <xsl:with-param name="default" select="''"/>
        </xsl:call-template>
        <xsl:text>",&#10;</xsl:text>
        <xsl:text>      "City"              : "</xsl:text>
        <xsl:call-template name="val">
            <xsl:with-param name="node" select="City"/>
            <xsl:with-param name="default" select="''"/>
        </xsl:call-template>
        <xsl:text>",&#10;</xsl:text>
        <xsl:text>      "PostalCode"        : "</xsl:text>
        <xsl:call-template name="val">
            <xsl:with-param name="node" select="PostalCode"/>
            <xsl:with-param name="default" select="''"/>
        </xsl:call-template>
        <xsl:text>",&#10;</xsl:text>
        <xsl:text>      "CountryRegion"     : "</xsl:text>
        <xsl:call-template name="val">
            <xsl:with-param name="node" select="CountryRegion"/>
            <xsl:with-param name="default" select="''"/>
        </xsl:call-template>
        <xsl:text>",&#10;</xsl:text>
        <xsl:text>      "State"             : "</xsl:text>
        <xsl:call-template name="val">
            <xsl:with-param name="node" select="State"/>
            <xsl:with-param name="default" select="''"/>
        </xsl:call-template>
        <xsl:text>"&#10;</xsl:text>
        <xsl:text>    }&#10;  ],&#10;</xsl:text>


        <!-- ── toCommunication: two entries — SubType 0001 carries the SAP UserID (= PersonnelNumber),
             SubType 0010 carries the work EmailID ── -->
        <xsl:text>  "toCommunication" : [&#10;</xsl:text>
        <!-- Entry 1 (SubType 0001): UserID — set to PersonnelNumber per spec -->
        <xsl:text>    {&#10;</xsl:text>
        <xsl:text>      "PersonnelNumber"   : "</xsl:text>
        <xsl:value-of select="$perner"/>
        <xsl:text>",&#10;</xsl:text>
        <xsl:text>      "SubType"           : "0001",&#10;</xsl:text>
        <xsl:text>      "StartDate"         : "</xsl:text>
        <xsl:value-of select="$startDT"/>
        <xsl:text>",&#10;</xsl:text>
        <xsl:text>      "EndDate"           : "9999-12-31T00:00:00",&#10;</xsl:text>
        <xsl:text>      "CommunicationType" : "0001",&#10;</xsl:text>
        <xsl:text>      "UserID"            : "</xsl:text>
        <!-- UserID = PERNER as per spec -->
        <xsl:value-of select="$perner"/>
        <xsl:text>",&#10;</xsl:text>
        <xsl:text>      "EmailID"           : ""&#10;</xsl:text>
        <xsl:text>    },&#10;</xsl:text>
        <!-- Entry 2 (SubType 0010): EmailID — sourced from the EmailID field -->
        <xsl:text>    {&#10;</xsl:text>
        <xsl:text>      "PersonnelNumber"   : "</xsl:text>
        <xsl:value-of select="$perner"/>
        <xsl:text>",&#10;</xsl:text>
        <xsl:text>      "SubType"           : "0010",&#10;</xsl:text>
        <xsl:text>      "StartDate"         : "</xsl:text>
        <xsl:value-of select="$startDT"/>
        <xsl:text>",&#10;</xsl:text>
        <xsl:text>      "EndDate"           : "9999-12-31T00:00:00",&#10;</xsl:text>
        <xsl:text>      "CommunicationType" : "0010",&#10;</xsl:text>
        <xsl:text>      "UserID"            : "",&#10;</xsl:text>
        <xsl:text>      "EmailID"           : "</xsl:text>
        <xsl:call-template name="val">
            <xsl:with-param name="node" select="EmailID"/>
            <xsl:with-param name="default" select="''"/>
        </xsl:call-template>
        <xsl:text>"&#10;</xsl:text>
        <xsl:text>    }&#10;</xsl:text>
        <xsl:text>  ]&#10;</xsl:text>
        <xsl:text>}</xsl:text>
    </xsl:template>
</xsl:stylesheet>