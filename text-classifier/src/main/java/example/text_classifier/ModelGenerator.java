package example.text_classifier;

import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.unsupervised.attribute.StringToWordVector;

import java.io.File;

public class ModelGenerator {

	public static void main(String[] args) throws Exception {
		String rootDirectory = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
				+ File.separator + "java" + File.separator + "example" + File.separator + "text_classifier"
				+ File.separator;
		String pathToJSONFile = rootDirectory + "News_Category_Dataset_v2.json";
		String pathToARFFFile = rootDirectory + "News_Category_Dataset_v2.arff";

		// converts given dataset from JSON to ARFF format
		JSONToARFFConverter converter = new JSONToARFFConverter();
		converter.convert(pathToJSONFile, pathToARFFFile);

		// loads the converted ARFF dataset
		DataSource source = new DataSource(pathToARFFFile);
		Instances data = source.getDataSet();
		if (data.classIndex() == -1) {
			data.setClassIndex(0);
		}

		// builds the classifier
		StringToWordVector filter = new StringToWordVector();
		filter.setAttributeIndices("last");
		FilteredClassifier classifier = new FilteredClassifier();
		classifier.setFilter(filter);
		classifier.setClassifier(new NaiveBayes());
		classifier.buildClassifier(data);

		// saves the generated classification model
		SerializationHelper.write(rootDirectory + "NewsClassification.model", classifier);
	}

}
