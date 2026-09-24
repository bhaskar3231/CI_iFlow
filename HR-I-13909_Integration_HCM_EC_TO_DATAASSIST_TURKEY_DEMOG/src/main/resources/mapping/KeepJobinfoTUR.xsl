<xsl:stylesheet version="3.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output method="xml" indent="yes"/>

  <xsl:mode on-no-match="shallow-copy"/>

  <xsl:template match="/EmpEmployment">
    <EmpEmployment>
      <xsl:apply-templates select="EmpEmployment[jobInfoNav/EmpJob/countryOfCompany = 'TUR']"/>
    </EmpEmployment>
  </xsl:template>
  
</xsl:stylesheet>