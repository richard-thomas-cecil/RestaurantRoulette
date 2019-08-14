import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperationManager {
	
	private String fileName;
	private File workingFile;
	private BufferedWriter fileWriter;
	private BufferedReader fileReader;
	
	FileOperationManager(String name) throws IOException{
		this.fileName = name;
		this.workingFile = new File(name);
	}
	
	public boolean doesExist() {
		return workingFile.exists();
	}
	
	public void createFile() {
		try {
			workingFile.createNewFile();
		} catch (IOException e) {
			System.out.print("file already exists");
		}
	}
	
	public void overWriteFile() throws IOException {
		
		if(workingFile.delete()) {
			//System.out.print("FileRemoved \r\n");
		}
		else
			System.out.print("Nope \r\n");
		workingFile.createNewFile();
	}
	
	public void writeFile(String toWrite) throws IOException {
		fileWriter.write(toWrite);
		fileWriter.newLine();
	}
	
	public String readFile() throws IOException {
		return fileReader.readLine();
		
	}
	
	public void openFileRead(){
		try{
			fileReader = new BufferedReader(new FileReader(fileName));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void openFileWrite() throws IOException {
		fileWriter = new BufferedWriter(new FileWriter(fileName, true));
	}
	
	public void closeFileWrite() throws IOException{
		fileWriter.close();
	}
	
	public void closeFileRead() throws IOException {
		fileReader.close();
	}

}
