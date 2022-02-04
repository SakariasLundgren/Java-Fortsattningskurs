package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BookReaderApplication {

	public static void main(String[] args) throws FileNotFoundException{
		Scanner s = new Scanner(new File("nilsholg.txt"));
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		Set<String> stopwords = new HashSet<String>();
			while (scan.hasNext()) { //lägga till alla stop ord till "StopWords"
				stopwords.add(scan.next().toLowerCase());
			}
		GeneralWordCounter count = new GeneralWordCounter(stopwords);
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning
			while (s.hasNext()) {
				String word = s.next().toLowerCase();
				count.process(word);
			}	
		count.report();
		s.close();
		BookReaderController reader = new BookReaderController(count);
	}

}
