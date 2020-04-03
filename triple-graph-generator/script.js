var a = 0;
var subjects = Array();
var predicates = Array();
var objects = Array();
var dbr_set = new Set(); 

function insert_triples(){
	var subject = document.getElementById("subject").value;
	var predicate = document.getElementById("predicate").value;
	var object = document.getElementById("object").value;
	
	if(subject=="" || predicate=="" || object=="") {
		document.getElementById("message_insertion").innerHTML = "";
		document.getElementById("message_display").innerHTML = "";
		alert("The triple form does not accept empty values.");
		return;
	}
	
	if(a+1>10) {
		document.getElementById("message_insertion").innerHTML = "";
		document.getElementById("message_display").innerHTML = "";
		alert('Cannot add more then 10 triples.');
		return;
	}
	
	if(subject.startsWith("dbr:") && !dbr_set.has(subject)) {
		dbr_set.add(subject);
	}
	if (object.startsWith("dbr:")&& !dbr_set.has(object)) {
		dbr_set.add(object);
	}
	
	if(dbr_set.size>5) {
		document.getElementById("message_insertion").innerHTML = "";
		document.getElementById("message_display").innerHTML = "";
		alert('Cannot add more than 5 unique subject or object resources.');
		return;
	}
	
	subjects[a] = subject;
	predicates[a] = predicate;
	objects[a] = object;
	
	a++;
	
	document.getElementById("message_insertion").innerHTML = "Triple is added.";
	document.getElementById("message_display").innerHTML = "";
	document.getElementById("subject").value = "";
	document.getElementById("predicate").value = "";
	document.getElementById("object").value = "";
}

function display_triples() {
	var f = "<hr/>";
	for (var i=0; i<a; i++) {
		f += "Triple "+ i + ": " + subjects[i] + "," + predicates[i] + "," + objects[i] + "<br>";
	}
	document.getElementById("message_display").innerHTML = f;
}

function generate_graph() {
	var div = document.getElementById('graph');
	while(div.firstChild){
    div.removeChild(div.firstChild);
	}
	document.getElementById("message_insertion").innerHTML = "";
	document.getElementById("message_display").innerHTML = "";
	
	const g = new Dracula.Graph
	for (var i=0; i<a; i++) {
		g.addEdge(subjects[i], objects[i], { directed : true, label : predicates[i] })
	}
	const layouter = new Dracula.Layout.Spring(g)	
	const renderer = new Dracula.Renderer.Raphael('#graph', g)
	renderer.draw()
}