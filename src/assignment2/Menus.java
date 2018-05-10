/**
 *File name: Menus.java 
 *Author: Oroi S. Wang, 040886757
 *Course: CST8284 – OOP
 *Assignment: 2
 *Date: Jan 12, 2018
 *Professor: RAYMOND PETERKIN
 *Purpose: Create menu for web browser
 */


package assignment2;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.web.WebView;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.control.SeparatorMenuItem;
import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseButton;
import javafx.scene.web.WebHistory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyCode;
import javafx.event.EventHandler;
import javafx.scene.web.WebEvent;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Class Menus creates the menu bar, menu items and related functions for the web browser
 * @author Oroi S. Wang
 * @version 1.0
 * @see assignment2
 * @since 1.8.0_version
 */

public class Menus {
	/**
	 * Create menu bar to hold menus
	 */
	private static MenuBar menuBar= new MenuBar();
	
	/**
	 * Create the File menu
	 */
	private static Menu mnuFile= new Menu("_File");
	/**
	 * Create the Settings menu
	 */
	private static Menu mnuSettings= new Menu("_Settings");
	/**
	 * Create the Bookmarks menu
	 */
	private static Menu mnuBookmarks= new Menu("_Bookmarks");
	/**
	 * Create the Help menu
	 */
	private static Menu mnuHelp= new Menu("_Help");
	
	/**
	 * The menu item to refresh the web page
	 */
	private static MenuItem mnuItmRefresh= new MenuItem("Refresh");
	/**
	 * The menu item to exit browser
	 */
	private static MenuItem mnuItmExit=new MenuItem("Exit");
	/**
	 * The menu item to toggle address bar
	 */
	private static MenuItem mnuItmToggleAddressBar= new MenuItem("Toggle Address Bar");
	/**
	 * The menu item to change default page
	 */
	private static MenuItem mnuItmStartup= new MenuItem("Change Start-up Page");
	/**
	 * The menu item to show history pages
	 */
	private static MenuItem mnuItmHistory= new MenuItem("History");
	/**
	 * The menu item to show xml code
	 */
	private static MenuItem mnuItmDisplayCode= new MenuItem("Display Code");
	/**
	 * The menu item to add current page to bookmark
	 */
	private static MenuItem mnuItmAddBookmark= new MenuItem("Add Bookmark");
	/**
	 * The menu item to display author's info
	 */
	private static MenuItem mnuItmAbout= new MenuItem("About");
	
	/**
	 * Sections of the web page
	 */
	private static VBox topPanel = new VBox();
	private static VBox bottomPanel = new VBox();
	private static VBox rightPanel = new VBox();
	private static HBox addressBar;
	
	/**
	 * Fields to help toggle sections
	 */
	private static boolean showAddress = true;
	private static boolean showHistory = true;
	private static boolean showXMLCode = true;
	
	/**
	 * Fields to help otther methods
	 */
	private static ArrayList<String> bookmarks = new ArrayList<>();
	private static TextField xmlCode = new TextField();

	/**
	 * Get the menu bar
	 * @param wv WebView
	 * @return menu bar
	 */
	public static MenuBar getMenuBar(WebView wv) {
		menuBar.getMenus().addAll(getMnuFile(wv), getMnuSettings(wv), getMnuBookmarks(wv), getMnuHelp());
		return menuBar;
	}
	
	/**
	 * Get the File Menu
	 * @param wv webView
	 * @return File Menu
	 */
	public static Menu getMnuFile(WebView wv) {
		mnuFile.getItems().addAll(getMnuItmRefresh(wv), getMnuItmExit());
		mnuFile.setAccelerator(KeyCombination.keyCombination("Ctrl+F"));
		return mnuFile;
	}
	
	/**
	 * Get the Settings Menu
	 * @param wv webView
	 * @return Settings Menu
	 */
	public static Menu getMnuSettings(WebView wv) {
		mnuSettings.getItems().addAll(getMnuItmToggleAddressBar(), getMnuItmStartup(wv), getMnuItmHistory(wv), getMnuItmDisplayCode(wv));
		mnuSettings.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
		return mnuSettings;
	}
	
	/**
	 * Get the Bookmarks Menu
	 * @param wv webView
	 * @return Bookmarks Menu
	 */
	public static Menu getMnuBookmarks(WebView wv) {		
		mnuBookmarks.getItems().add(getMnuItmAddBookmark(mnuBookmarks, wv));
		mnuBookmarks.setAccelerator(KeyCombination.keyCombination("Ctrl+B"));
		loadBookmarks(mnuBookmarks, wv);
		return mnuBookmarks;
	}
	
	/**
	 * Get the Help Menu
	 * @return Help Menu
	 */
	public static Menu getMnuHelp() {
		mnuHelp.getItems().add(getMnuItmAbout());
		mnuHelp.setAccelerator(KeyCombination.keyCombination("Ctrl+H"));
		return mnuHelp;
	}
	
