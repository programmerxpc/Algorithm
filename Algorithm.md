# 排序

## 1 arr[L...R]范围上求最大值

```java
// mid=(L+R)/2  L+R可能会溢出
// mid=L+(R-L)/2
// mid=L+(R-L)>>1
public class GetMax_220630 {
    public static void main(String[] args) {
        int[] arr = {3,0,1,2,10,27,7,25};
        System.out.println(getMax(arr, 0, arr.length-1));
    }

    private static int getMax(int[] arr, int L, int R){
        if (L == R)
            return arr[L];
        int mid = L + ((R - L) >> 1);
        int leftMax = getMax(arr, L, mid);
        int rightMax = getMax(arr, mid+1, R);
        return Math.max(leftMax, rightMax);
    }
}
```



## 2 归并排序

```java
    public static void process(int[] arr, int L, int R) {
        if (L == R)
            return;
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid+1, R);
        merge(arr, L, mid, R);
    }

    private static void merge(int[] arr, int L, int M, int R) {
        int help[] = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) { // 都不越界
            if (arr[p1] <= arr[p2]){
                help[i] = arr[p1];
                p1++;
                i++;
            } else {
                help[i] = arr[p2];
                p2++;
                i++;
            }
        }
        while (p1 <= M) {
            help[i] = arr[p1];
            p1++;
            i++;
        }
        while (p2 <= R) {
            help[i] = arr[p2];
            p2++;
            i++;
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }
```



## 3 快速排序

```java
    public static void main(String[] args) {
        int[] arr = {3,6,2,5,7,5};
        System.out.println(Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    // arr[L...R]排好序
    public static void quickSort(int[] arr, int L, int R) {
        if (L < R) {
            swap(arr, L + (int)(Math.random() * (R - L + 1)), R);
            int[] p = partition(arr, L, R);
            quickSort(arr, L, p[0] - 1); // <区
            quickSort(arr, p[1] + 1, R); // >区
        }
    }

    // 处理arr[L...R]的函数
    // 返回等于区域（左边界，右边界），所以返回一个长度为2的数组
    public static int[] partition(int[] arr, int L, int R) {
        int less = L - 1; // <区右边界
        int more = R; // >区左边界
        while (L < more) { // L表示当前数的位置
            if (arr[L] < arr[R]) { // 当前数 < 划分值 当前数和小于区的下一个作交换，小于区右扩，L++
                swap(arr, ++less, L++);
            } else if (arr[L] > arr[R]) { // 当前数 > 划分值 当前数和大于区的前一个作交换，大于区左扩，L不变（L是新交换过来的还没判断）
                swap(arr, --more, L);
            } else {
                L++;
            }
        }
        swap(arr, more, R);
        return new int[] {less + 1, more};
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
```



## 4 堆排序

```java
    public static void main(String[] args) {
        int[] arr = {3,6,2,5,7,5};
        System.out.println(Arrays.toString(arr));
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void heapSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    // 某个数现在处在index位置，往上继续移动
    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    // 某个数在index位置，能否往下移动
    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1; // 左孩子的下标
        while (left < heapSize) { // 下方还有孩子的时候
            // 两个孩子中，谁的值大，就把下标largest
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left]
                    ? left + 1 : left;
            // 父和孩子之间，谁的值大，就把下标给largest
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
```

堆排序扩展：

已知一个几乎有序的数组，几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离可以不超过k，并且k相对于数组来说比较小。请选择一个合适的排序算法针对这个数据进行排序。

```java
    public static void main(String[] args) {
        int[] arr = new int[] {8, 3, 1, 9, 9, 20};
        System.out.println(Arrays.toString(arr)); // [8, 3, 1, 9, 9, 20]
        sortArrayDistanceLessK(arr, 2);
        System.out.println(Arrays.toString(arr)); // [1, 3, 8, 9, 9, 20]
    }

    public static void sortArrayDistanceLessK(int[] arr, int k) {
        // 默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        for (; index < Math.min(arr.length, k); index++) {
            heap.add(arr[index]);
        }
        int i = 0;
        for (; index < arr.length; i++, index++) {
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }
```



## 5 桶排序

