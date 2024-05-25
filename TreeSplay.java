public class TreeSplay<T extends Comparable<T>> {
    // Node Subclass
    public class Node {
        private T data;
        private Node left;
        private Node right;
        private Node parent;

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

        public Node getParent() {
            return parent;
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

        public void setParent(Node parent) {
            this.parent = parent;
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

        public boolean isLeft() {
            return parent != null && this == parent.left;
        }

        public boolean isRight() {
            return parent != null && this == parent.right;
        }

        @Override
        public String toString() {
            return data.toString();
        }

    }

    // Attributes
    private Node root;

    // Constructors
    public TreeSplay() {
        root = null;
    }

    // Methods - CRUD
    public void insert(T data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            root = newNode;
            return;
        }

        Node current = root;
        Node parent = null;

        while (current != null) {
            parent = current;
            if (data.compareTo(current.getData()) < 0) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }

        newNode.setParent(parent);
        if (data.compareTo(parent.getData()) < 0) {
            parent.setLeft(newNode);
        } else {
            parent.setRight(newNode);
        }

        splay(newNode);
    }

    public void remove(T data) {
        Node node = search(root, data);
        if (node != null) {
            remove(node);
        }
    }

    public Node remove(Node node) {
        splay(node);

        if (!node.hasBoth()) {
            transplant(node, node.getLeft() != null ? node.getLeft() : node.getRight());
        } else {
            Node predecessor = findMax(node.getLeft());
            node.getLeft().setParent(null);
            splay(predecessor);

            predecessor.setRight(node.getRight());
            if (node.hasRight()) {
                node.getRight().setParent(predecessor);
            }
            transplant(node, predecessor);
        }

        return node;
    }

    public Node search(T data) {
        Node node = search(root, data);
        if (node != null) {
            splay(node);
        }
        return node;
    }

    // Methods - Splay Tree
    private void splay(Node node) {
        while (node.getParent() != null) {
            if (node.getParent().getParent() == null) {
                if (node.isLeft()) {
                    rotateRight(node.getParent());
                } else {
                    rotateLeft(node.getParent());
                }
            } else if (node.isLeft() && node.getParent().isLeft()) {
                rotateRight(node.getParent().getParent());
                rotateRight(node.getParent());
            } else if (node.isRight() && node.getParent().isRight()) {
                rotateLeft(node.getParent().getParent());
                rotateLeft(node.getParent());
            } else if (node.isLeft() && node.getParent().isRight()) {
                rotateRight(node.getParent());
                rotateLeft(node.getParent());
            } else {
                rotateLeft(node.getParent());
                rotateRight(node.getParent());
            }
        }
    }

    // Methods - Red-Black Tree
    private void transplant(Node oldNode, Node newNode) {
        if (oldNode.getParent() == null) {
            root = newNode;
        } else if (oldNode.isLeft()) {
            oldNode.getParent().setLeft(newNode);
        } else {
            oldNode.getParent().setRight(newNode);
        }

        if (newNode != null) {
            newNode.setParent(oldNode.getParent());
        }
    }

    private void rotateLeft(Node node) {
        Node right = node.getRight();

        node.setRight(right.getLeft());
        if (right.hasLeft()) {
            right.getLeft().setParent(node);
        }

        right.setParent(node.getParent());
        if (node.getParent() == null) {
            root = right;
        } else if (node.isLeft()) {
            node.getParent().setLeft(right);
        } else {
            node.getParent().setRight(right);
        }

        right.setLeft(node);
        node.setParent(right);
    }

    private void rotateRight(Node node) {
        Node left = node.getLeft();

        node.setLeft(left.getRight());
        if (left.hasRight()) {
            left.getRight().setParent(node);
        }

        left.setParent(node.getParent());
        if (node.getParent() == null) {
            root = left;
        } else if (node.isLeft()) {
            node.getParent().setLeft(left);
        } else {
            node.getParent().setRight(left);
        }

        left.setRight(node);
        node.setParent(left);
    }

    // Methods - Binary Search Tree
    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(T data) {
        return search(data) != null;
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