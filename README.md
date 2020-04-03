# Instructions

## Text Classifier

### To be installed:
* Java
* Maven

Import the **text-classifier** folder as a Maven project into an IDE (preferrably Eclipse). <br>
Download the JSON dataset from the clickable [link](https://www.kaggle.com/rmisra/news-category-dataset) and store into ```text-classifier/src/main/java/example/text-classifier/``` directory. The whole program logic is located in this directory.<br><br>
To build the classifier, run the ```ModelGenerator.java``` file from the IDE (e.g. Eclipse). The model will be generated and saved in the same directory the latter Java file is located. <br><br>
To run the REST service at ```localhost:8090/classify```, run the ```RestServiceApplication.java``` file from the IDE. Spring Boot lines will be automatically printed in the console hinting that the service is running. In order to use the news classification service, type the following URL ```localhost:8090/classify?query="headline_1"&query="headline_2"&query="headline_n"```. From one to a number of headlines can be given in the shown format and after pressing **ENTER**, the predicted respective categories will be printed in the website in a JSON format.

### Implementation details
I have used the WEKA library for building the classifier and classifying the news articles. Since this library only reads its own JSON format and can not read the given JSON format of the dataset, I have converted the dataset from JSON format to ARFF format. ARFF format is the default format of WEKA library. After the conversion, I have built the classification model and saved it as ```NewsClassification.model``` in the root directory.<br><br>
I have used Spring for building the RESTful news classification Web Service. I get the news headlines as a query and save it in a String array. Then I build one more time the unclassified dataset in an ARFF format so that the generated model can read and classify. Then I load the saved model and classify the given headlines into categories. Lastly, I reposond with the predicted categories which are shown in the website in a JSON format.

## Triple Graph Generator

Inside the ```triple_graph_generator``` your will find the HTML, CSS and JS files. Double click on ```index.html``` to open the website in a browser. A form will be shown, where RDF triples can be inserted as subject, predicate, object. After inserting a triple, it can be saved by clicking the **Save** button. Whenever we want to see a list of the triples we have inserted, click on the **Display** button. At any time, we can click on the **Generate Graph** button to generate a directed graph containing the triples that we have inserted up to that moment.

### Implementation details
Each time a triple is inserted on the form and then saved through the **Save** button, the subject, predicate and object field texts are saved into respective arrays in ```script.js```. Before being saved, it is any of the triple form fields are empty since this is not allowed. Furthermore, it it checked if less than or equal to ten triples are saved so far. Lastly, it is checked that no more than five unique ```dbr:``` are added so far. If all these three checks are surpassed, then the given triple will be saved.<br><br>
For generating the given graph at any time, I have used the Dracula library, whom source code can be found on the ```dracula-master``` folder and under the clickable [link](https://github.com/strathausen/dracula).