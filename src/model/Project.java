package model;


import java.math.BigDecimal;
import java.util.Date;

/**
 * This Project is to handle the budget for the user.
 *
 * @author Thinh Le
 * @version 0.1
 */
public class Project {

    /**
     * id for project
     */
   private int id;
    /**
     * this is the total cost
     */
    private BigDecimal totalCost;
    /**
     * This is the budget of a user.
     */
    private BigDecimal budget;
    /**
     * This is amount of money.
     */
    private BigDecimal balance;
    /**
     * Name of item.
     */

    private String itemName;
    /**
     * The date that users buy item.
     */
    private Date date;


    /**
     * This constructor helps to calculate the cost of item for user, and let the uses to see the current
     *  budget, and this is also helps them to track item by date and total cost.
     * @param id
     * @param totalCost
     * @param budget
     * @param balance
     * @param date
     * @param itemName
     */
    public Project(int id,BigDecimal totalCost, BigDecimal budget, BigDecimal balance, Date date, String itemName){
    this.totalCost = totalCost;
    this.budget = budget;
    this.balance = balance;
    this.id = id;
    this.date = date;
    this.itemName = itemName;
    }

    /**
     * Getter for id.
     * @return
     */
    public int getId(){
        return id;
    }

    /**
     * Getter for item name.
     * @return
     */
    public String getItemName(){
        return itemName;
    }

    /**
     * Getter for date.
     * @return
     */
    public Date getDate(){
        return date;
    }

    /**
     * Getter for total cost.
     * @return
     */
    public BigDecimal getTotalCost(){
        return totalCost;
    }

    /**
     * Getter for budget.
     * @return
     */
    public BigDecimal getBudget(){
    return budget;
    }

    /**
     * Getter for balance.
     * @return
     */
    public BigDecimal getBalance(){
    return balance;
    }

    /**
     * This is the setter for item name.
     * @param itemName
     */
    public void setItemName(String itemName){
        this.itemName = itemName;
    }

    /**
     * This is the setter for balance
     * @param balance
     */
    public void setBalance(BigDecimal balance){
        this.balance = balance;
    }

    /**
     * This is the setter for Date.
     * @param date
     */
    public void setDate(Date date){
        this.date = date;
    }

    /**
     * This is the setter for id.
     * @param id
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * This is the setter for budget
     * @param budget
     */
    public void setBudget(BigDecimal budget){
        this.budget = budget;
    }

    /**
     *
     * This is the seeter for total caost
     * @param totalCost
     */
    public void setTotalCost(BigDecimal totalCost){
        this.totalCost = totalCost;
    }

}
