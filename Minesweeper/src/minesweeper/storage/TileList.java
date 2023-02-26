/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package minesweeper.storage;
//Libraries

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import minesweeper.tiles.Tile;

//Class
public class TileList {
    //Variables
    public static int rows, columns;
    
    List<Tile> tile_list = new ArrayList<>();
    private static TileList instance = null;
    
    //Constructor
    public TileList(int rows, int columns, int mines){
        TileList.rows = rows;
        TileList.columns = columns;
        build(rows,columns);
        setMines(mines);
    }
    
    public TileList(){      
    }
    
    private void build(int rows, int columns){
        for(int i=0;i<rows;i++)
            for(int j=0;j<columns;j++)
                tile_list.add(new Tile(i,j,ImageList.getInstance().getImageFromList(9)));
    }
    
    private void setMines(int mines){
        
        //Create a Random object
        Random rand = new Random();
        
        //List of unique values to avoid confict
        int[] uniques = new int[mines];
        int count = 0;
        boolean isUnique = true;
        
        while(count<mines){
            //Generate random number between 0 and list.size()...
            int temp = rand.nextInt(tile_list.size());
            
            //if value does not belong to unique list...
            for(int i : uniques)
                if(temp == i)
                    isUnique = false;
            
            //...assign a bomb at temp element
            if(isUnique){
                tile_list.get(temp).setMine(true);
                uniques[count++] = temp;
            }
            //reset flag
            isUnique = true;
        }
        for(Tile tile : tile_list)
                if(tile.isMine())
                    System.out.println("Bomb: X="+tile.getPosX()+", Y="+tile.getPosY());
    }
    
    public List<Tile> getTileList(){
        return tile_list;
    }
       
    public static TileList getInstance(int rows, int columns, int mines){ //
        System.out.println("TileList  Instance Created! : Rows: "+rows+", Columns: "+columns+", Mines: "+mines);
        instance = new TileList(rows,columns,mines);
        
        return instance;
    }
    
    public static TileList getInstance(){ //For usage after instance creation
        if(instance == null){
            System.out.println("TileList  Instance Created!");
            instance = new TileList();
        }
        return instance;
    }
}
