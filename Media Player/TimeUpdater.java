import javax.swing.JLabel;

import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

public class TimeUpdater extends Thread {
	private JLabel textBox;
	private EmbeddedMediaPlayer mediaPlayer;
	
	public TimeUpdater(JLabel textBox, EmbeddedMediaPlayer mediaPlayer) {
		this.textBox = textBox;
		this.mediaPlayer = mediaPlayer;
	}
	
	public void run() {
		try {
			while(true) {
				textBox.setText(Long.toString(mediaPlayer.getTime()/1000/60));
				Thread.sleep(1000);
			}
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
