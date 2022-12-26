import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

import org.apache.commons.codec.digest.DigestUtils;


public class TasksL5 {
    public static void main(String[] args) {
        System.out.println(encrypt("Hello"));                                           // [72, 29, 7, 0, 3]
        System.out.println(decrypt(new int[] {72, 33, -73, 84, -12, -3, 13, -13, -68}));    // "Hi there!"
        System.out.println(encrypt("Sunshine"));                                        // [83, 34, -7, 5, -11, 1, 5, -9]
        System.out.println();
        System.out.println(canMove("Rook", "A8", "H8"));   // true
        System.out.println(canMove("Bishop", "A7", "G1")); // true
        System.out.println(canMove("Queen", "C4", "D6"));  // false
        System.out.println();
        System.out.println(canComplete("butl", "beautiful"));  // true
        System.out.println(canComplete("butlz", "beautiful")); // false
        System.out.println(canComplete("tulb", "beautiful"));  // false
        System.out.println();
        System.out.println(sumDigProd(new int[] {16, 28})); // 6
        System.out.println(sumDigProd(new int[] {0}));      // 0
        System.out.println(sumDigProd(new int[] {1, 2, 3, 4, 5, 6}));
        System.out.println();
        System.out.println(sameVowelGroup(new String[] {"toe", "ocelot", "maniac"}));                       // [toe, ocelot]
        System.out.println(sameVowelGroup(new String[] {"many", "carriage", "emit", "apricot", "animal"})); // [many]
        System.out.println();
        System.out.println(validateCard("1234567890123456")); // false
        System.out.println(validateCard("1234567890123452")); // true
        System.out.println();
        System.out.println(numToEng(0));   // zero
        System.out.println(numToEng(18));  // eighteen
        System.out.println(numToEng(126)); // one hundred twenty six
        System.out.println(numToEng(909)); // nine hundred nine
        System.out.println();
        System.out.println(getSha256Hash("password123")); // ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f
        System.out.println();
        System.out.println(correctTitle("jOn SnoW, kINg IN thE noRth."));         // Jon Snow, King in the North.
        System.out.println(correctTitle("sansa stark, lady of winterfell."));     // Sansa Stark, Lady of Winterfell.
        System.out.println(correctTitle("TYRION LANNISTER, HAND OF THE QUEEN.")); // Tyrion Lannister, Hand of the Queen.
        System.out.println();
        System.out.println(hexLattice(1));  // "o"
        System.out.println(hexLattice(7));  // "o o \n o o o \n o o"
        System.out.println(hexLattice(19));
    }

    public static String encrypt(String str) {
        int[] res = new int[str.length()];
        char[] arr = str.toCharArray();
        res[0] = arr[0];
        for(int i = 1; i < str.length(); i++) {
            res[i] = arr[i] - arr[i - 1];
        }
        return Arrays.toString(res);
    }

    public static String decrypt(int[] arr) {
        StringBuilder str = new StringBuilder();
        str.append((char) arr[0]);
        char curr;
        for(int i = 1; i < arr.length; i++) {
            curr = (char) (arr[i] += arr[i-1]);
            str.append(curr);
        }
        return str.toString();
    }

    public static boolean canMove(String piece, String fromPos, String toPos) {
        String verbs = "ABCDEFGH";
        int dX = Math.abs(verbs.indexOf(toPos.charAt(0)) - verbs.indexOf(fromPos.charAt(0)));
        int dY = Math.abs(Integer.parseInt(toPos.substring(1)) - Integer.parseInt(fromPos.substring(1)));
        switch (PIECES.valueOf(piece)) {
            case Pawn:
                return dX == 0 && dY == 1;
            case Knight:
                return (dX == 1 && dY == 2) || (dX == 2 && dY == 1);
            case Bishop:
                return dX == dY;
            case Rook:
                return dX == 0 || dY == 0;
            case Queen:
                return dX == 0 || dY == 0 || dX == dY;
            case King:
                return dX + dY == 1 || (dX == 1 &&  dY == 1);
        }
        return true;
    }

    public static boolean canComplete(String uncompleted, String word) {
        int uncIndex = 0;
        for(int i = 0; i < word.length() && uncIndex < uncompleted.length(); i++)
            if(uncompleted.charAt(uncIndex) == word.charAt(i))
                uncIndex += 1;
        return uncIndex == uncompleted.length();
    }

