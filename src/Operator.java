public enum Operator {
    CONCATENATION {
        public double action(double a, double b) {
            return a * 10 + b;
        }
    },
    ADDITION {
        public double action(double a, double b) {
            return a + b;
        }
    },
    SUBTRACTION {
        public double action(double a, double b) {
            return a - b;
        }
    },
    MULTIPLICATION {
        public double action(double a, double b) {
            return a * b;
        }
    },
    DIVISION {
        public double action(double a, double b) {
            return a / b;
        }
    };
    public abstract double action(double a, double b);
}
