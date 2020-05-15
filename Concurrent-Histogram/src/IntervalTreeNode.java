
public class IntervalTreeNode<Key extends Comparable<Key>, Value> {
	private Interval1D<Key> intervalNode;
	private Value value;
	private Key maxInRight;
	private IntervalTreeNode<Key,Value> leftNode = null;
	private IntervalTreeNode<Key,Value> rightNode = null;
	
	public IntervalTreeNode(Interval1D<Key> intervalNode,Value value)
	{
		this.intervalNode = intervalNode;
		this.value = value;
		this.maxInRight = intervalNode.getHigh();
	}
	
	public IntervalTreeNode<Key,Value> getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(IntervalTreeNode<Key,Value> leftNode) {
		this.leftNode = leftNode;
	}

	public IntervalTreeNode<Key,Value> getRightNode() {
		return rightNode;
	}

	public void setRightNode(IntervalTreeNode<Key,Value> rightNode) {
		this.rightNode = rightNode;
	}

	public Interval1D<Key> getInterval() {
		return intervalNode;
	}

	public Value getValue() {
		return value;
	}
	public void setValue(Value value) {
		this.value = value;
	}
	public Key getMaxInRight() {
		return maxInRight;
	}
	public void setMaxInRight(Key maxInRight) {
		this.maxInRight = maxInRight;
	}

}
