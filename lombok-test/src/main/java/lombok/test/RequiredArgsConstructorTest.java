package lombok.test;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName = "test")
public class RequiredArgsConstructorTest {
    private Long id;
    @NonNull
    private String name;
}
