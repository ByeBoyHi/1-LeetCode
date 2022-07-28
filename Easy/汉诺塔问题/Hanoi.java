package Easy.汉诺塔问题;

import java.util.List;

public class Hanoi {

    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        if (A.size() == 1) {
            C.add(A.remove(0));
            return;
        }
        f(A.size(), A, B, C);
    }

    public void f(int n, List<Integer> A, List<Integer> B, List<Integer> C) {
        if (n == 1) {
            C.add(A.remove(A.size() - 1));
            return;
        }
        f(n - 1, A, C, B);
        C.add(A.remove(A.size() - 1));
        f(n - 1, B, A, C);
    }

    public void process(int n, char A, char B, char C) {
        if (n == 1) {
            System.out.println(A + "-" + C);
            return;
        }
        process(n - 1, A, C, B);
        System.out.println(A + "-" + C);
        process(n - 1, B, A, C);
    }
}
