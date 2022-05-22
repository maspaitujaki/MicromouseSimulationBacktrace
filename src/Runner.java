

public class Runner {
    public static final int delay = 1000;
    public Runner(){
        
    }

    public boolean run(Maze maze){
        return this.run(maze, maze.getStartX(), maze.getStartY());
    }

    private boolean run(Maze maze, int x, int y) {
        if (maze.getCell(x, y) == Block.FINISH){
            return true;
        } else if (maze.getCell(x, y) == Block.PATH){
            maze.setCell(x, y, Block.VISITED);
            System.out.print("\033[H\033[2J");  
            System.out.flush();
            maze.print();
            try {
                Thread.sleep(250);
            } catch (Exception e) {
            }
            if (x < maze.getNRows() - 1){
                //KEBAWAH
                // run(maze, x + 1, y);
                if (run(maze, x + 1, y)){
                    return true;
                }
            }
            if (x > 0){
                //KEATAS
                // run(maze, x - 1, y);
                if (run(maze, x - 1, y)){
                    return true;
                }
            }
            if (y < maze.getNCols() - 1){
                //Ke Kanan
                // run(maze, x, y + 1);
                if (run(maze, x, y + 1)){
                    return true;
                }
            }
            if (y > 0){
                //Ke Kiri
                // run(maze, x, y - 1);
                if (run(maze, x, y - 1)){
                    return true;
                }
            }
            // Backtrack
            maze.setCell(x, y, Block.DEAD);
            
            try {
                Thread.sleep(delay);
            } catch (Exception e) {
            }
            System.out.print("\033[H\033[2J");  
            System.out.flush();
            maze.print();
            System.out.println("Backtrack");
        }
        return false;
    }
}
