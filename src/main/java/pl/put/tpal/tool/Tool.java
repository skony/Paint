package pl.put.tpal.tool;

import java.io.IOException;
import java.util.Optional;
import javafx.scene.layout.FlowPane;
import pl.put.tpal.DrawOperation;
import pl.put.tpal.DrawParameters;

/**
 * @author Asus
 */
public interface Tool {
    
    public String getIconPath();
    
    public void setUp(FlowPane flowPane) throws IOException;
    
    public String getId();
    
    public Optional<DrawOperation> onMouseClicked(DrawParameters drawParameters);
    
    public Optional<DrawOperation> onMousePressed(DrawParameters drawParameters);
    
    public Optional<DrawOperation> onMouseDragged(DrawParameters drawParameters);
    
    public Optional<DrawOperation> onMouseReleased(DrawParameters drawParameters);
}
