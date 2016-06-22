import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * 
 * @author Gabriel Larson gsl13
 *
 */

//and given @test is a different scenario, and a story is a requirement
//given, when, then
//as a, i want, so that
public class WebTestingBDD {

	static WebDriver driver = new HtmlUnitDriver();
	
	@Before
	public void setUp() throws Exception {
		
		driver.get("http://lit-bayou-7912.herokuapp.com/");
	}
	
	/**
	 * As a User, 
	 * I want to be able to Tokenize ruby code
	 * So that i can Analyze the Tokenized code to better my program
	 */

	//Given that I am on the main screen of Hoodpopper
	//When I look at the bottom of the text box
	//Then I should see a Tokenize button
	@Test
	public void TestHasTolkenizeButton(){
		
		driver.findElement(By.name("commit"));
	}
	
	//Given that I am on the main screen of hoodpopper
	//When I enter nothing, and click the tokenize button
	//Then I should not see anything on the tokenize screen
	@Test
	public void TestEmptyTokenize(){
		
		String PassInVal = ""; // Blank String
		
		String GoalVal = ""; // What the Result Should be
		
		WebElement TextBox = driver.findElement(By.id("code_code")); // Should give me the box that you can enter code from the main screen
		
		WebElement TokenizeButton = driver.findElement(By.name("commit")); // the Tokenize button
		
		TextBox.sendKeys(PassInVal); // Enter blank target text into code_code
		
		TokenizeButton.click(); // click Tokenize Button
		
		WebElement TokenizeAnswer = driver.findElement(By.cssSelector("code")); // Gives me the Results Box
		
		String TokenizeText = TokenizeAnswer.getText(); // gives me the text of the Result Box after pressing Tokenize.
		
		assertEquals(true, TokenizeText.contains(GoalVal));	// Makes sure the Result is blank.
	}
	
	// Given I are on the main page of Hoodpopper
	// When I enter the strings x - 7
	// Then I should see the Tokenized code for the given string (shown bellow in CorrectText)
	@Test
	public void TestTokenizeSmallMath(){
		
		String EntryVal = "x - 7";
		
		String CorrectText = "[[1, 0], :on_ident, \"x\"]\n[[1, 1], :on_sp, \" \"]\n[[1, 2], :on_op, \"-\"]\n[[1, 3], :on_sp, \" \"]\n[[1, 4], :on_int, \"7\"]";
		
		WebElement TextBox = driver.findElement(By.id("code_code")); // Should give me the box that you can enter code from the main screen
		
		WebElement TokenizeButton = driver.findElement(By.name("commit")); // the Tokenize button
		
		TextBox.sendKeys(EntryVal); // Enter x - 7 target text into code_code
		
		TokenizeButton.click(); // click Tokenize Button
		
		WebElement TokenizeAnswer = driver.findElement(By.cssSelector("code")); // Gives me the Results Box
	
		String TokenizeText = TokenizeAnswer.getText(); // gives me the text of the Result Box after pressing Tokenize.
	
		assertEquals(true, TokenizeText.contains(CorrectText));	// Makes sure the Result is blank.
	}
	
	// Given that I am on the main page of Hoodpopper 
	// When I press Tokenize
	// Then I should see that the Page contains the Word Tokenize in the heading
	@Test
	public void TestTokenizePageHasTitle(){
		
		driver.findElement(By.name("commit")).click(); // click the Tokenize button
		
		String title = driver.findElement(By.cssSelector("h1")).getText();
		
		assertTrue(title.contains("Tokenize"));
	}
	
