import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class GraphMatrix<T> {
    // Attributes
    private T[] vertices;
    private int[][] edges;
    private int maxVertices;
    private boolean isDirected;
    private boolean isWeighted;

    // Constructors
    @SuppressWarnings("unchecked")
    public GraphMatrix(int maxVertices, boolean isDirected, boolean isWeighted) {
        this.maxVertices = maxVertices;
        this.isDirected = isDirected;
        this.isWeighted = isWeighted;
        this.vertices = (T[]) new Object[maxVertices];
        this.edges = new int[maxVertices][maxVertices];
    }

    // Methods
    public int addVertex(T vertex) {
        for (int i = 0; i < maxVertices; i++) {
            if (vertices[i] == null) {
                vertices[i] = vertex;
                return i;
            }
        }
        return -1;
    }

    public int getVertexIndex(T vertex) {
        for (int i = 0; i < maxVertices; i++) {
            if (vertices[i] == vertex) {
                return i;
            }
        }
        return -1;
    }

    public void addEdge(T source, T destination, int weight) {
        int sourceIndex = getVertexIndex(source);
        int destinationIndex = getVertexIndex(destination);

        if (sourceIndex == -1) {
            sourceIndex = addVertex(source);
        }

        if (destinationIndex == -1) {
            destinationIndex = addVertex(destination);
        }

        if (sourceIndex != -1 && destinationIndex != -1) {
            if (isWeighted) {
                edges[sourceIndex][destinationIndex] = weight;
            } else {
                edges[sourceIndex][destinationIndex] = 1;
            }

            if (!isDirected) {
                edges[destinationIndex][sourceIndex] = edges[sourceIndex][destinationIndex];
            }
        }
    }

    public void removeVertex(T vertex) {
        int vertexIndex = getVertexIndex(vertex);

        if (vertexIndex != -1) {
            for (int i = 0; i < maxVertices; i++) {
                edges[i][vertexIndex] = 0;
                edges[vertexIndex][i] = 0;
            }
            vertices[vertexIndex] = null;
        }
    }

    public int getVertexCount() {
        int count = 0;
        for (int i = 0; i < maxVertices; i++) {
            if (vertices[i] != null) {
                count++;
            }
        }
        return count;
    }

    public int getEdgeCount() {
        int count = 0;
        for (int i = 0; i < maxVertices; i++) {
            for (int j = 0; j < maxVertices; j++) {
                if (edges[i][j] != 0) {
                    count++;
                }
            }
        }
        if (!isDirected) {
            count /= 2;
        }
        return count;
    }

    public boolean hasVertex(T vertex) {
        return getVertexIndex(vertex) != -1;
    }

    public boolean hasEdge(T source, T destination) {
        int sourceIndex = getVertexIndex(source);
        int destinationIndex = getVertexIndex(destination);

        if (sourceIndex != -1 && destinationIndex != -1) {
            return edges[sourceIndex][destinationIndex] != 0;
        }
        return false;
    }

    public int getWeight(T source, T destination) {
        int sourceIndex = getVertexIndex(source);
        int destinationIndex = getVertexIndex(destination);

        if (sourceIndex != -1 && destinationIndex != -1) {
            return edges[sourceIndex][destinationIndex];
        }
        return -1;
    }

    // Methods - Traversal
    public List<T> getNeighbors(T vertex) {
        List<T> neighbors = new ArrayList<>();
        int vertexIndex = getVertexIndex(vertex);

        if (vertexIndex != -1) {
            for (int i = 0; i < maxVertices; i++) {
                if (edges[vertexIndex][i] != 0) {
                    neighbors.add(vertices[i]);
                }
            }
        }
        return neighbors;
    }

    public List<T> depthFirstSearch(T start) {
        List<T> visited = new ArrayList<>();
        Stack<T> stack = new Stack<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            T vertex = stack.pop();

            if (!visited.contains(vertex)) {
                visited.add(vertex);
                List<T> neighbors = getNeighbors(vertex);

                for (T neighbor : neighbors) {
                    stack.push(neighbor);
                }
            }
        }
        return visited;
    }

    public List<T> breadthFirstSearch(T start) {
        List<T> visited = new ArrayList<>();
        Queue<T> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            T vertex = queue.poll();

            if (!visited.contains(vertex)) {
                visited.add(vertex);
                List<T> neighbors = getNeighbors(vertex);

                for (T neighbor : neighbors) {
                    queue.add(neighbor);
                }
            }
        }
        return visited;
    }

    @Override
    public String toString() {
        T start = vertices[0];
        return breadthFirstSearch(start).toString();
    }

}