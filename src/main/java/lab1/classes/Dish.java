package lab1.classes;

import java.util.Objects;
/**
 * class "Dish" with fields: dishName, group, price, weight
 * @author User
 * @version 1.0
 */
public class Dish {
    public enum Group{soup, meat, drink, fish, garnish, snack }

    private String dishName;
    private Group group;
    private int price;
    private int weight;

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public int getDishId() {
        return dishId;
    }

    private int dishId;

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    /**
     *  Overridden function of obtaining a string representation of
     *  an instance of a class "Dish"
     *  @return returns the string representation
     */
    @Override
    public String toString() {
        return "Dish{" +
                "dishName='" + dishName + '\'' +
                ", group=" + group +
                ", price=" + price +
                ", weight=" + weight +
                '}';
    }
    /**
     * Overridden function of comparison an instance of
     * the class "Dish" and an instance of the class "Object"
     * @return returns the boolean value of the comparison
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return Float.compare(dish.price, price) == 0 && Float.compare(dish.weight, weight) == 0 && dishName.equals(dish.dishName) && group == dish.group;
    }
    /**
     * Overridden function of obtaining the hash code
     * @return returns the numeric value of the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(dishName, group, price, weight);
    }

    /**
     * Class "DishBuilder" with fields: region
     * @author User
     * @version 1.0
     */
    public static class DishBuilder{
        private Dish dish;
        public DishBuilder(){
            dish = new Dish();
        }

        /**
         * Setter name designation
         * @param dishName - name of dish
         * @return returns current object
         */
        public DishBuilder setDishName(String dishName){
            dish.dishName = dishName;
            return this;
        }
        /**
         * Setter square designation
         * @param group - dish group
         * @return returns current object
         */
        public DishBuilder setGroup(Group group){
            dish.group = group;
            return this;
        }
        public DishBuilder setDishId(int dishId){
            dish.dishId = dishId;
            return this;
        }
        /**
         * Setter number of people designation
         * @param price - dish price
         * @return returns current object
         */
        public DishBuilder setPrice(int price){
            dish.price = price;
            return this;
        }
        /**
         * Setter number of animal designation
         * @param weight - dish weight
         * @return returns current object
         */
        public DishBuilder setWeight(int weight){
            dish.weight = weight;
            return this;
        }

        /**
         * Function of creating an object of class "Dish"
         * @return returns new object of class "Dish"
         */
        public Dish build(){
            return dish;
        }
    }

    public static void main(String args[]) {
    }
}
