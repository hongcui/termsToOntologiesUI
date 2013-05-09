<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.List, ui.db.*, bioportal.beans.*, bioportal.db.*, javax.servlet.http.HttpSession, ui.action.*" %>

<s:if test="%{action=='send'}">
	<h2>New Terms</h2>
	<h3>Structure</h3>
	<s:iterator var="i" step="1" value="structureProvisionalTerms">
		<s:url action="provisionalTerm" var="termURL">
			<s:param name="action">%{action}</s:param>
			<s:param name="localId">
				<s:property value="localId"></s:property>
			</s:param>
		</s:url>
		<s:a href="%{termURL}">
			<s:property value="term"></s:property>
		</s:a>
	</s:iterator>
	<h3>Character</h3>
	<s:iterator var="i" step="1" value="characterProvisionalTerms">
		<s:url action="provisionalTerm" var="termURL">
			<s:param name="action">%{action}</s:param>
			<s:param name="localId">
				<s:property value="localId"></s:property>
			</s:param>
		</s:url>
		<s:a href="%{termURL}">
			<s:property value="term"></s:property>
		</s:a>
	</s:iterator>
</s:if>


<s:if test="%{action=='update'}">
<h2>Pending Terms</h2>
* marks your submissions

	<h3>Structure</h3>
	<s:iterator var="i" step="1" value="structureAwaitingAdoptionProvisionalTerms">
		<s:url action="provisionalTerm" var="termURL">
			<s:param name="action">%{action}</s:param>
			<s:param name="localId">
				<s:property value="localId"></s:property>
			</s:param>
		</s:url>
		<s:a href="%{termURL}">
			<s:property value="term"></s:property>
		</s:a>
	</s:iterator>
	<h3>Character</h3>
	<s:iterator var="i" step="1" value="characterAwaitingAdoptionProvisionalTerms">
		<s:url action="provisionalTerm" var="termURL">
			<s:param name="action">%{action}</s:param>
			<s:param name="localId">
				<s:property value="localId"></s:property>
			</s:param>
		</s:url>
		<s:a href="%{termURL}">
			<s:property value="term"></s:property>
		</s:a>
	</s:iterator>
</s:if>

<s:if test="%{action=='adopted'}">
<h2>Adopted Terms</h2>
* marks your submissions

	<h3>Structure</h3>
		<s:iterator var="i" step="1" value="structureAwaitingAdoptionProvisionalTerms">
		<s:url action="provisionalTerm" var="termURL">
			<s:param name="action">%{action}</s:param>
			<s:param name="localId">
				<s:property value="localId"></s:property>
			</s:param>
		</s:url>
		<s:a href="%{termURL}">
			<s:property value="term"></s:property>
		</s:a>
	</s:iterator>
	<h3>Character</h3>
	<s:iterator var="i" step="1" value="characterAwaitingAdoptionProvisionalTerms">
		<s:url action="provisionalTerm" var="termURL">
			<s:param name="action">%{action}</s:param>
			<s:param name="localId">
				<s:property value="localId"></s:property>
			</s:param>
		</s:url>
		<s:a href="%{termURL}">
			<s:property value="term"></s:property>
		</s:a>
	</s:iterator>
</s:if>