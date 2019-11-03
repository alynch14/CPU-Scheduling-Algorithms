package commands;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(commandNames = "random", commandDescription = "Creates the specified amount of processes with random values for burst and arrival times, and process ids")
public class RandomProcessCommand {
    @Parameter(names = {"-n","--number-of-processes"}, description = "Number of processes the user wants in the simulation")
    private int randomProcessNumber = 0;

    @Parameter(names = {"-t", "--time-quantum"}, description = "Sets the time quantum for round robin algorithm.")
    private int timeQuantum;

    public int getRandomProcessNumber() {
        return randomProcessNumber;
    }

    public void setRandomProcessNumber(int randomProcessNumber) {
        this.randomProcessNumber = randomProcessNumber;
    }

    public int getTimeQuantum() {
        return timeQuantum;
    }

    public void setTimeQuantum(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }
}
