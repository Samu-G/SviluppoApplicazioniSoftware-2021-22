package persistence;

import businesslogic.event.ServiceInfo;
import businesslogic.procedure.Procedure;
import businesslogic.procedure.ProcedureManager;
import businesslogic.shift.Shift;
import businesslogic.summarySheet.SummarySheet;
import businesslogic.summarySheet.SummarySheetEventReciever;
import businesslogic.summarySheet.Task;
import businesslogic.user.User;

public class PersistenceSummarySheet implements SummarySheetEventReciever {
    @Override
    public void updateSummarySheetGenerated(SummarySheet sumSheet) {
        SummarySheet.saveNewSummarySheet(sumSheet);
    }

    @Override
    public void updateSummarySheetDeleted(SummarySheet sumSheet) {
        SummarySheet.deleteSummarySheet(sumSheet);
    }

    @Override
    public void updateOpenSummarySheet(ServiceInfo service) {
        SummarySheet.loadSummarySheetByService(service);
    }

    @Override
    public void updateSummarySheetLocked(SummarySheet sumSheet) {
        SummarySheet.lockSummarySheet(sumSheet);
    }

    @Override
    public void updateSummarySheetUnlocked(SummarySheet sumSheet) {
        SummarySheet.unlockSummarySheet(sumSheet);
    }

    @Override
    public void updateTaskAdded(SummarySheet sumSheet, Task t) {
        Task.addNewTaskToSummarySheet(sumSheet, t);
    }


    @Override
    public void updateTaskDeleted(SummarySheet sumSheet, Task t) {
        Task.deleteTask(sumSheet, t);
    }

    @Override
    public void updateProcedureAdded(SummarySheet sumSheet, Procedure p) {
        ProcedureManager.addNewProcedureToTask(sumSheet, p);
    }

    @Override
    public void updateShiftAdded(Task t, Shift s) {
        Task.addNewShiftToTask(t, s);
    }

    @Override
    public void updateCookerAdded(Task t, User u) {
        Task.updateCookerToTask(t, u);
    }

    @Override
    public void updateQuantityEdited(Task t, int quantity) {
        Task.updateQuantityToTask(t, quantity);
    }

    @Override
    public void updateEstimatedTimeEdited(Task t, int estimatedTime) {
        Task.updateEstimatedTimeToTask(t, estimatedTime);
    }

    @Override
    public void updateTaskReady(Task t, boolean isReady) {
        Task.updateTaskReadyToTask(t, isReady);
    }

    @Override
    public void updateTaskCompleted(Task t, boolean isCompleted) {
        Task.updateTaskCompletedToTask(t, isCompleted);
    }

}
