<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output method="xml" indent="yes" encoding="UTF-8"/>

  <!-- Identity template -->
  <xsl:template match="@*|node()">
    <xsl:copy>
      <xsl:apply-templates select="@*|node()"/>
    </xsl:copy>
  </xsl:template>

  <!-- GUARD: DELETE action is NEVER modified -->
  <xsl:template match="national_id:value-of select="."/>
    </xsl:copy>
  </xsl:template>

  <!-- INSERT → CHANGE when paired DELETE has different national_id -->
  <xsl:template match="national_id_card[action='INSERT']/action"l:variable name="cardType" select="$card/card_type"/>
    <xsl:variable name="natId"    select="$card/national_id"/>

    <xsl:variable name="deletePair"
      select="$card/../national_id_card[action='DELETE'
                                        and card_type = $cardType]"/
        </xsl:when>
        <xsl:otherwise>
          <xsl:value-of select="."/>
        </xsl:otherwise>
      </xsl:choose>
    </action>
  </xsl:template>

</xsl:stylesheet>