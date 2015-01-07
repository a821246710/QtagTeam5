import java.util.ArrayList;


public class Tag {
	String name;
	ArrayList<Data> data = new ArrayList<Data>();
	
	Tag(String name){
		this.name = name;
	}
	
	String getName(){ return name; }
	
	ArrayList<Data> getData(){ return data; }
}
