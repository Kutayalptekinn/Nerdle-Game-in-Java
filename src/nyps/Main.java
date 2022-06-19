package nyps;

import java.util.Random;

import javax.swing.JOptionPane;

public class Main {
	
	
public String generateEquation() {
	int sonuc = 0;
	int rand=RandNumber7to9();
	if(rand==7 || rand==8 || rand==9)
	{
		int flag=1;
		String res="";
		while(flag==1){ 
			int sayi1=RandNumberMaxTwoDigit();
          	int sayi2=RandNumberMaxTwoDigit();
          	char op1=RandOperator();
          	if(op1=='+') sonuc=sayi1+sayi2;
      		if(op1=='-') sonuc=sayi1-sayi2;
          	if(op1=='*') sonuc=sayi1*sayi2;
          	if(op1=='/'){
          		if(sayi2==0)
          			sayi2++;
          		else {
      				 while(sayi1%sayi2!=0) {
       					 sayi2--;
       				 }
       			}
          		sonuc=sayi1/sayi2;
          	}	        			
          	res=Integer.toString(sayi1)+Character.toString(op1)+Integer.toString(sayi2)+"="+Integer.toString(sonuc);
          	if(res.length()==rand && sonuc>0)
          		{
          			System.out.println(res);
          			flag=0;
          			return res;
          		}		
          	if(res.length()==5 || res.length()==6 || res.length()==7) {	
          		if(sonuc!=0){
          			int sayi3=RandNumberMaxTwoDigit();
              		char op2=RandOperator();	
              		if(op2=='+') sonuc=sonuc+sayi3;
               		if(op2=='-') sonuc=sonuc-sayi3;
                  	if(op2=='*' && op1=='+')
                  		sonuc=sayi1+(sayi2*sayi3);
              		if(op2=='*' && op1=='-')
              			sonuc=sayi1-(sayi2*sayi3);
                  	if(op2=='*' && (op1=='*' || op1=='/')){
                  		sonuc=sonuc*sayi3;
                  	}
                  	if(op2=='/' && op1=='+'){
                  		if(sayi3!=0 && sayi2%sayi3==0)
                  			sonuc=sayi1+sayi2/sayi3;		
                  		else 
                  			sonuc=0;
                  	}
                  			if(op2=='/' && op1=='-') {
                  				if(sayi3!=0 && sayi2%sayi3==0)
                  					sonuc=sayi1-sayi2/sayi3;		
                  				else 
                  					sonuc=0;
                  			}
                  			if(op2=='/' && (op1=='*' || op1=='/')){
                  				if(sayi3!=0 && sonuc%sayi3==0)
                  					sonuc=sonuc/sayi3;		
                  				else 
                  					sonuc=0;
                  			}
                  			res=Integer.toString(sayi1)+Character.toString(op1)+Integer.toString(sayi2)+Character.toString(op2)+Integer.toString(sayi3)+"="+Integer.toString(sonuc);
                  			if(res.length()==rand && sonuc>0) {
                  				System.out.println(res);
                      			flag=0;
                      			return res;
                  			}	
              	}
          	}	
		}
		}
		return "1";
	}

	public int RandNumber7to9()
	{
		int max=9;
		int min=7;
		return (int)Math.floor(Math.random()*(max-min+1)+min);
	}
	
	public int RandNumberMaxTwoDigit()
	{
		int max=99;
		int min=0;
		return (int)Math.floor(Math.random()*(max-min+1)+min);
	}
	
	public char RandOperator()
	{
		Random rnd = new Random();
		String characters = "+-/*";
		char randomChar = characters.charAt(rnd.nextInt(characters.length()));
		return randomChar;
	}
}