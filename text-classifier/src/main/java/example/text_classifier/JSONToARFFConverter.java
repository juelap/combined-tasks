package example.text_classifier;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

public class JSONToARFFConverter {

	public void convert(String jsonFilePath, String arffFilePath) {
		Gson gson = new Gson();
		String line;
		try (BufferedReader br = new BufferedReader(new FileReader(jsonFilePath));
				FileWriter writer = new FileWriter(arffFilePath);
				BufferedWriter bw = new BufferedWriter(writer)) {
			bw.write(writeHeader());
			while ((line = br.readLine()) != null) {
				News news = gson.fromJson(line, News.class);
				bw.write(news.getLine());
			}
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
	}

	private String writeHeader() {
		String arffHeader = "@RELATION News_Category_Dataset_v2\n"
				+ "@ATTRIBUTE category {POLITICS,WELLNESS,ENTERTAINMENT,TRAVEL,STYLE_AND_BEAUTY,OTHER}\n"
				+ "@ATTRIBUTE headline STRING\n" + "@DATA\n";
		return arffHeader;
	}

}
