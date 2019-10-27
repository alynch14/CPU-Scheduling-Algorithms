package schedulers;

import process.Process;

import java.util.List;

public interface CpuSchedulingAlgorithm {
    public void setProcesses(List<Process> processList);
    public void runAlgorithm();
    public String getGanttChart();
}
