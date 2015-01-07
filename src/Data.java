import java.util.ArrayList;


public class Data {
	String path;
	ArrayList<Tag> tags = new ArrayList<Tag>();
	boolean isExist = false;
	boolean isEmptyTags = false;
	
	Data(String path){
		this.path = path;
	}
	
	String getPath(){ return path; }
	
	ArrayList<Tag> getTags(){ return tags; }
} 
