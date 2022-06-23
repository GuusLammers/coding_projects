import java.awt.Dimension;

import javax.swing.JFrame;

public class View extends JFrame {
    
    int WINDOW_WIDTH = 500;
    int WINDOW_HEIGHT = 500;

    MainPanel mainPanel;

    public View() {
        this.setTitle("2048"); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setResizable(true);

        this.mainPanel = new MainPanel();
        this.mainPanel.setPreferredSize(new Dimension(this.WINDOW_WIDTH, this.WINDOW_HEIGHT));
        this.add(this.mainPanel);
        this.pack();
    }

    public MainPanel getMainPanel() {
        return this.mainPanel;
    }

}
