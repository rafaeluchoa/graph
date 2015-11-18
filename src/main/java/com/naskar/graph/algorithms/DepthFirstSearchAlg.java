package com.naskar.graph.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.naskar.graph.model.Graph;
import com.naskar.graph.model.Vertex;

public class DepthFirstSearchAlg {
	
	private interface VertexVisitor {
		boolean visit(Stack<String> stack);
	}
	
	public List<List<String>> findPaths(Graph g, String from, String to) {
		
		final Vertex source = g.getVertex(from);
		final Vertex target = g.getVertex(to);
		
		final List<List<String>> paths = new ArrayList<List<String>>();
		
		iterate(source, new VertexVisitor() {
			@Override
			public boolean visit(Stack<String> stack) {
				
				if(stack.size() >= g.getVertices().size()) {
					return false;
				}
				
				if(stack.peek().equals(target.getId())) {
					addPath(paths, stack.subList(0, stack.size()));
					return false;
				}
				
				return true;
			}
		});
		
		return paths;
	}

	private void addPath(final List<List<String>> paths, final List<String> path) {
		List<String> copy = new ArrayList<String>(path.size());
		copy.addAll(path);
		if(!paths.contains(copy)) {
			paths.add(copy);
		}
		// path.clear();
	}
	
	public void iterate(Vertex root, VertexVisitor f) {
		List<String> visited = new ArrayList<String>();
		Stack<String> stack = new Stack<String>(); 
		iterateInterno(visited, stack, root, f);
	}
	
	// Function<Stack<String>, Boolean> f
	private void iterateInterno(
		List<String> visited, Stack<String> stack, Vertex root, VertexVisitor f) {
		
		//if(!visited.contains(root.getId())) {
			//visited.add(root.getId());
			stack.add(root.getId());
			
			if(f.visit(stack)) {
				List<Vertex> list = root.getAdjacencies();
				for(Vertex i : list) {
					iterateInterno(visited, stack, i, f);
				}
			}
			
			stack.pop();
			
		//}
		
	}
}
