package com.baeldung.commons.math;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.AllowedSolution;
import org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver;
import org.apache.commons.math3.analysis.solvers.UnivariateSolver;

public class RootFindingExample {

    public void run() {
        final UnivariateFunction function = v -> Math.pow(v, 2) - 2;
        UnivariateSolver solver = new BracketingNthOrderBrentSolver(1.0e-12, 1.0e-8, 5);
        double c = solver.solve(100, function, -10.0, 10.0, 0);

        System.out.println(c);
    }

}
