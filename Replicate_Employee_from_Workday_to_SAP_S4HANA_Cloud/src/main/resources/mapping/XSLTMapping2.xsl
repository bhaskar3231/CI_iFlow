<xsl:stylesheet version="2.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:wd="urn:com.workday/bsvc"
    exclude-result-prefixes="wd">

  <xsl:output method="xml" indent="yes" omit-xml-declaration="yes"/>
  <xsl:strip-space elements="*"/>

  <xsl:template match="/">

    <!-- ── Shorthand variables ──────────────────────────────────────────── -->
    <xsl:variable name="worker"   select="/Response_Data/wd:Worker/wd:Worker_Data"/>
    <xsl:variable name="personal" select="$worker/wd:Personal_Data"/>
    <xsl:variable name="contact"  select="$personal/wd:Contact_Data"/>
    <xsl:variable name="status"   select="$worker/wd:Employment_Data/wd:Worker_Status_Data"/>

    <!-- ── StartDate: derived from Hire_Date; append T00:00:00 ─────────── -->
    <xsl:variable name="startDate">
      <xsl:choose>
        <xsl:when test="$status/wd:Hire_Date">
          <xsl:value-of select="concat($status/wd:Hire_Date, 'T00:00:00')"/>
        </xsl:when>
        <xsl:otherwise>2026-01-01T00:00:00</xsl:otherwise>
      </xsl:choose>
    </xsl:variable>

    <!-- ── Primary home address (primary flag=1, HOME type) ────────────── -->
    <xsl:variable name="homeAddr"
      select="$contact/wd:Address_Data[
                wd:Usage_Data/wd:Type_Data[@wd:Primary='1']/wd:Type_Reference/wd:ID[
                  @wd:type='Communication_Usage_Type_ID' and text()='HOME'
                ]
              ][1]"/>

    <!-- ── Work email address ───────────────────────────────────────────── -->
    <xsl:variable name="workEmail"
      select="$contact/wd:Email_Address_Data[
                wd:Usage_Data/wd:Type_Data/wd:Type_Reference/wd:ID[
                  @wd:type='Communication_Usage_Type_ID' and text()='WORK'
                ]
              ][1]/wd:Email_Address"/>

    <!-- ── Gender: translate Workday label to SAP code (M=1, F=2) ───────── -->
    <xsl:variable name="genderCode">
      <xsl:choose>
        <xsl:when test="$personal/wd:Gender_Reference/wd:ID[@wd:type='Gender_Code'] = 'Male'">1</xsl:when>
        <xsl:when test="$personal/wd:Gender_Reference/wd:ID[@wd:type='Gender_Code'] = 'Female'">2</xsl:when>
        <xsl:otherwise>
          <xsl:value-of select="$personal/wd:Gender_Reference/wd:ID[@wd:type='Gender_Code']"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>

     <HRWorkForcePerson>
      <HRWorkForcePersonType>
        <PersonnelNumber>
          <xsl:value-of select="$worker/wd:Worker_ID"/>
        </PersonnelNumber>
        <ActionType>01</ActionType>

        <!-- ── toAction ──────────────────────────────────────────────────── -->
        <toAction>
          <PersonnelNumber>
            <xsl:value-of select="$worker/wd:Worker_ID"/>
          </PersonnelNumber>
          <SubType/>
          <EndDate>9999-12-31T00:00:00</EndDate>
          <StartDate>
            <xsl:value-of select="$startDate"/>
          </StartDate>
          <ActionType>01</ActionType>
          <ReasonForAction>01</ReasonForAction>
          <EmployementStatus>3</EmployementStatus>
        </toAction>

        <!-- ── toOrganizationStructure ───────────────────────────────────── -->
        <toOrganizationStructure>
          <PersonnelNumber>
            <xsl:value-of select="$worker/wd:Worker_ID"/>
          </PersonnelNumber>
          <EndDate>9999-12-31T00:00:00</EndDate>
          <StartDate>
            <xsl:value-of select="$startDate"/>
          </StartDate>
          <CompanyCode>US01</CompanyCode>
          <PersonnelArea>US01</PersonnelArea>
          <EmployeeGroup>1</EmployeeGroup>
          <EmployeeSubGroup>1S</EmployeeSubGroup>
          <PersonnelSubArea>0001</PersonnelSubArea>
          <PayrollArea>US</PayrollArea>
          <OrganizationalUnit>00000000</OrganizationalUnit>
          <EmployeePosition>99999999</EmployeePosition>
          <Job>00000000</Job>
        </toOrganizationStructure>

        <!-- ── toPersonalData ────────────────────────────────────────────── -->
        <toPersonalData>
          <PersonnelNumber>
            <xsl:value-of select="$worker/wd:Worker_ID"/>
          </PersonnelNumber>
          <StartDate>
            <xsl:value-of select="$startDate"/>
          </StartDate>
          <EndDate>9999-12-31T00:00:00</EndDate>
          <LastName>
            <xsl:value-of select="$personal/wd:Name_Data/wd:Legal_Name_Data/wd:Name_Detail_Data/wd:Last_Name"/>
          </LastName>
          <FirstName>
            <xsl:value-of select="$personal/wd:Name_Data/wd:Legal_Name_Data/wd:Name_Detail_Data/wd:First_Name"/>
          </FirstName>
          <Gender>
            <xsl:value-of select="$genderCode"/>
          </Gender>
          <DateOfBirth>
            <xsl:if test="$personal/wd:Birth_Date">
              <xsl:value-of select="concat($personal/wd:Birth_Date, 'T00:00:00')"/>
            </xsl:if>
          </DateOfBirth>
          <Nationality>
            <xsl:value-of select="$personal/wd:Primary_Nationality_Reference/wd:ID[@wd:type='ISO_3166-1_Alpha-2_Code']"/>
          </Nationality>
          <CommunicationLanguage>EN</CommunicationLanguage>
          <PersonalIDNumber>999999999</PersonalIDNumber>
        </toPersonalData>

        <!-- ── toAddress ─────────────────────────────────────────────────── -->
        <toAddress>
          <PersonnelNumber>
            <xsl:value-of select="$worker/wd:Worker_ID"/>
          </PersonnelNumber>
          <EndDate>9999-12-31T00:00:00</EndDate>
          <StartDate>
            <xsl:value-of select="$startDate"/>
          </StartDate>
          <SubType>1</SubType>
          <AddressRecordType>1</AddressRecordType>
          <StreetHouseNumber>
            <xsl:value-of select="$homeAddr/wd:Address_Line_Data[@wd:Type='ADDRESS_LINE_1']"/>
          </StreetHouseNumber>
          <City>
            <xsl:value-of select="$homeAddr/wd:Municipality"/>
          </City>
          <PostalCode>
            <xsl:value-of select="$homeAddr/wd:Postal_Code"/>
          </PostalCode>
          <CountryRegion>
            <xsl:value-of select="$homeAddr/wd:Country_Reference/wd:ID[@wd:type='ISO_3166-1_Alpha-2_Code']"/>
          </CountryRegion>
          <State>
            <xsl:value-of select="$homeAddr/wd:Country_Region_Reference/wd:ID[@wd:type='ISO_3166-2_Code']"/>
          </State>
        </toAddress>

        <!-- ── toCommunication – system user ID (0001) ───────────────────── -->
        <toCommunication>
          <PersonnelNumber>
            <xsl:value-of select="$worker/wd:Worker_ID"/>
          </PersonnelNumber>
          <SubType>0001</SubType>
          <StartDate>
            <xsl:value-of select="$startDate"/>
          </StartDate>
          <EndDate>9999-12-31T00:00:00</EndDate>
          <CommunicationType>0001</CommunicationType>
          <UserID>
            <xsl:value-of select="$worker/wd:User_ID"/>
          </UserID>
          <EmailID/>
        </toCommunication>

        <!-- ── toCommunication – work email (0010) ───────────────────────── -->
        <toCommunication>
          <PersonnelNumber>
            <xsl:value-of select="$worker/wd:Worker_ID"/>
          </PersonnelNumber>
          <SubType>0010</SubType>
          <StartDate>
            <xsl:value-of select="$startDate"/>
          </StartDate>
          <EndDate>9999-12-31T00:00:00</EndDate>
          <CommunicationType>0010</CommunicationType>
          <UserID/>
          <EmailID>
            <xsl:value-of select="$workEmail"/>
          </EmailID>
        </toCommunication>

      </HRWorkForcePersonType>
    </HRWorkForcePerson>

  </xsl:template>
</xsl:stylesheet>