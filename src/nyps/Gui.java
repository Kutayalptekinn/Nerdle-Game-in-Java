package nyps;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.JButton;
import java.lang.runtime.ObjectMethods;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import java.awt.Font;


public class Gui extends JFrame {
	String data;
	int satir;

	private JTextField textField_1;
	private JPanel contentPane;
	private JTextField istatistikler;
	private JLabel lblHosgeldiniz;
	private JTextField istatistik2;
	private JTextField istatistik3;
	private JTextField istatistik4;
	private JTextField istatistik5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Gui() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 824, 749);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnYeniOyun = new JButton("Yeni Oyun\r\n");
		btnYeniOyun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main main = new Main();
				String result=main.generateEquation();
				dispose();
				GameGui gui=new GameGui(result);
				gui.setVisible(true);
						
			}
		});
		btnYeniOyun.setBounds(155, 104, 469, 45);
		contentPane.add(btnYeniOyun);
		
		JButton btnDevamEt = new JButton("Devam Et");
		btnDevamEt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			     try {
			            File Obj = new File("dosyam.txt");
			            Scanner Reader = new Scanner(Obj);
			            data = Reader.nextLine();
		                System.out.println(data);
						GameGui gui=new GameGui(data);
						
			            dispose();
						
						satir=0;
			            while (Reader.hasNextLine()) {
			            	 data = Reader.nextLine();			            	 
						     gui.setBoxes(satir, data);
			            	 satir++;
			            }
			            Reader.close();
			            gui.setVisible(true);
			        }
			        catch (FileNotFoundException ed) {
			            System.out.println("An error has occurred.");
			            ed.printStackTrace();
			        }
				
			}
		});
		btnDevamEt.setBounds(155, 162, 469, 52);
		contentPane.add(btnDevamEt);
		
		JButton btnTest = new JButton("Test");
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Test test=new Test();
				test.setVisible(true);
				dispose();
			}
		});
		btnTest.setBounds(155, 223, 469, 45);
		contentPane.add(btnTest);
		
		lblHosgeldiniz = new JLabel("NÖRDIL");
		lblHosgeldiniz.setFont(new Font("Sitka Heading", Font.BOLD, 37));
		lblHosgeldiniz.setBounds(319, 37, 162, 57);
		contentPane.add(lblHosgeldiniz);
		
		JLabel lblIstatistikler = new JLabel("\u0130STAT\u0130ST\u0130KLER\r\n\r\n ");
		lblIstatistikler.setFont(new Font("Sitka Heading", Font.BOLD, 17));
		lblIstatistikler.setBounds(320, 278, 679, 57);
		contentPane.add(lblIstatistikler);
		
		textField_1 = new JTextField();
		textField_1.setText("Grup 12\r\nAhmet Enis \u015E\u0130M\u015E\u0130R 19011077\r\nKutay Alptekin 20011615");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField_1.setColumns(10);
		textField_1.setBounds(10, 634, 331, 35);
		contentPane.add(textField_1);
		
		istatistikler = new JTextField();
		istatistikler.setFont(new Font("Tahoma", Font.BOLD, 17));
		istatistikler.setEditable(false);
		istatistikler.setBounds(155, 345, 469, 45);
		contentPane.add(istatistikler);
		istatistikler.setColumns(10);
		
		istatistik2 = new JTextField();
		istatistik2.setFont(new Font("Tahoma", Font.BOLD, 17));
		istatistik2.setEditable(false);
		istatistik2.setColumns(10);
		istatistik2.setBounds(155, 400, 469, 45);
		contentPane.add(istatistik2);
		
		istatistik3 = new JTextField();
		istatistik3.setFont(new Font("Tahoma", Font.BOLD, 17));
		istatistik3.setEditable(false);
		istatistik3.setColumns(10);
		istatistik3.setBounds(155, 460, 469, 45);
		contentPane.add(istatistik3);
		
		istatistik4 = new JTextField();
		istatistik4.setFont(new Font("Tahoma", Font.BOLD, 17));
		istatistik4.setEditable(false);
		istatistik4.setColumns(10);
		istatistik4.setBounds(155, 515, 469, 45);
		contentPane.add(istatistik4);
		
		istatistik5 = new JTextField();
		istatistik5.setFont(new Font("Tahoma", Font.BOLD, 17));
		istatistik5.setEditable(false);
		istatistik5.setColumns(10);
		istatistik5.setBounds(155, 570, 469, 45);
		contentPane.add(istatistik5);
		try { //Burada dosyadan okuma yaparak istatistikleri ekrana yazdiriyoruz
            File Obj = new File("tamamlanan.txt");
            Scanner Reader = new Scanner(Obj);
            data = Reader.nextLine();
            Reader.close();
        }
        catch (FileNotFoundException ed) {
            data="0";
        }
		istatistikler.setText("Tamamlanan oyun sayisi: " + data);
		try { 
            File Obj = new File("kaybedilen.txt");
            Scanner Reader = new Scanner(Obj);
            data = Reader.nextLine();
            Reader.close();
        }
        catch (FileNotFoundException ed) {
            data="0";
        }
		istatistik2.setText("\nKaybedilen oyun sayisi: " + data);
		try {
            File Obj = new File("yaridaBirakilan.txt");
            Scanner Reader = new Scanner(Obj);
            data = Reader.nextLine();
            Reader.close();
        }
        catch (FileNotFoundException ed) {
            data="0";
        }
		istatistik3.setText("\nYarida birakilan oyun sayisi: " + data);
		try { 
            File Obj = new File("ortalamaSatir.txt");
            Scanner Reader = new Scanner(Obj);
            data = Reader.nextLine();
            Reader.close();
        }
        catch (FileNotFoundException ed) {
            data="tamamlanan oyun yok";
        }
		istatistik4.setText("\nOrtalama kaç satirda tamamlandi: " + data);
		try {
            File Obj = new File("ortalamaSure.txt");
            Scanner Reader = new Scanner(Obj);
            data = Reader.nextLine();
            Reader.close();
        }
        catch (FileNotFoundException ed) {
        	data="tamamlanan oyun yok";
        }
		istatistik5.setText("\nOrtalama ne kadar sürede tamamlandi: " + data);
      }
}
