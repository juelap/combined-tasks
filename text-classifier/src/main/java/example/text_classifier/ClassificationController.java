package example.text_classifier;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClassificationController {

	private List<String> categoryResponse = new ArrayList<String>();

	@GetMapping("/classify")
	public CategoryResponse greeting(@RequestParam(value = "query") List<String> query) throws Exception {
		NewsClassifier classifer = new NewsClassifier();
		categoryResponse = classifer.classify(query);
		return new CategoryResponse(categoryResponse);
	}

}
