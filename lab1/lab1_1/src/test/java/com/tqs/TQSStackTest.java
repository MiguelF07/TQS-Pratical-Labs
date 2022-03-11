package com.tqs;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class TQSStackTest 
{
    /**
     * Rigorous Test :-)
     */
    private TQSStack stack;

    @BeforeEach
    public void setUp(){
        this.stack = new TQSStack();
    }
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @DisplayName("Alinea A) - A stack is empty on construction.")
    @Test
    public void stackSize_isEmptyOnConstruction() {
        assertTrue(stack.isEmpty());
    }

    @DisplayName("Alinea B) - A stack has size 0 on construction.")
    @Test
    public void stackSize_hasSize0OnConstruction() {
        assertEquals(0,stack.size(),"Expected size 0");
    }

    // @ParameterizedTest
    // @ValueSource(strings = {"2","4","5"})

    @DisplayName("Alinea C) - After n pushes to an empty stack, n > 0, the stack is not empty and its size is n")
    @Test
    public void stackSize_afterNPushes() {
        stack.push("Hello");
        stack.push("World");
        assertEquals(2, stack.size(),"Stack doesn't have size 2 after 2 pushes");
    }

    @DisplayName("Alinea D) - If one pushes x then pops, the value popped is x.")
    @Test
    public void pushThenPop() {
        stack.push("Sea");
        String returned = stack.pop();
        assertEquals("Sea", returned,"The returned value is not \"Sea\"");
    }

    @DisplayName("Alinea E) - If one pushes x then peeks, the value returned is x, but the size stays the same")
    @Test
    public void pushThenPeek() {
        stack.push("Star");
        assertAll("Should return Star and 1",
            () -> assertEquals("Star",stack.peek()),
            () -> assertEquals(1, stack.size())
        );
    }

    @DisplayName("Alinea F) - If the size is n, then after n pops, the stack is empty and has a size 0")
    @Test
    public void stackSize_afterNPops() {
        stack.push("Hello");
        stack.push("Darkness");
        stack.push("My");
        stack.push("Old");
        stack.push("Friend");

        for(int i=0;i<5;i++) {
            stack.pop();
        }

        assertAll("Should be empty and have size 0",
            () -> assertTrue(stack.isEmpty()),
            () -> assertEquals(0, stack.size())
        );
    }

    @DisplayName("Alinea G) - Popping from an empty stack does throw a NoSuchElementException")
    @Test
    public void emptyStack_ThrowsExceptionWhenPopping() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            stack.pop();
        },"NoSuchElementException error was expected when popping");
    }

    @DisplayName("Alinea H) - Peeking into an empty stack does throw a NoSuchElementException")
    @Test
    public void emptyStack_ThrowsExceptionWhenPeeking() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            stack.peek();
        },"NoSuchElementException error was expected when peeking");
    }

    @DisplayName("Alinea I) - For bounded stacks only: pushing onto a full stack does throw an IllegalStateException")
    @Test
    public void fullBoundedStack_ThrowsException() {
        stack = new TQSStack(2);

        stack.push("Engenharia");
        stack.push("Informatica");

        Assertions.assertThrows(IllegalStateException.class, () -> {
            stack.push("UA");
        },"IllegalStateException error was expected when pushing into a full stack");
    }
}
