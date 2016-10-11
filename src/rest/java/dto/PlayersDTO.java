package dto;

import java.io.Serializable;


public class PlayersDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String team;
	String name;
	int number;
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	
}
