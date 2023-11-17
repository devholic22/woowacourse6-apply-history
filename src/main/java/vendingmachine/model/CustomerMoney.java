package vendingmachine.model;

public class CustomerMoney {

    private final Number customerMoney;

    private CustomerMoney(final Number customerMoney) {
        this.customerMoney = customerMoney;
    }

    public static CustomerMoney from(final String moneyInput) {
        Number money = Number.from(moneyInput);
        return new CustomerMoney(money);
    }

    public int getMoney() {
        return customerMoney.getNumber();
    }
}
