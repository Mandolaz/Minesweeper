package minesweeper.tiles;
//Libraries

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import minesweeper.frame.GameFrame;
import minesweeper.frame.mainmenu.MenuFrame;
import minesweeper.frame.statusline.StatusLine;
import minesweeper.frame.statusline.labels.TimerLabel;
import static minesweeper.frame.statusline.labels.TimerLabel.format;
import minesweeper.storage.Highscore;
import minesweeper.storage.ImageList;
import minesweeper.storage.TileList;

//Class
public class TileActionListener implements ActionListener{
    //Variables
    Tile tile;
    
    //Listener
    @Override
    public void actionPerformed(ActionEvent e) {
        //Get the Tile user clicked
        tile = (Tile) e.getSource();
        
        //if the tile is a mine...
        if(tile.isMine){
            //Reduce LIVES by 1
            if(GameFrame.LIVES > 0){
                GameFrame.LIVES--;
                //When LIVES are 0...
                if(GameFrame.LIVES <= 0){
                    TimerLabel.stopTimer();
                    CreateOptionsPane("You run out of lives!", "Game Over!");
                }
                //Redice Mines by 1
                if(GameFrame.MINES>0)
                    GameFrame.MINES--;
                
                GameFrame.CORRECT_TILES++;
                System.out.println("Tiles Found: "+GameFrame.CORRECT_TILES);
            
                //Re-write labels with the correct values
                StatusLine.LIVESCOUNT_LABEL.rewriteLives();
                StatusLine.MINECOUNT_LABEL.rewriteMines();
            }
        }
        else{
            boolean found = false;
            for(int i=0;i<12;i++)
                if(tile.getIcon() == ImageList.getInstance().getImageFromList(i))
                    if(tile.getIcon() != ImageList.getInstance().getImageFromList(11)  && tile.getIcon() != ImageList.getInstance().getImageFromList(9))
                        found = true;
            if(!found)
                GameFrame.CORRECT_TILES++;
            
            System.out.println("Tiles Found: "+GameFrame.CORRECT_TILES);
            if(GameFrame.CORRECT_TILES >= TileList.getInstance().getTileList().size() - GameFrame.MINES_MAX && GameFrame.CORRECT_FLAGS >= GameFrame.MINES_MAX){
                if(Highscore.isHighscore(GameFrame.getDifficulty(), TimerLabel.getTime())){
                    CreateOptionsPane("You win!\nDifficulty: "+GameFrame.getDifficulty()+"\nTime: "+format(TimerLabel.getTime() / 60) + ":" + format(TimerLabel.getTime() % 60)+"\nNew Highscore!", "Game Over!");
                    Highscore.saveFile(GameFrame.getDifficulty(), TimerLabel.getTime(), GameFrame.getUserName());
                }
                else
                    CreateOptionsPane("You win!\nDifficulty: "+GameFrame.getDifficulty()+"\nTime: "+TimerLabel.getTime(), "Game Over!");
            }
        }
        
    }
    
    private void CreateOptionsPane(String message, String title){
         //Create popup dialog...
        Component component = (Component) tile;
        JFrame frame = (JFrame) SwingUtilities.getRoot(component);
        Object[] options = {"OK"};

        int option = JOptionPane.showOptionDialog(frame, message, title, 
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, 
                    options, options[0]);
        if(option != -2){
            frame.dispose();
            new MenuFrame();
        }
        
    }
}
