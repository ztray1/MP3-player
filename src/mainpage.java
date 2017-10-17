import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.Color;

public class mainpage extends JFrame {

	private JPanel contentPane;
	int returnValue;
	JFileChooser browser= new JFileChooser();
	FileNameExtensionFilter filter= new FileNameExtensionFilter("WAV Sound","wav");
	String[] musics= new String[10];
	int index =0;
	File selectedFile;
	File sound;
	AudioInputStream ais;
	Clip clip;
	FloatControl gainControl;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainpage frame = new mainpage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mainpage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JButton btnvolume = new JButton("+");
		btnvolume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					     gainControl.setValue(+10.0f);
			}
		});
		btnvolume.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnvolume.setBounds(10, 47, 58, 27);
		contentPane.add(btnvolume);
		
		
		JComboBox list = new JComboBox();
		list.setBackground(Color.WHITE);
		list.setBounds(0, 0, 432, 24);
		contentPane.add(list);
		browser.setFileFilter(filter);
		
		JButton btnadd = new JButton("add song");
		btnadd.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnValue = browser.showOpenDialog(null);
				if (returnValue==browser.APPROVE_OPTION)
				{
					selectedFile = browser.getSelectedFile();
					musics[index]=selectedFile.toString();
					//JOptionPane.showMessageDialog(null,selectedFile.toString());
					list.addItem("song-"+ index);
					index++;
				}
			}
		});
		btnadd.setBounds(14, 104, 113, 27);
		contentPane.add(btnadd);
		
		JButton btnPlay = new JButton("play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if (list.getSelectedIndex()==0)
					{
						sound= new File (musics[list.getSelectedIndex()]);
						ais = AudioSystem.getAudioInputStream(sound);
						clip = AudioSystem.getClip();
						clip.open(ais);
						clip.start();
					}
					if (list.getSelectedIndex()==1)
					{
						sound= new File (musics[list.getSelectedIndex()]);
						ais = AudioSystem.getAudioInputStream(sound);
						clip = AudioSystem.getClip();
						clip.open(ais);
						clip.start();
					}
				}
				catch(Exception ae){
					JOptionPane.showMessageDialog(null, ae);
				}
				
			}
		});
		btnPlay.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnPlay.setBounds(14, 158, 113, 27);
		contentPane.add(btnPlay);
		
		JButton btnStop = new JButton("pause");
		btnStop.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clip.stop();
				
			}
		});
		btnStop.setBounds(156, 47, 113, 27);
		contentPane.add(btnStop);
		
		JButton btnNextSong = new JButton(">>");
		btnNextSong.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNextSong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if (list.getSelectedIndex()==0)
					{
					
						clip.stop();
						sound= new File (musics[list.getSelectedIndex()+1]);
						ais = AudioSystem.getAudioInputStream(sound);
						clip = AudioSystem.getClip();
						clip.open(ais);
						clip.start();
					}
				}
				catch(Exception ae){
					JOptionPane.showMessageDialog(null, ae);
				}
				
			}
		});
		btnNextSong.setBounds(156, 104, 113, 27);
		contentPane.add(btnNextSong);
		
		JButton btnBeforeSong = new JButton("<<");
		btnBeforeSong.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBeforeSong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if (list.getSelectedIndex()!=0)
					{
					
						clip.stop();
						sound= new File (musics[list.getSelectedIndex()-1]);
						ais = AudioSystem.getAudioInputStream(sound);
						clip = AudioSystem.getClip();
						clip.open(ais);
						clip.start();
					}
				}
				catch(Exception ae){
					JOptionPane.showMessageDialog(null, ae);
				}
				
			}
		});
		btnBeforeSong.setBounds(156, 158, 113, 27);
		contentPane.add(btnBeforeSong);
		
		JButton btnNewButton = new JButton("-");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			     gainControl.setValue(-10.0f);
			}
		});
		btnNewButton.setBounds(82, 48, 60, 27);
		contentPane.add(btnNewButton);
		
		
	}
}
