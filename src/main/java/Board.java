import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class Board {
    HashMap<String, Ship> shipHashMap;
    int [][] BoardMatrix = new int[10][10];
    Board(){
        int max = 10, min = 0;

        int shipsNeeded = 5;
        Random rng = new Random();

        Set<String> generatedPositions = new LinkedHashSet<>();
        Set<Integer> generatedLengths = new LinkedHashSet<>();
        int[] generatedLength = new int[5];
        int[] generatedDir = new int[5];
        int[] arr = new int[]{2, 3, 3, 4, 5};
        while (!(generatedPositions.size() == shipsNeeded) ) {
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
            }
        }
        shipHashMap = generateShipHashMap(generatedPositions, generatedLength, generatedDir);
        System.out.println(shipHashMap);
    }

    private static HashMap<String, Ship> generateShipHashMap(Set<String> generatedPositions, int[] generatedLength, int[] generatedDir) {
        HashMap<String,Ship> shipHashMap = new HashMap<>();
        Ship[] shipArray =new Ship[5];
        int arrayPosition=0;
        for(String position: generatedPositions){
            int y = (int) (position.charAt(0))-65;
            int x = Integer.parseInt(String.valueOf(position.charAt(1)));
            shipArray[arrayPosition] = new Ship(x,y, generatedLength[arrayPosition], generatedDir[arrayPosition]);
            int dir = generatedDir[arrayPosition];
            int rowShift, colShift;
            if (dir==0) { rowShift = 0 ; colShift = 1; }
            else { rowShift = 1; colShift = 0; }
            for (int start = 0; start< generatedLength[arrayPosition] ; start++) {
                String shipPosition;
                shipPosition = ((char)(65 + y + (colShift*start))+String.valueOf(x + (rowShift*start))) ;
                shipHashMap.put(shipPosition,shipArray[arrayPosition]);
            }
            arrayPosition += 1;
        }
        return shipHashMap;
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

    public String checkHitOrMissORSink(String inputMove) {
        int y = (int) (inputMove.charAt(0))-65;
        int x = Integer.parseInt(String.valueOf(inputMove.charAt(1)));
        if (this.BoardMatrix[x][y]==1)
            {
                Ship thisShip = shipHashMap.get(inputMove);
                thisShip.hitArray+=1;
                if(thisShip.hitArray== thisShip.shipLength) {
                    System.out.println("Ship has sunk");
                    return "SINK";
                }
                return "HIT";
            }
        return "MISS";
    }
}
