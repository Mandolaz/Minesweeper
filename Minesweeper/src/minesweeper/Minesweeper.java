/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;
//Libraries

import javax.swing.UIManager;
import minesweeper.frame.mainmenu.MenuFrame;


//@author Mandolaz
public class Minesweeper {
    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        new MenuFrame();
    }
}
