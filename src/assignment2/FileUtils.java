/**
 *File name: FileUtils.java 
 *Author: Oroi S. Wang, 040886757
 *Course: CST8284 – OOP
 *Assignment: 2
 *Date: Jan 12, 2018
 *Professor: RAYMOND PETERKIN
 *Purpose: To deal with File I/O
 */


package assignment2;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

/**
 * Class FileUtils contains general File I/O methods for saving ancillary files
 * @author Oroi S. Wang
 * @version 1.0
 * @see assignment2
 * @since 1.8.0_version
 */


public class FileUtils {
	
	/**
	 * Name of the file
	 */
	private String fileName;
	
	/**
	 * Save URLs to the file
	 * @param f File to be saved
	 * @param ar: ArrayList of URLs as Strings
	 */
	public static void saveFileContents(File f, ArrayList<String> ar) {
		if(f.exists())
			f.delete();
		
		try{
			PrintWriter output= new PrintWriter(f);
			
			for(int i=0; i<ar.size(); i++) {
				output.println(ar.get(i));
			}
			
			output.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Read URLs from file
	 * @param f File to be read
	 * @return ArrayList of URLs as Strings
	 */
	public static ArrayList<String> getFileContentsAsArrayList(File f){
		ArrayList<String> ar= new ArrayList<>();
		
		try{
			Scanner input= new Scanner(f);
			
			while(input.hasNext()) {
				ar.add(input.nextLine());
			}
			
			input.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
		return ar;
	}
	
	/**
	 * Check if file exists
	 * @param f File to be checked
	 * @return True if file exists
	 */
	public static boolean fileExists(File f) {
		if(f.exists()&&f.isFile())
			return true;
		else
			return false;		
	}
	
	/**
	 * Check if file exists
	 * @param s Name of file to be checked
	 * @return True if file exists
	 */
	public static boolean fileExists(String s) {
		return fileExists(new File(s));
		
	}
	

	/**
	 * Get name of file
	 * @return file name as String
	 */
	public String getFileName() {
		return fileName;
	}
	
	/**
	 * Set name of file
	 * @param s file name
	 */
	public void setFileName(String s) {
		fileName=s;
	}
	
}
