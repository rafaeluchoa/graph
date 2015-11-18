package com.naskar.graph.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
	
	private Map<String, Vertex> vertices;
	
	public Graph() {
		this.vertices = new HashMap<String, Vertex>();
	}
	
	public void addEdge(String from, String to, int weight) {
		if(from.equals(to)) {
			throw new IllegalArgumentException(
				"Constraint: No permited cycles with only a vertex.");
		}
		
		Vertex fromV = this.vertices.get(from);
		Vertex toV = this.vertices.get(to);
		
		if(fromV == null) {
			fromV = new Vertex(from);
			this.vertices.put(from, fromV);
		}
		
		if(toV == null) {
			toV = new Vertex(to);
			this.vertices.put(to, toV);
		}
		
		fromV.addAdjacency(toV, weight);
	}
	
	public void removeVertice(String id) {
		Vertex s = this.vertices.remove(id);
		for(Vertex v : this.vertices.values()) {
			s.removeAdjacency(v);
		}
	}

	public Vertex getVertex(String node) {
		return this.vertices.get(node);
	}
	
	public List<Vertex> getVertices() {
		List<Vertex> list = new ArrayList<Vertex>();
		list.addAll(this.vertices.values());
		return list;
	}

}
