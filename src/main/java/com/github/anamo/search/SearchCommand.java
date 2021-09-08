package com.github.anamo.search;

import com.github.anamo.StackOverflowCliCommand;
import com.github.anamo.api.Question;
import com.github.anamo.api.StackOverflowHttpClient;
import jakarta.inject.Inject;
import picocli.CommandLine;

@CommandLine.Command(name = "search", description = "Search questions matching criteria", mixinStandardHelpOptions = true)
final public class SearchCommand implements Runnable{

    @CommandLine.Option(names = {"-q","--query"},description = "Search phrase.")
    String query="";

    @CommandLine.Option(names = {"-t","--tag"}, description = "Search inside specific tag.")
    String tag="";

    @CommandLine.Option(names = {"-n","--limit"}, description = "Limit Results Default: 10")
    int limit=10;

    @CommandLine.Option(names = {"-s","--sort-by"}, description = "Available values relevance, votes, creation, activity. Default: relevance")
    String sort = "relevance";

    @CommandLine.Option(names = {"--verbose"}, description = "Print verbose output")
    boolean verbose;

    @Inject
    StackOverflowHttpClient client;

    @Override
    public void run() {
        var response = client.search(query, tag, limit, sort);

        response.items.stream().map(SearchCommand::formatQuestion)
                .forEach(System.out::println);
        if(verbose)
        {
            System.out.printf("Items size %d | Quoata max: %d | Quoata Reamining: %d | Has More: %s\n",
                    response.items.size(),
                    response.quotaMax,
                    response.quotarRmaining,
                    response.hasMore);
        }
    }

    static private String formatQuestion(final Question question)
    {
        return CommandLine.Help.Ansi.AUTO.string(String.format(
                "@|bold,fg(green) %s|@ %d|%d @|bold,fg(yellow) %s|@\n      %s",
                question.accepted ? "✔":"",
                question.score,
                question.answers,
                question.title,
                question.link
        ));
    }
}
