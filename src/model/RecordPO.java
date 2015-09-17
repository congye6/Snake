package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RecordPO implements Serializable{

	private int first;
	private int second;
	private int third;
	
	
	public void addRecord(int newRecord){
		if(newRecord>third&&newRecord<=second){
			third=newRecord;
		}else if (newRecord>second&&newRecord<=first) {
			third=second;
			second=newRecord;
		}else if(newRecord>first){
			third=second;
			second=first;
			first=newRecord;
		}
		System.out.println("first="+first);
		System.out.println("second="+second);
		System.out.println("third="+third);
	}
	
	public int getFirst() {
		return first;
	}
	public int getSecond() {
		return second;
	}
	public int getThird() {
		return third;
	}
	
}
