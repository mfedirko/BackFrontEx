package com.nouhoun.springboot.jwt.integration.domain;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class AccountDeserializer extends JsonDeserializer<Account> {
	
	    @Override
	    public Account deserialize(JsonParser jp, DeserializationContext ctxt) throws 
	            IOException, JsonProcessingException {
	    	System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww!!!!!!!!!!");
	        Account account = new Account();
	        JsonNode node = jp.readValueAsTree();
	        JsonNode user = node.get("user");
	        account.setId(Long.parseLong(user.get("id").asText()));
	       
	        return account;
	  
	}
}
