import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieServices {
	
	private static MovieDAO movieDao = new MovieDAO();
	
	private void printError(Exception e) {
		System.out.println("Error in MovieService "+e.getMessage());
	}
	
	List<Movie> populateMovies(File file){
		List<Movie> movies = new ArrayList<>();
		try(Scanner sc = new Scanner(file)){
			while(sc.hasNext()) {
			String line [] = sc.nextLine().split(",");
			List<String> casting = new ArrayList<>();
			
			for(String cast:line[5].split("/")) {
				casting.add(cast);
			}
			
			Movie m = new Movie();
			m.setMovieId(Caster.getId(line[0]));
			m.setMovieName(line[1]);
			m.setMovieType(Caster.getCategory(line[2]));
			m.setMovieLanguage(Caster.getLang(line[3]));
			m.setMovieReleaseDate(Caster.getDate(line[4]));
			m.setMovieCasting(casting);
			m.setMovieRating(Caster.getDouble(line[6]));
			m.setMovieTotalBussinessDone(Caster.getDouble(line[7]));
			
			movies.add(m);
		}
		}
		catch(FileNotFoundException e) {
			printError(e);
		}
		return movies;
	}
	
	public boolean addAllMoviesinDB(List<Movie> movies) {
		int count = 0;
		for(Movie m:movies) {
			try {
				if(movieDao.addMovie(m)) {
					count+=1;
					continue;
				}
				else {
					System.out.println("Cannot add movie to Database");
					return false;
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return count==movies.size();
	}
	
	public void addMovie(Movie movie,List<Movie> movies) {
		try {
		movies.add(movie);
		System.out.println("Movie Added Succesfully");
		}
		catch(Exception e) {
			System.out.println("Cannot Add movie to list");
		}
	}
	
	public List<Movie> getMoviesByYear(int year){
		List<Movie> temp;
		temp = movieDao.getMoviesYear(year);
		return temp;
	}
	
	public void serializeMovies(List<Movie> movies,String filename) {
		try(FileOutputStream fos = new FileOutputStream(filename);
		ObjectOutputStream oos = new ObjectOutputStream(fos)){
			oos.writeObject(movies);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Movie> deserializeMovies(String filename) {
		List<Movie> movies = null;
		try(FileInputStream fos = new FileInputStream(filename);
		ObjectInputStream oos = new ObjectInputStream(fos)){
			movies = (List<Movie>) oos.readObject();
		}
		
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return movies;
	}
	
	public List<Movie> getMoviesReleasedInYear(int year){
		List<Movie> movies = new ArrayList<>();
		return movies;
	}
	
	public List<Movie> getMoviesByActor(String...actorNames){
		List<Movie> res = new ArrayList<>();
		List<Movie> movies = movieDao.getMovies();
		
		for(String actor:actorNames) {
			for(Movie m:movies) {
				for(String cast:m.getMovieCasting()) {
					if(cast.equalsIgnoreCase(actor)) {
						res.add(m);
					}
				}
			}
		}
		return res;
	}
	
	public void updateRatings(Movie movie,double rating,List<Movie> movies) {
		for(Movie m:movies) {
			if(m.getMovieId()==movie.getMovieId()) {
				m.setMovieRating(rating);
			}
		}
	}
	
	public void updateBussiness(Movie movie,double amount,List<Movie> movies) {
		for(Movie m:movies) {
			if(m.getMovieId()==movie.getMovieId()) {
				m.setMovieTotalBussinessDone(amount);
			}
		}
	}
	
	public Movie getId(List<Movie> movie,int id) {
		Movie z=null;
		for(Movie m:movie) {
			if(m.getMovieId()==id) {
				z = m;
				break;
			}
		}
		return z;
	}
	
	
}
