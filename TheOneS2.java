import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

interface WatchState {
    public void Button_A(WatchStateContext ctx);

    public void Button_B();

    public void Button_C();
}

class WatchStateContext {
    private WatchState currentState;
    private WatchState[] States = { new TimeMode(), new DateMode(), new StoperMode(), new BrandMode() };

    public WatchStateContext() {
        currentState = States[0];
    }

    public void setState(WatchState state) {
        currentState = state;
    }

    public void Button_A() {
        currentState.Button_A(this);
    }

    public void Button_B() {
        currentState.Button_B();
    }

    public void Button_C() {
        currentState.Button_C();
    }

    public WatchState getState(int index) {
        return States[index];
    }
}

class TimeMode implements WatchState {
    private DateFormat dateFormat;

    public TimeMode() {
        dateFormat = new SimpleDateFormat("hh:mm a");
    }

    @Override
    public void Button_A(WatchStateContext ctx) {
        ctx.setState(ctx.getState(1));

    }

    @Override
    public void Button_B() {
        System.out.println(dateFormat.format(new Date()));

    }

    @Override
    public void Button_C() {
        System.out.println("Light");

    }

}

class DateMode implements WatchState {
    private DateFormat dateFormat;

    public DateMode() {
        dateFormat = new SimpleDateFormat("EEEE");
    }

    @Override
    public void Button_A(WatchStateContext ctx) {
        ctx.setState(ctx.getState(2));
    }

    @Override
    public void Button_B() {
        System.out.println(Calendar.getInstance().get(Calendar.YEAR));

    }

    @Override
    public void Button_C() {
        System.out.println(dateFormat.format(new Date()));

    }

}

class StoperMode implements WatchState {

    @Override
    public void Button_A(WatchStateContext ctx) {
        ctx.setState(ctx.getState(3));

    }

    @Override
    public void Button_B() {

    }

    @Override
    public void Button_C() {

    }

}

class BrandMode implements WatchState {

    @Override
    public void Button_A(WatchStateContext ctx) {
        ctx.setState(ctx.getState(0));

    }

    @Override
    public void Button_B() {
        try {
            FileWriter myWriter = new FileWriter("output.txt");
            myWriter.write("The One s2!");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public void Button_C() {
    }

}

class TheOneS2 {
    public static void main(String[] args) {
        WatchStateContext stateContext = new WatchStateContext();
        stateContext.Button_A();
        stateContext.Button_B();

    }
}