package businesslogic.procedure;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.summarySheet.SummarySheet;
import businesslogic.summarySheet.Task;
import persistence.PersistenceManager;

import java.util.ArrayList;

public class ProcedureManager {
    private Procedure openProcedure;
    private Procedure procedureToCopy;
    private final ArrayList<ProcedureEventReciver> eventReceivers;

    public ProcedureManager() {
        this.eventReceivers = new ArrayList<>();
    }

    public static void addNewProcedureToTask(SummarySheet sumSheet, Procedure p) {
        if (sumSheet.getTaskInEdit() != null) {
            String procedureName = p.getName();

            String instruction = p.getInstruction();

            int procedureType = 0;

            switch (p.getProcedureType()) {
                case "Recipe" -> procedureType = 1;
                case "Preparation" -> procedureType = 2;
            }

            int ownerId = sumSheet.getOwner().getId();

            int taskId = sumSheet.getTaskInEdit().getId();

            String insert = "INSERT procedures (name, instruction, owner_id, task_id, procedure_type) " +
                    "VALUES ('" + procedureName + "', '" + instruction + "', '" + ownerId + "', '" + taskId + "', '" + procedureType + "')";

            PersistenceManager.executeUpdate(insert);

            p.setId(PersistenceManager.getLastId());
        }
    }

    // OPERATION METHOD

    public void deleteProcedure(Procedure p) throws UseCaseLogicException {
        SummarySheet currentSummarySheet = CatERing.getInstance().getSummarySheetManager().getCurrentSummarySheet();

        if ((currentSummarySheet == null) || currentSummarySheet.isLocked() || currentSummarySheet.getTasks().isEmpty()) {
            throw new UseCaseLogicException();
        }

        this.notifyProcedureDeleted(p);

        CatERing.getInstance().getSummarySheetManager().updateProcedure(currentSummarySheet.getTaskInEdit());
    }

    // GETTER AND SETTER

    public Procedure getOpenProcedure() {
        return openProcedure;
    }

    public void setOpenProcedure(Procedure openProcedure) {
        this.openProcedure = openProcedure;
    }

    public Procedure getProcedureToCopy() {
        return procedureToCopy;
    }

    public void setProcedureToCopy(Procedure procedureToCopy) {
        this.procedureToCopy = procedureToCopy;
    }

    // EVENT SENDER METHOD

    public void addEventReceiver(ProcedureEventReciver rec) {
        this.eventReceivers.add(rec);
    }

    public void removeEventReceiver(ProcedureEventReciver rec) {
        this.eventReceivers.remove(rec);
    }

    private void notifyProcedureCreated(Procedure p, Task t) {
        for (ProcedureEventReciver er : this.eventReceivers) {
            er.updateProcedureCreated(p);
        }
    }

    private void notifyProcedureDeleted(Procedure p) {
        for (ProcedureEventReciver er : this.eventReceivers) {
            er.updateProcedureDeleted(p);
        }
    }

}
