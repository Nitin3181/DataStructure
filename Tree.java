package TreeCreation;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


public class Tree {

    Node root;

    public void createRoot(String data){
      root = new Node(data,null,null);
    }

    void addEges(Node source,Node leftNode,Node rightNode){
        source.left = leftNode;
        source.right = rightNode;
    }

    void implementTree(){
        createRoot("1");
        Node source = root;
        Node leftNode = new Node("2");
        Node rightNode = new Node("3");

        addEges(source,leftNode,rightNode);
        source = leftNode;
        leftNode = new Node("4");
        rightNode = new Node("5");
        addEges(source,leftNode,rightNode);

        source = rightNode;
        leftNode = new Node("6");
        rightNode = new Node("7");
        addEges(source,leftNode,rightNode);
    }

    List<Node> inverseLevelOrderTraversal(Node root, Tree tree){
        List<Node> preorderList = new ArrayList<>();
        Stack<Node> treeStack = new Stack();
        if(root == null){
            return preorderList;
        }
        Node workingNode = root;
        treeStack.push(workingNode);
        while(!treeStack.isEmpty()){
            workingNode = treeStack.pop();
            preorderList.add(workingNode);
            if(workingNode.left!= null) {
                treeStack.push(workingNode.left);
            }
            if(workingNode.right!= null) {
                treeStack.push(workingNode.right);
            }
        }
            return preorderList;
    }

    List<Node> preorderTraversal(Node root, Tree tree){
        List<Node> preorderList = new ArrayList<>();
        Stack<Node> treeStack = new Stack();
        if(root == null){
            return preorderList;
        }
        Node workingNode = root;
        treeStack.push(workingNode);
        while(!treeStack.isEmpty()){
            workingNode = treeStack.pop();
            preorderList.add(workingNode);
            if(workingNode.right!= null) {
                treeStack.push(workingNode.right);
            }
            if(workingNode.left!= null) {
                treeStack.push(workingNode.left);
            }
        }
        return preorderList;
    }

        public List<Node> postorderTraversal(Node root){
        List<Node> postorderList = new ArrayList<>();
        Stack<Node> treeStack = new Stack();
        List<Node> visited = new ArrayList<>();
        if(root == null){
            return postorderList;
        }
        Node workingNode = root;
        treeStack.push(workingNode);
        while(!treeStack.isEmpty()){
            visited.add(workingNode);
            if(workingNode.left!=null) {
                if (!visited.contains(workingNode.left)) {
                    treeStack.push(workingNode.left);
                }
            }
            if(workingNode.right!=null) {
                if (!visited.contains(workingNode.right)) {
                    treeStack.push(workingNode.right);
                }
            }
                workingNode = treeStack.pop();
                postorderList.add(workingNode);
            }
        return postorderList;
        }

        public int heightBinaryTreeRecursive(Node root) {
            if(root==null)
            return 0;
            int leftHeight = heightBinaryTreeRecursive(root.left);
            int rightHeight = heightBinaryTreeRecursive(root.right);
            int result = 1 + Math.max(leftHeight,rightHeight);
            return result;

        }

        private List<Node> levelOrderTraversal(Node root){
            List<Node> levelorderList = new ArrayList<>();
            Queue<Node> queue = new LinkedList<>();
            if(root==null)
                return levelorderList;
            queue.offer(root);
            while (!queue.isEmpty()){
               Node newNode =  queue.remove();
                levelorderList.add(newNode);
                if(newNode.left!=null){
                    queue.offer(newNode.left);
                }
                if(newNode.right!=null){
                    queue.offer(newNode.right);
                }
            }
            return levelorderList;

        }

        public int heightBinaryTreeIterative(Node root) {
            if(root==null)
                return 0;
            Queue<Node> queue = new LinkedList<>();
            queue.offer(root);
            int height = 0;
            while(1==1) {
                int nodeCount = queue.size();
                if(nodeCount==0)
                    return height;

                height++;

                while (nodeCount > 0) {
                    Node newNode = queue.remove();
                    nodeCount--;
                    if (newNode.left != null) {
                        queue.offer(newNode.left);
                    }
                    if (newNode.right != null) {
                        queue.offer(newNode.right);
                    }

                }
            }

        }

        public int maxLevelSumBinaryTree(Node root){
        if(root==null)
        return 0;
        Queue<Node> queue = new LinkedList();
        queue.add(root);
        int max = 0;
        int sum = 0;
        while(1==1){
            int nodecount = queue.size();
            if(sum>max) max = sum;
            sum = 0;
            if(nodecount==0)
            return max;

        while(nodecount>0){

           Node newNode = queue.remove();
           sum += Integer.parseInt(newNode.data);
           nodecount--;
            if (newNode.left != null) {
                queue.offer(newNode.left);
            }
            if (newNode.right != null) {
                queue.offer(newNode.right);
            }

        }
        }
    }

        private void printPathsIterativelyforSum(Node root, Integer sumval){

        ArrayList<Node> pathList = new ArrayList<>();
        ArrayList<Node> visited = new ArrayList<>();


        if(root==null)
        return;

        Stack<Node> stack = new Stack();
        stack.push(root);

        while(!stack.isEmpty()){
            Node newNode = stack.pop();
            pathList.add(newNode);

            if(newNode.left==null&&newNode.right==null){
                AtomicInteger sum = new AtomicInteger();
                ArrayList<Node> toRemove = new ArrayList<>();
                visited.add(newNode);
                System.out.println(" ");
                pathList.forEach(s -> {
                    sum.addAndGet(Integer.parseInt(s.data));

                    if((s.left==null&&s.right==null)||
                            (visited.contains(s.left)&&visited.contains(s.right)))
                        toRemove.add(s);
                    });
                if(sum.intValue() == sumval){
                    pathList.forEach(s -> System.out.print(s.data));
                }
                pathList.removeAll(toRemove);
                }
                if(newNode.left!=null)
                    stack.push(newNode.left);
                if(newNode.right!=null)
                    stack.push(newNode.right);


        }



        }



    private void printAncestorsIteratively(Node root,String child) {

        ArrayList<Node> pathList = new ArrayList<>();
        ArrayList<Node> visited = new ArrayList<>();


        if (root == null)
            return;

        Stack<Node> stack = new Stack();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node newNode = stack.pop();
            pathList.add(newNode);
            if (newNode.left == null && newNode.right == null) {
                ArrayList<Node> toRemove = new ArrayList<>();
                visited.add(newNode);
                System.out.println(" ");
                if(pathList.stream().anyMatch(node -> node.data.equals(child))){
                    pathList.forEach(s -> System.out.print(s.data));
                }
                pathList.forEach(s -> {
                   //System.out.print(s.data);
                    if ((s.left == null && s.right == null) || (visited.contains(s.left) && visited.contains(s.right)))
                        toRemove.add(s);
                });
                pathList.removeAll(toRemove);
            }
            if (newNode.left != null)
                stack.push(newNode.left);
            if (newNode.right != null)
                stack.push(newNode.right);


        }
    }




        public static void main(String[] args) {
        Tree tree = new Tree();
        tree.implementTree();
        //System.out.println(tree.root.left.left);
        tree.levelOrderTraversal(tree.root).forEach(node -> System.out.println(node.data));
        System.out.println("Recursive height is :"+tree.heightBinaryTreeRecursive(tree.root));
        System.out.println("Iterative height is :"+tree.heightBinaryTreeIterative(tree.root));
        System.out.println("Max sum is :"+tree.maxLevelSumBinaryTree(tree.root));
        tree.printPathsIterativelyforSum(tree.root,15);
        tree.printAncestorsIteratively(tree.root,"6");
    }

}