```java
    public static void main(String[] args) {
        int[] arr = new int[] {8, 3, 1, 9, 9, 20};
        System.out.println(Arrays.toString(arr)); // [8, 3, 1, 9, 9, 20]
        radixSort(arr);
        System.out.println(Arrays.toString(arr)); // [1, 3, 8, 9, 9, 20]
    }

    public static void radixSort(int[] arr) {
        radixSort(arr, 0, arr.length - 1, maxbits(arr));
    }

    // 最大的数有多少位
    public static int maxbits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int res = 0;
        while (max != 0) {
            res++;
            max /= 10;
        }
        return res;
    }

    public static int getDigit(int x, int d) {
        return ((x / ((int) Math.pow(10, d - 1))) % 10);
    }

    // arr[begin...end]排序
    public static void radixSort(int[] arr, int L, int R, int digit) {
        final int radix = 10;
        int i = 0, j = 0;
        // 有多少个数准备多少个辅助空间
        int[] bucket = new int[R - L + 1];
        for (int d = 1; d <= digit; d++) { // 有多少次位就进出桶多少次
            int[] count = new int[radix];
            for (i = L; i <= R; i++) {
                j = getDigit(arr[i], d);
                count[j]++;
            }
            for (i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1]; // 将count数组处理成最小前缀和数组
            }
            for (i = R; i >= L; i--) {
                j = getDigit(arr[i], d);
                bucket[count[j] - 1] = arr[i];
                count[j]--;
            }
            for(i = L, j = 0; i <= R; i++, j++) {
                arr[i] = bucket[j];
            }
        }
    }
```



# 链表

## 1 反转链表

### 1.1 反转单向链表

```java
    public static void main(String[] args) {
        Node node = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        node.next = node1;
        node1.next = node2;
        node2.next = null;

        Node head = reverseNode(node);
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data+" ");
            temp = temp.next;
        }
    }

    static class Node {
        private Object data;
        private Node next;

        public Node(Object data) {
            this.data = data;
        }
    }

    public static Node reverseNode(Node head) {
        Node cur = head;
        Node pre = null;
        while (cur != null) {
            // 保存当前节点下一个节点
            Node temp = cur.next;
            // 指向前一个节点（头结点指向null）
            cur.next = pre;
            // pre和cur向后移
            pre = cur;
            cur = temp;
        }
        return pre;
    }
```



### 1.2 反转双向链表

```java
    static class DNode {
        private Object data;
        private DNode pre;
        private DNode next;

        public DNode(Object data) {
            this.data = data;
        }
    }

    public static DNode reverseDNode(DNode head) {
        DNode cur = head;
        DNode pre = null;
        while (cur != null) {
            // 交换后向指针和前向指针
            DNode temp = cur.next;
            cur.next = pre;
            cur.pre = temp;
            // pre和cur后移
            pre = cur;
            cur = temp;
        }
        return pre;
    }
```



## 2 打印两个有序链表的公共部分

```java
    public static void printListCommon(Node head1, Node head2) {
        Node cur1 = head1;
        Node cur2 = head2;
        while (cur1 != null && cur2 != null) {
            if (cur1.data < cur2.data) {
                cur1 = cur1.next;
            }
            if (cur1.data > cur2.data) {
                cur2 = cur2.next;
            }
            if (cur1.data == cur2.data) {
                System.out.println(cur1.data + " ");
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
        }
    }
```



## 3 判断一个链表是否为回文结构

给定一个单链表的头结点head，请判断该链表是否为回文结构。

```java
    public static boolean judgeList(Node head) { // 借助栈完成
        Node cur = head;
        Stack<Integer> stack = new Stack<>();
        while (cur != null) {
            stack.push(cur.data);
            cur = cur.next;
        }
        cur = head;
        while (!stack.isEmpty() && cur != null) {
            if (!stack.peek().equals(cur.data)) {
                return false;
            }
            stack.pop();
            cur = cur.next;
        }
        return true;
    }

    static class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }
```

```java
    public static boolean judgeList1(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node n1 = head;
        Node n2 = head;
        while (n2.next != null && n2.next.next != null) { // 找中间节点
            n1 = n1.next;
            n2 = n2.next.next;
        }
        n2 = n1.next; // n2指向右部分第一个节点
        n1.next = null; // n1指向null
        Node n3 = null;
        while (n2 != null) { // 右部分反转
            n3 = n2.next; // n3 保存下一个节点
            n2.next = n1; // 右边下一个节点反转
            n1 = n2; // n1右移
            n2 = n3; // n2右移
        }
        n3 = n1; // n3保存最后一个节点
        n2 = head; // n2左边第一个节点
        boolean res = true;
        while (n1 != null && n2 != null) { // 判断回文
            if (n1.data != n2.data) {
                res = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        n1 = n3.next;
        n3.next = null;
        while (n1 != null) { // 反转回去
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;
    }
```



## 4 按某值划分单链表

将单向链表按某值划分成左边小、中间相等、右边大的形式。

给定一个单链表的头结点

```java
    public static Node listPartition2(Node head, int pivot) {
        Node sH = null;
        Node sT = null;
        Node eH = null;
        Node eT = null;
        Node mH = null;
        Node mT = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.data < pivot) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = head;
                }
            } else if (head.data == pivot) {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = head;
                }
            } else {
                if (mH == null) {
                    mH = head;
                    mT = head;
                } else {
                    mT.next = head;
                    mT = head;
                }
            }
            head = next;
        }
        // 小于区和等于区连接
        if (sT != null) { // 如果有小于区域
            sT.next = eH;
            eT = eT == null ? sT : eT; // 下一步，谁去连大于区域的头，谁就变成eT
        }
        if (eT != null) { // 如果小于区域和等于区域，不是都没有
            eT.next = mH;
        }
        return sH != null ? sH : (eH != null ? eH : mH);
    }

    static class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }
```

