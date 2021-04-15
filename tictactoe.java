import java.util.*;

public class tictactoe{

    static ArrayList<Integer> playerPos = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPos = new ArrayList<Integer>();
    public static void main(String[] args){

        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                              {'-', '+', '-', '+', '-'},
                              {' ', '|', ' ', '|', ' '},
                              {'-', '+', '-', '+', '-'},
                              {' ', '|', ' ', '|', ' '}};
        printGB(gameBoard); 

       
        while(true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter your placement (1-9);");
            int pos = scan.nextInt();
            while(playerPos.contains(pos) || cpuPos.contains(pos)){
                System.out.println("Position taken! Enter a correct Position");
                pos = scan.nextInt();
            }
            String result = checkWinner();
            if(result.length() > 0 ){
                System.out.println(result);
                scan.close();
                break;
            }

            placePiece(gameBoard, pos, "player");
            Random rand = new Random();
            int cPos = rand.nextInt(9) + 1;
            while(playerPos.contains(cPos) || cpuPos.contains(cPos)){
                cPos = rand.nextInt(9) + 1;
            }
            placePiece(gameBoard, cPos, "cpu");
            printGB(gameBoard);
            result = checkWinner();
            if(result.length() > 0 ){
                System.out.println(result);
                scan.close();
                break;
            }
            
        }
        

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
        List topRow = Arrays.asList(1,2,3);
        List midRow = Arrays.asList(4,5,6);
        List botRow = Arrays.asList(7,8,9);
        List leftCol = Arrays.asList(1,4,7);
        List midCol = Arrays.asList(2,5,8);
        List rightCol = Arrays.asList(3,6,9);
        List rightDiag = Arrays.asList(1,5,9);
        List leftDiag = Arrays.asList(7,5,3);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(rightDiag);
        winning.add(leftDiag);

        for(List l : winning){
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

}