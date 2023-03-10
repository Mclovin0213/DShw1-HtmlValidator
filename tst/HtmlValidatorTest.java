// This is JUnit test program stub
// DO NOT CHANGE THE NAME OF THE METHODS GIVEN
// 0) test0 is by the instructor as example to test your validate() method
// 1) You are to reproduce testing validate() method with test1.html-test8.html and
//    match the expected output
// 2) You are to add your own JUnit test for testing your removeAll method (At least 4)
// 3) Feel free to add more test cases to test any of your public methods in HtmlValidator (No extra credit, but for your own benefit)

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class HtmlValidatorTest {
    /**
     * Below code returns the String format
     * of the content of the given file
     * @param expectedFileName The name of the file that has expected output
     *                         Make sure put relative path in front of
     *                         the file name
     *                         (For example, if your files under tst folder,
     *                         expectedFileName should be "tst/YOUR_FILE_NAME"
     * @return The String format of what the expectedFileName contains
     */
    private static String expectedOutputToString (String expectedFileName) {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner fileScanner = new Scanner(new File(expectedFileName));
            while (fileScanner.hasNextLine()) {
                sb.append(fileScanner.nextLine()+ System.lineSeparator());
            }
        } catch (FileNotFoundException ex) {
            Assert.fail(expectedFileName + "not found. Make sure this file exists. Use relative path to root in front of the file name");
        }
        return sb.toString();
    }

    /** Below code returns the String format
     * of what your validator's validate prints to the console
     * Feel free to use it so that you can compare it with the expected string
     * @param validator HtmlValidator to test
     * @return String format of what HtmlValidator's validate outputs
     */
    private static String validatorOutputToString(HtmlValidator validator) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out;
        System.setOut(ps);
        validator.validate();
        System.out.flush();
        System.setOut(oldOut);
        return baos.toString();
    }

    /**
     * This test is an instructor given test case to show you some example
     * of testing your validate() method
     * <b>Hi</b><br/> is the hypothetical html file to test
     */
    @Test
    public void test0(){
        //<b>Hi</b><br/>
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("b", true));      // <b>
        tags.add(new HtmlTag("b", false));     // </b>
        tags.add(new HtmlTag("br"));           // <br/>
        HtmlValidator validator = new HtmlValidator(tags);

        //Note test0_expected_output.txt is placed under tst. Use relative path!
        Assert.assertEquals(expectedOutputToString("tst/test0_expected_output.txt"),
                            validatorOutputToString(validator));
    }

    /**
     * This test1 method should test your validate() method
     * reproducing the test of
     * input_html/test1.html and expected_output/validate_result_for_test1.txt
     */
	@Test
	public void test1(){
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("b", true));
        tags.add(new HtmlTag("i", true));
        tags.add(new HtmlTag("i", false));
        tags.add(new HtmlTag("b", false));
        HtmlValidator validator = new HtmlValidator(tags);

        Assert.assertEquals(expectedOutputToString("expected_output/validate_result_for_test1.txt"),
                validatorOutputToString(validator));
    }

    /**
     * This test2 method should test your validate() method
     * reproducing the test of
     * input_html/test2.html and expected_output/validate_result_for_test2.txt
     */
	@Test
	public void test2(){
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("html", true));
        tags.add(new HtmlTag("b", true));
        tags.add(new HtmlTag("i", true));
        tags.add(new HtmlTag("i", false));
        HtmlValidator validator = new HtmlValidator(tags);

        Assert.assertEquals(expectedOutputToString("expected_output/validate_result_for_test2.txt"),
                validatorOutputToString(validator));
	}


    /**
     * This test3 method should test your validate() method
     * reproducing the test of
     * input_html/test3.html and expected_output/validate_result_for_test3.txt
     */
	@Test
	public void test3(){
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("b", true));
        tags.add(new HtmlTag("i", true));
        tags.add(new HtmlTag("b", false));
        tags.add(new HtmlTag("i", false));
        HtmlValidator validator = new HtmlValidator(tags);

        Assert.assertEquals(expectedOutputToString("expected_output/validate_result_for_test3.txt"),
                validatorOutputToString(validator));
	}


    /**
     * This test4 method should test your validate() method
     * reproducing the test of
     * input_html/test4.html and expected_output/validate_result_for_test4.txt
     */
	@Test
	public void test4(){
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("b", true));
        tags.add(new HtmlTag("i", true));
        tags.add(new HtmlTag("b", false));
        tags.add(new HtmlTag("i", false));
        tags.add(new HtmlTag("b", false));
        tags.add(new HtmlTag("html", false));
        HtmlValidator validator = new HtmlValidator(tags);

        Assert.assertEquals(expectedOutputToString("expected_output/validate_result_for_test4.txt"),
                validatorOutputToString(validator));
	}

    /**
     * This test5 method should test your validate() method
     * reproducing the test of
     * input_html/test5.html and expected_output/validate_result_for_test5.txt
     */
	@Test
	public void test5(){
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("html", false));
        HtmlValidator validator = new HtmlValidator(tags);

        Assert.assertEquals(expectedOutputToString("expected_output/validate_result_for_test5.txt"),
                validatorOutputToString(validator));
	}

    /**
     * This test1 method should test your validate() method
     * reproducing the test of
     * input_html/test6.html and expected_output/validate_result_for_test6.txt
     */
	@Test
	public void test6(){
        Queue<HtmlTag> tags = new LinkedList<>();
        HtmlValidator validator = new HtmlValidator(tags);

        Assert.assertEquals(expectedOutputToString("expected_output/validate_result_for_test6.txt"),
                validatorOutputToString(validator));
	}

    /**
     * This test7 method should test your validate() method
     * reproducing the test of
     * input_html/test7.html and expected_output/validate_result_for_test7.txt
     */
	@Test
	public void test7(){
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("!doctype", true));
        tags.add(new HtmlTag("!--", true));
        tags.add(new HtmlTag("html", true));
        tags.add(new HtmlTag("head", true));
        tags.add(new HtmlTag("title", true));
        tags.add(new HtmlTag("title", false));
        tags.add(new HtmlTag("meta", true));
        tags.add(new HtmlTag("link", true));
        tags.add(new HtmlTag("head", false));
        tags.add(new HtmlTag("body", true));
        tags.add(new HtmlTag("p", true));
        tags.add(new HtmlTag("a", true));
        tags.add(new HtmlTag("a", false));
        tags.add(new HtmlTag("p", false));
        tags.add(new HtmlTag("p", true));
        tags.add(new HtmlTag("img", true));
        tags.add(new HtmlTag("p", false));
        tags.add(new HtmlTag("body", false));
        tags.add(new HtmlTag("html", false));
        HtmlValidator validator = new HtmlValidator(tags);

        Assert.assertEquals(expectedOutputToString("expected_output/validate_result_for_test7.txt"),
                validatorOutputToString(validator));
	}

    /**
     * This test8 method should test your validate() method
     * reproducing the test of
     * input_html/test8.html and expected_output/validate_result_for_test8.txt
     */
	@Test
	public void test8(){
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("!doctype", true));
        tags.add(new HtmlTag("!--", true));
        tags.add(new HtmlTag("html", true));
        tags.add(new HtmlTag("head", true));
        tags.add(new HtmlTag("title", true));
        tags.add(new HtmlTag("meta", true));
        tags.add(new HtmlTag("link", true));
        tags.add(new HtmlTag("head", false));
        tags.add(new HtmlTag("head", false));
        tags.add(new HtmlTag("body", true));
        tags.add(new HtmlTag("p", true));
        tags.add(new HtmlTag("a", true));
        tags.add(new HtmlTag("a", false));
        tags.add(new HtmlTag("p", false));
        tags.add(new HtmlTag("br", false));
        tags.add(new HtmlTag("p", true));
        tags.add(new HtmlTag("img", true));
        tags.add(new HtmlTag("p", false));
        tags.add(new HtmlTag("html", false));
        HtmlValidator validator = new HtmlValidator(tags);

        Assert.assertEquals(expectedOutputToString("expected_output/validate_result_for_test8.txt"),
                validatorOutputToString(validator));
	}

	/**
	 * This myRemoveAllTest1() tests the removeAll method
	 */
	@Test
	public void myRemoveAllTest1(){
        HtmlValidator testValidator = new HtmlValidator();
        testValidator.addTag(new HtmlTag("p", true));
        testValidator.addTag(new HtmlTag("p", true));
        testValidator.addTag(new HtmlTag("p", true));
        testValidator.addTag(new HtmlTag("img", true));
        testValidator.addTag(new HtmlTag("html", true));

        HtmlValidator correctOutput = new HtmlValidator();
        correctOutput.addTag(new HtmlTag("img", true));
        correctOutput.addTag(new HtmlTag("html", true));

        testValidator.removeAll("p");

        Assert.assertEquals(correctOutput.tags, testValidator.tags);
    }
}
