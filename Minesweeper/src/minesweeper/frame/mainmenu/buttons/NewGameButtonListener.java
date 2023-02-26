/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package minesweeper.frame.mainmenu.buttons;
//Libraries

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import minesweeper.frame.GameFrame;


//Class
public class NewGameButtonListener implements ActionListener{
    //Variables
    String name;
    String[] options = {"Beginner","Intermidiate","Expert"};
   
    @Override
    public void actionPerformed(ActionEvent e) {
        int option = JOptionPane.showOptionDialog(null, "Select  Difficulty", null, 
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, 
                options, options[0]);
                
                Component component = (Component) e.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                
                if(option != -1)
                    switch(options[option]){
                        case "Beginner": 
                            name = JOptionPane.showInputDialog("Enter your name:");
                            new GameFrame(9, 9, 10, name);
                            frame.dispose();
                            break;

                        case "Intermidiate":
                            name = JOptionPane.showInputDialog("Enter your name:");
                            new GameFrame(16, 16, 30, name);
                            frame.dispose();
                            break;

                        case "Expert": 
                            name = JOptionPane.showInputDialog("Enter your name:");                           
                            new GameFrame(16, 30, 50, name);
                            frame.dispose();
                            break;
                    }
    }
}
