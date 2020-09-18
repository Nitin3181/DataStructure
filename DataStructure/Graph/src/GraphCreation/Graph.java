package GraphCreation;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Graph {

    Map<Vertices, List<Vertices>> verticesListMap = new HashMap<>();

    void addVertex(String label) {
        verticesListMap.put(new Vertices(label), new ArrayList<>());
    }

    void removeVertex(String label) {
        Vertices v = new Vertices(label);
        //verticesListMap.entrySet().forEach(e -> e.getValue().stream().anyMatch(v));
        verticesListMap.values().stream().forEach(e -> e.remove(v));
        verticesListMap.remove(v);
    }


    void addEdge(String label1, String label2) {
        Vertices v1 = new Vertices(label1);
        Vertices v2 = new Vertices(label2);
        verticesListMap.get(v1).add(v2);
        verticesListMap.get(v2).add(v1);
    }

    void removeEdge(String label1, String label2) {
        Vertices v1 = new Vertices(label1);
        Vertices v2 = new Vertices(label2);
        verticesListMap.get(v1).remove(v2);
        verticesListMap.get(v2).remove(v1);
    }


    Graph createGraph() {
        Graph graph = new Graph();
        graph.addVertex("Bob");
        graph.addVertex("Alice");
        graph.addVertex("Mark");
        graph.addVertex("Rob");
        graph.addVertex("Maria");
        graph.addVertex("breaker");
        graph.addEdge("Bob", "Alice");
        graph.addEdge("Bob", "breaker");
        graph.addEdge("Mark", "Alice");
        graph.addEdge("Alice", "Maria");
        graph.addEdge("Rob", "Maria");
        graph.addEdge("Rob", "Bob");
        return graph;
    }

    List<Vertices> getAdjacentVertices(String label) {
        Vertices v = new Vertices(label);
        return verticesListMap.get(v);
    }

    List<Vertices> breadthFirstTraversal(Graph graph, Vertices root) {
        List<Vertices> visited = new ArrayList<>();
        Queue<Vertices> queue = new LinkedList<>();
        queue.add(root);
        visited.add(root);
        while (!queue.isEmpty()) {
            Vertices vertices = queue.remove();
            if (graph.getAdjacentVertices(vertices.label) != null) {
                for (Vertices v : graph.getAdjacentVertices(vertices.label)) {
                    if (!visited.contains(v)) {
                        visited.add(v);
                        queue.add(v);
                    }
                }
            }
        }
        return visited;
    }

    List<Vertices> depthFirstTraversal(Graph graph, Vertices root) {
        List<Vertices> visited = new ArrayList<>();
        Stack<Vertices> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            Vertices vertices = stack.pop();
            if (!visited.contains(vertices)) {
                visited.add(vertices);
                graph.getAdjacentVertices(vertices.label).forEach(vertices1 -> stack.push(vertices1));
            }
        }
        return visited;
    }


    public static void main(String[] args) {
        Graph graph = new Graph();
        Graph finalGraph;
        finalGraph = graph.createGraph();
        System.out.println(finalGraph.getAdjacentVertices("Rob").stream().count());
        /*//finalGraph.removeVertex("Mark");
        finalGraph.removeEdge("Mark", "Rob");
        System.out.println(finalGraph.getAdjacentVertices("Rob").stream().count());*/
        List<Vertices> resultList = graph.breadthFirstTraversal(finalGraph, new Vertices("Bob"));
        for (Vertices v : resultList) {
            System.out.println(v.label);
        }
        List<Vertices> depthFirstResultList = graph.depthFirstTraversal(finalGraph, new Vertices("Bob"));
        for (Vertices v : depthFirstResultList) {
            System.out.println(v.label);
        }
    }


}
