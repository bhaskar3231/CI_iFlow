<!--XSLT to remove the mutiple records on an employee and send the latest Active info-->
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema">
<xsl:output method="xml" omit-xml-declaration="yes" encoding="UTF-8" indent="yes" />
<xsl:param name="current_date"/>


<xsl:template match="/">
<EmpEmployment>


<xsl:for-each-group select="/EmpEmployment/EmpEmployment" group-by="personIdExternal">


            <xsl:for-each select="current-group()">

              <!--Sequencing the record based on JOb Info startDate-->
              <xsl:sort select="job_startDate" order="descending"/>
              <!--Sequencing the record based on sequence Number-->
              <xsl:sort select="job_seqNumber" order="descending"/>
              <!--Sequencing the record based on emplStatus-->
              <xsl:sort select="emplStatus" order="ascending"/>
              
              <!-- Send top most record-->
            <xsl:if test="position() = 1">
                <xsl:copy-of select="."/>
            </xsl:if>

            </xsl:for-each>


</xsl:for-each-group>
</EmpEmployment>

</xsl:template>
</xsl:stylesheet>
