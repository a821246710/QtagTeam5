import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;


public class FileManager {
	
	File rootDirectory = new File("C:\\Users\\joy\\Desktop\\軟體工程");
	
	
	ArrayList<String> listFile(File folder, String parent){
		folder = (folder == null) ? rootDirectory : folder;
		
		ArrayList<String> A = new ArrayList<String>();
		for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	        	A.addAll(listFile(fileEntry, parent + fileEntry.getName()+"/"));
	        } else {
	            A.add(parent+fileEntry.getName());
	        }
	    }		
		return A;
	}
	
	void fetchAllFromDB(ArrayList<Data> data,ArrayList<Tag> tags){
		
		//try to load Data
		try(BufferedReader br = new BufferedReader(new InputStreamReader
				(new FileInputStream(new File("data\\Data.txt").getAbsoluteFile()), "UTF-8"))) {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();	//skip first empty line
	        line = br.readLine();
	        while (line != null) {            
	            String[] ss = line.split("\\:");
	            Data newData = new Data(ss[0]);
	            data.add(newData);
	            for(int i=1;i<ss.length;i++){
	            	Tag t;
	            	if(tags.contains(ss[i])){
	            		t = tags.get(tags.indexOf(ss[i]));	
	            		t.data.add(newData);
	            	}
	            	else{
	            		t = new Tag(ss[i]);
	            		tags.add(t);
	            	}
	            	
	            	newData.tags.add(t);
	            }	            	            
	            line = br.readLine();
	        }
	    } catch (FileNotFoundException e) {	
			System.out.println("no such file");
		} catch (IOException e) {			
			e.printStackTrace();
			System.out.println("讀檔發生錯誤");
		}	
		
		
		
	}
	
	void putAllBackToDB(ArrayList<Data> data,ArrayList<Tag> tags){
		File Datafile = new File("data\\Data.txt").getAbsoluteFile();//建立檔案，準備寫檔
        try{
            BufferedWriter bufWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Datafile,false),"utf8"));
            bufWriter.write(System.lineSeparator());	//first empty line
            for(Data d : data){
            	bufWriter.write(d.getPath() + System.lineSeparator());           
            }
            bufWriter.close();
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("寫檔發生錯誤");
        }
        
	}
	
}








