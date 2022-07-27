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
            Procedure newProcedureRecipe = CatERing.getInstance().getProcedureManager().createProcedure("Nuova ricetta di cucina",
                    "Taglia il porro, scalda olio, metti il porro a cuocere a fuoco basso per 10 minuti coprendo il recipiente", CatERing.getInstance().getUserManager().getCurrentUser(), "Recipe");
            Procedure newProcedurePreparation = CatERing.getInstance().getProcedureManager().createProcedure("Nuova preparazione di cucina",
                    "Porta in ebollizione mezzo litro di acqua, prepara il riso, tosta il riso, sfiamma con vino bianco, aggiungi acqua", CatERing.getInstance().getUserManager().getCurrentUser(), "Preparation");
            CatERing.getInstance().getSummarySheetManager().setSelectedTask(firstNewTask);
            CatERing.getInstance().getSummarySheetManager().addProcedure(newProcedureRecipe);
            CatERing.getInstance().getSummarySheetManager().addProcedure(newProcedurePreparation);

            System.out.println("\n TEST REMOVE RECIPE");
            CatERing.getInstance().getSummarySheetManager().removeProcedure(newProcedureRecipe);
            System.out.println(summarySheet);

            System.out.println("\n TEST REMOVE PREPARATION");
            CatERing.getInstance().getSummarySheetManager().removeProcedure(newProcedurePreparation);
            System.out.println(summarySheet);

        } catch (UseCaseLogicException e) {
            System.out.println("Errore di logica nello use case");
        }
    }
}
