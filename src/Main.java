import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        int count = 4;
        int res = 1;
        for(int i = 1; i <= count; i++) {
            res = res * i;
        }
        System.out.println(res);
        Scanner in = new Scanner(System.in);
        System.out.print("Введите имя: ");
        String name = in.nextLine();
        System.out.print("Приветствую " + name);
        System.out.println();


        System.out.println("градусов: " + "0" + "  30 " + "     45     " + "    60    " + " 90 " + " 180" + " 270 " + " 360");
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.println("радиан:  " + " 0 " + " p/6" + "     p/4    " + "    p/3   " + " p/2 " + " p " + " 3p/2" + "  2p ");
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.println("sin(a):  " + " 0 " + " 1/2" + "  sqrt(2)/2 " + " sqrt(3)/2" + "  1  " + " 0 " + " -1  " + "  0  ");

    }
}