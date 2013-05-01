<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ page import="java.util.List, ui.db.*, bioportal.beans.*" %>

<h2>Structure</h2>
<%
	List<ProvisionalTerm> structureProvisionalTerms = UnadoptedTermDAO.getInstance().getUnadoptedStructureTerms();
	for(ProvisionalTerm structureProvisionalTerm : structureProvisionalTerms) {
		%>
		<s:url action="provisionalTerm" var="termURL" >
		    <s:param name="localId"><%= structureProvisionalTerm.getLocalId() %></s:param><br/>
		</s:url>
		<s:a href="%{termURL}"><%= structureProvisionalTerm.getTerm() %></s:a>

		<%
	}
%>

<h2>Character</h2>
<%
	List<ProvisionalTerm> characterProvisionalTerms = UnadoptedTermDAO.getInstance().getUnadoptedCharacterTerms();
	for(ProvisionalTerm characterProvisionalTerm : characterProvisionalTerms) {
		%>
		<s:url action="provisionalTerm" var="termURL" >
		    <s:param name="localId"><%= characterProvisionalTerm.getLocalId() %></s:param><br/>
		</s:url>
		<s:a href="%{termURL}"><%= characterProvisionalTerm.getTerm() %></s:a>

		<%
	}
%>