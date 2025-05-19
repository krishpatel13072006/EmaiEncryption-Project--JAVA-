package Service;

import java.util.Random;

public class generateOTP {

    public static String getotp()
    {
        Random random=new Random();
        return String.format("%04d",random.nextInt(10000));
    }
}
