package Customer;

public class Customer {

    protected double wallet;

    public Customer(double wallet) {
        this.wallet = wallet;
    }

    public double getWallet() {
        return this.roundWallet();
    }

    private double roundWallet(){
        return wallet= Math.round(wallet*100.00)/100.00;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }
}
