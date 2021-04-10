public class Main{
    public static void main(String[] args){
        BBoard board = new BBoard();
        BBoard shown = new BBoard();
        board.makeShips();
        while(board.getWin() == false){
            board.debugPrint();
            //only shows location of the board, H/M shows on the real board.
            shown.printBoard();
            board.cordtoString(shown);
            board.checkHit();
        }
    }
}