	/**
	 * Get the Refresh Menu item
	 * @param wv webView
	 * @return Refresh Menu item
	 */
	public static MenuItem getMnuItmRefresh(WebView wv) {
		mnuItmRefresh.setOnAction((ActionEvent e)->wv.getEngine().reload());
		mnuItmRefresh.setAccelerator(KeyCombination.keyCombination("Ctrl+R"));
		return mnuItmRefresh;
	}
	
	/**
	 * Get the Exit Menu item
	 * @return Exit Menu item
	 */
	public static MenuItem getMnuItmExit() {
		mnuItmExit.setOnAction((ActionEvent e)->Platform.exit());
		mnuItmExit.setAccelerator(KeyCombination.keyCombination("Ctrl+E"));
		return mnuItmExit;
	}
	
	/**
	 * Get the Toggle address bar Menu item
	 * @return Toggle address bar Menu item
	 */
	public static MenuItem getMnuItmToggleAddressBar() {		
		mnuItmToggleAddressBar.setOnAction((ActionEvent e)->{
			if(showAddress) {
				topPanel.getChildren().add(addressBar);
			}
			else {
				topPanel.getChildren().remove(addressBar);
			}
			showAddress=!(showAddress);
		});
		mnuItmToggleAddressBar.setAccelerator(KeyCombination.keyCombination("Ctrl+T"));

		return mnuItmToggleAddressBar;
	}
	
	/**
	 * Get the start up Menu item
	 * @param wv webView
	 * @return Start up Menu item
	 */
	public static MenuItem getMnuItmStartup(WebView wv) {
		mnuItmStartup.setOnAction((ActionEvent e)->{
			File file= new File("default.web");
			ArrayList<String> ar= new ArrayList<>();
			
			ar.add(getURL(wv));
			FileUtils.saveFileContents(file, ar);
		});
		mnuItmStartup.setAccelerator(KeyCombination.keyCombination("Ctrl+P"));
		
		return mnuItmStartup;
	}
	
	/**
	 * Get the History Menu item
	 * @param wv webView
	 * @return History Menu item
	 */
	public static MenuItem getMnuItmHistory(WebView wv) {

		HBox hbox = new HBox();

		Button next = new Button("Next");
		Button previous = new Button("Previous");

		WebHistory history = wv.getEngine().getHistory();
		ObservableList<String> list = FXCollections.observableArrayList();
		ListView<String> historyList = new ListView<>();

		next.setOnAction((ActionEvent e)-> { 
			if(history.getEntries().size() >1 && history.getCurrentIndex() < history.getEntries().size()-1)
				history.go(1); 
			else
				history.go(0);
		});

		previous.setOnAction((ActionEvent e)-> { 
			if(history.getEntries().size() >1 && history.getCurrentIndex() > 0)
				history.go(-1); 
			else
				history.go(0);
		});

		hbox.getChildren().addAll( previous, next);
		historyList.setItems(list);
		
		
		mnuItmHistory.setOnAction((ActionEvent e)->{
			if(showHistory) {
				rightPanel.getChildren().addAll( historyList, hbox);;
			}
			else {
				rightPanel.getChildren().removeAll(historyList, hbox);
			}
			showHistory= !(showHistory);
			
			wv.getEngine().load(history.getEntries().get(history.getEntries().size()-1).getUrl());
			list.clear();
			for(int i =0; i<history.getEntries().size(); i++) {
				list.add(history.getEntries().get(i).getTitle());
			}
		});
		
		mnuItmHistory.setAccelerator(KeyCombination.keyCombination("Ctrl+H"));
		return mnuItmHistory;
	}
	
	/**
	 * Get the DisplayCode Menu item
	 * @param wv webView
	 * @return DisplayCode Menu item
	 */
	public static MenuItem getMnuItmDisplayCode(WebView wv) {
		mnuItmDisplayCode.setOnAction((ActionEvent e)->{
			
			if(showXMLCode) {
				bottomPanel.getChildren().add(xmlCode);
			}
			else {
				bottomPanel.getChildren().remove(xmlCode);
			}
			showXMLCode= !(showXMLCode);
		});
		mnuItmDisplayCode.setAccelerator(KeyCombination.keyCombination("Ctrl+D"));
		return mnuItmDisplayCode;
	}
	
	
	/**
	 * Get the add bookmark Menu item
	 * @param wv webView
	 * @param mnu Menu
	 * @return add bookmark Menu item
	 */
	public static MenuItem getMnuItmAddBookmark(Menu mnu, WebView wv) {
		mnuItmAddBookmark.setOnAction((ActionEvent e) -> {
			updateBookmarks(mnuBookmarks, getURL(wv), wv);
			bookmarks.add(getURL(wv));
		});
		mnuItmAddBookmark.setAccelerator(KeyCombination.keyCombination("Ctrl+M"));
		return mnuItmAddBookmark;
	}
	
