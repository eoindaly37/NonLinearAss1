package week2;
import java.util.LinkedList;

/*
 *  Implementation of the interface Graph with adjacency matrix.
*/

 
public class GraphAdjMatrix implements Graph{

	// ATTRIBUTES: 
    private int[][] matrix;
    private boolean directed;
    private int size;
    
 
    
	// CONSTRUCTOR: Creates a directed/undirected graph with V vertices and no edges
	public GraphAdjMatrix(int V, boolean directed) {
    	this.matrix = new int[V][V];
    	this.directed = directed;
    	this.size = V;
    }


    // 1. IMPLEMENTATION METHOD numVerts: 
    public int numVerts() { 
    	int res = size;
    	/*
    	LinkedList<Integer> vertices = new LinkedList<Integer>();
    	for(int row=0; row<size; row++) {
			for(int col=0; col<size; col++) {
				if(matrix[row][col]>0) {
					if(vertices.contains(row)==false) {
						vertices.add(row);
					}
					if(vertices.contains(col)==false) {
						vertices.add(col);
					}
				}
				if(matrix[col][row]>0) {
					if(vertices.contains(col)==false) {
						vertices.add(col);
					}
					if(vertices.contains(col)==false) {
						vertices.add(col);
					}
				}
			}
		}
		for(int i=0; i<vertices.size(); i++) {
			System.out.print(vertices.get(i));
			
		}
		System.out.println();
		res = vertices.size();
    	
    	*/
    	
    	return res;
    }
    
   
    // 2. IMPLEMENTATION METHOD numEdges:
    public int numEdges() { 
    	int res = 0;
    	if(directed==true) {
    		for(int row = 0; row<size; row++) {
        		for(int col = 0; col<size; col++) {
        			if(matrix[row][col]>0) {
        				res++;
        			}
        		}
        	}
    	}
    	else {
    		for(int row = 0; row<size; row++) {
        		for(int col = 0; col<=row; col++) {
        			if(matrix[row][col]>0) {
        				res++;
        			}
        		}
        	}
    	}
    	
    	return res;
        }


   //  3. IMPLEMENTATION METHOD addEdge:
    public void addEdge(int v1, int v2, int w) {
        if(v1<size && v2<size) {
        	matrix[v1][v2] = w;
            if(directed==false) {
            	matrix[v2][v1] = w;
            }
        }
        else {
        	System.out.println("Failed to add edge at " + v1 + ", " + v2);
        }
        
    }
    
   // 4. IMPLEMENTATION METHOD removeEdge:
   public void removeEdge (int v1, int v2)
   {
	   if(v1<size && v2<size) {
       	matrix[v1][v2] = 0;
           if(directed==false) {
           	matrix[v2][v1] = 0;
           }
       }
       else {
       	System.out.println("Failed to remove edge at " + v1 + ", " + v2);
       }
   }

    // 5. IMPLEMENTATION METHOD hasEdge:
    public boolean hasEdge(int v1, int v2) {
    	boolean res = false;
    	if(matrix[v1][v2] > 0) {
    		res = true;
    	}
    	
    	return res;
    }
    
    // 6. IMPLEMENTATION METHOD getWeightEdge:
	public int getWeightEdge(int v1, int v2) {
		return matrix[v1][v2];
	}

    
	// 7. IMPLEMENTATION METHOD getNeighbors:
	public LinkedList<Integer> getNeighbors(int v)
	{
    	LinkedList<Integer> res = new LinkedList<Integer>();

    	for(int col = 0; col<size; col++) {
			if(matrix[v][col]>0) {
				res.add(col);
			}
		}
    	//CHANGED AS NEIGHBOURS IN DIRECTED GRAPH ONLY COUNT
    	//AS EDGES COMING FROM VERTEX
//    	if(directed==true) {
//    		for(int row = 0; row<size; row++) {
//    			if(matrix[row][v]>0) {
//    				res.add(row);
//    			}
//    		}
//    	}
    	
    	return res;
	}
   	
	// 8. IMPLEMENTATION METHOD getDegree:
	public int getDegree(int v) {
		int res = 0;
		if(directed==true) {
			for(int i = 0; i<size; i++) {
				if(matrix[v][i]>0) {
					res++;
				}
				else if(matrix[i][v]>0) {
					res++;
				}
			}
		}
		else {
			for(int i=0; i<size; i++) {
				if(matrix[v][i]>0) {
					res++;
				}
			}
		}
		return res;
	}
	

	// 9. IMPLEMENTATION METHOD toString:
   	public String toString() {
   		String res = "";
   		for (int row = 0; row < size; row++){
            for (int col = 0; col < size; col++){
            	res += matrix[row][col] + "  ";
            }
            res += "\n";
        }
   		return res;
    }    
}