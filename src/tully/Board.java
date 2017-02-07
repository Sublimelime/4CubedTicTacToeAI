/* Board - Stores all data for a 4x4x4 tic tac toe game
 * and allows its manipulation*/
import java.awt.*;
import java.util.ArrayList;

public class Board implements BoardInt {

    private char[][][] board = new char[4][4][4];
    private ArrayList<GamePolygon> polys = new ArrayList<GamePolygon>();

    Color xWin = Color.red;
    Color yWin = Color.blue;
    int timer = 0;

    /* Pre: None
	 * Post: Creates a 4x4x4 gameboard where every location is
	 * set to empty ('-')*/
    public Board() {
        for (int sheet = 0; sheet < numSheets(); sheet++) {
            for (int row = 0; row < numRows(); row++) {
                for (int col = 0; col < numCols(); col++) {
                    board[sheet][row][col] = '-';
                }
            }
        }

        for (int r = 0; r < numRows(); r++) {
            int rowH = 68 * (r + 1);
            for (int s = 0; s < numSheets(); s++) {
                int sheetHMod = 0 - 14 * s;
                for (int c = 0; c < numCols(); c++) {
                    int colW = 20 + 14 * c;
                    int rowWMod = s * 7;
                    int x = colW + rowWMod;
                    int y = rowH + sheetHMod;

                    Polygon p = new Polygon();
                    p.addPoint(x + 8, y);
                    p.addPoint(x + 22, y);

                    p.addPoint(x + 14, y + 15);
                    p.addPoint(x, y + 15);

                    polys.add(new GamePolygon(x, y, new Location(s, r, c), p));
                }
            }
        }
    }

    /* Pre: Recieves a board
	 * Post: Constructs a copy of the recieved board*/
    public Board(BoardInt b) {
        for (int sheet = 0; sheet < numSheets(); sheet++) {
            for (int row = 0; row < numRows(); row++) {
                for (int col = 0; col < numCols(); col++) {
                    board[sheet][row][col] = b.getLocation(new Location(sheet, row, col));
                }
            }
        }
    }

    /* Pre: None
	 * Post: Returns an ArrayList of GamePolygons for each location
	 * on the board*/
    public ArrayList<GamePolygon> getPolys() {
        return polys;
    }

    /* Pre: None
	 * Post: returns a 3D char array that stores the boards data*/
    public char[][][] getData() {
        return board;
    }

    /* Pre: Receives a location and letter (in the form of 'X' or 'O')
	 * Post: when the provided location is empty ('-') it places the
	 * received letter into the location and returns true, otherwise
	 * it returns false*/
    public boolean setLocation(LocationInt l, char c) {
        if (isEmpty(l)) {
            board[l.getSheet()][l.getRow()][l.getCol()] = c;
            return true;
        } else {
            return false;
        }
    }

    /* Pre: None
	 * Post: returns a 3D char array that stores the boards data*/
    public char[][][] getBoardArray() {
        return board;
    }

    /* Pre: Receives a location and letter (in the form of 'X' or 'O')
	 * Post: it places the received letter into the location */
    public void setLocationNoCheck(LocationInt l, char c) {
        board[l.getSheet()][l.getRow()][l.getCol()] = c;
    }

    /* Pre: Receives a location
	 * Post: returns the letter at the provided location */
    public char getLocation(LocationInt l) {
        return board[l.getSheet()][l.getRow()][l.getCol()];
    }

