package process;

public interface Process{
    public void setProcessId(int id);
    public void setProcessBurstTime(int burstTime);
    public void setProcessArrivalTime(int arrivalTime);
    public int getProcessId();
    public int getProcessBurstTime();
    public int getProcessArrivalTime();
}