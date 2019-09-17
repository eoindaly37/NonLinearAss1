package week2;

import java.util.LinkedList;
import java.util.Iterator;

	/**
	* Graph implementation that uses Adjacency Lists to store edges. It
	* contains one linked-list for every vertex i of the graph. The list for i
	* contains one instance of VertexAdjList for every vertex that is adjacent to i.
	* For directed graphs, if there is an edge from vertex i to vertex j then there is
	* a corresponding element in the adjacency list of node i (only). For
	* undirected graphs, if there is an edge between vertex i and vertex j, then there is a
	* corresponding element in the adjacency lists of *both* vertex i and vertex j. The
	* edges are not sorted; they contain the adjacent nodes in *order* of
	* edge insertion. In other words, for a graph, the node at the head of
	* the list of some vertex i corresponds to the edge involving i that was
	* added to the graph least recently (and has not been removed, yet). 
	*/

	public class GraphAdjList  implements Graph {

	// ATTRIBUTES: 
		private boolean directed;
		private LinkedList<Edge>[] og;
		private int size;
		private int totalEdges;
		
	 /*
	  * CONSTRUCTOR: Creates a directed/undirected graph with V vertices and no edges.
	  * It initializes the array of adjacency edges so that each list is empty.
	  */
	    
	 public GraphAdjList(int V, boolean directed) {
		 this.size = V;
		 this.directed = directed;
		 og = new LinkedList[size];
		 for(int i=0; i<size; i++) {
			 og[i] = new LinkedList<Edge>();
		 }
		 this.totalEdges = 0;
	 }

	 
	  // 1. IMPLEMENTATION METHOD numVerts: 
	  public int numVerts() { 
		  int res = size;
		  /*
		  LinkedList<Integer> vertices = new LinkedList<Integer>();
		  
		  for(int i=0; i<size; i++) {
			  if(og[i].size()>0) {
				  vertices.add(i);
			  }
		  }
		  if(directed==true) {
			  for(int i=0; i<size; i++) {
				  for(int j=0; j<og[i].size(); i++) {
					  if(og[i].get(j).getWeight()>0) {
						  int vertex = og[i].get(j).getVertex();
						  if(vertices.contains(vertex)==false) {
							  vertices.add(vertex);
						  }
					  }
				  }
			  }
		  }
		  
		  res = vertices.size();
		  */
		  
		  return res;
     }

	  // 2. IMPLEMENTATION METHOD numEdges:
	  public int numEdges() { 
		  
		  int res = totalEdges;
		  /*
		  if(directed==true) {
			  for(int i=0; i<size; i++) {
				  for(int j=0; j<og[i].size(); j++) {
					  if(og[i].get(j).getWeight()>0) {
						  res++;
					  }
				  }
			  }
		  }
		  else {
			  LinkedList<Integer> used = new LinkedList<Integer>();
			  for(int i=0; i<size; i++) {
				  for(int j=0; j<og[i].size(); j++) {
					  if(og[i].get(j).getWeight()>0) {
						  int weight = og[i].get(j).getWeight();
						  if(used.contains(weight)==false) {
							  used.add(weight);
						  }
					  }
				  }
			  }
			  res = used.size();
			  for(int i=0; i<used.size(); i++) {
				  System.out.print(used.get(i));
			  }
			  System.out.println();
		  }
		  */
		  return res;
	  }
	    
	  //  3. IMPLEMENTATION METHOD addEdge:
	  public void addEdge(int v1, int v2, int w) { 
		  if(v1>=size || v2>=size) {
			  System.out.println("Failed to add edge");
		  }
		  else {
			  int check = 0;
			  for(int i=0; i<og[v1].size(); i++) {
				  if(og[v1].get(i).getVertex()==v2) {
					  check++;
				  } 
			  }
			  if(check>0) {
				  for(int i=0; i<og[v1].size(); i++) {
					  if(og[v1].get(i).getVertex()==v2) {
						  og[v1].get(i).setWeight(w);
						  if(directed==false) {
							  og[v2].get(i).setWeight(w);
						  }
					  } 
			  }
			  }
			  else {
				  og[v1].add(new Edge(v2,w));
				  if(directed==false && v1!=v2) {
					  og[v2].add(new Edge(v1,w));
				  }
				  totalEdges++;
			  }
		  }
    }
	  
	 // 4. IMPLEMENTATION METHOD removeEdge: 
	 public void removeEdge(int v1, int v2) {
		 if(v1<size && v2<size) {
			 for(int i=0; i<og[v1].size(); i++) {
				 if(og[v1].get(i).getVertex()==v2) {
					 if(directed==false) {
						 for(int j=0; j<og[v2].size();j++) {
							 if(og[v2].get(j).getVertex()==v1) {
								 og[v2].get(j).setWeight(0);
							 }
						 }
					 }
					 og[v1].get(i).setWeight(0);
					 totalEdges--;
				 }
			 }
		 }
		 else {
			 System.out.println("Failed to remove edge at " + v1 + ", " + v2);
		 }
	 }
	 
	 // 5. IMPLEMENTATION METHOD hasEdge:
	 public boolean hasEdge(int v1, int v2) {
		 
		 boolean res = false;
		 
		 for(int i=0; i<og[v1].size(); i++) {
			 if(og[v1].get(i).getVertex()==v2) {
				 res = true;
			 }
		 }
		 
		 return res;
	 }

	// 6. IMPLEMENTATION METHOD getWeightEdge:
	 public int getWeightEdge(int v1, int v2) {
		 int res = 0;
		 
		 for(int i=0; i<og[v1].size(); i++) {
			 if(og[v1].get(i).getVertex()==v2) {
				 res = og[v1].get(i).getWeight();
			 }
		 }		 
		 
		 return res;
	 }

	// 7. IMPLEMENTATION METHOD getNeighbors:
	 public LinkedList<Integer> getNeighbors(int v) {
	     LinkedList<Integer> neighbours = new LinkedList<Integer>();
	     
	     for(int i=0; i<og[v].size(); i++) {
	    	 neighbours.add(og[v].get(i).getVertex());
	     }
	     if(directed==true) {
	    	 for(int i=0; i<size; i++) {
				  for(int j=0; j<og[i].size(); j++) {
					  int vertex = og[i].get(j).getVertex();
					  if(vertex==v) {
						  if(neighbours.contains(i)==false) {
							  neighbours.add(i);
						  }
					  }
				  }
			  }
	     }
	     
	     return neighbours;
	 }

    // 8. IMPLEMENTATION METHOD getDegree:
	public int getDegree(int v) {
		int res = 0;
		
		if(directed==false) {
			res = og[v].size();
		}
		else {
			LinkedList<Integer> degree = new LinkedList<Integer>();
			
			for(int i=0; i<og[v].size(); i++) {
				degree.add(og[v].get(i).getVertex());
			}
			for(int i=0; i<size; i++) {
				for(int j=0; j<og[i].size(); j++) {
					int vertex = og[i].get(j).getVertex();
					if(vertex==v) {
						if(degree.contains(i)==false) {
							degree.add(i);
						}
					}
				}
			}
			res = degree.size();
		}
		
		return res;
	}

	// 9. IMPLEMENTATION METHOD toString:
	 public String toString() {
	     String res = "Format: {Vertex, Weight}\n";
	     
	     for(int i=0; i<size; i++) {
	    	 res += "Vertex " + i + ": [ ";
	    	 for(int j=0; j<og[i].size(); j++) {
	    		 res += "{" + og[i].get(j).getVertex() + ", " 
	    				 + og[i].get(j).getWeight() + "}, ";
	    	 }
	    	 res += " ]\n";
	     }
    
	     return res;
	 }
 
	}


