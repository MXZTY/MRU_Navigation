package com.example.mtyso.mru_navigation;

public class Bucket {
    private Node head;
    private int size = 0;

    public boolean add(LocationInstance data) {
        if (this.head == null) {
            Node head = new Node(data, null);
            this.size++;
            this.head = head;
            return true;
        } else if(this.head.data.getName().compareTo(data.getName()) == 0) {
            return false;
        } else if (this.head.data.getName().compareTo(data.getName()) > 0) {
            Node newHead = new Node(data, this.head);
            this.head = newHead;
            this.size ++;
            return true;
        } else {
            boolean added = add(data, head.next, head);
            return added;
        }
    }

    public boolean add(LocationInstance data, Node current, Node prev) {
        if (current != null){
            if (data.getName().compareTo(current.data.getName()) == 0) {
                return false;
            } else if (data.getName().compareTo(current.data.getName()) < 0) {
                Node newNode = new Node(data, current);
                prev.next = newNode;
                this.size ++;
                return true;
            } else {
                boolean added = add(data, current.next, current);
                return added;
            }
        } else {
            Node newNode = new Node(data, null);
            prev.next = newNode;
            this.size ++;
            return true;
        }
    }

    public boolean remove(LocationInstance data) {
        if (this.head == null) {
            return false;
        } else if (this.head.data.getName().compareTo(data.getName()) == 0) {
            this.head = this.head.next;
            this.size--;
            return true;
        } else {
            boolean removed = remove(data, head.next, head);
            return removed;
        }
    }
    public boolean remove(LocationInstance data, Node current, Node prev) {
        if (current != null) {
            if (current.data.getName().compareTo(data.getName()) == 0) {
                if (current.next != null){
                    prev.next = current.next;
                    this.size--;
                    return true;
                } else {
                    prev.next = null;
                    this.size--;
                    return true;
                }
            } else if (current.data.getName().compareTo(data.getName()) > 0) {
                return false;
            } else {
                boolean removed = remove(data, current.next, current);
                return removed;
            }
        } else {
            return false;
        }
    }

    public boolean find(String userInput) {
        boolean found = false;
        if (head == null){
            return found;
        } else {
            found = find(head, userInput);
        }
        return found;
    }

    public boolean find(Node current, String userInput) {
        if (current == null) {
            return false;
        } else if (current.data.getName().compareTo(userInput) == 0) {
            return true;
        } else if (current.data.getName().compareTo(userInput) > 0) {
            return false;
            //will not be in list as it is greater than currents data.
        } else {
            boolean found = find(current.next, userInput);
            return found;
        }
    }

    public LocationInstance getLocationData(String userInput) {
        LocationInstance found = null;
        if (head == null){
            return found;
        } else {
            found = getLocationData(head, userInput);
        }
        return found;
    }

    public LocationInstance getLocationData(Node current, String userInput) {
        if (current == null) {
            return null;
        } else if (current.data.getName().compareTo(userInput) == 0) {
            return current.data;
        } else if (current.data.getName().compareTo(userInput) > 0) {
            return null;
            //will not be in list as it is greater than currents data.
        } else {
            LocationInstance found = getLocationData(current.next, userInput);
            return found;
        }
    }


    public void printIt(){
        Node node = this.head;
        while(node != null) {
            System.out.println(node.data + " ");
            node = node.next;
        }
    }






    private class Node {
        private LocationInstance data;
        private Node next;
        public Node() {}
        public Node(LocationInstance data, Node next) {
            this.data = data;
            this.next = next;
        }
    }






    public int getsize() {

        return this.size;
    }

}
