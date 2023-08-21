import java.util.*;
class Player{
    String name;
    int pos;
    Player(String name){
        this.name = name;
        this.pos=1;
    }
    void move(int steps){
        this.pos+=steps;
    }

}
class Snake{
    int Snake_start;
    int Snake_end;
    Snake(int Snake_start, int Snake_end){
        this.Snake_start = Snake_start;
        this.Snake_end = Snake_end;
    }
}
class Ladder{
    int Ladder_start;
    int Ladder_end;
    Ladder(int Ladder_start, int Ladder_end){
        this.Ladder_start = Ladder_start;
        this.Ladder_end = Ladder_end;
    }

}
class SnakeAndLadderBoard {
    int size;
    List<Player> players;
    List<Snake> snakes;
    List<Ladder> ladders;

    SnakeAndLadderBoard(int size) {
        this.size = size;
        this.players = new ArrayList<>();
        this.snakes = new ArrayList<>();
        this.ladders = new ArrayList<>();
    }

    void addPlayer(Player player) {
        players.add(player);
    }

    void addLadders(Ladder ladder) {
        ladders.add(ladder);
    }

    void addSnakes(Snake snake) {
        snakes.add(snake);
    }

    boolean isSnakeOrLadder(int position) {
        for (Snake snake : snakes) {
            if (snake.Snake_start == position) {
                return true;
            }
            for (Ladder ladder : ladders) {
                if (ladder.Ladder_start == position) {
                    return true;
                }
            }
        }
        return false;
    }

    int getNewPosition(int position) {
        for (Snake snake : snakes) {
            if (snake.Snake_start == position) {
                return snake.Snake_end;
            }
        }
        for (Ladder ladder : ladders) {
            if (ladder.Ladder_start == position)
                return ladder.Ladder_end;
        }
        return position;
    }

    boolean hadPlayerWon(Player player) {
        return player.pos >= size;
    }
}

    public class SnakeAndLadder {
        public static void main(String[] args) {
            Scanner cin = new Scanner(System.in);
            System.out.println("Enter Number of Players");
            int numberOfPlayers=cin.nextInt();
            SnakeAndLadderBoard board = new SnakeAndLadderBoard(100);
            for(int i=1;i<=numberOfPlayers;i++){
                System.out.println("Enter name of the " + i + " Player");
                String name = cin.next();
                Player player = new Player(name);
                board.addPlayer(player);

            }
            Snake snake1 = new Snake(80,42);
            Snake snake2 = new Snake(98,11);
            Snake snake3 = new Snake(48,20);
            board.addSnakes(snake1);
            board.addSnakes(snake2);
            board.addSnakes(snake3);
            Ladder ladder1 = new Ladder(24,66);
            Ladder ladder2 = new Ladder(37,51);
            Ladder ladder3 = new Ladder(77,93);
            board.addLadders(ladder1);
            board.addLadders(ladder2);
            board.addLadders(ladder3);

            while(true){
                for(Player player: board.players){
                    if(!board.hadPlayerWon(player)){
                        System.out.println(player.name + " Press Enter to roll the dice");
                        cin.nextLine();

                        int diceRoll = (int)(Math.random()*6)+1;
                        System.out.println(player.name + " Rolled a " + diceRoll);

                        int newPosition = player.pos + diceRoll;
                        if(newPosition <= board.size){
                            newPosition = board.getNewPosition(newPosition);
                            player.move(newPosition - player.pos);

                            if(board.hadPlayerWon(player)){
                                System.out.println(player.name + " won");
                                return;
                            }
                        }
                        System.out.println(player.name + "is now at postion "+ player.pos);
                    }
                }
            }

        }

    }

