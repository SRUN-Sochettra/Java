import java.io.FileReader;
import java.io.IOException;

public class ReadText {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("test.txt")) {
            int ch;
            while ((ch = reader.read()) != -1) {
                System.out.print((char) ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}