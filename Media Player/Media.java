import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.windows.Win32FullScreenStrategy;

@SuppressWarnings("serial")
public class Media extends JFrame {
	final EmbeddedMediaPlayer emp;
	final MediaPlayerFactory mpf;
	
	public Media(String filename) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		mpf = new MediaPlayerFactory();
		emp = mpf.newEmbeddedMediaPlayer(new Win32FullScreenStrategy(this));
		emp.playMedia(filename);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				emp.release();
				System.exit(0);
			}
		});
	}
	
	public EmbeddedMediaPlayer getEmbeddedMediaPlayer() {
		return emp;
	}
}
