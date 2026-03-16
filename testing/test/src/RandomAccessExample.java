import java.io.RandomAccessFile;
import java.io.IOException;

public class RandomAccessExample {
    public static void main(String[] args) {
        try (RandomAccessFile raf = new RandomAccessFile("data.dat", "rw")) {
            raf.writeInt(100);   // bytes 0-3
            raf.writeInt(200);   // bytes 4-7
            raf.writeInt(300);   // bytes 8-11

            raf.seek(4);         // move to second int
            int value = raf.readInt();
            System.out.println(value); // 200

            raf.seek(4);         // go back again
            raf.writeInt(999);   // overwrite second int
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}