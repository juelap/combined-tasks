package example.text_classifier;

import java.util.ArrayList;
import java.util.List;

public class CategoryResponse {

	private final List<String> categories = new ArrayList<String>();

	public CategoryResponse(List<String> categories) {
		for (String category : categories) {
			this.categories.add(category);
		}
	}

	public List<String> getCategory() {
		return categories;
	}

}
