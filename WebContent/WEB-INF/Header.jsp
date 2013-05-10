<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<h1>Terms To Ontologies</h1>
<table>
<tr>
<td>
<s:url action="menuAction" var="menuURL" >
<s:param name="action">send</s:param><br/>
</s:url>
<s:a href="%{menuURL}">Send</s:a>
</td>
<td>
<s:url action="menuAction" var="menuURL" >
<s:param name="action">update</s:param><br/>
</s:url>
<s:a href="%{menuURL}">Pending</s:a>
</td>
<td>
<s:url action="menuAction" var="menuURL" >
<s:param name="action">adopted</s:param><br/>
</s:url>
<s:a href="%{menuURL}">Adopted</s:a>
</td>
<td>
<s:url action="menuAction" var="menuURL" >
<s:param name="action">check</s:param><br/>
</s:url>
<s:a href="%{menuURL}">Check Adopted</s:a>
</td>
</tr>
</table>