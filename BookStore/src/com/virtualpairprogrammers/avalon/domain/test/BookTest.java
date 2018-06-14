package com.virtualpairprogrammers.avalon.domain.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.virtualpairprogrammers.avalon.domain.Book;

public class BookTest {

	@Test
	public void testBooksWithTheSameIsbnNumberAreConsideredTheSameProduct()
	{
		// arrange
		Book bookOne = new Book("7861568632", "Java Puzzles", "Josh Bloch", 10.99);
		Book bookTwo = new Book("7861568632", "Java Puzzles", "Josh Bloch", 10.99);
		
		// act
		boolean areTheyEqual = bookOne.equals(bookTwo);
		
		// assert
		assertTrue(areTheyEqual);
	}
	
	@Test
	public void testFormatOfTheToStringIsCorrect()
	{
		// arrange
		Book bookOne = new Book("7861568632", "Java Puzzles", "Josh Bloch", 10.99);
		
		//act
		String toStringResult = bookOne.toString();
		
		//assert
		assertEquals("Java Puzzles by Josh Bloch", toStringResult);
	}

}
