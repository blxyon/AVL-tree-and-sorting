class AVLTree {
	AVLTreeNode root;
	AVLTreeNode[] tempRoute;
	
	public AVLTree() {
		root=null;
	}
	
	public void createTestTree() {
		AVLTreeNode node1=new AVLTreeNode(null,null,"1");
		AVLTreeNode node7=new AVLTreeNode(null,null,"7");
		AVLTreeNode node3=new AVLTreeNode(null,null,"3");
		AVLTreeNode node5=new AVLTreeNode(null,null,"5");
		AVLTreeNode node2=new AVLTreeNode(node1,node3,"2");
		node2.height=1;
		AVLTreeNode node6=new AVLTreeNode(node5,node7,"6");
		node6.height=1;
		AVLTreeNode node4=new AVLTreeNode(node2,node6,"4");
		node4.height=2;
		root=node4;
	}
	
	public int maxx(int x, int y)
	{
		if(x>y)
			return x;
		else return y;
	}
	
	public void updateCurrNodeH(AVLTreeNode n)
	{
		if(n==null)return;
		if(n.left!=null && n.right!=null) n.height = maxx(n.left.height, n.right.height) + 1; 
		else if(n.left!=null) n.height = maxx(n.left.height, 0) + 1;
		else if(n.right!=null) n.height = maxx(0, n.right.height) + 1;
		else n.height=0;
		
	}
	
	public AVLTreeNode rRotation(AVLTreeNode n) { 
        AVLTreeNode leftN=n.left;
		AVLTreeNode tempNode=leftN.right;		
		n.left=tempNode; 
        leftN.right=n; 
  
		updateCurrNodeH(n);
		
		if(leftN.left!=null && n!=null) leftN.height = maxx(leftN.left.height, n.height) + 1; 
		else if(leftN.left!=null) leftN.height = maxx(leftN.left.height, 0) + 1;
		else if(n!=null) leftN.height = maxx(0, n.height) + 1;
		else leftN.height=0;
  
        return leftN; //we rotate right and so we return the node's left
    } 
  
    public AVLTreeNode lRotation(AVLTreeNode n) { 
        AVLTreeNode rightN = n.right;
		
		AVLTreeNode tempNode=rightN.left;
		n.right = tempNode;
        rightN.left = n; 
		
		
		updateCurrNodeH(n);
		
		if(rightN.right!=null && n!=null) rightN.height = maxx(rightN.right.height, n.height) + 1; 
		else if(rightN.right!=null) rightN.height = maxx(rightN.right.height, 0) + 1;
		else if(n!=null) rightN.height = maxx(0, n.height) + 1;
		else rightN.height=0;
  
        return rightN; 
    } 
  
    public int getBalance(AVLTreeNode node) { 
        if (node==null) 
            return 0; 
  
        if(node.left!=null && node.right!=null) return node.left.height-node.right.height; 
		else if(node.left!=null) return node.left.height;
		else if(node.right!=null)return -node.right.height; 
		else return 0;
    } 
	
	public void print() {
		if(root!=null)
		{
			AVLTreeNode leftNode=root.left;
			AVLTreeNode rightNode=root.right;
			System.out.println(root.elem);
			if(leftNode!=null) printTree(leftNode," ");
			if(rightNode!=null) printTree(rightNode," ");		
		}
		else return;
	}
	
	public void printTree(AVLTreeNode root, String indent)
	{
		if(indent==null)
		{
			System.out.println("Please insert the starting identation next time.");
			return;
		}
		if(root!=null)
		{
			System.out.println(indent+root.elem);
			if(root.left!=null)
			{
				printTree(root.left,(indent+" "));
			}
			if(root.right!=null)
			{
				printTree(root.right,(indent+" "));
			}
		}
		else return;//unwinding
		
	}
	
	public boolean inTree(String e) { 
		if(isTreeEmpty()) return false;
		if(e==null) return false;
		AVLTreeNode tempNode=root;
		while(tempNode!=null)
		{	if(e.equals(tempNode.elem))
			{
				return true;
			}
			else if(e.compareTo(tempNode.elem)<0)
			{
				tempNode=tempNode.left;
			}
			else if(e.compareTo(tempNode.elem)>0)
			{
				tempNode=tempNode.right;
			}
		}
		return false;
	}

	public void insert(String e) { 
		e=checkWord(e);
		if(e!=null)
		{
			
			if(isTreeEmpty()) root=new AVLTreeNode(null,null,e);
			else if(inTree(e)) 
			{
				System.out.println("Element: "+e+" already exists in tree.");
				return;
			}
			else
			{
				int routeHeight=1;
				AVLTreeNode tempNode=root;
				while(tempNode!=null)
				{	
					if(e.compareTo(tempNode.elem)<0)
					{//if e is smaller than tempNode.elem
						if(tempNode.left!=null)tempNode=tempNode.left;
						else
						{
							tempNode.left=new AVLTreeNode(null,null,e);
							break;
						}
					}
					else if(e.compareTo(tempNode.elem)>0)
					{//if e is bigger than tempNode.elem
						if(tempNode.right!=null)tempNode=tempNode.right;
						else 
						{
							tempNode.right=new AVLTreeNode(null,null,e);
							break;
						}
					}
					routeHeight++;
				
				}
				updateHeights(routeHeight,e);
			}
		}
	}
	
	public void updateHeights(int h, String e)
	{
		AVLTreeNode tempNode=root;
		tempRoute=new AVLTreeNode[h+1];//we do include the element that is being added
		if(tempNode.height<h) tempNode.height=h;
		h--;
		while(tempNode!=null)
		{
			if(h+1>=0)tempRoute[h+1]=tempNode;
			if(e.equals(tempNode.elem)) break;
			else if(e.compareTo(tempNode.elem)<0)
			{//if e is smaller than tempNode.elem
				if(tempNode.left!=null)
				{
					tempNode=tempNode.left;
					if(tempNode.height<h) tempNode.height=h;
				}
				else break;
			}
			else if(e.compareTo(tempNode.elem)>0)
			{//if e is bigger than tempNode.elem
				if(tempNode.right!=null)
				{
					tempNode=tempNode.right;
					if(tempNode.height<h) tempNode.height=h;
				}
				else break;
			}
			h--;
		}
	}
	public boolean isTreeEmpty()
	{
		if(root==null) return true;
		else return false;
	}
	public void insertBalanced(String e) { 
		if(isTreeEmpty()) root=new AVLTreeNode(null,null,e);
		else if(inTree(e)) 
		{
			System.out.println("Element: "+e+" already exists in tree.");
			return;
		}
		else
		{
			insert(e);//then balance the route nodes
			goThroughRouteAndRotate(e);
		}
		
	}
	public String checkWord(String e)
	{//spaces at the begining of the word will mess up the tree so I created this method
	
		String formerdW;
		if(e==null) return null;
		else if(e.equals("")) return null;
		else if(e.charAt(0)!=' ') return e;
		else 
		{
			int i=0;
			while(e.charAt(i)==' ')
			{
				i++;
				if(i==e.length()) return null;

			}
			
			formerdW=e.substring(i);
				
			
			
		}
		
		return formerdW;
		
		
	}
	public void goThroughRouteAndRotate(String e)
	{
		AVLTreeNode tempNode;
		AVLTreeNode tempParentN;
		for(int i=0;i<=tempRoute.length-2;i++)
		{//we go through the route we have identified to traverse untill element e and balance every node on it
			tempNode=tempRoute[i];
			tempParentN=tempRoute[i+1];//the parent of tempNode
			if(getBalance(tempNode)<-1)
			{//if it is unbalanced and has more height on the right
				if(tempNode.elem.compareTo(tempParentN.elem)<0)
				{
					tempParentN.left=lRotation(tempNode);
				}
				else tempParentN.right=lRotation(tempNode);
				//because we update what the parent of tempNode points at and we rotated what is bellow it we shall update its height
				updateCurrNodeH(tempParentN);
			}
			else if(getBalance(tempNode)>1)
			{
				if(tempNode.elem.compareTo(tempParentN.elem)<0)
				{
					tempParentN.left=rRotation(tempNode);
				}
				else tempParentN.right=rRotation(tempNode);
				updateCurrNodeH(tempParentN);
			}
			
		}
		
		//Further more we are rotating the root if it is not balanced. It is a special case because we have not got a parent for it and so we consider it separately.
		
		tempNode=tempRoute[tempRoute.length-1];
		if(getBalance(tempNode)<-1)
		{
			root=lRotation(tempNode);
			updateCurrNodeH(root);
		}
		else if(getBalance(tempNode)>1)
		{
			root=rRotation(tempNode);
			updateCurrNodeH(root);
		}	
		//if the tree is balance we do not need to rotate or do something about it as it has the right height already from the previous iteration.
		
	}
}