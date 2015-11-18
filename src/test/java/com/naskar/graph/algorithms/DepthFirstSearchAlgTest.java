package com.naskar.graph.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.naskar.graph.model.Graph;

public class DepthFirstSearchAlgTest {
	
	private DepthFirstSearchAlg target;
	
	@Before
	public void setup() {
		target = new DepthFirstSearchAlg();
	}
	
	@Test
	public void testCase1() {
		// Arrange
		Graph g = new Graph();
		
		g.addEdge("0", "1", 1);
		g.addEdge("0", "3", 3);
		g.addEdge("0", "4", 10);
		g.addEdge("1", "2", 5);
		g.addEdge("2", "4", 1);
		g.addEdge("3", "2", 2);
		g.addEdge("3", "4", 6);
		
		List<String> expected = Arrays.asList( 
			"04",
			"0124",
			"034",
			"0324"
		);
		Collections.sort(expected);
		
		// Act
		List<String> actual = convert(target.findPaths(g, "0", "4"));
		
		// Assert
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void testCase2() {
		// Arrange
		Graph g = new Graph();
		
		g.addEdge("1", "2", 1);
		g.addEdge("3", "1", 1);
		g.addEdge("4", "1", 2);
		g.addEdge("4", "3", 2);
		g.addEdge("3", "2", 3);
		g.addEdge("5", "3", 10);
		g.addEdge("5", "4", 5);
		g.addEdge("5", "6", 4);
		g.addEdge("6", "4", 1);
		g.addEdge("6", "1", 5);
		
		List<String> expected = Arrays.asList(
			"5312", "532", "5412", "54312", "5432", "5612", "56412", "56432"
		);
		
		// Act
		List<String> actual = convert(target.findPaths(g, "5", "2"));
		
		// Assert
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testCase3() {
		// Arrange
		Graph g = new Graph();
		
		g.addEdge("A", "B", 5);
		g.addEdge("B", "C", 4);
		g.addEdge("C", "D", 8);
		g.addEdge("D", "C", 8);
		g.addEdge("D", "E", 6);
		g.addEdge("A", "D", 5);
		g.addEdge("C", "E", 2);
		g.addEdge("E", "B", 3);
		g.addEdge("A", "E", 7);
		
		List<String> expected = Arrays.asList( 
			"BCE",
			"BCDE"
		);
		Collections.sort(expected);
		
		// Act
		List<String> actual = convert(target.findPaths(g, "B", "E"));
		
		// Assert
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testCase4() {
		// Arrange
		Graph g = new Graph();
		
		g.addEdge("A", "B", 5);
		g.addEdge("B", "C", 4);
		g.addEdge("C", "D", 8);
		g.addEdge("D", "C", 8);
		g.addEdge("D", "E", 6);
		g.addEdge("A", "D", 5);
		g.addEdge("C", "E", 2);
		g.addEdge("E", "B", 3);
		g.addEdge("A", "E", 7);
		
		List<String> expected = Arrays.asList(
			"ADC",
			"ABC",
			"AEBC"
		);
		Collections.sort(expected);
		
		// Act
		List<String> actual = convert(target.findPaths(g, "A", "C"));
		
		// Assert
		Assert.assertEquals(expected, actual);
	}
	
	private List<String> convert(List<List<String>> found) {
		final List<String> actual = new ArrayList<String>(); 
		found.forEach(p -> {
			StringBuilder t = new StringBuilder();
			
			p.forEach(q -> {
				t.append(q); 
			});
			
			actual.add(t.toString());
		});
		Collections.sort(actual);
		
		return actual;
	}
}
