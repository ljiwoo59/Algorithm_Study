package linkedlist;

public class SingleLinkedListTest {

	public static void main(String[] args) {
		
		SingleLinkedList list = new SingleLinkedList();
		
		list.addLastNode("grape");
		System.out.println(list.getLastNode());
		list.printList();

		list.addLastNode("melon");
		list.printList();
		
		list.addFirstNode("strawberry");
		list.printList();
		
		System.out.println(list.getLastNode());
		System.out.println(list.getNode("grape"));
		
		list.deleteNode("grape");
		list.printList();
		
	}

}
