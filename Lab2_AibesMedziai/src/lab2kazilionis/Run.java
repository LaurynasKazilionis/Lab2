
package lab2kazilionis;

import java.util.Locale;
import javafx.application.Application;
import javafx.stage.Stage;
import laborai.demo.VykdymoModulisFX;
import laborai.gui.fx.Lab2WindowFX;

public class Run extends Application 
{
    public static void main(String [] args) {
        VykdymoModulisFX.launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Locale.setDefault(Locale.US); 
        Lab2WindowFX.createAndShowFXGUI(primaryStage);
    }
}
