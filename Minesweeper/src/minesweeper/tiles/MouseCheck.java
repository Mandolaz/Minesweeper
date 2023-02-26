/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package minesweeper.tiles;
//Libraries

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import minesweeper.frame.GameFrame;
import minesweeper.frame.statusline.StatusLine;
import minesweeper.storage.ImageList;
import minesweeper.storage.TileList;

//Class
public class MouseCheck extends MouseAdapter{
    
    //Variables
    boolean pressed;
    Tile tile;
    //Constructor
    public MouseCheck(Tile button){
        this.tile = button;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        tile.getModel().setArmed(true);
        tile.getModel().setPressed(true);
        pressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        tile.getModel().setArmed(false);
        tile.getModel().setPressed(false);
        
        //If the tile is pressed...
        if (pressed)
            //...right click function as long as tile is face-down...
            if (SwingUtilities.isRightMouseButton(e) && tile.getIcon() == ImageList.getInstance().getImageFromList(9)){
                //Set flag with right click
                if(GameFrame.MINES > 0){
                    tile.setIcon(ImageList.getInstance().getImageFromList(11));
                    
                    //Reduce MINES by 1
                    if(GameFrame.MINES > 0)
                        GameFrame.MINES--;
                
                    //if its a mine...
                    if(tile.isMine){
                        //...increase Correct Guesses
                        System.out.println("correct: "+GameFrame.CORRECT_FLAGS++);
                    }
                
                
                }
                
                //Re-write label with correct values
                StatusLine.MINECOUNT_LABEL.rewriteMines();
            }
            //...if right-clicked a flag...
            else if(SwingUtilities.isRightMouseButton(e) && tile.getIcon() == ImageList.getInstance().getImageFromList(11)){
                //Remove flag and return to face down
                tile.setIcon(ImageList.getInstance().getImageFromList(9));
                
                //Increase MINES by 1
                GameFrame.MINES++;
                
                //if its a mine AND CORRECT >0 ...
                if(tile.isMine && GameFrame.CORRECT_FLAGS >= 0)
                    //Decrease Correct guesses
                    GameFrame.CORRECT_FLAGS--;
                
                //Rewrite label with correct values
                StatusLine.MINECOUNT_LABEL.rewriteMines();
            }
            else
                //if tile clicked is a mine and is not revealed
                if(tile.isMine && tile.getIcon() != ImageList.getInstance().getImageFromList(10)){ //Reveal Bomb...
                    tile.setIcon(ImageList.getInstance().getImageFromList(10));
                    tile.setMine(false);
                }
                
                //if tile is a mine and it's revealed
                else if(tile.getIcon() == ImageList.getInstance().getImageFromList(10)){
                    //do nothing
                }
                else{
                    if(tile.getIcon() == ImageList.getInstance().getImageFromList(11)){
                        if(GameFrame.MINES < GameFrame.MINES_MAX)
                            GameFrame.MINES++;
                        checkSurroundings();
                    }
                    else
                        checkSurroundings();
                    
                    StatusLine.MINECOUNT_LABEL.rewriteMines();
                }
        
        pressed = false;
    }
    @Override
    public void mouseExited(MouseEvent e) {
        pressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        pressed = true;
    }                    
    
    private void checkSurroundings(){
        //
        int mines = 0;
        for(int i=-1;i<2;i++)
            /*Conditions for validation check: 
            0 <= x < ROWS
            0 <= y < COLUMNS
            BOTH x and y are NOT the same as tile's cords.
            */
            for(int j=-1;j<2;j++)
                if  (tile.getPosX()+i>=0 && tile.getPosX()+i<TileList.rows && 
                    tile.getPosY()+j>=0 && tile.getPosY()+j<TileList.columns && 
                    (tile.getPosX()+i!=tile.getPosX() || tile.getY()+j!=tile.getY())
                    )  
                    
                    if(isBombed(tile.getPosX()+i, tile.getPosY()+j)){
                        mines++;
                    }
        
        //Set the tile's image to a numeral image indicating the number of bombs surrounding it
        tile.setIcon(ImageList.getInstance().getImageFromList(mines));
    }
    
    private boolean isBombed(int x, int y){
        //For each tile in TileList's List...
        for(Tile tile : TileList.getInstance().getTileList()){
            
            //...if the coord is bombed...
            if (tile.getPosX() == x && tile.getPosY() == y && tile.isMine)
                return true;
        }
        
        //...else return false
        return false;
    }
    
}
