package bst;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Tree {
	private Node root; // the root node of the tree

	/******************************** Implement Here: *****************************************/
	/*Finish the two basic functions below "insertNode(Node node)" and "deleteNode(Node node)*/

	/*
	 * The function below is to insert a new node into the binary search tree. You need to
	 * implement the code: if a node is successfully
	 * inserted, it returns "true"; if the node to be inserted has the value
	 * already exist in the tree, it should not be inserted and return "false".
	 * So you need to modify the code to forbid nodes with repeating values to
	 * be inserted.
	 */
	public boolean insertNode(Node node) {


        //Implementation Here...
		//if the tree is empty
				if(root == null)
					root = node;
				else
				{
					Node temp = root;
					
					boolean inserted = false; //indicator
					
					while(!inserted)
					{
						if(node.getValue() < temp.getValue())
						{
							//Before move temp to its left, check if its left child is empty
							if(temp.getLeft() == null)
							{ 
								//if the left child is already empty, just assign node as its left
								temp.setLeft(node);
								node.setParent(temp);
								node.setLeft_child_of_parent(true);
								inserted = true;
							}
							temp = temp.getLeft();
						}
						else //go to the right direction
						{
							if(temp.getRight() == null)
							{ 
								//if the left child is already empty, just assign node as its left
								temp.setRight(node);
								node.setParent(temp);
								node.setLeft_child_of_parent(false);
								inserted = true;
							}
							temp = temp.getRight();
						}
					}
					
					Queue<Node> q = new LinkedList();
					q.add(this.root);
					while(!q.isEmpty())
					{
						Node cur_node = q.poll();
						System.out.println(cur_node.getValue());
						
						if(cur_node.getLeft() != null)
							q.add(cur_node.getLeft());
						
						if(cur_node.getRight() != null)
							q.add(cur_node.getRight());
						
					}
					
					
				}
				
				
				//Exercise: use BFS to print out all the nodes
				System.out.println("All the nodes are: ");
				

		return true;
	}

	/*
	 * The function below is to delete a selected node from the tree. You need
	 * to finish the implementation. 
	 */
	public boolean deleteNode(Node node) {		

		
        //Implementation Here...

				
		
		//The two variables below are for GUI use only. You don't need to use them.
		select_node_value = -1;	
		selected_node = null;
		
		Node right = node.getRight();
		if(right!=null) {
			while(right.getLeft()!=null) {
				right = right.getLeft();
			}
		}else if(node.getLeft()!=null) {
		//right node is null	
			right = node.getLeft();
		}else {
			//No child nodes
			//Only leaf node
			if(node!=root) {
			if(node.isLeft_child_of_parent())node.getParent().setLeft(null);
			else node.getParent().setRight(null);
			node = null;
			}
		}
		//right = node to replace
		//node - node to delete
		if(node == root) {
			if(root.getLeft()!=null && root.getRight()!=null)
			{
				root = null;
				if(node.getRight().getLeft() == null && node.getRight().getRight() == null) {
					root = node.getRight();
					root.setRight(null);
					root.setLeft(node.getLeft());
				}else
				if(node.getRight().getLeft()!=null) {
					root = right;
					right.setLeft(node.getLeft());
					right.setRight(node.getRight());
					node.getRight().setParent(right);
					node.getLeft().setParent(right);
					right.getParent().setLeft(null);
				}else if(node.getRight().getRight()!=null) {
					root = node.getRight();
					root.setLeft(node.getLeft());
					root.setRight(node.getRight().getRight());
				}
			}else if(root.getLeft()==null && root.getRight()==null) {
				root = null;
			}else if(root.getRight()!=null) {
				if(node.getRight().getLeft() == null && node.getRight().getRight() == null) {
					root = node.getRight();
					root.setRight(null);
					root.setLeft(node.getLeft());
				}else
				if(node.getRight().getLeft()!=null) {
					root = right;
					right.setLeft(node.getLeft());
					right.setRight(node.getRight());
					node.getRight().setParent(right);
					if(node.getLeft()!=null)node.getLeft().setParent(right);
					right.getParent().setLeft(null);
				}else if(node.getRight().getRight()!=null) {
					root = node.getRight();
					root.setLeft(node.getLeft());
					root.setRight(node.getRight().getRight());
				}
			}else if(root.getLeft()!=null) {
				if(node.getLeft().getLeft() == null && node.getLeft().getRight() == null) {
					root = node.getLeft();
					root.setLeft(null);
					root.setRight(node.getRight());
				}else
				if(node.getLeft().getRight()!=null) {
					root = node.getLeft().getRight();
					node.getLeft().getRight().setLeft(node.getLeft());
					if(node.getRight()!=null)node.getLeft().getRight().setRight(node.getRight());
					if(node.getRight()!=null)node.getRight().setParent(node.getLeft().getRight());
					if(node.getLeft()!=null)node.getLeft().setParent(node.getLeft().getRight());
					node.getLeft().getRight().getParent().setRight(null);
				}else if(node.getLeft().getLeft()!=null) {
					root = node.getLeft();
					//root.setLeft(node.getLeft().getLeft());
					//root.setRight(node.getRight().getRight());
				}
			}
			
			//One more case: root with two leaf nodes
			//case: root with one leaf node
		}
		else {
			if(node!=null && node != root) {
			//Intermediate node with one children
			//Intermediate node with two leaf nodes
		if(!node.isLeft_child_of_parent())
		{
			if(node.getRight()!=null) {
				if(node.getRight().getLeft()==null && node.getRight().getRight()==null) { //leaf node condition
					Node parent = node.getParent();
					parent.setRight(null);
					node.getRight().setParent(parent);
					if(node.getLeft()!=null)node.getRight().setLeft(node.getLeft());
					else node.getRight().setLeft(null);;
					if(node.getLeft()!=null)node.getLeft().setParent(node.getRight());
					parent.setRight(node.getRight());
					node.setRight(null);
				}
				else {
					if(node.getRight().getRight()!=null) {
						Node parent = node.getParent();
						parent.setRight(null);
						parent.setRight(node.getRight());
						if(node.getLeft()!=null)node.getRight().setLeft(node.getLeft());
						node.getRight().setParent(parent);
						
						
					}else if(node.getRight().getLeft()!=null){
						Node parent = node.getParent();
						parent.setRight(null);
						parent.setRight(right);
						if(node.getLeft()!=null)right.setLeft(node.getLeft());
						node.getRight().setParent(parent);
						right.setRight(node.getRight());
						node.getRight().setLeft(null);
					}
				}
			}else if(node.getLeft()!=null) {
				//leaf node condition
				if(node.getLeft().getLeft()==null && node.getLeft().getRight()==null) {
					Node parent = node.getParent();
					parent.setRight(null);
					node.getLeft().setParent(parent);
					if(node.getRight()!=null)node.getLeft().setRight(node.getRight());
					else node.getLeft().setRight(null);
					if(node.getRight()!=null)node.getRight().setParent(node.getLeft());
					parent.setRight(node.getLeft());
					node.setLeft(null);
				}else {
					if(node.getLeft().getRight()!=null) {
						Node parent = node.getParent();
						parent.setLeft(null);
						parent.setLeft(node.getRight());
						if(node.getLeft()!=null)node.getRight().setLeft(node.getLeft());
						node.getRight().setParent(parent);
						
					}else if(node.getLeft().getLeft()!=null){
						Node parent = node.getParent();
						parent.setLeft(null);
						parent.setLeft(node.getLeft());
						node.getLeft().setParent(parent);
					}
				}
				//not a leaf node condition
			}
			//Intermediate with two leaf nodes - DONE
			//Intermediate with one children - DONE
		}else {
			if(node.getRight()!=null) {
				if(node.getRight().getRight()==null && node.getRight().getLeft()==null) { //leaf node condition
					Node parent = node.getParent();
					parent.setLeft(null);
					node.getRight().setParent(parent);
					if(node.getLeft()!=null)node.getRight().setLeft(node.getLeft());
					else node.getRight().setLeft(null);;
					if(node.getLeft()!=null)node.getLeft().setParent(node.getRight());
					parent.setLeft(node.getRight());
					node.setRight(null);
				}
				else {
					//Not a leaf node
					if(node.getRight().getRight()!=null) {
						Node parent = node.getParent();
						parent.setLeft(null);
						parent.setLeft(node.getRight());
						if(node.getLeft()!=null)node.getRight().setLeft(node.getLeft());
						node.getRight().setParent(parent);
						
						
					}else if(node.getRight().getLeft()!=null){
						Node parent = node.getParent();
						parent.setLeft(null);
						parent.setLeft(right);
						if(node.getLeft()!=null)right.setLeft(node.getLeft());
						node.getRight().setParent(right);
						right.setRight(node.getRight());
						node.getRight().setLeft(null);
					}
					
				}
			}else if(node.getLeft()!=null) {
				//leaf node condition
				if(node.getLeft().getRight()==null && node.getLeft().getLeft()==null) {
					Node parent = node.getParent();
					parent.setLeft(null);
					node.getLeft().setParent(parent);
					parent.setLeft(node.getLeft());
					node.setLeft(null);
				}
				else {					
					if(node.getLeft().getRight()!=null) {
						Node parent = node.getParent();
						parent.setLeft(null);
						parent.setLeft(node.getLeft());
						node.getLeft().setParent(parent);
						node.setLeft(null);
						node.setParent(null);
						
					}else if(node.getLeft().getLeft()!=null){
						Node parent = node.getParent();
						parent.setLeft(null);
						parent.setLeft(node.getLeft());
						node.getLeft().setParent(parent);
					}	
				}
				//not a leaf node condition
			}
			
		}
			
			}
		}
		return true;		
	}
	
	
	

	/************************************************** End of Implementation **************************************************************/

	
	
	/***********************************************
	 * Below are read-only! You don't need to make any changes
	 ******************************/
	/***********************************************
	 * The data members and functions below are just for the GUI Part
	 ******************************************/

	/* Below are the GUI part you don't need to use */
	private int tree_max_height; // record the maximum height of the tree
	private int tree_max_width; // record the maximum width of the tree
	private Canvas canvas; // the canvas where a node is drawn
	private GraphicsContext gc; // the brush used to draw on canvas
	private int canvas_width = 640;
	private int canvas_height = 480;
	private Vector<Vector<Node>> layer_nodes = new Vector<Vector<Node>>(); // store
																			// each
																			// node
																			// into
																			// the
																			// corresponding
																			// layer
	private Node new_node; // the newly inserted node
	private double old_dragging_x;
	private double old_dragging_y;
	private boolean dragging = false;
	private Node selected_node;
	private double delta_x;
	private double delta_y;
	private int radius = 30; // the size of the node
	private int select_node_value; // indicate which node is selected

	public Tree() {

	}

	public Tree(Canvas c, GraphicsContext gc) {
		canvas = c;
		this.gc = gc;
	}

	/*
	 * The function below is for GUI use to make sure the tree fits in the
	 * canvas: (1) according to the current tree distribution, determine the
	 * tree height and width; (2) Check whether there is any overlap between
	 * tree nodes
	 */
	private void organizeTree() {
		// Reset several variables
		layer_nodes.clear(); // clear the layer

		// Set positions on Canvas

	}

	private void setNewProp(Node node) {
		if(node == null)
			return;
		setNewNodeProperties(node);
		if (node.getLeft() != null)
			setNewProp(node.getLeft());
		if (node.getRight() != null)
			setNewProp(node.getRight());
	}

	// Draw the tree on canvas
	public void showTree(boolean insertion_occur) {

		if (insertion_occur) {
			// Set the basic properties for then new node
			setNewProp(root);
		}

		// Traverse the tree and draw all the nodes onto canvas
		bfsTreeDraw(this);

	}

	/* For mouse dragging use (update the sub-tree) */
	private void updateTreePos(Node moving_node, double delta_x, double delta_y) {

		Queue<Node> queue = new LinkedList<Node>();
		Node current_node;

		queue.add(moving_node); // push the root node into queue
		while (!queue.isEmpty()) {
			current_node = queue.remove();

			// Check left child
			if (current_node.getLeft() != null) {
				Point2D pos = new Point2D(current_node.getLeft().getPosition().getX() + delta_x,
						current_node.getLeft().getPosition().getY() + delta_y);
				current_node.getLeft().setPosition(pos);
				queue.add(current_node.getLeft());

			}

			// Check right child
			if (current_node.getRight() != null) {
				Point2D pos = new Point2D(current_node.getRight().getPosition().getX() + delta_x,
						current_node.getRight().getPosition().getY() + delta_y);
				current_node.getRight().setPosition(pos);
				queue.add(current_node.getRight());

			}
		}

	}

	/* Apply Breath First Search Tree to render all the node */
	private void bfsTreeDraw(Tree tree) {
		Queue<Node> queue; // store the retrieved nodes from edges
		Node current_node; // point to the current node processing on

		if (tree.getRoot() == null) {
			return;
		}

		queue = new LinkedList<Node>();

		queue.add(tree.getRoot()); // push the root node into queue
		while (!queue.isEmpty()) {
			current_node = queue.remove();
			current_node.showNode(select_node_value); // draw the node on the
														// canvas

			// Check left child
			if (current_node.getLeft() != null) {

				// Draw the edge between current node the the left child
				double start_x = current_node.getPosition().getX() + radius / 2;
				double start_y = current_node.getPosition().getY() + radius / 2;
				double end_x = current_node.getLeft().getPosition().getX() + radius / 2;
				double end_y = current_node.getLeft().getPosition().getY() + radius / 2;

				// Draw the edge
				gc.setStroke(Color.BLACK);
				gc.strokeLine(start_x, start_y, end_x, end_y);

				// Show current node again to cover the edge
				current_node.showNode(select_node_value);

				queue.add(current_node.getLeft());
			}

			// Check right child
			if (current_node.getRight() != null) {
				// Draw the edge between current node the the right child
				double start_x = current_node.getPosition().getX() + radius / 2;
				double start_y = current_node.getPosition().getY() + radius / 2;
				double end_x = current_node.getRight().getPosition().getX() + radius / 2;
				double end_y = current_node.getRight().getPosition().getY() + radius / 2;

				// Draw the edge
				gc.setStroke(Color.BLACK);
				gc.strokeLine(start_x, start_y, end_x, end_y);

				// Show current node again to cover the edge
				current_node.showNode(select_node_value);

				queue.add(current_node.getRight());
			}
		}

	}

	/*
	 * Apply Breath First Search Tree to (1) find the parent of the target_node
	 * and setup connection between them (2) set the GUI properties for the node
	 */
	private void setNewNodeProperties(Node target_node) {

		Queue<Node> queue; // store the retrieved nodes from edges
		Node current_node; // point to the current node processing on
		layer_nodes.clear(); // clear all the layer

		if (target_node == root) {

			target_node.setDepth(0); // set the root depth to 0
			target_node.setParent(null);
			Point2D pos = new Point2D(320, 5); // do the setting for the root
			target_node.setPosition(pos);
			target_node.setDepth(0);

			Vector<Node> layer = new Vector<Node>();
			layer.add(target_node);
			layer_nodes.add(layer);
			target_node.setLayer_idx(0);

			return;
		}

		// Start the BFS search
		queue = new LinkedList<Node>();
		queue.add(getRoot()); // push the root node into queue

		while (!queue.isEmpty()) {
			current_node = queue.remove();

			/* If current node is in a new layer, then create a new layer */
			if (layer_nodes.size() - 1 < current_node.getDepth()) {
				Vector<Node> layer = new Vector<Node>();
				layer.add(current_node);
				layer_nodes.add(layer);
				current_node.setLayer_idx(0);
			} else {
				layer_nodes.get(current_node.getDepth()).add(current_node);
				current_node.setLayer_idx(layer_nodes.get(current_node.getDepth()).size() - 1);
			}

			if (current_node.getLeft() == target_node) {

				/* Set the depth and parent information */
				target_node.setDepth(current_node.getDepth() + 1);
				target_node.setParent(current_node);
				target_node.setLeft_child_of_parent(true);
			} else if (current_node.getRight() == target_node) {
				/* Set the depth and parent information */
				target_node.setDepth(current_node.getDepth() + 1);
				target_node.setParent(current_node);
				target_node.setLeft_child_of_parent(false);

			}

			/*
			 * If the current node does not have the target node as one of its
			 * children, then keep finding it
			 */
			if (current_node.getLeft() != null)
				queue.add(current_node.getLeft());
			if (current_node.getRight() != null)
				queue.add(current_node.getRight());

		}

		/* Set the 2D position on Canvas */
		setNodePosition(target_node);
	}

	/*
	 * Set the target_node position. This new node may affect the other node's
	 * current positions
	 */
	void setNodePosition(Node target_node) {
		double x_left_offset = -(double) radius * 6.0 / (double) target_node.getDepth() + 2.0; // the
																								// left
																								// child
																								// x
																								// relative
																								// position
																								// to
																								// the
																								// current
																								// node's
																								// x
																								// position
																								// (maximum
																								// offset)
		double x_right_offset = (double) radius * 6.0 / (double) target_node.getDepth() + 2.0;
		int y_offset = radius * 2; // the left child x position relative
									// (maximum offset)

		// Below are the two indicator whether need to adjust the tree size
		boolean layer_crowded = false;
		boolean tree_too_high = false;

		// Set position for the target_node
		Point2D pos = new Point2D(0, 0); // the position will be assigned to the
											// target_node
		if (target_node.isLeft_child_of_parent()) {
			pos = new Point2D(target_node.getParent().getPosition().getX() + x_left_offset,
					target_node.getParent().getPosition().getY() + y_offset);
		} else {
			pos = new Point2D(target_node.getParent().getPosition().getX() + x_right_offset,
					target_node.getParent().getPosition().getY() + y_offset);
		}
		target_node.setPosition(pos);

		// Check this new node has overlapping with its left or right neighbors
		if (target_node.getLayer_idx() > 0) // check overlapping with left
											// neighbor
		{
			int left_neighbor = target_node.getLayer_idx() - 1;
			Point2D left_pos = layer_nodes.get(target_node.getDepth()).get(left_neighbor).getPosition();
			if (pos.getX() - left_pos.getX() < radius) {
				layer_crowded = true;
				System.out.println("Overlapping with left! "
						+ layer_nodes.get(target_node.getDepth()).get(left_neighbor).getValue());
			}
		}

	}

	/* Tracking the mouse event to see whether a node is being dragged. */
	public void finishNodeDragging(double x, double y) {
		dragging = false;
		delta_x = 0;
		delta_y = 0;
	}

	/* Tracking the mouse event to see whether a node is being dragged. */
	public void checkNodeDragging(double x, double y) {
		for (int j = 0; j < layer_nodes.size(); j++) {
			for (int i = 0; i < layer_nodes.get(j).size(); i++) {
				double node_x = layer_nodes.get(j).get(i).getPosition().getX();
				double node_y = layer_nodes.get(j).get(i).getPosition().getY();
				if (((node_x + radius / 2 - x) * (node_x + radius / 2 - x)
						+ (node_y + radius / 2 - y) * (node_y + radius / 2 - y)) < radius * radius / 4) {
					dragging = true;
					selected_node = layer_nodes.get(j).get(i);
					old_dragging_x = x;
					old_dragging_y = y;
					select_node_value = selected_node.getValue();

					gc.clearRect(0, 0, canvas_width, canvas_height);
					bfsTreeDraw(this);
					break;
				}
			}
			if (dragging == true)
				break;
		}

		// update the selection
		if (dragging == false) {
			select_node_value = -1;
			gc.clearRect(0, 0, canvas_width, canvas_height);
			bfsTreeDraw(this);
		}

	}

	/*
	 * If a node is being dragged, update its position and its children's
	 * positions
	 */
	public void doNodeDragging(double x, double y) {
		// update dragged node position
		if (dragging == true) {

			delta_x = x - old_dragging_x;
			delta_y = y - old_dragging_y;
			Point2D pos = new Point2D(selected_node.getPosition().getX() + delta_x,
					selected_node.getPosition().getY() + delta_y);
			selected_node.setPosition(pos);
			old_dragging_x = x;
			old_dragging_y = y;
			updateTreePos(selected_node, delta_x, delta_y);
			gc.clearRect(0, 0, canvas_width, canvas_height);
			bfsTreeDraw(this);
		}

	}

	/* Adjust the tree width according to the layer value */
	// layer_idx stores the layer index that has issue with the new node
	void adjustTreeWidth(int layer_idx) {
		int x_left_offset = -radius * 3; // the left child x relative position
											// to the current node's x position
											// (maximum offset)
		int x_right_offset = radius * 3;
		int y_offset = radius * 2; // the left child x position relative
									// (maximum offset)

		// Calculate the width of problem layer = # * (node size + node gap)
		int target_layer_width = layer_nodes.get(layer_idx).size() * radius * 2
				+ (layer_nodes.get(layer_idx).size() - 1) * x_right_offset * 2;
		Point2D layer_center = new Point2D(canvas_width / 2, new_node.getPosition().getY());

		// If the problem layer width is smaller than the canvas width, then no
		// need to adjust the node size but change the current layer's position
		if (target_layer_width < canvas_width) {
			// If there are even number of nodes
			if (layer_nodes.get(layer_idx).size() % 2 == 0) {
				int left_node_besides_center = layer_nodes.get(layer_idx).size() / 2;
				int right_node_besides_center = layer_nodes.get(layer_idx).size() / 2 + 1;
				System.out.println("center: " + layer_center.getX());
				// Update the nodes positions on the left side
				Point2D pos = new Point2D(layer_center.getX() + x_left_offset, layer_center.getY());
				System.out.println("left: " + pos.getX());
				layer_nodes.get(layer_idx).get(left_node_besides_center).setPosition(pos);
				for (int i = left_node_besides_center - 1; i >= 0; i--) {
					pos = new Point2D(layer_nodes.get(layer_idx).get(i + 1).getPosition().getX() + x_left_offset * 2,
							layer_center.getY());
					layer_nodes.get(layer_idx).get(i).setPosition(pos);
				}

				// Update the nodes positions on the left side
				pos = new Point2D(layer_center.getX() + x_right_offset, layer_center.getY());
				System.out.println("Right: " + pos.getX());
				layer_nodes.get(layer_idx).get(left_node_besides_center).setPosition(pos);
				for (int i = right_node_besides_center + 1; i < layer_nodes.get(layer_idx).size(); i++) {
					pos = new Point2D(layer_nodes.get(layer_idx).get(i - 1).getPosition().getX() + x_right_offset * 2,
							layer_center.getY());
					layer_nodes.get(layer_idx).get(i).setPosition(pos);
				}

			}
			// If there are odd number of nodes
			else {
				int node_at_center = (layer_nodes.get(layer_idx).size() + 1) / 2;

				Point2D pos = new Point2D(layer_center.getX(), layer_center.getY());
				layer_nodes.get(layer_idx).get(node_at_center).setPosition(pos);

				// Update the nodes positions on the left side
				for (int i = node_at_center - 1; i >= 0; i--) {
					pos = new Point2D(layer_nodes.get(layer_idx).get(i + 1).getPosition().getX() + x_left_offset * 2,
							layer_center.getY());
					layer_nodes.get(layer_idx).get(i).setPosition(pos);
				}

				// Update the nodes positions on the left side
				for (int i = node_at_center + 1; i < layer_nodes.get(layer_idx).size(); i++) {
					pos = new Point2D(layer_nodes.get(layer_idx).get(i - 1).getPosition().getX() + x_right_offset * 2,
							layer_center.getY());
					layer_nodes.get(layer_idx).get(i).setPosition(pos);
				}
			}
		}

	}

	/*
	 * Apply Breath First Search Tree to set basic tree properties for each
	 * node: (left or right) parent, children, depth, push node to corresponding
	 * layer
	 */
	private void setBasicTreeProperties(Tree tree) {

		Queue<Node> queue; // store the retrieved nodes from edges
		Node current_node; // point to the current node processing on

		if (tree.getRoot() == null) {
			return;
		}

		tree.getRoot().setDepth(0); // set the root depth to 0
		tree.getRoot().setParent(null);

		// Start the BFS search
		queue = new LinkedList<Node>();
		queue.add(tree.getRoot()); // push the root node into queue
		while (!queue.isEmpty()) {
			current_node = queue.remove();

			/* If current node is in a new layer, then create a new layer */
			if (layer_nodes.size() - 1 < current_node.getDepth()) {
				Vector<Node> layer = new Vector<Node>();
				layer.add(current_node);
				layer_nodes.add(layer);
			} else {
				layer_nodes.get(current_node.getDepth()).add(current_node);
			}

			// Check left child
			if (current_node.getLeft() != null) {
				current_node.getLeft().setParent(current_node); // let the left
																// child point
																// to the
																// current node
				current_node.getLeft().setLeft_child_of_parent(true); // let the
																		// left
																		// child
																		// have
																		// left
																		// parent
				current_node.getLeft().setDepth(current_node.getDepth() + 1); // assign
																				// the
																				// depth
																				// to
																				// the
																				// left
																				// child

				queue.add(current_node.getLeft());
			}

			// Check right child
			if (current_node.getRight() != null) {
				current_node.getRight().setParent(current_node); // let the left
																	// child
																	// point to
																	// the
																	// current
																	// node
				current_node.getRight().setLeft_child_of_parent(false); // let
																		// the
																		// left
																		// child
																		// have
																		// left
																		// parent
				current_node.getRight().setDepth(current_node.getDepth() + 1); // assign
																				// the
																				// depth
																				// to
																				// the
																				// left
																				// child
				queue.add(current_node.getRight());
			}
		}

	}

	/*
	 * Because of the position change of one layer of nodes, this information is
	 * propagated to other layers
	 */
	void changePropogation() {

		Node node = root; // node is just a temporary node holder

		Point2D pos = new Point2D(320, 0); // do the setting for the root
		root.setPosition(pos);

		int x_left_offset = -radius * 3; // the left child x relative
											// position
											// to the current node's x position
											// (maximum offset)
		int x_right_offset = radius * 3;
		int y_offset = radius * 2; // the left child x position relative
									// (maximum offset)

		/* Preset all the nodes' positions; some of them maybe changed later */
		for (int i = 1; i < layer_nodes.size(); i++) {
			for (int j = 0; j < layer_nodes.get(i).size(); j++) {
				if (layer_nodes.get(i).get(j).isLeft_child_of_parent())
					pos = new Point2D(layer_nodes.get(i).get(j).getParent().getPosition().getX() + x_left_offset,
							layer_nodes.get(i).get(j).getParent().getPosition().getY() + y_offset);
				else
					pos = new Point2D(layer_nodes.get(i).get(j).getParent().getPosition().getX() + x_right_offset,
							layer_nodes.get(i).get(j).getParent().getPosition().getY() + y_offset);

				layer_nodes.get(i).get(j).setPosition(pos);
			}
		}

		/*
		 * Traverse all the layers to see if any layer has node overlapping or
		 * out of the range issue
		 */
		for (int y = layer_nodes.size() - 1; y >= 0; y--) // start from the
															// bottom layer
		{

			boolean width_issue = false; // an indicator that the tree suffers
											// width problem
			boolean height_issue = false; // an indicator the tree suffers
											// height problem

			// Below is to process each layer
			for (int x = 0; x < layer_nodes.get(y).size(); x++) {
				// If the tree is too wide
				if (layer_nodes.get(y).get(x).getPosition().getX() < radius / 2
						|| layer_nodes.get(y).get(x).getPosition().getX() > (canvas_width - radius / 2)) {
					width_issue = true;
				}

				// If the tree is too high
				else if (layer_nodes.get(y).get(x).getPosition().getY() > (canvas_height - radius / 2)) {

				}

			}
		}

	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public Node getNew_node() {
		return new_node;
	}

	public void setNew_node(Node new_node) {
		this.new_node = new_node;
	}

	public int getSelect_node_value() {
		return select_node_value;
	}

	public void setSelect_node_value(int select_node_value) {
		this.select_node_value = select_node_value;
	}

	public Node getSelectedNode() {
		return selected_node;
	}

	public void setSelectedNode(Node dragged_node) {
		this.selected_node = dragged_node;
	}
}
