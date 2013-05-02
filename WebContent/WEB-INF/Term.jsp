<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test="%{action=='send'}">
<s:if test="%{provisionalTerm.localId!=''}">
<s:form action="provisionalTermSubmission" method="post" validate="true">
		<s:hidden name="provisionalTerm.localId" />
		<s:hidden name="provisionalTerm.category" />
		<s:hidden name="provisionalTerm.temporaryid" />
		<s:hidden name="provisionalTerm.permanentid" />
		<s:textfield name="provisionalTerm.term" label="term"/>
        <s:textfield name="provisionalTerm.definition" label="definition"/>    
        <s:textfield name="provisionalTerm.source" label="source"/>
        <s:textfield name="provisionalTerm.superclass" label="superclass"/>    
        <s:textfield name="provisionalTerm.ontologyIds" label="ontologyIds"/>
        <s:textfield name="provisionalTerm.synonyms" label="synonyms"/>    
	<s:submit method="submitprovisionalTerm" key="label.send.term" align="center" />
</s:form>
</s:if>
</s:if>

<s:if test="%{action=='update'}">
<s:if test="%{provisionalTerm.temporaryid!=''}">
<s:form action="provisionalTermUpdate" method="post" validate="true">
		<s:hidden name="provisionalTerm.localId" />
		<s:hidden name="provisionalTerm.category" />
		<s:hidden name="provisionalTerm.temporaryid" />
		<s:hidden name="provisionalTerm.permanentid" />
		<s:textfield name="provisionalTerm.term" label="term"/>
        <s:textfield name="provisionalTerm.definition" label="definition"/>    
        <s:textfield name="provisionalTerm.source" label="source"/>
        <s:textfield name="provisionalTerm.superclass" label="superclass"/>    
        <s:textfield name="provisionalTerm.ontologyIds" label="ontologyIds"/>
        <s:textfield name="provisionalTerm.synonyms" label="synonyms"/>     
	<s:submit method="updateprovisionalTerm" key="label.update.term" align="center" />
</s:form>
<s:form action="provisionalTermDelete" method="post" validate="true">
	<s:hidden name="provisionalTerm.temporaryid" />
	<s:submit method="deleteprovisionalTerm" key="label.delete.term" align="center" />
</s:form>
</s:if>
</s:if>