import java.io.Serializable;
import java.util.Date;
import java.util.List;

enum Language{
	Hindi,English;
}

enum Category{
	sci_fi,Action,Romantic,Thriller;
}

public class Movie implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int movieId;
	private String movieName;
	private Category movieType;
	private Language language;
	private Date releaseDate;
	private List<String> casting;
	private Double rating;
	private Double totalBusinessDone;
	
	//--------------Setter Methods--------------------
	
	public void setMovieId(int id) {
		movieId = id;
	}

	public void setMovieName(String name) {
		movieName = name;
	}
	
	public void setMovieType(Category type) {
		movieType = type;
	}
	
	public void setMovieLanguage(Language lang) {
		language = lang;
	}
	
	public void setMovieReleaseDate(Date d) {
		releaseDate = d;
	}
	
	public void setMovieCasting(List<String> cast) {
		casting = cast;
	}
	
	public void setMovieRating(double rate) {
		rating = rate;
	}
	
	public void setMovieTotalBussinessDone(double tbd) {
		this.totalBusinessDone = tbd;
	}
	
	//--------------Getter Methods-----------------
	
	public int getMovieId() {
		return movieId;
	}

	public String getMovieName() {
		return movieName;
	}
	
	public Category getMovieType() {
		return movieType;
	}
	
	public Language getMovieLanguage() {
		return language;
	}
	
	public Date getMovieReleaseDate() {
		return releaseDate;
	}
	
	public List<String> getMovieCasting() {
		return casting;
	}
	
	public double getMovieRating() {
		return rating;
	}
	
	public double getMovieTotalBussinessDone() {
		return totalBusinessDone;
	}
}
