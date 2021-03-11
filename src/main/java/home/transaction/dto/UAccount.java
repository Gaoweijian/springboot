package home.transaction.dto;

public class UAccount {

    private int id;

    private String name;

    private int money;

    /**
     * Getter method for property <tt>id</tt>.
     *
     * @return property value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     *
     * @param id value to be assigned to property id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     *
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for property <tt>money</tt>.
     *
     * @return property value of money
     */
    public int getMoney() {
        return money;
    }

    /**
     * Setter method for property <tt>money</tt>.
     *
     * @param money value to be assigned to property money
     */
    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "id:" + getId() + "\t" +
                "name:" + getName() + "\t" +
                "money:" + getMoney() + "\t"
                ;
    }
}
