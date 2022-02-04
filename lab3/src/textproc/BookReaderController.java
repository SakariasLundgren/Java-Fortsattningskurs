package textproc;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class BookReaderController {
	
	public BookReaderController (GeneralWordCounter counter) {
		SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 100, 300));
	}
	
	private void createWindow(GeneralWordCounter counter, String title, int width, int height) {
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = frame.getContentPane();
		
		//Rullista
		SortedListModel<Map.Entry<String, Integer>> sortedList = new SortedListModel(counter.getWordList());
		JList list = new JList(sortedList);
		JScrollPane scrollPane = new JScrollPane(list);		
		
		//Skapa "Alphabetic" knapp
		JRadioButton button1 = new JRadioButton ("Alphabetic");
		button1.addActionListener(event -> 
			sortedList.sort((m1, m2) -> m1.getKey().compareTo(m2.getKey())));
		
		//Skapa "Frequency" knapp
		JRadioButton button2 = new JRadioButton ("Frequency");
		button2.addActionListener(event -> 
			sortedList.sort((m1, m2) -> m2.getValue().compareTo(m1.getValue())));
		JPanel buttonPanel = new JPanel();
		ButtonGroup buttonG = new ButtonGroup();
		buttonG.add(button1);
		buttonG.add(button2);
		buttonPanel.add(button1);
		buttonPanel.add(button2);
		
		//Skapa sökruta
		JButton search = new JButton("Search");
		JTextField searchField = new JTextField();
		search.addActionListener(event -> {
			String input = searchField.getText().trim().toLowerCase(); //Valbara V1
				if(counter.map.containsKey(input)) {
					for(int i = 0; i<sortedList.getSize(); i++) {
						if(input.equals(sortedList.getElementAt(i).getKey())) {
							list.ensureIndexIsVisible(i);
							list.setSelectedIndex(i);
							break;
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(pane, "Word not found"); //Valbar uppgift V2
				}
		});
		searchField.setPreferredSize(new Dimension (170, 20));
		buttonPanel.add(searchField);
		buttonPanel.add(search);
		
		//Lägga till alla funktioner till 
		pane.add(scrollPane, BorderLayout.CENTER);
		pane.add(buttonPanel, BorderLayout.SOUTH);
		
		scrollPane.setPreferredSize(new Dimension(500, 600));
		frame.pack();
		frame.setVisible(true);
	}
}
