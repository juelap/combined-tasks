package example.text_classifier;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ARFFFileBuilder {

	public void build(String pathToARFFTestFile, List<String> query) {
		try (FileWriter writer = new FileWriter(pathToARFFTestFile); BufferedWriter bw = new BufferedWriter(writer)) {
			bw.write(writeHeader());
			for (String category : query) {
				bw.write("?," + category + "\n");
			}
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
	}

	private String writeHeader() {
		String arffHeader = "@RELATION test\n"
				+ "@ATTRIBUTE category {POLITICS,WELLNESS,ENTERTAINMENT,TRAVEL,STYLE_AND_BEAUTY,OTHER}\n"
				+ "@ATTRIBUTE headline STRING\n" + "@DATA\n";
		return arffHeader;
	}

}
