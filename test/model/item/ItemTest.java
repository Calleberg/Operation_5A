package model.item;

import static org.junit.Assert.*;

import model.items.Item;

import org.junit.Test;


public class ItemTest {
	
	@Test
	public void getIconNumberTest(){
		int testValue=42;
		Item i = new Item(null, testValue);
		assertTrue(i.getIconNumber()==testValue);
	}
	
}
