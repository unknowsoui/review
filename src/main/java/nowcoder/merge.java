package nowcoder;

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

public class merge {
    public static ListNode Merge(ListNode list1, ListNode list2) {
        ListNode result;
        int resultval = Math.min(list1.val,list2.val);
        result = new ListNode(resultval);
        ListNode head = result;
        while(list1 != null && list2 != null){
            if(list1.val < list2.val){
                result.next = list1;
                list1 = list1.next;
            }else{
                result.next = list2;
                list2 = list2.next;
            }
            result = result.next;
        }
        if(list1 != null){
            result.next = list1;
        }
        if(list2 != null){
            result.next = list2;
        }
        head = head.next;
        return head;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(3);
        ListNode listNode2 = new ListNode(5);
        listNode.next = listNode1;
        listNode1.next = listNode2;
        ListNode listNode3 = new ListNode(2);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(6);
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        ListNode newListNode = Merge(listNode,listNode3);
    }
}
