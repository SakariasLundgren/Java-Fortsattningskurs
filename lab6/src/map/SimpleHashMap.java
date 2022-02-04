package map;

import java.util.Random;

public class SimpleHashMap<K, V> implements Map<K, V> {
	private final double LOAD = 0.75;
	private Entry<K, V> table [];
	private Entry<K, V> tableTemp [];
	private int size = 0;
	
	public static void main(String[] args) {
		final int NBR_OF_VALUES = 8;
		Random rand = new Random();
		
		SimpleHashMap<Integer, Integer> map = new SimpleHashMap<Integer, Integer>(10);
		
		for (int i = 0; i < NBR_OF_VALUES; i++) {
			int rndNbr = rand.nextInt(200) - 100;
			map.put(i, rndNbr);
			
		}
		
		System.out.println(map.show());
	}

	
	public SimpleHashMap() {
		table = (Entry<K, V>[]) new Entry[16];
	}
	
	public SimpleHashMap(int capacity) {
		table = (Entry<K, V>[]) new Entry[capacity];
	}

	private static class Entry<K, V> implements Map.Entry<K, V>{
		private K key;
		private V value;
		private Entry<K, V> next;
		
		private Entry (K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return this.key;
		}

		@Override
		public V getValue() {
			return this.value;
		}

		@Override
		public V setValue(V value) {
			this.value = value;
			return value;
		}
		
		@Override
		public String toString() {
			return this.key + " = " + this.value;
		}
		
	}

	public String show(){
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < table.length ; i++) {
			if(table[i] != null) {
				str.append(i + "\t" + table[i].toString());
				Entry<K, V> temp = table[i];
				while (temp.next != null) {
					str.append(i + "\t" + table[i].next.toString());
					temp = temp.next;
				}
				str.append("\n");
			} else {
				str.append(i + "\t null \n");
			}
		}
		return str.toString();
	}
	
	private int index(K key) {
		int index = key.hashCode() % table.length;
		if (index < 0 ) {
			index = index + table.length;
		} 
		return index;
	}
	
	private Entry<K, V> find (int index, K key) {
		Entry<K, V> temp = this.table[index];
		
		while (temp != null) {
			if(temp.getKey().equals(key)) {
				return temp;
			}
			temp = temp.next;
		}
		return null;
	}
	
	@Override
	public V get(Object arg0) {
		K key = (K) arg0;
		Entry<K, V> temp = find(index(key), key);
		
		if(temp == null) {
			return null;
		} 
		
		return temp.getValue();
	}

	@Override
	public boolean isEmpty() {
		if(size > 0) {
			return false;
		}
		return true;
	}

	@Override
	public V put(K arg0, V arg1) {
		int ind = index(arg0);
		V val = null;
		Entry<K, V> temp = this.find(ind, arg0);

		if(temp != null) {
			val = temp.getValue();
			temp.setValue(arg1);
		} else {
			Entry<K, V> e = new Entry<K, V>(arg0, arg1);
			
			if (this.table[ind] == null) {
				this.table[ind] = e;
			} else {
				temp = table[ind];
				while (temp.next != null) {
					temp = temp.next;
				}
				temp.next = e;
			}
			size++;
		}
		
		if(size >= (LOAD*table.length)) {
			rehash();
		}
		
		return val;
	}
	
	private void rehash () {
		tableTemp = table;
		table = (Entry<K, V>[]) new Entry[table.length * 2];
		for(int i = 0; i < tableTemp.length; i++) {
			Entry<K, V> tableTempE = tableTemp[i];
			
			while(tableTempE != null) {
				put(tableTempE.getKey(), tableTempE.getValue());
				size--;
				tableTempE = tableTempE.next;
			}
		}
	}

	@Override
	public V remove(Object arg0) {
		K key = (K) arg0;
		Entry<K, V> delete = find(index(key), key);
		Entry<K, V> temp = table[index(key)];
		
		//Om listan är tom
		if(isEmpty()) {
			return null;
		}
		
		//Om entryn som man vill tabort inte finns
		if(delete == null) {
			return null;
		}  
			
		//om Entryn som ska tasbort är första noden
		if(temp.getKey().equals(key)) { 
			table[index(key)] = delete.next; //Varför funkar inte temp?	
			size--;
			return delete.getValue();
		}
		
		//om det inte är något av ovanstådende
		while (temp.next.getKey().equals(key) == false) {					
			temp = temp.next;
		}
		temp.next = delete.next;
		size--;
		return delete.getValue();
		
	}

	@Override
	public int size() {
		return size;
	}

}
