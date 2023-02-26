package minesweeper.frame.mainmenu.buttons;
//Libraries

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import static minesweeper.frame.statusline.labels.TimerLabel.format;
import minesweeper.storage.Highscore;
import org.json.simple.JSONObject;

//Class
public class HighscoreButtonListener implements ActionListener{
    //Variables
    List<JSONObject> highscores = new ArrayList<>();
    JFrame      frame;
    JPanel      panel;
    int[]       time_scores;
    String[]    names, time_formated;
    String[]    difficulties = {"Beginner","Intermediate","Expert"};
    
    public HighscoreButtonListener(){
        frame = new JFrame("Highscores");
        panel = new JPanel(new GridLayout(4, 3));
        
        //read JSON
        highscores = Highscore.readHighscoreJSON();

        //Create arrays with information
        time_scores     = new int[highscores.size()];
        time_formated   = new String[highscores.size()];
        names           = new String[highscores.size()];
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {        
        for(int i=0;i<highscores.size();i++){
            time_scores[i]  = (highscores.get(i).get("Time") == null) ? 0       : ((Long)highscores.get(i).get("Time")).intValue();
            names[i]        = (highscores.get(i).get("Name") == null) ? "N/A"   : (String) highscores.get(i).get("Name");
            time_formated[i]= format(time_scores[i] / 60) + ":" + format(time_scores[i] % 60);
        }
        
        build_frame();
        frame_config();
    }
    
    private void build_frame(){
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
        
        panel.add(new JLabel("Difficulty", SwingConstants.CENTER));
        panel.add(new JLabel("Time", SwingConstants.CENTER));
        panel.add(new JLabel("Name", SwingConstants.CENTER));
        
        for(int i=0;i<3;i++){
            panel.add(new JLabel(difficulties[i], SwingConstants.CENTER));
            panel.add(new JLabel(String.valueOf(time_formated[i]), SwingConstants.CENTER));
            panel.add(new JLabel(names[i], SwingConstants.CENTER));
        }
        
        frame.add(panel);
    }
    
    private void frame_config(){
        frame.setPreferredSize(new Dimension(250, 150));
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }
}
