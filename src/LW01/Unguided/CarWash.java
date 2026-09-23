package LW01.Unguided;


public class CarWash extends WashService {


    public CarWash(String id, int days, int units){

        super(id, days, units);

    }

    @Override 
    public int calculateCharge(){

        int days = getDays();

        int charge;

        if (days <= 3) {
            charge = days * 35000;
        }

        else {
            charge = (3 * 35000) + ((days - 3) * 25000);
        }

        return charge + 15000;
    }
    
    public String label(){

        return "Car";
    }
}
