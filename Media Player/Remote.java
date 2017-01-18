import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

@SuppressWarnings("serial")
public class Remote extends JFrame {
	private JLabel vidTime;
	private JButton pauseButton;
	private JButton parseButton;
	private JButton fsButton;
	private TextField textBox;
	private EmbeddedMediaPlayer mediaPlayer;
	private TimeUpdater tu;
	
	public Remote(EmbeddedMediaPlayer emp) {
		super("Remote ( ͡° ͜ʖ ͡°)");
		
		JPanel controlsPane = new JPanel();
		pauseButton = new JButton("Pause");
		parseButton = new JButton("Sync to");
		vidTime = new JLabel("goo");
		fsButton = new JButton("Fullscreen");
		textBox = new TextField(20);
		mediaPlayer = emp;
		tu = new TimeUpdater(vidTime, mediaPlayer);
		
		setBounds(200, 580, 450, 70);

		initControls(mediaPlayer);
		tu.start();
		
		controlsPane.add(vidTime);
		controlsPane.add(fsButton);
		controlsPane.add(pauseButton);
		controlsPane.add(parseButton);
		controlsPane.add(textBox);
		add(controlsPane, BorderLayout.WEST);
		
        setVisible(true);
	}
	
	private void initControls(final EmbeddedMediaPlayer emp) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				emp.release();
				System.exit(0);
			}
		});
		
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(emp.isPlaying()) {
            		emp.pause();
            		pauseButton.setText("Play");
            	} else {
            		emp.play();
            		pauseButton.setText("Pause");
            	}
            }
        });
        
        parseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	emp.pause();
            	emp.setTime(Integer.parseInt(textBox.getText()) * 60 * 1000);
            	textBox.setText("");
            	new TimeServer().waitOnMinute();
            	emp.play();
            }
        });
        
        fsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	emp.setFullScreen(!emp.isFullScreen());
            }
        });
	}
}
