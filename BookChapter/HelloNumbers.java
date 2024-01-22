public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
	int i = x;
        while (x < 10) {
            System.out.print(i + " ");
            x = x + 1;
	    i += x;
        }
    }
}
