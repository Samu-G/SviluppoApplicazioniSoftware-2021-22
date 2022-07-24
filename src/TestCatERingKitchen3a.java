import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.procedure.Procedure;
import businesslogic.summarySheet.SummarySheet;
import businesslogic.summarySheet.Task;
import businesslogic.user.User;

public class TestCatERingKitchen3a {
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

            System.out.println("\n TEST DELETE PROCEDURE RECIPE");
            CatERing.getInstance().getProcedureManager().deleteProcedure(newProcedureRecipe);
            System.out.println(summarySheet);

            System.out.println("\n TEST DELETE PROCEDURE PREPARATION");
            CatERing.getInstance().getProcedureManager().deleteProcedure(newProcedurePreparation);
            System.out.println(summarySheet);

        } catch (UseCaseLogicException e) {
            System.out.println("Errore di logica nello use case");
        }
    }
}
