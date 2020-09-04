package nowcoder;

class ListNode1 {
    int val;
    ListNode1 next = null;

    ListNode1(int val) {
        this.val = val;
    }
}

public class listnode {
    public static ListNode1 ReverseList(ListNode1 head) {
        if(head == null){
            return null;
        }
        ListNode1 cur = head;
        ListNode1 result = null;
        while(cur != null){
            if(result == null){
                result = new ListNode1(cur.val);
            }else {
                ListNode1 listNode = new ListNode1(cur.val);
                listNode.next = result;
                result = listNode;
            }
            cur = cur.next;
        }
        return result;
    }

    public static ListNode1 FindKthToTail(ListNode1 head,int k) {
        ListNode1 p = head,q;
        int i = 0;
        for (q = head; p != null; i++) {
            if (i >= k){
                q = q.next;
            }
            p = p.next;
        }
        if(i < k){
            return null;
        }
        return q;
    }


    public static boolean isLT(int num){
        if(num >= 10){
            return true;
        }else {
            return false;
        }
    }

    public ListNode1 addInList (ListNode1 head1, ListNode1 head2) {
        // write code here
        if(head1 == null){
            return head2;
        }
        if(head2 == null){
            return head1;
        }
        head1 = ReverseList(head1);
        head2 = ReverseList(head2);
        ListNode1 result = null;
        int num = head1.val + head2.val;
        boolean isLT = isLT(num);
        result = new ListNode1(num % 10);
        ListNode1 cur = result;
        head1 = head1.next;
        head2 = head2.next;
        while(head1 != null && head2 != null){
            num = head1.val + head2.val;
            if(isLT){
                num += 1;
            }
            ListNode1 temp = new ListNode1(num % 10);
            result.next = temp;
            result = result.next;
            head1 = head1.next;
            head2 = head2.next;
            isLT = isLT(num);
        }
        while(head1 != null){
            if(isLT){
                head1.val += 1;
            }
            isLT = isLT(head1.val);
            head1.val %= 10;
            result.next = head1;
            result = result.next;
            head1 = head1.next;
        }
        while(head2 != null){
            if(isLT){
                head2.val += 1;
            }
            isLT = isLT(head2.val);
            head2.val %= 10;
            result.next = head2;
            result = result.next;
            head2 = head2.next;
        }
        if(isLT){
            result.next = new ListNode1(1);
        }
        cur = ReverseList(cur);
        return cur;
    }

    public static void main(String[] args) {
        ListNode1 node1 = new ListNode1(6);
        ListNode1 node2 = new ListNode1(3);

        ListNode1 node3 = new ListNode1(9);
        ListNode1 node4 = new ListNode1(3);
        ListNode1 node5 = new ListNode1(7);

        node1.next = node2;
//        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

//        ListNode1 r = FindKthToTail(node1,1);
//        ListNode1 r = addInList(node1,node3);
    }
}
