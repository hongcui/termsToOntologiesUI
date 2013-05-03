<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript" src="<s:url value="js.js"/>"></script>  
<s:if test="%{action=='send'}">
<s:if test="%{provisionalTerm.localId!=''}">
<s:form action="provisionalTermSubmission" method="post" validate="true">
		<s:hidden name="provisionalTerm.localId" />
		<s:hidden name="provisionalTerm.category" />
		<s:hidden name="provisionalTerm.temporaryid" />
		<s:textfield name="provisionalTerm.term" key="term"/>
        <s:textfield name="provisionalTerm.definition" key="definition"/>    
         <s:textfield name="provisionalTerm.synonyms" key="synonyms"/>
         <s:select key="ontology" headerKey="0" headerValue="" list="ontologies" name="provisionalTerm.ontology"
        	onChange="redirectIFrame(this);" />    
        <s:textfield id="superclass" name="provisionalTerm.superclass" key="superclass"/>
        <s:label name="provisionalTerm.source" key="source"/>
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
		<s:textfield name="provisionalTerm.term" key="term"/>
        <s:textfield name="provisionalTerm.definition" key="definition"/>    
         <s:textfield name="provisionalTerm.synonyms" key="synonyms"/>
         <s:select key="ontology" headerKey="0" headerValue="" list="ontologies" name="provisionalTerm.ontology"
        	onChange="redirectIFrame(this);" />    
        <s:textfield id="superclass" name="provisionalTerm.superclass" key="superclass"/>
        <s:label name="provisionalTerm.source" key="source"/>
	<s:submit method="updateprovisionalTerm" key="label.update.term" align="center" />
</s:form>
<s:form action="provisionalTermDelete" method="post" validate="true">
	<s:hidden name="provisionalTerm.temporaryid" />
	<s:submit method="deleteprovisionalTerm" key="label.delete.term" align="center" />
</s:form>
</s:if>
</s:if>