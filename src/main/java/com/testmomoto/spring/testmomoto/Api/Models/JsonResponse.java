package com.testmomoto.spring.testmomoto.Api.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown= true)
public class JsonResponse {
    
    private int count;
    private String next;
    private String previous;
    private List<PokemonResponse> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<PokemonResponse> getResults() {
        return results;
    }

    public void setResults(List<PokemonResponse> results) {
        this.results = results;
    }
}
