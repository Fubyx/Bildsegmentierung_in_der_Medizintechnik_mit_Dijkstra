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

        for(int i = 0; i < 10; ++i){
            for(int j = 0; j < 10; ++j){
                System.out.print(picture[i][j] + "\t");
            }
            System.out.println("");
        }
        //Code für Einlesen von Bild

        Graph g = new Graph();
        g.build(columns, rows, picture, 5);

        //g.showGraph();

        System.out.println("\n");
        Node []nodes = dijkstra(g.startNode, g.nodes[9][5], 10);

        for(int i = 0; i < nodes.length; ++i){
            System.out.println(nodes[i].weight);
        }

    }

    public static Node[] dijkstra(Node source, Node target, int pixelWidthAmount){
        ArrayList<Node> dijkstraQueue = new ArrayList<>();
        Node currentNode = source;
        dijkstraQueue.add(currentNode);
        source.dijkstraValue = 0;
        while(dijkstraQueue.size() > 0){
            currentNode = getSmallestInQueue(dijkstraQueue);
            for(Edge e: currentNode.childEdges){
                if(e.neighbor.dijkstraValue > currentNode.dijkstraValue + e.weight){
                    e.neighbor.dijkstraValue = currentNode.dijkstraValue + e.weight;
                    e.neighbor.dijkstraParent = currentNode;
                }else if(e.neighbor.dijkstraParent == null){
                    e.neighbor.dijkstraParent = currentNode;
                }
                e.neighbor.dijkstraParent = currentNode;
                if(!dijkstraQueue.contains(e.neighbor)){
                    dijkstraQueue.add(e.neighbor);
                }
            }

        }

        Node [] retVals = new Node[pixelWidthAmount];
        currentNode = target;
        int i = pixelWidthAmount - 1;
        while (i >= 0){
            System.out.println(i);
            retVals[i] = currentNode;
            currentNode = currentNode.dijkstraParent;
            --i;
        }
        return retVals;
    }

    private static Node getSmallestInQueue (ArrayList<Node> queue){
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
