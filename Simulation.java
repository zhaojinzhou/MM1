import java.util.LinkedList;
import java.util.Queue;

public class Simulation {
	
	private final int CUSTOMERARRIVAL = 1;
	private final int CUSTOMERDEPARTURE = 2;
	private final int SIMULATIONNUMBER = 500000;
	
	
	private double lambda, u; //arrival rate and service rate

	private int numberOfDepartures = 0;   //�����������
	
	private Queue<Customer> customers = new LinkedList<Customer>(); //buffer
	
	private double systemTime = 0;  //��ǰϵͳʱ��
	//�¼�ʱ��ڵ�
	private double customerArrivalTime = 0, customerDepartureTime = 0;
	//�¼�ʱ����
	private double customerArrivalInterval = 0, customerDepartureInterval = 0;
	
	private int lastDepartureQueueLenght = 0;        //�ϴη�����ӳ�
	private double lastDepartureTime = 0;            //�ϴη�����ϵͳʱ��
	
	private double totalQueueLength = 0;
	public static int b[]=new int[30];

	public Simulation(double lambda, double u) {	
		this.lambda = lambda;
		this.u = u;
	}
	
	public void simulation() {
		
		initialization(); //��ʼ��
		
		while (numberOfDepartures < SIMULATIONNUMBER){
			
			int next = findNext(customerArrivalTime, customerDepartureTime, customers.size());
			
			if (next == CUSTOMERARRIVAL){ //���ȷ������¼��ǹ˿͵���
				
				systemTime = customerArrivalTime; //����ϵͳʱ��
					
				Customer tmp = new Customer();
				tmp.setArriveTime(systemTime);                   //��¼����ʱ��
				tmp.setArrivalQueueLength(customers.size());     //��¼����ʱ�Ķӳ�
				b[customers.size()]++;
				
				customers.add(tmp);

				customerArrivalInterval = exponential(lambda); //������һ�ε���ʱ��
				customerArrivalTime = systemTime + customerArrivalInterval;
				
				if (customers.size() == 1) {		
					customerDepartureInterval = serviceInterval(); //������һ�η������
					customerDepartureTime = systemTime + customerDepartureInterval; 
				}
			}
				
			if (next == CUSTOMERDEPARTURE){ //���ȷ������¼��Ƿ������
				
				systemTime = customerDepartureTime;   //����ϵͳʱ��
					
				Customer tmp = customers.remove(); 	  //�Ӷ������Ƴ�
				tmp.setDepartureTime(systemTime);                //��¼��ȥʱ��
				tmp.setDepartureQueueLength(customers.size());   //��¼��ȥʱ�ӳ�
					
				if (tmp.getArrivalQueueLength() == 0){ 
					//����ʱ����Ϊ�գ����Ｔ��ʼ����
					tmp.setStartServiceQueueLength(tmp.getArrivalQueueLength());
					tmp.setStartServiceTime(tmp.getArriveTime());				
				}else{
					//�ϴη����꼴��η���ʼ
					tmp.setStartServiceQueueLength(lastDepartureQueueLenght);
					tmp.setStartServiceTime(lastDepartureTime);
				}
				
				totalQueueLength += tmp.getArrivalQueueLength();

				lastDepartureQueueLenght = customers.size(); //������һ�η������
				lastDepartureTime = systemTime;
					
				customerDepartureInterval = serviceInterval(); //������һ�η������
				customerDepartureTime = systemTime + customerDepartureInterval; 
							
				numberOfDepartures ++;//�뿪��������
			}
		}
		resultOutput();
	}
	
	public void resultOutput(){	
		//System.out.print("Average Queue Length: ");
		//System.out.println(totalQueueLength / SIMULATIONNUMBER);
	}
	
	public void initialization(){
		//��ʼʱû�й˿ͣ������ܹ˿���ȥ
		customerArrivalInterval = exponential(lambda);
		customerArrivalTime += customerArrivalInterval;
	}
	
	public int findNext(double customerArrivalTime,double customerDepartureTime,int numberOfCustomers){	
		if (numberOfCustomers == 0) return CUSTOMERARRIVAL; //����Ϊ�գ��˿Ͳ�����ȥ		
		else { //���п��ܷ���
			if (customerArrivalTime < customerDepartureTime) return CUSTOMERARRIVAL;
			else return CUSTOMERDEPARTURE;
		}
	}
	
	private double exponential(double mean) { return (-(1 / mean) * Math.log(Math.random())); }
	private double serviceInterval() { return exponential(u);}
}
