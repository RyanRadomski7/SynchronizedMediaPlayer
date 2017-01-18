import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class FileChooser extends JFrame {
	private JButton parseButton;
	private TextField textBox;
	private StringContainer file;
	
	public FileChooser(StringContainer filename) {
		super("Name of video file");
		
		JPanel controlsPane = new JPanel();
		parseButton = new JButton("Select File");
		textBox = new TextField(20);
		file = filename;
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(200, 580, 450, 70);

		initControls();
		
		controlsPane.add(parseButton);
		controlsPane.add(textBox);
		add(controlsPane, BorderLayout.CENTER);
		
        setVisible(true);
	}
	
	private void initControls() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		parseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	file.string = textBox.getText();
            	dispose();
            }
        });
	}
}
