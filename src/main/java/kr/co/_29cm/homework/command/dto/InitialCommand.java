package kr.co._29cm.homework.command.dto;

import kr.co._29cm.homework.command.enums.InitialCommandEnum;
import kr.co._29cm.homework.global.exception.InvalidCommandException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@NoArgsConstructor
@Getter
public class InitialCommand {
    private String command;

    public void input(String command) {
        Arrays.stream(InitialCommandEnum.values())
                .filter(initialCommandEnum -> command == null || initialCommandEnum.value().equals(command.toUpperCase()))
                .findAny()
                .orElseThrow(InvalidCommandException::new);

        this.command = command;
    }

    public boolean isTerminated() {
        return command.equals("q");
    }

}