```java
    public static void arrPartition(Node[] nodeArr, int pivot) {
        int small = -1;
        int big = nodeArr.length;
        int index = 0;
        while (index != big) {
            if (nodeArr[index].data < pivot) {
                swap(nodeArr, ++small, index++);
            } else if (nodeArr[index].data == pivot) {
                index++;
            } else {
                swap(nodeArr, --big, index);
            }
        }
    }

    public static void swap(Node[] arr, int i, int j) {
        Node temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static Node listPartition1(Node head, int pivot) {
        if (head == null) {
            return head;
        }
        Node cur = head;
        int i = 0;
        while (cur != null) {
            i++;
            cur = cur.next;
        }
        Node[] nodeArr = new Node[i];
        i = 0;
        cur = head;
        for (i = 0; i < nodeArr.length; i++) {
            nodeArr[i] = cur;
            cur = cur.next;
        }
        arrPartition(nodeArr, pivot);
        for (i = 1; i < nodeArr.length; i++) {
            nodeArr[i - 1].next = nodeArr[i];
        }
        nodeArr[i - 1].next = null;
        return nodeArr[0];
    }
```



## 5 复制含有随机指针节点的链表

一种特殊的单链表节点类描述如下：

```java
class Node {
    int value;
    Node next;
    Node rand;
    Node(int val) {
        value = val;
    }
}
```

rand指针是单链表节点结构中新增的指针，rand可能指向链表中的任意一个节点，也可能指向null。给定一个由Node节点类型组成的无环单链表的头结点head，请实现一个函数完成这个链表的复制，并返回复制的新链表的头结点。

```java
    static class Node {
        int value;
        Node next;
        Node rand;
        Node(int val) {
            value = val;
        }
    }

    public static Node copyListWithRand1(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }
```

```java
    public static Node copyListWithRand2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node next = null;
        // 在当前节点后克隆一个节点
        // 1 -> 2
        // 1 -> 1' -> 2
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next;
            cur = next;
        }
        cur = head;
        Node curCopy = null;
        // 设置克隆节点rand
        // 1 -> 1' -> 2 -> 2'
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.rand = cur.rand != null ? cur.rand.next : null;
            cur = next;
        }
        Node res = head.next;
        cur = head;
        // 分离
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            cur.next = next;
            curCopy.next = next != null ? next.next : null;
            cur = next;
        }
        return res;
    }
```



## 6 两个单链表相交

​    给定两个可能有环也可能无环的单链表，头结点head1和head2。请实现一个函数，如果两个链表相交，请返回相交的第一个节点。如果不相交，返回null。

​	如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度请达到O(1)。

```java
    static class Node {
        private int value;
        private Node next;

        public Node(int value){
            this.value = value;
        }
    }

    // 找到链表第一个入环节点，如果无环，返回null
    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node n1 = head.next; // n1 -> slow
        Node n2 = head.next.next; // n2 -> fast
        while (n1 != n2) {
            if (n2.next == null || n2.next.next == null) {
                return null;
            }
            n2 = n2.next.next;
            n1 = n1.next;
        }
        n2 = head; // n2 回到头结点
        while (n1 != n2) {
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;
    }

    // 如果两个链表都无环，返回第一个相交节点，如果不相交返回null
    public static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }
        if (cur1 != cur2) {
            return null;
        }
        // n: 链表1长度减去链表2长度的值
        cur1 = n > 0 ? head1 : head2; // cur1指向长链表的头
        cur2 = cur1 == head1 ? head2 : head1; // cur2指向短链表的头
        n = Math.abs(n);
        while (n != 0) {
            n--;
            cur1 = cur1.next;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    // 两个有环链表，返回第一个相交的节点，如果不相交返回null
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;
        if (loop1 == loop2) {
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            cur1 = loop1.next;
            while (cur1 != loop1) {
                if (cur1 == loop2) {
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }

    public static Node getInterSectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        return null;
    }
```



# 二叉树

## 遍历

### 先序遍历非递归

1. 从栈中弹出一个节点cur；
2. 打印（处理）cur；
3. 先把cur右孩子压入栈再把其左孩子压入栈（如果有）；
4. 重复上面直到栈为空。

```java
    public static void preOrderUnRecur(Node head) {
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            stack.add(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.print(head.value);
                if (head.right != null) {
                    stack.push(head.right);
                }
                if (head.left != null) {
                    stack.push(head.left);
                }
            }
        }
    }
```



