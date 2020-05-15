
public class IntervalTree<Key extends Comparable<Key>, Value> {
	private IntervalTreeNode<Key,Value> root = null;
	
	public void insert(Interval1D<Key> interval, Value value)
	{
		IntervalTreeNode<Key,Value> current = root;
		while(current!=null)
		{
			if(current.getLeftNode() == null)
				current = current.getRightNode();
			else if(current.getLeftNode().getMaxInRight().compareTo(interval.getHigh()) < 0)
				current = current.getRightNode();
			else
				current = current.getLeftNode();
		}
		root = new IntervalTreeNode<Key,Value>(interval ,value);
	}
	
	public Value get(Interval1D<Key> interval)
	{
		IntervalTreeNode<Key,Value> current = root;
		while(current != null)
		{	
			if(interval.getLow().compareTo(current.getInterval().getLow()) <0)
				current = current.getLeftNode();
			else if(interval.getLow().compareTo(current.getInterval().getLow()) >0)
				current = current.getRightNode();
			else if(interval.getHigh().equals(current.getInterval().getHigh()))
				return current.getValue();
			else
				current = null;
				
		}
		return null;
	}

}
