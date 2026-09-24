<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="3.0">
  <xsl:output method="xml" indent="yes"/>

  <!-- Root template: match root EmpEmployment and filter on non-empty personRerlationshipNav -->
  <xsl:template match="/EmpEmployment">
    <EmpEmployment>
      <xsl:apply-templates select="EmpEmployment[personNav/PerPerson/personRerlationshipNav/*]"/>
    </EmpEmployment>
  </xsl:template>

  <!-- Copy EmpEmployment if it has non-empty personRerlationshipNav -->
  <xsl:template match="EmpEmployment[personNav/PerPerson/personRerlationshipNav/*]">
    <xsl:copy>
      <xsl:copy-of select="@* | node()"/>
    </xsl:copy>
  </xsl:template>
</xsl:stylesheet>