import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
    Node startNode;
    int columns;

    public void build (int columns, int rows, int [][]picture, int pixel){
        this.columns = columns;
        Node [][] nodes = new Node[columns][rows];
        for(int i = 0; i < columns; ++i){
            for(int j = 0; j < rows; ++j){
                nodes[i][j] = new Node(i, j, picture[i][j]);
            }
        }

        for(int i = 0; i < columns +- 1; ++i){
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

        startNode = nodes[0][5];
        buildChildren(1, nodes, startNode);
    }

    public void buildChildren(int x, Node [][]picture, Node start){
        if(x >= columns){
            return;
        }

        start.childNodes = new ArrayList<>();
        start.childEdges = new ArrayList<>();
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
