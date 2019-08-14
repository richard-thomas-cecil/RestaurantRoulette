import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;


public class GraphicalInterface {

	private RestaurantSelector restaurants;
	private JFrame mainWindow;
	private int frameWidth;
	private int frameHeight;
	private int buttonWidth;
	private int buttonHeight;
	
	public GraphicalInterface(String fileName) throws Exception{
		this.restaurants = new RestaurantSelector(fileName);
		restaurants.initialize();
		mainWindow = new JFrame();
		frameWidth = 400;
		frameHeight = 500;
		buttonWidth = frameWidth/2;
		buttonHeight = 40;
		
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
		
	
	public void buildWindow() {
		
		JTextField field=new JTextField();
		JButton chooseButton;
		JButton addButton;
		JButton listButton;
		JButton removeButton;
		JButton closeButton;
		
		int textFieldWidth = 300;
		int textFieldHeight = 20;
		
		field.setBounds((frameWidth-textFieldWidth)/2,frameHeight/8,textFieldWidth,textFieldHeight);
				
		
		chooseButton = buildChooseButton(field);
		addButton = buildAddButton();
		listButton = buildListButton();
		removeButton = buildRemoveButton();
		closeButton = buildCloseButton();
		
		mainWindow.add(chooseButton);
		mainWindow.add(addButton);
		mainWindow.add(listButton);
		mainWindow.add(removeButton);
		mainWindow.add(closeButton);
		mainWindow.add(field);
		
		mainWindow.setSize(frameWidth,frameHeight);
		mainWindow.setLayout(null);
		mainWindow.setVisible(true);
	}
	
	private JButton buildChooseButton(JTextField displayField) {
		JButton button = new JButton();
		
		button.setBounds((frameWidth-buttonWidth)/2, frameHeight/4, buttonWidth, buttonHeight);
		button.setText("Choose Restaurant");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseRestaurant(displayField);
			}
		});
		
		return button;
	}
	
	private JButton buildAddButton() {
		JButton button = new JButton("Add Restaurant");
		
		button.setBounds((frameWidth-buttonWidth)/2, (frameHeight/4) + buttonHeight + 20, buttonWidth, buttonHeight);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newRestaurant = JOptionPane.showInputDialog(mainWindow, "Enter New Restaurant to Add");
				try {
					restaurants.addRestaurant(newRestaurant);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		return button;
	}
	
	private JButton buildListButton() {
		JButton button = new JButton("Show Restaurant List");
		
		button.setBounds((frameWidth-buttonWidth)/2, (frameHeight/4)+(buttonHeight*2)+(20*2), buttonWidth,buttonHeight);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listRestaurantDialogue();
			}
		});
		
		return button;
	}
	
	private void listRestaurantDialogue() {
		JDialog listDialogue = new JDialog(mainWindow, "Restaurant List", true);
		JTextArea restaurantList = new JTextArea(restaurants.getRestaurantNumber(), 1);
		JScrollPane scrollBar = new JScrollPane(restaurantList);
		JButton closeWindow = new JButton("OK");
		JPanel textAreaPanel = new JPanel();
		
		int dialogueWidth = 500;
		int dialogueHeight = 500;
		int listWidth = dialogueWidth - 100;
		int listHeight = dialogueHeight - 200;
		int dialogueButtonWidth = 200;
		
		textAreaPanel.setLayout(new FlowLayout());
		textAreaPanel.setBounds((dialogueWidth - listWidth)/2, 20, listWidth, listHeight);
		 
		listDialogue.setLayout(null);
		listDialogue.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		restaurantList.setEditable(false);
		
		scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollBar.setPreferredSize(new Dimension(listWidth,listHeight));
				
		
		for(int i = 0; i < restaurants.getRestaurantNumber();i++) {
			restaurantList.append(restaurants.getSelectedRestaurant(i) +"\r\n");
		}
		
		closeWindow.setBounds((dialogueWidth - dialogueButtonWidth)/2, listHeight + 50, dialogueButtonWidth,buttonHeight);
		
		closeWindow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listDialogue.dispose();
			}
		});
		
		textAreaPanel.add(scrollBar);
		
		listDialogue.add(textAreaPanel);
		listDialogue.add(closeWindow);
		
		listDialogue.setSize(dialogueWidth,dialogueHeight);
		listDialogue.setVisible(true);
		
	}
	
	private JButton buildRemoveButton() {
		JButton button = new JButton("Remove Restaurant");
		
		button.setBounds((frameWidth-buttonWidth)/2, (frameHeight/4)+(buttonHeight*3)+(20*3), buttonWidth,buttonHeight);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeRestaurant();
			}
		});
		
		return button;
	}
	
	private void removeRestaurant() {
		JDialog removeDialogue = new JDialog(mainWindow, "Remove Restaurant", true);
		JList<String> removeList;
		JPanel listPanel = new JPanel();
		JScrollPane scrollBar;
		JButton removeButton = new JButton("Remove");
		JButton cancelButton = new JButton("Cancel");
		
		String restaurantsAvailable[] = new String[restaurants.getRestaurantNumber()];
				
		int dialogueHeight = 500;
		int dialogueWidth = 500;
		int listWidth = dialogueWidth - 100;
		int listHeight = dialogueHeight - 200;
		int dialogueButtonWidth = 200;
		
		removeDialogue.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		for(int i = 0; i < restaurants.getRestaurantNumber(); i++)
			restaurantsAvailable[i] = restaurants.getSelectedRestaurant(i);
		
				
		listPanel.setLayout(new FlowLayout());
		listPanel.setBounds((dialogueWidth - listWidth)/2, 20, listWidth, listHeight);
		
		removeList = new JList<String>(restaurantsAvailable);
		removeList.setBounds((dialogueWidth - listWidth)/2, 20, listWidth, listHeight);
		
		scrollBar = new JScrollPane(removeList);
		scrollBar.getViewport().setView(removeList);
		scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		scrollBar.setPreferredSize(new Dimension(listWidth, listHeight));
		
		removeButton.setBounds((dialogueWidth - dialogueButtonWidth)/2, listHeight + 50, dialogueButtonWidth,buttonHeight);
		cancelButton.setBounds((dialogueWidth - dialogueButtonWidth)/2, listHeight + 100, dialogueButtonWidth,buttonHeight);
		
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isRemoved = false;
				if(removeList.getSelectedIndex() != -1) {
					try {
						isRemoved = restaurants.removeRestaurant(removeList.getSelectedValue());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(isRemoved)
						JOptionPane.showMessageDialog(removeList, "Restaurant Has Been Removed!");
					else
						JOptionPane.showMessageDialog(removeList, "Restaurant failed to Remove");
					removeDialogue.dispose();
				}
				else
					JOptionPane.showMessageDialog(removeList, "Please Select a Restaurant");
					
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeDialogue.dispose();
			}
		});
		
		listPanel.add(scrollBar);
		
		removeDialogue.add(listPanel);
		removeDialogue.add(removeButton);
		removeDialogue.add(cancelButton);
		
		removeDialogue.setLayout(null);
		removeDialogue.setSize(dialogueWidth,dialogueHeight);
		removeDialogue.setVisible(true);	
		
	}
	
	public JButton buildCloseButton() {
		JButton button = new JButton("Exit");
		
		button.setBounds((frameWidth-buttonWidth)/2, (frameHeight/4)+(buttonHeight*4)+(20*4), buttonWidth,buttonHeight);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		return button;
	}
	
	private void chooseRestaurant(JTextField text) {
		Random randomSelection = new Random();
		int selection = randomSelection.nextInt(restaurants.getRestaurantNumber());
		text.setText("You should eat at " + restaurants.getSelectedRestaurant(selection));
	}

}
