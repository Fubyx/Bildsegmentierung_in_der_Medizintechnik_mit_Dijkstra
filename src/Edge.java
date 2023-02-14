public class Edge {
    Node root, neighbor;
    int weight;

    Edge(Node root, Node neighbor, int weight){
        this.root = root;
        this.neighbor = neighbor;
        this.weight = weight;
    }
}
