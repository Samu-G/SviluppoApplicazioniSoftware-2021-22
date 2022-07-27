package businesslogic.summarySheet;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.event.EventInfo;
import businesslogic.event.ServiceInfo;
import businesslogic.procedure.Procedure;
import businesslogic.shift.Shift;
import businesslogic.user.User;
import persistence.PersistenceManager;
import persistence.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SummarySheetManager {

    private SummarySheet currentSummarySheet;

    private final ArrayList<SummarySheetEventReciever> eventReceivers;

    public SummarySheetManager() {
        eventReceivers = new ArrayList<>();
    }

    public SummarySheet createSummarySheet(String name, ServiceInfo serviceCorrelated) throws UseCaseLogicException {

        User user = CatERing.getInstance().getUserManager().getCurrentUser();

        if (!user.isChef()) {
            throw new UseCaseLogicException();
        }

        EventInfo eventInfoCorrelated = CatERing.getInstance().getEventManager().getEventInfoByService(serviceCorrelated);

        if (eventInfoCorrelated == null || eventInfoCorrelated.getOrganizer().getId() != user.getId()) {
            throw new UseCaseLogicException();
        }

        SummarySheet s = new SummarySheet(name, user, serviceCorrelated);

        this.notifySummarySheetCreated(s);

        return s;
    }

    public void deleteSummarySheet(SummarySheet sumSheet) throws UseCaseLogicException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();

        if (!user.isChef()) {
            throw new UseCaseLogicException();
        }

        if (sumSheet == null || sumSheet.isLocked() || sumSheet.getOwner() != user) {
            throw new UseCaseLogicException();
        }

        this.notifySummarySheetDeleted(sumSheet);
    }

    public void lockSummarySheet(SummarySheet sumSheet) throws UseCaseLogicException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();

        if (!user.isChef()) {
            throw new UseCaseLogicException();
        }

        if (sumSheet == null || sumSheet.isLocked() || sumSheet.getOwner() != user) {
            throw new UseCaseLogicException();
        }

        this.notifySummarySheetLocked(sumSheet);
    }

    public void unlockSummarySheet(SummarySheet sumSheet) throws UseCaseLogicException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();

        if (!user.isChef()) {
            throw new UseCaseLogicException();
        }

        if (sumSheet == null || sumSheet.getOwner() != user) {
            throw new UseCaseLogicException();
        }

        this.notifySummarySheetUnlocked(sumSheet);
    }

    public void openSummarySheet(ServiceInfo serviceCorrelated) throws UseCaseLogicException {
        if (serviceCorrelated == null) {
            throw new UseCaseLogicException();
        }

        this.notifyOpenSummarySheet(serviceCorrelated);
    }

    public void setCurrentSummarySheet(SummarySheet sumSheet) throws UseCaseLogicException {
        if (sumSheet != null && sumSheet.isLocked()) {
            throw new UseCaseLogicException();
        }
        this.currentSummarySheet = sumSheet;
    }

    public SummarySheet getCurrentSummarySheet() throws UseCaseLogicException {
        if (this.currentSummarySheet == null) {
            System.out.print("Errore: nessun foglio riepilogativo aperto! ");
            throw new UseCaseLogicException();
        } else {
            return this.currentSummarySheet;
        }
    }

    public void addProcedure(Procedure procedure) throws UseCaseLogicException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();

        if (!user.isChef()) {
            throw new UseCaseLogicException();
        }

        if (currentSummarySheet == null || currentSummarySheet.isLocked() || currentSummarySheet.getOwner() != user) {
            throw new UseCaseLogicException();
        }

        Task taskInEdit = currentSummarySheet.getTaskInEdit();

        if (taskInEdit == null) {
            throw new UseCaseLogicException();
        }

        this.notifyProcedureAdded(taskInEdit, procedure);

        this.updateProcedure(taskInEdit);
    }

    public void removeProcedure(Procedure procedure) throws UseCaseLogicException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();

        if (!user.isChef()) {
            throw new UseCaseLogicException();
        }

        if (currentSummarySheet == null || currentSummarySheet.getOwner() != user || currentSummarySheet.isLocked()) {
            throw new UseCaseLogicException();
        }

        Task taskInEdit = currentSummarySheet.getTaskInEdit();

        if (taskInEdit == null) {
            throw new UseCaseLogicException();
        }

        this.notifyProcedureRemoved(taskInEdit, procedure);

        this.updateProcedure(taskInEdit);
    }

    public void updateProcedure(Task taskInEdit) {
        int taskInEditId = taskInEdit.getId();

        String query = "SELECT * FROM procedures WHERE task_id = '" + taskInEditId + "'";

        taskInEdit.getProcedures().clear();

        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {

                do {
                    taskInEdit.addProcedure(new Procedure(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("instruction"),
                            rs.getString("tag"),
                            rs.getInt("time"),
                            rs.getInt("quantity"),
                            User.loadUserById(rs.getInt("owner_id")),
                            rs.getInt("procedure_type")
                    ));
                } while (rs.next());
            }
        });
    }

    public Task createTask(String name, boolean completed, int quantity, int estimatedTime, boolean ready, User cooker) throws UseCaseLogicException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();

        if (!user.isChef()) {
            throw new UseCaseLogicException();
        }

        if ((currentSummarySheet == null) || currentSummarySheet.isLocked() || currentSummarySheet.getOwner() != user) {
            throw new UseCaseLogicException();
        }

        Task t = new Task(name, completed, quantity, estimatedTime, ready, cooker);

        this.notifyTaskAdded(this.currentSummarySheet, t);

        this.updateTasks(this.currentSummarySheet);

        return t;
    }

    public void deleteTask(Task t) throws UseCaseLogicException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();

        if (!user.isChef()) {
            throw new UseCaseLogicException();
        }

        if ((currentSummarySheet == null) || currentSummarySheet.isLocked() || currentSummarySheet.getOwner() != user) {
            throw new UseCaseLogicException();
        }

        this.notifyTaskDeleted(this.currentSummarySheet, t);

        this.updateTasks(this.currentSummarySheet);

        this.currentSummarySheet.selectTaskToEdit(t);
    }

    public void arrangeTasks() throws UseCaseLogicException {
        if (currentSummarySheet == null || currentSummarySheet.isLocked()) {
            throw new UseCaseLogicException();
        }

        currentSummarySheet.sortTask();
    }

    public void setSelectedTask(Task t) throws UseCaseLogicException {
        if (currentSummarySheet == null) {
            throw new UseCaseLogicException();
        }

        this.currentSummarySheet.selectTaskToEdit(t);
    }

    public void updateTasks(SummarySheet summarySheet) {
        int sum_sheet_id = summarySheet.getId();

        String query = "SELECT * FROM tasks WHERE sum_sheet_id = '" + sum_sheet_id + "'";

        summarySheet.getTasks().clear();

        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                do {
                    summarySheet.getTasks().add(new Task(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getBoolean("completed"),
                            rs.getInt("quantity"),
                            rs.getInt("estimated_time"),
                            rs.getBoolean("ready"),
                            User.loadUserById(rs.getInt("cook_id"))
                    ));
                } while (rs.next());
            }
        });
    }

    public Task getSelectedTask() {
        return this.currentSummarySheet.getTaskInEdit();
    }

    public void assignShiftToTaskInEdit(Shift shift) throws UseCaseLogicException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();

        if (!user.isChef()) {
            throw new UseCaseLogicException();
        }

        if (currentSummarySheet == null || shift == null) {
            throw new UseCaseLogicException();
        }

        Task taskInEdit = currentSummarySheet.getTaskInEdit();

        if (taskInEdit == null) {
            throw new UseCaseLogicException();
        }

        this.notifyShiftAdded(taskInEdit, shift);

        this.updateShifts(taskInEdit);
    }


    public void setCookerToTaskInEdit(User cooker) throws UseCaseLogicException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();

        if (!user.isChef()) {
            throw new UseCaseLogicException();
        }

        if (currentSummarySheet == null || cooker == null) {
            throw new UseCaseLogicException();
        }

        Task taskInEdit = currentSummarySheet.getTaskInEdit();

        if (taskInEdit == null) {
            throw new UseCaseLogicException();
        }

        this.notifyCookerUpdated(taskInEdit, cooker);

        this.updateCooker(cooker);
    }


    public void setQuantityToTaskInEdit(int quantity) throws UseCaseLogicException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();

        if (!user.isChef()) {
            throw new UseCaseLogicException();
        }

        if (currentSummarySheet == null || quantity < 0) {
            throw new UseCaseLogicException();
        }

        Task taskInEdit = currentSummarySheet.getTaskInEdit();

        if (taskInEdit == null) {
            throw new UseCaseLogicException();
        }

        this.notifyQuantityUpdated(taskInEdit, quantity);

        this.updateQuantity(quantity);
    }


    public void setEstimatedTimeToTaskInEdit(int estimatedTime) throws UseCaseLogicException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();

        if (!user.isChef()) {
            throw new UseCaseLogicException();
        }

        if (currentSummarySheet == null || estimatedTime < 0) {
            throw new UseCaseLogicException();
        }

        Task taskInEdit = currentSummarySheet.getTaskInEdit();

        if (taskInEdit == null) {
            throw new UseCaseLogicException();
        }

        this.notifyEstimatedTimeEdited(taskInEdit, estimatedTime);

        this.updateEstimatedTime(estimatedTime);
    }

    public void setReadyToTaskInEdit(boolean isReady) throws UseCaseLogicException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();

        if (!user.isChef()) {
            throw new UseCaseLogicException();
        }

        if (currentSummarySheet == null) {
            throw new UseCaseLogicException();
        }

        Task taskInEdit = currentSummarySheet.getTaskInEdit();

        if (taskInEdit == null) {
            throw new UseCaseLogicException();
        }

        this.notifyTaskReadyEdited(taskInEdit, isReady);

        this.updateTaskReady(isReady);
    }

    public void setCompletedToTaskInEdit(boolean isCompleted) throws UseCaseLogicException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();

        if (!user.isChef()) {
            throw new UseCaseLogicException();
        }

        if (currentSummarySheet == null) {
            throw new UseCaseLogicException();
        }

        Task taskInEdit = this.currentSummarySheet.getTaskInEdit();

        if (taskInEdit == null) {
            throw new UseCaseLogicException();
        }

        this.notifyTaskCompletedEdited(taskInEdit, isCompleted);

        this.updateTaskCompleted(isCompleted);
    }

    private void updateShifts(Task task) {
        int taskInEditId = task.getId();

        String query = "SELECT * FROM taskshifts WHERE task_id = '" + taskInEditId + "'";

        ArrayList<Integer> shiftsId = new ArrayList<>();

        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {

                do {
                    shiftsId.add(rs.getInt("shift_id"));
                } while (rs.next());
            }
        });

        task.getShifts().clear();

        for (int i : shiftsId) {
            task.addShift(Shift.loadShiftById(i));
        }
    }


    private void updateCooker(User u) {
        currentSummarySheet.getTaskInEdit().setCooker(User.loadUserById(u.getId()));
    }

    private void updateQuantity(int quantity) {
        currentSummarySheet.getTaskInEdit().setQuantity(quantity);
    }

    private void updateEstimatedTime(int estimatedTime) {
        currentSummarySheet.getTaskInEdit().setEstimatedTime(estimatedTime);
    }


    private void updateTaskReady(boolean b) {
        currentSummarySheet.getTaskInEdit().setTaskReady(b);
    }

    private void updateTaskCompleted(boolean b) {
        currentSummarySheet.getTaskInEdit().setTaskCompleted(b);
    }

    /* EVENT SENDER METHOD */

    public void addEventReceiver(SummarySheetEventReciever rec) {
        this.eventReceivers.add(rec);
    }

    private void removeEventReceiver(SummarySheetEventReciever rec) {
        this.eventReceivers.remove(rec);
    }


    /* NOTIFY */
    private void notifySummarySheetCreated(SummarySheet sumSheet) {
        for (SummarySheetEventReciever er : this.eventReceivers) {
            er.updateSummarySheetGenerated(sumSheet);
        }
    }

    private void notifySummarySheetDeleted(SummarySheet sumSheet) {
        for (SummarySheetEventReciever er : this.eventReceivers) {
            er.updateSummarySheetDeleted(sumSheet);
        }
    }

    private void notifyOpenSummarySheet(ServiceInfo serviceCorrelated) {
        for (SummarySheetEventReciever er : this.eventReceivers) {
            er.updateOpenSummarySheet(serviceCorrelated);
        }
    }

    private void notifySummarySheetLocked(SummarySheet sumSheet) {
        for (SummarySheetEventReciever er : this.eventReceivers) {
            er.updateSummarySheetLocked(sumSheet);
        }
    }

    private void notifySummarySheetUnlocked(SummarySheet sumSheet) {
        for (SummarySheetEventReciever er : this.eventReceivers) {
            er.updateSummarySheetUnlocked(sumSheet);
        }
    }

    private void notifyTaskAdded(SummarySheet sumSheet, Task t) {
        for (SummarySheetEventReciever er : this.eventReceivers) {
            er.updateTaskAdded(sumSheet, t);
        }
    }

    private void notifyTaskDeleted(SummarySheet sumSheet, Task t) {
        for (SummarySheetEventReciever er : this.eventReceivers) {
            er.updateTaskDeleted(sumSheet, t);
        }
    }

    private void notifyProcedureAdded(Task taskInEdit, Procedure p) {
        for (SummarySheetEventReciever er : this.eventReceivers) {
            er.updateProcedureAdded(taskInEdit, p);
        }
    }

    private void notifyProcedureRemoved(Task taskInEdit, Procedure p) {
        for (SummarySheetEventReciever er : this.eventReceivers) {
            er.updateProcedureRemoved(taskInEdit, p);
        }
    }


    private void notifyShiftAdded(Task t, Shift s) {
        for (SummarySheetEventReciever er : this.eventReceivers) {
            er.updateShiftAdded(t, s);
        }
    }

    private void notifyCookerUpdated(Task t, User u) {
        for (SummarySheetEventReciever er : this.eventReceivers) {
            er.updateCookerAdded(t, u);
        }
    }

    private void notifyQuantityUpdated(Task t, int quantity) {
        for (SummarySheetEventReciever er : this.eventReceivers) {
            er.updateQuantityEdited(t, quantity);
        }
    }

    private void notifyEstimatedTimeEdited(Task t, int estimatedTime) {
        for (SummarySheetEventReciever er : this.eventReceivers) {
            er.updateEstimatedTimeEdited(t, estimatedTime);
        }
    }

    private void notifyTaskReadyEdited(Task t, boolean isReady) {
        for (SummarySheetEventReciever er : this.eventReceivers) {
            er.updateTaskReady(t, isReady);
        }
    }

    private void notifyTaskCompletedEdited(Task t, boolean isCompleted) {
        for (SummarySheetEventReciever er : this.eventReceivers) {
            er.updateTaskCompleted(t, isCompleted);
        }
    }

}
