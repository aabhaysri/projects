public class Board {
    private String[][] b = new String[10][10];
    private int[] shipLength = { 5, 4, 3, 3, 2 };
    private String[] shipNames = { "A", "B", "D", "S", "P"};
    private boolean[] ver = new boolean[5];
    private boolean[] placed = new boolean[5];

    public Board() {
        for (int r = 0; r < b.length; r++) {
            for (int c = 0; c < b[0].length; c++) {
                b[r][c] = "~";
            }
        }
    }

    public String[][] getBoard(){
        return b;
    }

    public String[] getShipNames(){
        return shipNames;
    }

    public void printBoard() {
        String[] x = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
        System.out.println("   1  2  3  4  5  6  7  8  9  10");
        for (int r = 0; r < b.length; r++) {
            System.out.print(x[r]);
            for (int c = 0; c < b[0].length; c++) {
                System.out.print("  " + b[r][c]);
            }
            System.out.println("    ");
        }
    }

    public void makeShips() {
        int shipX;
        int shipY;
        int checkCount = 0;
        for (int i = 0; i < 5; i++) {
            double test = Math.random();
            if (test < .5) {
                ver[i] = true;
            }
        }

        for (int i = 0; i < 5; i++) {
            if (ver[i] == true) {
                while (placed[i] == false) {
                    shipX = getRandom(0, 9);
                    shipY = getRandom(0, 10 - shipLength[i]);
                    checkCount = 0;
                    
                    for (int count = shipY; count < shipLength[i]; count++) {
                        if (b[shipX][count].equals("~")) {
                            checkCount++;
                        }
                    }

                    if (checkCount != shipLength[i]) {
                        continue;
                    }

                    for (int count = shipY; count < shipLength[i]; count++) {
                        b[shipX][count] = shipNames[i];
                    }
                    placed[i] = true;
                }
            } else {
                while (placed[i] == false) {
                    shipY = getRandom(0, 9);
                    shipX = getRandom(0, 10 - shipLength[i]);
                    checkCount = 0;

                    for (int count = shipX; count < shipLength[i]; count++) {
                        if (b[count][shipY].equals("~")) {
                            checkCount++;
                        }
                    }

                    if (checkCount != shipLength[i]) {
                        continue;
                    }

                    for (int count = shipX; count < shipLength[i]; count++) {
                        b[count][shipY] = shipNames[i];
                    }
                    placed[i] = true;
                }
            }
        }
    }

    public int getRandom(int low, int high) {
        return ((int) (Math.random() * (high - low + 1) + low));
    }
}