package businesslogic.procedure;

public interface ProcedureEventReciver {
    void updateProcedureCreated(Procedure procedure);

    void updateProcedureDeleted(Procedure procedure);
}
