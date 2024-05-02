package at.spengergasse.solrjspring.controller;

import at.spengergasse.solrjspring.entities.DomainBean;
import at.spengergasse.solrjspring.solr.SolrClientFactory;
import at.spengergasse.solrjspring.solr.SolrDomainIndexer;
import at.spengergasse.solrjspring.solr.SolrQueryExecutor;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/domain")
public class DomainController {

    SolrClientFactory solrClientFactory;
    HttpSolrClient solrClient;
    SolrQueryExecutor<DomainBean> queryExecutor;

    public DomainController(SolrClientFactory solrClientFactory, HttpSolrClient solrClient) throws IOException {
        this.solrClientFactory = new SolrClientFactory();
        this.solrClient = solrClientFactory.solrBean();
        this.queryExecutor = new SolrQueryExecutor<>(solrClient, DomainBean.class);
    }

    @GetMapping("/search/{query}")
    public SolrDocumentList searchDomains(
            @PathVariable String query,
            @RequestParam(required = false) String rows) throws SolrServerException, IOException
    {
        int rowsParsed = 10;

        if (rows != null){
            rowsParsed = Integer.parseInt(rows);
        }

        return queryExecutor.executeQuery(query, rowsParsed);
    }

    @GetMapping("/index")
    public String buildIndex() throws SolrServerException, IOException {
        SolrDomainIndexer.buildIndex(solrClient);

        return "Index built!";
    }
}
