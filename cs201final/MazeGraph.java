import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.*;

/*****************
 * This class reads in a maze (as created by Maze.java)
 * as a graph, then solves it, then prints out its solution
 *
 * To run it:
 * java MazeGraph [-w] mazefile.txt
 *
 *Edited by Sarah and Nyla 
 *
 * Use -w if you want to take weights into account
 *
 * Andy Exley
 */
public class MazeGraph {

  public static void main(String[] args) {
    String fname = null;
    String startvertex = null;
    String endvertex = null;
    boolean weighted = false;

  
    if(args.length < 3 || (args[0].equals("-w") && args.length < 4)) {
      System.err.println("Usage:\njava MazeGraph [-w] mazefile.txt start end");
      System.exit(1);
    } else if (args[0].equals("-w")) {
      fname = args[1];
      startvertex = args[2];
      endvertex = args[3];
      weighted = true;
    } else {
      fname = args[0];
      startvertex = args[1];
      endvertex = args[2];
    }
    
    if(!weighted) {
      BasicGraphADT<String> gmaze = loadMaze(fname);
      List<Vertex<String>> path1 = solveMazeDepthFirst(gmaze, startvertex, endvertex);
      System.out.println("Solution using DFS:");
      for(int i = 0; path1 != null && i < path1.size(); i++) {
        System.out.println(path1.get(i));
      }

      // reload maze in case the graph needs to be reset
      BasicGraphADT<String> gmaze2 = loadMaze(fname);
      List<Vertex<String>> path2 = solveMazeBreadthFirst(gmaze2, startvertex, endvertex);
      System.out.println("Solution using BFS:");
      for(int i = 0; path2 != null && i < path2.size(); i++) {
        System.out.println(path2.get(i).getLabel());
      }
    } else {


      //changed stuff here we load the normal maze
      // the weighed works differentely as what was expected. 


      BasicGraphADT<String> gmaze = loadMaze(fname);
      List<Vertex<String>> path3 = solveMaze(gmaze, startvertex, endvertex);
      System.out.println("Solution with least weight:");
      for(int i = 0; i < path3.size(); i++) {
        System.out.println(path3.get(i));
      }
    }

    MazeGraph mazy = new MazeGraph();
    BasicGraphADT<String> maz = mazy.loadMaze("maze2.txt");
    // List<Vertex<String>> thing = mazy.solveMazeBreadthFirst(maze, "b3", "x1");
    List<Vertex<String>> bfirst = mazy.solveMazeBreadthFirst(maz, startvertex, endvertex);
    List<Vertex<String>> dfirst = mazy.solveMazeDepthFirst(maz, startvertex, endvertex);
    
    //System.out.println("This is the traversal  ");
    //System.out.println(bfirst.size() == dfirst.size());
    // for(int i = 0; i < bfirst.size(); i++) {
    // 	System.out.print(bfirst.get(i).equals(dfirst.get(i)));
    // }
    // mymaze.
    // for(int i = 0; i <)
    // System.out.println()

  }

  /*********************
   * This method loads a maze from a given file with name fname
   *********************/
  public static BasicGraphADT<String> loadMaze(String fname) {
    BasicGraphADT<String> mymaze = new Graph<String>(); 
    String[] arr = null;
    File tfile = new File(fname);
    try {
      Scanner scan = new Scanner(tfile);
      //gets the dimension of the array
      int numb = scan.nextInt();
      //System.out.println(numb);
      //go to next line because the first line has dimensions
      scan.nextLine();
      int colCount = 0;
      int rowCount = 0;
      //make mazeMatrix with same dimensions of maze
      String[][] mazeMatrix = new String[numb][numb];
      //go through until there is no data
      while(scan.hasNext()) {
      	//go through each column and add to matrix and graph
        while(colCount != numb) {
          String tempString = scan.next();
          if(tempString.charAt(tempString.length()-1) != '0') {
            mazeMatrix[rowCount][colCount] = tempString;
            mymaze.addVertex(tempString);// tempString.charAt(0)?? should we only add the first char?
            colCount++;
          }
          else {
            mazeMatrix[rowCount][colCount] = null;
            colCount++;
          }
        }
        colCount = 0;
        rowCount++;
      }

      //This code goes through the matrix and checks if it is a vertex of the graph. If true it goes and checks
      //the neighbors, if the neighbors are something it adds them as edges. 

      for (int row = 1; row < mazeMatrix.length-1; row++ ) {
        for ( int col = 1; col < mazeMatrix.length-1; col++ ) {
          String thingy = mazeMatrix[col][row];
          if(mymaze.hasVertex(thingy)){
            if(mazeMatrix[col-1][row] != null){
              mymaze.addEdge(thingy, mazeMatrix[col-1][row]);
            }
            if(mazeMatrix[col+1][row] != null){
              mymaze.addEdge(thingy, mazeMatrix[col+1][row]);
            }
            if(mazeMatrix[col][row+1] != null){
              mymaze.addEdge(thingy, mazeMatrix[col][row+1]);
            }
            if(mazeMatrix[col][row-1] != null){
              mymaze.addEdge(thingy, mazeMatrix[col][row-1]);
            }
          }
        }
      }
    } catch(FileNotFoundException e) {
      System.err.println("Error, cannot find file: " + fname);
      System.exit(1);
    }
    //mymaze.stringMaker();

    return mymaze;
  }

  /*********************
   * This method loads a maze from a given file with name fname as
   * a weighted graph
   *********************/
  public static WeightedGraphADT<String> loadWeightedMaze(String fname) {
    WeightedGraphADT<String> mymaze = null; // change this to initalize your graph
    // build your maze based on the given file

    // Sarah and Nyla tried doing this in a different way. 

    return mymaze;
  }  

