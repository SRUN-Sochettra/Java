import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class DeserializeExample {
    public static void main(String[] args) {
        try (
                FileInputStream fis = new FileInputStream("student.ser");
                ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            Student student = (Student) ois.readObject();
            System.out.println(student);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}