package com.naskar.graph.algorithms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.ws.Holder;

import com.naskar.graph.model.Graph;
import com.naskar.graph.model.Stack;
import com.naskar.graph.model.State;
import com.naskar.graph.model.Vertex;
import com.naskar.graph.model.VisitedPath;

public class DepthFirstSearchAlg {
	
	public interface VertexVisitor {
		boolean visit(Stack<State> stack, State current);
	}
	
	public interface WeightVisitor {
		Integer visit(Integer weight, Vertex vfrom, Vertex vto);
	}
	
	public List<String> findPath(Graph g, String from, String to) {
		return findPath(g, from, to, g
			.getVertices()
				.stream()
				.mapToInt(p -> 
					p.getWeights()
						.stream()
						.mapToInt(q -> q)
						.sum())
				.sum(),
			(weight, vfrom, vto) -> weight + vfrom.weight(vto));
	}
	
	public Stack<State> findState(Graph g, String from, String to) {
		return findState(g, from, to, g
			.getVertices()
				.stream()
				.mapToInt(p -> 
					p.getWeights()
						.stream()
						.mapToInt(q -> q)
						.sum())
				.sum(),
			(weight, vfrom, vto) -> weight + vfrom.weight(vto));
	}
	
	public List<String> findPath(
			Graph g, String from, String to, int minor, WeightVisitor ws) {
		
		return findState(g, from, to, minor, ws).asList()
			.stream()
			.map(p -> p.getVertex().getId())
			.collect(Collectors.toList());
	}
	
	public Stack<State> findState(Graph g, String from, String to, final int minor, WeightVisitor ws) {
		
		final Vertex source = g.getVertex(from);
		final Vertex target = g.getVertex(to);
		
		if(source == null || target == null) {
			return null;
		}
		
		final Holder<Stack<State>> pathHolder = new Holder<Stack<State>>();
		
		iterate(source, ws, new VertexVisitor() {
			
			private Integer minorWeigth = minor;

			// Map<Vertex.getId, Vertex.weight(to)>
			private Map<String, VisitedPath> visiteds = 
				new HashMap<String, VisitedPath>(); 
			
			@Override
			public boolean visit(Stack<State> stack, State current) {
				
				if(current.getWeight() >= minorWeigth) {
					return false;
				}
				
				if(stack.contains(current)) {
					return false;
				}
				
				VisitedPath visitedPath = visiteds.get(current.getVertex().getId());
				if(visitedPath != null) {
					
					Integer w = current.getWeight() + visitedPath.getWeight(); 
					synchronized (minorWeigth) {
						if(w < minorWeigth) {
							
							Stack<State> ss = stack.concat(visitedPath.getStack());
							State s = ss.pop();
							ss.push(new State(s.getVertex(), w));
							pathHolder.value = ss;
							minorWeigth = w;
							
							storeVisiteds(ws, ss);
						}
					}
					
					return false;
				}
				
				if(current.getVertex().getId().equals(target.getId())) {
					
					synchronized (minorWeigth) {
						if(current.getWeight() < minorWeigth) {
							Stack<State> ss = stack.copy();
							ss.push(current);
							pathHolder.value = ss;
							minorWeigth = current.getWeight();
							
							storeVisiteds(ws, ss);
						}						
					}
					
					return false;
				}
				
				return true;
			}

			private void storeVisiteds(WeightVisitor ws,
					final Stack<State> stack) {
				List<State> path = stack.asList();
				
				// inverse order without first (from)
				Integer iw = 0;
				
				for(int i = path.size()-1; i > 1; i--) {
					State currentS = path.get(i);
					State previousS = path.get(i-1);
					
					iw = ws.visit(iw, previousS.getVertex(), currentS.getVertex());
					
					synchronized (visiteds) {
						
						VisitedPath visitedPath = 
							visiteds.get(previousS.getVertex().getId());
						if(visitedPath == null || iw < visitedPath.getWeight()) {
							visiteds.put(previousS.getVertex().getId(), 
								new VisitedPath(new Stack<State>(path.subList(i-1, path.size())), iw));
						}
					
					}
				}
				
			}

		});
		
		return pathHolder.value;
	}
	
	public void iterate(Vertex root, WeightVisitor ws, VertexVisitor f) {
		Stack<State> stack = new Stack<State>();
		State rootState = new State(root, 0);
		iterateInternal(rootState, ws, f, stack);
	}
	
	private void iterateInternal(State root, WeightVisitor ws, VertexVisitor f, Stack<State> stack) {
		
		if(f.visit(stack, root)) {
			
			State previous = null;
			if(!stack.isEmpty()) {
				previous = stack.peek();
			}
			
			final State pp = previous;
			
			final Stack<State> stackCopy = stack.copy();
			stackCopy.push(root);
			
			// TEST
			root.getVertex().getAdjacencies()
				// .parallelStream().forEach((v) -> {
				.forEach((v) -> {
				
				// without cycles with same vertex
				if(pp == null || !v.getId().equals(pp.getVertex().getId())) {
					
					Integer w = ws.visit(root.getWeight(), root.getVertex(), v);
					
					iterateInternal(new State(v, w), ws, f, stackCopy);
					
				}
				
			});
			
		}
		
	}
}