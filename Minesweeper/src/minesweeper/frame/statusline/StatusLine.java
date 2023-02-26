package minesweeper.frame.statusline;
//Libraries

import minesweeper.frame.statusline.labels.LivesCountLabel;
import minesweeper.frame.statusline.labels.MineCountLabel;
import minesweeper.frame.statusline.labels.TimerLabel;
import java.awt.GridLayout;
import javax.swing.JPanel;


//Class
public class StatusLine extends JPanel{
    //Variables
    public static TimerLabel TIMER_LABEL;
    public static MineCountLabel MINECOUNT_LABEL;
    public static LivesCountLabel LIVESCOUNT_LABEL;
    
    //Constructor
    public StatusLine(int mines){
        super(new GridLayout(1, 0));
        
        MINECOUNT_LABEL = new MineCountLabel(mines);
        TIMER_LABEL = new TimerLabel();
        LIVESCOUNT_LABEL = new LivesCountLabel();
        
        this.add(TIMER_LABEL);
        this.add(MINECOUNT_LABEL);
        this.add(LIVESCOUNT_LABEL);
        
    }
}
