class ClockDisplay {

	NumberDisplay hours;
	NumberDisplay minutes;

	public ClockDisplay(NumberDisplay hours, NumberDisplay minutes) {
		this.hours = hours;
		this.minutes = minutes;
	}
}

class NumberDisplay {

	int limit;
	int value;

	// limit e' l'ultimo dei valori accettati
	// ad es per ore limit = 23
	public NumberDisplay(int limit, int value) {
		this.limit = limit;
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int val) {
		if (value >= 0 && value <= limit) {
			value = val;
		}
	}

	public String getDisplayValue() {
		// return value < 10 ? "0" + value : value.toString();

		if (value < 10) {
			return "0" + value;
		} 
		return value.toString();
	}

	public void increment() {
		if (value + 1 > limit) {
			value = 0;
		} else {
			value++;
		}
	}
}

class Test {

	public void static main(String[] args) {
		NumberDisplay h = new NumberDisplay(11, 0);
		NumberDisplay m = new NumberDisplay(59, 0);
		ClockDisplay clock = new ClockDisplay(h, m);
	}
}





