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
        String times = "0";
        int lastProcessBurstTime = 0;

        sortData();
        for(Process process : processList){
            mainChart.append(process.getProcessId()).append("|");
//            times += " " +
        }
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
