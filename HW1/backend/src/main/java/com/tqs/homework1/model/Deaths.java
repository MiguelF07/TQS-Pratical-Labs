package com.tqs.homework1.model;

import java.util.Objects;

public class Deaths {
    private String newDeaths;
    private String oneM_pop;
    private String total;


    public Deaths(String newDeaths, String oneM_pop, String total) {
        this.newDeaths = newDeaths;
        this.oneM_pop = oneM_pop;
        this.total = total;
    }


    public String getNewDeaths() {
        return this.newDeaths;
    }

    public void setNewDeaths(String newDeaths) {
        this.newDeaths = newDeaths;
    }

    public String getOneM_pop() {
        return this.oneM_pop;
    }

    public void setOneM_pop(String oneM_pop) {
        this.oneM_pop = oneM_pop;
    }

    public String getTotal() {
        return this.total;
    }

    public void setTotal(String total) {
        this.total = total;
    }



    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Deaths)) {
            return false;
        }
        Deaths deaths = (Deaths) o;
        return Objects.equals(newDeaths, deaths.newDeaths) && Objects.equals(oneM_pop, deaths.oneM_pop) && Objects.equals(total, deaths.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newDeaths, oneM_pop, total);
    }


    @Override
    public String toString() {
        return "{" +
            " newDeaths='" + getNewDeaths() + "'" +
            ", oneM_pop='" + getOneM_pop() + "'" +
            ", total='" + getTotal() + "'" +
            "}";
    }

}
