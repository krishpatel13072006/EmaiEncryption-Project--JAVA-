package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Welcome {

    public void enterScreen()
    {
        //inputStream converts raw data in character data(text)
        // BufferReader used to read data efficiently -> provides method like ->>ReadLine()-to read complete line at a time.
        /*BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to the Application");
        System.out.println("Enter 1 -> login");
        System.out.println("Enter 2 -> Signup");
        System.out.println("Enter 0 -> exit");
        int choice=0;
        try{
            choice=Integer.parseInt(br.readLine());
        }catch (IOException e)
        {
            e.printStackTrace();
        }*/
        Scanner sc=new Scanner(System.in);
        System.out.println("Welcome to the Application");
        System.out.println("Enter 1 -> login");
        System.out.println("Enter 2 -> Signup");
        System.out.println("Enter 0 -> exit");
        
        int choice=0;
        try{
           choice=sc.nextInt();
        }catch(InputMismatchException e)
        {
            e.printStackTrace();
        }
        
        switch (choice)
        {
            case 1:
                Login();
                break;
            case 2:
                Signup();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Input is not correct please Try-Again!!!");
        }

    }

    private void Signup() {
    }

    private void Login() {
    }
}
