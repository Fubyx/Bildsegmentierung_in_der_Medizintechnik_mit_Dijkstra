import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int rows = 10, columns = 10;
        int [][] picture = new int[columns][rows];
        Random rand = new Random();
        for(int i = 0; i < columns; ++i){
            for(int j = 0; j < rows; ++j){
                picture[i][j] = rand.nextInt(0, 255);
            }
        }
        //Code für Einlesen von Bild

        Graph g = new Graph();
        g.build(columns, rows, picture, 5);

        g.showGraph();

    }

    private Node[] dijkstra(Graph g, Node source, Node target){
        ArrayList<Edge> usedEdges = new ArrayList<>();
        ArrayList<Node> dijkstraQueue = new ArrayList<>();
        Node currentNode = source;
        dijkstraQueue.add(currentNode);
        while(dijkstraQueue.size()  > 0){
            if(currentNode == target){

            }
            for(Edge e: currentNode.childEdges){
                e.neighbor.dijkstraValue = currentNode.weight + e.weight;
                e.neighbor.dijkstraParent = currentNode;
            }
            currentNode = getSmallestInQueue(dijkstraQueue);
        }
    }

    private Node getSmallestInQueue (ArrayList<Node> queue){
        int smallest = Integer.MAX_VALUE;
        int index = 0;
        for(int i = 0; i < queue.size(); ++i){
            if(queue.get(i).dijkstraValue < smallest){
                smallest = queue.get(i).dijkstraValue;
                index = i;
            }
        }
        Node smallestNode = queue.get(index);
        queue.remove(smallestNode);
        return smallestNode;
    }
}
