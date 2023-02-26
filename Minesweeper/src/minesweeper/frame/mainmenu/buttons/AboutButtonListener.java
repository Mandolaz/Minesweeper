package minesweeper.frame.mainmenu.buttons;
//Libraries

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

//Class
public class AboutButtonListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        Component component = (Component) e.getSource();
        JFrame frame = (JFrame) SwingUtilities.getRoot(component);
        
        JOptionPane.showMessageDialog(frame, "Manos Damaskinakis\nTP4750", "About me", JOptionPane.INFORMATION_MESSAGE);
    }
}