### 后序遍历非递归

1. 弹出cur；
2. cur放入收集栈；
3. 先把cur左孩子压入栈，再把右孩子压入栈；
4. 重复上面直到栈为空。

```java
    public static void posOrderUnRecur1(Node head) {
        if (head != null) {
            Stack<Node> s1 = new Stack<>();
            Stack<Node> s2 = new Stack<>();
            s1.push(head);
            while (!s1.empty()) {
                head = s1.pop();
                s2.push(head);
                if (head.left != null) {
                    s1.push(head.left);
                }
                if (head.right != null) {
                    s1.push(head.right);
                }
            }
            while (!s2.empty()) {
                System.out.print(s2.pop().value + " ");
            }
        }
    }
```



### 中序遍历非递归

```java
    public static void inOrderUnRecur(Node head) {
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            while (!stack.empty() || head != null) {
                if (head != null) { // 把左边界进栈
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.print(head.value + "");
                    head = head.right;
                }
            }
        }
    }
```



### 层次遍历

```java
    public static void w(Node head) {
        if (head == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }
```



## 1 二叉树最大宽度

```java
    public static int w1(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Map<Node, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);
        int curLevel = 1;
        int curLevelNodes = 0;
        int max = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int curNodeLevel = levelMap.get(cur);
            if (curNodeLevel == curLevel) {
                curLevelNodes++;
                max = Math.max(max, curLevelNodes);
            } else {
                curLevel++;
                curLevelNodes = 1;
                max = Math.max(max, curLevelNodes);
            }
            if (cur.left != null) {
                levelMap.put(cur.left, curNodeLevel+1);
                queue.add(cur.left);
            }
            if (cur.right != null) {
                levelMap.put(cur.right, curNodeLevel+1);
                queue.add(cur.right);
            }
        }
        return max;
    }
```



## 搜索二叉树

任何一个节点的左孩子比该节点小，右节点比该节点大。

## 2 判断搜索二叉树

```java
    public static class ReturnData {
        private boolean isBST;
        private int min;
        private int max;

        public ReturnData(boolean isBST, int min, int max) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }
    public static ReturnData process(Node x) {
        if (x == null) {
            return null;
        }
        ReturnData leftData = process(x.left);
        ReturnData rightData = process(x.right);
        int min = x.value;
        int max = x.value;
        if (leftData != null) {
            min = Math.min(min, leftData.min);
            max = Math.max(max, leftData.max);
        }
        if (rightData != null) {
            min = Math.min(min, rightData.min);
            max = Math.max(max, rightData.max);
        }
        boolean isBST = true;
        if (leftData != null && (!leftData.isBST || leftData.max >= x.value)) {
            isBST = false;
        }
        if (rightData != null && (!rightData.isBST || x.value >= rightData.min)) {
            isBST = false;
        }
        return new ReturnData(isBST, min, max);
    }
```

```java
    public static int preValue = Integer.MIN_VALUE;

    public static boolean checkBST(Node head) {
        if (head == null) {
            return true;
        }
        boolean isLeftBst = checkBST(head.left);
        if (!isLeftBst) {
            return false;
        }
        if (head.value < preValue) {
            return false;
        } else {
            preValue = head.value;
        }
        return checkBST(head.right);
    }

```

```java
    public static boolean checkBST2(Node head) {
        List<Node> inOrderList = new ArrayList<>();
        process2(head, inOrderList);
        for (int i = 1; i < inOrderList.size(); i++) {
            if (inOrderList.get(i-1).value > inOrderList.get(i).value) {
                return false;
            }
        }
        return true;
    }
    public static void process2(Node head, List<Node> inOrderList) {
        if (head == null) {
            return;
        }
        process2(head.left, inOrderList);
        inOrderList.add(head);
        process2(head.right, inOrderList);
    }
```



## 完全二叉树

二叉树不满的只有最后一层。

## 3 判断完全二叉树

```java
    public static boolean isCBT(Node head) {
        if (head == null) {
            return true;
        }
        Queue<Node> queue = new LinkedList<>();
        // 是否遇到过左右两个孩子不双全的节点
        boolean leaf = false;
        Node l = null;
        Node r = null;
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            if ((leaf && (l != null || r != null)) || (l == null && r != null)) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;
    }
```



## 4 判断平衡二叉树

```java
    static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node (int value) {
            this.value = value;
        }
    }

    public static boolean isBalanced(Node head) {
        return process(head).isBalanced;
    }

    public static class ReturnType {
        public boolean isBalanced;
        public int height;

        public ReturnType(boolean isB, int hei){
            isBalanced = isB;
            height = hei;
        }
    }

    public static ReturnType process(Node x) {
        if (x == null) {
            return new ReturnType(true, 0);
        }
        ReturnType leftData = process(x.left);
        ReturnType rightData = process(x.right);
        int height = Math.max(leftData.height, rightData.height) + 1;
        boolean isBalanced = leftData.isBalanced && rightData.isBalanced && Math.abs(leftData.height - rightData.height) < 2;
        return new ReturnType(isBalanced, height);
    }
```



