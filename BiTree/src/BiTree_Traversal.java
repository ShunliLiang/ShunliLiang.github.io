//构建二叉树，先序中序后序排列
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BiTree_Traversal {

	public BiTree_Traversal lChild;//左孩子
	public BiTree_Traversal rChild;//右孩子
	public BiTree_Traversal root;//根节点
	public Object data;//数据域
	public int lm;
	public int rm;
	public BiTree_Traversal(){}
	public BiTree_Traversal(BiTree_Traversal lChrild,BiTree_Traversal rChild,Object data){
		this.lChild = lChrild;
		this.rChild = rChild;
		this.data = data;
	}
	public BiTree_Traversal(Object data){
		this(null, null, data);
	}
	
	public void createTree(Object[] objects){
		List<BiTree_Traversal> datas = new ArrayList<>();//存储所有节点
		for(Object obj:objects){
			datas.add(new BiTree_Traversal(obj));
		}
		root = datas.get(0);//将一个作为根节点
		for(int i = 0;i < objects.length/2;i++){
			datas.get(i).lChild = datas.get(2*i+1);
			if(2*i+2 < objects.length){//避免偶数的时候，下标越界	
				datas.get(i).rChild = datas.get(2*i+2);
			}
		}
	}
	
	//先序遍历
	public void preorder(BiTree_Traversal root){
		if(root != null){
			visit(root.getData());
			preorder(root.lChild);
			preorder(root.rChild);
		}	
	}
	//非递归先序遍历
	     //先序遍历：首先把头结点压栈，然后当栈不为空时执行循环，
        //	循环里面的逻辑为：弹出栈顶元素，如果弹出的结点有左右孩子，则依次压入右孩子结点和左孩子结点。
	public List<Object> preorderTraversal(BiTree_Traversal root) {
        List<Object> list = new ArrayList<Object>();
        Stack<BiTree_Traversal> stack = new Stack<BiTree_Traversal>();
        if (root == null) return list;
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            list.add(root.data);
            visit(root.getData());
            if (root.rChild != null) {
                stack.push(root.rChild);
            }
            if (root.lChild != null) {
                stack.push(root.lChild);
            }
        }
        return list;
    }

	//中序遍历
	public void inorder(BiTree_Traversal root){
		if(root != null){
			inorder(root.lChild);
			visit(root.getData());
			inorder(root.rChild);
		}
	}
	//非递归中序遍历
	//	中序遍历：分两种情况：（1）只要当前结点有左孩子，一直压栈，直到压完子树的左边界；
	//（2）第一种情况除外的情况。弹出栈顶结点，然后指向弹出结点的右孩子
	 public List<Object> inorderTraversal(BiTree_Traversal root) {

	        Stack<BiTree_Traversal>  stack = new Stack<BiTree_Traversal>();
	        List<Object> list = new ArrayList<Object>();
	        if(root == null){
	            return list;
	        }
	        while (!stack.isEmpty() || root != null){
	        //循环终止的条件是栈为空，且当前结点也为空
	            if(root!=null){
	            //如果结点不为空，则将二叉树的左边界全部压栈
	                stack.push(root);
	                root = root.lChild;
	            }
	            else{
	            //如果结点为空（也就是说上一个结点没有左孩子或者没有右孩子）
	                 root =  stack.pop();//弹出当前结点
	                 list.add(root.data);
	                 visit(root.getData());
	                root = root.rChild;//进而指向弹出结点的右结孩子
	            }
	        }
	        return list;
	    }

	//后续遍历
	public void afterorder(BiTree_Traversal root){
		if(root != null){
			afterorder(root.lChild);
			afterorder(root.rChild);
			visit(root.getData());
		}
	}
	//非递归后序遍历：对于一棵二叉树来说，后序遍历的顺序为 左→右→中，而对于上面的先序遍历非递归方式中，
	//结点入栈的顺序为中→右→左，所以后续遍历非递归方式的一个比较易于理解的做法就是从先序遍历中寻找灵感。使用两个栈，
	//栈s1为逻辑处理中的辅助栈，栈s1弹出的所有元素压入栈2。栈2出栈的顺序即为后序遍历的顺序
	public List<Object> postorderTraversal(BiTree_Traversal root) {
        List list = new ArrayList();
        Stack<BiTree_Traversal> stack1 = new Stack<BiTree_Traversal>();
        Stack<BiTree_Traversal> stack2 = new Stack<BiTree_Traversal>();
        if (root == null) return list;
        stack1.push(root);
        while (!stack1.isEmpty()) {
            root = stack1.pop();
            stack2.add(root);
            if (root.lChild != null) {
                stack1.push(root.lChild);
            }
            if (root.rChild != null) {
                stack1.push(root.rChild);
            }
        }
        while (!stack2.isEmpty()) {
        	Object temp=stack2.pop().data;
            list.add(temp);
            visit(temp);
        }
        return list;
    }

	
	private void visit(Object obj) {
		System.out.print(obj+" ");
	}
	private Object getData() {
		return data;
	}
	public BiTree_Traversal getRoot() {
		return root;
	}
	
	public static void main(String[] args) {
		Object[] objs = {0,1,2,3,4,5,6,7};
		BiTree_Traversal binTree = new BiTree_Traversal();
		binTree.createTree(objs);
		
		binTree.preorder(binTree.getRoot());//0 1 3 7 4 2 5 6 
		System.out.println();
		binTree.preorderTraversal(binTree.getRoot());
		System.out.println();
		
		binTree.inorder(binTree.getRoot());//7 3 1 4 0 5 2 6 
		System.out.println();
		binTree.inorderTraversal(binTree.getRoot());
		System.out.println();
		
		binTree.afterorder(binTree.getRoot());//7 3 4 1 5 6 2 0
		System.out.println();
		binTree.postorderTraversal(binTree.getRoot());		
	}

}
