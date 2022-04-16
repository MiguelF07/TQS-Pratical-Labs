package com.tqs.homework1.model;

import java.util.Objects;

public class Tests {
    private String oneM_pop;
    private Long total;


    public Tests(String oneM_pop, Long total) {
        this.oneM_pop = oneM_pop;
        this.total = total;
    }


    public String getOneM_pop() {
        return this.oneM_pop;
    }

    public void setOneM_pop(String oneM_pop) {
        this.oneM_pop = oneM_pop;
    }

    public Long getTotal() {
        return this.total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }



    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Tests)) {
            return false;
        }
        Tests tests = (Tests) o;
        return Objects.equals(oneM_pop, tests.oneM_pop) && Objects.equals(total, tests.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oneM_pop, total);
    }


    @Override
    public String toString() {
        return "{" +
            " oneM_pop='" + getOneM_pop() + "'" +
            ", total='" + getTotal() + "'" +
            "}";
    }
    
    
}