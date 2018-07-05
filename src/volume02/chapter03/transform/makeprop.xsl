<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	
	<xsl:output method="text" omit-xml-declaration="yes" />
	
	<xsl:template match="/staff/employee">
		employee.<xsl:value-of select="position()" />
		name=<xsl:value-of select="name/text()" />
		employee.<xsl:value-of select="position()" />
		salary=<xsl:value-of select="salary/text()" />
		employee.<xsl:value-of select="position()" />
		hiredate=<xsl:value-of select="hiredate/@year"/>-<xsl:value-of select="hiredate/@month"/>-<xsl:value-of select="hiredate/day"/>
	</xsl:template>
	
</xsl:stylesheet>