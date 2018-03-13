package com.example.planegame.model;

import java.util.ArrayList;
import java.util.List;

public class UserInfo {
	private String name;//�û���
	private int military;//���μ���
	private int money;//�����
	private List<Integer> ownPlanes = new ArrayList<Integer>();//��ӵ�еķɻ��ͺ�
	private int map;//�ѽ����ĵ�ͼ�ؿ�
	
	public UserInfo(){
		
	}
	public UserInfo(String name,int military,int money,List<Integer> ownPlanes,int map){
		this.name = name;
		this.money = money;
		this.ownPlanes = ownPlanes;
		this.military = military;
		this.map = map;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMilitary() {
		return military;
	}
	public void setMilitary(int military) {
		this.military = military;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public List<Integer> getOwnPlanes() {
		return ownPlanes;
	}
	public void setOwnPlane(List<Integer> ownPlanes) {
		this.ownPlanes = ownPlanes;
	}
	public int getMap() {
		return map;
	}
	public void setMap(int map) {
		this.map = map;
	}
	
	public void addPlanes(int planeNumber){
		ownPlanes.add(planeNumber);
	}
	@Override
	public String toString() {
		return "UserInfo [name=" + name + ", military=" + military + ", money="
				+ money + ", ownPlanes=" + ownPlanes + ", map=" + map + "]";
	}
	
}
