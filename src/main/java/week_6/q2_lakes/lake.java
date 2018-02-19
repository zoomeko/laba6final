package week_6.q2_lakes;
import java.util.ArrayList;
import java.util.ArrayList;

public class lake {

    public class Lake {

        private String lakeName;

        private ArrayList<Double> times;

        public Lake(String lakeName) {

            this.lakeName = lakeName;

            this.times = new ArrayList<>();;

        }

        public String getLakeName() {

            return lakeName;

        }

        public void addTime(double time) {

            times.add(time);

        }

        public double getFastest() {

            if(times.size() == 0) {

                return -1;

            } else {

                double min = times.get(0);

                for(double t: times) {

                    if(t < min) {

                        min = t;

                    }

                }

                return min;

            }

        }

        public double getAverage() {

            if(times.size() == 0) {

                return -1;

            } else {

                double sum = 0.0;

                for(double t: times) {

                    sum += t;

                }

                return sum/times.size();

            }

        }

    }


}

