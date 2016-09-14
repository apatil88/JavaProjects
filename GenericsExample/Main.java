package com.genericsexample;

public class Main {

	public static void main(String[] args) {
		
		FootballPlayer joe = new FootballPlayer("Joe");
		BaseballPlayer pat = new BaseballPlayer("Pat");
		SoccerPlayer beckham = new SoccerPlayer("Beckham");
		
		//Right player needs to be added the particular sport team. Example: FootballPlayer is only added to Football team.

		Team<FootballPlayer> adelaideCrows = new Team<FootballPlayer>("Adelaide Crows");
		adelaideCrows.addPlayer(joe);
		
		/** Cannot add baseball and soccer player to football player**/
		//adelaideCrows.addPlayer(pat);
		//adelaideCrows.addPlayer(beckham);
		
		Team<BaseballPlayer> baseballTeam = new Team<BaseballPlayer>("Chicago Cubs");
		baseballTeam.addPlayer(pat);
		
		Team<SoccerPlayer> soccerTeam = new Team<SoccerPlayer>("Man U");
		soccerTeam.addPlayer(beckham);
		
		Team<FootballPlayer> melbourne = new Team<FootballPlayer>("Melbourne");
		FootballPlayer banks = new FootballPlayer("Gordon");
		melbourne.addPlayer(banks);
		
		Team<FootballPlayer> hawthorn = new Team<FootballPlayer>("Hawthorn");
		Team<FootballPlayer> fremantle = new Team<FootballPlayer>("Fremantle");
		
		melbourne.matchResult(fremantle, 2, 1);
		melbourne.matchResult(adelaideCrows, 1, 2);
		
		adelaideCrows.matchResult(fremantle, 5, 2);
		adelaideCrows.matchResult(hawthorn, 2, 6);
		
		System.out.println("Rankings");
		System.out.println(adelaideCrows.getName() + " : " + adelaideCrows.ranking());
		System.out.println(fremantle.getName() + " : " + fremantle.ranking());
		System.out.println(hawthorn.getName() + " : " + hawthorn.ranking());
		System.out.println(melbourne.getName() + " : " + melbourne.ranking());
		
		System.out.println(adelaideCrows.compareTo(fremantle));
		System.out.println(melbourne.compareTo(hawthorn));
		System.out.println(fremantle.compareTo(hawthorn));
		
		
	}

}