## 5 判断满二叉树

```java
    public static boolean isF(Node head) {
        if (head == null) {
            return true;
        }
        ReturnInfo info = processF(head);
        return info.nodes == ((1 << info.height) - 1);
    }
    static class ReturnInfo {
        private int height;
        private int nodes;

        public ReturnInfo(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }
    public static ReturnInfo processF(Node x) {
        if (x == null) {
            return new ReturnInfo(0, 0);
        }
        ReturnInfo leftData = processF(x.left);
        ReturnInfo rightData = processF(x.right);
        int height = Math.max(leftData.height, rightData.height) + 1;
        int nodes = leftData.nodes + rightData.nodes + 1;
        return new ReturnInfo(height, nodes);
    }
```



## 6 二叉树节点的最大公共祖先

给定两个二叉树节点node1和node2，找到它们的最低公共祖先节点。

```java
    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    // o1,o2一定属于head为头的树
    public static Node lca(Node head, Node o1, Node o2) {
        Map<Node, Node> fatherMap = new HashMap<>();
        fatherMap.put(head, head);
        process(head, fatherMap);
        Set<Node> set1 = new HashSet<>();
        Node cur = o1;
        while (cur != fatherMap.get(cur)) {
            set1.add(cur);
            cur = fatherMap.get(cur);
        }
        set1.add(head);
        cur = o2;
        while (cur != fatherMap.get(cur)) {
            for (Node temp : set1) {
                if (temp == cur) {
                    return cur;
                }
            }
            cur = fatherMap.get(cur);
        }
        return head;
    }

    public static void process(Node head, Map<Node, Node> fatherMap) {
        if (head == null) {
            return;
        }
        fatherMap.put(head.left, head);
        fatherMap.put(head.right, head);
        process(head.left, fatherMap);
        process(head.right, fatherMap);
    }
```

```java
    public static Node lowestAncestor(Node head, Node o1, Node o2) {
        if (head == null || head == o1 || head == o2) {
            return head;
        }
        Node left = lowestAncestor(head.left, o1, o2);
        Node right = lowestAncestor(head.right, o1, o2);
        if (left != null && right != null) {
            return head;
        }
        // 左右两颗树并不都有返回值
        return left != null ? left : right;
    }
```



## 7 二叉树中找到一个节点的后继节点

二叉树节点类型

```java
    static class Node {
        int value;
        Node left;
        Node right;
        Node parent;

        public Node(int val) {
            value = val;
        }
    }
```

```java
    public static Node getSuccessorNode(Node node) {
        if (node == null) {
            return node;
        }
        if (node.right != null) {
            return getLeftMost(node.right);
        } else { // 无右树
            Node parent = node.parent;
            while (parent != null && parent.left != node) { // 当前节点是其父亲节点右孩子
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }
    public static Node getLeftMost(Node node) {
        if (node == null) {
            return node;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
```



## 8 二叉树序列化和反序列化

```java
    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int val) {
            value = val;
        }
    }

    // 以head为头的树，请序列化成字符串返回
    public static String serialByPre(Node head) {
        if (head == null) {
            return "#_";
        }
        String res = head.value + "_";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }

    public static Node reconByPreString(String preStr) {
        String[] values = preStr.split("_");
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i != values.length; i++) {
            queue.add(values[i]);
        }
        return reconPreOrder(queue);
    }

    public static Node reconPreOrder(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals("#")) {
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.left = reconPreOrder(queue);
        head.right = reconPreOrder(queue);
        return head;
    }
```



# 图

```java
public class Graph {

    public HashMap<Integer, Node> nodes;
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }

}
public class Node {
    public int value;
    public int in;
    public int out;
    public ArrayList<Node> nexts;
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
public class Edge {
    public int weight;
    public Node from;
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}

```



## 宽度优先遍历

```java
    public static void bfs(Node node) {
        if (node == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }
```



## 广度优先遍历

```java
    public static void dfs(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.push(node);
        set.add(node);
        System.out.println(node.value);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }
```



## 拓补排序

```java
    public static List<Node> sort(Graph graph) {
        // key: 某一个node    value： 剩余的入度
        HashMap<Node, Integer> inMap = new HashMap<>();
        // 入度为0的点，才能进入这个队列
        Queue<Node> zeroInQueue = new LinkedList<>();
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }
        List<Node> result = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            Node cur = zeroInQueue.poll();
            result.add(cur);
            for (Node next : cur.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }
```



## kruskal

