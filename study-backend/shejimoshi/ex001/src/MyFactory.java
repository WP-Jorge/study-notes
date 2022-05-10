import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyFactory {
	
	public static Draw factory() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
		
		Properties properties = new Properties();
		File file = new File("D:\\developTools\\allProject\\sehjimoshi\\ex01\\src\\main\\resources\\config.properties");
		InputStream in = new FileInputStream(file);
		properties.load(in);
		String entity = properties.getProperty("entity");
		String aPackage = properties.getProperty("package");
		entity = aPackage + "." + entity;
		
		Draw aClass = (Draw) Class.forName(entity).newInstance();
		
		return  aClass;
		// return null;
	}
	
}
