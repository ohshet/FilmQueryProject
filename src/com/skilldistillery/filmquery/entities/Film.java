package com.skilldistillery.filmquery.entities;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.filmquery.database.DatabaseAccessorObject;

public class Film {
	private int id, releaseYear, languageId, length;
	private String title, description, rating;
	private List<Actor> actorList;

	public Film(int id, String title, String description, int releaseYear, int languageId, int length, String rating) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.languageId = languageId;
		this.length = length;
		this.rating = rating;
	}

	public Film(int id, String title, String description, int releaseYear, int languageId, int length, String rating,
			List<Actor> actorList) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.languageId = languageId;
		this.length = length;
		this.rating = rating;
		this.actorList = actorList;
	}

	public String toString() {
		DatabaseAccessorObject dao = new DatabaseAccessorObject();
		String language = null;
		try {
			language = dao.getLanguage(languageId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "ID: " + id + "\nTitle: " + title + "\nDescription: " + description + "\nYear: " + releaseYear
				+ "\nLanguage: " + language + "\nLength: " + length + " Minutes\nRating: " + rating + "\nCast: "
				+ actorList + "\n";

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + languageId;
		result = prime * result + length;
		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
		result = prime * result + releaseYear;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (languageId != other.languageId)
			return false;
		if (length != other.length)
			return false;
		if (rating == null) {
			if (other.rating != null)
				return false;
		} else if (!rating.equals(other.rating))
			return false;
		if (releaseYear != other.releaseYear)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}
