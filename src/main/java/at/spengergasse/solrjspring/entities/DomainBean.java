package at.spengergasse.solrjspring.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.solr.client.solrj.beans.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DomainBean {
    @Field("name")
    String name;
}