import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ChaikinDemo extends JPanel implements MouseListener, KeyListener {

    private final List<int[]> points = new ArrayList<>();
    private List<int[]> tmpPoints = new ArrayList<>();
    private boolean started = false;
    private boolean showMessage = false;
    private int step = 0;
    private long lastUpdateTime = System.nanoTime();

    public ChaikinDemo() {
        setBackground(Color.BLACK);
        addMouseListener(this);
        setFocusable(true);
        addKeyListener(this);

        // Animation timer (~60 FPS)
        Timer timer = new Timer(16, e -> {
            updateLogic();
            repaint();
        });
        timer.start();
    }

    private void updateLogic() {
        if (started && points.size() > 1) {
            long now = System.nanoTime();
            // 0.5 seconds = 500,000,000 ns
            if (step < 7 && now - lastUpdateTime > 500_000_000L) {
                // Use your Chaikin algo with the current step count
                tmpPoints = Chaikin.algo(points, step);
                step++;
                lastUpdateTime = now;
            }

            if (step >= 7) {
                tmpPoints = new ArrayList<>(points);
                step = 0;
                lastUpdateTime = now;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw points (RED)
        g2.setColor(Color.RED);
        for (int[] p : points) {
            int r = 2;  // radius
            int x = p[0];
            int y = p[1];
            g2.drawOval(x - r, y - r, 2 * r, 2 * r);
        }

        // Message if no points and Enter pressed
        if (showMessage) {
            String text = "click on the mouse to draw the point!!!";
            g2.setColor(Color.WHITE);
            Font font = g2.getFont().deriveFont(30f);
            g2.setFont(font);
            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(text);
            int x = (getWidth() - textWidth) / 2;
            int y = getHeight() / 2;
            g2.drawString(text, x, y);
        } else if (started && points.size() > 1) {
            // Draw Chaikin lines (GREEN)
            g2.setColor(Color.GREEN);
            List<int[]> toDraw = tmpPoints;
            for (int i = 0; i < toDraw.size() - 1; i++) {
                int[] p1 = toDraw.get(i);
                int[] p2 = toDraw.get(i + 1);
                g2.drawLine(p1[0], p1[1], p2[0], p2[1]);
            }
        }

        g2.dispose();
    }

    // MouseListener
    @Override
    public void mouseClicked(MouseEvent e) {
        // Left click and not started: add point
        if (SwingUtilities.isLeftMouseButton(e) && !started) {
            points.add(new int[]{e.getX(), e.getY()});
            showMessage = false;
            repaint();
        }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}

    // KeyListener
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }

        if (code == KeyEvent.VK_C) {
            started = false;
            points.clear();
            tmpPoints.clear();
            step = 0;
            showMessage = false;
            repaint();
        }

        if (code == KeyEvent.VK_ENTER) {
            if (points.isEmpty()) {
                showMessage = true;
            } else if (points.size() > 1) {
                started = true;
                showMessage = false;
                // Reset animation state
                step = 0;
                lastUpdateTime = System.nanoTime();
            }
            repaint();
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}

    // Main entry point
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame(
                "CHAIKIN => Left Click -> Add Point | Enter -> Animate | Esc -> Quit | C -> restart"
            );
            ChaikinDemo panel = new ChaikinDemo();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(panel);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            panel.requestFocusInWindow();
        });
    }
}
