import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class Board {
    public static void main(String[] args) {
        int max = 10, min = 0;

        int numbersNeeded = 5;
        Random rng = new Random();

        Set<String> generatedPositions = new LinkedHashSet<>();
        Set<Integer> generatedLengths = new LinkedHashSet<>();
        int [][] BoardMatrix = new int[10][10];
        int[] generatedLength = new int[5];
        int[] generatedDir = new int[5];
        int[] arr = new int[]{2, 3, 3, 4, 5};
        while (!(generatedPositions.size() == numbersNeeded) ) {
            int temp = rng.nextInt(max - min) + min;
            String tempCol = String.valueOf((char) ((char) 65 + temp));
            int tempRow = rng.nextInt(max - min) + min;
            int tempDir = rng.nextInt(2);
            int tempIndexLen = rng.nextInt(arr.length);
            if((!generatedPositions.contains(tempCol.concat(String.valueOf(tempRow)))) && (!generatedLengths.contains(tempIndexLen))) {
                //System.out.println(tempRow+" "+temp + " " + tempIndexLen +" "+tempDir);
                if ((tempDir ==0 && temp + arr[tempIndexLen] < max) ||(tempDir==1 && tempRow + arr[tempIndexLen] < max)) {
                    int rowShift, colShift;
                    if (tempDir == 0) {
                        rowShift = 0;
                        colShift = 1;
                    } else {
                        rowShift = 1;
                        colShift = 0;
                    }
                    if (BoardMatrix[tempRow][temp] == 0) {
                        boolean result = checkIfValidShipPositions(BoardMatrix, arr, temp, tempRow, tempIndexLen, rowShift, colShift); //generatedPositions,generatedLengths,generatedLength,generatedDir,arr,temp, tempCol,tempRow,tempIndexLen,tempDir, BoardMatrix
                        if (result)
                            generatePositions(generatedPositions, generatedLengths, generatedLength, generatedDir, arr, temp, tempCol, tempRow, tempIndexLen, tempDir, BoardMatrix);
                    }
                }
                //System.out.println(tempCol + "" + tempRow + "->" + arr[tempIndexLen] + "in" + tempDir);
            }
        }
        System.out.println(generatedPositions);
        System.out.println(Arrays.toString(generatedLength));
        System.out.println(Arrays.toString(generatedDir));
        System.out.println("  A B C D E F G H I J");
        for (int row = 0; row < 10; row++) {
            System.out.print(row+" ");
            for (int col = 0; col < 10; col++) {
                System.out.print(BoardMatrix[row][col]+" ");
            }
            System.out.println();
        }
    }

    private static boolean checkIfValidShipPositions(int[][] boardMatrix, int[] arr, int temp, int tempRow, int tempIndexLen, int rowShift, int colShift) {
        for (int startPos = 1; startPos < arr[tempIndexLen]; startPos++) {
            if((tempRow + rowShift)>=0 && (tempRow + rowShift)<10 && (temp + colShift)>=0 && (temp + colShift)<10) {
                if (boardMatrix[tempRow + (rowShift*startPos)][temp + (colShift*startPos)] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void generatePositions(Set<String> generatedPositions, Set<Integer> generatedLengths, int[] generatedLength, int[] generatedDir, int[] arr, int temp, String tempCol, int tempRow, int tempIndexLen, int tempDir, int[][] BoardMatrix) {
        int rowShift, colShift;
        if (tempDir==0) { rowShift = 0 ; colShift = 1; }
        else { rowShift = 1; colShift = 0; }
        BoardMatrix[tempRow][temp] = 1;
        for (int start = 1; start < arr[tempIndexLen]; start++) {
            // System.out.println(start+" "+tempRow+" "+temp+" "+rowShift+" "+colShift);
            BoardMatrix[tempRow+(rowShift*start)][temp + (colShift*start)] = 1;
        }
        generatedPositions.add(tempCol.concat(String.valueOf(tempRow)));
        generatedLengths.add(tempIndexLen);
        generatedLength[generatedPositions.size() - 1] = arr[tempIndexLen];
        generatedDir[generatedPositions.size() - 1] = tempDir;
    }
}
