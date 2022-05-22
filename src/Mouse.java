import java.util.Map;

public class Mouse {
    private int posX, posY;
    private Maze mouseMap;
    private Arah arah;
    private static final Map<Arah, Integer> yMap = Map.of(
            Arah.KIRI, -1,
            Arah.KANAN, 1,
            Arah.ATAS, 0,
            Arah.BAWAH, 0
        );

    private static final Map<Arah, Integer> xMap = Map.of(
            Arah.KIRI, 0,
            Arah.KANAN, 0,
            Arah.ATAS, -1,
            Arah.BAWAH, 1
        );
    
    private static final Map<Arah, Arah> putarMap = Map.of(
        Arah.KIRI, Arah.KANAN,
        Arah.KANAN, Arah.KIRI,
        Arah.ATAS, Arah.BAWAH,
        Arah.BAWAH, Arah.ATAS
    );

    private static final Map<Arah, Arah> belokKiriMap = Map.of(
        Arah.KIRI, Arah.BAWAH,
        Arah.KANAN, Arah.ATAS,
        Arah.ATAS, Arah.KIRI,
        Arah.BAWAH, Arah.KANAN
    );

    private static final Map<Arah, Arah> belokKananMap = Map.of(
        Arah.KIRI, Arah.ATAS,
        Arah.KANAN, Arah.BAWAH,
        Arah.ATAS, Arah.KANAN,
        Arah.BAWAH, Arah.KIRI
    );

    public Mouse(){
        
    }

