
public class Main
{

	public static void main(String[] args)
	{

	
// OK

//		Nürnberg
//		double o1AL = 11.1325165;
//		double o1AB = 49.4047224;
//		Ingolstandt
//		double o1BL = 11.442193624023503;
//		double o1BB = 48.754382177599418;

//		Eckental
//		double o1AL = 11.2209253422;
//		double o1AB = 49.5776384211;
//		Erlangen
//		double o1BL = 10.995123015896866;
//		double o1BB = 49.555687872136865;

//		Eckental
		double o1AL = 11.2209253422;
		double o1AB = 49.5776384211;
//		Igensdorf
		double o1BL = 11.2423452958;
		double o1BB = 49.6236483973;
		
		double lat1 = Math.toRadians(o1AB);
		double lat2 = Math.toRadians(o1BB);
		double long1 = Math.toRadians(o1AL);
		double long2 = Math.toRadians(o1BL);

		System.out.println(get1(lat1, long1, lat2, long2));
		System.out.println(get2(lat1, long1, lat2, long2));
		System.out.println(get3(lat1, long1, lat2, long2));

		
	}

	public static double get1(double b1, double l1, double b2, double l2)
	{
		double deltaLong = l2 - l1;
		
		double x = Math.asin(deltaLong * Math.cos(b2));
		double y = Math.cos(b1)* Math.sin(b2) - Math.sin(b1) * Math.cos(b2) * Math.cos(deltaLong);
		
		double res = Math.toDegrees(Math.atan2(y, x));
		
		res = res < 0 ? 360 + res : res; 
		
		return res;
	}
	
	public static double get2(double b1, double l1, double b2, double l2)
	{
		double deltaPsi = Math.log(Math.tan(Math.PI / 4 + b2 / 2) / Math.tan(Math.PI / 4 + b1 / 2));
		
		double deltaLambda = l2 - l1;
		
		if(Math.abs(deltaLambda) > Math.PI) deltaLambda = deltaLambda > 0 ? -(2 * Math.PI - deltaLambda) : (2 * Math.PI + deltaLambda);
		
		double res =  Math.toDegrees(Math.atan2(deltaLambda, deltaPsi));
		
		res = res < 0 ? 360 + res : res; 
				
		return res;
	}
	
	public static double get3(double b1, double l1, double b2, double l2)
	{
		double deltaLong = Math.toRadians(l2 - l1);
		
		double lat = Math.sin(deltaLong) * Math.cos(b2);
		double dLong = Math.cos(b1) * Math.sin(b2) - Math.sin(b1) * Math.cos(b2) * Math.cos(deltaLong);
		
		return Math.toDegrees(Math.atan2(dLong, lat));
	}
}
