package sample;

public class Currencies {
    private String currencyName;
    private float rate;

    public Currencies(String currencyName, float rate){
        this.currencyName=currencyName;
        this.rate=rate;
    }
    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public float getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return "Currencies{" +
                "currencyName='" + currencyName + '\'' +
                ", rate=" + rate +
                '}';
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
