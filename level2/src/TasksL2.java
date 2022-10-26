import java.util.Arrays;

public class TasksL2 {
    public static void main(String[] args) {
        System.out.println(repeat("mice", 5));  // mmmmmiiiiiccccceeeee
        System.out.println(repeat("hello", 3)); // hhheeellllllooo
        System.out.println(repeat("stop", 1));  // stop
        System.out.println();
        System.out.println(differenceMaxMin(new int[] {10,4,1,4,-10,-50,32,21})); // 82
        System.out.println(differenceMaxMin(new int[] {44,32,86,19}));            // 67
        System.out.println();
        System.out.println(isAvgWhole(new int[] {1,3}));     // true
        System.out.println(isAvgWhole(new int[] {1,2,3,4})); // false
        System.out.println(isAvgWhole(new int[] {9,2,2,5})); // false
        System.out.println();
        System.out.println(Arrays.toString(cumulativeSum(new int[] {1,2,3})));          // [1, 3, 6]
        System.out.println(Arrays.toString(cumulativeSum(new int[] {3,3,-2,408,3,3}))); // [3, 6, 4, 412, 415, 418]
        System.out.println();
        System.out.println(getDecimalPlaces("43.20")); // 2
        System.out.println(getDecimalPlaces("400"));   // 0
        System.out.println();
        System.out.println(Fibonacci(3)); // 3
        System.out.println(Fibonacci(7)); // 21
        System.out.println();
        System.out.println(isValid("59001"));  // true
        System.out.println(isValid("853a7"));  // false
        System.out.println(isValid("732 32")); // false
        System.out.println();
        System.out.println(isStrangePair("sparkling", "groups")); // true
        System.out.println(isStrangePair("bush", "hubris"));      // false
        System.out.println(isStrangePair("", ""));                // true
        System.out.println();
        System.out.println(isPrefix("automation","auto-"));      // true
        System.out.println(isPrefix("retrospect", "sub-"));      // false
        System.out.println(isSuffix("arachnophobia","-phobia")); // true
        System.out.println(isSuffix("vocation","-logy"));        // false
        System.out.println();
        System.out.println(boxSeq(0)); // 0
        System.out.println(boxSeq(1)); // 3
        System.out.println(boxSeq(2)); // 2
    }

    public static String repeat(String word, int n) {
        String res = "";
        for(int i = 0; i < word.length(); i++) {
            for(int k = 0; k < n; k++) {
                res += word.charAt(i);
            }
        }
        return res;
    }

    public static int differenceMaxMin(int[] numbers) {
        Arrays.sort(numbers);
        return(numbers[numbers.length-1] - numbers[0]);
    }

    public static boolean isAvgWhole(int[] numbers) {
        int sum = 0;
        for(int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }
        return sum % numbers.length == 0;
    }

    public static int[] cumulativeSum(int[] numbers) {
        int sum = 0;
        for(int i = 0; i < numbers.length; i++) {
            numbers[i] += sum;
            sum = numbers[i];
        }
        return numbers;
    }

    public static int getDecimalPlaces(String num) {
        if(num.contains(".")) {
            String[] arr = num.split("\\.");
            return arr[1].length();
        }
        return 0;
    }

    public static int Fibonacci(int n) {
        if(n == 0 || n == 1) {
            return 1;
        }
        else return Fibonacci(n-2) + Fibonacci(n-1);
    }

    public static boolean isValid(String postcode) {
        return postcode.matches("[0-9]+") && postcode.length() == 5;
    }

    public static boolean isStrangePair(String str1, String str2) {
        if(str1.length() == 0 && str2.length() == 0) {
            return true;
        }
        return str1.charAt(0) == str2.charAt(str2.length() - 1) && str1.charAt(str1.length() - 1) == str2.charAt(0);
    }

    public static boolean isPrefix(String word, String prefix) {
        prefix = prefix.replace("-","");
        return word.startsWith(prefix);
    }
    public static boolean isSuffix(String word, String suffix) {
        suffix = suffix.replace("-","");
        return word.endsWith(suffix);
    }

    public static int boxSeq(int n) {
        if(n % 2 == 0)
            return n;
        else
            return n+2;
    }
}