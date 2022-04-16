package com.tqs.homework1.model;

import java.util.Objects;

public class Cases {
    private String newCases;
    private Long active;
    private Long critical;
    private Long recovered;
    private String oneM_pop;
    private Long total;



    public Cases(String newCases, Long active, Long critical, Long recovered, String oneM_pop, Long total) {
        this.newCases = newCases;
        this.active = active;
        this.critical = critical;
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

    public Long getActive() {
        return this.active;
    }

    public void setActive(Long active) {
        this.active = active;
    }

    public Long getCritical() {
        return this.critical;
    }

    public void setCritical(Long critical) {
        this.critical = critical;
    }

    public Long getRecovered() {
        return this.recovered;
    }

    public void setRecovered(Long recovered) {
        this.recovered = recovered;
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
        if (!(o instanceof Cases)) {
            return false;
        }
        Cases cases = (Cases) o;
        return Objects.equals(newCases, cases.newCases) && Objects.equals(active, cases.active) && Objects.equals(critical, cases.critical) && Objects.equals(recovered, cases.recovered) && Objects.equals(oneM_pop, cases.oneM_pop) && Objects.equals(total, cases.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newCases, active, critical, recovered, oneM_pop, total);
    }


    @Override
    public String toString() {
        return "{" +
            " newCases='" + getNewCases() + "'" +
            ", active='" + getActive() + "'" +
            ", critical='" + getCritical() + "'" +
            ", recovered='" + getRecovered() + "'" +
            ", oneM_pop='" + getOneM_pop() + "'" +
            ", total='" + getTotal() + "'" +
            "}";
    }
    


}
