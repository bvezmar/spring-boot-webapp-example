package vezmar.boris.spring.boot.demo.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JokeRequest {

	@JsonProperty(value = "content")
	private String content;

	@JsonProperty(value = "category")
	private String category;

	public JokeRequest() {
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
