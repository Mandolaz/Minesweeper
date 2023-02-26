package minesweeper.frame.statusline.labels;
//Libraries

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

//Class
public class TimerLabel extends JLabel implements ActionListener{
    //Variables
    static Timer TIMER;
    static int TIME = 0;
    //Constructor
    public TimerLabel(){
        //Starting Label is 
        super("Time: 00:00");
        build();
    }
    
    //Methods
    private void build(){
        TIMER = new Timer(1000, this);
        TIMER.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TIME++;
        this.setText("Time: "+format(TIME / 60) + ":" + format(TIME % 60));
    }
    
    public static String format(int i) {
        String result = String.valueOf(i);
        if (result.length() == 1) {
            result = "0" + result;
        }
        return result;
    }
    
    public static void stopTimer(){
        TIMER.stop();
    }
    
    public static int getTime(){
        return TIME;
    }
}
