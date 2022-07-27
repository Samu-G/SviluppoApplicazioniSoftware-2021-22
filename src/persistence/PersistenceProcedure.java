package persistence;

import businesslogic.CatERing;
import businesslogic.procedure.Procedure;
import businesslogic.procedure.ProcedureEventReciver;

public class PersistenceProcedure implements ProcedureEventReciver {

    @Override
    public void updateProcedureCreated(Procedure p) {
        Procedure.createProcedure(p);
    }

    @Override
    public void updateProcedureDeleted(Procedure p) {
        Procedure.removeProcedure(p);
    }

}
