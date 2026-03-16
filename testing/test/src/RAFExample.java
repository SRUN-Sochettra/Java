import java.io.RandomAccessFile;
import java.io.IOException;

public class RAFExample {
    public static void main(String[] args) {
        try (RandomAccessFile raf = new RandomAccessFile("numbers.dat", "rw")) {
            raf.writeInt(111);
            raf.writeInt(222);
            raf.writeInt(333);

            raf.seek(4);
            System.out.println(raf.readInt()); // 222
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
