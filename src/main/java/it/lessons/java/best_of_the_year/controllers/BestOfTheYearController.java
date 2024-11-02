package it.lessons.java.best_of_the_year.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.lessons.java.best_of_the_year.model.Movie;
import it.lessons.java.best_of_the_year.model.Song;

@Controller
@RequestMapping("/")
public class BestOfTheYearController {

	@GetMapping("/bestoftheyear")
	public String bestOfTheYear(@RequestParam("name") String name, Model model) {

		model.addAttribute("name", name);

		return "bestoftheyear";

	}

	private List<Movie> getBestMovies() {
		List<Movie> listMovies = new ArrayList<>();

		listMovies.add(new Movie(1, "Perfect days"));
		listMovies.add(new Movie(2, "Past Lives"));
		listMovies.add(new Movie(3, "Povere Creature"));
		listMovies.add(new Movie(4, "Furiosa"));
		listMovies.add(new Movie(5, "Dune - Part two"));
		listMovies.add(new Movie(6, "Inside Out 2"));
		listMovies.add(new Movie(7, "Kind of Kindness"));
	
		return listMovies;
	}

	private List<Song> getBestSongs() {
		List<Song> listSongs = new ArrayList<>();
		
		listSongs.add(new Song(1, "Modena City Ramblers - In un giorno di pioggia"));
		listSongs.add(new Song(2, "STRLGHT - Rave"));
		listSongs.add(new Song(3, "Murubutu - Le notti bianche"));
		listSongs.add(new Song(4, "Claudio Lolli - Borghesia"));
		listSongs.add(new Song(5, "Finley - Diventerai una star"));
		listSongs.add(new Song(6, "Rancore - Eden"));
		listSongs.add(new Song(7, "Alice in chains - Nutshell"));
		
		return listSongs;
	}
	
	private boolean isVisible(List<?> list) {
		return !list.isEmpty();
	}
	
	private boolean isVisible(String stringa) {
		return stringa == null ? false : stringa.trim().length() != 0;
	}

	@GetMapping("/movies")
	public String movies(Model model) {
		model.addAttribute("visible", isVisible(getBestMovies()));
		model.addAttribute("movies", getBestMovies());

		return "movies";
		
	}

	@GetMapping("/songs")
	public String songs(Model model) {
		model.addAttribute("visible", isVisible(getBestSongs()));
		model.addAttribute("songs", getBestSongs());
		return "songs";

	}
	
	@GetMapping("/movies/{id}")
	public String filteredMovies(@PathVariable int id, Model model) {
		for(Movie movie : getBestMovies()) {
			if(movie.getId() == id) {
				model.addAttribute("visible", isVisible(movie.getTitle()));
				model.addAttribute("movie", movie.getTitle());
			}
		}
		
		return "movies";
	}
	
	@GetMapping("/songs/{id}")
	public String filteredSongs(@PathVariable int id, Model model) {
		for(Song song : getBestSongs()) {
			if(song.getId() == id) {
				model.addAttribute("visible", isVisible(song.getTitle()));
				model.addAttribute("song", song.getTitle());
			}
		}
		return "songs";
	}
	
}
