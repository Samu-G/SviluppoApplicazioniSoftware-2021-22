import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.procedure.Procedure;
import businesslogic.shift.Shift;
import businesslogic.summarySheet.SummarySheet;
import businesslogic.summarySheet.Task;
import businesslogic.user.User;

import java.util.ArrayList;


public class TestCatERingKitchen {
    public static void main(String[] args) {
        try {
            System.out.println("TEST FAKE LOGIN");
            CatERing.getInstance().getUserManager().fakeLogin("Lidia");
            System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());

            System.out.println("\nTEST CREATE SUMMARY SHEET");
            SummarySheet summarySheet = CatERing.getInstance().getSummarySheetManager().createSummarySheet("Primo foglio riepilogativo di Lidia", CatERing.getInstance().getEventManager().getEventInfo().get(0).getServices().get(0));

            System.out.println("\nTEST CREATE TASK");
            Task firstNewTask = CatERing.getInstance().getSummarySheetManager().createTask("Preparare la polenta", false, 8, 60, false, User.loadUser("Piergiorgio"));
            Task secondNewTask = CatERing.getInstance().getSummarySheetManager().createTask("Preparare lo crema di funghi", true, 15, 45, false, User.loadUser("Silvia"));
            Task thirdNewTask = CatERing.getInstance().getSummarySheetManager().createTask("Preparare la carne di capriolo", true, 8, 120, true, User.loadUser("Guido"));
            System.out.println(CatERing.getInstance().getSummarySheetManager().getCurrentSummarySheet());

            System.out.println("\nTEST CREATE PROCEDURE");
            CatERing.getInstance().getSummarySheetManager().setSelectedTask(thirdNewTask);
            Procedure newProcedureRecipe = CatERing.getInstance().getSummarySheetManager().createProcedure("Nuova ricetta di cucina",
                    "Taglia il porro, scalda olio, metti il porro a cuocere a fuoco basso per 10 minuti coprendo il recipiente", CatERing.getInstance().getUserManager().getCurrentUser(), "Recipe");
            Procedure newProcedurePreparation = CatERing.getInstance().getSummarySheetManager().createProcedure("Nuova preparazione di cucina",
                    "Porta in ebollizione mezzo litro di acqua, prepara il riso, tosta il riso, sfiamma con vino bianco, aggiungi acqua", CatERing.getInstance().getUserManager().getCurrentUser(), "Preparation");
            System.out.println(CatERing.getInstance().getSummarySheetManager().getSelectedTask());

            System.out.println("\nTEST SORT TASK");
            CatERing.getInstance().getSummarySheetManager().arrangeTasks();
            System.out.println(CatERing.getInstance().getSummarySheetManager().getCurrentSummarySheet().getTasks());

            System.out.println("\nTEST GET SHIFT BOARD");
            ArrayList<Shift> shiftBoard = CatERing.getInstance().getShiftManager().getShiftsBoard();
            System.out.println(shiftBoard);

            System.out.println("\nTEST ASSIGN SHIFT TO TASK");
            CatERing.getInstance().getSummarySheetManager().setSelectedTask(thirdNewTask);
            CatERing.getInstance().getSummarySheetManager().assignShiftToTaskInEdit(CatERing.getInstance().getShiftManager().getShiftsBoard().get(0));
            CatERing.getInstance().getSummarySheetManager().assignShiftToTaskInEdit(CatERing.getInstance().getShiftManager().getShiftsBoard().get(1));
            System.out.println(CatERing.getInstance().getSummarySheetManager().getSelectedTask());

        } catch (UseCaseLogicException e) {
            System.out.println("Errore di logica nello use case");
        }
    }
}
