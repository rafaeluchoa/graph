package com.naskar.graph.model;

public class State {
	
	private Vertex vertex;
	private Integer weight;
	
	public State(Vertex vertex, Integer weight) {
		this.vertex = vertex;
		this.weight = weight;
	}
	
	@Override
	public int hashCode() {
		return vertex.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return vertex.getId().equals(((State)obj).vertex.getId());
	}

	@Override
	public String toString() {
		return "state:{v:" + vertex + ",w:" + weight + "}";
	}
	
	public Vertex getVertex() {
		return vertex;
	}
	
	public Integer getWeight() {
		return weight;
	}
}