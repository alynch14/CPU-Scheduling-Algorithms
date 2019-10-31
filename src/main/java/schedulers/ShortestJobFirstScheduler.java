package schedulers;

import process.Process;

import java.util.*;

public class ShortestJobFirstScheduler implements CpuSchedulingAlgorithm {
    private List<Process> processList = new ArrayList<Process>();
    private String ganttChart;

    public void setProcesses(List<Process> processList) {
        this.processList.addAll(processList);
    }

    public void runAlgorithm() {
        StringBuilder mainChart = new StringBuilder("|");
        StringBuilder times = new StringBuilder("0");
        int totalTime = 0;

        sortData();
        for(Process process : processList){
            totalTime += (process.getProcessBurstTime() - process.getProcessArrivalTime());
            mainChart.append(process.getProcessId()).append("|");
            times.append(" ").append(totalTime);
            process.setTurnaroundTime(process.getProcessBurstTime());
            if(process.getProcessArrivalTime() > 0){
                process.setWaitTime(totalTime - process.getProcessArrivalTime());
            }else{
                process.setWaitTime(totalTime);
            }
        }

        ganttChart = mainChart.toString() + "\n" + times.toString();
    }

    private void sortData(){
        for(Process process : processList){
            process.setProcessBurstTime((process.getProcessArrivalTime() + process.getProcessBurstTime()));
        }

        processList.sort(Comparator.comparing(Process::getProcessBurstTime));
    }

    public String getGanttChart() {
        return ganttChart;
    }
}
