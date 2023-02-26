package minesweeper.frame.statusline.labels;

//Libraries
import javax.swing.JLabel;
import minesweeper.frame.GameFrame;

//Class
public class LivesCountLabel extends JLabel{
    //Variables
    
    //Constructor
    public LivesCountLabel(){
        super();
        this.setEnabled(true);
        rewriteLives();
    }
    
    //Methods
    public void rewriteLives(){
        this.setText("Lives: "+GameFrame.LIVES);
    }
}

