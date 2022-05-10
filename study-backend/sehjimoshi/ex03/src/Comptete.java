import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Comptete {
	private static volatile Comptete comptete = null;
	private Comptete() {}
	public static Comptete getInstance() {
		if (comptete == null) {
			synchronized (Comptete.class) {
				if (comptete == null) {
					comptete = new Comptete();
				}
			}
		}
		return comptete;
	}
	public String getCompete() {
		StringBuffer buffer = new StringBuffer();
		try {
			BufferedReader bufferedReader = new BufferedReader(
					new FileReader("D:\\developTools\\allProject\\sehjimoshi\\ex03\\src\\resources\\compete.txt"));
			String str;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
				buffer.append("\r\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}
}
