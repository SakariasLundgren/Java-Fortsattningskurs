package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland", "norge" };

	public static void main(String[] args) throws FileNotFoundException {
		long t0 = System.nanoTime();
		ArrayList<TextProcessor> allWords = new ArrayList<>();
		allWords.add(new SingleWordCounter("nils"));
		allWords.add(new SingleWordCounter("norge"));
		Scanner s = new Scanner(new File("nilsholg.txt"));
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		Set<String> stopwords = new HashSet<String>();
			while (scan.hasNext()) { //lägga till alla stop ord till "StopWords"
				stopwords.add(scan.next().toLowerCase());
			}
		TextProcessor r = new GeneralWordCounter(stopwords);
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning
		
		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			r.process(word);
		}
		r.report();
		s.close();
		long t1 = System.nanoTime();
		System.out.println("tid: " + (t1 - t0)/1000000 + "ms");
	}
}