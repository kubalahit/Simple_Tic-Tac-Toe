import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                int i = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Not a number");
                i = 0
            }
            break;
        }
    }
}