package com.naskar.graph.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.xml.ws.Holder;

import com.naskar.graph.model.Graph;
import com.naskar.graph.model.Vertex;

public class DepthFirstSearchAlg {
	
	private interface VertexVisitor {
		boolean visit(List<String> stack);
	}
	
	public List<String> findPath(Graph g, String from, String to) {
		return findPath(g, from, to, g.getSizeVertices());
	}
	
	public List<String> findPath(Graph g, String from, String to, final int minor) {
		
		final Vertex source = g.getVertex(from);
		final Vertex target = g.getVertex(to);
		
		final Holder<List<String>> pathHolder = new Holder<List<String>>();
		
		iterate(source, new VertexVisitor() {
			
			private int minorPath = minor;
			
			@Override
			public boolean visit(List<String> stack) {
				
				if(stack.size() >= minorPath) {
					return false;
				}
				
				String current = stack.get(stack.size()-1);
				
				if(stack.subList(0, stack.size()-1).contains(current)) {
					return false;
				}
								
				if(current.equals(target.getId())) {
					if(stack.size() < minorPath) {
						pathHolder.value = new ArrayList<String>(stack);
						minorPath = stack.size();
					}
					return false;
				}
				
				return true;
			}
		});
		
		return pathHolder.value;
	}
	
	public void iterate(Vertex root, VertexVisitor f) {
		List<String> stack = new ArrayList<String>(); 
		iterateInterno(stack, root, f);
	}
	
	private void iterateInterno(List<String> stack, Vertex root, VertexVisitor f) {
		
		String previous = "";
		if(!stack.isEmpty()) {
			previous = stack.get(stack.size()-1);
		}
		stack.add(root.getId());
		
		if(f.visit(stack)) {
			Set<Vertex> list = root.getAdjacencies();
			for(Vertex i : list) {
				if(!i.getId().equals(previous)) {
					iterateInterno(stack, i, f);
				}
			}
		}
		
		stack.remove(stack.size()-1);
	}
}
