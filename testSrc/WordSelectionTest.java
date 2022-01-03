import org.junit.Assert;
import org.junit.Test;
import ru.vsu.cs.novichikhin.ListUtils;
import ru.vsu.cs.novichikhin.WordSelection;

import java.io.FileNotFoundException;
import java.util.List;

public class WordSelectionTest {

    @Test
    public void testFindRightWordsFromTextWithOneWord() throws FileNotFoundException {
        List<String> text = ListUtils.readLinesFromFile("testSrc/TestFiles/TestCase/inputForTextWithOneWord.txt");
        List<String> expectedList = ListUtils.readWordsFromFile("testSrc/TestFiles/TestResult/outputForTextWithOneWord.txt");

        WordSelection selection = new WordSelection();
        List<String> rightWords = selection.findRightWords(text);

        Assert.assertEquals(expectedList, rightWords);
    }

    @Test
    public void testFindRightWordsFromTextWithNumbers() throws FileNotFoundException {
        List<String> text = ListUtils.readLinesFromFile("testSrc/TestFiles/TestCase/inputForTextWithNumbers.txt");
        List<String> expectedList = ListUtils.readWordsFromFile("testSrc/TestFiles/TestResult/outputForTextWithNumbers.txt");

        WordSelection selection = new WordSelection();
        List<String> rightWords = selection.findRightWords(text);

        Assert.assertEquals(expectedList, rightWords);
    }

    @Test
    public void testFindRightWordsFromTextWithEmptyLine() throws FileNotFoundException {
        List<String> text = ListUtils.readLinesFromFile("testSrc/TestFiles/TestCase/inputForTextWithEmptyLine.txt");
        List<String> expectedList = ListUtils.readWordsFromFile("testSrc/TestFiles/TestResult/outputForTextWithEmptyLine.txt");

        WordSelection selection = new WordSelection();
        List<String> rightWords = selection.findRightWords(text);

        Assert.assertEquals(expectedList, rightWords);
    }

    @Test
    public void testFindRightWordsFromTextWithRepeatedWord() throws FileNotFoundException {
        List<String> text = ListUtils.readLinesFromFile("testSrc/TestFiles/TestCase/inputForTextWithRepeatedWord.txt");
        List<String> expectedList = ListUtils.readWordsFromFile("testSrc/TestFiles/TestResult/outputForTextWithRepeatedWord.txt");

        WordSelection selection = new WordSelection();
        List<String> rightWords = selection.findRightWords(text);

        Assert.assertEquals(expectedList, rightWords);
    }

    @Test
    public void testFindRightWordsFromTextWithAllRightWords() throws FileNotFoundException {
        List<String> text = ListUtils.readLinesFromFile("testSrc/TestFiles/TestCase/inputForTextWithAllRightWords.txt");
        List<String> expectedList = ListUtils.readWordsFromFile("testSrc/TestFiles/TestResult/outputForTextWithAllRightWords.txt");

        WordSelection selection = new WordSelection();
        List<String> rightWords = selection.findRightWords(text);

        Assert.assertEquals(expectedList, rightWords);
    }
}