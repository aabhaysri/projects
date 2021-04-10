import java.util.*;

public class BBoard extends Board{
    private ArrayList<String> inputs = new ArrayList<String>();
    private boolean win = false;
    private boolean a = false;
    private boolean b = false;
    private boolean d = false;
    private boolean s = false;
    private boolean p = false;
    private int totalCount = 0;


    public BBoard(){
        super();
    }

    public void debugPrint(){
        this.printBoard();
    }

    public String getUserInput(String input){
        Scanner in = new Scanner(System.in);
        System.out.println(input);
        String x = in.nextLine();
        return x;
    }

    public int[] cordtoString(Board bb){
        String x = getUserInput("Enter a coordinate!");
        int[] output = new int[2];
        ArrayList<String> letters = new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J"));
        ArrayList<Integer> nums = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        if(x.length() <= 1){
            System.out.println("Error, please enter a valid coordinate!");
            return cordtoString(bb);
        }
        String x1 = x.substring(0, 1);
        String x2;
        if(x.substring(1).equals("10")){
            x2 = "10";
        } else{
            x2 = x.substring(1, 2);
        }
        int count = 0;
        if(letters.indexOf(x1) > -1){
            count++;
        }
        for(int i = 0; i < nums.size(); i++){
            if(x2.equals(Integer.toString(nums.get(i)))){
                count++;
            }
        }
        if(inputs.indexOf(x) == -1){
            count++;
        }
        if(count == 3){
            output[0] = letters.indexOf(x1);
            output[1] = nums.indexOf(Integer.parseInt(x2));
            inputs.add(x);
            if(super.getBoard()[output[0]][output[1]].equals("~")){
                bb.getBoard()[output[0]][output[1]] = "M";
                super.getBoard()[output[0]][output[1]] = "M";
            } else{
                bb.getBoard()[output[0]][output[1]] = "H";
                super   .getBoard()[output[0]][output[1]] = "M";
                totalCount++;
            }
            return output;
        } else{
            System.out.println("Error, please enter a valid coordinate!");
            return cordtoString(bb);
        }
    }

    public void checkHit(){
        int checkA = 0;
        int checkB = 0;
        int checkD = 0;
        int checkS = 0;
        int checkP = 0;
        for(int r = 0; r < super.getBoard().length; r++){
            for(int c = 0; c < super.getBoard()[0].length; c++){
                if(super.getBoard()[r][c].equals("A")){
                    checkA++;
                } else if(super.getBoard()[r][c].equals("B")){
                    checkB++;
                } else if (super.getBoard()[r][c].equals("D")){
                    checkD++;
                } else if (super.getBoard()[r][c].equals("S")){
                    checkS++;
                } else if (super.getBoard()[r][c].equals("P")){
                    checkP++;
                }
            }
        }
        if(totalCount > 0){
            if(checkA == 0){
                a = true;
            } if(checkB == 0){
                b = true;
            } if (checkD == 0){
                d = true;
            } if (checkS == 0){
                s = true;
            } if (checkP == 0){
                p = true;
            }
    }
        System.out.println("Sunken: ");
        System.out.println("Aircraft Carrier " + a);
        System.out.println("Battleship " + b);
        System.out.println("Destroyer " + d);
        System.out.println("Submarine " + s);
        System.out.println("Patrol Boat " + p);
        if(a == true && b == true && d == true && s == true && p == true){
            win = true;
        }
        System.out.println("Number of Tries (Lose at 50): " + inputs.size());
        if(win == true){
            System.out.println("Congradulations! You've won!");
        } else if (inputs.size() == 50){
            System.out.println("Game over. You lose!");
            win = true;
        }
    }

    public boolean getWin(){
        return win;
    }
}