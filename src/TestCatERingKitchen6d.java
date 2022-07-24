import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.summarySheet.SummarySheet;
import businesslogic.summarySheet.Task;
import businesslogic.user.User;

public class TestCatERingKitchen6d {
    public static void main(String[] args) {
        try {
            System.out.println("\nFAKE LOGIN");
            CatERing.getInstance().getUserManager().fakeLogin("Lidia");
            System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());

            System.out.println("\nCREATE SUMMARY SHEET");
            SummarySheet summarySheet = CatERing.getInstance().getSummarySheetManager().createSummarySheet("Primo foglio riepilogativo di Lidia", CatERing.getInstance().getEventManager().getEventInfo().get(0).getServices().get(0));

            System.out.println("\nCREATE TASK");
            Task firstNewTask = CatERing.getInstance().getSummarySheetManager().createTask("Preparare la polenta", false, 8, 60, false, User.loadUser("Piergiorgio"));
            CatERing.getInstance().getSummarySheetManager().setSelectedTask(firstNewTask);
            System.out.println(CatERing.getInstance().getSummarySheetManager().getSelectedTask());

            System.out.println("\nTEST SET TASK READY");
            CatERing.getInstance().getSummarySheetManager().setReadyToTaskInEdit(true);
            System.out.println(CatERing.getInstance().getSummarySheetManager().getSelectedTask());

        } catch (UseCaseLogicException e) {
            System.out.println("Errore di logica nello use case");
        }
    }
}
