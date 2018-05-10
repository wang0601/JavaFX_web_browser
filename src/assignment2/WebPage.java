/**
 *File name: WebPage.java 
 *Author: Oroi S. Wang, 040886757
 *Course: CST8284 – OOP
 *Assignment: 2
 *Date: Jan 12, 2018
 *Professor: RAYMOND PETERKIN
 *Purpose: To realize web page functionality
 */


package assignment2;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * Class WebPage deals with the web page loaded to the browser
 * @author Oroi S. Wang
 * @version 1.0
 * @see assignment2
 * @since 1.8.0_version
 */

public class WebPage {
	
	/**
	 * Create Webview object to access its methods
	 */
	private WebView webview = new WebView();
	/**
	 * Create WebEngine object to access its methods
	 */
	private WebEngine engine;
	
	/**
	 * Add listener to the WebEngine to detect its state
	 * @param stage: Main stage of the application
	 * @return WebEngine itself
	 */
	public WebEngine createWebEngine(Stage stage) {
		
		WebView wv = getWebView();
		engine = wv.getEngine();
		
		engine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
			@Override
			public void changed(ObservableValue<? extends State> ov, State oldState, State newState) {
				if (newState == Worker.State.SUCCEEDED) {
					stage.setTitle(engine.getLocation());
				}
			}
		});
		
		return engine;
	}
	
	/**
	 * Get the WebView object
	 * @return WebView
	 */
	public WebView getWebView() {
		return webview;
	}
	
	/**
	 * Get the WebEngine object
	 * @return WebEngine
	 */
	public WebEngine getWebEngine() {
		return engine;
	}
}
