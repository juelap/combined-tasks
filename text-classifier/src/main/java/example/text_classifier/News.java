package example.text_classifier;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class News {

	private String category;
	private String headline;
	private String authors;
	private String link;
	private String short_description;
	private String date;

	public News() {
		this.category = null;
		this.headline = null;
		this.authors = null;
		this.link = null;
		this.short_description = null;
		this.date = null;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getShort_description() {
		return short_description;
	}

	public void setShort_description(String short_description) {
		this.short_description = short_description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLine() {
		Set<String> categories = new HashSet<>(
				Arrays.asList("POLITICS", "WELLNESS", "ENTERTAINMENT", "TRAVEL", "STYLE & BEAUTY"));
		if (!categories.contains(this.category)) {
			this.category = "OTHER";
		}
		this.category = category.replaceAll("\\s", "_");
		this.category = category.replaceAll("&", "AND");
		this.headline = headline.replaceAll("'", "\\\\'");
		return category + ",\'" + headline + "\'\n";
	}

}
