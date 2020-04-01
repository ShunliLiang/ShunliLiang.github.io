//ʵ�ֶ���������ȷ�ʽ�����֣��ݹ��Լ��ǵݹ顣

//�ٵݹ�ʵ�֣�Ϊ����������ȣ���������������������Ⱥ�����������ȣ������õݹ�ʵ�֣��ݹ�ĳ��ھ��ǽڵ�Ϊ�ա�����ֵΪ0��
//�ڷǵݹ�ʵ�֣����ò�α������㷨�����ñ���level��¼��ǰ�ڵ����ڵĲ��������ñ���lastָ��ǰ������һ���ڵ㣬
//�������굱ǰ������һ���ڵ㣬��levelָ��+1���������ñ���cur��¼��ǰ���Ѿ����ʵĽڵ�ĸ�������cur����lastʱ��
//��ʾ�ò���ʽ�������α����������Ŀ�ȡ����ĳһ��ڵ㣬ĳһ��ڵ������ÿһ��ڵ���������Բ�ȡ���Ƶ��㷨��
//���Ŀ�ȣ�����������㷨�����ϣ���һ����¼���ʹ��Ĳ�ڵ�������ı���max,�ڷ���ÿ��ǰmax��last�Ƚϣ����max�Ƚϴ�max���䣬���maxС��last����last��ֵ��max;
import java.util.LinkedList;
public class TreeDepth
{
    //�ݹ�ʵ��1
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
  
  
  //�ݹ�ʵ��2
  public int findDeep1(BiTree_Traversal root)
  {
    
      
	  if(root == null)
      {
          return 0;
      }
      else
      {
       int lchilddeep = findDeep1(root.lChild);//�������������
       int rchilddeep = findDeep1(root.lChild);//�������������
       return lchilddeep > rchilddeep ? lchilddeep + 1 : rchilddeep + 1;//����������������Ƚϴ���Ǹ���һ���������������
      }
  }
  //�ǵݹ�ʵ��
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
         cur = 0;//��¼�����Ѿ������Ľڵ����
         last = queue.size();//�������굱ǰ���Ժ󣬶�����Ԫ��ȫ����һ���Ԫ�أ����еĳ�������һ��Ľڵ�ĸ���
         while(cur < last)//����û�б������������һ���ڵ�ʱѭ��
         {
             current = queue.poll();//����һ��Ԫ��
             cur++;
             //�ѵ�ǰ�ڵ�����ҽڵ���ӣ�������ڵĻ���
             if(current.lChild != null)
             {
                 queue.offer(current.lChild);
             }
             if(current.rChild != null)
             {
                 queue.offer(current.rChild);
             }
         }
         level++;//ÿ������һ��level+1
     }
     return level;
  }
  
  int maxDist(BiTree_Traversal root) { 
		      //������ǿյģ��򷵻�0 
		      if(root == null) 
		          return 0; 
		      int max=0;
		      if(root.lChild != null) { 
		          root.lm = maxDist(root.lChild) +1; 
		      } 
		      if(root.rChild != null) 
		          root.rm = maxDist(root.rChild) +1; 
		      //����Ըýڵ�Ϊ���������������ľ��룬�Ǿ͸��������� 
		      int sum = root.rm + root.lm; 
		      if(sum > max) { 
		          max = sum; 
		      } 
		    
		      return root.rm > root.lm ?root.rm : root.lm; 
  }  

//���������������ڵ�֮��·������󳤶�
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