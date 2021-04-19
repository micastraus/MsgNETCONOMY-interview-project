package view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.GenerateMapAction;

public class AppWindow extends JFrame {
	
	private JComboBox<String> outputTypeList;
	private JTextField fieldDriver;
	private JTextField fieldOutputFile;
	
	public AppWindow() {
		setTitle("Roads");
		setSize(350, 200);
		setResizable(false);
		
		initialise();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void initialise() {
		JPanel jp = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jp.setPreferredSize(new Dimension(280, 180));
		JLabel lblDriver = new JLabel("Driver ID");
		fieldDriver = new JTextField(30);
		
		JLabel lblOutputType = new JLabel("Output type");
		String[] types = {"TXT", "PNG"};
		outputTypeList = new JComboBox<String>(types);
		outputTypeList.setVisible(true);
		
		
		JLabel lblFileName = new JLabel("Output file name");
		fieldOutputFile = new JTextField(30);

		
		JPanel pnl1 = new JPanel();
		pnl1.setPreferredSize(new Dimension(180, 20));
		JPanel pnl2 = new JPanel();
		pnl1.setPreferredSize(new Dimension(180, 20));
		
		
		JButton btn = new JButton("Generate map");
		btn.addActionListener(new GenerateMapAction());
		jp.add(lblDriver);
		jp.add(fieldDriver);
		jp.add(lblOutputType);
		jp.add(outputTypeList);
		jp.add(pnl1);
		jp.add(lblFileName);
		jp.add(fieldOutputFile);
		jp.add(btn);
		
		add(jp);
	}
	
	public JComboBox<String> getListTypes() {
		return outputTypeList;
	}
	
	public JTextField getFieldDriver() {
		return fieldDriver;
	}
	
	public JTextField getFieldOutputFile() {
		return fieldOutputFile;
	}

}
