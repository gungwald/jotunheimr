package lanterna;
import java.io.IOException;
import java.util.Arrays;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.DefaultWindowManager;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.TextBox.Style;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class Jotunheimr {
	private Terminal terminal;
	private Screen screen;
	
	public Jotunheimr() throws IOException {
		terminal = new DefaultTerminalFactory().createTerminal();
		screen = new TerminalScreen(terminal);
		screen.startScreen();

		// Create window to hold the panel
		BasicWindow window = new BasicWindow();
		window.setHints(Arrays.asList(Window.Hint.FULL_SCREEN, Window.Hint.NO_DECORATIONS));
		
		Button openButton = new Button("Open");
		Button saveButton = new Button("Save");
		
		TextBox textBox = new TextBox(new TerminalSize(80,24),Style.MULTI_LINE);
		
		Panel buttonPanel = new Panel(new LinearLayout(Direction.HORIZONTAL));
		buttonPanel.addComponent(openButton);
		buttonPanel.addComponent(saveButton);
		
		Panel mainPanel = new Panel(new LinearLayout(Direction.VERTICAL));
		mainPanel.addComponent(buttonPanel);
		mainPanel.addComponent(textBox);
		
		window.setComponent(mainPanel);
		
		// Create gui and start gui
		MultiWindowTextGUI gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLUE));
		gui.addWindowAndWait(window);
	}
	
    public static void main(String[] args) {
    	try {
			@SuppressWarnings("unused")
			Jotunheimr j = new Jotunheimr();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
}
