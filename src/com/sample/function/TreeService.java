package com.sample.function;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * a service tree for reasoning chain
 * @author liuyuping
 * It has a virtual root for all of the reasoning chain in one tree.
 * 
 */
public class TreeService {
	
	private List<Node> list = new ArrayList<TreeService.Node>(); //the list for the whole tree
	private List<Integer> formatList = new ArrayList<Integer>(); //format tree for show
	Integer level = 0; //level for format
	
	/**
	 * tree chain node inner class
	 * @author liuyuping
	 * father node and current node
	 */
	class Node {
		Integer data;
		Integer parent;
		Set<Integer> brothers = new HashSet<Integer>();
		double r;
	}
	
	/**
	 * format the tree to list which could be showed. 
	 * @param parent
	 * @param lel
	 * @return formatList
	 * The arrangement in the list is a level after a value.
	 */
	public List<Integer> formatList(Integer parent, Integer lel) {
		Set<Integer> set = getChild(parent);
		if(false == set.isEmpty()) {
			for (Integer integer : set) {
				formatList.add(integer);
				level = lel + 1;
				formatList.add(level);
				formatList(integer, level);
			}
		}
		return formatList;
	}
	
	
	/**
	 * add more then one child not for a some father node
	 * @param parent
	 * @param child
	 */
	public void adds(Integer parent, Integer...child) {
		for (int i = 0; i < child.length; i++) {
			add(parent, child[i]);
		}
	}
	
	public int adds(Integer parent, Set<Integer> child) {
		int index = 0;
		for (Integer integer : child) {
			index = add(parent, integer);
		}
		return index;
	}
	/**
	 * add a relation of father and child
	 * @param parent
	 * @param child
	 * @return list.size
	 */
	public int add(Integer parent, Integer child) {
		Node node = new Node();
		node.parent = parent;
		node.data = child;
		list.add(node);
		return list.size();
	}
	
	/**
	 * get father node value by child node value
	 * @param data
	 * @return the value or null
	 */
	public Integer getParent(Integer data) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).data == data) {
				return list.get(i).parent;
			}
		}
		return null;
	}
	
	/**
	 * judge a child node has a father node or not
	 * @param child
	 * @return true or false
	 */
	public boolean hasParent(Integer child) {
		if(null == getParent(child)) {
			return false;
		}
		return true;
	}
	
	public Set<Integer> getBrother(Integer data) {
		for (int i = 0; i < list.size(); i++) {
			if (data == list.get(i).data) {
				return list.get(i).brothers;
			}
		}
		return new HashSet<Integer>();
	}
	
	/**
	 * get all of the children and grandchildren nodes of a father node
	 * @param parent
	 * @return set of child node
	 */
	public Set<Integer> getChild(Integer parent) {
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).parent == parent) {
				set.add(list.get(i).data);
			}
		}
		return set;
	}
	
	/**
	 * judge a father node has a child or not
	 * @param parent
	 * @return true or false
	 */
	public boolean hasChild(Integer parent) {
		if (getChild(parent).isEmpty()) {
			return true;
		}
		return false;
	}
}
