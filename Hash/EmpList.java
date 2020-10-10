package Hash;

public class EmpList<E> {
    private Emp head;
    private int size;

    public EmpList() {
        this.size = 0;
        this.head = null;
    }

    public void add(Emp emp) {
        if (isEmpty()) {
            this.head = emp;
            size++;
            return;
        }
        Emp cursor = this.head;
        while (null != cursor.next) {
            cursor = cursor.next;
        }
        cursor.next = emp;
        size++;

    }

    public boolean isEmpty() {
        return null == head;
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("the list is already empty!");
            return;
        }
        Emp cursor = this.head;
        while (null != cursor) {
            System.out.print(cursor.toString()+"\t");
            cursor = cursor.next;
        }
        System.out.println("");
    }

}
