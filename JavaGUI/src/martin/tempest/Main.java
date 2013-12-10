package martin.tempest;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import martin.tempest.core.TSDRLibrary;
import martin.tempest.core.exceptions.TSDRException;

public class Main implements TSDRLibrary.FrameReadyCallback {
	
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	
	private final ImageVisualizer viz = new ImageVisualizer();
	private JFrame frame;

	public static void main(String[] args) {
		try {
			new Main();
		} catch(Exception e) {
			System.err.println("Cannot load! Reason: "+e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	public Main() throws TSDRException {
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(viz, BorderLayout.CENTER);
		frame.setSize(WIDTH,HEIGHT);
		frame.setVisible(true);
		
		new Thread() {
			public void run() {
				try {
					TSDRLibrary sdrlib = new TSDRLibrary(TSDRLibrary.getAllSources()[0], WIDTH, HEIGHT);
					sdrlib.registerFrameReadyCallback(Main.this);
					sdrlib.startAsync();
				} catch (Throwable e) {e.printStackTrace();};
				
			};
		}.start();
		
	}

	@Override
	public void onFrameReady(TSDRLibrary lib, BufferedImage frame) {
		viz.drawImage(frame);
		try { lib.setSampleRate(0); } catch (Exception e) {};
	}

	@Override
	public void onException(TSDRLibrary lib, Exception e) {
		e.printStackTrace();
	}

	@Override
	public void onClosed(TSDRLibrary lib) {
		System.out.println("Closed");
	}

}
