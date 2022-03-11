package com.tqs;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class TQSStack 
{
    private LinkedList<String> stack;
    private boolean hasLimit;
    private int limit;

    public TQSStack() {
        this.hasLimit = false;
        this.stack = new LinkedList<String>();
    }

    public TQSStack(int limit) {
        this.hasLimit = true;
        this.stack = new LinkedList<String>();
        this.limit = limit;
    }

    public LinkedList<String> getStack() {
        return this.stack;
    }

    public void setStack(LinkedList<String> stack) {
        this.stack = stack;
    }

    public void push(String st) {
        if(this.hasLimit) {
            if(this.stack.size()<limit) {
                this.stack.add(st);
            }
            else {
                System.out.println(limit);
                throw new IllegalStateException();
            }
        }
        else {
            this.stack.add(st);
        }
        
    }

    public String pop() {
        if(this.stack.size()==0)
        {
            throw new NoSuchElementException();
        }
        return this.stack.pop();
    }

    public String peek() {
        if(this.stack.size()==0)
        {
            throw new NoSuchElementException();
        }
        return this.stack.peek();
    }

    public Integer size() {
        return this.stack.size();
    }

    public boolean isEmpty() {
        if(this.stack.size()==0)
        {
            return true;
        }
        else {
            return false;
        }
    }
}
