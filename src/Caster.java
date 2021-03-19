import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Caster {
	
	public static int getId(String id) {
		int ID = Integer.parseInt(id);
		return ID;
	}
	
	public static Category getCategory(String type) {
		Category category = Category.valueOf(type);
		return category;
	}
	
	public static Language getLang(String lang) {
		Language language = Language.valueOf(lang);
		return language;
	}
	
	public static Date getDate(String d) {
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
		Date date = null;
		try {
			 date = sdf.parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static double getDouble(String d) {
		double value = Double.parseDouble(d);
		return value;
	}
	
	public static String listCombiner(List<String> casting) {
		String cast = "";
		for(String i:casting) {
			cast+=i+"/";
		}
		cast =cast.substring(0,cast.length()-2);
		return cast;
	}
	
}
