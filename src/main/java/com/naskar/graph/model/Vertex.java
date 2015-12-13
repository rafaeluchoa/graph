package com.naskar.graph.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Vertex {
	
	// node id
	private String id;
	
	// Map<Vertex, Weight>
	private Map<Vertex, Integer> adjacencies;
	
	public Vertex(String id) {
		if(id == null || id.equals("")) {
			throw new IllegalArgumentException("id can't be null");
		}
		
		this.id = id;
		this.adjacencies = new HashMap<Vertex, Integer>();
	}

	public Integer weight(Vertex v) {
		return this.adjacencies.get(v);
	}

	public void addAdjacency(Vertex to, Integer weight) {
		this.adjacencies.put(to, weight);
	}
	
	public void removeAdjacency(Vertex v) {
		this.adjacencies.remove(v);
	}
	
	public Set<Vertex> getAdjacencies() {
		return this.adjacencies.keySet();
	}
	
	public Collection<Integer> getWeights() {
		return this.adjacencies.values();
	}
	
	public int getAdjacenciesSize() {
		return this.adjacencies.size();
	}
	
	public String getId() {
		return this.id;
	}
	
	@Override
	public String toString() {
		return id;
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}
	
}