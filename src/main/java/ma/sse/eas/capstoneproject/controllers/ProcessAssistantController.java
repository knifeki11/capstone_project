package ma.sse.eas.capstoneproject.controllers;


import org.springframework.web.bind.annotation.*;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/assistant")
public class ProcessAssistantController {


    private final ChatClient chatClient;

    public ProcessAssistantController(ChatClient.Builder builder, VectorStore vectorStore) {
        this.chatClient = builder
                .defaultAdvisors(new QuestionAnswerAdvisor(vectorStore))
                .build();
    }

    @GetMapping("/prompt")
    public String chat(@RequestParam String process) {
        return chatClient.prompt()
                .user(String.format("In the %s process, what optimization suggestion can you provide?", process))
                .call()
                .content();
    }

}
