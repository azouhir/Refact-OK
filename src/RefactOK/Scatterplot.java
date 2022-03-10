package RefactOK;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Scatterplot extends JFrame {

    public Scatterplot() {
        super("Scatterplot");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        List points = WeMovin.getXY();

        JPanel panel = new JPanel() {
                @Override
                public void paintComponent(Graphics g) {
                    //g.translate(0, 0);
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g.create();
//                    g.setColor(Color.RED);
//                    g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
                    g.setColor(Color.BLACK);
                    for (Iterator i = points.iterator(); i.hasNext();) {
                        Point2D.Float pt = (Point2D.Float) i.next();
                        Ellipse2D dot = new Ellipse2D.Float(pt.x - 1, pt.y - 1, 2, 2);
                        g2d.fill(dot);
                    }
                    g2d.dispose();
                }
            };

            setContentPane(panel);
            setBounds(10, 10, 200, 200);

            setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                new Scatterplot();
            }
        });
    }
}