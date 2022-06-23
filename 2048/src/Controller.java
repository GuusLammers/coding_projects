import Model.Model;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller {
    
    Model model;
    View view;

    public Controller(Model model, View view) throws Exception {
        this.model = model;
        this.view = view;

        this.view.addKeyListener(new KeyArrowListener());

        updateView();
    }

    private void updateView() throws Exception {
        /*
        Updates the view with the current state of the model.
        */
        this.view.getMainPanel().updateBoard(this.model.getBoard());
    }

    public class KeyArrowListener extends KeyAdapter {
        /*
        A key pressed event listener that listens for arrow key inputs.
        */
        public void keyPressed(KeyEvent e) {
            try {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    model.shiftUp();
                }
                else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    model.shiftDown();
                }
                else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    model.shiftLeft();
                }   
                else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    model.shiftRight();
                } 

                updateView();
            } 
            catch(Exception e1) {
                e1.printStackTrace();
            }
        }
    }

}
