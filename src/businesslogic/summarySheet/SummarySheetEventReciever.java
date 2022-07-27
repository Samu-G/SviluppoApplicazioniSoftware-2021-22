package businesslogic.summarySheet;

import businesslogic.event.ServiceInfo;
import businesslogic.procedure.Procedure;
import businesslogic.shift.Shift;
import businesslogic.user.User;

public interface SummarySheetEventReciever {
    void updateSummarySheetGenerated(SummarySheet sumSheet);

    void updateSummarySheetDeleted(SummarySheet sumSheet);

    void updateOpenSummarySheet(ServiceInfo service);

    void updateSummarySheetLocked(SummarySheet sumSheet);

    void updateSummarySheetUnlocked(SummarySheet sumSheet);

    void updateTaskAdded(SummarySheet sumSheet, Task t);

    void updateTaskDeleted(SummarySheet sumSheet, Task t);

    void updateProcedureAdded(Task t, Procedure p);

    void updateProcedureRemoved(Task t, Procedure p);

    void updateShiftAdded(Task t, Shift s);

    void updateCookerAdded(Task t, User s);

    void updateQuantityEdited(Task t, int quantity);

    void updateEstimatedTimeEdited(Task t, int estimatedTime);

    void updateTaskReady(Task t, boolean isReady);

    void updateTaskCompleted(Task sumSheet, boolean t);

}
