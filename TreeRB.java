public class TreeRB<T extends Comparable<T>> {
    private class Node {
        // Node - Attributes
        T data;
        Node left;
        Node right;
        Node parent;
        boolean red;

        // Node - Constructors
        public Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.parent = null;
            this.red = true;
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

        public boolean isLeft() {
            return this == this.parent.left;
        }

        public boolean isRight() {
            return this == this.parent.right;
        }

        @Override
        public String toString() {
            String text = this.data.toString();
            return text + (this.red ? "(R)" : "(B)");
        }

    }

    // Attributes
    public Node root;

    // Constructors
    public TreeRB() {
        this.root = null;
    }

    // Methods - Insert
    public void insert(T data) {
        Node newNode = new Node(data);

        if (this.root == null) {
            this.root = newNode;
            this.root.red = false;
            return;
        }

        Node current = this.root;
        while (current != null) {
            int compare = newNode.data.compareTo(current.data);
            if (compare == 0) {
                return;
            } else if (compare < 0) {
                if (!current.hasLeft()) {
                    current.left = newNode;
                    newNode.parent = current;
                    break;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = newNode;
                    newNode.parent = current;
                    break;
                }
                current = current.right;
            }
        }

        fixInsert(newNode);
    }

    private void fixInsert(Node current) {
        Node parent = current.parent;

        while (isRed(parent) && parent != this.root) {
            Node uncle = parent.isLeft() ? parent.parent.right : parent.parent.left;

            if (isRed(uncle)) {
                parent.red = false;
                uncle.red = false;
                parent.parent.red = true;
                current = parent.parent;
                parent = current.parent;
                continue;
            } else {
                if (parent.isLeft()) {
                    if (current.isRight()) {
                        current = parent;
                        rotateLeft(current);
                        parent = current.parent;
                    }
                    parent.red = false;
                    parent.parent.red = true;
                    rotateRight(parent.parent);
                } else {
                    if (current.isLeft()) {
                        current = parent;
                        rotateRight(current);
                        parent = current.parent;
                    }
                    parent.red = false;
                    parent.parent.red = true;
                    rotateLeft(parent.parent);
                }
            }

        }

        this.root.red = false;
    }

    // Remove Methods
    public void remove(T data) {
        Node toDelete = search(data);
        if (toDelete != null) {
            remove(toDelete);
        }
    }

    public void remove(Node current) {
        if (current.isLeaf()) {
            if (!isRed(current)) {
                fixDelete(current);
            }
            transplant(current, null);
        } else if (current.hasLeft() && current.hasRight()) {
            Node predecessor = min(current.right);
            current.data = predecessor.data;
            remove(predecessor);
        } else {
            Node child = current.hasLeft() ? current.left : current.right;
            transplant(current, child);
            if (!isRed(current)) {
                fixDelete(child);
            }
        }
    }

    private void fixDelete(Node current) {
        Node sibling = null;
        while (current != this.root && !isRed(current)) {
            if (current.isLeft()) {
                sibling = current.parent.right;
                if (isRed(sibling)) {
                    sibling.red = false;
                    current.parent.red = true;
                    rotateLeft(current.parent);
                    sibling = current.parent.right;
                }
                if (!isRed(sibling.left) && !isRed(sibling.right)) {
                    sibling.red = true;
                    current = current.parent;
                } else {
                    if (!isRed(sibling.right)) {
                        sibling.left.red = false;
                        sibling.red = true;
                        rotateRight(sibling);
                        sibling = current.parent.right;
                    }
                    sibling.red = current.parent.red;
                    current.parent.red = false;
                    sibling.right.red = false;
                    rotateLeft(current.parent);
                    current = this.root;
                }
            } else {
                sibling = current.parent.left;
                if (isRed(sibling)) {
                    sibling.red = false;
                    current.parent.red = true;
                    rotateRight(current.parent);
                    sibling = current.parent.left;
                }
                if (!isRed(sibling.left) && !isRed(sibling.right)) {
                    sibling.red = true;
                    current = current.parent;
                } else {
                    if (!isRed(sibling.left)) {
                        sibling.right.red = false;
                        sibling.red = true;
                        rotateLeft(sibling);
                        sibling = current.parent.left;
                    }
                    sibling.red = current.parent.red;
                    current.parent.red = false;
                    sibling.left.red = false;
                    rotateRight(current.parent);
                    current = this.root;
                }
            }
        }
        current.red = false;
    }

    // Methods - Red-Black Tree
    private void transplant(Node oldNode, Node newNode) {
        if (oldNode.parent == null) {
            this.root = newNode;
        } else if (oldNode.isLeft()) {
            oldNode.parent.left = newNode;
        } else {
            oldNode.parent.right = newNode;
        }
        if (newNode != null) {
            newNode.parent = oldNode.parent;
        }
    }

    private boolean isRed(Node node) {
        return node != null && node.red;
    }

    private void rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;

        if (y.hasLeft()) {
            y.left.parent = x;
        }

        y.parent = x.parent;

        if (x.parent == null) {
            this.root = y;
        } else if (x.isLeft()) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }

        y.left = x;
        x.parent = y;
    }

    private void rotateRight(Node x) {
        Node y = x.left;
        x.left = y.right;

        if (y.hasRight()) {
            y.right.parent = x;
        }

        y.parent = x.parent;

        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }

        y.right = x;
        x.parent = y;
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

        int compare = data.compareTo(current.data);
        if (compare < 0) {
            return search(current.left, data);
        } else if (compare > 0) {
            return search(current.right, data);
        } else {
            return current;
        }
    }

    public T min() {
        return min(this.root).data;
    }

    private Node min(Node current) {
        if (current.left == null) {
            return current;
        }
        return min(current.left);
    }

    public T max() {
        return max(this.root).data;
    }

    private Node max(Node current) {
        while (current.right != null) {
            current = current.right;
        }
        return current;
    }

    // Methods - Transversal
    public String preOrder() {
        LinkedListSingle<Node> nodes = new LinkedListSingle<Node>();
        preOrder(this.root, nodes);
        return nodes.toString();
    }

    private void preOrder(Node current, LinkedListSingle<Node> nodes) {
        if (current != null) {
            nodes.add(current);
            preOrder(current.left, nodes);
            preOrder(current.right, nodes);
        }
    }

    public String inOrder() {
        LinkedListSingle<Node> nodes = new LinkedListSingle<Node>();
        inOrder(this.root, nodes);
        return nodes.toString();
    }

    private void inOrder(Node current, LinkedListSingle<Node> nodes) {
        if (current != null) {
            inOrder(current.left, nodes);
            nodes.add(current);
            inOrder(current.right, nodes);
        }
    }

    public String postOrder() {
        LinkedListSingle<Node> nodes = new LinkedListSingle<Node>();
        postOrder(this.root, nodes);
        return nodes.toString();
    }

    private void postOrder(Node current, LinkedListSingle<Node> nodes) {
        if (current != null) {
            postOrder(current.left, nodes);
            postOrder(current.right, nodes);
            nodes.add(current);
        }
    }

    public void print() {
        QueueLinkedList<Node> queue = new QueueLinkedList<Node>();
        queue.enqueue(this.root);
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

    public String bfs() {
        LinkedListSingle<Node> nodes = new LinkedListSingle<Node>();
        QueueLinkedList<Node> queue = new QueueLinkedList<Node>();
        queue.enqueue(this.root);

        while (!queue.isEmpty()) {
            Node current = queue.dequeue();
            nodes.add(current);

            if (current.hasLeft())
                queue.enqueue(current.left);

            if (current.hasRight())
                queue.enqueue(current.right);
        }
        return nodes.toString();
    }

    public String dfs() {
        LinkedListSingle<Node> nodes = new LinkedListSingle<Node>();
        StackLinkedList<Node> stack = new StackLinkedList<Node>();
        stack.push(this.root);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            nodes.add(current);

            if (current.hasRight())
                stack.push(current.right);

            if (current.hasLeft())
                stack.push(current.left);
        }
        return nodes.toString();
    }

    @Override
    public String toString() {
        return bfs();
    }

}