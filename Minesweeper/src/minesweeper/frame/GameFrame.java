package minesweeper.frame;
//Libraries

import java.awt.BorderLayout;
import minesweeper.frame.statusline.StatusLine;
import minesweeper.frame.game.GamePanel;
import javax.swing.JFrame;

//Class
public class GameFrame extends JFrame{
    //Variables
    public static int LIVES = 2;
    public static int MINES;
    public static int MINES_MAX;
    public static int CORRECT_TILES = 0;
    public static int CORRECT_FLAGS = 1;
    
    private static String DIFFICULTY, USERNAME;
    
    GamePanel game_panel;
    StatusLine status_line;
    //Constructor
    public GameFrame(int rows, int columns, int mines, String name){
        super("Minesweeper");
        resetValues();
        GameFrame.MINES_MAX = mines;
        GameFrame.MINES     = mines;
        
        this.USERNAME = name;
        
        this.setLayout(new BorderLayout());
        
        set_difficulty(columns);
        game_panel(rows,columns,mines);
        status_line(mines);
        frame_config();
    }
    
    //Methods
    private void set_difficulty(int columns){
        switch(columns){
            case 9:
                DIFFICULTY = "Beginner";
                break;
            case 16:
                DIFFICULTY = "Intermediate";
                break;
            case 30:
                DIFFICULTY = "Expert";
                break;
            default:
                DIFFICULTY = "Beginner";
                break;
        }
    }
    
    private void game_panel(int rows, int columns, int mines){
        game_panel = new GamePanel(rows,columns,mines);
        this.add(game_panel,BorderLayout.CENTER);
    }
    
    private void status_line(int mines){
        status_line = new StatusLine(mines);
        this.add(status_line,BorderLayout.PAGE_END);
    }
    
    private void frame_config(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    public static String getUserName(){
        return USERNAME;
    }
    
    public static String getDifficulty(){
        return DIFFICULTY;
    }
    
    private void resetValues(){
        LIVES = 2;
        CORRECT_TILES = 0;
        CORRECT_FLAGS = 1;
    }
}
