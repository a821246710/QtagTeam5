import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class FileManager {
	
	private File dbPath = new File("./data.db");
	private File rootDirectory = getRootDirectoryFromDB();
	
	public FileManager() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean setRootDirectory(String newRootDirectory){
		try{
			rootDirectory = new File(newRootDirectory);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("somthing error");
		}	
		return false;
	}
	
	private File getRootDirectoryFromDB(){
		String path = "";
		
		//try to load Data
		try(BufferedReader br = new BufferedReader(new InputStreamReader
				(new FileInputStream(dbPath), "UTF-8"))) {
			
	        path = br.readLine();	
	        	        
	    } catch (FileNotFoundException e) {	
			System.out.println("db missing");
			buildDB();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("somthing error");
		}	
		
		return new File(path);
	}
	
	public File getRootDirectory(){
		return rootDirectory;
	}
	
	private void buildDB(){
		boolean result = false;
		
		if (!dbPath.exists()) {
		    try{
		        dbPath.createNewFile();
		        result = true;
		    } catch(IOException e){
		    	System.out.println("error occur, can't create database");
		    }
		    
		    if(result) System.out.println("database created");  
		}
	}
	
	ArrayList<String> listFile(File folder){
		folder = (folder == null) ? rootDirectory : folder;
		
		ArrayList<String> A = new ArrayList<String>();
		for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	        	A.addAll(listFile(fileEntry));
	        } else {
	            A.add(folder.getName() + "/" + fileEntry.getName());
	        }
	    }		
		return A;
	}
	
	void fetchAllFromDB(ArrayList<Data> data,ArrayList<Tag> tags){
		
		//try to load Data
		try(BufferedReader br = new BufferedReader(new InputStreamReader
				(new FileInputStream(dbPath), "UTF-8"))) {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();	//skip first empty line
	        line = br.readLine();
	        while (line != null) {            
	            String[] ss = line.split("\\:");
	            Data newData = new Data(ss[0]);
	            data.add(newData);
	            for(int i=1;i<ss.length;i++){
	            	Tag inDataTag = null;
	            	boolean in_tags=false;
	            	for(Tag t : tags){
	            		if(t.name.equals(ss[i])){
	            			inDataTag = t;
	            			t.data.add(newData);
	            			in_tags = true;
	            			break;
	            		}
	            	}
	            	if(!in_tags){
	            		inDataTag = new Tag(ss[i]);
	            		inDataTag.data.add(newData);
	            		tags.add(inDataTag);	            		
	            	}
	            	
	            	newData.tags.add(inDataTag);
	            }	            	            
	            line = br.readLine();
	        }
	    } catch (FileNotFoundException e) {	
			System.out.println("no such file");
			buildDB();
		} catch (IOException e) {			
			e.printStackTrace();

			System.out.println("read file error");
		}	
	}
	
	void putAllBackToDB(ArrayList<Data> data,ArrayList<Tag> tags){
        try{
            BufferedWriter bufWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dbPath,false),"utf8"));
            bufWriter.write(rootDirectory.getPath() + System.lineSeparator());	//first root-dir line
            for(Data d : data){
            	bufWriter.write(d.getPath());
            	for(Tag t : d.tags){
            		bufWriter.write(":" + t.name);
            	}
            	bufWriter.write(System.lineSeparator());
            }
            bufWriter.close();
        } catch (FileNotFoundException e) {	
			System.out.println("no such file");
			buildDB();
		} catch(IOException e){
            e.printStackTrace();
            System.out.println("wirte file error");
        }
        
	}
	
}








