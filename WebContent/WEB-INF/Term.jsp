<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:actionerror />
<s:actionmessage />

<s:if test="%{provisionalTerm.term!=''}">

<script type="text/javascript" src="<s:url value="js.js"/>"></script>  
<s:if test="%{action=='send'}">
<s:if test="%{provisionalTerm.localId!=''}">
<s:form action="provisionalTermSubmission" method="post" validate="true">
		<s:hidden name="provisionalTerm.localId" />
		<s:hidden name="provisionalTerm.termType" />
		<s:hidden name="provisionalTerm.temporaryid" />
		<s:textfield name="provisionalTerm.term" key="term"/>
        <s:textfield name="provisionalTerm.definition" key="definition"/>    
        <s:textfield name="provisionalTerm.synonyms" key="synonyms"/>
        <s:select key="ontology" headerKey="0" headerValue="" list="ontologies" name="provisionalTerm.ontology"
         	/>
        	<!--  onChange="redirectIFrame(this);"  -->
        <s:textfield id="superclass" name="provisionalTerm.superclass" key="superclass"/>
        <s:textfield name="provisionalTerm.source" readonly="true" key="source"/>
       	<s:textfield name="provisionalTerm.termCategory" readonly="true" key="category"/>
	<s:submit method="submitprovisionalTerm" key="label.send.term" align="center" />
</s:form>
</s:if>
</s:if>

<s:if test="%{action=='update'}">
<s:if test="%{provisionalTerm.temporaryid!=''}">
<s:form action="provisionalTermUpdate" method="post" validate="true">
		<s:hidden name="provisionalTerm.localId" />
		<s:hidden name="provisionalTerm.termType" />
		<s:hidden name="provisionalTerm.temporaryid" />
		<s:textfield name="provisionalTerm.term" key="term"/>
        <s:textfield name="provisionalTerm.definition" key="definition"/>    
        <s:textfield name="provisionalTerm.synonyms" key="synonyms"/>
        <s:select key="ontology" headerKey="0" headerValue="" list="ontologies" name="provisionalTerm.ontology"
        	 />    
        	 <!-- onChange="redirectIFrame(this);" -->
        <s:textfield id="superclass" name="provisionalTerm.superclass" key="superclass"/>
        <s:textfield name="provisionalTerm.source" readonly="true" key="source"/>
       	<s:textfield name="provisionalTerm.termCategory" readonly="true" key="category"/>
	<s:submit method="updateprovisionalTerm" key="label.update.term" align="center" />
</s:form>
<s:form action="provisionalTermDelete" method="post" validate="true">
	<s:hidden name="provisionalTerm.temporaryid" />
	<s:hidden name="provisionalTerm.localId" />
	<s:submit method="deleteprovisionalTerm" key="label.delete.term" align="center" />
</s:form>
</s:if>
</s:if>

<s:label>* marks required fields</s:label>

</s:if>