    /* Pre: Receives a player's letter
	 * Post: returns if the received letter has a win one the board.
	 * It will also toggle the winning move line from lowercase to uppercase
	 * each call.*/
    public boolean isWinner(char player) {
        // check rows
        for (int r = 0; r < numRows(); r++) {
            for (int s = 0; s < numSheets(); s++) {
                if (board[s][r][0] == player
                        && board[s][r][1] == player
                        && board[s][r][2] == player
                        && board[s][r][3] == player) {
                    if (board[s][r][0] == 'X') {
                        board[s][r][0]
                                = board[s][r][1]
                                = board[s][r][2]
                                = board[s][r][3] = 'x';
                    } else {
                        board[s][r][0]
                                = board[s][r][1]
                                = board[s][r][2]
                                = board[s][r][3] = 'o';
                    }
                    return true;
                }

            }
        }

        // check colums
        for (int c = 0; c < numCols(); c++) {
            for (int s = 0; s < numSheets(); s++) {
                if (board[s][0][c] == player
                        && board[s][1][c] == player
                        && board[s][2][c] == player
                        && board[s][3][c] == player) {
                    if (board[s][0][c] == 'X') {
                        board[s][0][c]
                                = board[s][1][c]
                                = board[s][2][c]
                                = board[s][3][c] = 'x';
                    } else {
                        board[s][0][c]
                                = board[s][1][c]
                                = board[s][2][c]
                                = board[s][3][c] = 'o';
                    }
                    return true;
                }
            }
        }

        // check depth
        for (int c = 0; c < numCols(); c++) {
            for (int r = 0; r < numRows(); r++) {
                if (board[0][r][c] == player
                        && board[1][r][c] == player
                        && board[2][r][c] == player
                        && board[3][r][c] == player) {
                    if (board[0][r][c] == 'X') {
                        board[0][r][c]
                                = board[1][r][c]
                                = board[2][r][c]
                                = board[3][r][c] = 'x';
                    } else {
                        board[0][r][c]
                                = board[1][r][c]
                                = board[2][r][c]
                                = board[3][r][c] = 'o';
                    }
                    return true;
                }
            }

        }

        // check crosses in z plane sheets
        for (int s = 0; s < numSheets(); s++) {
            if (board[s][0][0] == player
                    && board[s][1][1] == player
                    && board[s][2][2] == player
                    && board[s][3][3] == player) {
                if (board[s][0][0] == 'X') {
                    board[s][0][0]
                            = board[s][1][1]
                            = board[s][2][2]
                            = board[s][3][3] = 'x';
                } else {
                    board[s][0][0]
                            = board[s][1][1]
                            = board[s][2][2]
                            = board[s][3][3] = 'o';
                }
                return true;
            }
            if (board[s][0][3] == player
                    && board[s][1][2] == player
                    && board[s][2][1] == player
                    && board[s][3][0] == player) {
                if (board[s][0][3] == 'X') {
                    board[s][0][3]
                            = board[s][1][2]
                            = board[s][2][1]
                            = board[s][3][0] = 'x';
                } else {
                    board[s][0][3]
                            = board[s][1][2]
                            = board[s][2][1]
                            = board[s][3][0] = 'o';
                }
                return true;
            }
        }

        // check crosses in y plane sheets
        for (int r = 0; r < numRows(); r++) {
            if (board[0][r][0] == player
                    && board[1][r][1] == player
                    && board[2][r][2] == player
                    && board[3][r][3] == player) {
                if (board[0][r][0] == 'X') {
                    board[0][r][0]
                            = board[1][r][1]
                            = board[2][r][2]
                            = board[3][r][3] = 'x';
                } else {
                    board[0][r][0]
                            = board[1][r][1]
                            = board[2][r][2]
                            = board[3][r][3] = 'o';
                }
                return true;
            }
            if (board[3][r][0] == player
                    && board[2][r][1] == player
                    && board[1][r][2] == player
                    && board[0][r][3] == player) {
                if (board[3][r][0] == 'X') {
                    board[3][r][0]
                            = board[2][r][1]
                            = board[1][r][2]
                            = board[0][r][3] = 'x';
                } else {
                    board[3][r][0]
                            = board[2][r][1]
                            = board[1][r][2]
                            = board[0][r][3] = 'o';
                }
                return true;
            }
        }

        // check crosses in x plane sheets
        for (int c = 0; c < numCols(); c++) {
            if (board[0][0][c] == player
                    && board[1][1][c] == player
                    && board[2][2][c] == player
                    && board[3][3][c] == player) {
                if (board[0][0][c] == 'X') {
                    board[0][0][c]
                            = board[1][1][c]
                            = board[2][2][c]
                            = board[3][3][c] = 'x';
                } else {
                    board[0][0][c]
                            = board[1][1][c]
                            = board[2][2][c]
                            = board[3][3][c] = 'o';
                }
                return true;
            }
            if (board[0][3][c] == player
                    && board[1][2][c] == player
                    && board[2][1][c] == player
                    && board[3][0][c] == player) {
                if (board[0][3][c] == 'X') {
                    board[0][3][c]
                            = board[1][2][c]
                            = board[2][1][c]
                            = board[3][0][c] = 'x';
                } else {
                    board[0][3][c]
                            = board[1][2][c]
                            = board[2][1][c]
                            = board[3][0][c] = 'o';
                }
                return true;
            }
        }

        // check special cross one
        if (board[0][0][0] == player
                && board[1][1][1] == player
                && board[2][2][2] == player
                && board[3][3][3] == player) {
            if (board[0][0][0] == 'X') {
                board[0][0][0]
                        = board[1][1][1]
                        = board[2][2][2]
                        = board[3][3][3] = 'x';
            } else {
                board[0][0][0]
                        = board[1][1][1]
                        = board[2][2][2]
                        = board[3][3][3] = 'o';
            }
            return true;
        }

        // check special cross two
        if (board[0][0][3] == player
                && board[1][1][2] == player
                && board[2][2][1] == player
                && board[3][3][0] == player) {
            if (board[0][0][3] == 'X') {
                board[0][0][3]
                        = board[1][1][2]
                        = board[2][2][1]
                        = board[3][3][0] = 'x';
            } else {
                board[0][0][3]
                        = board[1][1][2]
                        = board[2][2][1]
                        = board[3][3][0] = 'o';

            }
            return true;
        }

        // check special cross three
        if (board[3][0][0] == player
                && board[2][1][1] == player
                && board[1][2][2] == player
                && board[0][3][3] == player) {
            if (board[3][0][0] == 'X') {
                board[3][0][0]
                        = board[2][1][1]
                        = board[1][2][2]
                        = board[0][3][3] = 'x';
            } else {
                board[3][0][0]
                        = board[2][1][1]
                        = board[1][2][2]
                        = board[0][3][3] = 'o';
            }
            return true;
        }

        // check special cross four
        if (board[3][0][3] == player
                && board[2][1][2] == player
                && board[1][2][1] == player
                && board[0][3][0] == player) {
            if (board[3][0][3] == 'X') {
                board[3][0][3]
                        = board[2][1][2]
                        = board[1][2][1]
                        = board[0][3][0] = 'x';
            } else {
                board[3][0][3]
                        = board[2][1][2]
                        = board[1][2][1]
                        = board[0][3][0] = 'o';
            }
            return true;
        }
        return false;

    }

