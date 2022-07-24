import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.summarySheet.SummarySheet;

public class TestCatERingKitchen1d {
    public static void main(String[] args) {
        try {
            System.out.println("\nFAKE LOGIN");
            CatERing.getInstance().getUserManager().fakeLogin("Lidia");
            System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());

            SummarySheet summarySheet = CatERing.getInstance().getSummarySheetManager().createSummarySheet("Foglio riepilogativo da cancellare di Lidia", CatERing.getInstance().getEventManager().getEventInfo().get(0).getServices().get(1));

            System.out.println("\nSUMMARY SHEET CREATED");
            System.out.println(summarySheet);

            System.out.println("\nDELETE SUMMARY SHEET");
            CatERing.getInstance().getSummarySheetManager().deleteSummarySheet(summarySheet);

        } catch (UseCaseLogicException e) {
            System.out.println("Errore di logica nello use case");
        }
    }
}
