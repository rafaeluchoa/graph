package com.naskar.graph.algorithms;

import java.util.Arrays;
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
		
		g.addEdge("0", "1");
		g.addEdge("0", "3");
		g.addEdge("0", "4");
		g.addEdge("1", "2");
		g.addEdge("2", "4");
		g.addEdge("3", "2");
		g.addEdge("3", "4");
		
		List<String> expected = Arrays.asList( "0", "4");
		
		// Act
		List<String> actual = target.findPath(g, "0", "4");
		
		// Assert
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testCase2() {
		// Arrange
		Graph g = new Graph();
		
		g.addEdge("1", "2");
		g.addEdge("3", "1");
		g.addEdge("4", "1");
		g.addEdge("4", "3");
		g.addEdge("3", "2");
		g.addEdge("5", "3");
		g.addEdge("5", "4");
		g.addEdge("5", "6");
		g.addEdge("6", "4");
		g.addEdge("6", "1");
		
		List<String> expected = Arrays.asList("5", "3", "2");
		
		// Act
		List<String> actual = target.findPath(g, "5", "2");
		
		// Assert
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testCase3() {
		// Arrange
		Graph g = new Graph();
		
		g.addEdge("A", "B");
		g.addEdge("B", "C");
		g.addEdge("C", "D");
		g.addEdge("D", "C");
		g.addEdge("D", "E");
		g.addEdge("A", "D");
		g.addEdge("C", "E");
		g.addEdge("E", "B");
		g.addEdge("A", "E");
		
		List<String> expected = Arrays.asList("B", "C", "E");
		
		// Act
		List<String> actual = target.findPath(g, "B", "E");
		
		// Assert
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testCase4() {
		// Arrange
		Graph g = new Graph();
		
		g.addEdge("A", "B");
		g.addEdge("B", "C");
		g.addEdge("C", "D");
		g.addEdge("D", "C");
		g.addEdge("D", "E");
		g.addEdge("A", "D");
		g.addEdge("C", "E");
		g.addEdge("E", "B");
		g.addEdge("A", "E");
		
		List<String> expected = Arrays.asList("A", "B", "C");
		
		// Act
		List<String> actual = target.findPath(g, "A", "C");
		
		// Assert
		Assert.assertEquals(expected, actual);
	}
}
