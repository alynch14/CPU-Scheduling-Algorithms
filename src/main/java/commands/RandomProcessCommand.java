package commands;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(commandDescription = "Creates the specified amount of processes with random values for burst and arrival times, and process ids")
public class RandomProcessCommand {
    @Parameter(names = {"-n","--number-of-processes"}, description = "Number of processes the user wants in the simulation")
    private int randomProcessNumber = 0;

    public int getRandomProcessNumber() {
        return randomProcessNumber;
    }

    public void setRandomProcessNumber(int randomProcessNumber) {
        this.randomProcessNumber = randomProcessNumber;
    }
}
