import java.util.ArrayList;

public class Node {
    int x, y, weight;
    int dijkstraValue = 0;
    Node dijkstraParent = null;


    ArrayList<Node> childNodes;
    ArrayList<Edge> childEdges;

    Node(int x, int y, int weight){
        this.x = x;
        this.y = y;
        this.weight = weight;
    }


    public void showNode(){
        System.out.println("X = " + x + "\tY = " + y + "\tW = " + weight + "\t Children:");
        if(childNodes == null)
            return;
        for(int i = 0; i < childNodes.size(); ++i){
            childNodes.get(i).showNode();
        }
        System.out.println("\n");
    }
}
