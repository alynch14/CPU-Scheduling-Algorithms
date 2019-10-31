package process;

public interface Process{
    public void setProcessId(int id);
    public void setProcessBurstTime(int burstTime);
    public void setProcessArrivalTime(int arrivalTime);
    public int getProcessId();
    public int getProcessBurstTime();
    public int getProcessArrivalTime();
    public int decrementBurstTime();
    public int getRemainingBurstTimeToExecute();
    public void setWaitTime(int waitTime);
    public void setTurnaroundTime(int turnaroundTime);
    public int getWaitTime();
    public int getTurnaroundTime();
}