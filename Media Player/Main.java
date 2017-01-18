import javax.swing.SwingUtilities;

import uk.co.caprica.vlcj.discovery.NativeDiscovery;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		final StringContainer name = new StringContainer();
		new NativeDiscovery().discover();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	new FileChooser(name);
            }
        });
		
        //wait for name input without racking up CPU usage
		while(true)
			if(!name.string.equals(""))
				break;
			else
				Thread.sleep(100);

		//file chooser has exited by itself
		
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Media media = new Media(name.string);
                new Remote(media.getEmbeddedMediaPlayer());
            }
        });
	}
}
