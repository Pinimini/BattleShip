import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] masString = reader.readLine().split(" ");
        ArrayList<String> mas = new ArrayList<>(Arrays.asList(masString));
        for (int i = 0; i < mas.size(); i++) {
            if (mas.get(i).equals("")) {
                mas.remove(i);
                i--;
            }
        }
        System.out.println(mas.size());
        reader.close();
    }
}
