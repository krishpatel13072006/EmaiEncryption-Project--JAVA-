package views;

import Model.User;
import Service.SendOTPService;
import Service.UserService;
import Service.generateOTP;
import dao.UserDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
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
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Name: ");
        String name = sc.nextLine();
        System.out.println("Enter Email: ");
        String email = sc.nextLine();

        // Generate OTP here
        String genotp = generateOTP.getotp(); // ✅ Call OTP generator
        SendOTPService.sendOTP(email, genotp); // ✅ Send the generated OTP to the email

        System.out.println("Enter OTP: ");
        String otp = sc.nextLine();

        if (otp.equals(genotp)) {
            User user = new User(name, email);
            int response = UserService.saveuser(user);

            switch (response) {
                case 0 -> System.out.println("User Registered !");
                case 1 -> System.out.println("User already Exist!");
            }
        } else {
            System.out.println("Wrong OTP!!");
        }
    }

    private void Login() {
        Scanner sc=new Scanner(System.in);
            String email=sc.nextLine();
            try{
                if(UserDAO.isexist(email))
                {
                    String genOTP= generateOTP.getotp();
                    SendOTPService.sendOTP(email,genOTP);
                    System.out.println("Enter the OTP : ");
                    String  otp=sc.nextLine();
                    if(otp.equals(genOTP))
                    {
                        System.out.println("Welcome \uD83D\uDE00");
                    }else{
                        System.out.println("Wrong OTP!! \uD83D\uDE14");
                    }
                }else{
                    System.out.println("Uer not Found \uD83D\uDE14 !!");
                }
            }catch(SQLException e)
            {
                e.printStackTrace();
            }
        }

    public static void main(String[] args) {
        Welcome x=new Welcome();
        do{
            x.enterScreen();
        }while(true);

    }
    }


