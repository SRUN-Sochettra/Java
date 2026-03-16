import java.io.FileInputStream;
import java.io.IOException;

public class ReadBinary {
    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("bytes.bin")) {
            int b;
            while ((b = fis.read()) != -1) {
                System.out.println(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}