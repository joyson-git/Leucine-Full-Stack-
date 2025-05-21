package com.todolist.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class SummaryService {

    @Autowired
    private WebClient webClient;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${slack.webhook.url}")
    private String slackWebhookUrl;

    public Mono<String> summarizeTodos(List<String> todos) {
        String prompt = "Summarize the following todos: " + String.join(", ", todos);

        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                    .path(geminiApiUrl)
                    .queryParam("key", geminiApiKey)
                    .build())
                .header("Content-Type", "application/json")
                .bodyValue("""
                    {
                      "contents": [
                        {
                          "parts": [
                            {
                              "text": "%s"
                            }
                          ]
                        }
                      ]
                    }
                    """.formatted(prompt))
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(e -> {
                    System.err.println("An error occurred while summarizing todos: " + e.getMessage());
                    return Mono.just("Error: Unable to summarize todos.");
                });
    }


    public Mono<String> sendToSlack(String summary) {
        // Escape quotes and backslashes to prevent JSON errors
        String safeSummary = summary.replace("\\", "\\\\").replace("\"", "\\\"");

        return webClient.post()
                .uri(slackWebhookUrl)
                .header("Content-Type", "application/json")
                .bodyValue("{\"text\": \"" + safeSummary + "\"}")
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(e -> {
                    System.err.println("An error occurred while sending to Slack: " + e.getMessage());
                    return Mono.just("Error: Unable to send summary to Slack.");
                });
    }
}
