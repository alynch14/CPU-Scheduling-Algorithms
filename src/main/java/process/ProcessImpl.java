package process;

public class ProcessImpl implements Process {
    private int processId;
    private int processBurstTime;
    private int processArrivalTime;
    private int remainingBurstTimeToExecute;
    private int waitTime;
    private int turnaroundTime;

    @Override
    public void setProcessId(int id) {
        processId = id;
    }

    @Override
    public void setProcessBurstTime(int burstTime) {
        processBurstTime = burstTime;
        remainingBurstTimeToExecute = burstTime;
    }

    @Override
    public void setProcessArrivalTime(int arrivalTime) {
        processArrivalTime = arrivalTime;
    }

    @Override
    public int getProcessId() {
        return processId;
    }

    @Override
    public int getProcessBurstTime() {
        return processBurstTime;
    }

    @Override
    public int getProcessArrivalTime() {
        return processArrivalTime;
    }

    @Override
    public int getRemainingBurstTimeToExecute(){
        return remainingBurstTimeToExecute;
    }

    @Override
    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    @Override
    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    @Override
    public int getWaitTime() {
        return waitTime;
    }

    @Override
    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    @Override
    public int decrementBurstTime(){
        remainingBurstTimeToExecute--;
        return remainingBurstTimeToExecute;
    }
}
