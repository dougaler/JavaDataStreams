import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class FileMaster {
    static String ogFile = "";
    static Path Filename = null;

    static Path target = new File(System.getProperty("user.dir")).toPath();
    static Scanner inFile;
    static String resultSearch = "";

    public static String getFile() {
        JFileChooser chooser = new JFileChooser();
        String oldline;

        target = target.resolve("src");
        chooser.setCurrentDirectory(target.toFile());
        try {
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                target = chooser.getSelectedFile().toPath();

                inFile = new Scanner(target);
                int i = 5;
                Filename = target.getName(i);
                while (inFile.hasNextLine()) {
                    oldline = inFile.nextLine();
                    ogFile = ogFile + (oldline + "\n");
                }


            } else {
                System.out.println("You did not choose a file. Quitting.");
                System.exit(0);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found Error");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException Error");
            e.printStackTrace();
        }
        return ogFile;
    }
    public static String getFileName() {
        String filePath;
        filePath = target.toFile().getAbsolutePath();
        return filePath;
    }
    public static String getSearchedString(String searchedString)
    {
        try (Stream<String> lines = Files.lines(Paths.get(getFileName())))
        {
            List<Object> examples = lines
                    .filter(w -> !w.endsWith("."))
                    .filter(w -> w.toLowerCase().contains(searchedString))
                    .collect(Collectors.toList());
            for(int i = 0; i<examples.size();i++) {
                String stringLines = (String) examples.get(i);
                resultSearch = resultSearch + stringLines +"\n" +"\n";
            }
            return resultSearch;


        }
        catch (IOException e) {
            e.printStackTrace();
            return "IOException Error";
        }

    }
    public static void getFileClose()
    {
        inFile.close();
    }
}
