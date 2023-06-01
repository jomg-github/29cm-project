package kr.co._29cm.homework.command.dto;

import kr.co._29cm.homework.global.exception.InvalidCommandException;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class OrderCommand {
    private String productIdCommand;
    private String orderProductQuantityCommand;

    public void inputProductId(String command) {
        if (!command.isBlank()) {
            try {
                Long.parseLong(command);
            } catch (NumberFormatException e) {
                throw new InvalidCommandException();
            }
        }

        productIdCommand = command;
    }

    public void inputOrderProductQuantityCommand(String command) {
        if (!command.isBlank()) {
            try {
                Integer.parseInt(command);
            } catch (NumberFormatException e) {
                throw new InvalidCommandException();
            }
        }

        orderProductQuantityCommand = command;
    }

    public boolean isTerminated() {
        return productIdCommand.equals(" ") && orderProductQuantityCommand.equals(" ");
    }

    public Long getProductId() {
        return Long.parseLong(productIdCommand);
    }

    public Integer getOrderProductQuantity() {
        return Integer.parseInt(orderProductQuantityCommand);
    }
}
