package com.tqs.homework1.model;

import java.util.Objects;

public class Cases {
    private String newCases;
    private String active;
    private String Critical;
    private String recovered;
    private String oneM_pop;
    private String total;


    public Cases(String newCases, String active, String Critical, String recovered, String oneM_pop, String total) {
        this.newCases = newCases;
        this.active = active;
        this.Critical = Critical;
        this.recovered = recovered;
        this.oneM_pop = oneM_pop;
        this.total = total;
    }


    public String getNewCases() {
        return this.newCases;
    }

    public void setNewCases(String newCases) {
        this.newCases = newCases;
    }

    public String getActive() {
        return this.active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCritical() {
        return this.Critical;
    }

    public void setCritical(String Critical) {
        this.Critical = Critical;
    }

    public String getRecovered() {
        return this.recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
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
        if (!(o instanceof Cases)) {
            return false;
        }
        Cases cases = (Cases) o;
        return Objects.equals(newCases, cases.newCases) && Objects.equals(active, cases.active) && Objects.equals(Critical, cases.Critical) && Objects.equals(recovered, cases.recovered) && Objects.equals(oneM_pop, cases.oneM_pop) && Objects.equals(total, cases.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newCases, active, Critical, recovered, oneM_pop, total);
    }


    @Override
    public String toString() {
        return "{" +
            " newCases='" + getNewCases() + "'" +
            ", active='" + getActive() + "'" +
            ", Critical='" + getCritical() + "'" +
            ", recovered='" + getRecovered() + "'" +
            ", oneM_pop='" + getOneM_pop() + "'" +
            ", total='" + getTotal() + "'" +
            "}";
    }


}
