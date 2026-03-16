import java.nio.file.Path;

public class PathExample {
    public static void main(String[] args) {
        Path path = Path.of("C:\\Users\\ROG\\IdeaProjects\\test.txt");

        System.out.println("File name: " + path.getFileName());
        System.out.println("Parent: " + path.getParent());
        System.out.println("Absolute? " + path.isAbsolute());
    }
}