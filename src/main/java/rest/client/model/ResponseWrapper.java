package rest.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseWrapper {

    private EmbeddedWrapper _embedded;
    private Object _links;
    private Object page;

    public EmbeddedWrapper get_embedded() {
        return _embedded;
    }

    public void set_embedded(EmbeddedWrapper _embedded) {
        this._embedded = _embedded;
    }

    public Object get_links() {
        return _links;
    }

    public void set_links(Object _links) {
        this._links = _links;
    }

    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
        this.page = page;
    }
}