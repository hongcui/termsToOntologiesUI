<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="termDescriptionSubmission" method="post" validate="true">
		<s:textfield name="termDescription.term" label="term"/>
        <s:textfield name="termDescription.definition" label="definition"/>    
        <s:textfield name="termDescription.source" label="source"/>
        <s:textfield name="termDescription.superclass" label="superclass"/>    
        <s:textfield name="termDescription.ontologyIds" label="ontologyIds"/>
        <s:textfield name="termDescription.synonyms" label="synonyms"/>    
	<s:submit method="submitTermDescription" key="label.send.term" align="center" />
</s:form>