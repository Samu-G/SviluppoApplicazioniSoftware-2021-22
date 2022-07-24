import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.summarySheet.SummarySheet;

public class TestCatERingKitchen1b {
    public static void main(String[] args) {
        try {
            System.out.println("\nFAKE LOGIN");
            CatERing.getInstance().getUserManager().fakeLogin("Lidia");
            System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());

            SummarySheet summarySheet = CatERing.getInstance().getSummarySheetManager().createSummarySheet("Foglio riepilogativo da bloccare di Lidia", CatERing.getInstance().getEventManager().getEventInfo().get(0).getServices().get(2));

            System.out.println("\nSUMMARY SHEET CREATED");
            System.out.println(summarySheet);

            System.out.println("\nTEST LOCK SUMMARY SHEET");
            CatERing.getInstance().getSummarySheetManager().lockSummarySheet(summarySheet);
            System.out.println(summarySheet);

        } catch (UseCaseLogicException e) {
            System.out.println("Errore di logica nello use case");
        }
    }
}
