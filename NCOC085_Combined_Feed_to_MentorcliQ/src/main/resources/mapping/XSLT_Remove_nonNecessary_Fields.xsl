<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <!-- Copy all nodes and attributes to the output -->
  <xsl:template match="@*|node()">
    <xsl:copy>
      <xsl:apply-templates select="@*|node()"/>
    </xsl:copy>
  </xsl:template>

  <!-- Remove non necessary elements -->
  <xsl:template match="job_startDate"/>
  <xsl:template match="job_seqNumber"/>
  <xsl:template match="emplStatus"/>
  

</xsl:stylesheet>