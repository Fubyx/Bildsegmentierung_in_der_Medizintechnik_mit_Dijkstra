import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
    Node startNode, endNode;

    Node [][] nodes;
    int columns;

    public void build (int columns, int rows, int [][]picture, int pixel){
        this.columns = columns;
        nodes = new Node[columns][rows];
        for(int i = 0; i < columns; ++i){
            for(int j = 0; j < rows; ++j){
                nodes[i][j] = new Node(i, j, picture[i][j]);
            }
        }

        for(int i = 0; i < columns - 1; ++i){
            for(int j = 0; j < rows; ++j){
                nodes[i][j].childEdges = new ArrayList<>();
                for(int k = 0; k < rows; ++k){
                    int l;
                    if(k > j){
                        l = k - j;
                    }else{
                        l = j - k;
                    }
                    nodes[i][j].childEdges.add(new Edge(nodes[i][j], nodes[i + 1][k], l));
                }
            }
        }


        startNode = new Node(-1, rows/2, 0);
        endNode = new Node(columns, rows/2, 0);

        for(int i = 0; i < rows; ++i){
            nodes[columns - 1][i].childEdges = new ArrayList<>();
            nodes[columns - 1][i].childEdges.add(new Edge(nodes[columns - 1][i], endNode, 0));
            startNode.childEdges = new ArrayList<>();
            startNode.childEdges.add(new Edge(startNode, nodes[0][i], 0));
        }
        buildChildren(0, nodes, startNode);
    }

    public void buildChildren(int x, Node [][]picture, Node start){
        if(x > columns){
            return;
        }


        start.childNodes = new ArrayList<>();
        start.childEdges = new ArrayList<>();

        if(x == columns){
            start.childNodes.add(endNode);
            return;
        }
        start.childNodes.addAll(Arrays.asList(picture[x]));

        for(int i = 0; i < start.childNodes.size(); ++i){
            buildChildren(x + 1, picture, start.childNodes.get(i));
        }

        /*
        ArrayList<Node> children = new ArrayList<>();
        for(int i = 0; i < picture[x].length; ++i){
            int j;
            if(i > startingPoint){
                j = i - startingPoint;
            }else{
                j = startingPoint - i;
            }
            Node n = new Node(x, i, picture[x][i].weight + j);
            n.setChildNodes(buildChildren(x + 1, picture, startingPoint));
            children.add(n);
        }
        return children;*/
    }

    public void showGraph(){
        startNode.showNode();
    }
}
