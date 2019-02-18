package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";

	public DatabaseAccessorObject() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) throws SQLException {
		String user = "student";
		String pass = "student";
		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sql = "select * from film where id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet rs = stmt.executeQuery();
		int id = 0, releaseYear = 0, languageId = 0 , length = 0;
		String title = null, description = null, rating = null;
		while (rs.next()) {
		id = rs.getInt("id");
		title = rs.getString("title");
		description = rs.getString("description");
		releaseYear = rs.getInt("release_year");
		languageId = rs.getInt("language_id");
		length = rs.getInt("length");
		rating = rs.getString("rating");
		}
		List<Actor> actorList = this.findActorsByFilmId(filmId);
		Film film = new Film (id, title, description, releaseYear, languageId, length, rating, actorList);
		rs.close();
		stmt.close();
		conn.close();
		return film;
	}

	public Actor findActorById(int actorId) throws SQLException {
		String user = "student";
		String pass = "student";
		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sql = "select * from actor where id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, actorId);
		ResultSet rs = stmt.executeQuery();
		int id = 0;
		String firstName = null, lastName = null;
		while (rs.next()) {
		id = rs.getInt("id");
		firstName = rs.getString("first_name");
		lastName = rs.getString("last_name");
		}
		Actor actor = new Actor (id, firstName, lastName);
		rs.close();
		stmt.close();
		conn.close();
		return actor;
	}

	public List<Actor> findActorsByFilmId(int filmId) throws SQLException {
		String user = "student";
		String pass = "student";
		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sql = "select actor.first_name, actor.last_name from actor join film_actor on actor.id = film_actor.actor_id join film on film_actor.film_id = film.id where film.id = ?;";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet rs = stmt.executeQuery();
		List<Actor> actorList = new ArrayList<>();
//		int id = 0;
		String firstName = null, lastName = null;
		while (rs.next()) {
		firstName = rs.getString("first_name");
		lastName = rs.getString("last_name");
		actorList.add(new Actor (firstName, lastName));
		}
		rs.close();
		stmt.close();
		conn.close();
		return actorList;
	}
	
	public List<Film> findFilmByKeyword(String keyword) throws SQLException {
		String user = "student";
		String pass = "student";
		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sql = "select * from film where title like ? or description like ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1,  keyword);
		stmt.setString(2, keyword);
		ResultSet rs = stmt.executeQuery();
		Film film = null;
		List<Film> filmList = new ArrayList<>();
		int id = 0, releaseYear = 0, languageId = 0 , length = 0;
		String title = null, description = null, rating = null;
		while (rs.next()) {
		id = rs.getInt("id");
		title = rs.getString("title");
		description = rs.getString("description");
		releaseYear = rs.getInt("release_year");
		languageId = rs.getInt("language_id");
		length = rs.getInt("length");
		rating = rs.getString("rating");
		List<Actor> actorList = this.findActorsByFilmId(id);
		film = new Film (id, title, description, releaseYear, languageId, length, rating, actorList);
		filmList.add(film);
		}
		rs.close();
		stmt.close();
		conn.close();
		return filmList;
	}
	
	public String getLanguage(int languageId) throws SQLException {
		String user = "student";
		String pass = "student";
		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sql = "select * from language where id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, languageId);
		ResultSet rs = stmt.executeQuery();
		String language = null;
		while (rs.next()) {
		language = rs.getString("name");
		}
		rs.close();
		stmt.close();
		conn.close();
		return language;
	}


}
