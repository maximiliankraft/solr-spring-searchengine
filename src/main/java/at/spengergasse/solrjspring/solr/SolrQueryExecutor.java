package at.spengergasse.solrjspring.solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

public class SolrQueryExecutor<T> {

    HttpSolrClient solrClient;
    Class<T> beanRepresentation;

    public SolrQueryExecutor(HttpSolrClient solrClient, Class<T> beanRepresentation) {
        this.solrClient = solrClient;
        this.beanRepresentation = beanRepresentation;
    }

    public SolrDocumentList executeQuery(String name, int amount) throws SolrServerException, IOException {
        SolrQuery query = new SolrQuery();
        query.set("q", "name:"+name);
        query.set("rows", amount);

        QueryResponse response = solrClient.query(query);

        SolrDocumentList docList = response.getResults();

        return response.getResults();
    }


}