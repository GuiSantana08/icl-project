package tests;

import main.InterMain;
import org.junit.Assert;
import org.junit.Test;
import parser.ParseException;


public class ParserTester {

	private void testCase(String expression) throws ParseException {
		Assert.assertTrue(InterMain.accept(expression));
	}
	
	private void testNegativeCase(String expression) throws ParseException {
		Assert.assertFalse(InterMain.accept(expression));
	}
	
	@Test
	public void test01() throws Exception {
		testCase("1\n");
		testCase("11\n");
	}

	@Test
	public void test02ArithmeticOps() throws Exception {
		testCase("1+2\n");
		testCase("1-2-3\n");
		testCase("4*2\n");
		testCase("4/2/2\n");
		testCase("(4/2)/2\n");
	}
	
	@Test
	public void test03Error01() throws Exception {
		testNegativeCase("1++1\n");
		testNegativeCase("*2\n");
		testNegativeCase("4/+2/2\n");
	}
	
	@Test
	public void testsLabClass01() throws Exception {
		testCase("-1\n");
		testCase("-1*3\n");
		testCase("true\n");
		testCase("false\n");
		testCase("11 > 22\n");
		testCase("11 < 22\n");
		testCase("11 >= 22\n");
		testCase("11 <= 22\n");
		testCase("11 == 22\n");
		testCase("11 != 22\n");
		testCase("11 != 22 == true\n");
		testCase("3*5+4 != 1+2\n");
		testCase("3*5 != 1+2 == true\n");
		testCase("1 == 2 && 3 == 4\n");
		testCase("1 == 2 || 3 == 4\n");
		testCase("1 == 2 || 3 == 4 && true \n");
		testCase("!(1 == 2) && true \n");
		testNegativeCase("< 11\n");
		testNegativeCase("11 >\n");
		testNegativeCase("<= 11\n");
		testNegativeCase("&& A\n");
	}
}









