//package ma.sse.eas.capstoneproject.rag;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.ai.document.Document;
//import org.springframework.ai.embedding.*;
//import org.springframework.ai.reader.TextReader;
//import org.springframework.ai.transformer.splitter.TokenTextSplitter;
//import org.springframework.ai.vectorstore.SimpleVectorStore;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.Resource;
//
//
//import java.util.List;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.io.File;
//import java.util.Map;
//
//
//@Configuration
//public class RagConfigurer {
//
//    private static final Logger log = LoggerFactory.getLogger(RagConfigurer.class);
//
//    @Value("classpath:/resources/documents/ExportingBusiness.txt")
//    private Resource faq;
//
//    @Value("vectorstore.json")
//    private String vectorStoreName;
//
//    @Bean
//    SimpleVectorStore simpleVectorStore(EmbeddingModel embeddingModel) {
//        SimpleVectorStore simpleVectorStore= new SimpleVectorStore(embeddingModel);
//        File vectorStoreFile = getVectorStoreFile();
//        if (vectorStoreFile.exists()){
//            log.info("Vector store file exists!");
//            simpleVectorStore.load(vectorStoreFile);
//        } else {
//            log.info("Vector store file not found, loading documents...");
//            TextReader textReader = new TextReader(faq);
//            textReader.getCustomMetadata().put("filename", "ExportingBusiness.txt");
//            List<Document> documents = textReader.get();
//            TokenTextSplitter textSplitter = new TokenTextSplitter();
//            List<Document> splitDocuments = textSplitter.apply(documents);
//            simpleVectorStore.add(splitDocuments);
//            simpleVectorStore.save(vectorStoreFile);
//
//        }
//        return simpleVectorStore;
//
//    }
//
//    private File getVectorStoreFile() {
//        Path path = Paths.get("src", "main", "resources", "data");
//        String absolutePath = path.toFile().getAbsolutePath() + "/" + vectorStoreName;
//        return new File(absolutePath);
//    }
//
//}