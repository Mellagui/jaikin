import javax.swing.*;

public class JaikinWindow {
    
    public JaikinWindow() {
        final JFrame frame = new JFrame("Jaikin - Chaikin's Algorithm");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setResizable(false);

        frame.setSize(800, 600);

        final ChaikinCanvas canvas = new ChaikinCanvas();
    
        frame.add(canvas);
        
        frame.setVisible(true);
    }
}
