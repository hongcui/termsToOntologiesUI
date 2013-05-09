<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ page import="java.util.List, ui.db.*, bioportal.beans.*, bioportal.db.*" %>


<s:if test="%{action=='send'}">
<h2>New Terms</h2>

	<h3>Structure</h3>
	<%
	List<ProvisionalTerm> structureProvisionalTerms = UnadoptedTermDAO.getInstance().getUnadoptedStructureTerms();
	for(ProvisionalTerm structureProvisionalTerm : structureProvisionalTerms) {
		%>
		<s:url action="provisionalTerm" var="termURL" >
		 	<s:param name="action">%{action}</s:param>
		    <s:param name="localId"><%= structureProvisionalTerm.getLocalId() %></s:param>
		</s:url>
		<s:a href="%{termURL}"><%= structureProvisionalTerm.getTerm() %></s:a>

		<%
	}
%>

<h3>Character</h3>
<%
	List<ProvisionalTerm> characterProvisionalTerms = UnadoptedTermDAO.getInstance().getUnadoptedCharacterTerms();
	for(ProvisionalTerm characterProvisionalTerm : characterProvisionalTerms) {
		%>
		<s:url action="provisionalTerm" var="termURL" >
			<s:param name="action">%{action}</s:param>
		    <s:param name="localId"><%= characterProvisionalTerm.getLocalId() %></s:param><br/>
		</s:url>
		<s:a href="%{termURL}"><%= characterProvisionalTerm.getTerm() %></s:a>

		<%
	}
%>
</s:if>


<s:if test="%{action=='update'}">
<h2>Pending Terms</h2>

	<h3>Structure</h3>
	<%
	List<ProvisionalTerm> structureProvisionalTerms = ProvisionalTermDAO.getInstance().getAllStructureAwaitingAdoption();
	for(ProvisionalTerm structureProvisionalTerm : structureProvisionalTerms) {
		%>
		<s:url action="provisionalTerm" var="termURL" >
		 	<s:param name="action">%{action}</s:param>
		    <s:param name="localId"><%= structureProvisionalTerm.getLocalId() %></s:param>
		</s:url>
		<s:a href="%{termURL}"><%= structureProvisionalTerm.getTerm() %></s:a>
		<%
	}
%>

<h3>Character</h3>
<%
List<ProvisionalTerm> characterProvisionalTerms = ProvisionalTermDAO.getInstance().getAllCharacterAwaitingAdoption();
	for(ProvisionalTerm characterProvisionalTerm : characterProvisionalTerms) {
		%>
		<s:url action="provisionalTerm" var="termURL" >
			<s:param name="action">%{action}</s:param>
		    <s:param name="localId"><%= characterProvisionalTerm.getLocalId() %></s:param><br/>
		</s:url>
		<s:a href="%{termURL}"><%= characterProvisionalTerm.getTerm() %></s:a>

		<%
	}
%>
</s:if>




<s:if test="%{action=='adopted'}">
<h2>Adopted Terms</h2>

		<h3>Structure</h3>
	<%
	List<ProvisionalTerm> structureProvisionalTerms = ProvisionalTermDAO.getInstance().getAdoptedStructureTerms();
	for(ProvisionalTerm structureProvisionalTerm : structureProvisionalTerms) {
		%>
		<s:url action="provisionalTerm" var="termURL" >
		 	<s:param name="action">%{action}</s:param>
		    <s:param name="localId"><%= structureProvisionalTerm.getLocalId() %></s:param>
		</s:url>
		<s:a href="%{termURL}"><%= structureProvisionalTerm.getTerm() %></s:a>

		<%
	}
%>

<h3>Character</h3>
<%
	List<ProvisionalTerm> characterProvisionalTerms = ProvisionalTermDAO.getInstance().getAdoptedCharacterTerms();
	for(ProvisionalTerm characterProvisionalTerm : characterProvisionalTerms) {
		%>
		<s:url action="provisionalTerm" var="termURL" >
			<s:param name="action">%{action}</s:param>
		    <s:param name="localId"><%= characterProvisionalTerm.getLocalId() %></s:param><br/>
		</s:url>
		<s:a href="%{termURL}"><%= characterProvisionalTerm.getTerm() %></s:a>

		<%
	}
%>
</s:if>