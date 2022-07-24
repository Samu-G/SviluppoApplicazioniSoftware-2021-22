package persistence;

import businesslogic.procedure.Procedure;
import businesslogic.procedure.ProcedureEventReciver;

public class PersistenceProcedure implements ProcedureEventReciver {

    @Override
    public void updateProcedureCreated(Procedure procedure) {

    }

    @Override
    public void updateProcedureDeleted(Procedure procedure) {
        int procedureId = procedure.getId();

        String query = "DELETE FROM procedures WHERE id = '" + procedureId + "'";

        PersistenceManager.executeUpdate(query);
    }

}
