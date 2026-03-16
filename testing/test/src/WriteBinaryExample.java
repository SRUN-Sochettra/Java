import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class WriteBinaryExample {
    public static void main(String[] args) {
        try (
                FileOutputStream fos = new FileOutputStream("binary.dat");
                DataOutputStream dos = new DataOutputStream(fos)
        ) {
            dos.writeInt(12345);
            dos.writeDouble(3.14);
            dos.writeBoolean(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}