//By Nyla Worker and Sarah Ogle

import java.util.*;


public class Graph<T> implements BasicGraphADT<T> {
  
  /*******************
   * Add a vertex to this graph with given label
   * @return Whether the vertex was successfully added
   ********************/
  public int count;
  
  //create a hashmap
  public HashMap<T,Vertex<T>>  hush = new HashMap<T,Vertex<T>>();
  public int edgeNum = 0;
  public ArrayList<T> doggy = new ArrayList<T>();

  /*******************
   * Add a vertex to this graph with given label
   * @return Whether the vertex was successfully added
   ********************/
  public boolean addVertex(T vert) {
    while(vert != null ) { 
      //System.out.print(vert);
      Vertex<T> verty = new Vertex<T>(vert);
      //System.out.println(verty.getLabel());
      hush.put(vert, verty);
      count++;
      if(!doggy.contains(vert)) {
        doggy.add(vert);
      }
      return true;
    }
    return false;
  }

  /******************
   * Add an edge to this graph between the two given labels
   * @return Whether the edge was successfully added
   ********************/
  public boolean addEdge(T beg, T end) {
    if(hush.containsKey(beg) && hush.containsKey(end)){
      //System.out.println("hi");
      hush.get(beg).addNeighbor(hush.get(end));
      hush.get(end).addNeighbor(hush.get(beg));
      edgeNum++;
      return true;
    }
    return false;
  }

  /******************
   * Tests whether a vertex exists in the graph
   * @return Whether the vertex exists
   ********************/
  public boolean hasVertex(T vert) {
    if(hush.containsKey(vert)) {
      return true;
    }
    return false;
  }

  /******************
   * Tests whether an edge exists in the graph
   * @return Whether the edge exists
   ********************/
  public boolean hasEdge(T beg, T end) {
    if(hush.containsKey(beg) && hush.containsKey(end)) {
      List<Vertex<T>> temp = hush.get(beg).getNeighbors();
      for(int i = 0; i < temp.size(); i++) {
        if(temp.get(i) == end) {
          return true;
        }
      }
    }
  	return false;
  }

  /*****************
   * Gets a list containing all the neighbors of a given vertex
   * @return the neighbor list as a java List
   *********************/
  public List<Vertex<T>> getNeighbors(T vert) {
    if(hush.containsKey(vert)) {
      List<Vertex<T>> nay = hush.get(vert).getNeighbors();
      return nay;
    }
    return null;
  }

  /****************************
   * Gets the vertex object associated with the given label
   * @return the vertex
   ************************/
  public Vertex<T> getVertex(T lab) {
    if(hush.containsKey(lab)) {
      return hush.get(lab);
    }
  	return null;
  }

  /*****************
   * Tests if the graph is empty
   * @return Whether the graph is empty
   *******************/
  public boolean isEmpty() {
    if(hush.isEmpty()){
      return true;
    }
    return false;
  }

  /********************
   * Gets the number of vertices
   * @return The number of vertices
   *******************/
  public int getNumVertices() {
  	return count;
  }

  /********************
   * Gets the number of edges
   * @return The number of edges
   *********************/
  public int getNumEdges() {
  	return edgeNum;
  }

  /**************
   * Clear all edges and vertices from the graph
   ********************/
  public void clear() {
    count = 0;
    edgeNum = 0;
    hush.clear();
  }

  public void stringMaker() {
    for(int i = 0; i < doggy.size(); i++){
      T tempKey = doggy.get(i);
      List<Vertex<T>> listaVecinos = hush.get(tempKey).getNeighbors();
      System.out.print(tempKey+ "   ->   " );
      for(int j = 0; j < listaVecinos.size(); j++){
        System.out.print(listaVecinos.get(j).getLabel() + "    ");
      }
      System.out.println( );
    }
  }

  //main method to test 
  public static void main(String[] args) {
    Graph<String> gr = new Graph<String>();
    gr.addVertex("bar");
    gr.addVertex("baz");
    gr.addVertex("ninja");
    gr.addVertex("foo");
    gr.addVertex("robot");
    gr.addEdge("foo", "bar");
    gr.addEdge("foo", "baz");
    gr.addEdge("foo", "ninja");
    gr.addEdge("ninja", "robot");
    gr.stringMaker();
  }

}