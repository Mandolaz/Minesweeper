package minesweeper.frame.mainmenu;
//Libraries

import minesweeper.frame.mainmenu.buttons.NewGameButtonListener;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import minesweeper.frame.mainmenu.buttons.AboutButtonListener;
import minesweeper.frame.mainmenu.buttons.HighscoreButtonListener;

//Class
public class MenuFrame extends JFrame{
    //Variables
    JButton new_game, highscore, about;
    JPanel  frame_panel;
    //Constructor
    public MenuFrame(){
        super("Minesweeper");
        layout_config();
        build();
        frame_config();
    }
    
    //Methods
    private void layout_config(){
        frame_panel = new JPanel(new GridLayout(1,0));
    }
    
    private void build(){
        
        //New Game button...
        new_game    = new JButton("New Game");
        new_game.addActionListener(new NewGameButtonListener());
        
        //Highscore button...
        highscore   = new JButton("Highscore");
        highscore.addActionListener(new HighscoreButtonListener());
        
        //About button...
        about       = new JButton("About");
        about.addActionListener(new AboutButtonListener());
        
        //Add buttons to frame_panel...
        frame_panel.add(new_game);
        frame_panel.add(highscore);
        frame_panel.add(about);
        this.add(frame_panel);
    }
    
    private void frame_config(){
        this.setPreferredSize(new Dimension(400, 100));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
    }
}
