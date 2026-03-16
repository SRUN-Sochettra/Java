import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class ReadBinaryExample {
    public static void main(String[] args) {
        try (
                FileInputStream fis = new FileInputStream("binary.dat");
                DataInputStream dis = new DataInputStream(fis)
        ) {
            int number = dis.readInt();
            double pi = dis.readDouble();
            boolean flag = dis.readBoolean();

            System.out.println(number);
            System.out.println(pi);
            System.out.println(flag);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}