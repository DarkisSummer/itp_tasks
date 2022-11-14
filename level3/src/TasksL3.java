import java.util.Arrays;

public class TasksL3 {
    public static void main(String[] args) {
        System.out.println(solutions(1,0,-1)); // 2
        System.out.println(solutions(1,0,0));  // 1
        System.out.println(solutions(1,0,1));  // 0
        System.out.println();
        System.out.println(findZip("all zip files are zipped"));
        System.out.println(findZip("all zip files are compressed"));
        System.out.println();
        System.out.println(checkPerfect(6));   // true
        System.out.println(checkPerfect(28));  // true
        System.out.println(checkPerfect(496)); // true
        System.out.println(checkPerfect(12));  // false
        System.out.println();
        System.out.println(flipEndChars("Cat, dog, and mouse.")); // .at, dog, and mouseC
        System.out.println(flipEndChars("ada"));                  // Two's a pair
        System.out.println(flipEndChars("z"));                    // Incompatible
        System.out.println();
        System.out.println(isValidHexCode("#CD5C5C"));  // true
        System.out.println(isValidHexCode("#EAECEE"));  // true
        System.out.println(isValidHexCode("#eaecee"));  // true
        System.out.println(isValidHexCode("#CD5C58C")); // false
        System.out.println(isValidHexCode("#CD5C5Z"));  // false
        System.out.println(isValidHexCode("#CD5C&C"));  // false
        System.out.println(isValidHexCode("CD5C5C"));   // false
        System.out.println();
        System.out.println(same(new int[] {1,3,4,4,4}, new int[] {2,5,7})); // true
        System.out.println(same(new int[] {9,8,7,6}, new int[] {4,4,3,1})); // false
        System.out.println(same(new int[] {2}, new int[] {3,3,3,3,3}));     // true
        System.out.println();
        System.out.println(isKaprekar(3));   // false
        System.out.println(isKaprekar(5));   // false
        System.out.println(isKaprekar(297)); // true
        System.out.println();
        System.out.println(longestZero("01100001011000")); // "0000"
        System.out.println(longestZero("100100100"));      // "00"
        System.out.println(longestZero("11111"));          // ""
        System.out.println();

        System.out.println(nextPrime(12));
        System.out.println(nextPrime(24));
        System.out.println();

        System.out.println(rightTriangle(3, 4, 5));       // true
        System.out.println(rightTriangle(145, 105, 100)); // true
        System.out.println(rightTriangle(70, 130, 110));  // false
    }
    public static int solutions(int a, int b, int c) {
        int D = b * b - 4 * a * c;
        if(D < 0)
            return 0;
        else if(D == 0)
            return 1;
        else
            return 2;
    }

    public static int findZip(String str) {
            return str.indexOf("zip", str.indexOf("zip") + 1);
    }

    public static boolean checkPerfect(int num) {
        int sum = 1;
        for(int i = 2; i <= num/2; i++) {
            if(num % i == 0)
                sum += i;
        }
        return sum == num;
    }

    public static String flipEndChars(String str) {
        char verb = str.charAt(0);
        if(str.length() < 2)
            return "Incompatible";
        else if(str.charAt(0) == str.charAt(str.length()-1))
            return "Two's a pair";
        else
            str = str.replace(str.charAt(0), str.charAt(str.length()-1));
            str = str.substring(0, str.length()-1) + verb;
            return str;
    }

    public static boolean isValidHexCode(String code) {
        return code.matches("#[0-9A-Fa-f]{6}");
    }

    public static boolean same(int[] arr1, int[] arr2) {
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        int n1 = 0;
        int n2 = 0;
        for(int i = 1; i < arr1.length; i++) {
            if(!(arr1[i] == arr1[i-1]))
                n1 += 1;
        }
        for(int i = 1; i < arr2.length; i++) {
            if(!(arr2[i] == arr2[i-1]))
                n2 += 1;
        }
        return n1 == n2;
    }

    public static boolean isKaprekar(int n) {
        String sqr = Integer.toString(n * n);
        if(sqr.length() == 1) {
            return Integer.parseInt(sqr) == n;
        }
        int left = Integer.parseInt(sqr.substring(0, sqr.length() / 2));
        int right = Integer.parseInt(sqr.substring(sqr.length()/2));
        return left + right == n;
    }

    public static String longestZero(String str) {
        String max = "";
        String count = "";
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '0'){
                count += "0";
            }
            else{
                if(max.length() < count.length()){
                    max = count;
                }
                count = "";
            }
        }
        return max;
    }

    public static int nextPrime(int number) {
        int n = number;
        while(true) {
            if(n % 2 == 0){
                n += 1;
                continue;
            }
            boolean check = true;
            for(int i = 3; i <= n/2; i++) {
                if(n % i == 0) {
                    check = false;
                    break;
                }
            }
            if(check){return n;}
            n += 1;
        }
    }

    public static boolean rightTriangle(int x, int y, int z) {
        if(x > y && x > z)
            return x*x == y*y + z*z;
        else if(y > x && y > z)
            return y*y == x*x + z*z;
        else
            return z*z == x*x + y*y;
    }
}