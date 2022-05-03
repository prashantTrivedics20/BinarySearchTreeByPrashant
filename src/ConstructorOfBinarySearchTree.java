import java.util.ArrayList;
import java.util.Stack;

public class ConstructorOfBinarySearchTree {
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
    public static int size(Node node)
    {
        if(node==null)
        {
            return 0;
        }
        int lf=size(node.left);
        int rf=size(node.right);
        return lf+rf+1;
    }
    public static int sum(Node node)
    {
        if(node==null)
        {
            return 0;
        }
        int lf=sum(node.left);
        int rf=sum(node.right);
        return lf+rf+node.data;
    }
    public static int max(Node node)
    {
        if(node.right!=null)
        {
            return max(node.right);
        }
        else
        {
            return node.data;
        }
    }
    public static int min(Node node)
    {
        if(node.left!=null)
        {
            return min(node.left);
        }
        else
        {
            return node.data;
        }
    }
    public static boolean find(Node node,int finddata)
    {
        if(node==null)
        {
            return false;
        }
        if(finddata==node.data)
        {
            return true;
        }
        if(finddata>node.data)
        {
            boolean rf=find(node.right,finddata);
            if(rf)
            {
                return true;
            }

        }
        if(finddata<node.data)
        {
            boolean lf=find(node.left,finddata);
            if(lf)
            {
                return true;
            }

        }
        return false;


    }
    // insert node in the BST
    public static Node add(Node node,int data)
    {
        if(node==null)
        {
            return new Node(data,null,null);
        }
        if(data>node.data)
        {
          node.right=  add(node.right,data);
        }
        else if(data<node.data)
        {
          node.left=  add(node.left,data);
        }
        return node;

    }
    // remove node of a BST
    public static Node remove(Node node,int data)
    {
        if(node==null)
        {
            return null;
        }
        if(data>node.data)
        {
            node.right=remove(node.right,data);
        }
        else if(data<node.data)
        {
            node.left=remove(node.left,data);
        }
        else
        {
            if(node.left!=null&&node.right!=null)
            {
              int lmax=max(node.left);
              node.data=lmax;
              node.left=remove(node.left,lmax);
              return node;
            }
            else if(node.left!=null)
            {
                return node.left;
            }
            else if(node.right!=null)
            {
              return node.right;
            }
            else
            {
                return null;
            }

        }
        return node;
    }
    // replace with some of larger
    static int s=0;
    public static void replaceWithSumOfLarger(Node node)
    {
        if(node==null)
        {
            return;
        }
        replaceWithSumOfLarger(node.right);
        int od=node.data;
        node.data=s;
        s=s+od;
        replaceWithSumOfLarger(node.left);
    }
    // Lowest Common Ancestor
    public static int  findLowestCommonAncestor(Node node,int d1,int d2)
    {
        if(node==null)
        {
            return 0;
        }
        if(d1<node.data&&d2<node.data)
        {
           int lf=findLowestCommonAncestor(node.left,d1,d2);
           return lf;
        }
        else if(d1>node.data&&d2>node.data)
        {
           int rf= findLowestCommonAncestor(node.right,d1,d2);
           return rf;
        }
        return node.data;
    }
    public static void printBstInRange(Node node,int lower,int upper)
    {
        if(node==null)
        {
            return;
        }
        if(lower<node.data&&upper<node.data)
        {
            printBstInRange(node.left,lower,upper);
        }
        else if(lower>node.data&&upper>node.data)
        {
            printBstInRange(node.right,lower,upper);
        }
        else
        {
            printBstInRange(node.left,lower,upper);
            System.out.println(node.data);
            printBstInRange(node.right,lower,upper);
        }

    }
    // TargetSumPair in Bst
    public static void targetSumPairInBst(Node root,Node node,int target)
    {
        if(node==null)
        {
            return;
        }
        targetSumPairInBst(root,node.left,target);
        int cmp=target-node.data;
        if(node.data<cmp) {
            if (find(root, cmp)) {
                System.out.println(node.data + " " + cmp);
            }
        }
        targetSumPairInBst(root,node.right,target);

    }
    // alternative approaches of the Target Sum
    static ArrayList<Integer>al=new ArrayList<>();
    public static void targetSumPair2(Node node,int target)
    {
        if(node==null)
        {
            return;
        }
        targetSumPair2(node.left,target);
        al.add(node.data);
        targetSumPair2(node.right,target);
    }
    // third approach of the Target sum
    public static class Pair
    {
        Node node;
        int state=0;
        Pair(Node node,int state)
        {
            this.node=node;
            this.state=state;
        }

    }
    public static Node getNextFromNormalInOrder(Stack<Pair>st)
    {
        while(st.size()>0)
        {
            Pair top=st.peek();
            if(top.state==0)
            {
                if(top.node.left!=null)
                {
                    st.push(new Pair(top.node.left,0));
                }
                top.state++;

            }
            else if(top.state==1)
            {
                if(top.node.right!=null)
                {
                    st.push(new Pair(top.node.right,0));
                }
                top.state++;
                return top.node;

            }
            else
            {
                st.pop();
            }


        }
        return null;
    }
    public static Node getNextFromReverseInOrder(Stack<Pair>st)
    {
        while(st.size()>0)
        {
            Pair top=st.peek();
            if(top.state==0)
            {
                if(top.node.right!=null)
                {
                    st.push(new Pair(top.node.right,0));
                }
                top.state++;

            }
            else if(top.state==1)
            {
                if(top.node.left!=null)
                {
                    st.push(new Pair(top.node.left,0));
                }
                top.state++;
                return top.node;

            }
            else
            {
                st.pop();
            }


        }
        return null;
    }
    public static void bestApproach(Node node,int target)
    {
        Stack<Pair>ls=new Stack<>();
        Stack<Pair>Rs=new Stack<>();
        ls.push(new Pair(node,0));
        Rs.push(new Pair(node,0));
        Node left=getNextFromNormalInOrder(ls);
        Node right=getNextFromReverseInOrder(Rs);
        while(left.data<right.data)
        {
            if(left.data+right.data<target)
            {
                left=getNextFromNormalInOrder(ls);
            }
            else if(left.data+right.data>target)
            {
                right=getNextFromNormalInOrder(Rs);
            }
            else
            {
                System.out.println(left.data+" "+right.data);
                left=getNextFromNormalInOrder(ls);
                right=getNextFromReverseInOrder(Rs);
            }
        }

    }
    public static void target(int target)
    {
        int i=0;
        int j=al.size()-1;
        while(i<=j&&i!=j)
        {
            if(al.get(i)+al.get(j)==target)
            {
                System.out.println(al.get(i)+" "+al.get(j));
                i++;
                j--;
            }
            else if(al.get(i)+al.get(j)<target)
            {
                i++;
            }
            else if(al.get(i)+al.get(j)>target)
            {
                j--;
            }



        }
    }
    // check is BsT
    public static class BSTPair{
        Boolean isBST;
        int min;
        int max;
        BSTPair()
        {
            this.isBST=true;
            this.min=min;
            this.max=max;

        }

    }
    public static BSTPair isBST(Node node)
    {
        if(node==null)
        {
            BSTPair bp=new BSTPair();
            bp.min=Integer.MAX_VALUE;
            bp.max=Integer.MIN_VALUE;
            return bp;
        }
        BSTPair lp=isBST(node.left);
        BSTPair rp=isBST(node.right);
        BSTPair mp=new BSTPair();
        mp.isBST=lp.isBST&&rp.isBST&&(node.data>=lp.max&&node.data<=rp.min);
        mp.min=Math.min(node.data,Math.min(lp.min,rp.min));
        mp.max=Math.min(node.data,Math.min(lp.max,rp.max));
        return mp;

    }
    // Largest BST subTree
    public static class LorgestBSTPair
    {
        boolean isBst;
        int max;
        int min;
        Node longesnst;
        int size;
//        LorgestBSTPair()
//        {
//            this.isBst=true;
//            this.max=max;
//            this.min=min;
//            this.longesnst=null;
//            this.size=0;
//        }
    }
    public static LorgestBSTPair LongestBstSubTree(Node node)
    {
        if(node==null)
        {
            LorgestBSTPair bp=new LorgestBSTPair();
            bp.min=Integer.MAX_VALUE;
            bp.max=Integer.MIN_VALUE;
            bp.longesnst=null;
            bp.size=0;
            bp.isBst=true;
            return bp;
        }
        LorgestBSTPair lf=LongestBstSubTree(node.left);
        LorgestBSTPair rf=LongestBstSubTree(node.right);
        LorgestBSTPair mp=new LorgestBSTPair();
        mp.isBst=lf.isBst&&rf.isBst&&lf.max<=node.data&&rf.min>=node.data;
        mp.min=Math.min(node.data,Math.min(lf.min,rf.min));
        mp.max=Math.max(node.data,Math.max(lf.max,rf.max));
        if(mp.isBst)
        {
            mp.longesnst=node;
            mp.size=lf.size+rf.size+1;
        }
        if(rf.size>lf.size)
        {
            mp.longesnst=rf.longesnst;
            mp.size=rf.size;
        }
        else
        {
            mp.longesnst=lf.longesnst;
            mp.size=lf.size;
        }
        return mp;





    }





    public static void display(Node node)
    {

        if(node==null)
        {
            return;
        }
        String str="";
        str+=node.left==null?".":node.left.data+"";
        str+="<-"+node.data+"->";
        str+=node.right==null?".":node.right.data+"";
        System.out.println(str);
        display(node.left);
        display(node.right);
    }
    public static void main(String[] args) {
        int []arr={10,25,37,50,62,75,87};
        Node root=construct(arr,0,arr.length-1);
        //display(root);
       // boolean res=find(root,50);
       // System.out.println(res);
//        Node addnode=add(root,60);
//        display(addnode);
//
//       Node res= remove(root,62);
//        display(res);
//        replaceWithSumOfLarger(root);
//        display(root);
//       int res= findLowestCommonAncestor(root,62,87);
//        System.out.println(res);
        //printBstInRange(root,37,87);
       // targetSumPairInBst(root,root,100);
//        targetSumPair2(root,100);
//        target(100);
      //  bestApproach(root,100);
//       BSTPair p= isBST(root);
//        System.out.println(p.isBST);
//        LongestBstSubTree(root);
//        LorgestBSTPair p=new LorgestBSTPair();
//        System.out.println(p.longesnst+"@"+p.size);



    }
}
