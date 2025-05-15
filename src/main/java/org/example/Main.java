package org.example;

import org.example.controller.ContaController;
import org.example.view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        ContaController controller = new ContaController();
        ConsoleView consoleView = new ConsoleView(controller);

        consoleView.iniciar();

    }
}