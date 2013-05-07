package view.menu;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
/**
 * @author Vidar Eriksson
 *
 */
@SuppressWarnings("serial")
public class LoadingScreen extends JPanel{
	private JProgressBar progressBar = new JProgressBar();
	public LoadingScreen(int maxValue) {
		
		JLabel lblLoading = new JLabel("Loading...");
		add(lblLoading);
		
		progressBar.setMaximum(maxValue);
		add(progressBar);
	}
	public void increase(){
		progressBar.setValue(progressBar.getValue()+1);
	}

}
