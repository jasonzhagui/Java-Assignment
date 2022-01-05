//Jason Zhagui Homework 1
package main;

import java.util.Arrays;

public class Homework {

    public static void main(String[] args) {
        //Creates Chocolate Chip recipe 
        Recipe ChocolateChip = new Recipe("Chocolate Chip");
        //Manually adds ingredients to array with measurement and item 
        ChocolateChip.addIngredient(1.5, "eggs");
        ChocolateChip.addIngredient(4.0, "milk");
        ChocolateChip.addIngredient(6.0, "chocolate");
        //Manually adds steps to steps array 
        ChocolateChip.addStep("add eggs");
        ChocolateChip.addStep("add milk");
        ChocolateChip.addStep("add chocolate");
        //Prints out the entire recipe
        System.out.println(ChocolateChip.toString());
    }
}
//Ingredients Class
class Ingredients{
    //Holds measurement, item and count
    public double measurement; 
    public String item;
    
    //Constructor 
    Ingredients(double measurement, String item) { 
        this.measurement = measurement; 
        this.item = item; 
    }
}
//Recipe Class
class Recipe{
    //Holds name, ingredients array, and steps array 
    public String name;
    public Ingredients[] ingredients = new Ingredients[0];
    public String[] steps = new String[0];
    public int ingredientsCount;
    //Constructor
    Recipe(String name){
        this.name = name;
    }
    //Dynamically adds ingredients to array
    public void addIngredient(double measurement, String item){
        
        this.ingredients = Arrays.copyOf(this.ingredients,this.ingredients.length+1);
        this.ingredients[this.ingredients.length - 1] = new Ingredients(measurement, item);
    }
    //Dynamically adds steps to array
    public void addStep(String step){
        this.steps = Arrays.copyOf(this.steps,this.steps.length+1);
        this.steps[this.steps.length-1] = step;
    }
    //Overrides toString() for output
    @Override
    public String toString(){
        
        String output = "Recipe for " + this.name + ":\n";
        //Reads through ingredients array and adds to string
        for(Ingredients ingredient:this.ingredients){
            //If it reads null it means it has reached the end of array
            if(ingredient==null){
                break;
            }
            output += ingredient.measurement + " " + ingredient.item+ "\n";
        }
        
        output += "\n";
        //Reads through steps array and adds to string
        for(String step:this.steps){
            //If it reads null it means it has reached the end of array
            if(step==null){
               break; 
            }
            output += "\n"+ step;
        }
        //Returns entire recipe
        return output;
  }
}