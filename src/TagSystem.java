import java.util.ArrayList;


public class TagSystem {
	FileManager flieManager = new FileManager();
	ArrayList<Data> data = new ArrayList<Data>();
	ArrayList<Tag> tags = new ArrayList<Tag>();
	
	void scanUptate(){
		ArrayList<String> fileList = flieManager.listFile(null,"");
		for(String filePath : fileList){
			/* To check whether the file is exist and have tags */
			for(Data d : data){
				if(d.path.equals(filePath)){
					d.isExist = true;
					if(d.tags.isEmpty())
						d.isEmptyTags = true;
					break;
				}
			}
		}
	}
}
