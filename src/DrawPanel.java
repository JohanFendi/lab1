package src;

import src.model.Position;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.
// Associates each pictureRoute with its graphical position.
public class DrawPanel extends JPanel{
    private static final String PICTURE_ROUTE_MISSING_ERROR = "PictureRouteError: Picture route not in DrawPanel.";
    private ArrayList<BufferedImage> images = new ArrayList<>();
    private ArrayList<Position> positions;
    private ArrayList<String> pictureRoutes;

    public void updatePicturePositions(ArrayList<Position> positions){
        this.positions = positions;
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, ArrayList<Position> positions, ArrayList<String> pictureRoutes, Color color) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(color);
        this.pictureRoutes = pictureRoutes;
        this.positions = positions;
        for (String pictureRoute : pictureRoutes){
            try{
                InputStream imgStream = DrawPanel.class.getResourceAsStream( pictureRoute);
                if (imgStream == null) {
                    throw new IllegalArgumentException("Image not found: " + pictureRoute);
                }
                this.images.add(ImageIO.read(imgStream));
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println(this.images.size() == this.positions.size());
        for (int i = 0; i < this.images.size(); i++){
            BufferedImage image = this.images.get(i);
            Position position = this.positions.get(i);
            g.drawImage(image, (int) position.getX(), (int) position.getY(), null); // see javadoc for more info on the parameters
        }
    }
}
