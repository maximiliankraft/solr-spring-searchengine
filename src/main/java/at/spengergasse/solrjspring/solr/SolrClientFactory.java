package at.spengergasse.solrjspring.solr;

import lombok.extern.java.Log;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
@Log
public class SolrClientFactory {

    @Bean
    public HttpSolrClient solrBean() throws IOException {
        Properties prop = new Properties();

        InputStream inputStream = getClass().getResourceAsStream("/application.properties");
        prop.load(inputStream);

        return SolrClientFactory.connect(prop);
    }

    public static HttpSolrClient connect(Properties props){

        String urlString = "http://"+props.getProperty("cli.solrhostname")+":"+props.getProperty("cli.solrhostport")+"/solr/"+props.getProperty("cli.solrcollection");

        log.info("Url string to connect to");
        log.info(urlString);

        HttpSolrClient solrClient = new HttpSolrClient.Builder(urlString).build();
        solrClient.setParser(new XMLResponseParser());

        return solrClient;
    }

}
