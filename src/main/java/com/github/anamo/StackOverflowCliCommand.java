package com.github.anamo;

import com.github.anamo.search.SearchCommand;
import io.micronaut.configuration.picocli.PicocliRunner;
import io.micronaut.context.ApplicationContext;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "stackOverflow-cli", description = "...",
        mixinStandardHelpOptions = true, subcommands = {SearchCommand.class})
public class StackOverflowCliCommand implements Runnable {

    @Option(names = {"-v", "--verbose"}, description = "...")
    boolean verbose;

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(StackOverflowCliCommand.class, args);
        System.exit(0);
    }

    public void run() {
        // business logic here
        if (verbose) {
            System.out.println("Hi!");
        }
    }
}
