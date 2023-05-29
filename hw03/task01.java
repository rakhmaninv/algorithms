package hw03;


public class task01 {

    public static void main(String[] args) {
        MyList lst = new MyList();
        
        int res;
        for (int i = 0; i < 10; i++) {
            res = (int) (Math.random() * 100);
            lst.add(res);
        }
        lst.print();
        lst.revert();
        System.out.println();
        lst.print();
    }
}
        