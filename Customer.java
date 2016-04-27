
public class Customer {
	
	private double arriveTime; 	         //到达时间
	private int arrivalQueueLength;      //到达时队长
	
	private double startServiceTime;     //开始服务时间
	private int startServiceQueueLength; //开始服务时队长
	
	private double departureTime;   	 //离去时间
	private int departureQueueLength;    //离去时队长

	public double getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(double arriveTime) {
		this.arriveTime = arriveTime;
	}
	public int getArrivalQueueLength() {
		return arrivalQueueLength;
	}
	public void setArrivalQueueLength(int arrivalQueueLength) {
		this.arrivalQueueLength = arrivalQueueLength;
	}
	public double getStartServiceTime() {
		return startServiceTime;
	}
	public void setStartServiceTime(double startServiceTime) {
		this.startServiceTime = startServiceTime;
	}
	public int getStartServiceQueueLength() {
		return startServiceQueueLength;
	}
	public void setStartServiceQueueLength(int startServiceQueueLength) {
		this.startServiceQueueLength = startServiceQueueLength;
	}
	public double getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(double departureTime) {
		this.departureTime = departureTime;
	}
	public int getDepartureQueueLength() {
		return departureQueueLength;
	}
	public void setDepartureQueueLength(int departureQueueLength) {
		this.departureQueueLength = departureQueueLength;
	}
}

