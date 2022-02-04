package textproc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class GeneralWordCounter implements TextProcessor{
	Map<String, Integer> map;
	private Set<String> S;
	private int temp;
	
	public GeneralWordCounter (Set<String> S) {
		map = new TreeMap<String,Integer>();
		this.S = S; //Listan med ord som ej ska räknas
	}
	
	public List<Map.Entry<String, Integer>> getWordList() {
		return new ArrayList<>(this.map.entrySet());
	}
	
	public void process(String w) {
		if (S.contains(w) == false) {
			if(map.containsKey(w) == false) { //Om map inte innehåller ordet läggs det till
				map.put(w, 1);
			} else {
				map.put(w, (map.get(w) + 1));
			}
		}
	}

	public void report() {
		Set<Map.Entry<String, Integer>> wordSet = map.entrySet();
		List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
		wordList.sort(new WordCountComparator());
		
		for(int i = 0; i < 30; i ++) {
				System.out.println(wordList.get(i));
			}
		}

}
