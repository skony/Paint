package pl.put.tpal.tool;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

/**
 * @author Asus
 */
public abstract class AbstractTool implements Tool {
    
    @Override
    public String getId() {
        return this.getClass().getSimpleName();
    }
    
    @Override
    public void setUp(FlowPane flowPane) throws IOException {
        URL url = getClass().getClassLoader().getResource(getIconPath());
        BufferedImage awtImg = ImageIO.read(url);
        // File icon = new File(getClass().getClassLoader().getResource(getIconPath()).getFile());
        // ImageView imageView = new ImageView(icon.toURI().toString());
        Image fxImg = SwingFXUtils.toFXImage(awtImg, null);
        ImageView imageView = new ImageView(fxImg);
        imageView.setId(this.getClass().getSimpleName());
        flowPane.getChildren().add(imageView);
    }
}
