package graph;
import java.util.*;
/**
 * This class implements general operations on a graph as specified by UndirectedGraphADT.
 * It implements a graph where data is contained in Vertex class instances.
 * Edges between verticies are unweighted and undirected.
 * A graph coloring algorithm determines the chromatic number. 
 * Colors are represented by integers. 
 * The maximum number of vertices and colors must be specified when the graph is instantiated.
 * You may implement the graph in the manner you choose. See instructions and course material for background.
 */
 
 public class UndirectedUnweightedGraph<T> implements UndirectedGraphADT<T> {
   // private class variables here.
   
   private int MAX_VERTICES;
   private int MAX_COLORS;
   private ArrayList<ArrayList<Vertex<T>>> vertex;
    // TODO: Declare class variables here.

   
   /**
    * Initialize all class variables and data structures. 
   */   
   public UndirectedUnweightedGraph (int maxVertices, int maxColors){
      MAX_VERTICES = maxVertices;
      MAX_COLORS = maxColors;
      vertex = new ArrayList<ArrayList<Vertex<T>>>();
     // TODO: Implement the rest of this method.

   }

   /**
    * Add a vertex containing this data to the graph.
    * Throws Exception if trying to add more than the max number of vertices.
   */
   public void addVertex(T data) throws Exception {
      if(vertex.size() == MAX_VERTICES) throw new Exception("Max vertices reached.");
      ArrayList<Vertex<T>> temp = new ArrayList<Vertex<T>>();
      temp.add(new Vertex<T>(data));
      vertex.add(temp);
    // TODO: Implement this method
   }
   
   /**
    * Return true if the graph contains a vertex with this data, false otherwise.
   */   
   public boolean hasVertex(T data){
      for(ArrayList<Vertex<T>> curr : vertex){
        if(curr.get(0).getData().equals(data)) return true;
      }
    // TODO: Implement this method.
      return false;
   } 

   /**
    * Add an edge between the vertices that contain these data.
    * Throws Exception if one or both vertices do not exist.
   */ 
   private ArrayList<Vertex<T>> getVertexList(T data){ //helper method which returns list of adjacent vertexes to vertex at index 0 of list
      for(ArrayList<Vertex<T>> curr : vertex){
        if(curr.get(0).getData().equals(data)) return curr;
      }
      return null;
   }

   public void addEdge(T data1, T data2) throws Exception{
      if(!hasVertex(data1) || !hasVertex(data2)) throw new Exception("One or both vertices do not exist");
      getVertexList(data1).add(getVertexList(data2).get(0)); //add vertex corresponding to data2 to list corresponding to data1
      getVertexList(data2).add(getVertexList(data1).get(0));
    // TODO: Implement this method.

   }

   /**
    * Get an ArrayList of the data contained in all vertices adjacent to the vertex that
    * contains the data passed in. Returns an array of zero length if no adjacencies exist in the graph.
    * Throws Exception if a vertex containing the data passed in does not exist.
   */   
   public ArrayList<T> getAdjacentData(T data) throws Exception{
     if(!hasVertex(data)) throw new Exception("Vertex does not exist");
     ArrayList<T> listData = new ArrayList<>();
     for(Vertex<T> curr : getVertexList(data)){
       listData.add(curr.getData());
     }
     listData.remove(0);
    // TODO: Implement this method.
      return listData;
   }
   
   /**
    * Returns the total number of vertices in the graph.
   */   
   public int getNumVertices(){
    // TODO: Implement this method.
      return vertex.size();
   }

   /**
    * Returns the total number of edges in the graph.
   */   
   public int getNumEdges(){
     int count = 0;
     for(ArrayList<Vertex<T>> curr : vertex){ //traverse through each vertex list and count total number of arguements
       count += curr.size() - 1;
     }
    // TODO: Implement this method.
      return count/2; //divide by two since each edge is added twice (once in each vertex's list)
   }

   /**
    * Returns the minimum number of colors required for this graph as 
    * determined by a graph coloring algorithm.
    * Throws an Exception if more than the maximum number of colors are required
    * to color this graph.
   */   
   public int getChromaticNumber() throws Exception{
     int highestColor = -1;
     int colorToUse = -1;
     for(ArrayList<Vertex<T>> curr : vertex){
       if(curr.get(0).getColor() == -1){
          colorToUse = getLowestUnusedColor(curr);
          curr.get(0).setColor(colorToUse);
          highestColor = max(highestColor, colorToUse);
       }
     }
     return highestColor + 1;
    // TODO: Implement this method.
   }

   private int getLowestUnusedColor(ArrayList<Vertex<T>> adj) throws Exception{
      int maxColor = -1;
      for(Vertex<T> curr : adj){
        if(curr.getColor() > maxColor){
          maxColor = curr.getColor();
        }
      }
      maxColor++; //this would be the lowest unused color
      if(maxColor > MAX_COLORS - 1) throw new Exception("Max colors have been reached"); //since color starts at 0 the maxColor val should be MAX_COLORS - 1
      return maxColor;
   }
   
   private int max(int a, int b){
     if(a > b) return a;
     return b;
   }
   
}