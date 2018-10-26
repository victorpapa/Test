public class MainClass {

    private static void oldness(Integer myInt)
    {
        boolean b = myInt > 0;
        if (!b) {
            System.out.println("Input must be positive");
        }
        else {
            System.out.print("Age in days = " + (int)(myInt * 365.25));
        }
    }
    public static void main(String args[]) {

        try {
            Integer i = Integer.parseInt(args[0]);
            oldness(i);
        }
        catch (ArrayIndexOutOfBoundsException | NumberFormatException e){
            e.printStackTrace();
        }
    }
}