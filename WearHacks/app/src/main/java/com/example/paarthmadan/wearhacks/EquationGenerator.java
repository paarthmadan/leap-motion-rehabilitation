package com.example.paarthmadan.wearhacks;

/**
 * Created by paarthmadan on 16-03-26.
 */
public class EquationGenerator {
    final int QUESTIONS_PER_LEVEL = 5;
    String level;



    public void createEquations(String level){

        switch (level){
            case "one":

                for(int i = 0; i <= QUESTIONS_PER_LEVEL; i++){

                    int firstNum = (int)(Math.random() * 10) + 1;
                    int operatorBool = (int)((Math.random() * 2) + 1);
                    String operator = "";

                    if(operatorBool == 1){
                        operator = "+";
                    }else if(operatorBool == 2){
                        operator = "-";
                    }

                    int secondNum = (int)(Math.random() * 10) + 1;

                    System.out.println(firstNum + " " + operator + " " + secondNum);



                }

                break;
            case "two":

                for(int i = 0; i <= QUESTIONS_PER_LEVEL; i++){

                    int firstNum = (int)(Math.random() * 90) + 10;
                    int operatorBool = (int)((Math.random() * 2) + 1);
                    String operator = "";

                    if(operatorBool == 1){
                        operator = "+";
                    }else if(operatorBool == 2){
                        operator = "-";
                    }

                    int secondNum = (int)(Math.random() * 90) + 10;

                    System.out.println(firstNum + " " + operator + " " + secondNum);

                    
                }


                break;
            case "three":
                break;
            case "four":
                break;
            case "five":
                break;
            case "six":
                break;
            default:
                break;
        }


    }




}
