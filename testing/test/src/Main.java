import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        try (FileOutputStream fos = new FileOutputStream("data.bin")) {
            fos.write(65); // writes one byte: ASCII 'A'
            fos.write(66); // 'B'
            fos.write(67); // 'C'
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (FileInputStream fis = new FileInputStream("data.bin")) {
            int b;
            while ((b = fis.read()) != -1) {
                System.out.println(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] data = {72, 101, 108, 108, 111}; // "Hello"

        try (FileOutputStream fos = new FileOutputStream("hello.bin")) {
            fos.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (FileWriter writer = new FileWriter("message.txt")) {
            writer.write("Hello, Java File I/O!\n");
            writer.write("This is text written to a file.\n");
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (FileReader reader = new FileReader("message.txt")) {
            int ch;
            while ((ch = reader.read()) != -1) {
                System.out.print((char) ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (
                FileOutputStream fos = new FileOutputStream("utf8.txt");
                OutputStreamWriter writer = new OutputStreamWriter(fos, StandardCharsets.UTF_8)
        ) {
            writer.write("Hello\n");
            writer.write("សួស្តី\n");
            writer.write("こんにちは\n");
            writer.write("This was written using FileOutputStream\n");
            writer.write("This was also interpreted using OutputStreamWriter\n");
            writer.write("This was read using FileInputStream\n");
            writer.write("This was also interpreted using InputStreamWriter\n");
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (
                FileInputStream fis = new FileInputStream("utf8.txt");
                InputStreamReader reader = new InputStreamReader(fis, StandardCharsets.UTF_8)
        ) {
            int ch;
            while ((ch = reader.read()) != -1) {
                System.out.print((char) ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            Path path = Path.of("C:\\Users\\ROG\\IdeaProjects\\notes.txt");
            Files.writeString(path, "Hello from Java!\n This is written using Path, and also read using Path.", StandardCharsets.UTF_8);

            String content = Files.readString(path, StandardCharsets.UTF_8);
            System.out.println(content);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
