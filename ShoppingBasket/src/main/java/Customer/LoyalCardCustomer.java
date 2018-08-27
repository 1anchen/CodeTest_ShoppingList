package Customer;

import Interface.ILoyal;

public class LoyalCardCustomer extends Customer implements ILoyal{

    double rate = 0.02;

    public LoyalCardCustomer(double wallet) {
        super(wallet);
    }


    @Override
    public double twoPercentOff() {
        double percentage = 1 - rate;
        return percentage;
    }


}
