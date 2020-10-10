package Hash;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HashMap {
    private EmpList[] arrEmpList;
    private int hashCap;

    public HashMap(int hashCap) {
        this.hashCap = hashCap;
        this.arrEmpList = new EmpList[this.hashCap];
        for (int i = 0; i < this.hashCap; i++) {
            arrEmpList[i] = new EmpList<Emp>();
        }
    }

    public int getHashCap() {
        return hashCap;
    }

    public void setHashCap(int hashCap) {
        this.hashCap = hashCap;
    }

    public static void main(String[] args) {
        HashMap map = new HashMap(8);
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        char key = ' ';
        while(flag){
            System.out.println("a-add,{no,name};");
            System.out.println("l-list;");
            System.out.println("e-exit;");
            key = scanner.next().charAt(0);
            switch(key){
                case 'a':
                    System.out.println("please input number:");
                    int no = scanner.nextInt();
                    System.out.println("please input name:");
                    String name = scanner.next();
                    try {
                        Emp emp = new Emp(no, name);
                        map.add(emp);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    } finally{
                        break;
                    }
                case 'l':
                    try {
                        map.list();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    } finally{
                        break;
                    }
                case 'e':
                    flag = false;
                    scanner.close();
                    break;
                default:
                    System.out.println("wrong input! plese try again!");
                    break;
            }
        }
    }

    private int hash(Emp emp) {
        int hash = emp.hashCode();
        return hash % this.hashCap;

    }

    public void add(Emp emp){
        int hash = hash(emp);
        for(int i=0; i<this.hashCap;i++){
            if(hash == i){
                arrEmpList[i].add(emp);
            }
        }
    }

    public void list(){
        for(int i=0; i<this.hashCap;i++){
            System.out.printf("the %sth list shows: \t", i);
            arrEmpList[i].list();
        }
    }
}
