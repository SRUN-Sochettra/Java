import java.io.FileWriter;
import java.io.IOException;

public class WriteText {
    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("test.txt")) {
            writer.write("Hello file!\n");
            writer.write("Second line");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}