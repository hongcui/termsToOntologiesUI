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
		<s:hidden name="provisionalTerm.submittedby" />
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
<s:label>* marks required fields</s:label>
</s:if>
</s:if>


<s:if test="%{action=='update'}">
<s:if test="%{provisionalTerm.temporaryid!=''}">

<s:if test="%{provisionalTerm.submittedby==#session.BIOPORTAL_USER_ID}">
	<s:form action="provisionalTermUpdate" method="post" validate="true">
		<s:hidden name="provisionalTerm.localId" />
		<s:hidden name="provisionalTerm.termType" />
		<s:hidden name="provisionalTerm.temporaryid" />
		<s:hidden name="provisionalTerm.submittedby" />
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
	<s:label>* marks required fields</s:label>
</s:if>
<s:else>
	<s:form action="provisionalTermUpdate" method="post" validate="true">
		<s:textfield readonly="true"  name="provisionalTerm.term" key="term"/>
        <s:textfield  readonly="true" name="provisionalTerm.definition" key="definition"/>    
        <s:textfield  readonly="true" name="provisionalTerm.synonyms" key="synonyms"/>
        <s:select  readonly="true" key="ontology" headerKey="0" headerValue="" list="ontologies" name="provisionalTerm.ontology"
        	 />    
        	 <!-- onChange="redirectIFrame(this);" -->
        <s:textfield  readonly="true" id="superclass" name="provisionalTerm.superclass" key="superclass"/>
        <s:textfield name="provisionalTerm.source" readonly="true" key="source"/>
       	<s:textfield name="provisionalTerm.termCategory" readonly="true" key="category"/>
	</s:form>
</s:else>


</s:if>
</s:if>



<s:if test="%{action=='adopted'}">
<s:if test="%{provisionalTerm.localId!=''}">
		<s:textfield name="provisionalTerm.term" readonly="true" key="term"/>
        <s:textfield name="provisionalTerm.definition" readonly="true" key="definition"/>    
        <s:textfield name="provisionalTerm.synonyms" readonly="true" key="synonyms"/>
        <s:select key="ontology" readonly="true" headerKey="0" headerValue="" list="ontologies" name="provisionalTerm.ontology"
         	/>
        	<!--  onChange="redirectIFrame(this);"  -->
        <s:textfield id="superclass" readonly="true" name="provisionalTerm.superclass" key="superclass"/>
        <s:textfield name="provisionalTerm.source" readonly="true" key="source"/>
       	<s:textfield name="provisionalTerm.termCategory" readonly="true" key="category"/>
</s:if>
</s:if>


</s:if>



