package Customer;

import Interface.ILoyal;

public class LoyalCardCustomer extends Customer implements ILoyal{

    public LoyalCardCustomer(double wallet) {
        super(wallet);
    }


    @Override
    public double twoPercentOff(double rate) {
        Double percentage = 1 - rate;
        return percentage;
    }


}
