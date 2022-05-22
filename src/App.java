public class App {
    public static void main(String[] args) throws Exception {
        Maze m1;
        try {
            m1 = MazeReader.readMaze("maze2.txt");
            System.out.println("done reading maze1.txt");
            m1.setStartArah(Arah.BAWAH);
            m1.print();
            // Runner runner = new Runner();
            // runner.run(m1);
            Mouse mouse = new Mouse();
            mouse.start(m1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

       
    }
}
