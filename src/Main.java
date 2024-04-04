import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class WektoryRoznejDlugosciException extends Exception {
    private int length1, length2;

    public WektoryRoznejDlugosciException(int l1, int l2) {
        super("Błędne długości wektorów: len(w1) == " + l1 + ", len(w2) == " + l2);
        this.length1 = l1;
        this.length2 = l2;
    }

    public int getLength1() {
        return length1;
    }

    public int getLength2() {
        return length2;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            List<Integer> first = new ArrayList<>();
            List<Integer> second = new ArrayList<>();

            System.out.println("Podaj v1: ");
            first = read(sc);

            System.out.println("Podaj v2: ");
            second = read(sc);

            try {
                if (first.size() != second.size()) {
                    throw new WektoryRoznejDlugosciException(first.size(), second.size());
                }
                List<Integer> res = addv(first, second);
                write_file(res);
                System.out.println("File saved!");
                break;
            } catch (WektoryRoznejDlugosciException e) {
                System.out.println(e.getMessage());
                System.out.println("Wprowadź ponownie wektory.");
            }
        }
    }

    private static List<Integer> read(Scanner sc) {
        List<Integer> vec = new ArrayList<>();
        String line = sc.nextLine();
        String[] inputs = line.split("\\s+");
        for (String input : inputs) {
            try {
                int num = Integer.parseInt(input);
                vec.add(num);
            } catch (NumberFormatException e) {
                System.out.printf("Nieprawidłowy format! Spróbuj ponownie.\n");
                return new ArrayList<>();
            }
        }
        return vec;
    }

    private static List<Integer> addv(List<Integer> first, List<Integer> second) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < first.size(); i++) {
            res.add(first.get(i) + second.get(i));
        }
        return res;
    }

    private static void write_file(List<Integer> vec) {
        try (BufferedWriter wr = new BufferedWriter(new FileWriter("result.txt"))) {
            for (int num : vec) {
                wr.write(num + " ");
            }
        } catch (IOException e) {
            System.out.printf(e.getMessage());
        }
    }
}
