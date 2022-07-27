package businesslogic.procedure;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.summarySheet.SummarySheet;
import businesslogic.summarySheet.Task;
import businesslogic.user.User;
import persistence.PersistenceManager;

import java.util.ArrayList;

public class ProcedureManager {
    private Procedure openProcedure;
    private Procedure procedureToCopy;
    private final ArrayList<ProcedureEventReciver> eventReceivers;

    public ProcedureManager() {
        this.eventReceivers = new ArrayList<>();
    }


    // OPERATION METHOD

    public Procedure createProcedure(String name, String instruction, User owner, String procedureType) throws UseCaseLogicException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();

        if (!user.isChef() || !user.isCooker()) {
            throw new UseCaseLogicException();
        }

        if (!procedureType.equals("Recipe") && !procedureType.equals("Preparation")) {
            throw new UseCaseLogicException();
        }

        Procedure p = new Procedure(name, instruction, owner, procedureType);

        if (procedureType.equals("Recipe")) {
            CatERing.getInstance().getRecipeManager().addRecipe(p);
        }

        notifyProcedureCreated(p);

        return p;
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

    private void notifyProcedureCreated(Procedure p) {
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
