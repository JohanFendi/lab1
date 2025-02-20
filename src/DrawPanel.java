package src;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    // Just a single image, TODO: Generalize -- Med en annan klass? en lista?
    BufferedImage volvoImage;
    BufferedImage saabImage;
    BufferedImage scaniaImage;

    // To keep track of a single car's position
    Point volvoPoint = new Point();
    Point saabPoint = new Point();
    Point scaniaPoint = new Point();


    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(300,300);




    void moveit(Vehicle car, int x, int y){
        if (car instanceof Volvo240) {
            volvoPoint.x = x;
            volvoPoint.y = y + 300;
        }
        else if (car instanceof Saab95) {
            saabPoint.x = x;
            saabPoint.y = y + 100;
        }
        else if (car instanceof Scania) {
            scaniaPoint.x = x;
            scaniaPoint.y = y + 200;
        }
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message if case file is not found with a try/catch block
        try {
            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
            volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
            saabImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
            scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));

        } catch (IOException ex)
        {
            System.err.println("Picture not found)");
            ex.printStackTrace();
        }

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);

        g.drawImage(scaniaImage, scaniaPoint.x, scaniaPoint.y, null);
        g.drawImage(saabImage, saabPoint.x, saabPoint.y, null);
        g.drawImage(volvoImage, volvoPoint.x, volvoPoint.y, null); // see javadoc for more info on the parameters
    }
}
