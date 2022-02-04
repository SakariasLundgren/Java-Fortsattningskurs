package textproc;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MultiWordCounter implements TextProcessor{
	private Map<String, Integer> map;
	private String [] words;
	
	public MultiWordCounter (String [] words) {
		map = new TreeMap<String,Integer>();
		this.words = words;
		for (int i = 0; i < words.length; i ++) {
			map.put(words[i], 0);
		}
	}
	
	@Override
	public void process(String w) {
		int temp;
		if (map.containsKey(w)) {
			temp = map.get(w);
			temp++;
			map.put(w, temp);
		}
	}
	
	@Override
	public void report() {
		for(int i = 0; i < words.length; i ++) {
			System.out.println(words[i] + ": " + map.get(words[i]));
		}
		
	}

}
