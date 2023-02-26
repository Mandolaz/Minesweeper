package minesweeper.frame.statusline.labels;
//Libraries

import javax.swing.JLabel;
import minesweeper.frame.GameFrame;


//Class
public class MineCountLabel extends JLabel{   
    //Variables
    
    //Constructor
    public MineCountLabel(int mines){
        super();
        this.setEnabled(true);
        rewriteMines();
    }
    
    //Methods
    public void rewriteMines(){
        this.setText("Mines: "+GameFrame.MINES);
    }
}
