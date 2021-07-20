package com.gzy.code.linkedUnion;

public class LinkedUnionFindSet {

    private Node nodes[];

    public LinkedUnionFindSet(int n){
        nodes = new Node[n];
        for (int i = 0; i < n; i ++){
            nodes[i] = new Node();
        }
    }

    public void union(int i, int j){
        boolean inSameSet = find(i, j);
        if (inSameSet){
            return;
        }

        Node li = nodes[i].R;
        Node lj = nodes[j].R;
        Node liTail = li.prev;
        Node ljTail = lj.prev;
        li.prev = ljTail;
        ljTail.next = li;
        lj.prev = liTail;
        liTail.next = lj;

        Node p = lj;

        while (p != ljTail){
            p.R = li.R;
            p = p.next;
        }

        ljTail.R = li.R;
    }

    public boolean find(int i, int j){
        return nodes[i].R == nodes[j].R;
    }

    public class Node {
        public Node prev = this;
        public Node next = this;
        public Node R = this;
    }


    public static void main(String[] args) {
        LinkedUnionFindSet linkedUnionFindSet = new LinkedUnionFindSet(7);
        System.out.println(linkedUnionFindSet.nodes[0].R);
        System.out.println(linkedUnionFindSet.nodes[1].R);
        System.out.println(linkedUnionFindSet.nodes[2].R);
        linkedUnionFindSet.union(0, 1);
        linkedUnionFindSet.union(1, 2);
        linkedUnionFindSet.union(2, 3);

//        linkedUnionFindSet.union(3, 4);
        linkedUnionFindSet.union(4, 5);
        linkedUnionFindSet.union(5, 6);
//        linkedUnionFindSet.union(6, 7);
        System.out.println(linkedUnionFindSet.nodes[0].R);
        System.out.println(linkedUnionFindSet.nodes[3].R);

        System.out.println(linkedUnionFindSet.nodes[4].R);
        System.out.println(linkedUnionFindSet.nodes[6].R);

        linkedUnionFindSet.union(1, 5);

        System.out.println(linkedUnionFindSet.nodes[1].R);
        System.out.println(linkedUnionFindSet.nodes[5].R);
    }
}
