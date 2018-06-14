package speltesting;

public class SimpleBeanB
{
	private String secondValue;
	private double randomValue;

	public int getRandomValue() {
		return (int)randomValue;
	}

	public void setRandomValue(int randomValue) {
		this.randomValue = randomValue;
	}

	public String getSecondValue() {
		return secondValue;
	}

	public void setSecondValue(String secondValue) {
		this.secondValue = secondValue;
	}
	
}
