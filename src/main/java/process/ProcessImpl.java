package process;

public class ProcessImpl implements Process {
    private int processId;
    private int processBurstTime;
    private int processArrivalTime;
    private int remainingBurstTimeToExecute;

    public void setProcessId(int id) {
        processId = id;
    }

    public void setProcessBurstTime(int burstTime) {
        processBurstTime = burstTime;
        remainingBurstTimeToExecute = burstTime;
    }

    public void setProcessArrivalTime(int arrivalTime) {
        processArrivalTime = arrivalTime;
    }

    public int getProcessId() {
        return processId;
    }

    public int getProcessBurstTime() {
        return processBurstTime;
    }

    public int getProcessArrivalTime() {
        return processArrivalTime;
    }

    public int getRemainingBurstTimeToExecute(){
        return remainingBurstTimeToExecute;
    }

    public int decrementBurstTime(){
        remainingBurstTimeToExecute--;
        return remainingBurstTimeToExecute;
    }
}
