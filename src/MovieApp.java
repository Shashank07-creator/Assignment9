import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieApp {
	private static MovieServices movie = new MovieServices();
	private static List<Movie> movies;
	
	private static void display(List<Movie> movies) {
		for(Movie m: movies) {
			System.out.println("=============================================");
			System.out.println("movie id:" + m.getMovieId());
			System.out.println("movie name: " + m.getMovieName());
			System.out.println("movie type: " + m.getMovieType());
			System.out.println("movie language: " + m.getMovieLanguage());
			System.out.println("release date: " + m.getMovieReleaseDate());
			System.out.println("cast: " + m.getMovieCasting());
			System.out.println("rating: " + m.getMovieRating());
			System.out.println("total business: " + m.getMovieTotalBussinessDone());
		}
	}
	
	public static void main(String []args) {
		List<Movie> temp;
		
		Scanner sc = new Scanner(System.in);
		String serializePath = "MyObject.txt";
		
		int choice;
		System.out.println("Enter\n1. Read from File \n2. Populate DB \n3. Add movie \n4. serialize \n5. Deserialize \n6. Search By Year\n7. Search By Actor\n8. Update Rating\n9. Update Business");
		choice = sc.nextInt();
		switch(choice) {
		case 1:
		File file = new File("MovieDetails.txt");
		movies = movie.populateMovies(file);
		display(movies);
		break;
		
		case 2:
		movie.addAllMoviesinDB(movies);
		System.out.println("Done");
		break;
		case 3:
			System.out.println("Enter movie ID,name,category,language,Release Date,cast,rating,business");
			String details = sc.nextLine();
			String line[] = details.split(",");
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
			
			movie.addMovie(m, movies);
			break;
		case 4:
		movie.serializeMovies(movies, serializePath);
		break;
		case 5:
		temp = movie.deserializeMovies(serializePath);
		display(temp);
		break;
		case 6:
			int year;
			System.out.println("Enter Actor name to search");
			year = sc.nextInt();
			temp = movie.getMoviesByYear(year);
			display(temp);
			break;
		case 7:
		String actor;
		System.out.println("Enter Actor name to search");
		actor = sc.nextLine();
		temp = movie.getMoviesByActor(actor);
		display(temp);
		break;
		case 8:
			int id;
			double rating;
			System.out.println("Enter Movie ID to update");
			id = sc.nextInt();
			
			Movie m1 = movie.getId(movies, id);
					
			System.out.println("Enter Movie Rating to update");
			rating = sc.nextDouble();
			
			movie.updateRatings(m1, rating, movies);
			System.out.println("Updated !");
			break;
		case 9:
			int ID;
			double amount;
			System.out.println("Enter Movie ID to update");
			id = sc.nextInt();
			
			Movie m11 = movie.getId(movies, id);
					
			System.out.println("Enter Movie Business to update");
			amount = sc.nextDouble();
			
			movie.updateBussiness(m11, amount, movies);
			System.out.println("Updated !");
			break;
		}
		sc.close();
	}
}
