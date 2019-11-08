package exp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class frame extends JFrame {
	public frame()
	{
		setTitle("Apache Solr RCE made by tusk1");
		setLayout(null);
		setBounds(300, 300, 700, 500);
		Container c = getContentPane();
		
		JLabel url_name = new JLabel("URL¬∑æ∂:");
		JLabel shell_name = new JLabel("√¸¡Ó:");
		JTextField url_text = new JTextField("");
		JTextField shell_text = new JTextField("");
		JButton check = new JButton("≈‰÷√ºÏ≤‚");
		JButton command = new JButton("÷¥––√¸¡Ó");
		JTextArea area = new  JTextArea("");
		
		url_name.setBounds(20, 20, 60, 40);
		url_text.setBounds(80, 25, 400, 25);
		check.setBounds(490, 25, 90, 25);
		
		shell_name.setBounds(30, 50, 60, 40);
		shell_text.setBounds(80, 55, 400, 25);
		command.setBounds(490, 55, 90, 25);
		
		//area.setBounds(20, 100, 530, 200);
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		JScrollPane js=new JScrollPane(area);
		js.setBounds(20, 100, 570, 300);
		//js.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		
		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				area.setText(config_check(url_text.getText()));
			}
		});
		
		command.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				area.setText(exp_command(url_text.getText(), shell_text.getText()));
			}
		});
		
		c.add(url_name);
		c.add(url_text);
		c.add(check);
		c.add(shell_name);
		c.add(shell_text);
		c.add(command);
		//c.add(area);
		c.add(js);
		
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public String config_check(String target)
	{
		String re1 = new payload().setConfig(target);
		String re2 = new payload().getShell(target, "id");
		String re = re1 + "\n=====================================================\n" + re2;
		return re;
	}
	
	public String exp_command(String target, String command)
	{
		String re = new payload().getShell(target, command);
		return re;
	}

	
	public static void main(String args[])
	{
		new frame();
	}

}
