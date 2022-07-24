import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.summarySheet.SummarySheet;
import businesslogic.summarySheet.Task;
import businesslogic.user.User;

public class TestCatERingKitchen2b {
    public static void main(String[] args) {
        try {
            System.out.println("\nFAKE LOGIN");
            CatERing.getInstance().getUserManager().fakeLogin("Lidia");
            System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());

            SummarySheet summarySheet = CatERing.getInstance().getSummarySheetManager().createSummarySheet("Foglio riepilogativo da aprire di Lidia", CatERing.getInstance().getEventManager().getEventInfo().get(1).getServices().get(0));
            Task firstNewTask = CatERing.getInstance().getSummarySheetManager().createTask("Preparare la polenta", false, 8, 60, false, User.loadUser("Piergiorgio"));

            System.out.println("\nSUMMARY SHEET CREATED");
            System.out.println(summarySheet);

            System.out.println("\nSELECTED TASK");
            CatERing.getInstance().getSummarySheetManager().getCurrentSummarySheet().setTaskInEdit(firstNewTask);
            System.out.println(CatERing.getInstance().getSummarySheetManager().getCurrentSummarySheet().getTaskInEdit());

            System.out.println("\nTEST SET TASK DONE");
            CatERing.getInstance().getSummarySheetManager().setCompletedToTaskInEdit(true);
            System.out.println(CatERing.getInstance().getSummarySheetManager().getCurrentSummarySheet().getTaskInEdit());

        } catch (UseCaseLogicException e) {
            System.out.println("Errore nella logica del caso d'uso");
        }
    }
}
