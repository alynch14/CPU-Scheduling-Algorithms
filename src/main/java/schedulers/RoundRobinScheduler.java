package schedulers;

import process.Process;

import java.util.ArrayList;
import java.util.List;

public class RoundRobinScheduler implements CpuSchedulingAlgorithm {
    private List<Process> processList = new ArrayList<Process>();
    private int timeQuantum = 2;
    private int currentTime = 0;
    private int index = 0;
    private int totalTime = 0;
    public String ganttChart;

    public void setProcesses(List<Process> processList) {
        this.processList.addAll(processList);
    }

    public void setTimeQuantum(int timeQuantum){
        this.timeQuantum = timeQuantum;
    }

    public String getGanttChart(){
        return ganttChart;
    }
    public void runAlgorithm() {
        checkArrivalTimes();
        executeProcess();
    }

    private void executeProcess(){
        currentTime++;
        boolean isAlgorithmRunning = true;
        int currentProcessTimeLeft;
        int timeRunning = 0;
        boolean isFirstRound = true;

        System.out.printf("| ");
        while(isAlgorithmRunning) {
            currentProcessTimeLeft = processList.get(index).decrementBurstTime();
            timeRunning++;
            if (currentProcessTimeLeft == 0 || timeRunning == timeQuantum) {
                index = findNextUnfinishedProcessIndex();
                timeRunning = 0;
            } else if (isFirstRound && processList.get(index+1).getProcessArrivalTime() == currentTime){
                index = findNextUnfinishedProcessIndex();
            } else if(totalTime == currentTime){
                //All processes have been finished
                isAlgorithmRunning = false;
            }

            if(index == processList.size()-1){
                isFirstRound = false;
            }
        }

    }

    private int findNextUnfinishedProcessIndex(){
        boolean isNextProcessIndexFound = false;
        int i = index;
        while(!isNextProcessIndexFound){
            if(processList.get(0).getRemainingBurstTimeToExecute() != 0){
                isNextProcessIndexFound = true;
            } else if (i == processList.size()-1){
                i = 0;
            }
        }
        return i;
    }

    /**
     * Sort the list of processes by their arrival times.
     */
    private void checkArrivalTimes(){
        int arrivalTime;

        for(Process process : processList){
            arrivalTime = process.getProcessArrivalTime();
            totalTime += process.getProcessBurstTime();
            processList.remove(process);
            processList.add(arrivalTime, process);
        }
    }
}