    public static int sumDigProd(int[] arr) {
        int number = IntStream.of(arr).sum();
        int newNumber = 1;
        StringBuilder strNumber = new StringBuilder(Integer.toString(number));
        while(strNumber.length() > 1) {
            for(int i = 0; i < strNumber.length(); i++) {
                newNumber *= Character.getNumericValue(strNumber.charAt(i));
            }
            strNumber.setLength(0);
            strNumber.append(newNumber);
            newNumber = 1;
        }
        return Integer.parseInt(strNumber.toString());
    }

    public static ArrayList<String> sameVowelGroup(String[] arr) {
        ArrayList<String> res = new ArrayList<>();
        ArrayList<Character> vowels = new ArrayList<>();
        Collections.addAll(vowels, 'a', 'e', 'i', 'o', 'u', 'y');
        ArrayList<Character> firstWordVowels = new ArrayList<>();
        ArrayList<Character> currentWordVowels = new ArrayList<>();
        for(int i = 0; i < arr[0].length(); i++) {
            if(vowels.contains(arr[0].charAt(i)) && !firstWordVowels.contains(arr[0].charAt(i)))
                firstWordVowels.add(arr[0].charAt(i));
        }
        res.add(arr[0]);
        String word;
        for(int i = 1; i < arr.length; i++) {
            word = arr[i];
            for(int j = 0; j < word.length(); j++) {
                if(vowels.contains(word.charAt(j)) && !currentWordVowels.contains(word.charAt(j)))
                    currentWordVowels.add(word.charAt(j));
            }
            if(currentWordVowels.equals(firstWordVowels))
                res.add(word);
            currentWordVowels.clear();
        }
        return res;
    }

    public static boolean validateCard(String number) {
        if(number.length() < 14 || number.length() > 19)
            return false;
        int lastIndex = Character.getNumericValue(number.charAt(number.length()-1));
        int sum = 0, x;
        for(int i = number.length()-2; i >= 0; i--) {
            x = Character.getNumericValue(number.charAt(i));
            if(i % 2 == 0) {
                x *= 2;
                if(x > 9)
                    x = 1 + x % 10;
            }
            sum += x;
        }
        return 10 - sum % 10 == lastIndex;
    }

    public static String numToEng(int number) {
        if(number == 0) {
            return "zero";
        }
        String digitArr[] = new String[] { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};
        String teens[] = new String[] {"eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        String dozenArr[] = new String[] { "twenty ", "thirty ", "fourty ", "fifty ", "sixty ", "seventy ", "eighty ",
                "ninety " };
        StringBuilder res = new StringBuilder();
        int hundreds = number / 100;
        int dozens = number - hundreds * 100;
        int digit = number % 10;
        if(hundreds != 0) {
            res.append(digitArr[hundreds - 1]);
            res.append(" hundred ");
        }
        if(dozens < 20) {
            if (number % 100 < 10)
                res.append(digitArr[digit - 1]);
            else
                res.append(teens[dozens % 10 - 1]);
        }
        else {
            res.append(dozenArr[dozens / 10 - 2]);
            if(digit != 0)
                res.append(digitArr[digit - 1]);
        }
        return res.toString();
    }

    public static String getSha256Hash(String str) {
        return DigestUtils.sha256Hex(str);
    }

    public static String correctTitle(String text) {
        String lowerText = text.toLowerCase();
        StringBuilder res = new StringBuilder();
        for(String str : lowerText.split(" ")) {
            if(str.equals("in") || str.equals("the") || str.equals("of"))
                res.append(str);
            else {
                res.append(Character.toUpperCase(str.charAt(0)));
                res.append(str.substring(1));
            }
            res.append(" ");
        }
        return res.toString();
    }

    public static String hexLattice(int number) {
        if(number == 1)
            return "o";
        if(number % 6 != 1)
            return "Invalid";
        boolean flag = false;
        int a = 1, startNum = 0, midNum = 0;
        for(int i = 1; a < number; i++) {
            a += i * 6;
            if(a == number) {
                flag = true;
                startNum = i + 1;
                midNum = 2 * i + 1;
                break;
            }
        }
        if(!flag)
            return "Invalid";
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < startNum; i++) {
            for(int j = 0; j < midNum / 2 - i; j++)
                res.append(" ");
            for(int j = 0; j < startNum + i; j++)
                res.append("o ");
            res.append("\n");
        }
        for(int i = startNum - 1; i > 0; i--) {
            for(int j = 0; j <= midNum / 2 - i; j++)
                res.append(" ");
            for(int j = startNum + i - 1; j > 0; j--) {
                res.append("o ");
            }
            res.append("\n");
        }
        return res.toString();
    }
}


enum PIECES {
    Pawn, Knight, Bishop, Rook, Queen, King
}