public class TreeBST<T extends Comparable<T>> {
    public class Node {
        // Node - Attributes
        private T data;
        private Node left;
        private Node right;

        // Node - Constructors
        public Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        // Node - Getters and Setters
        public T getData() {
            return this.data;
        }

        public Node getLeft() {
            return this.left;
        }

        public Node getRight() {
            return this.right;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public void setData(T data) {
            this.data = data;
        }

        // Node - Methods
        public boolean isLeaf() {
            return this.left == null && this.right == null;
        }

        public boolean hasLeft() {
            return this.left != null;
        }

        public boolean hasRight() {
            return this.right != null;
        }

        @Override
        public String toString() {
            return this.data.toString();
        }

    }

    // Attributes
    private Node root;

    // Constructors
    public TreeBST() {
        this.root = null;
    }

    // Getters and Setters
    public Node getRoot() {
        return this.root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    // Methods - CRUD
    public void insert(T data) {
        this.root = insert(this.root, data);
    }

    private Node insert(Node current, T data) {
        if (current == null) {
            return new Node(data);
        }

        int compare = current.getData().compareTo(data);
        if (compare < 0) {
            current.setRight(insert(current.getRight(), data));
        } else if (compare > 0) {
            current.setLeft(insert(current.getLeft(), data));
        }
        return current;
    }

    public void remove(T data) {
        this.root = remove(this.root, data);
    }

    private Node remove(Node current, T data) {
        if (current == null) {
            return null;
        }

        int compare = current.getData().compareTo(data);
        if (compare < 0) {
            current.setRight(remove(current.getRight(), data));
        } else if (compare > 0) {
            current.setLeft(remove(current.getLeft(), data));
        } else {
            if (current.isLeaf()) {
                return null;
            } else if (!current.hasLeft()) {
                return current.getRight();
            } else if (!current.hasRight()) {
                return current.getLeft();
            } else {
                Node min = min(current.getRight());
                current.setData(min.getData());
                current.setRight(remove(current.getRight(), min.getData()));
            }
        }
        return current;
    }

    // Methods - Utilities
    public boolean isEmpty() {
        return this.root == null;
    }

    public boolean contains(T data) {
        return search(data) != null;
    }

    public Node search(T data) {
        return search(this.root, data);
    }

    private Node search(Node current, T data) {
        if (current == null) {
            return null;
        }

        int compare = current.getData().compareTo(data);
        if (compare < 0) {
            return search(current.getRight(), data);
        } else if (compare > 0) {
            return search(current.getLeft(), data);
        }
        return current;
    }

    public T min() {
        return min(this.root).getData();
    }

    public T max() {
        return max(this.root).getData();
    }

    // Recursive Method
    private Node min(Node current) {
        if (current.getLeft() == null) {
            return current;
        }
        return min(current.getLeft());
    }

    // Iterative Method
    private Node max(Node current) {
        while (current.getRight() != null) {
            current = current.getRight();
        }
        return current;
    }

    // Methods - Transversal
    public String preOrder() {
        LinkedListSingle<T> nodes = new LinkedListSingle<T>();
        preOrder(this.root, nodes);
        return nodes.toString();
    }

    public String inOrder() {
        LinkedListSingle<T> nodes = new LinkedListSingle<T>();
        inOrder(this.root, nodes);
        return nodes.toString();
    }

    public String postOrder() {
        LinkedListSingle<T> nodes = new LinkedListSingle<T>();
        postOrder(this.root, nodes);
        return nodes.toString();
    }

    // Methods - Transversal - Recursive
    private void preOrder(Node current, LinkedListSingle<T> nodes) {
        if (current != null) {
            nodes.add(current.getData());
            preOrder(current.getLeft(), nodes);
            preOrder(current.getRight(), nodes);
        }
    }

    private void inOrder(Node current, LinkedListSingle<T> nodes) {
        if (current != null) {
            inOrder(current.getLeft(), nodes);
            nodes.add(current.getData());
            inOrder(current.getRight(), nodes);
        }
    }

    private void postOrder(Node current, LinkedListSingle<T> nodes) {
        if (current != null) {
            postOrder(current.getLeft(), nodes);
            postOrder(current.getRight(), nodes);
            nodes.add(current.getData());
        }
    }

    public String bfs() {
        LinkedListSingle<T> nodes = new LinkedListSingle<T>();
        QueueLinkedList<Node> queue = new QueueLinkedList<Node>();
        queue.enqueue(this.root);

        while (!queue.isEmpty()) {
            Node current = queue.dequeue();
            nodes.add(current.getData());

            if (current.hasLeft()) {
                queue.enqueue(current.getLeft());
            }

            if (current.hasRight()) {
                queue.enqueue(current.getRight());
            }
        }

        return nodes.toString();
    }

    public String dfs() {
        LinkedListSingle<T> nodes = new LinkedListSingle<T>();
        StackLinkedList<Node> stack = new StackLinkedList<Node>();
        stack.push(this.root);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            nodes.add(current.getData());

            if (current.hasRight()) {
                stack.push(current.getRight());
            }

            if (current.hasLeft()) {
                stack.push(current.getLeft());
            }
        }

        return nodes.toString();
    }

    @Override
    public String toString() {
        return bfs();
    }

}