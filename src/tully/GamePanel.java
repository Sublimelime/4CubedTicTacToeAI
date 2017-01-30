package tully;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;
import pack1.RecursivePython;

public class GamePanel extends JPanel implements Runnable, MouseListener {
    // Sleep Timers

    public static int playerInfoSleepTime = 0;
    public static int moveSleepTime = 5000;
    public static int endSleepTime = 4000;

    // number of games each player will get to play as Red
    public static int numGames = 100;

    // List of Players
    public ArrayList<PlayerInt> AIs1 = new ArrayList<PlayerInt>();
    public ArrayList<PlayerInt> AIs2 = new ArrayList<PlayerInt>();

    public PlayerInt player1 = null;
    public PlayerInt player2 = null;
    public BoardInt board = new Board();

    // Scoring List
    public ArrayList<PlayerScores> scoring = new ArrayList<PlayerScores>();

    private Thread boardDisplay = null;
    BufferedImage img;

    private boolean firstPlayersTurn = false;

    public GamePanel(int width, int height) {
        super();

        setSize(width, height);

        addMouseListener(this);

        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Add a player to both Player ArrayLists and then add a new Player to both ArrayLists
        AIs1.add(new Test_AI_1('X'));
        AIs2.add(new Test_AI_1('O'));

        //AIs1.add(new Test_AI_2('X'));
        //AIs2.add(new Test_AI_2('O'));
        AIs1.add(new RecursivePython('O'));
        AIs2.add(new RecursivePython('X'));

        //AIs1.add(new HumanPlayer('X',"Tully"));
        //AIs2.add(new HumanPlayer('O',"Tully"));
        // location to store a move
        LocationInt loc;

        // Creates Player Scores for each player
        for (int x = 0; x < AIs1.size(); x++) {
            scoring.add(new PlayerScores());
        }
    }

    public void paint(Graphics g) {
        board.draw(img.getGraphics());

        g.drawImage(img, 0, 0, null);
    }

    public void addNotify() {
        super.addNotify();
        Thread t = new Thread(this);
        t.start();
    }

    public void run() {
        for (int x = 0; x < AIs1.size(); x++) {
            for (int y = 0; y < AIs1.size(); y++) {
                if (y > x) {
                    System.out.println(AIs1.get(x).getName() + " vs " + AIs2.get(y).getName() + "\n");
                    System.out.println("\t" + AIs1.get(x).getName() + " is red and goes first.");
                    System.out.println("\t" + AIs2.get(y).getName() + " is blue and goes second.");

                    try {
                        Thread.sleep(playerInfoSleepTime);
                    } catch (Exception e) {
                        e.getMessage();
                    }

                    for (int temp = 0; temp < numGames; temp++) {
                        playGame(x, y);
                    }

                    System.out.println("\n\tChanging play order");
                    System.out.println("\t" + AIs2.get(y).getName() + " is red and goes first.");
                    System.out.println("\t" + AIs1.get(x).getName() + " is blue and goes second.");

                    try {
                        Thread.sleep(playerInfoSleepTime);
                    } catch (Exception e) {
                        e.getMessage();
                    }

                    for (int temp = 0; temp < numGames; temp++) {
                        playGame(y, x);
                    }
                }
            }
        }

        // prints the raw results for each player
        System.out.println("Score Summaries:");
        int max = 0;
        for (int x = 0; x < AIs1.size(); x++) {
            System.out.println(AIs1.get(x).getName() + " scored");
            System.out.println(scoring.get(x).getWins() + " wins");
            System.out.println(scoring.get(x).getLosses() + " losses");
            System.out.println(scoring.get(x).getCats() + " ties\n\n");
            if (max < scoring.get(x).totalScore()) {
                max = scoring.get(x).totalScore();
            }
        }

        // prints the winning team(s)
        System.out.println("Winning teams: ");
        for (int x = 0; x < AIs1.size(); x++) {
            if (max == scoring.get(x).totalScore()) {
                System.out.println(AIs1.get(x).getName());
            }
        }
    }

    public void playGame(int x, int y) {
        board.reset();
        LocationInt l;
        firstPlayersTurn = true;
        player1 = AIs1.get(x);
        player2 = AIs2.get(y);

        while (true) {
            if (!board.isWinner('X') && !board.isWinner('X') && !board.isCat()) {
                if (firstPlayersTurn) {
                    if (AIs1.get(x) instanceof HumanPlayer == false) {
                        l = AIs1.get(x).getMove(new Board(board));
                        //System.out.println("aaa"+board+l);
                        if (board.isEmpty(l)) {

                            board.setLocation(l, 'X');
                        } else {
                            System.out.println(AIs1.get(x).getName() + " failed to move.");
                        }
                        firstPlayersTurn = false;
                        try {
                            Thread.sleep(moveSleepTime);
                        } catch (Exception e) {
                            e.getMessage();
                        }
                    }
                } else {
                    if (AIs2.get(y) instanceof HumanPlayer == false) {
                        l = AIs2.get(y).getMove(new Board(board));
                        if (board.isEmpty(l))
                            board.setLocation(l, 'O');
                        else {
                            System.out.println(AIs2.get(y).getName() + " failed to move.");
                        }
                        firstPlayersTurn = true;
                    }
                    try {
                        Thread.sleep(moveSleepTime);
                    } catch (Exception e) {
                        e.getMessage();
                    }
                }
            }

            if (board.isWinner('X')) {
                System.out.println(AIs1.get(x).getName() + " wins.");
                scoring.get(x).addWin();
                scoring.get(y).addLoss();

                break;
            } else if (board.isWinner('O')) {
                System.out.println("\t\t" + AIs1.get(y).getName() + " wins the game!!!");
                scoring.get(y).addWin();
                scoring.get(x).addLoss();

                break;
            } else if (board.isCat()) {
                scoring.get(y).addCat();
                scoring.get(x).addCat();
                System.out.println("\t\t" + "Cat");

                break;
            }
            try {
                repaint();
                Thread.sleep(5);
            } catch (Exception e) {
                e.getMessage();
            }
        }

        Long start = System.nanoTime();
        while ((System.nanoTime() - start) / 1000000 <= endSleepTime) {
            try {
                repaint();
                Thread.sleep(50);
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        Location l = null;

        for (GamePolygon gp : board.getPolys()) {
            if (gp.getPolygon().contains(x, y)) {
                l = gp.getLocation();
                break;
            }
        }

        if (l != null && board.isEmpty(l)) {
            if (firstPlayersTurn == true && player1 instanceof HumanPlayer) {
                board.setLocation(l, 'X');
                firstPlayersTurn = false;
            } else if (firstPlayersTurn == false && player2 instanceof HumanPlayer) {
                board.setLocation(l, 'O');
                firstPlayersTurn = true;
            }

        }
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}
