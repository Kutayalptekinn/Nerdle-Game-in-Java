package nyps;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.Timer;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;
import java.awt.GridLayout;

public class GameGui extends JFrame implements ActionListener {

	private JTextField textField_1;
	String[] operator;
	Timer swingtimer;
	JLabel tLabel;
	JButton[][] boxes;
	String data;
	String resu;
	int ind,nmb,sonuc,length;
	int[] sayi;
	int tmp=-1;
    int ten=1; 
    int i =0,j=0,q=0,r=0,u=0,n=0,full=0,start=0,count=0,m=0;
	Stack<Integer> stack;
	private static final long serialVersionUID = -8007433787283514059L;
	private JPanel contentPane;

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					GameGui frame = new GameGui("");
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

	@SuppressWarnings({ "deprecation", "static-access", "static-access" })
	public GameGui(String res) {
 
       this.stack= new Stack<Integer>();
		this.length=res.length();
		sayi=new int[30];
		operator=new String[50];
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 832, 695);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 818, 425);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(6, 5, length, 5));
		
		boxes= new JButton[6][length];
		
		for(q=0;q<6;q++) {
			for(r=0;r<length;r++) {
				boxes[q][r] = new JButton();
				boxes[q][r].setOpaque(true);
				boxes[q][r].setBackground(Color.GRAY);
				boxes[q][r].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			for (int x = 0; x < 6; x++) {
	            for (int y = 0; y < length; y++) {
	                if (source.equals(boxes[x][y])) {
	                   u=x;
	                   j=y;
	                }
	            }
	        }
			}
		});
				panel.add(boxes[q][r]);
			}
		}
		
		tLabel=new JLabel(" ");
		tLabel.setBounds(330, 390, 140, 140);
		getContentPane().add(tLabel);		
		swingtimer = new Timer(1000,this);
		swingtimer.start();		

		contentPane.add(tLabel);
		
		 JButton btnNewButton_15 = new JButton("Tahmin Et");
		btnNewButton_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //tahmin et'e tikladigimizda bazi kontroller yapmamiz gerekiyor
            stack.clear();
            operator[0]=operator[1]=operator[2]=null;
            m=0;
		            if(full==1) //butun sutunlar dolu degilse tahmini degerlendirmiyoruz
		            {	
		            	for (int t = 0; t < length; t++) { //burada rakamlari birlestirerek sayilar elde ediyoruz
		            		String str=boxes[i][t].getText();
		            		if(str=="0" || str=="1" || str=="2" || str=="3" || str=="4" || str=="5" || str=="6" || str=="7" || str=="8" || str=="9")
		            		    stack.push(Integer.parseInt(str));
		            	    else if((str=="+" || str=="-" || str=="*" || str=="/" || str=="="))
		            	    {
		            	    	operator[m]=str;
		            	    	m++;
		            	    	if(!stack.isEmpty())
		            	    	{
			            	    	
			            	    	sayi[n]=0;
			            	    	while(!stack.isEmpty())
			            	    	{	
			            	    	    nmb=stack.pop();
			            	    	    nmb=nmb*ten;
			            	    	    sayi[n]=sayi[n]+nmb;	    
			            	    	    ten=ten*10;
			            	    	}
		            	    	    n++;
			            	    	ten=1; 
		            	    	}
		            	    	else
		            	    	{
		            	    		sayi[0]=sayi[1]=0;
		            	    		JOptionPane.showMessageDialog(null, "Hatali hesaplama yaptiniz.");
		            	    		return;
		            	    	}
   	
		            	    }
		            	}
            	    	while(!stack.isEmpty())
            	    	{
            	    	    nmb=stack.pop();
            	    	    nmb=nmb*ten;
            	    	    sayi[n]=sayi[n]+nmb;	    
            	    	    ten=ten*10;
            	    	}
            	    	ten=1;
            	    	
            	    	if(operator[0]==null)
            	    	{
            	    		JOptionPane.showMessageDialog(null, "Hatali hesaplama yaptiniz.");
            	    		sayi[0]=sayi[1]=0;
            	    	}
            	    	else if(operator[0].equals("+"))
            	    	{
            	    		if(m==3) sonuc=sayi[n-2]+sayi[n-3]; //m degiskeni denklemdeki operand sayisini gosteriyor
            	    		if(m==2) sonuc=sayi[n-1]+sayi[n-2];
            	    		count=0;
            				if(sayi[n]==sonuc && m!=3){
            					try {
									denklemKontrol(); //bu fonksiyonda renk atamalari yapiliyor
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} 
            				}
            	    		else if(m!=3){
            	    			m=0;
            	    			full=0;
            	    			JOptionPane.showMessageDialog(null, "Hatali hesaplama yaptiniz.");
                	    		sayi[0]=sayi[1]=0;
            	    		}
            	    	}
            	    	else if(operator[0].equals("-"))
            	    	{
            	    		if(m==3) sonuc=sayi[n-3]-sayi[n-2];
            	    		if(m==2) sonuc=sayi[n-2]-sayi[n-1];
            	    		count=0;
            				if(sayi[n]==sonuc && m!=3){
            					try {
									denklemKontrol();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
            				}
            	    		else if(m!=3){
            	    			m=0;
            	    			full=0;
            	    			JOptionPane.showMessageDialog(null, "Hatali hesaplama yaptiniz.");
                	    		sayi[0]=sayi[1]=0;
            	    		}
            	    	}
            	    	else if(operator[0].equals("/"))
            	    	{
              				if(sayi[n-1]!=0 && sayi[n-2]%sayi[n-1]==0)
                	    		if(m==3) sonuc=sayi[n-3]/sayi[n-2];
                	    		if(m==2) sonuc=sayi[n-2]/sayi[n-1];
                	    		count=0;
                				if(sayi[n]==sonuc && m!=3){
                					try {
										denklemKontrol();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
                				}
                	    		else if(m!=3){
                	    			m=0;
                	    			full=0;
                	    			JOptionPane.showMessageDialog(null, "Hatali hesaplama yaptiniz.");
                    	    		sayi[0]=sayi[1]=0;
                	    		}
            	    	}
            	    	else if(operator[0].equals("*"))
            	    	{
            	    		if(m==3) sonuc=sayi[n-3]*sayi[n-2];
            	    		if(m==2) sonuc=sayi[n-2]*sayi[n-1];
            	    		count=0;
            				if(sayi[n]==sonuc && m!=3){
            					try {
									denklemKontrol();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
            				}
            	    		else if(m!=3){
            	    			m=0;
            	    			full=0;
            	    			JOptionPane.showMessageDialog(null, "Hatali hesaplama yaptiniz.");
                	    		sayi[0]=sayi[1]=0;
            	    		}
            	    	}
            	    	
            	    	else if(operator[0].equals("="))
            	    	{
            	    		if(sayi[n]==sayi[n-1] && m!=3)
                	    	{
            	    			count=0;
            	    			try {
									denklemKontrol();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
                	    	}
            	    		else if(m!=3)
            	    		{
            	    			JOptionPane.showMessageDialog(null, "Hatali hesaplama yaptiniz.");
                	    		sayi[0]=sayi[1]=0;

            	    		}
            	    	}
            	    	
            	    	if(m==3)
            	    	{
              				if(operator[1]=="+") 
              				{
              					sonuc=sonuc+sayi[n-1];
              					count=0;
              					if(sonuc==sayi[n]){
              						try {
										denklemKontrol();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
              					}
              		    		else
              		    		{
              		    			JOptionPane.showMessageDialog(null, "Hatali hesaplama yaptiniz.");
              	    	    		sayi[0]=sayi[1]=sayi[2]=0;
              		    		}
              				}
              				else if(operator[1]=="-")
                  			{
              					sonuc=sonuc-sayi[n-1];
              					count=0;
              					if(sonuc==sayi[n]){
              						try {
										denklemKontrol();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
              					}
              		    		else
              		    		{
              		    			JOptionPane.showMessageDialog(null, "Hatali hesaplama yaptiniz.");
              	    	    		sayi[0]=sayi[1]=sayi[2]=0;
              		    		}
                  			}                  			
              				else if(operator[1]=="*" && operator[0]=="+")
                  			{
              					sonuc=sayi[n-3]+(sayi[n-2]*sayi[n-1]);
              					count=0;
              					if(sonuc==sayi[n]){
              						try {
										denklemKontrol();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
              					}
              		    		else
              		    		{
              		    			JOptionPane.showMessageDialog(null, "Hatali hesaplama yaptiniz.");
              	    	    		sayi[0]=sayi[1]=sayi[2]=0;
              		    		}
                  			}	
                  				
              				else if(operator[1]=="*" && operator[0]=="-")
                  			{
              					sonuc=sayi[n-3]-(sayi[n-2]*sayi[n-1]);
              					count=0;
              					if(sonuc==sayi[n]){
              						try {
										denklemKontrol();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
              					}
              		    		else
              		    		{
              		    			JOptionPane.showMessageDialog(null, "Hatali hesaplama yaptiniz.");
              	    	    		sayi[0]=sayi[1]=sayi[2]=0;
              		    		}
                  			}	
              				else if(operator[1]=="*" && (operator[0]=="*" || operator[0]=="/"))
                  			{
              					sonuc=sonuc*sayi[n-1];
              					count=0;
              					if(sonuc==sayi[n]){
              						try {
										denklemKontrol();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
              					}
              		    		else
              		    		{
              		    			JOptionPane.showMessageDialog(null, "Hatali hesaplama yaptiniz.");
              	    	    		sayi[0]=sayi[1]=sayi[2]=0;
              		    		}
                  			}
              				else if(operator[1]=="/" && operator[0]=="+")
                  			{
                  				if(sayi[n-1]!=0 && sayi[n-2]%sayi[n-1]==0)
                  					if(sayi[n]==sayi[n-3]+(sayi[n-2]/sayi[n-1]))
                  					{
                  						count=0;
                  						try {
											denklemKontrol();
										} catch (IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
                  					}
                       	    		else
                    	    		{
                       	    			JOptionPane.showMessageDialog(null, "Hatali hesaplama yaptiniz.");
                       	    			sayi[0]=sayi[1]=sayi[2]=0;
                    	    		}
                  			
                  				        
                  			}
              				else if(operator[1]=="/" && operator[0]=="-")
                  			{
                  				if(sayi[n-1]!=0 && sayi[n-2]%sayi[n-1]==0)
                  					if(sayi[n]==sayi[n-3]-(sayi[n-2]/sayi[n-1]))	
                   					    {
                  							count=0;
                  							try {
												denklemKontrol();
											} catch (IOException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
                   					    }
                       	    	     	else
                    	    	     	{
                       	    	     	JOptionPane.showMessageDialog(null, "Hatali hesaplama yaptiniz.");
                       	    	     sayi[0]=sayi[1]=sayi[2]=0;
                    	    		    }
                  			}
              				else if(operator[1]=="/" && (operator[0]=="*" || operator[0]=="/"))
                  			{
                  				if(sayi[n-1]!=0 && sonuc%sayi[n-1]==0)
                  				{
                  					count=0;
                  					sonuc=sonuc/sayi[n-1];
                  				    if(sayi[n]==sonuc) 
                            	    {
                  				    	try {
											denklemKontrol();
										} catch (IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
                            	    }
                       	    		else
                    	    		{
                       	    			JOptionPane.showMessageDialog(null, "Hatali hesaplama yaptiniz.");
                       	    			sayi[0]=sayi[1]=sayi[2]=0;
                    	    		}
                  				}
                  			}
            	    	}
		            }
			}
			private void denklemKontrol() throws IOException {
				resu=res;
	            try {
	            	for(r=0;r<length;r++){ 	
	    				if(boxes[i][r].getText().charAt(0)==res.charAt(r)){
	                     boxes[i][r].setBackground(Color.green);
	                     count++;
	                     resu=resu.substring(0, r) + "w" + resu.substring(r+1, resu.length());
	    				}
	    			}
	    			for(r=0;r<length;r++){
	    				if(resu.contains(boxes[i][r].getText()) && boxes[i][r].getBackground()!=Color.green){	
	    					int ind=resu.indexOf(boxes[i][r].getText());
	                         boxes[i][r].setBackground(Color.yellow);
	                         resu=resu.substring(0, ind) + "w" + resu.substring(ind+1, resu.length());
	    				}
	    				else if(boxes[i][r].getBackground()!=Color.green){
	    					boxes[i][r].setBackground(Color.red);
	    				}	
	    			}
				} catch (Exception e) {
					System.out.println("hata");
				}
	    			if(count==res.length()){
	    				swingtimer.stop();
    			      int input = JOptionPane.showConfirmDialog(null, "Denklemi basariyla buldunuz. Geçen zaman:"+start,"Ana Sayfaya dönmek için evet'i týklayýnýz",
    							JOptionPane.YES_NO_OPTION, JOptionPane.DEFAULT_OPTION);
    			      basariYazdir();
    			      ortalamaSatir(i);
    			      ortalamaSure(start);
                if(input==0) {
                	Gui frame = new Gui();
					frame.setVisible(true);
					dispose();
                	}
    			}
    			else if(i!=5){
	    			u++;
	    			i++;
	    			j=0;
	    			full=0;    
	    			n++;
	    			JOptionPane.showMessageDialog(null, "Basarili,Diger satira gecebilirsiniz.");
    			}
    			else{
    				JOptionPane.showMessageDialog(null, "Kaybettiniz,olmasý gereken denklem:"+" "+res);
    				kaybedilenYazdir();
    				Gui gvi = new Gui();
    				gvi.setVisible(true);
    				dispose();
    			}
			}
		});
		btnNewButton_15.setBackground(new Color(204, 255, 102));
		btnNewButton_15.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_15.setBounds(334, 565, 167, 51);
		contentPane.add(btnNewButton_15);
			
		JButton btnNewButton_16 = new JButton("Sil");
		btnNewButton_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				 try
		         {
		        	 boxes[i][j].setText(null);
						j=j-1;
						if(j==-1)
						{
							j=0;
						}
		         }
		         catch(Exception esd)
		         {
		        	 System.out.println("boyut aþýldý");
		         }
				
			}
		});
		btnNewButton_16.setBackground(new Color(255, 0, 51));
		btnNewButton_16.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_16.setBounds(511, 565, 86, 51);
		contentPane.add(btnNewButton_16);
		
		JButton btnNewButton_1_1 = new JButton("0");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(u==i)
				{
				   if(j<length)
				   {
					   boxes[i][j].setText(btnNewButton_1_1.getActionCommand());
						j++;
						if(j==length) full=1;
				   }	
				   else
				   {
					   JOptionPane.showMessageDialog(null, "Daha fazla ekleme yapamazsiniz.");
				   }
				}
			}
		});
		btnNewButton_1_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), new Color(204, 0, 255), new Color(102, 204, 51), new Color(255, 0, 0)));
		btnNewButton_1_1.setBackground(new Color(51, 255, 204));
		btnNewButton_1_1.setFocusPainted(false);
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_1_1.setBounds(31, 480, 50, 51);
		contentPane.add(btnNewButton_1_1);
		
		
		
		JButton btnNewButton_1_1_1 = new JButton("1");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(u==i)
				{
					   if(j<length)
					   {
						   boxes[i][j].setText(btnNewButton_1_1_1.getActionCommand());
							j++;
							if(j==length) full=1;
					   }	
					   else
					   {
						   JOptionPane.showMessageDialog(null, "Daha fazla ekleme yapamazsiniz.");
					   }
				}
			}
		});
		btnNewButton_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_1_1_1.setFocusPainted(false);
		btnNewButton_1_1_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), new Color(204, 0, 255), new Color(102, 204, 51), new Color(255, 0, 0)));
		btnNewButton_1_1_1.setBackground(new Color(51, 255, 204));
		btnNewButton_1_1_1.setBounds(99, 480, 50, 51);
		contentPane.add(btnNewButton_1_1_1);
		
		JButton btnNewButton_1_1_2 = new JButton("2");
		btnNewButton_1_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(u==i)
				{
					   if(j<length)
					   {
						   boxes[i][j].setText(btnNewButton_1_1_2.getActionCommand());
							j++;
							if(j==length) full=1;
					   }	
					   else
					   {
						   JOptionPane.showMessageDialog(null, "Daha fazla ekleme yapamazsiniz.");
					   }

				}
			}
		});
		btnNewButton_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_1_1_2.setFocusPainted(false);
		btnNewButton_1_1_2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), new Color(204, 0, 255), new Color(102, 204, 51), new Color(255, 0, 0)));
		btnNewButton_1_1_2.setBackground(new Color(51, 255, 204));
		btnNewButton_1_1_2.setBounds(166, 480, 50, 51);
		contentPane.add(btnNewButton_1_1_2);
		
		JButton btnNewButton_1_1_3 = new JButton("3");
		btnNewButton_1_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(u==i)
				{
					   if(j<length)
					   {
						   boxes[i][j].setText(btnNewButton_1_1_3.getActionCommand());
							j++;
							if(j==length) full=1;
					   }	
					   else
					   {
						   JOptionPane.showMessageDialog(null, "Daha fazla ekleme yapamazsiniz.");
					   }

				}
			}
		});
		btnNewButton_1_1_3.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_1_1_3.setFocusPainted(false);
		btnNewButton_1_1_3.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), new Color(204, 0, 255), new Color(102, 204, 51), new Color(255, 0, 0)));
		btnNewButton_1_1_3.setBackground(new Color(51, 255, 204));
		btnNewButton_1_1_3.setBounds(240, 480, 50, 51);
		contentPane.add(btnNewButton_1_1_3);
		
		JButton btnNewButton_1_1_4 = new JButton("4");
		btnNewButton_1_1_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(u==i)
				{
					   if(j<length)
					   {
						   boxes[i][j].setText(btnNewButton_1_1_4.getActionCommand());
							j++;
							if(j==length) full=1;
					   }	
					   else
					   {
						   JOptionPane.showMessageDialog(null, "Daha fazla ekleme yapamazsiniz.");
					   }

				}
			}
		});
		btnNewButton_1_1_4.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_1_1_4.setFocusPainted(false);
		btnNewButton_1_1_4.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), new Color(204, 0, 255), new Color(102, 204, 51), new Color(255, 0, 0)));
		btnNewButton_1_1_4.setBackground(new Color(51, 255, 204));
		btnNewButton_1_1_4.setBounds(315, 480, 50, 51);
		contentPane.add(btnNewButton_1_1_4);
		
		JButton btnNewButton_1_1_5 = new JButton("5");
		btnNewButton_1_1_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(u==i)
				{
					   if(j<length)
					   {
						   boxes[i][j].setText(btnNewButton_1_1_5.getActionCommand());
							j++;
							if(j==length) full=1;
					   }	
					   else
					   {
						   JOptionPane.showMessageDialog(null, "Daha fazla ekleme yapamazsiniz.");
					   }

				}
			}
		});
		btnNewButton_1_1_5.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_1_1_5.setFocusPainted(false);
		btnNewButton_1_1_5.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), new Color(204, 0, 255), new Color(102, 204, 51), new Color(255, 0, 0)));
		btnNewButton_1_1_5.setBackground(new Color(51, 255, 204));
		btnNewButton_1_1_5.setBounds(391, 480, 50, 51);
		contentPane.add(btnNewButton_1_1_5);
		
		JButton btnNewButton_1_1_6 = new JButton("6");
		btnNewButton_1_1_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(u==i)
				{
					   if(j<length)
					   {
						   boxes[i][j].setText(btnNewButton_1_1_6.getActionCommand());
							j++;
							if(j==length) full=1;
					   }	
					   else
					   {
						   JOptionPane.showMessageDialog(null, "Daha fazla ekleme yapamazsiniz.");
					   }
				}
			}
		});
		btnNewButton_1_1_6.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_1_1_6.setFocusPainted(false);
		btnNewButton_1_1_6.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), new Color(204, 0, 255), new Color(102, 204, 51), new Color(255, 0, 0)));
		btnNewButton_1_1_6.setBackground(new Color(51, 255, 204));
		btnNewButton_1_1_6.setBounds(470, 480, 50, 51);
		contentPane.add(btnNewButton_1_1_6);
		
		JButton btnNewButton_1_1_7 = new JButton("7");
		btnNewButton_1_1_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(u==i)
				{
					   if(j<length)
					   {
						   boxes[i][j].setText(btnNewButton_1_1_7.getActionCommand());
							j++;
							if(j==length) full=1;
					   }	
					   else
					   {
						   JOptionPane.showMessageDialog(null, "Daha fazla ekleme yapamazsiniz.");
					   }
				}
			}
		});
		btnNewButton_1_1_7.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_1_1_7.setFocusPainted(false);
		btnNewButton_1_1_7.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), new Color(204, 0, 255), new Color(102, 204, 51), new Color(255, 0, 0)));
		btnNewButton_1_1_7.setBackground(new Color(51, 255, 204));
		btnNewButton_1_1_7.setBounds(547, 480, 50, 51);
		contentPane.add(btnNewButton_1_1_7);
		
		JButton btnNewButton_1_1_8 = new JButton("8");
		btnNewButton_1_1_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(u==i)
				{
				   if(j<length)
				   {
					   boxes[i][j].setText(btnNewButton_1_1_8.getActionCommand());
						j++;
						if(j==length) full=1;
				   }	
				   else
				   {
					   JOptionPane.showMessageDialog(null, "Daha fazla ekleme yapamazsiniz.");
				   }
				}
			}
		});
		btnNewButton_1_1_8.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_1_1_8.setFocusPainted(false);
		btnNewButton_1_1_8.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), new Color(204, 0, 255), new Color(102, 204, 51), new Color(255, 0, 0)));
		btnNewButton_1_1_8.setBackground(new Color(51, 255, 204));
		btnNewButton_1_1_8.setBounds(628, 480, 50, 51);
		contentPane.add(btnNewButton_1_1_8);
		
		JButton btnNewButton_1_1_9 = new JButton("9");
		btnNewButton_1_1_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(u==i)
				{
					   if(j<length)
					   {
						   boxes[i][j].setText(btnNewButton_1_1_9.getActionCommand());
							j++;
							if(j==length) full=1;
					   }	
					   else
					   {
						   JOptionPane.showMessageDialog(null, "Daha fazla ekleme yapamazsiniz.");
					   }
				}
			}
		});
		btnNewButton_1_1_9.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_1_1_9.setFocusPainted(false);
		btnNewButton_1_1_9.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), new Color(204, 0, 255), new Color(102, 204, 51), new Color(255, 0, 0)));
		btnNewButton_1_1_9.setBackground(new Color(51, 255, 204));
		btnNewButton_1_1_9.setBounds(706, 480, 50, 51);
		contentPane.add(btnNewButton_1_1_9);
		
		JButton btnNewButton_1_1_10 = new JButton("+");
		btnNewButton_1_1_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(u==i)
				{
					   if(j<length)
					   {
						   boxes[i][j].setText(btnNewButton_1_1_10.getActionCommand());
							j++;
							if(j==length) full=1;
					   }	
					   else
					   {
						   JOptionPane.showMessageDialog(null, "Daha fazla ekleme yapamazsiniz.");
					   }

				}
			}
		});
		btnNewButton_1_1_10.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_1_1_10.setFocusPainted(false);
		btnNewButton_1_1_10.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), new Color(204, 0, 255), new Color(102, 204, 51), new Color(255, 0, 0)));
		btnNewButton_1_1_10.setBackground(new Color(51, 255, 204));
		btnNewButton_1_1_10.setBounds(31, 565, 50, 51);
		contentPane.add(btnNewButton_1_1_10);
		
		JButton btnNewButton_1_1_11 = new JButton("-");
		btnNewButton_1_1_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(u==i)
				{
					   if(j<length)
					   {
						   boxes[i][j].setText(btnNewButton_1_1_11.getActionCommand());
							j++;
							if(j==length) full=1;
					   }	
					   else
					   {
						   JOptionPane.showMessageDialog(null, "Daha fazla ekleme yapamazsiniz.");
					   }

				}
				
			}
		});
		btnNewButton_1_1_11.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_1_1_11.setFocusPainted(false);
		btnNewButton_1_1_11.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), new Color(204, 0, 255), new Color(102, 204, 51), new Color(255, 0, 0)));
		btnNewButton_1_1_11.setBackground(new Color(51, 255, 204));
		btnNewButton_1_1_11.setBounds(91, 565, 50, 51);
		contentPane.add(btnNewButton_1_1_11);
		
		JButton btnNewButton_1_1_12 = new JButton("*");
		btnNewButton_1_1_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(u==i)
				{
					   if(j<length)
					   {
						   boxes[i][j].setText(btnNewButton_1_1_12.getActionCommand());
							j++;
							if(j==length) full=1;
					   }	
					   else
					   {
						   JOptionPane.showMessageDialog(null, "Daha fazla ekleme yapamazsiniz.");
					   }

				}
				
			}
		});
		btnNewButton_1_1_12.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_1_1_12.setFocusPainted(false);
		btnNewButton_1_1_12.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), new Color(204, 0, 255), new Color(102, 204, 51), new Color(255, 0, 0)));
		btnNewButton_1_1_12.setBackground(new Color(51, 255, 204));
		btnNewButton_1_1_12.setBounds(151, 565, 50, 51);
		contentPane.add(btnNewButton_1_1_12);
		
		JButton btnNewButton_1_1_13 = new JButton("/");
		btnNewButton_1_1_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(u==i)
				{
					   if(j<length)
					   {
						   boxes[i][j].setText(btnNewButton_1_1_13.getActionCommand());
							j++;
							if(j==length) full=1;
					   }	
					   else
					   {
						   JOptionPane.showMessageDialog(null, "Daha fazla ekleme yapamazsiniz.");
					   }

				}
			}
		});
		btnNewButton_1_1_13.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_1_1_13.setFocusPainted(false);
		btnNewButton_1_1_13.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), new Color(204, 0, 255), new Color(102, 204, 51), new Color(255, 0, 0)));
		btnNewButton_1_1_13.setBackground(new Color(51, 255, 204));
		btnNewButton_1_1_13.setBounds(211, 565, 50, 51);
		contentPane.add(btnNewButton_1_1_13);
		
		JButton btnNewButton_1_1_14 = new JButton("=");
		btnNewButton_1_1_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(u==i)
				{
					   if(j<length)
					   {
						   boxes[i][j].setText(btnNewButton_1_1_14.getActionCommand());
							j++;
							if(j==length) full=1;
					   }	
					   else
					   {
						   JOptionPane.showMessageDialog(null, "Daha fazla ekleme yapamazsiniz.");
					   }

				}
			}
		});
		btnNewButton_1_1_14.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_1_1_14.setFocusPainted(false);
		btnNewButton_1_1_14.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), new Color(204, 0, 255), new Color(102, 204, 51), new Color(255, 0, 0)));
		btnNewButton_1_1_14.setBackground(new Color(51, 255, 204));
		btnNewButton_1_1_14.setBounds(271, 565, 50, 51);
		contentPane.add(btnNewButton_1_1_14);
		
		textField_1 = new JTextField();
		textField_1.setText("Grup 12\r\nAhmet Enis \u015E\u0130M\u015E\u0130R 19011077\r\nKutay Alptekin 20011615");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField_1.setColumns(10);
		textField_1.setBounds(10, 634, 331, 35);
		contentPane.add(textField_1);
		
		JButton btnNewButton_16_1 = new JButton("Sonra Bitir");
		btnNewButton_16_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					yaridaBirakilanYazdir();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Gui gvi = new Gui();
				gvi.setVisible(true);
				dispose();
			      try {
			            File Obj = new File("dosyam.txt");
			            if (Obj.createNewFile()) {
			                System.out.println("File created: "
			                                   + Obj.getName());
			            }
			            else {
			                System.out.println("Basarili");
			            }
			        }
			        catch (IOException es) {
			            System.out.println("Hata olustu.");
			            es.printStackTrace();
			        }
			      
			      try {
			            FileWriter Writer
			                = new FileWriter("dosyam.txt");
			            Writer.write(res+"\n");
			           
			            for (int z = 0;  z<i ; z++) {
			            	 for (int c = 0; c < res.length(); c++) {
			            		 Writer.write(boxes[z][c].getText());
								}
			            	 Writer.write("\n");
						}
			            Writer.close();
			            System.out.println("Basarili.");
			        }
			        catch (IOException es) {
			            System.out.println("Hata olustu");
			            es.printStackTrace();
			        }
			}
		});
		btnNewButton_16_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_16_1.setBackground(Color.ORANGE);
		btnNewButton_16_1.setBounds(612, 565, 167, 51);
		contentPane.add(btnNewButton_16_1);
	}
	
	public void setBoxes(int sat,String strs)
	{
		for (int f = 0; f < strs.length(); f++) 
		{
			this.boxes[sat][f].setText(Character.toString(strs.charAt(f)));
		}
		i=u=sat+1;
		j=0;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		start++;
		if (start >= 1) {
			tLabel.setText("GEÇEN ZAMAN: " + start+" "+"sn");
			
		} else {
			swingtimer.stop();
		}
	}
	
	private void basariYazdir() throws IOException{ //tamamlanan dosyasindaki sayiyi 1 artirmak yetiyor
		
		try {
	            File Obj = new File("tamamlanan.txt");
	            if (Obj.createNewFile()) { //Eger tamamlanan dosyasi yoksa olusturup icine 0 atiyoruz
	                System.out.println("File created: " + Obj.getName());
	                data="0";
	                FileWriter Writer = new FileWriter("tamamlanan.txt");
		            Writer.write(data);
		            Writer.close();
	              } 
	            else { //Varsa zaten icerisindeki degeri okuyup isliyoruz
	            	System.out.println("File already exists.");
	                Scanner Reader = new Scanner(Obj);
	                data = Reader.nextLine();
	                Reader.close();
	                }
	        }
	        catch (FileNotFoundException ed) {
	            System.out.println("An error has occurred.");
	            ed.printStackTrace();
	        }
	      
	      try { 
	    	int data2= Integer.parseInt(data);
	    	data2++;
	    	String data3=Integer.toString(data2);
	            FileWriter Writer = new FileWriter("tamamlanan.txt");
	            Writer.write(data3);
	            Writer.close();
	            System.out.println("Basarili.");
	        }
	        catch (IOException es) {
	            System.out.println("Hata olustu");
	            es.printStackTrace();
	        }
	}
	
	private void kaybedilenYazdir() throws IOException{ //kaybedilen dosyasindaki sayiyi 1 artirmak yetiyor
		try {
	            File Obj = new File("kaybedilen.txt");
	            if (Obj.createNewFile()) {
	                System.out.println("File created: " + Obj.getName());
	                data="0";
	                FileWriter Writer = new FileWriter("kaybedilen.txt");
		            Writer.write(data);
		            Writer.close();
	              }
	            else {
	            	Scanner Reader = new Scanner(Obj);
	            	data = Reader.nextLine();
	            	Reader.close();
	            }
	        }
	        catch (FileNotFoundException ed) {
	            System.out.println("An error has occurred.");
	            ed.printStackTrace();
	        }
	      
	      try {
	    	int data2= Integer.parseInt(data);
	    	data2++;
	    	String data3=Integer.toString(data2);
	            FileWriter Writer = new FileWriter("kaybedilen.txt");
	            Writer.write(data3);
	            Writer.close();
	            System.out.println("Basarili.");
	        }
	        catch (IOException es) {
	            System.out.println("Hata olustu");
	            es.printStackTrace();
	        }
	}
	
	private void yaridaBirakilanYazdir() throws IOException{ //yaridaBirakilan dosyasindaki sayiyi 1 artirmak yetiyor
		try {
	            File Obj = new File("yaridaBirakilan.txt");
	            if (Obj.createNewFile()) {
	                System.out.println("File created: " + Obj.getName());
	                data="0";
	                FileWriter Writer = new FileWriter("yaridaBirakilan.txt");
		            Writer.write(data);
		            Writer.close();
	              } 
	            else {
	            	Scanner Reader = new Scanner(Obj);
	            	data = Reader.nextLine();
	            	Reader.close();
	            }
	        }
	        catch (FileNotFoundException ed) {
	            System.out.println("An error has occurred.");
	            ed.printStackTrace();
	        }
	      
	      try {
	    	int data2= Integer.parseInt(data);
	    	data2++;
	    	String data3=Integer.toString(data2);
	            FileWriter Writer = new FileWriter("yaridaBirakilan.txt");
	            Writer.write(data3);
	            Writer.close();
	            System.out.println("Basarili.");
	        }
	        catch (IOException es) {
	            System.out.println("Hata olustu");
	            es.printStackTrace();
	        }
	}
	
	private void ortalamaSatir(int yeni) throws IOException { 
		/*eski ortalama ile eski tamamlanan sayisini carpinca suana kadarki toplam
		satir sayisi ortaya cikiyor. buna yenisini ekleyip yeni tamamlanan sayisina boluyoruz */
		try {
            File Obj = new File("tamamlanan.txt");
            Scanner Reader = new Scanner(Obj);
            data = Reader.nextLine();
            Reader.close();
        }
        catch (FileNotFoundException ed) {
            System.out.println("An error has occurred.");
            ed.printStackTrace();
        }
		int tamamlanan=Integer.parseInt(data);
		try {
            File Obj = new File("ortalamaSatir.txt");
            if (Obj.createNewFile()) {
                System.out.println("File created: " + Obj.getName());
                data="0";
                FileWriter Writer = new FileWriter("ortalamaSatir.txt");
	            Writer.write(data);
	            Writer.close();
             } 
            else {
            	Scanner Reader = new Scanner(Obj);
            	data = Reader.nextLine();
            	Reader.close();
            }
        }
        catch (FileNotFoundException ed) {
            System.out.println("An error has occurred.");
            ed.printStackTrace();
        }
		int ortalama=Integer.parseInt(data);
		int sonuc=(((tamamlanan-1)*ortalama)+yeni+1)/(tamamlanan);
		try {
	    	String data3=Integer.toString(sonuc);
	            FileWriter Writer = new FileWriter("ortalamaSatir.txt");
	            Writer.write(data3);
	            Writer.close();
	            System.out.println("Basarili.");
	        }
	        catch (IOException es) {
	            System.out.println("Hata oluþtu");
	            es.printStackTrace();
	        }
	}
	
	private void ortalamaSure(int yeni) throws IOException {
		/*eski ortalama ile eski tamamlanan sayisini carpinca suana kadarki toplam
		sure ortaya cikiyor. buna yenisini ekleyip yeni tamamlanan sayisina boluyoruz */
		try {
            File Obj = new File("tamamlanan.txt");
            Scanner Reader = new Scanner(Obj);
            data = Reader.nextLine();
            Reader.close();
        }
        catch (FileNotFoundException ed) {
            System.out.println("An error has occurred.");
            ed.printStackTrace();
        }
		int tamamlanan=Integer.parseInt(data);
		try {
            File Obj = new File("ortalamaSure.txt");
            if (Obj.createNewFile()) {
                System.out.println("File created: " + Obj.getName());
                data="0";
                FileWriter Writer = new FileWriter("ortalamaSure.txt");
	            Writer.write(data);
	            Writer.close();
            }
            else {
            	Scanner Reader = new Scanner(Obj);
            	data = Reader.nextLine();
            	Reader.close();
            }
        }
        catch (FileNotFoundException ed) {
            System.out.println("An error has occurred.");
            ed.printStackTrace();
        }
		int ortalama=Integer.parseInt(data);
		int sonuc=(((tamamlanan-1)*ortalama)+yeni)/(tamamlanan);
		try {
	    	String data3=Integer.toString(sonuc);
	            FileWriter Writer = new FileWriter("ortalamaSure.txt");
	            Writer.write(data3);
	            Writer.close();
	            System.out.println("Basarili.");
	        }
	        catch (IOException es) {
	            System.out.println("Hata olustu");
	            es.printStackTrace();
	        }
	}
}
