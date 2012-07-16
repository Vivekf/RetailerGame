import java.util.Random;
import java.util.*;

public class SalesSeason {

	//Fixed Session Params
	public int[][] demand;
	public double[] lift;
	private double demand_seed;
	public int no_weeks;
	public int no_prices;
	private double scale;
	private double[] price;
	
	//state variables
	public int capacity;
	public int max_capacity;
	private int price_level;
	private int current_week;
	private int current_demand;
	private double revenue;
	
	//state trajectory
	public Vector inventory_path;
	public Vector revenue_path;	
	
	
	public SalesSeason() {
		initialize(100000.0*Math.random());
	}
	
	public SalesSeason(double seed){
		initialize(seed);
	}
	
	private void initialize(double seed){
		
		// Initialize Session Params
		demand_seed = seed;
		no_weeks = 16;
		no_prices = 4;
		max_capacity = 2000;
		capacity = max_capacity;
		
		lift = new double[no_prices];
			//Tailored Settings
			lift[0]=1.0;
			lift[1]=1.3;
			lift[2]=1.7;
			lift[3]=2.8;
		
		price = new double[no_prices];
			//Tailored Settings
			price[0]=100;
			price[1]=90;
			price[2]=80;
			price[3]=60;
			
			
		
		demand = new int[no_weeks][no_prices];		
		Random generator = new Random((int)demand_seed);
		
		double mean = 0.7*max_capacity;
		
		scale = Math.abs(generator.nextGaussian()*0.6*mean+mean);		
		for (int i=0; i<no_weeks; i++){
			for(int j=0; j< no_prices; j++){
				mean = lift[j]*scale/(double)no_weeks;
				demand[i][j] = (int)Math.max(
						generator.nextGaussian()*0.2*mean +mean,
						0
						);
			}
		}
		
		//State Variables
		price_level = 1;
		current_week = 1;
		current_demand = demand[current_week-1][price_level-1];
		inventory_path = new Vector();
		revenue_path = new Vector();		
		
		
		
		int sales = (int)Math.min(current_demand,capacity);
		capacity -= sales;	
		inventory_path.addElement(new Integer(capacity));
		revenue += sales*price[price_level-1];
		revenue_path.addElement(new Integer((int)(revenue/1000)));
	}
	
	public void lower_price(int level){
		price_level = level;
		update_state();
	}
	
	public void same_price(){
		update_state();	
	}
	
	
	public void update_state(){
		if(current_week < no_weeks){
			current_week = current_week+1;
			current_demand = demand[current_week-1][price_level-1];
			double sales = (int)Math.min(current_demand,capacity);
			capacity -= sales;	
			inventory_path.addElement(new Integer((int)capacity));
			revenue += sales*price[price_level-1];	
			revenue_path.addElement(new Integer((int)(revenue/1000)));	
		}
	}
	
	
	public double computeMax(){
		
		double max_revenue = 0;
		
		for(int switch2 = 2; switch2 <= no_weeks+1; switch2++){
			for(int switch3 =2; switch3 <= no_weeks+1; switch3++){
				for(int switch4=2; switch4 <= no_weeks+1; switch4++){
					if(switch4>=switch3 && switch3>=switch2){
						double thisrev = getSimRevenue(switch2,switch3,switch4);
						max_revenue = Math.max(max_revenue, thisrev);
					}
				}
			}
		}
		
		
		return max_revenue;
	}
	
	
	public double getSimRevenue(int s2,int s3,int s4){
		
		int phantom_capacity = max_capacity;
		int phantom_rev = 0;
		int phantom_level = 1;
		
		for(int t=1;t<=no_weeks;t++){
			if(t>=s4){
				phantom_level = 4;
			}
			else if(t<s4 && t >= s3){
				phantom_level = 3;
			}
			else if(t<s3 && t >= s2){
				phantom_level = 2;
			}
			else{
				phantom_level = 1;
			}
			
			double phantom_demand = demand[t-1][phantom_level-1];
			double phantom_sales = (int)Math.min(phantom_demand,phantom_capacity);
			phantom_capacity -= phantom_sales;	
			phantom_rev += phantom_sales*price[phantom_level-1];				
		}
		
		return phantom_rev;
	}
	
	
	public Boolean ended(){
		if(current_week == no_weeks){
			return true;
		}
		else{
			return false;
		}
	}
	
	public double getRevenue(){
		return revenue;
	}
	
	public double getPriceLevel(){
		return price[price_level-1];
	}
	
	public int getCurrentWeek(){
		return current_week;
	}
	
	public int getCurrentDemand(){
		return (int)current_demand;
	}
	
	public int[] getInventoryTrajectory(){
		int[] traj = new int[inventory_path.size()];
		for(int j=0; j<traj.length; j++){
			traj[j]=((Integer)inventory_path.get(j)).intValue();
		}
		return traj;
	}

	
	public int[] getRevenueTrajectory(){
		int[] traj = new int[revenue_path.size()];
		for(int j=0; j<traj.length; j++){
			traj[j]=((Integer)revenue_path.get(j)).intValue();
		}
		return traj;
	}
	
	public int getCapacity(){
		return capacity;
	}
	
	
	
	

}
