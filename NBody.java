public class NBody{
	public static double readRadius(String folder){
		In in=new In(folder);
		int _1st=in.readInt();
		double radius=in.readDouble();
		return radius;
	}
	public static Planet[] readPlanets(String folder){
		In in=new In(folder);
		Planet[] p=new Planet[5];
		double garbage=in.readDouble();
		double garbage1=in.readDouble();
		for(int i=0;i<5;i++){
			double xP=in.readDouble();
			double yP=in.readDouble();
			double xV=in.readDouble();
			double yV=in.readDouble();
			double mass=in.readDouble();
			String name=in.readString();
			p[i]=new Planet(xP,yP,xV,yV,mass,name);
		}
		return p;
	}
	public static void main(String[] args) {
		double T=Double.parseDouble(args[0]);
		double dt=Double.parseDouble(args[1]);
		String filename=args[2];
		Planet[] p=readPlanets(filename);
		double radius=readRadius(filename);
		String image="./images/starfield.jpg";
		double presentTime=0;


// needs to be encoded as "javac -encoding UTF-8 NBody.java"
		StdDraw.enableDoubleBuffering();
		
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(radius, radius, image);
		StdDraw.picture(-radius, radius, image);
		StdDraw.picture(-radius, -radius, image);
		StdDraw.picture(radius, -radius, image);	
		StdDraw.show();
		StdDraw.picture(0, 0, image);

		for(int i=0;i<p.length;i++){
			p[i].draw();
		}
		
		while(presentTime<T){
			double[] xForce=new double[p.length];
			double[] yForce=new double[p.length];
			for(int i=0;i<p.length;i++){
				xForce[i]=xForce[i]+p[i].calcNetForceExertedByX(p);
				yForce[i]=yForce[i]+p[i].calcNetForceExertedByY(p);
			}
			for(int i=0;i<p.length;i++){
				p[i].update(dt,xForce[i],yForce[i]);
			}
			StdDraw.picture(0, 0, image);	

			for(int i=0;i<p.length;i++){
				p[i].draw();
			}
		    StdDraw.show(10);
		    //StdDraw.pause(10);
		    presentTime=presentTime+dt;
		}
		StdOut.printf("%d\n", p.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < p.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            p[i].xxPos, p[i].yyPos, p[i].xxVel,
            p[i].yyVel, p[i].mass, p[i].imgFileName);   
		}
	}
}