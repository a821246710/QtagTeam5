import java.util.ArrayList;


public class TagSystem {
	FileManager flieManager = new FileManager();
	ArrayList<Data> data = new ArrayList<Data>();
	ArrayList<Tag> tags = new ArrayList<Tag>();
	
	void scanUptate(){
		
		ArrayList<String> fileList = flieManager.listFile(null);
		ArrayList<Data> tempDL = new ArrayList<Data>();
		// reset all data satus
		for(Data d : data){
			d.isEmptyTags = false;
			d.isExist = false;
		}
		for(String filePath : fileList){
			/* To check whether the file is exist and have tags */
			boolean inData=false;
			for(Data d : data){
				if(d.path.equals(filePath)){
					inData = true;	
					d.isExist = true;
					if(d.tags.isEmpty())
						d.isEmptyTags = true;
					else
						d.isEmptyTags = false;
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
	
	Data getData(int index){
		return data.get(index);
	}
	
	ArrayList<Tag> parseTags(String str, String delimiter){
	
		ArrayList<Tag> tTags = new ArrayList<Tag>();
		String[] strTags = null;
		//if str is empty
		if(str.equals("") || str==null)
			return tTags;
		//parse search text
		if(delimiter.equals("&&")){
			strTags = str.split(" *\\&& *");
			for(int i=0;i<strTags.length;i++){
				boolean in_tags=false;
				for(Tag t : tags){					
					if(t.name.toUpperCase().equals(strTags[i].toUpperCase())){		//search don't distinguish lower or upper case				
						if(!tTags.contains(t)){
							tTags.add(t);
						}
						in_tags = true;
					}
				}
				if(!in_tags){
					//if the tag user enter dose not exist than return empty List
					return new ArrayList<Tag>();
				}	
			}
		}
		//parse edit text
		else if(delimiter.equals(",")){			
			strTags = str.split(" *\\, *");			
			for(int i=0;i<strTags.length;i++){
				Tag pTag = null;
				boolean in_tags=false;
				for(Tag t : tags){
					if(t.name.equals(strTags[i])){
						pTag = t;
						in_tags = true;
						break;
					}
				}
				if(!in_tags){
					pTag = new Tag(strTags[i]);
					tags.add(pTag);
					
				}	
				tTags.add(pTag);
			}
		}
		
		return tTags;		
	}
	
	void setConnected(Data data, ArrayList<Tag> ALT){
		data.tags = ALT;
		for(Tag t : ALT){
			if(!t.data.contains(data))
				t.data.add(data);
		}
	}
	
	// Upper case or lower case are not different  when search
	ArrayList<Data> search(String str){
		
		if(str.equals("") || str==null)
			return this.data;
		
		ArrayList<Data> data = new ArrayList<Data>();
		ArrayList<Tag> tags = parseTags(str,"&&");
		
		//find data that exist in every tags that we parsed
		for(Tag t : tags){
			//check the data we find has every tag that we parsed
			for(Data d : t.data){
				boolean in_all_tags=true;
				for(Tag t2 : tags){
					//d contains all t2 than in_all_tags will be true
					in_all_tags = in_all_tags && d.hasTag(t2.name);
				}
				if(in_all_tags && !data.contains(d))
					data.add(d);
			}
		}
		return data;
	}
} 










