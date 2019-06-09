package ro.ilies.bogdan;

import ro.ilies.bogdan.command.CreateCommand;
import ro.ilies.bogdan.command.LineCommand;
import ro.ilies.bogdan.command.RectangleCommand;
import ro.ilies.bogdan.drawing.Canvas;
import ro.ilies.bogdan.exception.CommandNotFoundException;
import ro.ilies.bogdan.exception.ParseException;
import ro.ilies.bogdan.exception.UninitializedCanvasException;
import ro.ilies.bogdan.exception.ValidationException;
import ro.ilies.bogdan.state.CanvasPrinter;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Console drawing app!
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Welcome to my drawing app!");
        Scanner scanner = new Scanner(System.in);
        Canvas canvas = new Canvas();
        Pattern commandPattern = Pattern.compile("([^\\s]+)\\s*(.*)");

        while (true) {
            System.out.println("enter command: ");
            String inputCommand = scanner.nextLine();
            if (inputCommand == null || inputCommand.length() == 0) {
                System.out.println("Invalid command");
                continue;
            }

            Matcher commandMatcher = commandPattern.matcher(inputCommand);
            if (!commandMatcher.matches()) {
                System.out.println("Invalid command");
                continue;
            }

            String commandType = commandMatcher.group(1);
            String commandParams = commandMatcher.group(2);

            if ("Q".equalsIgnoreCase(commandType.trim())) {
                System.out.println("Good bye!");
                break;
            }

            processCommand(commandType, commandParams, canvas);
        }
    }

    private static void processCommand(String commandType, String commandParams, Canvas canvas) {
        try {
            executorProvider(commandType.trim().toUpperCase()).accept(commandParams, canvas);
            CanvasPrinter.printer().accept(canvas);
        } catch (CommandNotFoundException e) {
            System.out.println("Unknown command.");
        } catch (UninitializedCanvasException e) {
            System.out.println("Please create canvas first.");
        } catch (ParseException e) {
            System.out.println("Bad command parameters.");
        } catch (ValidationException e) {
            System.out.println("Invalid parameters. Make sure you follow specifications and stay within canvas boundaries.");
        } catch (Exception e) {
            System.out.println("This is embarrassing. Failed to process command.");
        }
    }

    private static BiConsumer<String, Canvas> executorProvider(String type) {
        Map<String, BiConsumer<String, Canvas>> commandExecutors = new HashMap<>();
        commandExecutors.put("C", CreateCommand.command());
        commandExecutors.put("L", LineCommand.command());
        commandExecutors.put("R", RectangleCommand.command());
        return Optional.ofNullable(commandExecutors.get(type))
                .orElseThrow(() -> new CommandNotFoundException("Unsupported command."));
    }
}
