package com.naskar.graph.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public List<Vertex> getAdjacencies() {
		List<Vertex> list = new ArrayList<Vertex>();
		list.addAll(adjacencies.keySet());
		return list;
	}
	
	public String getId() {
		return this.id;
	}
	
	@Override
	public String toString() {
		return id;
	}
	
}
