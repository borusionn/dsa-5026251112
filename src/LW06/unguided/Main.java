package LW06.Unguided;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner read = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));

    BinarySearchTree bst = new BinarySearchTree();
    
    Scanner line = new Scanner(read.nextLine());
    while (line.hasNextInt()) {
        bst.insert(line.nextInt());
    }

    line.close();

    int target = read.nextInt();

    System.out.print("Preorder : ");
    bst.preOrder();

    System.out.print("Inorder : ");
    bst.inOrder();

    System.out.print("Postorder : ");
    bst.postOrder();

    System.out.println("Search " + target + " : " + bst.search(target));
    System.out.println("Nodes : " + bst.countNodes());
    System.out.println("Leaves : " + bst.countLeaves());

    read.close();
    }
}   