package tg.bot.timeLine.TelegramBotTimeLine.db.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tg.bot.timeLine.TelegramBotTimeLine.enums.botEnums.OpenAiPrompt;

@Service
public class OpenAIService {
    private final WebClient webClient;
    private final String openAiApiUrl;
    private final String apiKey;


    public OpenAIService(WebClient.Builder webClientBuilder,
                         @Value("${openai.api.url}") String openAiApiUrl,
                         @Value("${openai.api.key}") String apiKey) {
        this.webClient = webClientBuilder.build();
        this.openAiApiUrl = openAiApiUrl;
        this.apiKey = apiKey;
    }

    public Mono<String> processEvent(String message) {
        String prompt = OpenAiPrompt.PROMPT_PARSE_EVENT_MESSAGE + message;
        String requestBody = buildRequestBody(prompt);

        return webClient.post()
                .uri(openAiApiUrl)
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class);
    }

    private String buildRequestBody(String prompt) {
        return "{\"model\":\"gpt-4o\",\"messages\":[{\"role\":\"user\",\"content\":\"" + prompt + "\"}]}";
    }
}
