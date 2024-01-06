package byow;

import byow.Core.RandomUtils;

import java.util.Random;

public class Test {

    public static void main(String[] args) {
        Long seed = 50L;
        Random random = new Random(seed);
        System.out.println(RandomUtils.uniform(random, 100));
        System.out.println(RandomUtils.uniform(random, 100));

        Random random1 = new Random(seed);
        System.out.println(RandomUtils.uniform(random1, 100));
        System.out.println(RandomUtils.uniform(random1, 100));
    }
}
