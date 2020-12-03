import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] mas = reader.readLine().split("");
        for (int i = 0; i < mas.length; i++) {
            System.out.print(mas[mas.length - 1 - i]);
        }


    }
}