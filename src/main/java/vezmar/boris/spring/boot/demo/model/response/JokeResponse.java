package vezmar.boris.spring.boot.demo.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JokeResponse {

	@JsonProperty(value = "id")
	private Integer id;

	@JsonProperty(value = "content")
	private String content;

	@JsonProperty(value = "category")
	private String category;

	@JsonProperty(value = "likes")
	private Integer likes;

	@JsonProperty(value = "dislikes")
	private Integer dislikes;

	@JsonProperty(value = "difference")
	private Integer difference;

	public JokeResponse() {
	}

	public JokeResponse(Integer id, String content, String category, Integer likes, Integer dislikes,
			Integer difference) {
		this.id = id;
		this.content = content;
		this.category = category;
		this.likes = likes;
		this.dislikes = dislikes;
		this.difference = difference;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public Integer getDislikes() {
		return dislikes;
	}

	public void setDislikes(Integer dislikes) {
		this.dislikes = dislikes;
	}

	public Integer getDifference() {
		return difference;
	}

	public void setDifference(Integer difference) {
		this.difference = difference;
	}
}
