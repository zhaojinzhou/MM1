
public class Customer {
	
	private double arriveTime; 	         //����ʱ��
	private int arrivalQueueLength;      //����ʱ�ӳ�
	
	private double startServiceTime;     //��ʼ����ʱ��
	private int startServiceQueueLength; //��ʼ����ʱ�ӳ�
	
	private double departureTime;   	 //��ȥʱ��
	private int departureQueueLength;    //��ȥʱ�ӳ�

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

