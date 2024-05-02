package at.spengergasse.solrjspring.solr;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

@Component
@Log
@AllArgsConstructor
public class SolrClientFactory {

    Environment environment;

    @Bean
    public HttpSolrClient solrBean() throws IOException {
        Properties prop = new Properties();

        return SolrClientFactory.connect(environment);
    }

    public static HttpSolrClient connect(Environment environment){
        String urlString = "http://"+environment.getProperty("cli.solrhostname")+":"+environment.getProperty("cli.solrhostport")+"/solr/"+environment.getProperty("cli.solrcollection");

        log.info("Url string to connect to");
        log.info(urlString);

        HttpSolrClient solrClient = new HttpSolrClient.Builder(urlString).build();
        solrClient.setParser(new XMLResponseParser());

        return solrClient;
    }

}
