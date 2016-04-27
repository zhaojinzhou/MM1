import java.util.LinkedList;
import java.util.Queue;

public class Simulation {
	
	private final int CUSTOMERARRIVAL = 1;
	private final int CUSTOMERDEPARTURE = 2;
	private final int SIMULATIONNUMBER = 500000;
	
	
	private double lambda, u; //arrival rate and service rate

	private int numberOfDepartures = 0;   //服务完成人数
	
	private Queue<Customer> customers = new LinkedList<Customer>(); //buffer
	
	private double systemTime = 0;  //当前系统时间
	//事件时间节点
	private double customerArrivalTime = 0, customerDepartureTime = 0;
	//事件时间间隔
	private double customerArrivalInterval = 0, customerDepartureInterval = 0;
	
	private int lastDepartureQueueLenght = 0;        //上次服务完队长
	private double lastDepartureTime = 0;            //上次服务完系统时间
	
	private double totalQueueLength = 0;
	public static int b[]=new int[30];

	public Simulation(double lambda, double u) {	
		this.lambda = lambda;
		this.u = u;
	}
	
	public void simulation() {
		
		initialization(); //初始化
		
		while (numberOfDepartures < SIMULATIONNUMBER){
			
			int next = findNext(customerArrivalTime, customerDepartureTime, customers.size());
			
			if (next == CUSTOMERARRIVAL){ //最先发生的事件是顾客到达
				
				systemTime = customerArrivalTime; //更新系统时间
					
				Customer tmp = new Customer();
				tmp.setArriveTime(systemTime);                   //记录到达时间
				tmp.setArrivalQueueLength(customers.size());     //记录到达时的队长
				b[customers.size()]++;
				
				customers.add(tmp);

				customerArrivalInterval = exponential(lambda); //更新下一次到达时间
				customerArrivalTime = systemTime + customerArrivalInterval;
				
				if (customers.size() == 1) {		
					customerDepartureInterval = serviceInterval(); //更新下一次服务结束
					customerDepartureTime = systemTime + customerDepartureInterval; 
				}
			}
				
			if (next == CUSTOMERDEPARTURE){ //最先发生的事件是服务结束
				
				systemTime = customerDepartureTime;   //更新系统时间
					
				Customer tmp = customers.remove(); 	  //从队列中移出
				tmp.setDepartureTime(systemTime);                //记录离去时间
				tmp.setDepartureQueueLength(customers.size());   //记录离去时队长
					
				if (tmp.getArrivalQueueLength() == 0){ 
					//到达时队列为空，到达即开始服务
					tmp.setStartServiceQueueLength(tmp.getArrivalQueueLength());
					tmp.setStartServiceTime(tmp.getArriveTime());				
				}else{
					//上次服务完即这次服务开始
					tmp.setStartServiceQueueLength(lastDepartureQueueLenght);
					tmp.setStartServiceTime(lastDepartureTime);
				}
				
				totalQueueLength += tmp.getArrivalQueueLength();

				lastDepartureQueueLenght = customers.size(); //更新上一次服务结束
				lastDepartureTime = systemTime;
					
				customerDepartureInterval = serviceInterval(); //更新下一次服务结束
				customerDepartureTime = systemTime + customerDepartureInterval; 
							
				numberOfDepartures ++;//离开人数增加
			}
		}
		resultOutput();
	}
	
	public void resultOutput(){	
		//System.out.print("Average Queue Length: ");
		//System.out.println(totalQueueLength / SIMULATIONNUMBER);
	}
	
	public void initialization(){
		//初始时没有顾客，不可能顾客离去
		customerArrivalInterval = exponential(lambda);
		customerArrivalTime += customerArrivalInterval;
	}
	
	public int findNext(double customerArrivalTime,double customerDepartureTime,int numberOfCustomers){	
		if (numberOfCustomers == 0) return CUSTOMERARRIVAL; //队列为空，顾客不能离去		
		else { //均有可能发生
			if (customerArrivalTime < customerDepartureTime) return CUSTOMERARRIVAL;
			else return CUSTOMERDEPARTURE;
		}
	}
	
	private double exponential(double mean) { return (-(1 / mean) * Math.log(Math.random())); }
	private double serviceInterval() { return exponential(u);}
}
