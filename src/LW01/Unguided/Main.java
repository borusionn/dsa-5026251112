package LW01.Unguided;


import java.io.InputStream;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) {


        InputStream input = Main.class.getResourceAsStream("washes.txt");


        Scanner scanner = new Scanner(input);


        int total = scanner.nextInt();


        WashService[] washes = new WashService[total];



        for(int i = 0; i < total; i++){


            String type = scanner.next();

            String id = scanner.next();

            int days = scanner.nextInt();

            int units = scanner.nextInt();



            if(type.equals("MOTORCYCLE")){


                washes[i] = new MotorcycleWash(id, days, units);

            }
            else if(type.equals("CAR")){


                washes[i] = new CarWash(id, days, units);

            }

        }

        for(WashService wash : washes){


            System.out.println(wash.summary());


        }

        scanner.close();
    }


}