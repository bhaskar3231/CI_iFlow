<!-- This XSLT Script generates the home event reasons based on host event reasons -->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" indent="yes" omit-xml-declaration="yes"/>
    
    <xsl:param name="HOST_EVENT_REASON"/>
	 <xsl:template match="/">
		<EmpJob>
		  
		 <!-- <xsl:variable name="HOST_EVENT_REASON" select="'ENDSHADOWHM'"/>-->
		  <xsl:variable name="HOME_EVENT_REASON" select="/EmpJob/EmpJob[1]/eventReason"/>
		  
		  <EmpJob>
		     <xsl:copy-of select="/EmpJob/EmpJob[1]/seqNumber"/>
		     <xsl:copy-of select="/EmpJob/EmpJob[1]/userId"/>
		     <xsl:copy-of select="/EmpJob/EmpJob[1]/startDate"/>
		     <xsl:choose>
		       <!-- Begin Global assignment cases --> 
		        <xsl:when test="$HOME_EVENT_REASON = 'AWAYGA' and $HOST_EVENT_REASON = 'BEGINSHADOW'">
		             <eventReason>AWAYSHADOW</eventReason>
		        </xsl:when>
		        <xsl:when test="$HOME_EVENT_REASON = 'AWAYGA' and $HOST_EVENT_REASON = 'BEGINGASPLIT'">
		             <eventReason>AWAYGASPLIT</eventReason>
		        </xsl:when>
		         <xsl:when test="$HOME_EVENT_REASON = 'AWAYGA' and $HOST_EVENT_REASON = 'BEGINSHADOWHM'">
		             <eventReason>AWAYSHADOWHM</eventReason>
		        </xsl:when>
		        <!-- End Global assignment cases --> 
		        <xsl:when test="$HOME_EVENT_REASON = 'RETURNGA' and $HOST_EVENT_REASON = 'ENDSHADOW'">
		             <eventReason>RETURNSHADOW</eventReason>
		        </xsl:when>
		         <xsl:when test="$HOME_EVENT_REASON = 'RETURNGA' and $HOST_EVENT_REASON = 'ENDGASPLIT'">
		             <eventReason>RETURNGASPLIT</eventReason>
		        </xsl:when>
		        <xsl:when test="$HOME_EVENT_REASON = 'RETURNGA' and $HOST_EVENT_REASON = 'ENDSHADOWHM'">
		             <eventReason>RETURNSHADOWHM</eventReason>
		        </xsl:when>
		        <xsl:when test="$HOME_EVENT_REASON = 'RETURNGA' and $HOST_EVENT_REASON = 'ENDLOCAL'">
		             <eventReason>RETURNLOCAL</eventReason>
		        </xsl:when>
		        <xsl:otherwise>
		            <eventReason/>
		        </xsl:otherwise>
		      </xsl:choose>
		  </EmpJob>
		 
		</EmpJob>
	</xsl:template>
</xsl:stylesheet>