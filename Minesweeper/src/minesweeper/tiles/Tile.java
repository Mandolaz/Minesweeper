package minesweeper.tiles;
//Libraries

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;

//Class
public class Tile extends JButton{
    //Variables
    boolean isMine = false;
    int x;
    int y;
    
    //Constructor
    public Tile(int x, int y, ImageIcon icon){
        this.x = x;
        this.y = y;
        createTile(icon);
        addListeners();
    }
    
    private void createTile(ImageIcon icon){   //Set Tile's image to tile face down...
        this.setIcon(icon);
        this.setPreferredSize(new Dimension(20, 20));
    }
    
    private void addListeners(){
        this.addMouseListener(new MouseCheck(this));
        this.addActionListener(new TileActionListener());
    }
    
    public void setMine(boolean value){
        this.isMine = value;
    }
    
    public boolean isMine(){
        return this.isMine;
    }
    
    public int getPosX(){
        return this.x;
    }
    
    public int getPosY(){
        return this.y;
    }
}
