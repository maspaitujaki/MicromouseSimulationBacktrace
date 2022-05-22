import java.io.File;
import java.util.Map;
import java.util.Scanner;

public class MazeReader {
    private static final Map<Integer, Block> palette = Map.of(
        1, Block.WALL,
        2, Block.FINISH,
        0, Block.PATH
    );

    public static Maze readMaze(String fileName) throws Exception {
        Scanner in = new Scanner(new File(fileName));
        int nRows = in.nextInt();
        int nCols = in.nextInt();
        Maze maze = new Maze(nRows, nCols);
        maze.setStartX(in.nextInt());
        maze.setStartY(in.nextInt());
        for (int i = 0; i < nRows; i++) {
            Block[] row = new Block[nCols];
            for (int j = 0; j < nCols; j++) {
                row[j] = palette.get(in.nextInt());
            }
            maze.setRow(i, row);
        }
        return maze;
    }
}
