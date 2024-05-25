import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;
import java.util.List;

public class GraphList<T extends Comparable<T>> {
    // Attributes
    public Map<T, List<T>> adjList = new HashMap<>();
    private boolean isDirected;

    // Constructors
    public GraphList(boolean isDirected) {
        this.isDirected = isDirected;
    }

    // Methods
    public void addVertex(T vertex) {
        adjList.put(vertex, new LinkedList<>());
    }

    public void addEdge(T source, T destination) {
        if (!adjList.containsKey(source)) {
            addVertex(source);
        }
        if (!adjList.containsKey(destination)) {
            addVertex(destination);
        }

        if (!adjList.get(source).contains(destination)) {
            adjList.get(source).add(destination);

            if (!isDirected) {
                adjList.get(destination).add(source);
            }
        }
    }

    public void removeVertex(T vertex) {
        adjList.values().forEach(e -> e.remove(vertex));
        adjList.remove(vertex);
    }

    public int getVertexCount() {
        return adjList.keySet().size();
    }

    public int getEdgeCount() {
        int count = 0;
        for (T vertex : adjList.keySet()) {
            count += adjList.get(vertex).size();
        }
        if (!isDirected) {
            count /= 2;
        }
        return count;
    }

    public boolean hasVertex(T vertex) {
        return adjList.containsKey(vertex);
    }

    public boolean hasEdge(T source, T destination) {
        return adjList.containsKey(source) && adjList.get(source).contains(destination);
    }

    public List<T> getNeighbors(T vertex) {
        return adjList.get(vertex);
    }

    // Methods - Traversal
    public List<T> breadthFirstSearch(T start) {
        List<T> result = new LinkedList<>();
        LinkedList<T> queue = new LinkedList<>();
        Map<T, Boolean> visited = new HashMap<>();

        queue.add(start);
        visited.put(start, true);

        while (!queue.isEmpty()) {
            T vertex = queue.poll();
            result.add(vertex);

            for (T neighbor : getNeighbors(vertex)) {
                if (visited.get(neighbor) == null || !visited.get(neighbor)) {
                    queue.add(neighbor);
                    visited.put(neighbor, true);
                }
            }
        }

        return result;
    }

    @Override
    public String toString() {
        T start = adjList.keySet().iterator().next();
        return breadthFirstSearch(start).toString();
    }

}