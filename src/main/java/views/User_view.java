package views;

import Model.Data;
import dao.data_DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class User_view {

    private String email;

    User_view(String email)
    {
        this.email=email;
    }
    public void home() {

        do {
            Scanner sc=new Scanner(System.in);
            System.out.println("Welcome \uD83D\uDE00");
            System.out.println("Enter 1 to show hidden files");
            System.out.println("Enter 2 to hide files");
            System.out.println("Enter 3 to unhide files");
            System.out.println("Enter 0 to exit>>>>");

            int choice=0;
            try {
                choice=Integer.parseInt(sc.nextLine());
            }catch(InputMismatchException e)
            {
                e.printStackTrace();
            }

            switch (choice)
            {
                case 1-> {
                    try {
                        List<Data> files= data_DAO.getfiles(this.email);
                        System.out.println("ID - File Name");
                        for(Data file:files)
                        {
                            System.out.println(file.id()+" - "+file.name());
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                }case 2-> {
                System.out.println("Enter the File Path (e.g., C:\\Users\\HP\\Desktop\\hello.txt):");
                String path=sc.nextLine();
                File f=new File(path);
                if (!f.exists()) {
                    System.out.println("File does not exist at: " + path);
                    break;
                }
                Data obj = new Data(0, f.getName(), this.email, path);


                try{
                    data_DAO.hidefile(obj);
                }catch (SQLException | IOException e)
                {
                    e.printStackTrace();
                }
            }case 3 ->
            {
                try {
                    List<Data> files = data_DAO.getfiles(this.email);
                    System.out.println("ID - File Name");
                    for (Data file : files) {
                        System.out.println(file.id() + " - " + file.name());
                    }
                    System.out.println("Enter the id of File to Unhide");
                    int id = Integer.parseInt(sc.nextLine());
                    boolean isvalid = false;
                    for (Data file : files)
                    {
                        if(file.id() == id)
                        {
                            isvalid=true;
                            break;
                        }
                    }

                    if(isvalid)
                    {
                        data_DAO.unhide(id);
                    }else{
                        System.out.println("Wrong ID");
                    }
                }catch (SQLException | IOException e) {
                    throw new RuntimeException(e);
                }
            }case 4->{
                    System.exit(0);
            }
            }
        }while(true);

    }
}
