<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
    <xsl:output method="xml" omit-xml-declaration="yes" encoding="UTF-8" indent="yes" />
    <xsl:template match="/">
        <FOCostCenter>
             <xsl:for-each-group select="/FOCostCenter/FOCostCenter" group-by="externalCode">
               <Row>
                 <xsl:copy-of select="current-group()"/>
                </Row>
        	 </xsl:for-each-group>
		</FOCostCenter>
    </xsl:template>
</xsl:stylesheet>