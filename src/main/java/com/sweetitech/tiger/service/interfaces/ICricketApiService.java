package com.sweetitech.tiger.service.interfaces;

import java.util.List;

import com.sweetitech.tiger.model.cricketapi.Data;
import com.sweetitech.tiger.model.cricketapi.Match;

public interface ICricketApiService {
	//public CricketApiLogin logIn();
	
	public Data getMatchById(String matchId);
	
	public List<Match> getResentMatches();
}
