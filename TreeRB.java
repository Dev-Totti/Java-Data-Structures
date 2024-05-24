public class TreeRB<T extends Comparable<T>> {
    // Node - Subclass
    public class Node {
        private T data;
        private Node left;
        private Node right;
        private Node parent;
        private boolean red;

        public Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.parent = null;
            this.red = true;
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

        public boolean isRed() {
            return red;
        }

        public boolean isBlack() {
            return !red;
        }

        public void setRed() {
            red = true;
        }

        public void setBlack() {
            red = false;
        }

        public void setColor(boolean red) {
            this.red = red;
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

        public boolean isLeft() {
            return parent != null && this == parent.left;
        }

        public boolean isRight() {
            return parent != null && this == parent.right;
        }

        public boolean hasBoth() {
            return left != null && right != null;
        }

        @Override
        public String toString() {
            return data.toString() + (red ? "(R)" : "(B)");
        }

    }

    // Attributes
    private Node root;

    // Constructors
    public TreeRB() {
        root = null;
    }

    // Methods - CRUD
    public void insert(T data) {
        Node newNode = new Node(data);
        Node current = root;
        Node parent = null;

        while (current != null) {
            parent = current;
            int compare = data.compareTo(current.getData());
            if (compare < 0) {
                current = current.getLeft();
            } else if (compare > 0) {
                current = current.getRight();
            } else {
                return;
            }
        }

        newNode.setParent(parent);
        if (parent == null) {
            root = newNode;
            root.setBlack();
        } else if (data.compareTo(parent.getData()) < 0) {
            parent.setLeft(newNode);
        } else {
            parent.setRight(newNode);
        }

        insertFix(newNode);
    }

    private void insertFix(Node current) {
        while (isRed(current.getParent())) {
            Node parent = current.getParent();
            Node grandparent = parent.getParent();
            Node uncle = parent.isLeft() ? grandparent.getRight() : grandparent.getLeft();

            if (isRed(uncle)) {
                // Case 1
                parent.setBlack();
                uncle.setBlack();
                grandparent.setRed();
                current = grandparent;
            } else {
                if (parent.isLeft()) {
                    if (current.isRight()) {
                        // Case 2
                        current = parent;
                        rotateLeft(current);
                    }
                    // Case 3
                    parent.setBlack();
                    grandparent.setRed();
                    rotateRight(grandparent);
                } else {
                    if (current.isLeft()) {
                        // Case 2
                        current = parent;
                        rotateRight(current);
                    }
                    // Case 3
                    parent.setBlack();
                    grandparent.setRed();
                    rotateLeft(grandparent);
                }
            }
        }
        // Case 0
        root.setBlack();
    }

    // Remove using successor
    public void remove(T data) {
        Node toRemove = search(data);
        if (toRemove != null) {
            remove(toRemove);
        }
    }

    private void remove(Node current) {
        if (current.isLeaf()) {
            if (current.isBlack()) {
                removeFix(current);
            }
            transplant(current, null);
        } else if (current.hasBoth()) {
            Node sucessor = findMin(current.getRight());
            current.setData(sucessor.getData());
            remove(sucessor);
        } else {
            Node child = current.hasLeft() ? current.getLeft() : current.getRight();
            transplant(current, child);
            if (current.isBlack()) {
                removeFix(child);
            }
        }
    }

    // Delete using predecessor
    public void delete(T data) {
        Node toDelete = search(data);
        if (toDelete != null) {
            delete(toDelete);
        }
    }

    private void delete(Node current) {
        if (current.isLeaf()) {
            if (current.isBlack()) {
                removeFix(current);
            }
            transplant(current, null);
        } else if (current.hasBoth()) {
            Node predecessor = findMax(current.getLeft());
            current.setData(predecessor.getData());
            delete(predecessor);
        } else {
            Node child = current.hasLeft() ? current.getLeft() : current.getRight();
            transplant(current, child);
            if (current.isBlack()) {
                removeFix(child);
            }
        }
    }

    private void removeFix(Node current) {
        while (current != root && current.isBlack()) {
            Node parent = current.getParent();
            Node sibling = current.isLeft() ? parent.getRight() : parent.getLeft();
            Node w1 = current.isLeft() ? sibling.getLeft() : sibling.getRight();
            Node w2 = current.isLeft() ? sibling.getRight() : sibling.getLeft();

            if (isRed(sibling)) {
                // Case 1
                sibling.setBlack();
                parent.setRed();
                if (current.isLeft()) {
                    rotateLeft(parent);
                    sibling = parent.getRight();
                } else {
                    rotateRight(parent);
                    sibling = parent.getLeft();
                }
            }

            if (!isRed(w1) && !isRed(w2)) {
                // Case 2
                sibling.setRed();
                current = parent;
            } else {
                if (!isRed(w2)) {
                    // Case 3
                    sibling.setRed();
                    w1.setBlack();

                    if (current.isLeft()) {
                        rotateRight(sibling);
                        sibling = parent.getRight();
                    } else {
                        rotateLeft(sibling);
                        sibling = parent.getLeft();
                    }
                }

                // Case 4
                sibling.setColor(parent.isRed());
                parent.setBlack();
                w2.setBlack();

                if (current.isLeft()) {
                    rotateLeft(parent);
                } else {
                    rotateRight(parent);
                }
                current = root;
            }
        }
        // Case 0
        current.setBlack();
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

    private boolean isRed(Node current) {
        return current != null && current.isRed();
    }

    private void rotateLeft(Node current) {
        Node right = current.getRight();
        current.setRight(right.getLeft());

        if (right.hasLeft()) {
            right.getLeft().setParent(current);
        }

        right.setParent(current.getParent());

        if (current.getParent() == null) {
            root = right;
        } else if (current.isLeft()) {
            current.getParent().setLeft(right);
        } else {
            current.getParent().setRight(right);
        }

        right.setLeft(current);
        current.setParent(right);
    }

    private void rotateRight(Node current) {
        Node left = current.getLeft();
        current.setLeft(left.getRight());

        if (left.hasRight()) {
            left.getRight().setParent(current);
        }

        left.setParent(current.getParent());

        if (current.getParent() == null) {
            root = left;
        } else if (current.isRight()) {
            current.getParent().setRight(left);
        } else {
            current.getParent().setLeft(left);
        }

        left.setRight(current);
        current.setParent(left);
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