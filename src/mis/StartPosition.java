package mis;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

public class StartPosition {
	public static void centerOnScreen(Window window) {
	    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	    window.setLocation(
	      (d.width - window.getSize().width) / 2,
	      (d.height - window.getSize().height) / 2);
	  }

}
