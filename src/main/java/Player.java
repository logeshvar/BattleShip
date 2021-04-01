import java.util.Scanner;

public class Player {

    public String getMove(){
        System.out.println("Enter Q to Quit the Game");
        System.out.print("Enter position guess:");
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }

}


