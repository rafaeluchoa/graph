package com.naskar.graph.model;

public class VisitedPath {
	
	private Stack<State> stack;
	private Integer weight;
	
	public VisitedPath(Stack<State> stack, Integer weight) {
		this.stack = stack;
		this.weight = weight;
	}
	
	public String toString() {
		return "visited:{s:" + stack + ",w:" + weight + "}";
	}
	
	public Stack<State> getStack() {
		return stack;
	}
	
	public Integer getWeight() {
		return weight;
	}
	
}