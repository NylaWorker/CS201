import java.util.*;

/***********************
 * Represents an undirected weighted graph
 *****/
public class WGraph<T> extends Graph<T> implements WeightedGraphADT<T> {
  
   public int count = 0;

   public HashMap<T,Vertex<T>>  hush = new HashMap<T,Vertex<T>>();

   public int edgeNum = 0;

   public ArrayList<T> doggy = new ArrayList<T>();
   
  /******************
   * Add an edge to this graph between the two given labels with the given weight
   * @return Whether the edge was successfully added
   ********************/
  public boolean addEdgeW(T beg, T end, double weight) {
    Vertex begV = new Vertex(beg);
    Vertex endV = new Vertex(end);
    //add edge is the beg and end are in your hashmap (it is having trouble reaching inside this even when the keys are in the hashmap)
    if(hush.containsKey(beg) && hush.containsKey(end)) {
      //System.out.println("yes");
      //make them neighbors
      hush.get(beg).addNeighbor(hush.get(end));
      hush.get(end).addNeighbor(hush.get(beg));
      //assign a weight to each
      begV.setDistance(weight);
      endV.setDistance(weight);
      edgeNum++;
      return true;
    }
    return false;
  }
  
  /******************
   * Gets the weight of the edge between two vertices
   * @return the weight, or Double.POSITIVE_INFINITY if no edge exists
   ********************/
  public double getEdgeWeight(T beg, T end) {
    //make vertices
    Vertex begV = new Vertex(beg);
    Vertex endV = new Vertex(end);
    return begV.getDistance() + endV.getDistance();
  }
  
  //main method to test
  public static void main(String[] args) {
    WGraph<String> gr = new WGraph<String>();
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
    gr.addEdgeW("foo","robot",12.0);
    //gr.addEdgeW("foo","robot",12.0);
    //Vertex<String> foo = new Vertex<String>();
    Vertex foo = gr.getVertex("foo");
    System.out.println(gr.getEdgeWeight("foo","robot"));
    }

}