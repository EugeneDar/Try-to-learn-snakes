package sample;

public class TableThread extends Thread{
    Controller controller;

    public TableThread(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                System.out.println("Thread finish");
            }
        }
    }
}
