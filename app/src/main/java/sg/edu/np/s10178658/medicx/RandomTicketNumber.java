package sg.edu.np.s10178658.medicx;

import java.util.Random;

public class RandomTicketNumber {

    private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random random = new Random();

    public String generateString(int length) {
        StringBuilder builder = new StringBuilder(length);

        for (int i = 0; i <= length; i++) {
            builder.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return builder.toString();
    }

}
