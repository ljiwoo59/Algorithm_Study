package linkedlist;

public class SingleLinkedList {

	private Node head; // 첫번째 노드를 저장
	
	// 첫번쨰 노드로 삽입하기
	public void addFirstNode(String data) {
		Node newNode = new Node(data, head);
		head = newNode;
	}
	
	// 마지막 노드 찾기
	public Node getLastNode() {
		for (Node currNode = head; currNode != null; currNode = currNode.link) {
			if (currNode.link == null) {
				return currNode;
			}
		}
		return null;
	}
	
	// 마지막 노드로 삽입하기
	public void addLastNode(String data) {
		if (head == null)
			addFirstNode(data);
		else {
			Node newNode = new Node(data);
			getLastNode().link = newNode;
		}
	}
	
	public void insetAfterNode(Node preNode, String data) {
		if (preNode == null) {
			System.out.println("no prenode");
			return ;
		}
		Node newNode = new Node(data, preNode.link);
		preNode.link = newNode;
	}
	
	// data 로 노드 찾기
	public Node getNode(String data) {
		for (Node currNode = head; currNode != null; currNode = currNode.link) {
			if (currNode.data.equals(data))
				return currNode;
		}
		return null;
	}
	
	// target 의 이전 노드 찾기
	public Node getPreviousNode(Node target) {
		for (Node currNode = head; currNode != null; currNode = currNode.link) {
			if (currNode.link == target)
				return currNode;
		}
		return null;
	}
	
	// data 를 갖고있는 노드 삭제
	public void deleteNode(String data) {
		Node targetNode = getNode(data);
		if (targetNode == null) {
			System.out.println("no node");
			return;
		}
		Node preNode = getPreviousNode(targetNode);
		if (preNode == null) {
			head = targetNode.link;
			return;
		}
		preNode.link = targetNode.link;
		targetNode = null;
	}
	
	public void printList() {
		System.out.print("L = ( ");
		for (Node currNode = head; currNode != null; currNode = currNode.link) {
			System.out.print(currNode.data + " ");
		}
		System.out.println(") ");
	}
}
