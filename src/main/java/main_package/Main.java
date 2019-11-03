package main_package;

import com.beust.jcommander.JCommander;
import com.fasterxml.jackson.databind.ObjectMapper;
import commands.InputFileCommand;
import commands.RandomProcessCommand;
import process.ProcessImpl;
import process.Process;
import schedulers.CpuSchedulingAlgorithm;
import schedulers.RoundRobinScheduler;
import schedulers.ShortestJobFirstScheduler;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args){
        ObjectMapper objectMapper = new ObjectMapper();
        CpuSchedulingAlgorithm shortestJobFirst = new ShortestJobFirstScheduler();
        RoundRobinScheduler roundRobin = new RoundRobinScheduler();
        ArrayList<Process> processList = new ArrayList<>(20);
        InputFileCommand inputFileCommand = new InputFileCommand();
        RandomProcessCommand randomProcessCommand = new RandomProcessCommand();
        JCommander jCommander = JCommander.newBuilder()
                .addCommand(inputFileCommand)
                .addCommand(randomProcessCommand)
                .build();
        jCommander.parse(args);

        if(randomProcessCommand.getRandomProcessNumber() > 0){
            Random rand = new Random();
            int numberOfProcesses = rand.nextInt(20);

            for(int i = 0; i < numberOfProcesses; i++){
                processList.add(new ProcessImpl(i+1, rand.nextInt(20), rand.nextInt(10)));
            }
        } else if(inputFileCommand.getJsonFileName() != null){
            try {
                byte[] json = Files.readAllBytes(Paths.get(inputFileCommand.getJsonFileName()));
                processList = objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, ProcessImpl.class));
            } catch(Exception e){
                System.err.print("ERROR: file could not be found.\n\n" + e.getMessage());
            }
        }

        shortestJobFirst.setProcesses(processList);
        shortestJobFirst.runAlgorithm();
        String gantChart = shortestJobFirst.getGanttChart();

        System.out.println("Gantt Chart with SJF algorithm:\n\n" + gantChart);

        roundRobin.setProcesses(processList);
        if(randomProcessCommand.getTimeQuantum() != 0){
            roundRobin.setTimeQuantum(randomProcessCommand.getTimeQuantum());
        } else if (inputFileCommand.getTimeQuantum() != 0){
            roundRobin.setTimeQuantum(randomProcessCommand.getTimeQuantum());
        }

        roundRobin.runAlgorithm();
        gantChart = roundRobin.getGanttChart();

        System.out.println("Gantt Chart with Round Robin algorithm:\n\n" + gantChart);
    }
}