	//Given that I am on the main page of Hoodpopper
	//When I press Tokenize
	//Then I should see that there is a link "back"
	@Test
	public void TestTokenizePageHasBackButton(){
		
		driver.findElement(By.name("commit")).click(); // click the Tokenize button
		
		try {
			driver.findElement(By.linkText("Back"));

		} 
		catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	//Given that I on the results page for the Tokenize button
	//When I click the "Back" link
	//Then I should be taken back to the main page
	@Test
	public void TestTokenizeBackGoesToMain(){
		
		driver.findElement(By.name("commit")).click(); // click the Tokenize button to get to tokenize results page
		
		driver.findElement(By.linkText("Back")).click(); // click back link
		
		String title = driver.getCurrentUrl();
		
		assertTrue(title.contains("http://lit-bayou-7912.herokuapp.com/"));
	}
	
	 /**
	 * As a User, 
	 * I want to be able to Parse ruby code
	 * So that i can Analyze the Parsed code to better my program
	 */
	
	//Given that I am on the Main screen of Hoodpopper
	//When I look at the bottom of the textbox
	//Then I should see a Parse Button
	@Test
	public void TestHasParseButton(){
		
		driver.findElement(By.xpath("(//input[@name='commit'])[2]"));
	}
	
	// Given that I am on the Main Page of Hoodpopper
	// When I enter an empty String
	// Then I should find the parsed code for the blank string (shown below in GoalVal) in results textbox
	@Test
	public void TestEmptyParse(){
		
		String PassInVal = ""; // Blank String
	
		String GoalVal =  "program\n[[:void_stmt]]"; // What the Result Should be
		
		WebElement TextBox = driver.findElement(By.id("code_code")); // Should give me the box that you can enter code from the main screen
		
		WebElement ParseButton = driver.findElement(By.xpath("(//input[@name='commit'])[2]")); // the Parse button
		
		TextBox.sendKeys(PassInVal); // Enter blank Target text into code_code
		
		ParseButton.click(); // click Parse Button
		
		WebElement ParseAnswer = driver.findElement(By.cssSelector("code")); // Gives me the Results Box
		
		String ParseText = ParseAnswer.getText(); // gives me the text of the Result Box after pressing Parse.
		
		assertEquals(true, ParseText.contains(GoalVal));	// Makes sure the Result is blank.
	}
	
	// Given I am on the main page of Hoodpopper
	// When I enter the strings x + 7 followed by x * 8
	// Then I should see the compile code for the given string (shown bellow in CorrectText)
	@Test
	public void TestParseTwoMath(){
		
		String EntryVal = "x + 7\nx * 8";
		
		String CorrectText = "program\n[[:binary, [:vcall, [:@ident, \"x\", [1, 0]]], :+, [:@int, \"7\", [1, 4]]], [:binary, [:vcall, [:@ident, \"x\", [2, 0]]], :*, [:@int, \"8\", [2, 4]]]]";
		
		WebElement TextBox = driver.findElement(By.id("code_code")); // Should give me the box that you can enter code from the main screen
		
		WebElement ParseButton = driver.findElement(By.xpath("(//input[@name='commit'])[2]")); // the Parse button
		
		TextBox.sendKeys(EntryVal); // Enter x - 7 target text into code_code
		
		ParseButton.click(); // click Parse Button
		
		WebElement ParseAnswer = driver.findElement(By.cssSelector("code")); // Gives me the Results Box
		
		String ParseText = ParseAnswer.getText(); // gives me the text of the Result Box after pressing Parse.
		
		assertEquals(true, ParseText.contains(CorrectText));	// Makes sure the Result is equal to what we should get returned.
	}
	
	// Given that I am on the main page of Hoodpopper 
	// When I press Parse (input should not matter)
	// Then I should see that the Page contains the Word Parse in the heading
	@Test
	public void TestParsePageHasTitle(){
		
		String parse = "Parse";
		
		driver.findElement(By.xpath("(//input[@name='commit'])[2]")).click(); // click the Parse button
		
		String title = driver.findElement(By.cssSelector("h1")).getText();
		
		assertTrue(title.contains(parse));
	}
	
	//Given that I am on the main page of Hoodpopper
	//When I press Parse
	//Then I should see that there is a link "back" somewhere on the page
	@Test
	public void TestParsePageHasBackButton(){
		
		driver.findElement(By.xpath("(//input[@name='commit'])[2]")).click(); // click the Parse button
		
		try {
			driver.findElement(By.linkText("Back"));
		} 
		catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	//Given that I am on the results page for the Parse button
	//When I click the "Back" link
	//Then I am taken back to the main page
	@Test
	public void TestParseBackGoesToMain(){
		
		driver.findElement(By.xpath("(//input[@name='commit'])[2]")).click(); // click the Parse button to get to Parse results page
		
		driver.findElement(By.linkText("Back")).click(); // click back link
		
		String title = driver.getCurrentUrl();
		
		assertTrue(title.contains("http://lit-bayou-7912.herokuapp.com/"));
	}
	

	 /**
	 * As a User, 
	 * I want to be able to Analyze compiled ruby code
	 * So that I can better my program
	 */
	
	//Given that I am on the Main screen of Hoodpopper
	//When I look at the bottom of the textbox
	//Then I should see a Compile Button
	@Test
	public void TestHasCompileButton(){
		
		driver.findElement(By.xpath("(//input[@name='commit'])[3]"));
	}
	
	// Given that I am on the Main page of Hoodpoppper
	// When I Enter an empty string
	// Then I should see the compile code for a blank string (shown bellow in GoalVal)
	@Test
	public void TestEmptyCompile(){
		
		String PassInVal = ""; // Blank String
		
		String GoalVal = "== disasm: <RubyVM::InstructionSequence:<compiled>@<compiled>>==========\n0000 putnil ( 2)\n0001 leave"; // What the Result Should be
		
		WebElement TextBox = driver.findElement(By.id("code_code")); // Should give me the box that you can enter code from the main screen
		
		WebElement CompileButton = driver.findElement(By.xpath("(//input[@name='commit'])[3]")); // the Compile button
		
		TextBox.sendKeys(PassInVal); // Enter blank Target text into code_code
		
		CompileButton.click(); // click Compile Button
		
		WebElement CompileAnswer = driver.findElement(By.cssSelector("code")); // Gives me the Results Box
		
		String CompileText = CompileAnswer.getText(); // gives me the text of the Result Box after pressing Compile.
		
		assertEquals(true, CompileText.contains(GoalVal));	// Makes sure the Result is blank.
	}
	
	// Given that I am on the Main Page of Hoodpopper 
	// When I enter the string x + 3 followed by y - 8 on a new line then z / 5 on a new line, then put x on a new line
	// Then I should see the compile code for the given string (shown bellow in CorrectText)
	@Test
	public void TestCompileThreePutMath(){
		
		String EntryVal = "x + 3\ny - 8\nz / 5\nput x";
		
		String CorrectText = "== disasm: <RubyVM::InstructionSequence:<compiled>@<compiled>>==========\n0000 trace 1 ( 1)\n0002 putself\n0003 opt_send_simple <callinfo!mid:x, argc:0, FCALL|VCALL|ARGS_SKIP>\n0005 putobject 3\n0007 opt_plus <callinfo!mid:+, argc:1, ARGS_SKIP>\n0009 pop\n0010 trace 1 ( 2)\n0012 putself\n0013 opt_send_simple <callinfo!mid:y, argc:0, FCALL|VCALL|ARGS_SKIP>\n0015 putobject 8\n0017 opt_minus <callinfo!mid:-, argc:1, ARGS_SKIP>\n0019 pop\n0020 trace 1 ( 3)\n0022 putself\n0023 opt_send_simple <callinfo!mid:z, argc:0, FCALL|VCALL|ARGS_SKIP>\n0025 putobject 5\n0027 opt_div <callinfo!mid:/, argc:1, ARGS_SKIP>\n0029 pop\n0030 trace 1 ( 4)\n0032 putself\n0033 putself\n0034 opt_send_simple <callinfo!mid:x, argc:0, FCALL|VCALL|ARGS_SKIP>\n0036 opt_send_simple <callinfo!mid:put, argc:1, FCALL|ARGS_SKIP>\n0038 leave";
		
		WebElement TextBox = driver.findElement(By.id("code_code")); // Should give me the box that you can enter code from the main screen
		
		WebElement CompileButton = driver.findElement(By.xpath("(//input[@name='commit'])[3]")); // the Compile button
		
		TextBox.sendKeys(EntryVal); // Enter x + 3, y - 8, z / 5, put x  into code_code
		
		CompileButton.click(); // click Compile Button

		WebElement CompileAnswer = driver.findElement(By.cssSelector("code")); // Gives me the Results Box

		String CompileText = CompileAnswer.getText(); // gives me the text of the Result Box after pressing Compile.
		
		System.out.println(CompileText);
		
		assertEquals(true, CompileText.contains(CorrectText));	// Makes sure the Result is equal to what we should get returned.
	}
	
	// Given that we are on the main page of Hoodpopper
	// When I press Compile (input should not matter)
	// Then I should see that the Page contains the Word Compile in the heading
	@Test
	public void TestCompilePageHasTitle(){
		
		String compile = "Compile";
		
		driver.findElement(By.xpath("(//input[@name='commit'])[3]")).click(); // click the Compile button
		
		//TextBox.sendKeys(PassInVal); // Enter blank target text into code_code
		
		String title = driver.findElement(By.cssSelector("h1")).getText();
		
		assertTrue(title.contains(compile));
	}
	
	//Given that I am on the main page of Hoodpopper
	//When I press Compile
	//Then I should see that there is a link "back" somewhere on the page
	@Test
	public void TestCompilePageHasBackButton(){
		
		driver.findElement(By.xpath("(//input[@name='commit'])[3]")).click(); // click the Compile button
		
		try {
			driver.findElement(By.linkText("Back"));
		} 
		catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	//Given that I am on the results page for the Compile button
	//When I click the "Back" link
	//Then I am taken back to the main page
	@Test
	public void TestCompileBackGoesToMain(){

		driver.findElement(By.xpath("(//input[@name='commit'])[3]")).click(); // click the Tokenize button to get to tokenize results page
		
		driver.findElement(By.linkText("Back")).click(); // click back link
		
		String title = driver.getCurrentUrl();
		
		assertTrue(title.contains("http://lit-bayou-7912.herokuapp.com/"));
	}

}
