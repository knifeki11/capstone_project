//package ma.sse.eas.capstoneproject.services;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.ai.reader.TextReader;
//import org.springframework.ai.transformer.splitter.TextSplitter;
//import org.springframework.ai.transformer.splitter.TokenTextSplitter;
//import org.springframework.ai.vectorstore.VectorStore;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.io.Resource;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class IngestionService implements CommandLineRunner {
//    private static final Logger log = LoggerFactory.getLogger(IngestionService.class);
//    private final VectorStore vectorStore;
//
//    @Value("classpath:/docs/ExportingBusiness.txt")
//    private Resource exportingPDF;
//
//    public IngestionService(VectorStore vectorStore) {
//        this.vectorStore = vectorStore;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        var textReader = new TextReader(exportingPDF);
//        TextSplitter textSplitter = new TokenTextSplitter();
//        vectorStore.accept(textSplitter.apply(textReader.get()));
//        log.info("VectorStore Loaded with data!");
//    }
//}
