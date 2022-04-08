package io.cucumber.skeleton;

public class Belly {
    private int cukesInBelly;

    public Belly () {
        this.cukesInBelly = 0;
    }
    public void eat(int cukes) {
        this.cukesInBelly += cukes;
        System.out.println("I ate "+cukes+" cukes.");
    }

    public void wait(int hour) {
        System.out.println("I waited "+hour+" hours.");
        this.cukesInBelly = 0;
    }

    public int bellySize() {
        return cukesInBelly;
    }


}
