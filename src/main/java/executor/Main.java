package executor;

//import com.beust.jcommander.JCommander;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import commands.InputFileCommand;
//import commands.RandomProcessCommand;
import process.ProcessImpl;
import process.Process;
import schedulers.CpuSchedulingAlgorithm;
import schedulers.RoundRobinScheduler;
import schedulers.ShortestJobFirstScheduler;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
//        ObjectMapper objectMapper = new ObjectMapper();
        Scanner input = new Scanner(System.in);
        CpuSchedulingAlgorithm shortestJobFirst = new ShortestJobFirstScheduler();
        RoundRobinScheduler roundRobin = new RoundRobinScheduler();
        ArrayList<Process> sjfProcesses = new ArrayList<>(20);
        ArrayList<Process> roundRobinProcesses = new ArrayList<>(20);
//        InputFileCommand inputFileCommand = new InputFileCommand();
//        RandomProcessCommand randomProcessCommand = new RandomProcessCommand();
//        JCommander jCommander = JCommander.newBuilder()
//                .addCommand(inputFileCommand)
//                .addCommand(randomProcessCommand)
//                .build();
//        jCommander.parse(args);

        System.out.println("Would you like to randomly generate processes or use an input file?\nPlease enter either 'file' or 'random'.");
        String howToGetProcesses = input.nextLine();

        while(!(howToGetProcesses.charAt(0) == 'r' || howToGetProcesses.charAt(0) == 'f')){
            input.next();
            System.out.println("Error: invalid input.");

            System.out.println("Would you like to randomly generate processes or use an input file?\nPlease enter either 'file' or 'random'.");
            howToGetProcesses = input.nextLine();
        }

        if(howToGetProcesses.charAt(0) == 'r'){
            Random rand = new Random();
            int numberOfProcesses = rand.nextInt(19)+1;

            for(int i = 0; i < numberOfProcesses; i++){
                sjfProcesses.add(new ProcessImpl(i+1, rand.nextInt(19)+1, rand.nextInt(10)));
                roundRobinProcesses.add(new ProcessImpl(i+1, rand.nextInt(19)+1, rand.nextInt(10)));
            }
        } //else if(inputFileCommand.getJsonFileName() != null){
//            try {
//                byte[] json = Files.readAllBytes(Paths.get(inputFileCommand.getJsonFileName()));
//                processList = objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, ProcessImpl.class));
//            } catch(Exception e){
//                System.err.print("ERROR: file could not be found.\n\n" + e.getMessage());
//            }
//        }
        else if (howToGetProcesses.charAt(0) == 'f'){
            String filename;

            System.out.println("Please enter the name of the file with its extension: ");

            filename = input.nextLine();
            try {
                File processFile = new File(filename);
                Scanner fileReader = new Scanner(processFile);
                int i = 1;

                while(fileReader.hasNext()){
                    sjfProcesses.add(new ProcessImpl(i, Integer.parseInt(fileReader.next("\t")), Integer.parseInt(fileReader.next("\t"))));
                }
            } catch (FileNotFoundException e){
                System.err.println("Error: file with name '" + filename + "' does not exist.");
            }
        }
        for(Process process : sjfProcesses){
            System.out.println(process);
        }

        shortestJobFirst.setProcesses(sjfProcesses);
        shortestJobFirst.runAlgorithm();
        String gantChart = shortestJobFirst.getGanttChart();

        System.out.println("Gantt Chart with SJF algorithm:\n\n" + gantChart);

        for(Process process : sjfProcesses){
            System.out.println( process.getProcessId() + "\t" + process.getTurnaroundTime() + "\t" + process.getWaitTime());
        }

        roundRobin.setProcesses(roundRobinProcesses);
//        if(randomProcessCommand.getTimeQuantum() != 0){
//            roundRobin.setTimeQuantum(randomProcessCommand.getTimeQuantum());
//        } else if (inputFileCommand.getTimeQuantum() != 0){
//            roundRobin.setTimeQuantum(randomProcessCommand.getTimeQuantum());
//        }

        roundRobin.runAlgorithm();
        gantChart = roundRobin.getGanttChart();

        System.out.println("Gantt Chart with Round Robin algorithm:\n\n" + gantChart);

        for(Process process : roundRobinProcesses){
            System.out.println( process.getProcessId() + "\t" + process.getTurnaroundTime() + "\t" + process.getWaitTime());
        }
    }
}
