package minesweeper.storage;
//Libraries

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;


//Class
public class ImageList{
    //Variables
    List<ImageIcon> images = new ArrayList<>();
    private static ImageList instance = null;
    //Constructor
    public ImageList(){
        convertAllImages();
    }
    
    private void convertAllImages(){ //Converts images to appropriate size
        //From 0 to 8.png
        for(int i=0;i<9;i++){
            images.add(new ImageIcon(scale("resources/"+i+".png")));
        }
        
        //All the rest
        images.add(new ImageIcon(scale("resources/facingDown.png")));   //Index in List:  9
        images.add(new ImageIcon(scale("resources/bomb.png")));         //Index in List: 10
        images.add(new ImageIcon(scale("resources/flagged.png")));      //Index in List: 11
    }
    
    private Image scale(String filepath){ //Method used to scale down images from resources files...
        ImageIcon image_icon = new ImageIcon(filepath); 
        Image image = image_icon.getImage(); 
        Image new_image = image.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH); 
        
        return new_image;
    }
    
    public ImageIcon getImageFromList(int index){ //Returns image from list
        return images.get(index);
    }
    
    public static ImageList getInstance(){ //Returns singleton instance
        if(instance == null){
            System.out.println("ImageList Instance Created!");
            instance = new ImageList();
        }
        return instance;
    }
    
}
