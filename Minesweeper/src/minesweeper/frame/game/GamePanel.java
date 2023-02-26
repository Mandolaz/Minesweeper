package minesweeper.frame.game;
//Libraries

import java.awt.GridLayout;
import javax.swing.JPanel;
import minesweeper.storage.ImageList;
import minesweeper.storage.TileList;
import minesweeper.tiles.Tile;

//Class
public class GamePanel extends JPanel{
    //Variables
    int rows, columns;
    TileList tile_list;
    ImageList icon_list;
    
    //Constructor
    public GamePanel(int rows, int columns, int mines){
        super(new GridLayout(rows, columns));
        
        this.rows = rows;
        this.columns = columns;
        
        storages(rows, columns, mines);
        build();
    }
    
    private void storages(int rows, int columns, int mines){ //Generate Tiles based on specifications
        tile_list = TileList.getInstance(rows,columns,mines);
        icon_list = ImageList.getInstance();
    }
    
    private void build(){
        for(Tile tile : tile_list.getTileList())
            this.add(tile);
    }
}
