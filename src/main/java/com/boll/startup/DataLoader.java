package com.boll.startup;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.boll.model.Actor;
import com.boll.model.Movie;
import com.boll.repo.ActorRepository;
import com.boll.repo.MovieRepository;

@Component
public class DataLoader implements ApplicationRunner {

	private MovieRepository movieRepository;
	private ActorRepository actorRepository;

	private String photoDir = "images/";

	@Autowired
	public DataLoader(MovieRepository userRepository, ActorRepository actorRepository) {
		this.movieRepository = userRepository;
		this.actorRepository = actorRepository;
	}

	public void run(ApplicationArguments args) {
		createData("Rocky", "Sanjay Dutt", "Skinny dude tries to convince us that he can fight off goons", "rocky.jpg",
				"90");
		createData("Betaj Baadshah", "Anil Kapoor", "Hairy creep does creepy things", "betaaj.jpg", "90");
		createData("Coolie No 1", "Govinda", "Short fat man tries to by funny", "coolie.jpeg", "90");
		createData("Darr", "Shahrukh Khan", "Bad actor tries to convince us that he is scary", "darr.jpeg", "90");
		createData("Khiladi", "Akshay Kumar", "Tall weirdo starts his career", "khiladi.jpg", "90");

		createData("Kaho Na Pyar Hai", "Hrithik Roshan", "This dude can dance", "kahona.jpg", "20");
		createData("Dangal", "Amir Khan", "Something about wrestling", "dangal.jpg", "21");
		createData("Kaliya", "Mithun", "Moronic peice of shit!", "kaliya.jpg", "90");
		createData("Pyasa", "Guru Dutt", " old timey movie, havent seen it!", "pyasa.jpeg", "ol");

	}

	private void createData(String movieName, String actorName, String movieDesc, String photoUrl, String era) {
		photoUrl = photoDir + photoUrl;
		Actor actor = createOrGetActor(actorName);
		Movie movie = createOrGetMovie(movieName, movieDesc, photoUrl, era);
		makeActorMovieRelatioShip(actor, movie);
	}

	private Actor createOrGetActor(String name) {

		Actor actor = null;
		List<Actor> actors = actorRepository.findByName(name);

		if (actors == null || actors.isEmpty()) {
			actor = new Actor(name);
			actor = actorRepository.save(actor);
		} else
			actor = actors.get(0);

		return actor;
	}

	private Movie createOrGetMovie(String name, String description, String photoUrl, String era) {

		Movie movie = null;
		List<Movie> movies = movieRepository.findByName(name);

		if (movies == null || movies.isEmpty()) {
			movie = new Movie(name, description, photoUrl, era);
			movie = movieRepository.save(movie);
		} else
			movie = movies.get(0);

		// movie = saveMovieWithPhoto(movie);

		return movie;
	}

	private void makeActorMovieRelatioShip(Actor actor, Movie movie) {
		List<Actor> actors = movie.getActors();

		if (actors == null || actors.isEmpty())
			actors = new ArrayList<Actor>();

		actors.add(actor);

		movie.setActors(actors);

		movie = movieRepository.save(movie);

		List<Movie> movies = actor.getMovies();
		if (movies == null || movies.isEmpty())
			movies = new ArrayList<Movie>();

		movies.add(movie);

		actor.setMovies(movies);

		actor = actorRepository.save(actor);

	}

	private Movie saveMovieWithPhoto(Movie movie) {

		File file = new File("src/main/resources/salman1.jpg");
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);

			movie.setPhoto(IOUtils.toByteArray(inputStream));
			movie = movieRepository.save(movie);
			return movie;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null)
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return movie;

	}
}