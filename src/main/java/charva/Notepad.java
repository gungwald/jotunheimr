package charva;

import charvax.swing.*;
import charva.awt.*;
import charva.awt.event.*;
import java.util.Scanner;
import java.io.*;

public class Notepad extends JFrame implements ActionListener {
	private JTextArea textArea = new JTextArea("Stuff");
	private JMenuBar menuBar = new JMenuBar();
	private JMenu file = new JMenu("File");
	private JMenuItem openFile = new JMenuItem("Open");
	private JMenuItem saveFile = new JMenuItem("Save");
	private JMenuItem close = new JMenuItem("Close");

	public Notepad() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(textArea, BorderLayout.CENTER);
		this.setJMenuBar(menuBar);
		this.menuBar.add(file);

		this.openFile.addActionListener(this);
		this.openFile.setMnemonic(KeyEvent.VK_F2);
		this.file.add(openFile);

		this.saveFile.addActionListener(this);
		this.saveFile.setMnemonic(KeyEvent.VK_F3);
		this.file.add(saveFile);

		this.close.setMnemonic(KeyEvent.VK_F4);
		this.close.addActionListener(this);
		this.file.add(this.close);
	}

	public void actionPerformed(ActionEvent e) {
		// if the source of the event was our "close" option
		if (e.getSource() == this.close) {
			this.setVisible(false);
			System.exit(0);
		}

		// if the source was the "open" option
		else if (e.getSource() == this.openFile) {
			JFileChooser open = new JFileChooser(); // open up a file chooser (a
													// dialog for the user to
													// browse files to open)
			int option = open.showOpenDialog(this); // get the option that the
													// user selected (approve or
													// cancel)
			// NOTE: because we are OPENing a file, we call showOpenDialog~
			// if the user clicked OK, we have "APPROVE_OPTION"
			// so we want to open the file
			if (option == JFileChooser.APPROVE_OPTION) {
				this.textArea.setText(""); // clear the TextArea before applying
											// the file contents
				try {
					// create a scanner to read the file
					// (getSelectedFile().getPath() will get the path to the
					// file)
					Scanner scan = new Scanner(new FileReader(open
							.getSelectedFile().getPath()));
					while (scan.hasNext())
						// while there's still something to read
						this.textArea.append(scan.nextLine() + "\n"); // append
																		// the
																		// line
																		// to
																		// the
																		// TextArea
				} catch (Exception ex) { // catch any exceptions, and...
					// ...write to the debug console
					System.out.println(ex.getMessage());
				}
			}
		}

		// and lastly, if the source of the event was the "save" option
		else if (e.getSource() == this.saveFile) {
			JFileChooser save = new JFileChooser(); // again, open a file
													// chooser
			int option = save.showSaveDialog(this); // similar to the open file,
													// only this time we call
			// showSaveDialog instead of showOpenDialog
			// if the user clicked OK (and not cancel)
			if (option == JFileChooser.APPROVE_OPTION) {
				try {
					// create a buffered writer to write to a file
					BufferedWriter out = new BufferedWriter(new FileWriter(save
							.getSelectedFile().getPath()));
					out.write(this.textArea.getText()); // write the contents of
														// the TextArea to the
														// file
					out.close(); // close the file stream
				} catch (Exception ex) { // again, catch any exceptions and...
					// ...write to the debug console
					System.out.println(ex.getMessage());
				}
			}
		}
	}

	// the main method, for actually creating our notepad and setting it to
	// visible.
	public static void main(String args[]) {
		Notepad app = new Notepad();
		app.setVisible(true);
	}
}