//���������������������������
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BiTree_Traversal {

	public BiTree_Traversal lChild;//����
	public BiTree_Traversal rChild;//�Һ���
	public BiTree_Traversal root;//���ڵ�
	public Object data;//������
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
		List<BiTree_Traversal> datas = new ArrayList<>();//�洢���нڵ�
		for(Object obj:objects){
			datas.add(new BiTree_Traversal(obj));
		}
		root = datas.get(0);//��һ����Ϊ���ڵ�
		for(int i = 0;i < objects.length/2;i++){
			datas.get(i).lChild = datas.get(2*i+1);
			if(2*i+2 < objects.length){//����ż����ʱ���±�Խ��	
				datas.get(i).rChild = datas.get(2*i+2);
			}
		}
	}
	
	//�������
	public void preorder(BiTree_Traversal root){
		if(root != null){
			visit(root.getData());
			preorder(root.lChild);
			preorder(root.rChild);
		}	
	}
	//�ǵݹ��������
	     //������������Ȱ�ͷ���ѹջ��Ȼ��ջ��Ϊ��ʱִ��ѭ����
        //	ѭ��������߼�Ϊ������ջ��Ԫ�أ���������Ľ�������Һ��ӣ�������ѹ���Һ��ӽ������ӽ�㡣
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

	//�������
	public void inorder(BiTree_Traversal root){
		if(root != null){
			inorder(root.lChild);
			visit(root.getData());
			inorder(root.rChild);
		}
	}
	//�ǵݹ��������
	//	����������������������1��ֻҪ��ǰ��������ӣ�һֱѹջ��ֱ��ѹ����������߽磻
	//��2����һ�������������������ջ����㣬Ȼ��ָ�򵯳������Һ���
	 public List<Object> inorderTraversal(BiTree_Traversal root) {

	        Stack<BiTree_Traversal>  stack = new Stack<BiTree_Traversal>();
	        List<Object> list = new ArrayList<Object>();
	        if(root == null){
	            return list;
	        }
	        while (!stack.isEmpty() || root != null){
	        //ѭ����ֹ��������ջΪ�գ��ҵ�ǰ���ҲΪ��
	            if(root!=null){
	            //�����㲻Ϊ�գ��򽫶���������߽�ȫ��ѹջ
	                stack.push(root);
	                root = root.lChild;
	            }
	            else{
	            //������Ϊ�գ�Ҳ����˵��һ�����û�����ӻ���û���Һ��ӣ�
	                 root =  stack.pop();//������ǰ���
	                 list.add(root.data);
	                 visit(root.getData());
	                root = root.rChild;//����ָ�򵯳������ҽẢ��
	            }
	        }
	        return list;
	    }

	//��������
	public void afterorder(BiTree_Traversal root){
		if(root != null){
			afterorder(root.lChild);
			afterorder(root.rChild);
			visit(root.getData());
		}
	}
	//�ǵݹ�������������һ�ö�������˵�����������˳��Ϊ ����ҡ��У��������������������ǵݹ鷽ʽ�У�
	//�����ջ��˳��Ϊ�С��ҡ������Ժ��������ǵݹ鷽ʽ��һ���Ƚ����������������Ǵ����������Ѱ����С�ʹ������ջ��
	//ջs1Ϊ�߼������еĸ���ջ��ջs1����������Ԫ��ѹ��ջ2��ջ2��ջ��˳��Ϊ���������˳��
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
