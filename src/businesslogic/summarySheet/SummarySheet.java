package businesslogic.summarySheet;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.event.ServiceInfo;
import businesslogic.user.User;
import persistence.PersistenceManager;
import persistence.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SummarySheet {

    private int id;

    private String name;

    private boolean locked;

    private User owner;

    private Task taskInEdit;

    private ArrayList<Task> tasks;

    private ServiceInfo serviceCorrelated;

    private boolean inEdit;

    public SummarySheet() {
        this.id = -1;
        this.name = "";
        this.taskInEdit = null;
        this.locked = false;
        this.owner = CatERing.getInstance().getUserManager().getCurrentUser();
        this.tasks = new ArrayList<>();
        this.serviceCorrelated = null;
    }

    public SummarySheet(String name, User owner, ServiceInfo service) {
        this.name = name;
        this.taskInEdit = null;
        this.locked = false;
        this.owner = owner;
        this.tasks = new ArrayList<>();
        this.serviceCorrelated = service;
    }

    public static void loadSummarySheetByService(ServiceInfo service) {
        int serviceId = service.getId();

        SummarySheet summarySheet = new SummarySheet();

        String query = "SELECT * FROM summarysheet WHERE service_id = '" + serviceId + "'";

        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                int id = rs.getInt("id");
                if (id < 0) {
                    throw new SQLException();
                } else {
                    try {
                        summarySheet.setId(rs.getInt("id"));
                        summarySheet.setName(rs.getString("name"));
                        summarySheet.setServiceCorrelated(service);
                        summarySheet.setOwner(User.loadUserById(rs.getInt("owner_id")));
                        summarySheet.setTasks(Task.getTasksBySummarySheetId(rs.getInt("id")));
                        summarySheet.setLockOnSummarySheet(rs.getInt("locked"));
                    } catch (UseCaseLogicException e) {
                        System.out.println("Errore nella logica del caso d'uso");
                    }
                }
            }
        });

        try {
            CatERing.getInstance().getSummarySheetManager().setCurrentSummarySheet(summarySheet);
        } catch (UseCaseLogicException e) {
            System.out.println("Nessun foglio riepilogativo collegato al servizio: Errore di logica del Caso d'Uso");
        }
    }


    /* Getter */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Task getTaskInEdit() {
        return this.taskInEdit;
    }

    public boolean isLocked() {
        return this.locked;
    }

    public User getOwner() {
        return owner;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public ServiceInfo getServiceCorrelated() {
        return serviceCorrelated;
    }

    /* Setter */

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTaskInEdit(Task task) {
        boolean found = false;

        for (Task value : this.tasks) {
            if (task.getName().equals(value.getName())) {
                this.taskInEdit = value;
                found = true;
                break;
            }
        }

        if (!found) {
            this.taskInEdit = null;
        }
    }

    public void setLockOnSummarySheet(int value) throws UseCaseLogicException {
        switch (value) {
            case 0 -> this.locked = false;
            case 1 -> this.locked = true;
            default -> throw new UseCaseLogicException();
        }
    }

    public void setInEdit(boolean inEdit) {
        this.inEdit = inEdit;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void setServiceCorrelated(ServiceInfo service) {
        this.serviceCorrelated = service;
    }

    public void sortTask() {
        tasks.sort(Task::compareTo);
    }

    /* Persistence */
    public static void saveNewSummarySheet(SummarySheet sumSheet) {
        String name = sumSheet.getName();
        int user_id = CatERing.getInstance().getUserManager().getCurrentUser().getId();
        int service_id = sumSheet.getServiceCorrelated().getId();
        int locked = 0;
        String query = "INSERT INTO summarysheet (name, owner_id, service_id, locked) " +
                "VALUES ('" + name + "', '" + user_id + "', '" + service_id + "' , '" + locked + "');";
        try {
            int result = PersistenceManager.executeUpdate(query);
            if (result == 1) {
                sumSheet.id = getSummarySheetByName(sumSheet.name).id;
                CatERing.getInstance().getSummarySheetManager().setCurrentSummarySheet(sumSheet);
                CatERing.getInstance().getSummarySheetManager().getCurrentSummarySheet().setInEdit(true);
            } else if (result == 0) {
                CatERing.getInstance().getSummarySheetManager().setCurrentSummarySheet(null);
            }
        } catch (UseCaseLogicException e) {
            System.out.println("Errore di Logica del Caso d'Uso");
        }
    }


    public static SummarySheet getSummarySheetByName(String name) {
        SummarySheet result = new SummarySheet();

        String summarySheetQuery = "SELECT * FROM Summarysheet WHERE name = '" + name + "'";

        PersistenceManager.executeQuery(summarySheetQuery, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                result.setId(rs.getInt("id"));
            }
        });

        if (result.getId() == -1) {
            return null;
        }

        return result;
    }
    public static void deleteSummarySheet(SummarySheet sumSheet) {
        int sumSheetId = sumSheet.getId();

        String query = "DELETE FROM summarysheet WHERE id = '" + sumSheetId + "'";

        PersistenceManager.executeUpdate(query);
    }


    public static void lockSummarySheet(SummarySheet sumSheet) {
        int sumSheetId = sumSheet.getId();

        String query = "UPDATE summarysheet SET locked = 1 WHERE id = '" + sumSheetId + "'";

        PersistenceManager.executeUpdate(query);

        sumSheet.setLocked(true);
    }


    public static void unlockSummarySheet(SummarySheet sumSheet) {
        int sumSheetId = sumSheet.getId();

        String query = "UPDATE summarysheet SET locked = 0 WHERE id = '" + sumSheetId + "'";

        PersistenceManager.executeUpdate(query);

        sumSheet.setLocked(false);
    }

    /* Misc */



    @Override
    public String toString() {
        return this.name +
                "\nAutore: " + this.owner.getUserName() +
                (this.locked ? "\nIl foglio riepilogativo e' bloccato quindi saturo di compiti " : " ") +
                "\nCompito/i correlato/i: " + (this.tasks.size() == 0 ? "Nessuno" : tasks.toString()) +
                "\nServizo correlato: " + this.getServiceCorrelated() +
                "\nCompito in modifica: " + (taskInEdit == null ? "Nessuno" : taskInEdit.toString()) + "\n";
    }

}
