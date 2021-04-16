import java.util.*;

public class tttAiFirst{

    static ArrayList<Integer> playerPos = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPos = new ArrayList<Integer>();
    static int strat = 0;
    static int boardState = -1;

    public static void main(String[] args){

        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                              {'-', '+', '-', '+', '-'},
                              {' ', '|', ' ', '|', ' '},
                              {'-', '+', '-', '+', '-'},
                              {' ', '|', ' ', '|', ' '}};
        
        Random rand = new Random();
        Scanner scan = new Scanner(System.in);
        int pos= -1;
        int cPos = 0; 
        
        String result = "";
        
        strat = rand.nextInt(2)+1;
        if (strat == 2){
            cPos = 5;
        } else {
            cPos = strat;
        }
        placePiece(gameBoard,cPos,"cpu");

        printGB(gameBoard); 
        while(true) {
            scan = new Scanner(System.in);
            System.out.println("Enter your placement (1-9);");
            pos = scan.nextInt();
            while(playerPos.contains(pos) || cpuPos.contains(pos)){
                System.out.println("Position taken! Enter a correct Position");
                pos = scan.nextInt();
            }
            placePiece(gameBoard, pos, "player");
            result = checkWinner();
            if(result.length() > 0 ){
                printGB(gameBoard);
                System.out.println(result);
                break;
            }
            
            cPos = rand.nextInt(9) + 1;
            while(playerPos.contains(cPos) || cpuPos.contains(cPos)){
                cPos = rand.nextInt(9) + 1;
            }
            placePiece(gameBoard, cPos, "cpu");
            printGB(gameBoard);
            result = checkWinner();
            if(result.length() > 0 ){
                System.out.println(result);
                break;
            }
        }
        scan.close();

    }

    public static void printGB(char[] [] gb){
        for(char[] row: gb) {
            for(char c: row){
                System.out.print(c);
            }
            System.out.println();
        } 
    }

    public static void placePiece(char[][] gameBoard, int pos, String user){

        char symbol = ' ';
        if(user.equals("player")){
            symbol = 'X';
            playerPos.add(pos);
        } else if (user.equals("cpu")){
            symbol = 'O';
            cpuPos.add(pos);
        }


        switch(pos) {
            case 1:
                gameBoard[0][0]=symbol;
            break;
            case 2:
                gameBoard[0][2]=symbol;
            break;
            case 3:
                gameBoard[0][4]=symbol;
            break;
            case 4:
                gameBoard[2][0]=symbol;
            break;
            case 5:
                gameBoard[2][2]=symbol;
            break;
            case 6:
                gameBoard[2][4]=symbol;
            break;
            case 7:
                gameBoard[4][0]=symbol;
            break;
            case 8:
                gameBoard[4][2]=symbol;
            break;
            case 9:
                gameBoard[4][4]=symbol;
            break;
        }
    }

    public static String checkWinner() {
        List<Integer> topRow = Arrays.asList(1,2,3);
        List<Integer> midRow = Arrays.asList(4,5,6);
        List<Integer> botRow = Arrays.asList(7,8,9);
        List<Integer> leftCol = Arrays.asList(1,4,7);
        List<Integer> midCol = Arrays.asList(2,5,8);
        List<Integer> rightCol = Arrays.asList(3,6,9);
        List<Integer> rightDiag = Arrays.asList(1,5,9);
        List<Integer> leftDiag = Arrays.asList(7,5,3);

        List<List<Integer>> winning = new ArrayList<List<Integer>>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(rightDiag);
        winning.add(leftDiag);

        for(List<Integer> l : winning){
            if(playerPos.containsAll(l)){
                return "You Win!";
            } else if(cpuPos.containsAll(l)){
                return "You Lose";
            } else if(playerPos.size() + cpuPos.size() == 9){
                return "tie...";
            }
        }


        return "";
    }

    public static int secondAiMove(char[][]gb){
        switch(strat){
            case 1:
            if (playerPos.contains(2)||playerPos.contains(4) ||playerPos.contains(6) ||playerPos.contains(8)){
                boardState = 1;
                return 5;
            } else if (playerPos.contains(5)|| playerPos.contains(3)|| playerPos.contains(7)){
                boardState = 2;
                return 9;
            } else if (playerPos.contains(9)){
                boardState = 3;
                return 3;
            }
            break;
            case 2:
            if (playerPos.contains(2)||playerPos.contains(8)){
                boardState = 4;
                return 6;
            } else if (playerPos.contains(1)){
                boardState = 5;
                return 9;
            } else if (playerPos.contains(3)){
                boardState = 6;
                return 7;
            } else if (playerPos.contains(7)) {
                boardState = 7;
                return 3;
            } else if (playerPos.contains(9)){
                boardState = 8;
                return 1;
            } else if (playerPos.contains(4)||playerPos.contains(6)){
                boardState = 9;
                return 2;
            }
            break;
        }
        return -1;
    }

    public static int thirdAiMove() {
        switch(boardState){
            case 1:
            if(!playerPos.contains(9)){
                //win
                return 9;
            } else if (playerPos.contains(8)||playerPos.contains(2)){
                boardState = 1;
                return 7;
            } else if (playerPos.contains(6)||playerPos.contains(4)){
                boardState = 2;
                return 3;
            }
            break;
            case 2:
            if(!playerPos.contains(5)){
                //win
                return 5;
            } else if (playerPos.contains(3)) {
                boardState = 3;
                return 7;
            } else if (playerPos.contains(7)){
                boardState = 4;
                return 3;
            } else if (playerPos.contains(2)){
                boardState = 5;
                return 8;
            } else if (playerPos.contains(4)){
                boardState = 6;
                return 6;
            } else if (playerPos.contains(6)){
                boardState = 7;
                return 4;
            } else if (playerPos.contains(8)){
                boardState = 8;
                return 2;
            }
            break;
            case 3:
            if (!playerPos.contains(2)){
                //win
                return 2;
            } else {
                boardState = 9;
                return 5;
            }
            case 4:
            if (!playerPos.contains(4)){
                //win
                return 4;
            } else {
                boardState = 10;
                return 9;
            }
            case 5:
            if (playerPos.contains(2)){
                boardState = 11;
                return 3;
            } else if (playerPos.contains(3)){
                boardState = 12;
                return 2;
            } else if (playerPos.contains(4)){
                boardState = 13;
                return 7;
            } else if (playerPos.contains(6)){
                boardState = 14;
                return 8;
            } else if (playerPos.contains(7)){
                boardState = 15;
                return 4;
            } else if (playerPos.contains(8)){
                boardState = 16;
                return 6;
            }
            break;
            case 6:
            if (playerPos.contains(1)){
                boardState = 17;
                return 2;
            } else if (playerPos.contains(2)){
                boardState = 18;
                return 1;
            } else if (playerPos.contains(4)){
                boardState = 19;
                return 8;
            } else if (playerPos.contains(6)){
                boardState = 20;
                return 9;
            } else if (playerPos.contains(8)){
                boardState = 21;
                return 4;
            } else if (playerPos.contains(9)){
                boardState = 22;
                return 6;
            }
            break;
            case 7:
            if (playerPos.contains(1)){
                boardState = 23;
                return 4;
            } else if (playerPos.contains(2)){
                boardState = 24;
                return 6;
            } else if (playerPos.contains(4)){
                return 1;
            } else if (playerPos.contains(6)){
                return 2;
            } else if (playerPos.contains(8)){
                return 9;
            } else if (playerPos.contains(9)){
                return 8;
            }
            break;
            case 8:
            if (playerPos.contains(2)){
                return 4;
            } else if (playerPos.contains(3)){
                return 6;
            } else if (playerPos.contains(4)){
                return 2;
            } else if (playerPos.contains(6)){
                return 3;
            } else if (playerPos.contains(7)){
                return 8;
            } else if (playerPos.contains(8)){
                return 7;
            }
            break;
            case 9:
            if (!playerPos.contains(8)){
                //win
                return 8;
            } else {
                return 1;
            }
        }
        return -1;
    }

    public static int fourthAiMove(){
        switch(boardState){
            case 1:
            if(!playerPos.contains(4)){
                //win
                return 4;
            } else {
                //win
                return 3;
            }
            case 2:
            if(!playerPos.contains(2)){
                //win
                return 2;
            } else {
                //win
                return 7;
            }
            case 3:
            if(!playerPos.contains(4)){
                //win
                return 4;
            } else {
                //win
                return 8;
            }
            case 4:
            if(!playerPos.contains(2)){
                //win
                return 2;
            } else {
                //win
                return 6;
            }
            case 5:
            if(!playerPos.contains(7)){
                //win
                return 7;
            } else {
                boardState = 1;
                return 3;
            }
            case 6:
            if(!playerPos.contains(3)){
                //win
                return 3;
            } else {
                boardState = 2;
                return 7;
            }
            case 7:
            if(!playerPos.contains(7)){
                //win
                return 7;
            } else {
                boardState = 3;
                return 3;
            }
            case 8:
            if(!playerPos.contains(3)){
                //win
                return 3;
            } else {
                boardState = 4;
                return 7;
            }
            case 9:
            if(!playerPos.contains(7)){
                //win
                return 7;
            } else {
                boardState = 5;
                return 8;
            }
            case 10:
            if(!playerPos.contains(1)){
                //win
                return 1;
            } else {
                //win
                return 3;
            }
            case 11:
            if(!playerPos.contains(7)){
                //win
                return 7;
            } else {
                //win
                return 6;
            }
            case 12:
            if(!playerPos.contains(8)){
                //win
                return 8;
            } else {
                boardState = 6;
                return 6;
            }
            case 13:
            if(!playerPos.contains(3)){
                //win
                return 3;
            } else {
                //win
                return 8;
            }
            case 14:
            if(!playerPos.contains(7)){
                //win
                return 7;
            } else {
                //win
                return 2;
            }
            case 15:
            if(!playerPos.contains(6)){
                //win
                return 6;
            } else {
                boardState = 7;
                return 2;
            }
            case 16:
            if(!playerPos.contains(3)){
                //win
                return 3;
            } else {
                //win
                return 4;
            }
            case 17:
            if(!playerPos.contains(8)){
                //win
                return 8;
            } else {
                boardState = 8;
                return 6;
            }
            case 18:
            if(!playerPos.contains(4)){
                //win
                return 4;
            } else {
                //win
                return 9;
            }
            case 19:
            if(!playerPos.contains(9)){
                //win
                return 9;
            } else {
                //win
                return 2;
            }
            case 20:
            if(!playerPos.contains(8)){
                //win
                return 8;
            } else {
                //win
                return 2;
            }
            case 21:
            if(!playerPos.contains(1)){
                //win
                return 1;
            } else {
                //win
                return 6;
            }
            case 22:
            if(!playerPos.contains(4)){
                //win
                return 4;
            } else {
                boardState = 9;
                return 2;
            }
            case 23:
            if(!playerPos.contains(6)){
                //win
                return 6;
            } else {
                boardState = 10;
                return 8;
            }
            
        }
    }


}