import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.procedure.Procedure;
import businesslogic.summarySheet.SummarySheet;
import businesslogic.summarySheet.Task;
import businesslogic.user.User;

public class TestCatERingKitchen2c {
    public static void main(String[] args) {
        try {
            System.out.println("\nFAKE LOGIN");
            CatERing.getInstance().getUserManager().fakeLogin("Lidia");
            System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());

            System.out.println("\nCREATE SUMMARY SHEET");
            SummarySheet summarySheet = CatERing.getInstance().getSummarySheetManager().createSummarySheet("Primo foglio riepilogativo di Lidia", CatERing.getInstance().getEventManager().getEventInfo().get(0).getServices().get(0));

            System.out.println("\nCREATE TASK");
            Task firstNewTask = CatERing.getInstance().getSummarySheetManager().createTask("Preparare la polenta", false, 8, 60, false, User.loadUser("Piergiorgio"));

            System.out.println("\nCREATE PROCEDURE");
            CatERing.getInstance().getSummarySheetManager().setSelectedTask(firstNewTask);
            Procedure newProcedureRecipe = CatERing.getInstance().getSummarySheetManager().createProcedure("Nuova ricetta di cucina",
                    "Taglia il porro, scalda olio, metti il porro a cuocere a fuoco basso per 10 minuti coprendo il recipiente", CatERing.getInstance().getUserManager().getCurrentUser(), "Recipe");
            Procedure newProcedurePreparation = CatERing.getInstance().getSummarySheetManager().createProcedure("Nuova preparazione di cucina",
                    "Porta in ebollizione mezzo litro di acqua, prepara il riso, tosta il riso, sfiamma con vino bianco, aggiungi acqua", CatERing.getInstance().getUserManager().getCurrentUser(), "Preparation");
            System.out.println(summarySheet);

            System.out.println("\nTEST DELETE TASK");
            CatERing.getInstance().getSummarySheetManager().deleteTask(firstNewTask);
            System.out.println(summarySheet);

        } catch (UseCaseLogicException e) {
            System.out.println("Errore nella logica del caso d'uso");
        }
    }
}
