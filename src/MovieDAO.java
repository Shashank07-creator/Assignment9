import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import Connectivity.Connect;

public class MovieDAO {
	private Connection con = Connect.getConnection();
	
	public boolean addMovie(Movie m) throws SQLException {
		String query = "insert into movie values(?,?,?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(query);
		
		
		
		ps.setInt(1, m.getMovieId());
		ps.setString(2, m.getMovieName());
		ps.setString(3, m.getMovieType().toString());
		ps.setString(4, m.getMovieLanguage().toString());
		
		Date d = new Date(m.getMovieReleaseDate().getTime());
		
		ps.setDate(5, d);
		String cast = Caster.listCombiner(m.getMovieCasting());
		
		ps.setString(6, cast);
		ps.setDouble(7, m.getMovieRating());
		ps.setDouble(8,m.getMovieTotalBussinessDone());
		
		try {
			int flag = ps.executeUpdate();
			return flag>0;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Movie> getMovies(){
		List<Movie> movies = new ArrayList<>();
		try {
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("Select * from movie");
		while(rs.next()) {
			Movie m = new Movie();

			m.setMovieId(rs.getInt(1));
			m.setMovieName(rs.getString(2));
			m.setMovieType(Caster.getCategory(rs.getString(3)));
			m.setMovieLanguage(Caster.getLang(rs.getString(4)));
			m.setMovieReleaseDate(Caster.getDate(rs.getString(5)));
			
			List<String> casting = new ArrayList<>();
			
			for(String cast:rs.getString(6).split("/")) {
				casting.add(cast);
			}
			
			m.setMovieCasting(casting);
			m.setMovieRating(rs.getDouble(7));
			m.setMovieTotalBussinessDone(rs.getDouble(8));
			movies.add(m);
		}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return movies;
	}
	
	public List<Movie> getMoviesYear(int year){
		List<Movie> movies = new ArrayList<>();
		try {
		PreparedStatement stmt = con.prepareStatement("Select * from movie where release_date like ?%");
		stmt.setInt(1, year);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Movie m = new Movie();

			m.setMovieId(rs.getInt(1));
			m.setMovieName(rs.getString(2));
			m.setMovieType(Caster.getCategory(rs.getString(3)));
			m.setMovieLanguage(Caster.getLang(rs.getString(4)));
			m.setMovieReleaseDate(Caster.getDate(rs.getString(5)));
			
			List<String> casting = new ArrayList<>();
			
			for(String cast:rs.getString(6).split("/")) {
				casting.add(cast);
			}
			
			m.setMovieCasting(casting);
			m.setMovieRating(rs.getDouble(7));
			m.setMovieTotalBussinessDone(rs.getDouble(8));
			movies.add(m);
		}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return movies;
	}

}
