package com.github.anamo

import io.micronaut.configuration.picocli.PicocliRunner
import io.micronaut.context.ApplicationContext
import io.micronaut.context.env.Environment

import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

import java.io.ByteArrayOutputStream
import java.io.PrintStream

class StackOverflowCliCommandSpec extends Specification {

    @Shared @AutoCleanup ApplicationContext ctx = ApplicationContext.run(Environment.CLI, Environment.TEST)

    void "test stackOverflow-cli with command line option"() {
        given:
        ByteArrayOutputStream baos = new ByteArrayOutputStream()
        PrintStream out = System.out
        System.setOut(new PrintStream(baos))

        String[] args = ["search","-q","merge maps","-t","java","--verbose"] as String[]
        PicocliRunner.run(StackOverflowCliCommand, ctx, args)
        out.println baos.toString()

        // ✔
        expect:
        baos.toString() =~ $/✔? \d+\|\d+ [^\n]+\n {6}https://stackoverflow.com/questions/\d+/[a-z0-9\-]+/$
    }
}

