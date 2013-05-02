<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<h2>Terms To Ontologies</h2>
<table>
<tr>
<td>
<s:url action="menuAction" var="menuURL" >
<s:param name="action">send</s:param><br/>
</s:url>
<s:a href="%{menuURL}">Send Terms</s:a>
</td>
<td>
<s:url action="menuAction" var="menuURL" >
<s:param name="action">update</s:param><br/>
</s:url>
<s:a href="%{menuURL}">View/Update/Delete Terms under Review</s:a>
</td>
<td>
<s:url action="menuAction" var="menuURL" >
<s:param name="action">adopted</s:param><br/>
</s:url>
<s:a href="%{menuURL}">View Adopted Terms</s:a>
</td>
<td>
<s:url action="menuAction" var="menuURL" >
<s:param name="action">check</s:param><br/>
</s:url>
<s:a href="%{menuURL}">Check Adopted Terms</s:a>
</td>
</tr>
</table>