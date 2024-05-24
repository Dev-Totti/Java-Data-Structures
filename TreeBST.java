public class TreeBST<T extends Comparable<T>> {
    // Node - Subclass
    public class Node {
        private T data;
        private Node left;
        private Node right;

        public Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        public T getData() {
            return data;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public void setData(T data) {
            this.data = data;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasLeft() {
            return left != null;
        }

        public boolean hasRight() {
            return right != null;
        }

        public boolean hasBoth() {
            return left != null && right != null;
        }

        @Override
        public String toString() {
            return data.toString();
        }

    }

    // Attributes
    public Node root;

    // Constructors
    public TreeBST() {
        this.root = null;
    }

    // Methods - CRUD
    public void insert(T data) {
        root = insert(root, data);
    }

    private Node insert(Node current, T data) {
        if (current == null) {
            return new Node(data);
        }

        int compare = data.compareTo(current.getData());
        if (compare < 0) {
            current.setLeft(insert(current.getLeft(), data));
        } else if (compare > 0) {
            current.setRight(insert(current.getRight(), data));
        }

        return current;
    }

    // Remove using successor
    public void remove(T data) {
        root = remove(root, data);
    }

    private Node remove(Node current, T data) {
        if (current == null) {
            return null;
        }

        int compare = data.compareTo(current.getData());
        if (compare < 0) {
            current.setLeft(remove(current.getLeft(), data));
        } else if (compare > 0) {
            current.setRight(remove(current.getRight(), data));
        } else {
            if (current.isLeaf()) {
                return null;
            } else if (current.hasBoth()) {
                Node successor = findMin(current.getRight());
                current.setData(successor.getData());
                current.setRight(remove(current.getRight(), successor.getData()));
            } else {
                return current.hasLeft() ? current.getLeft() : current.getRight();
            }
        }

        return current;
    }

    // Delete using predecessor
    public void delete(T data) {
        root = delete(root, data);
    }

    private Node delete(Node current, T data) {
        if (current == null) {
            return null;
        }

        int compare = data.compareTo(current.getData());
        if (compare < 0) {
            current.setLeft(delete(current.getLeft(), data));
        } else if (compare > 0) {
            current.setRight(delete(current.getRight(), data));
        } else {
            if (current.isLeaf()) {
                return null;
            } else if (current.hasBoth()) {
                Node predecessor = findMax(current.getLeft());
                current.setData(predecessor.getData());
                current.setLeft(delete(current.getLeft(), predecessor.getData()));
            } else {
                return current.hasLeft() ? current.getLeft() : current.getRight();
            }
        }

        return current;
    }

    // Methods - Binary Search Tree
    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(T data) {
        return search(data) != null;
    }

    public Node search(T data) {
        return search(root, data);
    }

    private Node search(Node current, T data) {
        if (current == null) {
            return null;
        }

        int compare = data.compareTo(current.getData());
        if (compare < 0) {
            return search(current.getLeft(), data);
        } else if (compare > 0) {
            return search(current.getRight(), data);
        }

        return current;
    }

    public Node findMin() {
        return findMin(root);
    }

    public Node findMin(Node current) {
        if (!current.hasLeft()) {
            return current;
        }
        return findMin(current.getLeft());
    }

    public Node findMax() {
        return findMax(root);
    }

    public Node findMax(Node current) {
        if (!current.hasRight()) {
            return current;
        }
        return findMax(current.getRight());
    }

    // Methods - Traversal
    public LinkedListSingle<Node> inOrder() {
        LinkedListSingle<Node> nodes = new LinkedListSingle<>();
        inOrder(root, nodes);
        return nodes;
    }

    private void inOrder(Node current, LinkedListSingle<Node> nodes) {
        if (current != null) {
            inOrder(current.getLeft(), nodes);
            nodes.add(current);
            inOrder(current.getRight(), nodes);
        }
    }

    public LinkedListSingle<Node> preOrder() {
        LinkedListSingle<Node> nodes = new LinkedListSingle<>();
        preOrder(root, nodes);
        return nodes;
    }

    private void preOrder(Node current, LinkedListSingle<Node> nodes) {
        if (current != null) {
            nodes.add(current);
            preOrder(current.getLeft(), nodes);
            preOrder(current.getRight(), nodes);
        }
    }

    public LinkedListSingle<Node> postOrder() {
        LinkedListSingle<Node> nodes = new LinkedListSingle<>();
        postOrder(root, nodes);
        return nodes;
    }

    private void postOrder(Node current, LinkedListSingle<Node> nodes) {
        if (current != null) {
            postOrder(current.getLeft(), nodes);
            postOrder(current.getRight(), nodes);
            nodes.add(current);
        }
    }

    public LinkedListSingle<Node> bfs() {
        LinkedListSingle<Node> nodes = new LinkedListSingle<>();
        QueueLinkedList<Node> queue = new QueueLinkedList<>();
        queue.enqueue(root);

        while (!queue.isEmpty()) {
            Node current = queue.dequeue();
            nodes.add(current);

            if (current.hasLeft()) {
                queue.enqueue(current.getLeft());
            }

            if (current.hasRight()) {
                queue.enqueue(current.getRight());
            }
        }

        return nodes;
    }

    public LinkedListSingle<Node> dfs() {
        LinkedListSingle<Node> nodes = new LinkedListSingle<>();
        StackLinkedList<Node> stack = new StackLinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            nodes.add(current);

            if (current.hasRight()) {
                stack.push(current.getRight());
            }

            if (current.hasLeft()) {
                stack.push(current.getLeft());
            }
        }

        return nodes;
    }

    public void print() {
        QueueLinkedList<Node> queue = new QueueLinkedList<Node>();
        queue.enqueue(root);
        queue.enqueue(null);

        while (!queue.isEmpty()) {
            Node current = queue.dequeue();
            if (current == null) {
                System.out.print("| ");
                continue;
            }

            System.out.print(current + " ");

            if (current.hasLeft())
                queue.enqueue(current.left);

            if (current.hasRight())
                queue.enqueue(current.right);

            queue.enqueue(null);
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return bfs().toString();
    }

}