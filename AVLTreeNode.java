class AVLTreeNode {
	AVLTreeNode left=null;
	AVLTreeNode right=null;
	String elem=null;
	int height=0;
	
	public AVLTreeNode(AVLTreeNode leftNode, AVLTreeNode rightNode, String elem)
	{
		left=leftNode;
		right=rightNode;		
		this.elem=elem;
	}
}