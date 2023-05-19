package model;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

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

   private String id;



 /**
     * this is the total cost
     */
    private BigDecimal totalCost;
    /**
     * This is the budget of a user.
     */
    private BigDecimal budget;

   /**
   * this ownerName is used to find the owerName information who this project belongs to.
   */
  private String ownerName;



 /**
     * The date that users buy item.
     */
    private LocalDate  date;


    /**
     * This constructor helps to calculate the cost of item for user, and let the uses to see the current
     * budget, and this is also helps them to track item by date and total cost.
     *

     * @param totalCost
     * @param budget
     * @param ownerName
     */
    public Project( BigDecimal totalCost, BigDecimal budget, String ownerName){
     this.totalCost = totalCost;
     this.budget = budget;
     this.id =  UUID.randomUUID().toString();
     this.date = LocalDate.now();
     this.ownerName = ownerName;
    }

    /**
     * Getter for id.
     * @return
     */
    public String getId(){
        return id;
    }



    /**
     * Getter for date.
     * @return
     */
    public LocalDate  getDate(){
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
    return budget.subtract(totalCost);
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

   /**
    * OwnerName getter
    * @return
     */
     public String getOwnerName() {
     return ownerName;
    }


}
