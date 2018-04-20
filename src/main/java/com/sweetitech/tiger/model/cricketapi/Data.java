package com.sweetitech.tiger.model.cricketapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {
	
	String card_type;
	Card card;
	
	public String getCard_type() {
		return card_type;
	}
	
	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}
	
	public Card getCard() {
		return card;
	}
	
	public void setCard(Card card) {
		this.card = card;
	}
	
}