	/**
	 * Get the about Menu item
	 * @return about Menu item
	 */
	public static MenuItem getMnuItmAbout() {
		mnuItmAbout.setOnAction((ActionEvent e)->{
			Alert alert= new Alert(AlertType.INFORMATION);
			alert.setTitle("Student Info");
			alert.setHeaderText(null);
			alert.setContentText("Oroi S. Wang, 040886757");
			alert.showAndWait();
			
		});
		mnuItmAbout.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
		return mnuItmAbout;
	}
	
	/**
	 * Get the address bar
	 * @param wv webView
	 * @return address bar as HBox
	 */
	public static HBox getAddressBar(WebView wv) {
		Label lbl=new Label("Enter Address");
		TextField txtfld=new TextField(getURL(wv));
		txtfld.setMaxWidth(Double.MAX_VALUE);
		Button btn= new Button("Go");
		
		btn.setOnAction((ActionEvent e)-> wv.getEngine().load(txtfld.getText()));
		
		txtfld.setOnKeyPressed(e->{
			if(e.getCode()==KeyCode.ENTER)
				wv.getEngine().load(txtfld.getText());
		});		
		
		HBox hbx = new HBox();	
		hbx.getChildren().addAll(lbl, txtfld, btn);
		HBox.setHgrow(txtfld, Priority.ALWAYS);
		
		wv.getEngine().setOnStatusChanged(
				new EventHandler<WebEvent<String>>() {
					public void handle(WebEvent<String> we) {
						txtfld.setText(getURL(wv));						
					}
				});
		
		return hbx;
	}
	
	/**
	 * Get the top panel of page
	 * @param wv webView
	 * @return top panel as VBox
	 */
	public static VBox getTopPanel(WebView wv) {
		addressBar=getAddressBar(wv);
		
		MenuBar menuBar=getMenuBar(wv);
		topPanel.getChildren().add(menuBar);
		return topPanel;
		
	}
	
	/**
	 * Get the bottom panel of page
	 * @param wv webView
	 * @return bottom panel as VBox
	 */
	public static VBox getBottomPanel(WebView wv) {
		getXMLCode(wv);
		return bottomPanel;
	}
	
	/**
	 * Get the right panel of page
	 * @param wv webView
	 * @return right panel as VBox
	 */
	public static VBox getRightPanel(WebView wv) {
		
		return rightPanel;
	}
	
	
	/**
	 * Get current URL
	 * @param wv WebView
	 * @return URL as String
	 */
	public static String getURL(WebView wv) {
		return wv.getEngine().getLocation();		
	}
	
	/**
	 * Get bookmarks
	 * @return bookmarks as ArrayList of String
	 */
	public static ArrayList<String> getBookmarks() {
		
		return bookmarks;
	}
	

	/**
	 * Load bookmarks
	 * @param mnu: Menu
	 * @param wv WebView
	 */
	public static void loadBookmarks(Menu mnu, WebView wv) {
		
		if (FileUtils.fileExists("bookmarks.web")){
			
			File f= new File("bookmarks.web");
			
			bookmarks=FileUtils.getFileContentsAsArrayList(f);
			for (String url: bookmarks)
				updateBookmarks(mnu, url, wv);
		}
		
	}
	
	/**
	 * Update bookmarks
	 * @param mnu Menu
	 * @param URL URL as String
	 * @param wv WebView
	 */
	public static void updateBookmarks(Menu mnu, String URL, WebView wv){
		Label lbl= new Label(URL);
		
		CustomMenuItem mnuItm = new CustomMenuItem();
		mnuItm.setContent(lbl);
		mnuItm.setText(URL);
		ContextMenu cMnu = new ContextMenu();
			
		MenuItem remove= new MenuItem("Remove from bookmarks");
		
		cMnu.getItems().add(remove);
				
				
		EventHandler<MouseEvent> hdlr= new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				if(me.getButton()==MouseButton.PRIMARY)
					wv.getEngine().load(URL);
				if(me.getButton()==MouseButton.SECONDARY) {
					remove.setOnAction((ActionEvent e)->{
						mnu.getItems().remove(mnuItm);
						bookmarks.remove(mnuItm.getText());
						});
					mnuItm.setHideOnClick(false);
					cMnu.show(mnuItm.getContent(), me.getSceneX(), me.getScreenY());
					
				}			
			}
		};
		
		mnuItm.getContent().addEventHandler(MouseEvent.MOUSE_CLICKED, hdlr);
		
		if (mnu.getItems().size() == 1)
			mnu.getItems().add(new SeparatorMenuItem());
		mnu.getItems().add(mnuItm); 	

	}
	

	
	/**
	 * Get the xml code of page
	 * @param wv WebView
	 */
	public static void getXMLCode(WebView wv) {
		try {
            TransformerFactory transformerFactory = TransformerFactory
                .newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StringWriter stringWriter = new StringWriter();
            transformer.transform(new DOMSource(wv.getEngine().getDocument()),
                new StreamResult(stringWriter));
            String xml = stringWriter.getBuffer().toString();
            xmlCode.setText(xml);
                
          } catch (Exception e) {
            e.printStackTrace();
          }
			      
	}
			
}
