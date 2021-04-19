package Controller;

import readers.ExcelReaderImpl;
import readers.SQLiteReaderImpl;

public class TestMain {
	
	public static void main(String[] args) {
		for (String a : args) 
			System.out.println(a);
        
        AppSingleton app = AppSingleton.getInstance();
        AppSingleton.setFirstCommandLineArgument(args[0]);
        AppSingleton.setSecondCommandLineArgument(args[1]);
        
        if (args[0].endsWith(".xlsx")) {
			app.setReader(new ExcelReaderImpl(args[0]));
		}
		else if (args[0].endsWith(".db")) {
			app.setReader(new SQLiteReaderImpl());
		}
        
	}

}