    /* Pre: None
	 * Post: draws a text representation of the game board */
    public void display() {
        for (int s = numSheets() - 1; s >= 0; s--) {
            for (int r = 0; r < numRows(); r++) {
                for (int t = 0; t < s; t++) {
                    System.out.print("    ");
                }
                for (int c = 0; c < numCols(); c++) {

                    System.out.print(board[s][r][c]);

                }
                System.out.println();
            }

        }
    }

    /* Pre: Receives a location
	 * Post: returns true if the provided location is empty ('-'),
	 * otherwise it returns false*/
    public boolean isEmpty(LocationInt l) {
        if (board[l.getSheet()][l.getRow()][l.getCol()] == '-') {
            return true;
        } else {
            return false;
        }
    }

    /* Pre: None
	 * Post: returns true there is no winner and all the spaces
	 * are filled*/
    public boolean isCat() {
        int m = 0;
        for (int c = 0; c < numCols(); c++) {
            for (int r = 0; r < numRows(); r++) {
                for (int s = 0; s < numSheets(); s++) {
                    if (board[s][r][c] == '-') {
                        return false;
                    }
                }
            }
        }
        if (isWinner('X') || isWinner('O')) {
            return false;
        } else {
            return true;
        }
    }

    /* Pre: None
	 * Post: returns the number of sheets on the board*/
    public int numRows() {
        return board[0].length;
    }

