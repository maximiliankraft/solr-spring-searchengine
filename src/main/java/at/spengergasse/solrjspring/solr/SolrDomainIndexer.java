package at.spengergasse.solrjspring.solr;

import at.spengergasse.solrjspring.entities.DomainBean;
import lombok.extern.java.Log;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

@Component
@Log
public class SolrDomainIndexer {

    public static void buildIndex(HttpSolrClient solrClient) throws IOException, SolrServerException {

        if (solrClient == null){
            log.severe("Solr client is null");
            return;
        }

        // read file from resources
        InputStream inputStream = SolrQueryExecutor.class.getResourceAsStream("/domainnames.txt");

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        String line = "0";
        List<DomainBean> domainBeans = new LinkedList<>();

        log.info("Processing domains file");
        while (line != null ){
            line = br.readLine();

            DomainBean domainBean = new DomainBean(line);
            domainBeans.add(domainBean);
        }

        log.info("commiting to solr");
        solrClient.addBeans(domainBeans);
        solrClient.commit();
        log.info("Indexing done");
    }

}
