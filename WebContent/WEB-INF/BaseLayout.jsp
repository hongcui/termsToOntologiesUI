<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body>
<table border="1" cellpadding="2" cellspacing="2" align="center">
	<tr>
		<td height="30" colspan="2"><tiles:insertAttribute name="header" />
		</td>
	</tr>
	<tr>
		<td height="1000"><tiles:insertAttribute name="menu" /></td>
		<td width="1400" valign="top">
			<table border="1" cellpadding="2" cellspacing="2" align="center">
				<tr>
					<td height="200" width="1400">
						<tiles:insertAttribute name="body" />
					</td>
				</tr>
				<tr>
					<td height="800" width="1400">
						<iframe height="800" width="1400" src="http://www.ebi.ac.uk/ontology-lookup/"></iframe> 
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td height="30" colspan="2"><tiles:insertAttribute name="footer" />
		</td>
	</tr>
</table>
</body>
</html>