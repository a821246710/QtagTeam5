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
	
	/* the lower or upper case are not different */
	boolean hasTag(String tagName){
		for(Tag t : tags){
			if(t.name.toUpperCase().equals(tagName.toUpperCase()))
				return true;
		}
		return false;
	}
} 



