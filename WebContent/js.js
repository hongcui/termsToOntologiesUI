function redirectIFrame(dropdown) {
	switch(dropdown.value) {
	case 'PATO':
		document.getElementById('ontologyLookup').src = 'http://www.ebi.ac.uk/ontology-lookup/browse.do?ontName=PATO';
		break;
	case 'PO':
		document.getElementById('ontologyLookup').src = 'http://www.ebi.ac.uk/ontology-lookup/browse.do?ontName=PO';
		break;
	case 'HAO':
		document.getElementById('ontologyLookup').src = 'http://www.ebi.ac.uk/ontology-lookup/browse.do?ontName=HAO';
		break;
	}
}