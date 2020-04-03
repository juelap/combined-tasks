package example.text_classifier;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import weka.classifiers.meta.FilteredClassifier;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils.DataSource;

public class NewsClassifier {

	public List<String> classify(List<String> query) throws Exception {
		String rootDirectory = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
				+ File.separator + "java" + File.separator + "example" + File.separator + "text_classifier"
				+ File.separator;
		String pathToARFFTestFile = rootDirectory + "classify.arff";
		String classificationModel = rootDirectory + "NewsClassification.model";
		List<String> categoryResponse = new ArrayList<String>();

		// builds the ARFF file to be classified
		ARFFFileBuilder fileBuilder = new ARFFFileBuilder();
		fileBuilder.build(pathToARFFTestFile, query);

		// loads the ARFF to be classified
		DataSource source = new DataSource(pathToARFFTestFile);
		Instances testData = source.getDataSet();
		if (testData.classIndex() == -1) {
			testData.setClassIndex(0);
		}

		// load the classifier
		FilteredClassifier classifier = (FilteredClassifier) SerializationHelper.read(classificationModel);

		// classify the instances
		for (Instance newInstance : testData) {
			double prediction = classifier.classifyInstance(newInstance);
			String predictionStr = testData.classAttribute().value((int) prediction);
			categoryResponse.add(predictionStr);
		}
		return categoryResponse;
	}

}
