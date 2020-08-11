public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static final double gravitational=6.67e-11;
	public Planet(double xP, double yP, double xV,
              double yV, double m, String img){
		xxPos=xP;
		yyPos=yP;
		xxVel=xV;
		yyVel=yV;
		mass=m;
		imgFileName=img;
	}
	public Planet(Planet p){
		xxPos=p.xxPos;
		yyPos=p.yyPos;
		xxVel=p.xxVel;
		yyVel=p.yyVel;
		mass=p.mass;
		imgFileName=p.imgFileName;
	}
	public static double abs(double x){
		return (x<0) ? -x : x;
	}
	public double calcDistance(Planet p){
		return Math.sqrt((xxPos-p.xxPos)*(xxPos-p.xxPos)+(yyPos-p.yyPos)*(yyPos-p.yyPos));
	}
	public double calcForceExertedBy(Planet p){
		return gravitational*mass*p.mass/(calcDistance(p)*calcDistance(p));
	}
	public double calcForceExertedByX(Planet p){
		return calcForceExertedBy(p)*(p.xxPos-xxPos)/calcDistance(p);
	}
	public double calcForceExertedByY(Planet p){
		return calcForceExertedBy(p)*(p.yyPos-yyPos)/calcDistance(p);
	}
	public double calcNetForceExertedByX(Planet[] p){
		double sumForcex=0;
		for(int i=0;i<p.length;i++){
			if(!this.equals(p[i])){
				sumForcex=sumForcex+calcForceExertedByX(p[i]);
			}
		}
		return sumForcex;
	}
	public double calcNetForceExertedByY(Planet[] p){
				double sumForcey=0;
		for(int i=0;i<p.length;i++){
			if(!this.equals(p[i])){
				sumForcey=sumForcey+calcForceExertedByY(p[i]);
			}
		}
		return sumForcey;
	}
	public void update(double dt,double xForce,double yForce){
		xxVel=xxVel+xForce*dt/mass;
		yyVel=yyVel+yForce*dt/mass;
		xxPos=xxPos+xxVel*dt;
		yyPos=yyPos+yyVel*dt;
	}
	public void draw(){
		StdDraw.picture(xxPos, yyPos, "./images/"+imgFileName);
	}
}