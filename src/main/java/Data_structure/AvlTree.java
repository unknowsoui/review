package Data_structure;

/**
 * AVLTree是BST，所以节点值必须是可比较的
 */
public class AvlTree<E extends Comparable<E>>{
    private class Node{
        public E e;
        public Node left;
        public Node right;
        public int height;

        public Node(E e){
            this.e = e;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    private Node root;
    private int size;

    public AvlTree(){
        root=null;
        size=0;
    }

    //获取某一结点的高度
    private int getHeight(Node node){
        if(node==null){
            return 0;
        }
        return node.height;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 获取节点的平衡因子
     * @param node
     * @return
     */
    private int getBalanceFactor(Node node){
        if(node==null){
            return 0;
        }
        return getHeight(node.right)-getHeight(node.left);
    }

    //判断树是否为平衡二叉树
    public boolean isBalanced(){
        return isBalanced(root);
    }

    private boolean isBalanced(Node node){
        if(node==null){
            return true;
        }
        int balanceFactory = Math.abs(getBalanceFactor(node));
        if(balanceFactory>1){
            return false;
        }
        return isBalanced(node.left)&&isBalanced(node.right);
    }

    public Node rotateRight(Node node){
        Node left = node.left;
        Node leftR = left.right;

        left.left = node;
        node.left = leftR;

        node.height = Math.max(node.left.height,node.right.height) + 1;
        left.height = Math.max(left.left.height,left.right.height) + 1;

        return left;
    }

    public Node rotateLeft(Node node){
        Node right = node.right;
        Node rightL = right.left;

        right.left = node;
        node.right = rightL;

        node.height = Math.max(node.left.height,node.right.height) + 1;
        right.height = Math.max(right.left.height,right.right.height) + 1;

        return right;
    }

    public void add(E e){
        root.e = e;
        size++;
    }

    public Node add(Node node,E e){
        if(node == null){
            size++;
            return new Node(e);
        }

        if(e.compareTo(node.e) > 0){
            node.right = add(node.right,e);
        }else if(e.compareTo(node.e) < 0){
            node.left = add(node.left,e);
        }

        node.height = Math.max(node.left.height,node.right.height) + 1;

        int bf = getBalanceFactor(node);
        if (bf < -1 && getBalanceFactor(node.left) > 0){
           return rotateRight(node);
        }
        if (bf < -1 && getBalanceFactor(node.right) > 0){
            Node right = rotateLeft(node);
            return rotateRight(right);
        }
        if (bf > 1 && getBalanceFactor(node.left) > 0){
            Node left = rotateRight(node);
            return rotateLeft(node);
        }
        if (bf > 1 && getBalanceFactor(node.right) > 0){
            return rotateLeft(node);
        }

        return node;
    }
}

