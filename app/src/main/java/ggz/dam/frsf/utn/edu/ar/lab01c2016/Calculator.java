package ggz.dam.frsf.utn.edu.ar.lab01c2016;

public final class Calculator {
    public static Double calculate_interest(Double initial_amount, Integer term_in_days) {
        Double interest;
        String rate;
        if (term_in_days < R.integer.long_term_threshold_days) {
            if (initial_amount > R.integer.tier_2_upper_amount) {
                rate = String.valueOf(R.string.tier_3_short_term_rate);
            } else if (initial_amount > R.integer.tier_1_upper_amount) {
                rate = String.valueOf(R.string.tier_2_short_term_rate);
            } else if (initial_amount > 0) {
                rate = String.valueOf(R.string.tier_1_short_term_rate);
            }
        } else {
            if (initial_amount > R.integer.tier_2_upper_amount) {
                rate = String.valueOf(R.string.tier_3_long_term_rate);
            } else if (initial_amount > R.integer.tier_1_upper_amount) {
                rate = String.valueOf(R.string.tier_3_long_term_rate);
            } else {
                rate = String.valueOf(R.string.tier_3_long_term_rate);
            }
        }

        interest = initial_amount * (Math.pow(1 + Double.parseDouble(rate) / 100, (double) term_in_days / 360) - 1);
        return interest;
    }
}
