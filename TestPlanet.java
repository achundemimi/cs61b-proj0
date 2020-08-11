
public class TestPlanet{
	public static void main(String[] args){
		Planet Jupiter = new Planet(0,0,1,1,100,"Jupiter");
		Planet Mars = new Planet(10,10,2,2,50,"Mars");
		double force = Jupiter.calcForceExertedBy(Mars);
		double expected = Planet.gravitational*100*50/(10*10+10*10);
		System.out.println("expected: "+expected+" force: "+force);
	}
}