```java
    public static class MySets {
        public HashMap<Node, List<Node>> setMap;

        public MySets(List<Node> nodes) {
            setMap = new HashMap<>();
            for (Node cur : nodes) {
                List<Node> set = new ArrayList<>();
                set.add(cur);
                setMap.put(cur, set);
            }
        }

        public boolean isSameSet(Node from, Node to) {
            List<Node> fromSet = setMap.get(from);
            List<Node> toSet = setMap.get(to);
            return fromSet == toSet;
        }

        public List<Node> union(Node from, Node to) {
            List<Node> fromSet = setMap.get(from);
            List<Node> toSet = setMap.get(to);
            for (Node toNode : toSet) {
                fromSet.add(toNode);
                setMap.put(toNode, fromSet);
            }
            return fromSet;
        }
    }

    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> kruskal(Graph graph) {
        MySets mySets = new MySets(graph.nodes.values().stream().collect(Collectors.toList()));
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        for (Edge edge : graph.edges) {
            priorityQueue.add(edge);
        }
        Set<Edge> result = new HashSet<>();
        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            if (!mySets.isSameSet(edge.from, edge.to)) {
                result.add(edge);
                mySets.union(edge.from, edge.to);
            }
        }
        return result;
    }
```



## prim

```java
    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> prim(Graph graph) {
        // 依次解锁的边进入小根堆
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());

        HashSet<Node> set = new HashSet<>();

        Set<Edge> result = new HashSet<>();

        for (Node node : graph.nodes.values()) { // 随便挑一个点
            if (!set.contains(node)) { // node是开始点
                set.add(node);
                for (Edge edge : node.edges) { // 由一个点,解锁所有相连的边
                    priorityQueue.add(edge);
                }
                while (!priorityQueue.isEmpty()) {
                    Edge edge = priorityQueue.poll(); // 弹出解锁的边中，最小的边
                    Node toNode = edge.to; // 可能的一个新的点
                    if (!set.contains(toNode)) { // 不含有的时候就是新的点
                        set.add(toNode);
                        result.add(edge);
                        for (Edge nextEdge : toNode.edges) {
                            priorityQueue.add(nextEdge);
                        }
                    }
                }
            }
        }
        return result;
    }
```



# 贪心算法

## 1 项目最大收益

输入：

正数数组costs，正数数组profits，正数k，正数m。

含义：

costs[i] 表示 i 号项目的花费

profits[i] 表示 i 号项目在扣除花费之后的利润。

k 表示只能串行最多做k个项目。

m 表示初始资金。

输出：最后获得的最大钱数。

```java
    public static class Node {
        int c;
        int p;

        public Node (int c, int p) {
            this.c = c;
            this.p = p;
        }
    }

    public static class MinCostComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.c - o2.c;
        }
    }
    public static class MaxProfitComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o2.p - o1.p;
        }
    }

    public static int MaxMoney(int k, int m, int[] costs, int[] profits) {
        PriorityQueue<Node> minCostsQ = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Node> MaxProfitsQ = new PriorityQueue<>(new MaxProfitComparator());
        // 所有项目放入花费的小根堆
        for (int i = 0; i < costs.length; i++) {
            minCostsQ.add(new Node(costs[i], profits[i]));
        }
        for (int i = 0; i < k; i++) {
            while (!minCostsQ.isEmpty() && minCostsQ.peek().c <= m) {
                MaxProfitsQ.add(minCostsQ.poll());
            }
            if (MaxProfitsQ.isEmpty()) {
                return m;
            }
            m += MaxProfitsQ.poll().p;
        }
        return m;
    }
```



## 2 中位数

数据流数据，随时取中位数。

1. 把第一个数放入大根堆，取一个数cur <= 大根堆的值
2. 小于就把cur放入大根堆，否则放入小根堆。
3. size大的堆减去size小的堆 ==2时，就把size大的取一个放入size小的堆中。
4. 如果该数据流数量为偶数，大根堆小根堆各取一个值求平均值，为奇数就取size大的堆的第一个数。

```java
    public static int getMid(List<Integer> arr) {
        PriorityQueue<Integer> bigQ = new PriorityQueue<>(((o1, o2) -> o2 - o1));
        PriorityQueue<Integer> smallQ = new PriorityQueue<>(((o1, o2) -> o1 - o2));
        bigQ.add(arr.get(0));
        PriorityQueue<Integer> moreNumQ = bigQ;
        PriorityQueue<Integer> lessNumQ = smallQ;
        for (int i = 1; i < arr.size(); i++) {
            int cur = arr.get(i);
            if (cur <= bigQ.peek()) {
                bigQ.add(cur);
                moreNumQ = bigQ;
                lessNumQ = smallQ;
            } else {
                smallQ.add(cur);
                moreNumQ = smallQ;
                lessNumQ = bigQ;
            }
            if ((moreNumQ.size() - lessNumQ.size()) >= 2) {
                lessNumQ.add(moreNumQ.poll());
            }
        }
        if (arr.size() % 2 == 1) {
            return moreNumQ.peek();
        } else {
            return (bigQ.peek() + smallQ.peek()) / 2;
        }
    }
```



