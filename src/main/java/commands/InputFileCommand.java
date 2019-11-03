package commands;


import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(commandNames = "inputFile", commandDescription = "Specifies that there is an input file containing the processes to be simulated.")
public class InputFileCommand {

    @Parameter(names = {"-t", "--text-file"}, description = "Specifies that the input file is a text file.")
    String textFileName = null;

    @Parameter(names = {"-j", "--json-file"}, description = "Specifies that the input file is a json file.")
    String jsonFileName = null;

    @Parameter(names = {"-t", "--time-quantum"}, description = "Sets the time quantum for round robin algorithm.")
    private int timeQuantum;

    public String getTextFileName() {
        return textFileName;
    }

    public void setTextFileName(String textFileName) {
        this.textFileName = textFileName;
    }

    public String getJsonFileName() {
        return jsonFileName;
    }

    public void setJsonFileName(String jsonFileName) {
        this.jsonFileName = jsonFileName;
    }

    public int getTimeQuantum() {
        return timeQuantum;
    }

    public void setTimeQuantum(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }
}
