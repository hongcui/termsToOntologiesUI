<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ page import="java.util.List, db.*, beans.*" %>

<h2>Structure</h2>
<%
	List<TermDescription> structureTermDescriptions = UnadoptedTermDAO.getInstance().getUnadoptedStructureTerms();
	for(TermDescription structureTermDescription : structureTermDescriptions) {
		%>
		<s:url action="termDescription" var="termURL" >
		    <s:param name="term"><%= structureTermDescription.getTerm() %></s:param><br/>
		</s:url>
		<s:a href="%{termURL}"><%= structureTermDescription.getTerm() %></s:a>

		<%
	}
%>

<h2>Character</h2>
<%
	List<TermDescription> characterTermDescriptions = UnadoptedTermDAO.getInstance().getUnadoptedCharacterTerms();
	for(TermDescription characterTermDescription : characterTermDescriptions) {
		%>
		<s:url action="termDescription" var="termURL" >
		    <s:param name="term"><%= characterTermDescription.getTerm() %></s:param><br/>
		</s:url>
		<s:a href="%{termURL}"><%= characterTermDescription.getTerm() %></s:a>

		<%
	}
%>