# 暴力递归

## 1 字符串子序列

```java
    public static List<String> f(String str) {
        char[] chars = str.toCharArray();
        List<String> list = new ArrayList<>();
        process(chars, 0, list, "");
        return list;
    }

    // 当前来到i位置，要和不要，走两条路
    // res 之前的选择所形成的的列表
    public static void process(char[] str, int i, List<String> res, String path) {
        if (i == str.length) {
            res.add(path);
            return;
        }
        String yes = path + String.valueOf(str[i]);
        process(str, i+1, res, yes); // 要当前字符的路
        String no = path;
        process(str, i+1, res, no);
    }
```



## 2 字符串全排列

```java
    public static List<String> permutation(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }
        process(s.toCharArray(), 0, res);
        return res;
    }

    // str[i...]范围上，所有的字符，都可以在i位置上，后续都去尝试
    // str[0...i-1]范围上，是之前做的选择
    public static void process(char[] str, int i, List<String> res) {
        if (i == str.length) {
            res.add(String.valueOf(str));
        }
        boolean visit[] = new boolean[26];
        for (int j = i; j < str.length; j++) {
            if (!visit[str[j] - 'a']) {
                visit[str[j] - 'a'] = true;
                swap(str, i, j);
                process(str, i+1, res);
                swap(str, i, j);
            }
        }
    }
    public static void swap(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }
```



## 3 逆序栈

```java
    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int i = f(stack);
        reverse(stack);
        stack.push(i);
    }

    public static int f(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = f(stack);
            stack.push(result);
            return last;
        }
    }
```



## 岛问题

一个矩阵中只有0和1两种值，每个位置都可以和自己的上、下、左、右四个位置相连，如果有一片1连在一起，这个部分叫做一个岛，求一个矩阵中有多少个岛？

【举例】

001010

111010

100100

000000

这个矩阵中有三个岛

```java
    public static int countIslands(int[][] m) {
        if (m == null || m[0] == null) {
            return 0;
        }
        int N = m.length; // 行
        int M = m[0].length; // 列
        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (m[i][j] == 1) {
                    res++;
                    infect(m, i, j, N, M);
                }
            }
        }
        return res;
    }

    private static void infect(int[][] m, int i, int j, int N, int M) {
        if (i < 0 || i >= N || j < 0 || j >= M || m[i][j] != 1) {
            return;
        }
        m[i][j] = 2;
        infect(m, i + 1, j, N, M);
        infect(m, i - 1, j, N, M);
        infect(m, i, j + 1, N, M);
        infect(m, i, j - 1, N, M);
    }
```

使用并行算法解决这个问题。



### 并查集

```java
    public static class Element<V> {
        public V v;

        public Element(V v) {
            this.v = v;
        }
    }

    public static class UnionFindSet<V> {
        public HashMap<V, Element<V>> elementMap;
        // key 某个元素  value 该元素的父
        public HashMap<Element<V>, Element<V>> fatherMap;
        // key 某个元素的代表元素  value 该集合的大小
        public HashMap<Element<V>, Integer> sizeMap;

        public UnionFindSet(List<V> list){
            elementMap = new HashMap<>();
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V value : list) {
                Element<V> element = new Element<>(value);
                elementMap.put(value, element);
                fatherMap.put(element, element);
                sizeMap.put(element, 1);
            }
        }

        public boolean isSameSet(V a, V b) {
            if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
                return findHead(elementMap.get(a)) == findHead(elementMap.get(b));
            }
            return false;
        }

        public void union(V a, V b) {
            if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
                Element<V> aF = findHead(elementMap.get(a));
                Element<V> bF = findHead(elementMap.get(b));
                if (aF != bF) {
                    Element<V> big = sizeMap.get(aF) >= sizeMap.get(bF) ? aF : bF;
                    Element<V> small = big == aF ? bF : aF;
                    fatherMap.put(small, big);
                    sizeMap.put(big, sizeMap.get(aF) + sizeMap.get(bF));
                    sizeMap.remove(small);
                }
            }
        }

        // 给一个元素，往上一直找，把代表元素返回
        private Element<V> findHead(Element<V> element) {
            Stack<Element<V>> path = new Stack<>();
            while (element != fatherMap.get(element)) {
                path.push(element);
                element = fatherMap.get(element);
            }
            while (!path.isEmpty()) {
                fatherMap.put(path.pop(), element);
            }
            return element;
        }
    }
```



# KMP

