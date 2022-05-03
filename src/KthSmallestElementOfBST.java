import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class KthSmallestElementOfBST {
    public static class Node
    {
        int data;
        Node left;
        Node right;
        Node(int data,Node left,Node right)
        {
            this.data=data;
            this.left=left;
            this.right=right;
        }
    }
    public static Node construct(int arr[],int lo,int hi)
    {
        if(lo>hi)
        {
            return null;
        }
        int mid=(lo+hi)/2;
        int data=arr[mid];
        Node lc=construct(arr,lo,mid-1);
        Node rc=construct(arr,mid+1,hi);
        Node node=new Node(data,lc,rc);
        return node;
    }
    // kth smallest element in the tree
   static int c=0;
    public static void KthSmallest(Node node,int k)
    {
        if(node==null)
        {
            return;
        }

        KthSmallest(node.left,k);
        c++;
        if(c==k)
        {
            System.out.println(node.data);
        }
        KthSmallest(node.right,k);
    }
    // kth largest element of the tree
    static int c1=0;
    public static void Kthlargest(Node node,int k)
    {
        if(node==null)
        {
            return;
        }

        Kthlargest(node.right,k);
        c1++;
        if(c1==k)
        {
            System.out.println(node.data);
        }
        Kthlargest(node.left,k);
    }
    public static void main(String[] args) {
        int []arr={10,25,37,50,62,75,87};
        Arrays.sort(arr);
        Node root=construct(arr,0,arr.length-1);
//        KthSmallest(root,3);
//        Kthlargest(root,3);





    }
}
