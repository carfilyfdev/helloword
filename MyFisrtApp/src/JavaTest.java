public class JavaTest {

    static int m = 1024 * 1024;

    public static void main(String[] args) {
        //����2��
        byte[] a1 = new byte[2 * m];
        System.out.println("a1 ok");
        //����2��
        byte[] a2 = new byte[2 * m];
        System.out.println("a2 ok");
    }
}