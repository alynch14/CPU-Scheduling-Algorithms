package schedulers;

import process.Process;

import java.util.ArrayList;
import java.util.Comparator;
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
        boolean isAlgorithmRunning = true;
        int currentProcessTimeLeft;
        int timeRunning = 0;
        StringBuilder processRow = new StringBuilder("|");
        StringBuilder timeRow = new StringBuilder("0");

        while(isAlgorithmRunning) {
            currentTime++;
            currentProcessTimeLeft = processList.get(index).decrementBurstTime();
            timeRunning++;
            if (currentProcessTimeLeft == 0 || timeRunning == timeQuantum) {
                processRow.append(index).append("|");
                timeRow.append(" ").append(currentTime);
                index = findNextUnfinishedProcessIndex();
                timeRunning = 0;
            } else if(totalTime == currentTime){
                //All processes have finished
                isAlgorithmRunning = false;
            }
        }

        ganttChart = processRow.toString() + "\n" + timeRow.toString();
    }

    private int findNextUnfinishedProcessIndex(){
        boolean isNextProcessIndexFound = false;
        int i = ++index;
        while(!isNextProcessIndexFound){
            if(processList.get(i).getRemainingBurstTimeToExecute() != 0){
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

        for (Process process : processList) {
            totalTime += process.getProcessBurstTime();
        }

        processList.sort(Comparator.comparing(Process::getProcessArrivalTime));
    }
}
