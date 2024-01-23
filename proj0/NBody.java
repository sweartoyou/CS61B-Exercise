public class NBody {
    public static double readRadius(String inFile) {
        In in = new In(inFile);
        in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String inFile) {
        In in = new In(inFile);
        int num = in.readInt();
        in.readDouble();
        Planet[] planets = new Planet[num];

        for (int i = 0; i < num; i ++ ) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in. readDouble();
            String img = in.readString();
            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, img);
        }

        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        String imageToDraw = "images/starfield.jpg";

        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, imageToDraw);

        for (int i = 0; i < planets.length; i ++ ) {
            planets[i].draw();
        }

        StdDraw.enableDoubleBuffering();

        double tme;
        double[] xForces = new double[planets.length];
        double[] yForces = new double[planets.length];

        for (tme = 0; tme <= T; tme += dt) {
            for (int i = 0; i < planets.length; i ++ ) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            StdDraw.picture(0, 0, imageToDraw);

            for (int i = 0; i < planets.length; i ++ ) {
                planets[i].update(dt, xForces[i], yForces[i]);
                planets[i].draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
    }
}
