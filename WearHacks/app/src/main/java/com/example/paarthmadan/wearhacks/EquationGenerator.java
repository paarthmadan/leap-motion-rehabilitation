package com.example.paarthmadan.wearhacks;

/**
 * Created by paarthmadan on 16-03-26.
 */
public class EquationGenerator {
    final int QUESTIONS_PER_LEVEL = 5;
    String level;
    String [] equations1;
    String [] equations2;
    String [] equations3;
    String [] equations4;
    String [] equations5;
    String [] equations6;

    public String[] createEquations(String level){

        switch (level){
            case "one":

                 equations1 = new String [QUESTIONS_PER_LEVEL + 1];

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

                    if(secondNum > firstNum){
                        int temp = firstNum;

                        firstNum = secondNum;

                        secondNum = temp;
                    }

                    String finalEq = firstNum + " " + operator + " " + secondNum;

                    

                    equations1[i] = finalEq;


                }


                break;
            case "two":

                equations2 = new String [QUESTIONS_PER_LEVEL  + 1];
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

                    if(secondNum > firstNum){
                        int temp = firstNum;

                        firstNum = secondNum;

                        secondNum = temp;
                    }

                    String finalEq = firstNum + " " + operator + " " + secondNum;

                    

                    equations2[i] = finalEq;

                }



                break;
            case "three":

                equations3 = new String [QUESTIONS_PER_LEVEL + 1];

                for(int i = 0; i <= QUESTIONS_PER_LEVEL; i++){

                    int firstNum = (int)(Math.random() * 90) + 10;
                    int operatorBool = (int)((Math.random() * 2) + 1);
                    String operator = "";

                    if(operatorBool == 1){
                        operator = "*";
                    }else if(operatorBool == 2){
                        operator = "/";
                    }

                    boolean isSecondDivisbleByFirst = false;

                    int secondNum = (int) (Math.random() * 9) + 1;

                    if(operator.equals("/")) {

                        while (!isSecondDivisbleByFirst) {

                            secondNum = (int) (Math.random() * 9) + 1;

                            if (firstNum % secondNum == 0) {
                                isSecondDivisbleByFirst = true;
                            }


                        }
                    }

                    String finalEq = firstNum + " " + operator + " " + secondNum;

                    

                    equations3[i] = finalEq;


                }


                break;
            case "four":

                equations4 = new String [QUESTIONS_PER_LEVEL + 1];

                for(int i = 0; i <= QUESTIONS_PER_LEVEL; i++){

                    int firstNum = (int)(Math.random() * 900) + 100;
                    int operatorBool = (int)((Math.random() * 2) + 1);
                    String operator = "";

                    if(operatorBool == 1){
                        operator = "+";
                    }else if(operatorBool == 2){
                        operator = "-";
                    }

                    int secondNum = (int)(Math.random() * 900) + 100;



                    if(secondNum > firstNum){
                        int temp = firstNum;

                        firstNum = secondNum;

                        secondNum = temp;
                    }

                    String finalEq = firstNum + " " + operator + " " + secondNum;

                    

                    equations4[i] = finalEq;



                }


                break;
            case "five":

                equations5 = new String [QUESTIONS_PER_LEVEL + 1];

                for(int i = 0; i <= QUESTIONS_PER_LEVEL; i++){

                    int firstNum = (int)(Math.random() * 900) + 100;
                    int operatorBool = (int)((Math.random() * 2) + 1);
                    String operator = "";

                    if(operatorBool == 1){
                        operator = "/";
                    }else if(operatorBool == 2){
                        operator = "/";
                    }

                    boolean isSecondDivisbleByFirst = false;

                    int secondNum = (int) (Math.random() * 90) + 10;

                    if(operator.equals("/")) {

                        while (!isSecondDivisbleByFirst) {

                            secondNum = (int) (Math.random() * 90) + 10;

                            if (firstNum % secondNum == 0) {
                                isSecondDivisbleByFirst = true;
                            }


                        }

                    }
                    String finalEq = firstNum + " " + operator + " " + secondNum;

                    

                    equations5[i] = finalEq;

                }

                break;
            case "six":

                equations6 = new String [QUESTIONS_PER_LEVEL + 1];

                for(int i = 0; i <= QUESTIONS_PER_LEVEL; i++){

                    int firstNum = (int)(Math.random() * 900) + 100;
                    int operatorBool = (int)((Math.random() * 2) + 1);
                    String operator = "";

                    if(operatorBool == 1){
                        operator = "*";
                    }else if(operatorBool == 2){
                        operator = "*";
                    }


                    int secondNum = (int) (Math.random() * 90) + 10;


                    String finalEq = firstNum + " " + operator + " " + secondNum;

                    

                    equations6[i] = finalEq;


                }
                break;
            default:
                break;
        }

        if(level == "one"){
            return equations1;
        }else if(level == "two"){
            return equations2;
        }else if(level == "three"){
            return equations3;
        }else if(level == "four"){
            return equations4;
        }else if(level == "five"){
            return equations5;
        }else if(level == "six"){
            return equations6;
        }

        return null;

    }
}
