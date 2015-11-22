import java.io.*;
import java.util.Scanner;
/**
 * FILE NAME: Calc.java
 * This program emulates functions of a RPN
 * calculator using a stack.
 * 
 * @author (your name) 
 * @version 1.0
 */

public class Calc {
    private int top;
    private double [] stack;
    private double sum;
    // Constructor
    public Calc() {
        top = -1;
        stack = new double [100];
        sum =0;
    }
    
    // Push a number
    public void push(double x) {
        stack[++top] = x;
    }
    
    // Pop top number (removes)
    public double pop() {
        if(top ==-1){
            IOException io;  
            }
        return stack[top--]; //access item, then decrement top
    }
    
    // Peek at top number (does not remove)
    public double peek() {
        if(top ==-1){
            IOException io;  
            }
        return stack[top]; //return what is on top
    }
    
    // Add top two numbers
    public void add() {
        if(top <= 0){
            IOException io;  
            }
        sum = stack[top] + stack[top-1]; // add top 2 elements
        top--;
        stack[top]= sum;
        }
    
    // Subtract top two numbers (top on right side)
    public void subtract() {
        if(top <= 0){
            IOException io;  
            }
        sum = stack[top-1] - stack[top]; // add top 2 elements
        top--;
        stack[top]= sum;
    }

    // Multiply top two numbers
    public void multiply() {
        if(top <= 0){
            IOException io;  
            }
        sum = stack[top] * stack[top-1];
        top--;
        stack[top]=sum;
    }
    
    // Divide top two numbers (top on bottom)
    public void divide() {
        if(top <= 0){
            IOException io;  
            }
        sum = stack[top-1] / stack[top];
        top --;
        stack[top] = sum;
    }
    
    // Return how many numbers are in the stack
    public int depth() {
        if(top ==-1){
            IOException io;  
            }
        return top+1;
    }

    //calculates log base 2 of top of the stack
    public void log2(){
	stack[top] = Math.log(stack[top]) / Math.log(2) ;
    }
    public void reciprocal(){
	stack[top] = 1/stack[top];
    }
}
