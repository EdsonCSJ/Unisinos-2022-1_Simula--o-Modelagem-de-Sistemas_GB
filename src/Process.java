package src;

public class Process {
    private Integer processId; // atribuído pelo Scheduler
    private double duration; // é a duração (temporal) do processo;
                             // seu valor é calculado no início da execução da execução desta instância;
    private boolean active; // processo pode ser (des)habilitado;

    public Process(double duration) {
        this.duration = duration;
    }

    public double getDuration() {
        return this.duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public boolean isActive() {
        return this.active;
    }

    public void active(boolean active) {
        this.active = active;
    }

    public Entity executeOnStart() {return null;}
    
    public void executeOnEnd() {}
}
