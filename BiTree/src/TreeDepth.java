//实现二叉树的深度方式有两种，递归以及非递归。

//①递归实现：为了求树的深度，可以先求其左子树的深度和右子树的深度，可以用递归实现，递归的出口就是节点为空。返回值为0；
//②非递归实现：利用层次遍历的算法，设置变量level记录当前节点所在的层数，设置变量last指向当前层的最后一个节点，
//当处理完当前层的最后一个节点，让level指向+1操作。设置变量cur记录当前层已经访问的节点的个数，当cur等于last时，
//表示该层访问结束。层次遍历在求树的宽度、输出某一层节点，某一层节点个数，每一层节点个数都可以采取类似的算法。
//树的宽度：在树的深度算法基础上，加一个记录访问过的层节点个数最多的变量max,在访问每层前max与last比较，如果max比较大，max不变，如果max小于last，把last赋值给max;
import java.util.LinkedList;
public class TreeDepth
{
    //递归实现1
  public int findDeep(BiTree_Traversal root)
  {
      int deep = 0;
      
      if(root != null)
      {
          int lchilddeep = findDeep(root.lChild);
          int rchilddeep = findDeep(root.rChild);
          deep = lchilddeep > rchilddeep ?lchilddeep + 1 : rchilddeep + 1;
      }
      
      return deep;
  }
  
  
  //递归实现2
  public int findDeep1(BiTree_Traversal root)
  {
    
      
	  if(root == null)
      {
          return 0;
      }
      else
      {
       int lchilddeep = findDeep1(root.lChild);//求左子树的深度
       int rchilddeep = findDeep1(root.lChild);//求右子树的深度
       return lchilddeep > rchilddeep ? lchilddeep + 1 : rchilddeep + 1;//左子树和右子树深度较大的那个加一等于整个树的深度
      }
  }
  //非递归实现
  public int findDeep2(BiTree_Traversal root)
  {
     if(root == null)
         return 0;
    
     BiTree_Traversal current = null;
     LinkedList<BiTree_Traversal> queue = new LinkedList<BiTree_Traversal>();
     queue.offer(root);
     int cur,last;
     int level = 0;
     while(!queue.isEmpty())
     {
         cur = 0;//记录本层已经遍历的节点个数
         last = queue.size();//当遍历完当前层以后，队列里元素全是下一层的元素，队列的长度是这一层的节点的个数
         while(cur < last)//当还没有遍历到本层最后一个节点时循环
         {
             current = queue.poll();//出队一个元素
             cur++;
             //把当前节点的左右节点入队（如果存在的话）
             if(current.lChild != null)
             {
                 queue.offer(current.lChild);
             }
             if(current.rChild != null)
             {
                 queue.offer(current.rChild);
             }
         }
         level++;//每遍历完一层level+1
     }
     return level;
  }
  
  int maxDist(BiTree_Traversal root) { 
		      //如果树是空的，则返回0 
		      if(root == null) 
		          return 0; 
		      int max=0;
		      if(root.lChild != null) { 
		          root.lm = maxDist(root.lChild) +1; 
		      } 
		      if(root.rChild != null) 
		          root.rm = maxDist(root.rChild) +1; 
		      //如果以该节点为根的子树中有最大的距离，那就更新最大距离 
		      int sum = root.rm + root.lm; 
		      if(sum > max) { 
		          max = sum; 
		      } 
		    
		      return root.rm > root.lm ?root.rm : root.lm; 
  }  

//二叉树任意两个节点之间路径的最大长度
  public static void main(String[] args)
 {
	Object[] objs = {0,1,2,3,4,5,6,7};
	BiTree_Traversal binTree=new BiTree_Traversal();
	binTree.createTree(objs);
	TreeDepth deep = new TreeDepth();
    System.out.println(deep.findDeep(binTree.getRoot()));
    System.out.println(deep.findDeep1(binTree.getRoot()));
    System.out.println(deep.findDeep2(binTree.getRoot()));  
 }
}