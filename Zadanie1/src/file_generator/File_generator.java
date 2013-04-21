package file_generator;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class File_generator {

	private static final double MB = Math.pow(1024,  2); // calculating bytes in megabyte
	private static final String TEXT = "Just some random text, random 1, random 3, etc. \n";
    private static final int SIZE_OF_STRING = TEXT.getBytes().length;
	
    public static void main(String[] args) throws IOException{
    	List<String> records = new ArrayList<String>();
    	int currentSize = 0;
    	while(currentSize < 30 * MB)
    	{
    		records.add(TEXT);
    		currentSize += SIZE_OF_STRING;
    	}
    	System.out.println("Size of string in MB:" + currentSize/MB + " size:" + records.size());
    	System.out.println("Enter directory to file.");
    	String dir = directory(); //directory od file
    	String name = create(dir); //name of created file
    	if(name != "error")
    	writeToFile(dir, name, records);
    	else
    	System.out.println("Error occured with directory.");
    	System.out.println("Succesfully created '"+ dir + name + ".txt'");
    }
    //create a file in given directory returns name of file
    public static String create(String dir)
    {
    	String name = "file";
    	int i=0;//file name number var adder
    	while(true){
    	try {
    		File file = new File(dir,name+".txt");
    		
    		if(file.createNewFile()){
    			System.out.println("File " + name + " created");
    			break;
    		}
    		else{
    			System.out.println("File " + name + " already exists");
    			name += i;
    			i++;
    		}
    	} catch (IOException e){
    		e.printStackTrace();
    		name = "error";
    		break;
    	}
    	}
    	return  name;
    }
    //Get directory form user, check whether you can use it and return good directory in string
    private static String directory(){
    	String directory = "";
    	 @SuppressWarnings("resource")
 		Scanner in = new Scanner(System.in);
      	directory = in.nextLine();
    	File temp = new File(directory);
    	while(!temp.exists()){
    		System.out.println("Please give me another directory, because that doesnt suit me: ");
    		in = new Scanner(System.in);
          	directory = in.nextLine();
    		temp = new File(directory);
    	}
     	
    	return directory;
    }
    //This method write generated list of strings into file specified earlier
    private static void writeToFile(String directory, String name, List<String> records) throws IOException{
    	FileWriter fstream = new FileWriter(directory+name+".txt");
    	BufferedWriter out = new BufferedWriter(fstream);
    	for (String record: records){
    	out.write(record);
    	}
    	fstream.flush();
    	fstream.close();
    }

}
