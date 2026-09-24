<xsl:stylesheet version="3.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" exclude-result-prefixes="xs">
    <!-- Identity template: copies all nodes by default -->
    <xsl:mode on-no-match="shallow-copy"/>
    <!-- Suppress <national_id_card> blocks with <card_type> not equal to "TR" -->
    <xsl:template match="national_id_card[card_type != 'TR']"/>
    <!-- Explicitly copy other nodes (handled by on-no-match="shallow-copy") --> 
</xsl:stylesheet>