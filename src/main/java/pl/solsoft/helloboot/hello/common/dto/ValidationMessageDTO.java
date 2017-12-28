package pl.solsoft.helloboot.hello.common.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValidationMessageDTO {

	private List<String> messages;
}