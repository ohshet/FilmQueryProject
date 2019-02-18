package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
		try {
			app.launch();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void launch() throws SQLException {
		Scanner input = new Scanner(System.in);
		startUserInterface(input);
		input.close();
	}

	private void startUserInterface(Scanner input) throws SQLException{
		System.out.println("***Film database lookup***");
		System.out.println("Enter your selection");
		System.out.println("(1) to look up a film by ID number");
		System.out.println("(2) to look up a film by keyword");
		System.out.println("(3) to exit");
		int userInput = input.nextInt();
		switch (userInput) {
		case 1: {
			System.out.println("Enter film ID number:");
			int filmId = input.nextInt();
			Film film = db.findFilmById(filmId);
			if (film.getTitle() == null) {
				System.out.println("Film not found");
			}
			else {
			System.out.println(film);
			}
			break;
		}
		case 2: {
			System.out.println("Enter search keyword");
			String keyword = input.next();
			keyword = "%" + keyword + "%";
			List<Film> filmList = db.findFilmByKeyword(keyword);
			if (filmList.size() == 0) {
				System.out.println("Film not found");
			}
			else {
			System.out.println(filmList);
			}
			break;
		}
		case 3: {
			System.out.println("Goodbye");
			break;
		}
		default: {
			System.out.println("Invalid input");
			break;
		}

		}
	}
}
