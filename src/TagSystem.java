import java.util.ArrayList;


public class TagSystem {
	FileManager flieManager = new FileManager();
	ArrayList<Data> data = new ArrayList<Data>();
	ArrayList<Tag> tags = new ArrayList<Tag>();
	
	void scanUptate(){
		ArrayList<String> fileList = flieManager.listFile(null,"");
		ArrayList<Data> tempDL = new ArrayList<Data>();
		for(String filePath : fileList){
			/* To check whether the file is exist and have tags */
			boolean inData=false;
			for(Data d : data){
				if(d.path.equals(filePath)){
					inData = true;	
					d.isExist = true;
					if(d.tags.isEmpty())
						d.isEmptyTags = true;
					break;
				}
			}
			
			if(!inData){
				Data tempD = new Data(filePath);
				tempD.isExist = true;
				tempD.isEmptyTags = true;
				tempDL.add(tempD);
			}
		}
		data.addAll(tempDL);
	}
}