    public void start(Maze maze){
        this.posX = maze.getStartX();
        this.posY = maze.getStartY();
        this.mouseMap = maze;
        this.arah = maze.getStartArah();
        while (maze.getCell(this.posX, this.posY) != Block.FINISH) {
            System.out.println("posisi mouse: " + this.posX + " " + this.posY);
            System.out.println("arah mouse: " + this.arah);
            boolean b = false;
            if(this.arah == Arah.BAWAH){
                // Bawah lurus
                if(this.mouseMap.getCell(this.posX + 1, this.posY) == Block.PATH || this.mouseMap.getCell(this.posX + 1, this.posY) == Block.FINISH){
                    maju();
                } // Bawah belok kiri
                else if(this.mouseMap.getCell(this.posX, this.posY + 1) == Block.PATH || this.mouseMap.getCell(this.posX, this.posY + 1) == Block.FINISH){
                    belokKiri();
                } // Bawah belok kanan 
                else if(this.mouseMap.getCell(this.posX, this.posY - 1) == Block.PATH || this.mouseMap.getCell(this.posX, this.posY - 1) == Block.FINISH){
                    belokKanan();
                } else {
                    b = true;
                }
            } else if (this.arah == Arah.ATAS){
                if(this.mouseMap.getCell(this.posX - 1, this.posY) == Block.PATH || this.mouseMap.getCell(this.posX - 1, this.posY) == Block.FINISH){
                    maju();
                } else if (this.mouseMap.getCell(this.posX, this.posY - 1) == Block.PATH || this.mouseMap.getCell(this.posX, this.posY - 1) == Block.FINISH){
                    belokKiri();
                } else if (this.mouseMap.getCell(this.posX, this.posY + 1) == Block.PATH || this.mouseMap.getCell(this.posX, this.posY + 1) == Block.FINISH){
                    belokKanan();
                } else {
                    b = true;
                }
            } else if (this.arah == Arah.KANAN){
                if(this.mouseMap.getCell(this.posX, this.posY + 1) == Block.PATH || this.mouseMap.getCell(this.posX, this.posY + 1) == Block.FINISH){
                    maju();
                } else if (this.mouseMap.getCell(this.posX - 1, this.posY) == Block.PATH || this.mouseMap.getCell(this.posX - 1, this.posY) == Block.FINISH){
                    belokKiri();
                } else if (this.mouseMap.getCell(this.posX + 1, this.posY) == Block.PATH || this.mouseMap.getCell(this.posX + 1, this.posY) == Block.FINISH){
                    belokKanan();
                } else {
                    b = true;
                }
            } else if (this.arah == Arah.KIRI){
                if(this.mouseMap.getCell(this.posX, this.posY - 1) == Block.PATH || this.mouseMap.getCell(this.posX, this.posY - 1) == Block.FINISH){
                    maju();
                } else if (this.mouseMap.getCell(this.posX + 1, this.posY) == Block.PATH || this.mouseMap.getCell(this.posX + 1, this.posY) == Block.FINISH){
                    belokKiri();
                } else if (this.mouseMap.getCell(this.posX - 1, this.posY) == Block.PATH || this.mouseMap.getCell(this.posX - 1, this.posY) == Block.FINISH){
                    belokKanan();
                } else {
                    b = true;
                }
            } 
            // Backtrace
            if (b){
                if (this.arah == Arah.ATAS){
                    if (this.mouseMap.getCell(this.posX + 1, this.posY) == Block.VISITED){
                        putar();
                        backtrace();
                    } else if (this.mouseMap.getCell(this.posX - 1, this.posY) == Block.VISITED){
                        backtrace();
                    } else if (this.mouseMap.getCell(this.posX, this.posY + 1) == Block.VISITED){
                        belokKiri();
                    } else if (this.mouseMap.getCell(this.posX, this.posY - 1) == Block.VISITED){
                        belokKanan();
                    }
                } else if (this.arah == Arah.BAWAH){
                    if (this.mouseMap.getCell(this.posX - 1, this.posY) == Block.VISITED){
                        putar();
                        backtrace();
                    } else if (this.mouseMap.getCell(this.posX + 1, this.posY) == Block.VISITED){
                        backtrace();
                    } else if (this.mouseMap.getCell(this.posX, this.posY + 1) == Block.VISITED){
                        belokKiri();
                    } else if (this.mouseMap.getCell(this.posX, this.posY - 1) == Block.VISITED){
                        belokKanan();
                    }
                } else if (this.arah == Arah.KANAN){
                    if (this.mouseMap.getCell(this.posX, this.posY - 1) == Block.VISITED){
                        putar();
                        backtrace();
                    } else if (this.mouseMap.getCell(this.posX, this.posY + 1) == Block.VISITED){
                        backtrace();
                    } else if (this.mouseMap.getCell(this.posX - 1, this.posY) == Block.VISITED){
                        belokKiri();
                    } else if (this.mouseMap.getCell(this.posX + 1, this.posY) == Block.VISITED){
                        belokKanan();
                    }
                } else if (this.arah == Arah.KIRI){
                    if (this.mouseMap.getCell(this.posX, this.posY + 1) == Block.VISITED){
                        putar();
                        backtrace();
                    } else if (this.mouseMap.getCell(this.posX, this.posY - 1) == Block.VISITED){
                        backtrace();
                    } else if (this.mouseMap.getCell(this.posX + 1, this.posY) == Block.VISITED){
                        belokKiri();
                    } else if (this.mouseMap.getCell(this.posX - 1, this.posY) == Block.VISITED){
                        belokKanan();
                    }
                }
            }
            try {
                Thread.sleep(200);
                System.out.print("\033[H\033[2J");  
                System.out.flush();
            } catch (Exception e) {
            }
            this.mouseMap.print(this);
        }
        // for (int i = 0; i < mouseMap.getNRows(); i++) {
        //     for (int j = 0; j < mouseMap.getNCols(); j++) {
        //         if (i ==0 || j == 0 || i == mouseMap.getNRows() - 1 || j == mouseMap.getNCols() - 1){
        //             this.mouseMap.setCell(i, j, Block.WALL);
        //         } else if (i == this.posX && j == this.posY) {
        //             this.mouseMap.setCell(i, j, Block.VISITED);
        //         } else {
        //             this.mouseMap.setCell(i, j, Block.UNKNOWN);
        //         }
        //     }
            
        // }
    }
    public int getPosX() {
        return posX;
    }
    public Arah getArah() {
        return arah;
    }
    public int getPosY() {
        return posY;
    }

    public void maju() {
        this.mouseMap.setCell(this.posX, this.posY, Block.VISITED);
        this.posX += xMap.get(this.arah);
        this.posY += yMap.get(this.arah);
    }

    public void backtrace() {
        this.mouseMap.setCell(this.posX, this.posY, Block.DEAD);
        this.posX += xMap.get(this.arah);
        this.posY += yMap.get(this.arah);
    }

    public void putar() {
        this.arah = putarMap.get(this.arah);
    }

    public void belokKanan() {
        this.arah = belokKananMap.get(this.arah);
    }

    public void belokKiri() {
        this.arah = belokKiriMap.get(this.arah);
    }
}
