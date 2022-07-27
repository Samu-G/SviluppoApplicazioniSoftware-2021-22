package businesslogic.summarySheet;

import businesslogic.procedure.Procedure;
import businesslogic.shift.Shift;
import businesslogic.user.User;
import persistence.PersistenceManager;
import persistence.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Task implements Comparable<Task> {

    private int id;

    private String name;

    private boolean completed;

    private int quantity;

    private int estimatedTime;

    private boolean ready;

    private User cooker;

    private final ArrayList<Procedure> procedures;

    private final ArrayList<Shift> shifts;

    /* constructors */

    public Task(String name, boolean completed, int quantity, int estimatedTime, boolean ready, User cooker) {
        this.name = name;
        this.completed = completed;
        this.quantity = quantity;
        this.estimatedTime = estimatedTime;
        this.ready = ready;
        this.cooker = cooker;
        this.procedures = new ArrayList<>();
        this.shifts = new ArrayList<>();
    }

    public Task(int id, String name, boolean completed, int quantity, int estimatedTime, boolean ready, User cooker) {
        this.id = id;
        this.name = name;
        this.completed = completed;
        this.quantity = quantity;
        this.estimatedTime = estimatedTime;
        this.ready = ready;
        this.cooker = cooker;
        this.procedures = new ArrayList<>();
        this.shifts = new ArrayList<>();
    }

    /* getter and setter */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getCooker() {
        return cooker;
    }

    public void setCooker(User cooker) {
        this.cooker = cooker;
    }

    public boolean isReady() {
        return ready;
    }

    public void setTaskReady(boolean isReady) {
        this.ready = isReady;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isCompleted() {
        return completed;
    }


    public void setTaskCompleted(boolean b) {
        this.completed = b;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public ArrayList<Procedure> getProcedures() {
        return procedures;
    }

    public void addProcedure(Procedure newProcedureToAdd) {
        this.procedures.add(newProcedureToAdd);
    }

    public ArrayList<Shift> getShifts() {
        return shifts;
    }

    public void addShift(Shift s) {
        this.shifts.add(s);
    }

    /* persistence */
    public static void addNewTaskToSummarySheet(SummarySheet sumSheet, Task t) {
        String taskName = t.getName();

        int completed;

        if (t.isCompleted()) {
            completed = 1;
        } else {
            completed = 0;
        }

        int taskQuantity = t.getQuantity();

        int estimatedTime = t.getEstimatedTime();

        int ready;

        if (t.isReady()) {
            ready = 1;
        } else {
            ready = 0;
        }

        int cookerId = t.getCooker().getId();

        int sumSheetId = sumSheet.getId();

        String insert = "INSERT INTO tasks (name, completed, quantity,  estimated_time, ready, cook_id, sum_sheet_id)" +
                " VALUES ('" + taskName + "', '" + completed + "', '" + taskQuantity + "', '" + estimatedTime + "', '" + ready + "', '" + cookerId + "', '" + sumSheetId + "')";

        PersistenceManager.executeUpdate(insert);

        t.setId(PersistenceManager.getLastId());
    }


    public static void addNewProcedureToTask(Task t, Procedure p) {
        if (t != null && p != null) {
            int procedureId = p.getId();

            int taskId = t.getId();

            String insert = "UPDATE procedures SET task_id = '" + taskId + "' WHERE id = '" + procedureId + "'";

            PersistenceManager.executeUpdate(insert);

            p.setId(PersistenceManager.getLastId());
        }
    }

    public static void removeProcedureToTask(Task t, Procedure p) {
        if (t != null && p != null) {
            int procedureId = p.getId();

            int taskId = t.getId();

            String insert = "UPDATE procedures SET task_id = NULL WHERE task_id = '" + taskId + "' AND id = '" + procedureId + "'";

            PersistenceManager.executeUpdate(insert);

            p.setId(PersistenceManager.getLastId());

        }
    }

    public static void deleteTask(SummarySheet sumSheet, Task t) {
        int sumSheetId = sumSheet.getId();

        String deleteTask = "DELETE FROM tasks WHERE sum_sheet_id = + '" + sumSheetId + "'";

        PersistenceManager.executeUpdate(deleteTask);

        int taskId = t.getId();

        String deleteProcedures = "DELETE FROM procedures WHERE task_id = + '" + taskId + "'";

        PersistenceManager.executeUpdate(deleteProcedures);
    }


    public static void addNewShiftToTask(Task t, Shift s) {
        int taskId = t.getId();

        int shiftId = s.getId();

        String insert = "INSERT INTO taskshifts (task_id, shift_id) VALUES ('" + taskId + "', '" + shiftId + "')";

        PersistenceManager.executeUpdate(insert);
    }

    public static void updateCookerToTask(Task t, User u) {
        int taskId = t.getId();

        int userId = u.getId();

        String query = "UPDATE tasks SET cook_id = '" + userId + "' WHERE id = '" + taskId + "'";

        PersistenceManager.executeUpdate(query);
    }


    public static void updateQuantityToTask(Task t, int quantity) {
        int taskId = t.getId();

        String query = "UPDATE tasks SET quantity = '" + quantity + "' WHERE id = '" + taskId + "'";

        PersistenceManager.executeUpdate(query);
    }

    public static void updateEstimatedTimeToTask(Task t, int estimatedTime) {
        int taskId = t.getId();

        String query = "UPDATE tasks SET estimated_time = '" + estimatedTime + "' WHERE id = '" + taskId + "'";

        PersistenceManager.executeUpdate(query);
    }


    public static void updateTaskReadyToTask(Task t, boolean isReady) {
        int taskId = t.getId();

        int var;

        if (isReady) {
            var = 1;
        } else {
            var = 0;
        }

        String query = "UPDATE tasks SET ready = '" + var + "' WHERE id = '" + taskId + "'";

        PersistenceManager.executeUpdate(query);
    }


    public static void updateTaskCompletedToTask(Task t, boolean isCompleted) {
        int taskId = t.getId();

        int var;

        if (isCompleted) {
            var = 1;
        } else {
            var = 0;
        }

        String query = "UPDATE tasks SET completed = '" + var + "' WHERE id = '" + taskId + "'";

        PersistenceManager.executeUpdate(query);
    }


    public static ArrayList<Task> getTasksBySummarySheetId(int sumSheetId) {
        ArrayList<Task> load = new ArrayList<>();

        String taskQuery = "SELECT * FROM tasks WHERE sum_sheet_id = '" + sumSheetId + "'";

        PersistenceManager.executeQuery(taskQuery, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                load.add(new Task(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getBoolean("completed"),
                        rs.getInt("quantity"),
                        rs.getInt("estimated_time"),
                        rs.getBoolean("ready"),
                        User.loadUserById(rs.getInt("cook_id"))
                ));
            }
        });

        return load;

    }

    /* misc */

    @Override
    public int compareTo(Task o) {
        return Integer.compare(this.estimatedTime, o.estimatedTime);
    }

    @Override
    public String toString() {
        return "\nIl compito " + this.name + " risulta essere " + (this.completed ? "completato " : "non completato ") +
                "\nLe quantita' da preparare sono: " + this.quantity + ". Il tempo di preparazione stimato e' pari a " + this.estimatedTime + "." +
                "\nQuesto compito" + (this.ready ? " e' pronto" : " non e' pronto") + "." + " Lo chef incaricato a svolgere questo e' : " + this.cooker +
                "\n" + (this.procedures.isEmpty() ? "Nessuna procedura di cucina relativa a questo compito" : "Procedura/e correlata/e: " + this.procedures) +
                "\n" + (this.shifts.isEmpty() ? "Nessun turno associato a questo compito" : ("I turno/i associato/i a questo compito sono: " + this.shifts)) + "\n";
    }

}