```java
    // s>=m
    public static int getIndexOf(String s, String m) {
        if (s == null || m == null || s.length() < 1 || s.length() < m.length()) {
            return -1;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = m.toCharArray();
        int i1 = 0;
        int i2 = 0;
        int next[] = getNextArray(str2);
        while (i1 < str1.length && i2 < str2.length) {
            if (str1[i1] == str2[i2]) {
                i1++;
                i2++;
            } else if (next[i2] == -1) { // i2 == 0  str2中比对的位置已经无法往前跳了
                i1++;
            } else {
                i2 = next[i2]; // str2 往前跳
            }
        }
        // i2 越界代表已经配出整个str2，不是因为i2越界退出，代表没有匹配到str2
        return i2 == str2.length ? i1 - i2 : -1;
    }

    private static int[] getNextArray(char[] ms) {
        if (ms.length == 1) {
            return new int[] {-1};
        }
        int next[] = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2; // next数组的位置
        int cn = 0; // 拿哪个位置的字符和i-1的字符比，也代表当前使用的信息。
        while (i < next.length) {
            if (ms[i - 1] == ms[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) { // 当前跳到cn位置的字符，和i-1位置的字符配不上
                cn = next[cn];
            } else { // cn不大于0，没法往前跳了
                next[i++] = 0;
            }
        }
        return next;
    }
```



# Manacher

解决字符串str中，求最长回文子串的长度

1. 回文半径和回文直径

   回文直径：以一个中心向左右两边扩的时候，扩出来的整个区域大小。

   回文半径：该区域一半的长度。

   -a**-1-2-1-**b- 

   以2为中心向左右两边扩，回文直径为7，回文半径为4。

2. 每一个位置扩的过程中，把半径记录在回文半径数组中。

3. 之前扩的所有位置中，所到达的最右回文右边界R，中心点c。

   -1-2-2-1-......						int R = -1,  int c = -1

   以0位置为中心向左右两边扩，范围 0 ~ 0，int R = 0,  int c = 0；

   以1位置为中心向左右两边扩，范围 0 ~ 2， int R = 2,  int c = 1；

   以2位置为中心向左右两边扩，范围 2 ~ 2，int R = 2, int c = 2;

   以3位置为中心向左右两边扩，范围 2 ~ 4，int R = 4, int c = 3;

   不管哪一个位置扩的，只要扩的范围的右边界超过了之前记录的值，R 就负责记录，只增不减。

   R 更新 C一定更新，R不更新C不更新。

情况1：当前 i 来到的位置没在回文右边界里，暴力扩

情况2：i 在回文右边界里，存在L，i‘，c，i，R

​		一、i’ 自己的回文区域在L到R内部，i 的回文半径和 i‘ 一样；

​		二、i’ 回文区域左边超过 L，i 的回文半径是 i 到 R；

​		三、i‘ 回文区域左边在 L 上，从R右边扩。

-1-2-1-......								int R = -1, int c = -1

中心点来到0位置，暴力扩，范围 0 ~ 0，半径 1，int R = 0；

1位置，暴力扩，int R = 2;

2位置，int R = 2

```java
    public static int maxLcpsLength(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = manacherString(s); // 1221 -> #1#2#2#1#
        int[] pArr = new int[str.length]; // 回文半径数组
        int C = -1; // 中心
        int R = -1; // 回文右边界再往右一个位置     最右的有效区是R-1位置
        int max = Integer.MIN_VALUE; // 扩出来的最大值
        for (int i = 0; i != str.length ; i++) { // 每一个位置都求回文半径
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]])
                    pArr[i]++;
                else
                    break;
            }
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            max = Math.max(max, pArr[i]);
        }
        return max - 1;
    }

    private static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }
```



# 滑动窗口

![](E:\IdeaProjects\Algorithm\pics\1.png)

窗口的左边界L和右边界R只能往右动，任何一个时刻选择让L或R移动，左边界不超过右边界。

使用双端队列获取任意窗口下的最大值，保证双端队列由头到尾数字由大到小。

R向右移动时，新进入的数如果比队尾的数字小就进入队列，如果比队尾的数字大，将队尾的数字出队，直到新进入的数字小于队尾数字或队列为空时，将新数字加入队列。

L向右移动时，看队列中头部元素是不是等于过期元素(L右移后前一个元素)，等于就将头部元素出队。

双端队列维持的是在依次过期情况下，成为最大值的可能性。



# 单调栈

一个数组中，求每一个位置的数，左边离它最近的比它大的和右边离它最近的比它大的。

int [] = {5, 4, 3, 6, 1, 2, 0}，栈底到栈顶由大到小。



# Morris遍历

一种遍历二叉树的方式，并且时间复杂度O(N)，额外空间复杂度O(1)。通过利用原树中大量空闲指针的方式，达到节省空间的目的。

![](E:\IdeaProjects\Algorithm\pics\Morris.png)

特点：如果一个节点有左树，一定能回到它两次，
