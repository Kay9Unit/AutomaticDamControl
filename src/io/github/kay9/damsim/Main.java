package io.github.kay9.damsim;

import java.awt.*;
import java.io.Console;
import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException, InterruptedException
    {
        Console console = System.console();
        if (console == null && !GraphicsEnvironment.isHeadless())
        {
            String filename = Main.class.getProtectionDomain().getCodeSource().getLocation().toString().substring(6);
            Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "cmd", "/k", "java -jar \"" + filename + "\""});
        }
        else
        {
            AutomaticDamControl.run();
            System.out.println("Program has ended, please type 'exit' to close the console");
        }
    }
}