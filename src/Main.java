public class Main {
    public static void main(String[] args) throws InterruptedException {
        final int NUMBER_SWITCHES = 4;
        final int FREQUENCY_ACTIVATION = 3000;

        Box box = new Box();

        Runnable taskUp = () -> {
            int count = 0;
            while (count < NUMBER_SWITCHES) {
                try {
                    Thread.sleep(FREQUENCY_ACTIVATION);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + " включает тумблер");
                box.toggle = true;
                count++;
            }
        };

        Runnable taskDown = () -> {
            while (!Thread.currentThread().isInterrupted()) {
                if (box.toggle) {
                    System.out.println(Thread.currentThread().getName() + " выключает тумблер");
                    box.toggle = false;
                }
            }
        };

        Thread user = new Thread(null, taskUp, "Пользователь");
        user.start();
        Thread game = new Thread(null, taskDown, "Игрушка");
        game.start();

        user.join();
        game.interrupt();

    }
}
