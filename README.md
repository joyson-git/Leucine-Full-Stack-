# TodoList Summary Service

A Spring Boot service that summarizes your to-do items using the Gemini API and sends the summary to a Slack channel via webhook.

---

## Features

- Summarizes a list of to-do items into a concise summary using the Gemini API.
- Sends the generated summary as a message to a specified Slack channel.
- Handles errors gracefully with fallback messages.
- Uses reactive programming with Spring WebFlux’s `WebClient` for asynchronous HTTP calls.

---

## Technologies Used

- Java 17+
- Spring Boot
- Spring WebFlux (WebClient)
- Reactor (Mono)
- Jackson (JSON parsing)

---

## Configuration

Add the following properties to your `application.properties` or `application.yml`:

```properties
gemini.api.key=YOUR_GEMINI_API_KEY
gemini.api.url=https://api.gemini.example.com/v1/summarize
slack.webhook.url=https://hooks.slack.com/services/your/slack/webhook/url
Summarize Todos
Call the summarizeTodos(List<String> todos) method with your list of to-do strings.
Returns a Mono<String> with the summary text.

Send Summary to Slack
Call the sendToSlack(String summary) method with the generated summary string.
Returns a Mono<String> representing the Slack API response.

