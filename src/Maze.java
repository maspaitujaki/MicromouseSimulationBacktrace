import java.util.Map;

public class Maze {
    private int nRows, nCols;
    private Block[][] maze;
    private int startX, startY;
    private Arah startArah;
    private static final Map<Block, Character> pallete = Map.of(
        Block.WALL, '#',
        Block.DEAD, 'X',
        Block.PATH, ' ',
        Block.VISITED, 'o',
        Block.FINISH, 'F',
        Block.UNKNOWN, '?'
    );

    public Arah getStartArah() {
        return startArah;
    }
    public void setStartArah(Arah startArah) {
        this.startArah = startArah;
    }

    public Maze(int nRows, int nCols) {
        this.nRows = nRows;
        this.nCols = nCols;
        maze = new Block[nRows][nCols];
    }
    
    public int getStartX() {
        return startX;
    }
    public int getStartY() {
        return startY;
    }

    public void setStartX(int x) {
        this.startX = x;
    }

    public void setStartY(int y) {
        this.startY = y;
    }

    public void setCell(int r, int c, Block b) {
        maze[r][c] = b;
    }

    public Block getCell(int r, int c) {
        return maze[r][c];
    }
    
    public void setRow(int r, Block[] row) {
        for (int i = 0; i < nCols; i++) {
            maze[r][i] = row[i];
        }
    }

    public int getNRows() {
        return nRows;
    }

    public int getNCols() {
        return nCols;
    }

    public void print() {
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                printBlock(i, j);
            }
            System.out.println();
        }
    }

    public void print(Mouse mouse) {
        Map <Arah, Character> arahPallete = Map.of(
            Arah.ATAS, '^',
            Arah.BAWAH, 'v',
            Arah.KIRI, '<',
            Arah.KANAN, '>'
        );
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                if(i == mouse.getPosX() && j == mouse.getPosY()) {
                    System.out.print(arahPallete.get(mouse.getArah()));
                } else {
                    printBlock(i, j);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printBlock(int i, int j){
        System.out.print(pallete.get(maze[i][j]));
    }
}
