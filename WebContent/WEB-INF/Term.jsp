<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="provisionalTermSubmission" method="post" validate="true">
		<s:textfield name="provisionalTerm.term" label="term"/>
        <s:textfield name="provisionalTerm.definition" label="definition"/>    
        <s:textfield name="provisionalTerm.source" label="source"/>
        <s:textfield name="provisionalTerm.superclass" label="superclass"/>    
        <s:textfield name="provisionalTerm.ontologyIds" label="ontologyIds"/>
        <s:textfield name="provisionalTerm.synonyms" label="synonyms"/>    
	<s:submit method="submitprovisionalTerm" key="label.send.term" align="center" />
</s:form>