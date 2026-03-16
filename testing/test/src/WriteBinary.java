import java.io.FileOutputStream;
import java.io.IOException;

public class WriteBinary {
    public static void main(String[] args) {
        try (FileOutputStream fos = new FileOutputStream("bytes.bin")) {
            byte[] data = {10, 20, 30, 40};
            fos.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}