import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress game1 = new GameProgress(10, 20, 1, 100);
        GameProgress game2 = new GameProgress(11, 21, 2, 200);
        GameProgress game3 = new GameProgress(12, 22, 3, 300);
        saveGame("D://Games2//savegames//game1.dat", game1);
        saveGame("D://Games2//savegames//game2.dat", game2);
        saveGame("D://Games2//savegames//game3.dat", game3);
        List<String> listObjects = new ArrayList<>();
        listObjects.add("D://Games2//savegames//game1.dat");
        listObjects.add("D://Games2//savegames//game2.dat");
        listObjects.add("D://Games2//savegames//game3.dat");
        zipFiles("D://Games2//savegames//zip.zip", listObjects);

    }

    private static void zipFiles(String path, List<String> listObjects) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path))) {
            for (String object : listObjects) {
                try (FileInputStream fis = new FileInputStream(object)) {
                    ZipEntry entry = new ZipEntry(object);
                    zout.putNextEntry(entry);
                    while (fis.available() > 0) {
                        zout.write(fis.read());
                    }
                    zout.closeEntry();

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                File gameDat = new File(object);
                if (gameDat.delete()) System.out.println("Файл " + object + " удален");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void saveGame(String path, GameProgress game) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
