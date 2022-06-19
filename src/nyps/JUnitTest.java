package nyps;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JUnitTest {


	@Test
	void esittirVarMi() {
		Main main = new Main();
		String test = main.generateEquation();
		boolean kontrol = test.contains("=");
		assertTrue(kontrol);
	}
	
	@Test
	void esittirinSagindaTekSayiMiVar() {
		Main main = new Main();
		String test = main.generateEquation();
		int index = test.indexOf('=');
		boolean kontrol=true;
		for(int i=index+1;i<test.length();i++) {
			if(test.charAt(i)>'9' || test.charAt(i)<'0') {
				kontrol=false;
			}
		}
		assertTrue(kontrol);
	}
	
	@Test
	void denklemYeterliUzunluktaMi() {
		Main main = new Main();
		String test = main.generateEquation();
		boolean kontrol=true;
		if(test.length()<7 || test.length()>9) {
			kontrol=false;
		}
		assertTrue(kontrol);
	}
	
	@Test
	void operandVeOperatordenMiIbaret(){
		Main main = new Main();
		String test = main.generateEquation();
		boolean kontrol=true;
		for(int i=0;i<test.length();i++) {
			if(test.charAt(i)>'9' || test.charAt(i)<'0') {
				if(test.charAt(i)!='+' &&  test.charAt(i)!='-' && test.charAt(i)!='*' && test.charAt(i)!='/' && test.charAt(i)!='=') {
					kontrol=false;
				}
			}
		}
		assertTrue(kontrol);
	}

}