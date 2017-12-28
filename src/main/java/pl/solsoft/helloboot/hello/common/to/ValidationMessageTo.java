package pl.solsoft.helloboot.hello.common.to;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValidationMessageTo {

	private List<String> messages;
}