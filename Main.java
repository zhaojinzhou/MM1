public class Main {

	public static void main(String[] args) {
		int a = 0;
		int zzz[]=new int[2000];
		double lambda = 0.3, u = 1;
	for (int j=0;j<10000;j++)
		{Simulation sim = new Simulation(lambda, u);
		sim.simulation();
		for(int i = 0;i<Simulation.b.length;i++){
			//System.out.println(Simulation.b[i]);
			if ((5*i)<Simulation.b.length) a+=Simulation.b[5*i];
		}
		zzz[(int)((a-340000)/10)]++;
		//System.out.println(a);
		for(int i = 0;i<Simulation.b.length;i++)
		{
		Simulation.b[i]=0;}
		a=0;
		
		}		
	for (int z=0;z<zzz.length;z++){
		System.out.println(zzz[z]);
	}
		
		
	}
}
