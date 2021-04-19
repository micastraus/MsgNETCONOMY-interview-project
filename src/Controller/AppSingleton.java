package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.math3.ode.FirstOrderConverter;

import readers.AbstractReader;
import readers.ExcelReaderImpl;
import readers.SQLiteReaderImpl;
import view.AppWindow;
import writers.AbstractWriter;
import writers.PNGImageWriterImpl;

public class AppSingleton {
	
	private static AppSingleton instance;
	private AppWindow window;
	private AbstractReader reader;
	
	private AbstractWriter writer;
	
	private static Connection connection;
	
	private static String firstCommandLineArgument; // input file path
	private static String secondCommandLineArgument; // folder gde output file treba da se postavi
	
	private AppSingleton() {
		window = new AppWindow();
	}
	
	public static AppSingleton getInstance() {
		if (instance == null) {
			instance = new AppSingleton();
		}
		return instance;
	}
	
	public static Connection getConnection() throws SQLException {
		if (connection == null) {
			try {
				Class.forName("org.sqlite.JDBC");
				connection = DriverManager.getConnection("jdbc:sqlite:"+firstCommandLineArgument);
				return connection;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}
	
	public AppWindow getWindow() {
		return window;
	}
	
	public void setReader(AbstractReader reader) {
		this.reader = reader;
	}
	
	public AbstractReader getReader() {
		return reader;
	}
	
	public void setWriter(AbstractWriter writer) {
		this.writer = writer;
	}
	
	public AbstractWriter getWriter() {
		return writer;
	}
	
	
	public static void setFirstCommandLineArgument(String firstCommandLineArgument) {
		AppSingleton.firstCommandLineArgument = firstCommandLineArgument;
	}
	
	public static String getFirstCommandLineArgument() {
		return firstCommandLineArgument;
	}
	
	public static void setSecondCommandLineArgument(String secondCommandLineArgument) {
		AppSingleton.secondCommandLineArgument = secondCommandLineArgument;
	}
	
	public static String getSecondCommandLineArgument() {
		return secondCommandLineArgument;
	}
	

}
