/**
 *File name: MyJavaFXBrowser.java 
 *Author: Oroi S. Wang, 040886757
 *Course: CST8284 – OOP
 *Assignment: 2
 *Date: Jan 12, 2018
 *Professor: RAYMOND PETERKIN
 *Purpose: Create a web browser
 */


package assignment2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import java.io.File;
import java.util.ArrayList;

/**
 * Class MyJavaFXBrowser creates a web browser and display it
 * @author Oroi S. Wang
 * @version 1.0
 * @see assignment2
 * @since 1.8.0_version
 */

public class MyJavaFXBrowser extends Application {
	
	
	
	/**
	 * Application start
	 */
	@Override
	public void start(Stage primaryStage) {
		/**
		 * Create new web page
		 */
	    WebPage currentPage = new WebPage();
		WebView webView = currentPage.getWebView();
		WebEngine webEngine = currentPage.createWebEngine(primaryStage);
		
		/**
		 * Load default web page
		 */
		webEngine.load(getDefaultPage());		

		/**
		 * Border pane to hold all visible elements
		 */
		BorderPane root = new BorderPane();
		/**
		 * Display the web page
		 */
		root.setCenter(webView);
		/**
		 * Display menu and address bar
		 */
		root.setTop(Menus.getTopPanel(webView));
		/**
		 * Display xml code
		 */
		root.setBottom(Menus.getBottomPanel(webView));
		/**
		 * Display history
		 */
		root.setRight(Menus.getRightPanel(webView));
	

		/**
		 * Display everything on window
		 */
		Scene scene = new Scene(root, 800, 500);
		primaryStage.setScene(scene);
		primaryStage.show();	
		

	}
	
	/**
	 * Start executing program
	 * @param args args
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	/**
	 * Application stop; save bookmarks to file
	 */
	@Override
	public void stop() {
		File f=new File("bookmarks.web");
		FileUtils.saveFileContents(f, Menus.getBookmarks());
	}
	
	/**
	 * Get default web page to be loaded
	 * @return URL as String
	 */
	public String getDefaultPage() {
		File f= new File("default.web");
		ArrayList<String> ar= new ArrayList<>();
		
		if(FileUtils.fileExists(f)) {
			ar=FileUtils.getFileContentsAsArrayList(f);
			return ar.get(0);
		}
		else {
			return "https://www.google.ca/";
		}
	}
	


}



/**
 * References
 * Code.Makery. (2014). JavaFX Dialogs. Retrieved from http://code.makery.ch/blog/javafx-dialogs-official/ . Retrieved from http://code.makery.ch/blog/javafx-dialogs-official/
 * Java-Buddy. (2012). JavaFX 2.0: Set Accelerator (KeyCombination) for Menu Items [WebPage]. Retrieved from http://java-buddy.blogspot.ca/2012/02/javafx-20-set-accelerator.html 
 * Java2s.com. JavaFX Tutorial - JavaFX WebEngine. [WebPage]. Retrieved from http://www.java2s.com/Tutorials/Java/JavaFX/1500__JavaFX_WebEngine.htm
 * stack Overflow. (2015). How to program Back and Forward buttons in JavaFX with WebView and WebEngine?. Retrieved from https://stackoverflow.com/questions/18928333/how-to-program-back-and-forward-buttons-in-javafx-with-webview-and-webengine
 * stack Overflow. (2015). How to make a forward button to navigate through JavaFx browser and also a button that will open a list with all hyperlinks?. Retrived from https://stackoverflow.com/questions/32802248/how-to-make-a-forward-button-to-navigate-through-javafx-browser-and-also-a-butto
 * Oracle. (2014). JavaFX: Adding HTML Content to JavaFX Applications. Retrieved from https://docs.oracle.com/javase/8/javafx/embedded-browser-tutorial/history.htm
 */
