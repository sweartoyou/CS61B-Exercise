public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double G = 6.67e-11;
    
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Planet b) {
        double dx = this.xxPos - b.xxPos;
        double dy = this.yyPos - b.yyPos;
        double r = Math.sqrt(dx * dx + dy * dy);
        return r;
    }

    public double calcForceExertedBy(Planet b) {
        double r = calcDistance(b);
        double F = G * this.mass * b.mass / (r * r);
        return F;
    }

    public double calcForceExertedByX(Planet b) {
        double F = calcForceExertedBy(b);
        double r = calcDistance(b);
        double dx = b.xxPos - this.xxPos;
        double Fx = dx / r * F;
        return Fx;
    }

    public double calcForceExertedByY(Planet b) {
        double F = calcForceExertedBy(b);
        double r = calcDistance(b);
        double dy = b.yyPos - this.yyPos;
        double Fy = dy / r * F;
        return Fy;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        int num = allPlanets.length;
        double sum = 0;
        for (int i = 0; i < num; i ++ ) {
            if (this.equals(allPlanets[i])) {
                continue;
            }
            sum += calcForceExertedByX(allPlanets[i]);
        }
        return sum;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        int num = allPlanets.length;
        double sum = 0;
        for (int i = 0; i < num; i ++ ) {
            if (this.equals(allPlanets[i])) {
                continue;
            }
            sum += calcForceExertedByY(allPlanets[i]);
        }
        return sum;
    }

    public void update(double dt, double fx, double fy) {
        double ax = fx / this.mass;
        double ay = fy / this.mass;
        double vx = this.xxVel + ax * dt;
        double vy = this.yyVel + ay * dt;
        double px = this.xxPos + vx * dt;
        double py = this.yyPos + vy * dt;

        this.xxPos = px;
        this.xxVel = vx;
        this.yyPos = py;
        this.yyVel = vy;
    }

    public void draw() {
        String imgFile = "./images/" + this.imgFileName;
        StdDraw.picture(xxPos, yyPos, imgFile);
    }
}
