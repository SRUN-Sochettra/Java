import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class SerializeExample {
    public static void main(String[] args) {
        Student student = new Student("Dara", 20);

        try (
                FileOutputStream fos = new FileOutputStream("student.ser");
                ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(student);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}