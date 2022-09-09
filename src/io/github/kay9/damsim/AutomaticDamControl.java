package io.github.kay9.damsim;

import java.util.Scanner;

public class AutomaticDamControl
{
    private static final int WATER_RISE_RATE = 4;
    private static final String INFORMATION = String.format("""
            A Note for this simulation:
            This is a simulation for a water dam. Just like in real life, a dam can open and close.
            When the dam opens, it allows water in. When it is closed, water flows out.
            When the dam is open, water level increases at a rate of %s.
            When the dam is closed, water level decreases at a rate of 1.
            The 'trials' variable defines how many times to simulate water flow.
            The dam will open and close automatically depending on the given max and min level inputs
            to regulate the water level.
            """, WATER_RISE_RATE);

    public static void run() throws InterruptedException
    {
        System.out.println(INFORMATION);

        final var MAX_WATER_LEVEL = captureInput("Input maximum water level (inclusive):", 2, Integer.MAX_VALUE);
        final var MIN_WATER_LEVEL = captureInput("Input minimum water level (inclusive):", 0, MAX_WATER_LEVEL - 1);
        var trials = captureInput("Input number of trials to run the dam for:", 1, 5000);

        System.out.printf("Details: [Max Water Level: %s], [Min Water Level: %s], [Trials: %s]%n", MAX_WATER_LEVEL, MIN_WATER_LEVEL, trials);
        Thread.sleep(1000 * 2);

        var waterLevel = 0;
        var fill = false;
        while (trials > 0)
        {
            trials--;

            if (waterLevel <= MIN_WATER_LEVEL) fill = true;

            if (fill)
            {
                if (waterLevel >= MAX_WATER_LEVEL) fill = false;
                else waterLevel += WATER_RISE_RATE;
            }
            else waterLevel--;

            System.out.printf("Dam is: [%s] Water Level: [%s]%n", (fill? "Open" : "Closed"), waterLevel);
        }

        System.out.println("Finished dam simulation.");
    }

    private static int captureInput(String msg, int min, int max)
    {
        System.out.println(msg);
        int input;
        var in = new Scanner(System.in).nextLine();
        try
        {
            input = silentClamp(Integer.parseInt(in), min, max);
        }
        catch (Throwable t)
        {
            System.out.println("Please provide a proper value.");
            input = captureInput(msg, min, max);
        }
        return input;
    }

    private static int silentClamp(int v, int min, int max)
    {
        return (v < min)? min : (v > max)? max : v;
    }
}
