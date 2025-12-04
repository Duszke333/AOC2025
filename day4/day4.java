import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class day4 {
    public static void main(String[] args) {
        task1(args[0]);
        task2(args[0]);
    } 

    static final int[] dirX = {-1, 0, 1, 1, 1, 0, -1, -1};
    static final int[] dirY = {-1, -1, -1, 0, 1, 1, 1, 0};

    private static void task1(String filename) {
        List<char[]> tmp = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new File(filename))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                tmp.add(line.toCharArray());
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        char[][] map = tmp.toArray(new char[0][]);
        int height = map.length;
        int width = map[0].length;
        int total = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int neighboursCount = 0;
                if (map[y][x] != '@') continue;
                for (int dir = 0; dir < 8; dir++) {
                    int nx = x + dirX[dir];
                    int ny = y + dirY[dir];
                    if (nx >= 0 && nx < width && ny >= 0 && ny < height && map[ny][nx] == '@') neighboursCount++;
                    if (neighboursCount >= 4) break;
                }
                if (neighboursCount < 4) total++;
            }
        }
        System.out.println(total);
    }

    private static void task2(String filename) {
        List<char[]> tmp = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new File(filename))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                tmp.add(line.toCharArray());
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        char[][] map = tmp.toArray(new char[0][]);
        int height = map.length;
        int width = map[0].length;
        int total = 0;
        int rotation;
        do {
            rotation = 0;
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int neighboursCount = 0;
                    if (map[y][x] != '@') continue;
                    for (int dir = 0; dir < 8; dir++) {
                        int nx = x + dirX[dir];
                        int ny = y + dirY[dir];
                        if (nx >= 0 && nx < width && ny >= 0 && ny < height && map[ny][nx] == '@') neighboursCount++;
                        if (neighboursCount >= 4) break;
                    }
                    if (neighboursCount < 4) {
                        rotation++;
                        map[y][x] = '.';
                    }
                }
            }
            total += rotation;
        } while(rotation != 0);
        System.out.println(total);
    }
}