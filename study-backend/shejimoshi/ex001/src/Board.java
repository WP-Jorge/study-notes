import java.io.IOException;

/**
 * Hello world!
 *
 */
public class Board {
    public static void main( String[] args ) {
        try {
            Draw factory = MyFactory.factory();
            factory.painting();
            factory.erase();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
