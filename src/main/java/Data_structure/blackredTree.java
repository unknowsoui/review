package Data_structure;

public class blackredTree<E extends Comparable<E>>{
    private final boolean black = true;
    private final boolean red = false;

    private class Node{
        public E e;
        Node left;
        Node right;

        boolean color;

        Node parent;

        public Node(E e){
            this.e = e;
            this.left = null;
            this.right = null;

            boolean color = black;

            this.parent = null;
        }

        public Node(E e,Node parent){
            this.e = e;
            this.left = null;
            this.right = null;

            this.color = black;

            this.parent = parent;
        }
    }

    private Node root;
    private int size;

    public Node getRoot() {
        return root;
    }

    public blackredTree(){
        this.root = null;
        this.size = 0;
    }

    public void add(Node root, E e){
        this.root = new Node(e);
        size++;
    }

    public Node rotateRight(Node node){
        Node left = node.left;
        Node leftR = left.right;

        left.left = node;
        node.left = leftR;

        node.color = red;
        left.left.color = red;

        return left;
    }

    public Node rotateLeft(Node node){
        Node right = node.right;
        Node rightL = right.left;

        right.left = node;
        node.right = rightL;

        node.color = red;
        right.right.color = red;

        return right;
    }

    public Node add(Node node,E e,Node parent){
        if (node == null){
            Node temp = new Node(e,parent);
            temp.color = red;
            return temp;
        }

        Node temp = node;

        if(e.compareTo(node.e) > 0){
            node = add(node.right,e,node);
        }else if(e.compareTo(node.e) > 0){
            node = add(node.left,e,node);
        }

        if(parent.color == red && parent.parent.right.color == red){
            parent.color = black;
            parent.parent.right.color = black;
        }

        if(parent.color == red && parent.parent.left.color == red){
            parent.color = black;
            parent.parent.left.color = black;
        }

        if(parent.left.equals(node) && parent.color == red && parent.parent.right.color == black){
            return rotateRight(parent.parent);
        }

        if(parent.right.equals(node) && parent.color == red && parent.parent.right.color == black){
            rotateLeft(parent);
            return rotateRight(parent.parent);
        }

        if (parent.right.equals(node) && parent.color == red && parent.parent.left.color == black){
            return rotateLeft(parent.parent);
        }

        if(parent.left.equals(node) && parent.color == red && parent.parent.left.color == black){
            rotateRight(parent);
            return rotateRight(parent.parent);
        }

        return node;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static void main(String[] args) {
        blackredTree blackredTree = new blackredTree();
        blackredTree.add(blackredTree.getRoot(),10);
        blackredTree.add(blackredTree.getRoot(),20);
        blackredTree.add(blackredTree.getRoot(),30);
        blackredTree.add(blackredTree.getRoot(),15);
        System.out.println(blackredTree);
    }
}
