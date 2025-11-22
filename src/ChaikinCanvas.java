import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class ChaikinCanvas extends JPanel {

    Timer chaikinTimer;

    boolean drawLines = false;       
    boolean clickActive = true;      
    boolean show1PointText = false;  
    boolean runAnimation = false;   

    int currentStep = 1;
    int steps = 7; 

    ArrayList<Point> points = new ArrayList<>();
    ArrayList<Point> pointsOrigin = new ArrayList<>();

    public ChaikinCanvas() {
        setBackground(Color.BLACK);
        setFocusable(true);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (clickActive) {
                    pointsOrigin.add(new Point(e.getX(), e.getY()));
                    points.add(new Point(e.getX(), e.getY()));
                    repaint();
                }
            }
        });

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                    if (!runAnimation) {

                        if (points.size() == 1) {
                            show1PointText = true;

                        } else if (points.size() == 2) {
                            drawLines = true;
                            show1PointText = true;
                            clickActive = false;

                        } else if (points.size() > 2) {
                            show1PointText = false;
                            drawLines = true;
                            clickActive = false;
                            runAnimation = true;
                            startAnimation();
                        }
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    drawLines = false;
                    clickActive = true;
                    show1PointText = false;
                    runAnimation = false;
                    points.clear();
                    pointsOrigin.clear();
                }

                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }

                repaint();
            }
        });
    }

    public void startAnimation() {
        runAnimation = true;

        chaikinTimer = new Timer(1000, e -> {

            if (currentStep < steps) {
                points = (ArrayList<Point>) Chaikin.algo(points, 1);
                currentStep++;
                repaint();

            } else {
                currentStep = 0;
                points = (ArrayList<Point>) pointsOrigin.clone();

                if (!runAnimation) {
                    chaikinTimer.stop();
                }
            }
        });

        chaikinTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        int pointSize = 6;

        if (points.size() > 0) {
            final Point firstPoint = points.get(0);
            g.fillOval(firstPoint.x - 3, firstPoint.y - 3, pointSize, pointSize);
        }

        if (points.size() >= 2) {
            final Point lastPoint = points.get(points.size() - 1);
            g.fillOval(lastPoint.x - 3, lastPoint.y - 3, pointSize, pointSize);
        }

        for (Point p : points) {
            if (!drawLines) {
                g.drawOval(p.x - 3, p.y - 3, 6, 6);
            }
        }

        if (show1PointText) {
            g.drawString("You must create minimum 3 points to run Chaikin algorithm.", 20, 20);
            g.drawString("Press <Space> to restart.", 20, 40);
        }

        if (runAnimation) {
            g.drawString(String.format("Step number: %s", currentStep), 20, 20);

            for (int i = 0; i < points.size() - 1; i++) {
                final Point p1 = points.get(i);
                final Point p2 = points.get(i + 1);
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
            }

        } else {

            if (drawLines) {
                for (int i = 0; i < points.size() - 1; i++) {
                    final Point p1 = points.get(i);
                    final Point p2 = points.get(i + 1);
                    g.drawLine(p1.x, p1.y, p2.x, p2.y);
                }
            }
        }
    }
}
