import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.summarySheet.SummarySheet;

public class TestCatERingKitchen1a {
    public static void main(String[] args) {
        try {
            System.out.println("\nFAKE LOGIN");
            CatERing.getInstance().getUserManager().fakeLogin("Lidia");
            System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());

            SummarySheet summarySheet = CatERing.getInstance().getSummarySheetManager().createSummarySheet("Foglio riepilogativo da aprire di Lidia", CatERing.getInstance().getEventManager().getEventInfo().get(1).getServices().get(0));

            System.out.println("\nSUMMARY SHEET CREATED");
            System.out.println(summarySheet);

            System.out.println("\nTEST GET SUMMARY SHEET");
            CatERing.getInstance().getSummarySheetManager().openSummarySheet(CatERing.getInstance().getEventManager().getEventInfo().get(1).getServices().get(0));
            System.out.println(CatERing.getInstance().getSummarySheetManager().getCurrentSummarySheet());

        } catch (UseCaseLogicException e) {
            System.out.println("Errore di logica nello use case");
        }
    }
}
