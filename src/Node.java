import java.util.ArrayList;

public class Node {
    int x, y, weight;
    int dijkstraValue;
    Node dijkstraParent = null;


    ArrayList<Node> childNodes;

    Node(int x, int y, int weight){
        this.x = x;
        this.y = y;
        this.weight = weight;
        this.dijkstraValue = Integer.MAX_VALUE;
    }
}