  /******** 
   * This method should use a breadth-first traversal to find a path through the 
   * maze, then return that path.
   ******/
  public static List<Vertex<String>> solveMazeBreadthFirst(BasicGraphADT<String> maze, String startvert, String endvert) {
    // Use a breadth-first search to find a path through the maze
    //create empty queue newQ
    Queue<Vertex<String>> newQ = new LinkedList<Vertex<String>>();
    //Vertex<String> currentVert = new Vertex<String>("anystring");
    Vertex<String> currentVert = maze.getVertex(startvert);
    Vertex<String> finalVert = maze.getVertex(endvert);

    //create empty visitedlist vertL
    List<Vertex<String>> vertL = new LinkedList<Vertex<String>>();
    List<Vertex<String>>  ngbh = new LinkedList<Vertex<String>>();
    List<Vertex<String>> p = new ArrayList<Vertex<String>>();
    List<Vertex<String>> pfinal = new ArrayList<Vertex<String>>();

    List<Vertex<String>> ptemp = null;
    

    newQ.add(currentVert);

    while(!newQ.isEmpty()) {
      
      currentVert = newQ.remove();
      
      
      ngbh = currentVert.getNeighbors(); //maze.getVertex(doggy).getNeighbors();
      
      vertL.add(currentVert);
      if (currentVert.equals(finalVert)) {
        pfinal = currentVert.getPath();
        pfinal.add(currentVert);
        currentVert.setPath(pfinal); 
        return currentVert.getPath();
      }
      else {
        for(int i = 0; i < ngbh.size(); i++ ) {

          if(!vertL.contains(ngbh.get(i)) && !newQ.contains(ngbh.get(i))){

            p = currentVert.getPath();
            ptemp = new ArrayList<Vertex<String>>();

            for (int h = 0; h<p.size() ; h++ ) {
              ptemp.add(p.get(h));
              //this creates a clone of the path. This is done in order to avoid it deleting itself. 
            }
            ptemp.add(currentVert);
            newQ.add(ngbh.get(i));
            ngbh.get(i).setPath(ptemp);
            
          } 
        }
      }
    }
    return null;
  }

  /******** 
   * This method should use a depth-first traversal to find a path through the 
   * maze, then return that path.
   ******/
  public static List<Vertex<String>> solveMazeDepthFirst(BasicGraphADT<String> maze, String startvert, String endvert) {
  	//System.out.print("hi");
    Vertex<String> currentVert = maze.getVertex(startvert);
    Vertex<String> finalVert = maze.getVertex(endvert);
    Stack<Vertex<String>> newS = new Stack<Vertex<String>>();
    List<Vertex<String>> vertL = new LinkedList<Vertex<String>>();
    List<Vertex<String>>  ngbh = new LinkedList<Vertex<String>>();
    newS.push(currentVert);
    while(!newS.isEmpty()) {
     currentVert = newS.pop();
     currentVert.setPath(vertL);
     if(!vertL.contains(currentVert)) {
     	vertL.add(currentVert);
     	ngbh = currentVert.getNeighbors(); 
     	if(currentVert.equals(finalVert)) {
     	  return currentVert.getPath();
     	}
       else {
         for(int j = 0; j < ngbh.size(); j++) {
         	newS.push(ngbh.get(j));
         }
       }
     }
  }
  return null;
}

  /******** 
   * This method should use Dijkstra's algorithm to find the shortest cost path through the 
   * maze, then return that path.
   ******/

  /*
  This is an attempt to use the weighed method, without the extension of the grph adt.
  The calculated lengths are too long, but seem to be working. 
  I tested this and is returning a path that is working in small scale, but I can't attest,it is fully working. 

  There is also code in the file of the attemp of creating the weighed methods. 

  */
  public static List<Vertex<String>> solveMaze(BasicGraphADT<String> maze, String startvert, String endvert) {
  	Vertex<String> currentVert = maze.getVertex(startvert);
    Vertex<String> finalVert = maze.getVertex(endvert);
    List<Vertex<String>>  ngbh = new LinkedList<Vertex<String>>();
    List<Vertex<String>>  path = new LinkedList<Vertex<String>>();
    PriorityQueue<Vertex<String>> priQ = new PriorityQueue<Vertex<String>>();
    List<Vertex<String>> p = new ArrayList<Vertex<String>>();
    List<Vertex<String>> pfinal = new ArrayList<Vertex<String>>();
    List<Vertex<String>> ptemp = null;
    currentVert.setDistance(0);

  	priQ.add(currentVert);
    //currentVert.setPath(currentVert);


  	while(!priQ.isEmpty()) {
      currentVert = priQ.remove();
      ngbh = currentVert.getNeighbors(); 
  	  if(currentVert.equals(finalVert)) {
        return currentVert.getPath();
  	  }
      else {
 	  	  for(int j = 0; j < ngbh.size(); j++) {
          if(!priQ.contains(ngbh.get(j))){
            Vertex<String> stuff = ngbh.get(j);
            double d1 = (double) stuff.getLabel().charAt(stuff.getLabel().length()-1);
            double d2 = (double) currentVert.getLabel().charAt(currentVert.getLabel().length()-1);
      	    //stuff.setDistance(currentVert.getDistance() + maze.getEdgeWeight(currentVert, stuff));
            stuff.setDistance(currentVert.getDistance() + d1 + d2);
            p = currentVert.getPath();
            ptemp = new ArrayList<Vertex<String>>();
            for (int h = 0; h<p.size() ; h++ ) {
              ptemp.add(p.get(h));
            }
            ptemp.add(currentVert);
            stuff.setPath(ptemp);
            priQ.add(stuff);
          }
        }
      }
    }
    return null;
  }
}

  
