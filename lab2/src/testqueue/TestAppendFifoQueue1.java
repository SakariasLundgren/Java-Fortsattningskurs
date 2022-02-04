/**
 * 
 */
package testqueue;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Queue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import queue_singlelinkedlist.FifoQueue;

/**
 * @author sakke
 *
 */
class TestAppendFifoQueue1 {
	private FifoQueue<String> stringTest1;
	private FifoQueue<String> stringTest2;
	
	@BeforeEach
	void setup() {
		stringTest1= new FifoQueue<String>();
		stringTest2 = new FifoQueue<String>();
	}
	
	@AfterEach
	void tearDown(){
		stringTest1 = null;
		stringTest2 = null;
	}
	
	@Test
	void testEmptyLists () {
		stringTest1.append(stringTest2);
		assertTrue(stringTest1.isEmpty(), "Wrong results, due to list not empty");
	}
	
	@Test
	void testFirstEmpty () {
		stringTest2.offer("BlippBlapp");
		stringTest1.append(stringTest2);
		assertEquals("BlippBlapp", stringTest1.element(), "Wrong result, we seached for BlippBlapp as last element");
	}
	
	@Test
	void testSecondEmpty () {
		stringTest1.offer("Caonter Strajk");
		stringTest1.append(stringTest2);
		assertEquals("Caonter Strajk", stringTest1.element(), "" );
	}
	
	@Test
	void testTwoLists () {
		stringTest1.offer("hej");
		stringTest2.offer("Hej då");
		stringTest1.append(stringTest2);
		assertEquals("hej", stringTest1.peek(), "Wrong result");
	}
	
	@Test
	void testOneList() {
		stringTest1.offer("jag");
		stringTest1.append(stringTest1);
	}

}