    /* Pre: None
	 * Post: returns the number of columns on the board*/
    public int numCols() {
        return board[0][0].length;
    }

    /* Pre: None
	 * Post: returns the number of sheets on the board*/
    public int numSheets() {
        return board.length;
    }

    /* Pre: None
	 * Post: sets all the locations on the board to empty ('-')*/
    public void reset() {
        for (int s = 0; s < numSheets(); s++) {
            for (int r = 0; r < numRows(); r++) {
                for (int c = 0; c < numCols(); c++) {
                    board[s][r][c] = '-';
                }
            }
        }
    }

    /* Pre: Recieves a graphics object
	 * Post: draws a graphical representation of the board to the received
	 * graphics object */
    public void draw(Graphics g) {
        timer = (timer + 1) % 8;

        if (timer == 0) {
            if (xWin.equals(Color.red)) {
                xWin = yWin = Color.gray;
            } else {
                xWin = Color.red;
                yWin = Color.blue;
            }
        }
        g.setColor(Color.black);
        g.fillRect(0, 0, 145, 315);

        for (int a = 0; a < polys.size(); a++) {
            g.setColor(Color.white);
            g.drawPolygon(polys.get(a).getPolygon());
            int x = polys.get(a).getX();
            int y = polys.get(a).getY();
            char letter = getLocation(polys.get(a).getLocation());
            if (letter == 'X') {
                g.setColor(Color.red);
                g.fillOval(x + 5, y + 1, 14, 14);
            } else if (letter == 'O') {
                g.setColor(Color.blue);
                g.fillOval(x + 5, y + 1, 14, 14);
            } else if (letter == 'x') {
                g.setColor(xWin);
                g.fillOval(x + 5, y + 1, 14, 14);
            } else if (letter == 'o') {
                g.setColor(yWin);
                g.fillOval(x + 5, y + 1, 14, 14);
            }
        }
    }

    /* Pre: Receives a graphics object and location in the form on (x,y,z)
	 * and a letter.
	 * Post: draws a polygon with the letter on the screen 'X' is red,
	 * 'O' is blue and ('x'/'o') are white */
    public void drawLocation(Graphics g, int x, int y, char letter) {
        g.setColor(Color.gray);
        g.drawLine(x + 8, y, x + 22, y);
        g.drawLine(x + 8, y, x, y + 15);
        if (letter == 'X') {
            g.setColor(Color.red);
            g.fillOval(x + 5, y + 1, 14, 14);
        } else if (letter == 'O') {
            g.setColor(Color.blue);
            g.fillOval(x + 5, y + 1, 14, 14);
        } else if (letter == 'x') {
            g.setColor(xWin);
            g.fillOval(x + 5, y + 1, 14, 14);
        } else if (letter == 'o') {
            g.setColor(yWin);
            g.fillOval(x + 5, y + 1, 14, 14);
        }
        g.setColor(Color.gray);
        g.drawLine(x, y + 15, x + 14, y + 15);
        g.drawLine(x + 22, y, x + 14, y + 15);
    }

    /* Pre: None
	 * Post: returns a copy of the game board */
    public Object clone() {
        Board copy = new Board();
        for (int sheet = 0; sheet < numSheets(); sheet++) {
            for (int row = 0; row < numRows(); row++) {
                for (int col = 0; col < numCols(); col++) {
                    copy.getData()[sheet][row][col] = board[sheet][row][col];
                }
            }
        }
        return copy;
    }
}
