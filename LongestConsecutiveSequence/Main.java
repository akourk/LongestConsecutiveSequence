// Alex Kourkoumelis
// 5/30/2019
// Finds the longest consecutive sequence of integers in an array list

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {

        int[] arr = {3, -5, -8, -2, 5, 8, 7, 0, 1, 2, 0, -1};
//        int[] arr = {3, -5, -8, -2, 5, 8, 7, 0, 1, 2, 0, -1, -99, 100, 10000, 4, 6, -3, 9};
//        int[] arr = {};
//        int[] arr = {5};
//        int[] arr = {0, 0, 0, 0, 0, 0, 0};
//        int[] arr = {5, 6, 7};

        int[] seqArr = findLongestSeq(arr);
        for (int num : seqArr) {
            System.out.print(num + " ");
        }
    }

    private static int[] findLongestSeq(int[] input) {

        // a couple edge cases
        if (input.length == 0) {
            return new int[]{-1};
        }
        if (input.length == 1) {
            return input;
        }

        // adding everything to the HashSet
        HashSet set = new HashSet<>();
        for (int i = 0; i < input.length; i++) {
            set.add(input[i]);
        }

        // best vars
        int bestLength = 1;
        int bestStart = 0;
        // temp vars
        int tempValue;
        int tempStart;
        int tempLength = 1;

        // my silly shortcut.
        // Would be more useful with very large arrays with very long sequences
        for (int i = 0; i < input.length && input.length - i > bestLength; i++) {
            // stops iteration if the longest sequence found is already longer than the remainder of the array

            tempStart = input[i];

            tempValue = input[i] + 1;
            while (set.contains(tempValue)) {
                tempValue++;
                tempLength++;
            }

            tempValue = input[i] - 1;
            while (set.contains(tempValue)) {
                tempValue--;
                tempLength++;
                tempStart--;
            }

            if (tempLength > bestLength) {
                bestLength = tempLength;
                bestStart = tempStart;
            }
            tempLength = 1;
        }

        // populates return array
        int[] longestSeq = new int[bestLength];
        for (int i = 0; i < bestLength; i++) {
            longestSeq[i] = bestStart + i;
        }

        return longestSeq;
    }
}

// this also works and might be cleaner and easier to read

//    private static int[] findLongestSeq(int[] input) {
//        HashSet set = new HashSet<>();
//        for(int i = 0; i < input.length; i++) {
//            set.add(input[i]);
//        }
//
//        int bestLength = 1;
//        int tempLength = 1;
//        int bestStart = 0;
//        int tempDown;
//
//        for (int num:input) {
//
//            tempLength += checkUp(set, num);
//            tempDown = checkDown(set, num);
//            tempLength += tempDown;
//            if(tempLength > bestLength) {
//                bestLength = tempLength;
//                bestStart = num-tempDown;
//            }
//            tempLength = 1;
//        }
//
//        int[] longestSeq = new int[bestLength];
//        for(int i = 0; i < bestLength; i++) {
//            longestSeq[i] = bestStart+i;
//        }
//        return longestSeq;
//    }
//
//    public static int checkUp(HashSet set, int currentValue) {
//    int up = 0;
//    int temp = currentValue + 1;
//    while(set.contains(temp)) {
//        up++;
//        temp++;
//    }
//    return up;
//    }
//
//    public static int checkDown(HashSet set, int currentValue) {
//        int down = 0;
//        int temp = currentValue - 1;
//        while(set.contains(temp)) {
//            down--;
//            temp--;
//        }
//        return down;
//    }
//}